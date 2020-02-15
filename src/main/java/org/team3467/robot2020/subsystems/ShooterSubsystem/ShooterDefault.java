/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;

import org.team3467.robot2020.subsystems.ShooterSubsystem.ShooterSubsystem;

public class ShooterDefault extends CommandBase
{
    private final ShooterSubsystem m_shooterSub;
    private final DoubleSupplier m_gateSpeed;
  
  
    public ShooterDefault(final ShooterSubsystem shooterSubsys, final DoubleSupplier gateSpeed) {
      m_gateSpeed = gateSpeed;
      m_shooterSub = shooterSubsys;

      addRequirements(m_shooterSub);
    }
  
    // Called when the command is initially scheduled.
    @Override
      public void execute()
      {
        if (Math.abs(m_gateSpeed.getAsDouble()) > 0.1){
          m_shooterSub.runShooterGate(m_gateSpeed.getAsDouble());
        }
        else{
          m_shooterSub.runShooterGate(0.0);
        }
      }
}
