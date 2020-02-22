/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterGateSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.function.DoubleSupplier;

import org.team3467.robot2020.Constants;

public class GateDefault extends CommandBase
{
    private final GateSubsystem m_gateSub;
    private final DoubleSupplier m_runGate;
    private final DoubleSupplier m_runGateMultiple;
  
  
    public GateDefault(final GateSubsystem gateSubsys, final DoubleSupplier runGate, final DoubleSupplier runGateMultiple) {
      m_runGate = runGate;
      m_gateSub = gateSubsys;
      m_runGateMultiple = runGateMultiple;

      addRequirements(gateSubsys);
    }
  
    // Called when the command is initially scheduled.
    @Override
      public void execute()
      {
        if (m_runGate.getAsDouble() > 0.3) {
          new runShooterGate(m_gateSub, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime).schedule();
          new WaitCommand(2).schedule();
        } else if (m_runGateMultiple.getAsDouble() > 0.3) {
          new runShooterGate(m_gateSub, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime).schedule();
          new WaitCommand(0.25).schedule();
        }
        else {
          m_gateSub.runShooterGate(0.0);
        }
      }
}
