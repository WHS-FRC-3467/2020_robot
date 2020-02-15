/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class runManual extends CommandBase {
  ShooterSubsystem m_shooter;

  public runManual(ShooterSubsystem shooterSubsys)
  {
      m_shooter = shooterSubsys;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
      m_shooter.runManual();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean isFinished)
  {
    m_shooter.runShooter(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
      return false;
  }
}
