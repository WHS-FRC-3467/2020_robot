/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BringShooterToSpeed extends CommandBase
{
    ShooterSubsystem m_shooter;
    double m_targetVelocity;

    public BringShooterToSpeed(ShooterSubsystem shooterSubsys, double targetVelocity)
    {
        m_shooter = shooterSubsys;
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
        m_shooter.runShooter(m_targetVelocity);
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
        return m_shooter.isWheelAtSpeed();
    }
}
