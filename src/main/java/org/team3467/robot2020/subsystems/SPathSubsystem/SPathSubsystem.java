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

public class SPathSubsystem extends SubsystemBase
{
    // Motor driving SPath
    private TalonSRX m_SPathMotor = new TalonSRX(CanConstants.shooter_belt);

    public SPathSubsystem() 
    {
        m_SPathMotor.setInverted(true);
    }
    

    public void driveBelts(double speed)
    {
        m_SPathMotor.set(ControlMode.PercentOutput, -speed);
    }
    

}