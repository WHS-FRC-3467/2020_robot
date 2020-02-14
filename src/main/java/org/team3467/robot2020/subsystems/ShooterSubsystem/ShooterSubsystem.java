/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import java.lang.Math;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team3467.robot2020.Constants.CanConstants;
import org.team3467.robot2020.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.team3467.robot2020.Gains;

public class ShooterSubsystem extends SubsystemBase
{
    Gains m_speedGains;
    Gains m_hoodGains;
    ISpeedControl m_speedControl;
    TalonSRX m_shooterGate = new TalonSRX(CanConstants.shooter_gate);
    TalonMagicMotion m_shooterHood = new TalonMagicMotion(CanConstants.shooter_hood);
    
    boolean m_useFalcons = ShooterConstants.kUseFalcons;

    public ShooterSubsystem()
    {

        // Decide which motors to use for shooting
        if (m_useFalcons == true)
        {
            m_speedControl = new FalconVelocityPIDF();
            m_speedGains = ShooterConstants.kGains_Falcon;
        }
        else
        {
            m_speedControl = new NEOVelocityPIDF();
            m_speedGains = ShooterConstants.kGains_NEO;
        }

        // Get the PIDF gains for the Shooter Hood
        m_hoodGains = ShooterConstants.kGains_Hood;

        // Initialize the Shooter Hood

        /* Initialize Smart Dashboard display */
        SmartDashboard.putNumber("P Gain", m_speedGains.kP);
        SmartDashboard.putNumber("Feed Forward", m_speedGains.kF);

        SmartDashboard.putNumber("Current Velocity", 0);
        SmartDashboard.putNumber("Current Output Percent", 0);
        SmartDashboard.putNumber("Velocity Error", 0);

        SmartDashboard.putNumber("Target Velocity", 500);
        SmartDashboard.putNumber("ShooterGateSpeed", ShooterConstants.kShooterGateSpeed);

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
     *  Shooter Wheel control
     * 
     */
    /**
     * void runManual() - run Shooter Wheel at speed commanded by Shuffleboard
     */
    public void runManual()
    {
        // Run Shooter Wheel, getting desired m_velocity in RPM from SmartDasboard
        runShooter(SmartDashboard.getNumber("Target Velocity", 0));
    }

    /**
     * void runTracking() - run Shooter Wheel at speed commanded by Limelight tracking
     */
    public void runTracking()
    {
        // Get desired velocity from LimeLight tracking
        double targetVelocity = 0.0; // TO DO: Implement Limelight distance->RPM determination

        runShooter(targetVelocity);
    }

    /**
     * boolean isWheelAtSpeed() - return TRUE when wheel is equal to target, or within tolerance
     *
     * @return TRUE if shooter wheel is at commanded speed
     */
    public boolean isWheelAtSpeed()
    {
        return (Math.abs(m_speedControl.getError()) <= ShooterConstants.kShooterTolerance);
    }

    /**
     * void runShooter() - run the shooter at the speed commanded
     */
    public void runShooter(double targetVelocity)
    {
        // Show the commanded velocity on the SmartDashboard
        SmartDashboard.putNumber("Target Velocity", targetVelocity);

        // read PID coefficients from SmartDashboard
        double kP = SmartDashboard.getNumber("P Gain", 0);
        double kI = SmartDashboard.getNumber("I Gain", 0);
        double kD = SmartDashboard.getNumber("D Gain", 0);
        double kF = SmartDashboard.getNumber("Feed Forward", 0);

        // Update gains on the controller
        m_speedControl.updateGains(kP, kI, kD, kF);

        // Update the target velocity and get back the current velocity
        int currentVelocity = m_speedControl.runVelocityPIDF(targetVelocity);

        // Show the Current Velocity, Error, and Current Output Percent on the SDB
        SmartDashboard.putNumber("Current Velocity", currentVelocity);
        SmartDashboard.putNumber("Error", m_speedControl.getError());
        SmartDashboard.putNumber("Current Output Percent", m_speedControl.getOutputPercent());
        SmartDashboard.putNumber("Shooter Hood Up Setpoint", 0);
    }

    /**
     * void stopShooter() - set Shooter velocity to 0.0
     */
    public void stopShooter()
    {
        runShooter(0.0);
    }

    /*
     *
     *  Shooter Gate control
     * 
     */

    /**
     * void runShooterGate() - run the shooter gate wheel at the speed given on the SmartDashboard
     */
    public void runShooterGate()
    {
        m_shooterGate.set(ControlMode.PercentOutput, SmartDashboard.getNumber("ShooterGateSpeed", ShooterConstants.kShooterGateSpeed));
    }

    /**
     * void runShooterGate() - run the shooter gate wheel at the speed given on the SmartDashboard
     */
    public void runShooterGate(double speed)
    {
        m_shooterGate.set(ControlMode.PercentOutput, speed);
    }

    /**
     * void stopShooterGate() - set Shooter Gate velocity to 0.0
     */
    public void stopShooterGate()
    {
        runShooterGate(0.0);
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