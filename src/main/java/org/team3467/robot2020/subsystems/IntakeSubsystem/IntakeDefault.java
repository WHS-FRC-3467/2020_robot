/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import org.team3467.robot2020.control.XboxController;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeDefault extends CommandBase
{
    private final IntakeSubsystem m_intake;
    private final XboxController m_controller;
    private final DoubleSupplier m_intakeSpeed;

    public IntakeDefault(final IntakeSubsystem subsystem, final XboxController controller, final DoubleSupplier intakeSpeed)
    {
        m_intake = subsystem;
        m_controller = controller;
        m_intakeSpeed = intakeSpeed;
        addRequirements(m_intake);
    }

    @Override
    public void execute()
    {
        if (m_controller.getBumper(GenericHID.Hand.kLeft) && m_controller.getBumper(GenericHID.Hand.kRight))
        {
            m_intake.driveIntake(0);
        }
        else if (m_controller.getBumper(GenericHID.Hand.kLeft))
        {
            m_intake.driveIntake(-1.0);
        }
        else if (m_controller.getBumper(GenericHID.Hand.kRight))
        {
            m_intake.driveIntake(1.0);
        }
        else
        {
            m_intake.driveIntake(0);
        }
        m_intake.driveBelts(-1.0 * m_intakeSpeed.getAsDouble());
    }
}
