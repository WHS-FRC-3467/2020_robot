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
    Gains m_gains;
    ISpeedControl m_speedControl;
    TalonSRX m_shooterGate = new TalonSRX(CanConstants.shooter_gate);
    boolean useFalcons = ShooterConstants.kUseFalcons;

    public ShooterSubsystem()
    {
        m_shooterGate.setInverted(true);

        if (useFalcons == true)
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

        SmartDashboard.putNumber("Current Velocity", 0);
        SmartDashboard.putNumber("Current Output Percent", 0);
        SmartDashboard.putNumber("Error", 0);

        SmartDashboard.putNumber("Target Velocity", 0);
        SmartDashboard.putNumber("ShooterGateSpeed", ShooterConstants.kShooterGateSpeed);
    }

    /**
     * void runManual() - run Shooter Wheel at speed commanded by Shuffleboard
     */
    public void runManual()
    {
        // Get desired m_velocity in RPM from SmartDasboard
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
    boolean isWheelAtSpeed()
    {
        return (Math.abs(m_speedControl.getError()) <= ShooterConstants.kTolerance);
    }

    /**
     * void runShooterVelocity() - run the shooter at the speed commanded
     */
    public void runShooter(double targetVelocity)
    {
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
    }

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

}