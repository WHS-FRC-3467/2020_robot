/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CommandGroups;

import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PrepareShot extends CommandBase
{
    FlyWheelSubsystem m_flyWheel;
    double m_targetVelocity;

    public <m_FlyWheel> PrepareShot(FlyWheelSubsystem flyWheelSubsys, double targetVelocity)
    {
        m_flyWheel = flyWheelSubsys;
        m_targetVelocity = targetVelocity;
        addRequirements(m_flyWheel);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_flyWheel.runShooter(m_targetVelocity);
    }

    @Override
    public void end(boolean interupted) {
        m_flyWheel.stopShooter();
    }
}

