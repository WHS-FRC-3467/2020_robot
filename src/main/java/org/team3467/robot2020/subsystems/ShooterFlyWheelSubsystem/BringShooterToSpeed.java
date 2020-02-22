/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BringShooterToSpeed extends CommandBase
{
    FlyWheelSubsystem m_flyWheel;
    double m_targetVelocity;

    public BringShooterToSpeed(FlyWheelSubsystem flyWheelSubsys, double targetVelocity)
    {
        m_flyWheel = flyWheelSubsys;
        m_targetVelocity = targetVelocity;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize()
    {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute()
    {
        m_flyWheel.runShooter(m_targetVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean isFinished)
    {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return m_flyWheel.isWheelAtSpeed();
    }
}
