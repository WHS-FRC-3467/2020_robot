/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterHoodSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team3467.robot2020.Constants.CanConstants;
import org.team3467.robot2020.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.team3467.robot2020.Gains;

public class HoodSubsystem extends SubsystemBase
{
    TalonMagicMotion m_shooterHood = new TalonMagicMotion(CanConstants.shooter_hood);
    Gains m_hoodGains;
    public HoodSubsystem()
    {


        // Get the PIDF gains for the Shooter Hood
        m_hoodGains = ShooterConstants.kGains_Hood;

        // Initialize the Shooter Hood

        /* Initialize Smart Dashboard display */
       
        SmartDashboard.putNumber("Hood P Gain", m_hoodGains.kP);
        SmartDashboard.putNumber("Hood I Gain", m_hoodGains.kI);
        SmartDashboard.putNumber("Hood D Gain", m_hoodGains.kD);
        SmartDashboard.putNumber("Hood F Gain", m_hoodGains.kF);
        SmartDashboard.putNumber("Hood Position", 0);
        SmartDashboard.putNumber("Hood Setpoint", 0);
        SmartDashboard.putNumber("Hood Error", 0);

    }

    /*
     *
     *  Shooter Hood control
     * 
     */
    /**
     * void runShooterHood() - move Shooter Hood manually with stick control
    */
    
    public void runShooterHood(double speed)
    {
        m_shooterHood.set(ControlMode.PercentOutput, speed);
        SmartDashboard.putNumber("Hood Position", m_shooterHood.getSelectedSensorPosition());
    }
    

    public void runShooterHoodUp()
    {
        runShooterHood(-0.2);
    }

    public void runShooterHoodDown()
    {
        runShooterHood(0.2);
    }

    public void stopShooterHood()
    {
        runShooterHood(0.0);
    }
    
    /**
     * void positionManualHood() - move Shooter Hood to position commanded by Shuffleboard
     */
    public void positionManualHood()
    {
        // Position Shooter Hood, getting desired position from SmartDasboard
        positionShooterHood((int) SmartDashboard.getNumber("Hood Setpoint", 0));
    }
    
    /**
     * void dropShooterHood() - move Shooter Hood to stowed position
     */
    public void dropShooterHood()
    {
        positionShooterHood(0);
    }

    /**
     * void positionShooterHood() - move Shooter Hood to position commanded
     */
    
    public void positionShooterHood(int targetPosition)
    {
        // Show the commanded position on the SmartDashboard
        SmartDashboard.putNumber("Hood Setpoint", targetPosition);

        // read PID coefficients from SmartDashboard
        double kP = SmartDashboard.getNumber("Hood P Gain", 0);
        double kI = SmartDashboard.getNumber("Hood I Gain", 0);
        double kD = SmartDashboard.getNumber("Hood D Gain", 0);
        double kF = SmartDashboard.getNumber("Hood F Gain", 0);

        // Update gains on the controller
        m_shooterHood.updateGains(kP, kI, kD, kF);

        // Update the target position and get back the current velocity
        int currentPosition = m_shooterHood.runPositionPIDF(targetPosition);

        // Show the current Hood Position and Error on the SDB
        SmartDashboard.putNumber("Hood Position", currentPosition);
        SmartDashboard.putNumber("Hood Error", m_shooterHood.getError());
    }

}