/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.DriveSubsystem;

import org.team3467.robot2020.sensors.Limelight.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoLineup extends CommandBase
{

    DriveSubsystem m_drive;
    double m_DriveCommand;
    double m_SteerCommand;

    public AutoLineup(DriveSubsystem driveSubsys)
    {
        m_drive = driveSubsys;
        addRequirements(m_drive);
    }

    // Called just before this Command runs the first time
    public void initialize()
    {
        Limelight.setVisionMode(1);
        m_DriveCommand = 0.0;
        m_SteerCommand = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute()
    {
/*
        // was a target found and locked? if so, drive to it with the drive constants
        if (isLimelightTracking())
        {
            m_drive.arcadeDrive(-m_DriveCommand, -m_SteerCommand);
        }
        else
        {
            m_drive.arcadeDrive(0.0, 0.0);
        }
*/
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    public void end()
    {
        Limelight.setDriverMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted()
    {
        end();
    }

    public boolean isLimelightTracking()
    {
        // These numbers must be tuned for your Robot! Be careful!
        final double STEER_K = 0.15; // how hard to turn toward the target
        //final double DRIVE_K = 0.26; // how hard to drive fwd toward the target
        //final double DESIRED_TARGET_AREA = 5.0; // Area of the target when the robot reaches the wall
        //final double MAX_DRIVE = 0.37; // Simple speed limit so we don't drive too fast

        // No target found; return
        if (!Limelight.isTarget())
        {
            m_DriveCommand = 0.0;
            m_SteerCommand = 0.0;
            return (false);
        }

        double tx = Limelight.getTx();
        //double ta = Limelight.getTa();

        // Start with proportional steering
        m_SteerCommand = tx * STEER_K;

        /*
        // try to drive forward until the target area reaches our desired area
        m_DriveCommand = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
        
        // don't let the robot drive too fast into the goal
        if (m_DriveCommand > MAX_DRIVE)
        {
            m_DriveCommand = MAX_DRIVE;
        }
        */
        
        m_DriveCommand = 0.0;

        return (true);
    }
}
