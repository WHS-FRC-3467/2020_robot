/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.SPathSubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3467.robot2020.Constants.CanConstants;
import org.team3467.robot2020.Constants.SPathConstants;
import org.team3467.robot2020.sensors.BeamSensor.BeamSensor;

public class SPathSubsystem extends SubsystemBase
{
    public BeamSensor m_beamBreak0 = new BeamSensor(SPathConstants.kBeamBreak_0_Port, false);
    public BeamSensor m_beamBreak1 = new BeamSensor(SPathConstants.kBeamBreak_1_Port, false);
    public BeamSensor m_beamBreak2 = new BeamSensor(SPathConstants.kBeamBreak_2_Port, false);
    public BeamSensor m_beamBreak3 = new BeamSensor(SPathConstants.kBeamBreak_3_Port, false);

    // Motor driving S-Path Gate & CD7 Center belts
    private TalonSRX m_SPathMotor1 = new TalonSRX(CanConstants.SPath1);

    // Motor driving SPath
    private TalonSRX m_SPathMotor2 = new TalonSRX(CanConstants.SPath2);

    public SPathSubsystem()
    {
        m_SPathMotor1.configFactoryDefault();
        m_SPathMotor1.setInverted(true);

        m_SPathMotor2.configFactoryDefault();
        m_SPathMotor2.setInverted(true);
    }

    public void driveSPath(double speed)
    {
        m_SPathMotor2.set(ControlMode.PercentOutput, speed);
    }

    public void runSPath()
    {
        driveSPath(0.5);
    }

    public void driveGate(double speed)
    {
        m_SPathMotor1.set(ControlMode.PercentOutput, speed);
    }

}