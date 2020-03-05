/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterHoodSubsystem;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

import org.team3467.robot2020.Constants.ShooterConstants;

public class TalonMagicMotion extends TalonSRX
{
    /* Gains */
    double m_kP = 0.0;
    double m_kI = 0.0;
    double m_kD = 0.0;
    double m_kF = 0.0;

    /* Not really MagicMotion - just Position PID now */

    public TalonMagicMotion(int deviceID)
    {
        super(deviceID);
    
        /* Factory Default to prevent unexpected behaviour */
        configFactoryDefault();

        // Turn on Brake mode
        setNeutralMode(NeutralMode.Brake);
    
        /* Config sensor used for Primary PID */
        configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

        /* Phase sensor accordingly. */
		setSensorPhase(true);
        setInverted(false);
		
		/* Set relevant frame periods to be at least as fast as periodic rate */
		setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);

		/* Config the peak and nominal outputs */
		configNominalOutputForward(0, 30);
		configNominalOutputReverse(0, 30);
		configPeakOutputForward(0.5, 30);
        configPeakOutputReverse(-0.5, 30);
        
		/* set deadband to super small 0.001 (0.1 %) */
		configNeutralDeadband(0.001, 30);

		/* Zero the sensor once on robot boot up */
        setSelectedSensorPosition(0, 0, 30);

        /* set acceleration and cruise velocity - see CTRE documentation for how to tune */
    //    configMotionCruiseVelocity(ShooterConstants.kHoodCruiseVel, 30);
    //    configMotionAcceleration(ShooterConstants.kHoodAccel, 30);

        /* Set curve smoothing (0 - 8)*/
    //    configMotionSCurveStrength(5);

        /* Use the specified tolerance to set the allowable Closed-Loop error */
        configAllowableClosedloopError(0, ShooterConstants.kHoodTolerance, 10);
        
    }

    public void updateGains(double kP, double kI, double kD, double kF)
    {
        // if PIDF coefficients have changed, write new values to controller
		selectProfileSlot(0, 0);
        if((m_kP != kP)) { config_kP(0, kP, 30); m_kP = kP; }
        if((m_kI != kI)) { config_kI(0, kI, 30); m_kI = kI; }
        if((m_kD != kD)) { config_kD(0, kD, 30); m_kD = kD; }
        if((m_kF != kF)) { config_kF(0, kF, 30); m_kF = kF; }
    }

    public int runPositionPIDF(double targetPosition)
    {
        // Set Position setpoint
      //  set(ControlMode.MotionMagic, targetPosition);
        set(ControlMode.Position, targetPosition);

        // Get current position and return it
        return (getSelectedSensorPosition());
    }

    public int getError()
    {
        return getClosedLoopError();
    }
}
