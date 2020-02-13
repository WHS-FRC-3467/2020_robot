/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {
  DriveSubsystem m_drive;
  double m_inches;
  Boolean finished;
  public DriveDistance(DriveSubsystem driveSubsys, Double inches) {
    m_inches = inches;
    m_drive = driveSubsys;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoders();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ticks = m_drive.getAverageEncoderDistance();
    double distance =  (ticks/2048) * (11.28) * (18.85);
    if (distance < m_inches){
      m_drive.getRightTalon().set(ControlMode.PercentOutput, 0.5);
      m_drive.getLeftTalon().set(ControlMode.PercentOutput, 0.5);
    }
    else{
      m_drive.getRightTalon().set(ControlMode.PercentOutput, 0.0);
      m_drive.getLeftTalon().set(ControlMode.PercentOutput, 0.0);
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.getRightTalon().set(ControlMode.PercentOutput, 0.0);
    m_drive.getLeftTalon().set(ControlMode.PercentOutput, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (finished = true){
    return true;
    }
    else {
      return false;
    }
  }
}
