/*----------------------------------------------------------------------------*/
/*                                                                            */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.SPathSubsystem;

import java.util.function.DoubleSupplier;

import org.team3467.robot2020.Constants.SPathConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PCPathStateMachine extends CommandBase
{

    // Command States
    private enum eCmdState
    {
        Initial, /* The initial/entry state. Initializes the PCPSM and immediately goes to Waiting */
        Waiting_OnEntry, /* On Entry code for the Waiting state */
        Waiting, /* The state when we either have a PC ready to shoot, or we are waiting for new PCs to enter the CD7 */
        Admit_One_OnEntry, /* On Entry code for the AdmitOne state */
        Admit_One, /* The state where a single PC is being inducted into the S-Path */
        Run_S_Path_OnEntry, /* On Entry code for the Run_S_Path state */
        Run_S_Path; /* The state where a newly-inducted PC is being positioned in the S-Path */
    }

    private final SPathSubsystem m_sPath;
    private final DoubleSupplier m_pathSpeed;
    private final DoubleSupplier m_gateRevSpeed;

    private eCmdState m_currentState;
    private boolean m_BB2_IsTripped;

    protected Timer m_timer = new Timer();
    private double m_duration;

    /**
     * Creates a new PCPathStateMachine.
     */
    public PCPathStateMachine(SPathSubsystem sPathSubsys, final DoubleSupplier pathSpeed, final DoubleSupplier gateReverseSpeed)
    {
        m_sPath = sPathSubsys;
        m_pathSpeed = pathSpeed;
        m_gateRevSpeed = gateReverseSpeed;
        addRequirements(sPathSubsys);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize()
    {
        m_currentState = changeState(eCmdState.Initial);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute()
    {
        // First, Process manual commands, and if either is activated, skip the state machine and return.
        double pathSpeed = m_pathSpeed.getAsDouble();
        double gateRevSpeed = m_gateRevSpeed.getAsDouble();
        if (Math.abs(pathSpeed) > 0.2 || Math.abs(gateRevSpeed) > 0.2)
        {
            m_sPath.driveSPath(pathSpeed > 0.2 ? pathSpeed : 0.0);
            m_sPath.driveGate(gateRevSpeed > 0.2 ? (1.0 * gateRevSpeed) : 0.0);

            // Change command state
            m_currentState = changeState(eCmdState.Initial);
            return;
        }

        // Now run the State Machine
        switch (m_currentState)
        {
        case Initial:

            // This is the initial/entry state. It initializes the PCPSM and immediately goes to the Waiting state
            m_currentState = changeState(eCmdState.Waiting_OnEntry);

            break;

        case Waiting_OnEntry:

            // Stop S-Path Gate
            m_sPath.driveGate(0.0);

            // Stop S-Path Transport
            m_sPath.driveSPath(0.0);

            m_currentState = changeState(eCmdState.Waiting);

            // fall thru

        case Waiting:

            // This is the state when we either have a PC ready to shoot, or we are waiting for new PCs to enter the CD7

            // If BeamBreak3 is tripped, then we have a PC at the Shooter Gate, and everything stops until a PC is shot
            if (m_sPath.m_beamBreak3.GetStatus() == true)
            {
                // Stay in this state until the PC is shot, because nothing else can come into the S-Path until this PC exits.
                // NOTE: Moving the S-Path control stick will also cause an exit from this state on the next loop.

                // Just start the Wait loop over again
                m_currentState = changeState(eCmdState.Waiting_OnEntry);

                // Signal that Shooter is "armed" using LEDs, Shuffleboard indicator, etc.
                // <code>

            }
            else
            {
                // Signal that Shooter is "NOT armed" using LEDs, Shuffleboard indicator, etc.
                // <code>

                // If BeamBreak0 is tripped, there is a PC waiting at the S-Path Gate
                if (m_sPath.m_beamBreak0.GetStatus() == true)
                {
                    m_currentState = changeState(eCmdState.Admit_One_OnEntry);
                }
            }

            break;

        case Admit_One_OnEntry:

            // Start 30 Second Timer
            m_timer.reset();
            m_timer.start();
            m_duration = 30;

            // Run Path Gate (in)
            m_sPath.driveGate(SPathConstants.kGateInputSpeed);

            m_currentState = changeState(eCmdState.Admit_One);

            // fall thru

        case Admit_One:

            // This is the state where a single PC is being inducted into the S-Path

            if (m_timer.hasPeriodPassed(m_duration))
            {
                // Loop is timed out; no PCs available
                // Stop Timer
                m_timer.stop();
                m_currentState = changeState(eCmdState.Waiting_OnEntry);

            }
            else if (m_sPath.m_beamBreak1.GetStatus() == true)
            {
                // We have a PC in the S-Path now, ready to process

                // Stop Timer
                m_timer.stop();

                // Stop Path Gate
                m_sPath.driveGate(0.0);

                m_currentState = changeState(eCmdState.Run_S_Path_OnEntry);
            }

            break;

        case Run_S_Path_OnEntry:

            // If BeamBreak2 is tripped, because of a previous PC in the Path,
            // set a flag to wait for it to clear before checking it again for a trip
            if (m_sPath.m_beamBreak2.GetStatus() == true)
            {
                m_BB2_IsTripped = true;
            }

            // Run S-Path Transport
            m_sPath.driveSPath(SPathConstants.kPathInputSpeed);

            m_currentState = changeState(eCmdState.Run_S_Path);

            // fall thru

        case Run_S_Path:

            // This is the state where a newly-inducted PC is being positioned in the S-Path

            // If BeamBreak3 is tripped:
            if (m_sPath.m_beamBreak3.GetStatus() == true)
            {
                // Now there is a PC waiting at the Shooter Gate

                // Stop S-Path Transport
                m_sPath.driveSPath(0.0);

                m_currentState = changeState(eCmdState.Waiting_OnEntry);
            }
            else if (m_BB2_IsTripped)
            {
                // If BB2 was tripped when we started, then wait for it to clear before taking further action
                if (m_sPath.m_beamBreak2.GetStatus() == false)
                {
                    m_BB2_IsTripped = false;
                }
            }
            else if (m_sPath.m_beamBreak2.GetStatus() == true)
            {
                // BB2 has been tripped by the new PC, so stop the SPath and go back to induct another

                // Stop S-Path Transport
                m_sPath.driveSPath(0.0);

                m_currentState = changeState(eCmdState.Admit_One_OnEntry);
            }

            break;

        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted)
    {
        // Stop S-Path Gate
        m_sPath.driveGate(0.0);

        // Stop S-Path Transport
        m_sPath.driveSPath(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }

    /**
     * changeState() - Method for changing state and logging the transition
     */
    private eCmdState changeState(eCmdState newState)
    {
        // Add some sort of logging call here
        return newState;
    }
}
