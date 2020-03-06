/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CD7Subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3467.robot2020.Constants.CanConstants;

public class CD7Subsystem extends SubsystemBase
{
    // Motors driving belts on CD7 intake
    private TalonSRX m_SPath1 = new TalonSRX(CanConstants.SPath1);
    private TalonSRX m_sideBeltMotors = new TalonSRX(CanConstants.side_belts);

    public CD7Subsystem() 
    {
        m_SPath1.configFactoryDefault();
        m_sideBeltMotors.configFactoryDefault();
        m_sideBeltMotors.setInverted(true);
        m_SPath1.setInverted(true);
    }
    
    
    public void driveBelts(double speed)
    {
        m_sideBeltMotors.set(ControlMode.PercentOutput, speed);
        m_SPath1.set(ControlMode.PercentOutput, speed);
    }
    

}