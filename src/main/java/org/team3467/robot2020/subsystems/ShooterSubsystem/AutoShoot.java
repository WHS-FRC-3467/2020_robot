/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import org.team3467.robot2020.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoShoot extends CommandBase
{
    /**
     * Creates a new RunManualShooter.
     */
    ShooterSubsystem m_shooter;
    ISpeedControl m_speedControl;


    public AutoShoot(ShooterSubsystem shooterSubsys)
    {
        m_shooter = shooterSubsys;
        addRequirements(m_shooter);
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
        m_shooter.runManual();
        if ((Math.abs(m_speedControl.getError()) <= ShooterConstants.kTolerance))
        {
            m_shooter.runShooterGate();
        }
        System.out.println("test 1");
         
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted)
    {
        m_shooter.runShooter(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
