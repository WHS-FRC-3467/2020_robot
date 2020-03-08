/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterHoodSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team3467.robot2020.Constants.PneumaticConstants;

import org.team3467.robot2020.Gains;

public class HoodSubsystem extends SubsystemBase
{
    public DoubleSolenoid m_shooterHood;
    Gains m_hoodGains;
    public HoodSubsystem()
    {
        m_shooterHood = new DoubleSolenoid(PneumaticConstants.kHoodPistonDeploy, PneumaticConstants.kHoodPistonRetract);

    }
    public void deployHood()
    {
        m_shooterHood.set(Value.kForward);
    }
    public void retractHood() 
    {
        m_shooterHood.set(Value.kReverse);
    }
    public boolean isIntakeDeployed()
    {
        return m_shooterHood.get() == Value.kForward;
    }
}