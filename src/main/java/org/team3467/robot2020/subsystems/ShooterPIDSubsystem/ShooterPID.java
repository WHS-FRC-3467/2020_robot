/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterPIDSubsystem;

import org.team3467.robot2020.Constants.ShooterConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterPID extends CommandBase {
  ISpeedControl m_speedControl;
  Gains m_gains;

  /**
   * Creates a new ShooterPID.
   */
  public ShooterPID() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    if (ShooterConstants.useFalcons == true)
    {
        m_speedControl = new FalconVelocityPIDF();
        m_gains = ShooterConstants.kGains_Falcon;
    }
    else
    {
        m_speedControl = new NEOVelocityPIDF();
        m_gains = ShooterConstants.kGains_NEO;
    }    

    /* Initialize Smart Dashboard display */
    SmartDashboard.putNumber("P Gain", m_gains.kP);
    SmartDashboard.putNumber("I Gain", m_gains.kI);
    SmartDashboard.putNumber("D Gain", m_gains.kD);
    SmartDashboard.putNumber("Feed Forward", m_gains.kF);

    SmartDashboard.putNumber("Target Velocity", 0);
    SmartDashboard.putNumber("Current Velocity", 0);
    SmartDashboard.putNumber("Current Output Percent", 0);
    SmartDashboard.putNumber("Error", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double kP = SmartDashboard.getNumber("P Gain", 0);
    double kI = SmartDashboard.getNumber("I Gain", 0);
    double kD = SmartDashboard.getNumber("D Gain", 0);
    double kF = SmartDashboard.getNumber("Feed Forward", 0);

       // Update gains on the controller
    m_speedControl.updateGains(kP, kI, kD, kF);

    // Get desired m_velocity in RPM from SmartDasboard
    double targetVelocity = SmartDashboard.getNumber("Target Velocity", 0);
    
    // Update the target velocity and get back the current velocity
    int currentVelocity = m_speedControl.runVelocityPIDF(targetVelocity);

    // Show the Current Velocity, Error, and Current Output Percent on the SDB
    SmartDashboard.putNumber("Current Velocity", currentVelocity);
    SmartDashboard.putNumber("Error", m_speedControl.getError());
    SmartDashboard.putNumber("Current Output Percent", m_speedControl.getOutputPercent());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
