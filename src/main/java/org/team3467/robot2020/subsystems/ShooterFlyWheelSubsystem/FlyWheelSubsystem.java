/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem;

import java.lang.Math;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team3467.robot2020.Constants.ShooterConstants;
import org.team3467.robot2020.subsystems.CommandGroups.*;
import org.team3467.robot2020.Gains;

public class FlyWheelSubsystem extends SubsystemBase
{
    Gains m_speedGains;
    ISpeedControl m_speedControl;
    FlyWheelSubsystem m_flyWheel;
    
    
    boolean m_useFalcons = ShooterConstants.kUseFalcons;

    public FlyWheelSubsystem()
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

        /* Initialize Smart Dashboard display */
        SmartDashboard.putNumber("P Gain", m_speedGains.kP);
        SmartDashboard.putNumber("Feed Forward", m_speedGains.kF);

        SmartDashboard.putNumber("Current Velocity", 0);
        SmartDashboard.putNumber("Current Output Percent", 0);
        SmartDashboard.putNumber("Velocity Error", 0);

        SmartDashboard.putNumber("Target Velocity", 4500);
        
        SmartDashboard.putNumber("Init Line Velocity", ShooterConstants.kInitLineShotVelocity);
        SmartDashboard.putNumber("Trench Shot Velocity", ShooterConstants.kTrenchShotVelocity);
        SmartDashboard.putNumber("Wall Shot Velocity", ShooterConstants.kWallShotVelocity);
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
        return (Math.abs(m_speedControl.getError()) <= ShooterConstants.kShooterTolerance) && (m_speedControl.getOutputPercent() != 0);
    }

    /**
     * void runShooter() - run the shooter at the speed commanded
     */
    public void runShooter(double targetVelocity)
    {
        if (targetVelocity == 0.0)
        {
            stopShooter();
            SmartDashboard.putNumber("Current Velocity", 0.0);
            return;
        }    
        
        // Show the commanded velocity on the SmartDashboard
        //SmartDashboard.putNumber("Target Velocity", targetVelocity);

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

    public void wallShot(){
        new PrepareShot(m_flyWheel, SmartDashboard.getNumber("Wall Shot Velocity", ShooterConstants.kWallShotVelocity));
    }
    /**
     * void stopShooter() - Stop the Shooter by simply turning off the motors instead of commanding Velocity PID to 0.0
     */
    public void stopShooter()
    {
        m_speedControl.stop();
    }
}