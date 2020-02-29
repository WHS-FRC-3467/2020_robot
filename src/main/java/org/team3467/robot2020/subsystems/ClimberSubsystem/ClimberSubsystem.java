/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ClimberSubsystem;

import org.team3467.robot2020.Constants.PneumaticConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase
{
    private DoubleSolenoid m_climberPiston;

    public ClimberSubsystem() {
        m_climberPiston = new DoubleSolenoid(PneumaticConstants.kClimberPistonDeploy, PneumaticConstants.kClimberPistonRetract);
        m_climberPiston.set(Value.kReverse);
    }

    public void deployClimber()
    {
        m_climberPiston.set(Value.kForward);
    }

    public void retractClimber() 
    {
        m_climberPiston.set(Value.kReverse);
    }

    public boolean isClimberDeployed()
    {
        return m_climberPiston.get() == Value.kForward;
    }
}
