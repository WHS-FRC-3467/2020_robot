/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterHoodSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class HoodDefault extends CommandBase
{
    private HoodSubsystem m_hood;
    private DoubleSupplier m_speed;

    /**
     * Creates a new HoodDefault.
     */
    public HoodDefault(HoodSubsystem hood, final DoubleSupplier speedIn)
    {
        m_hood = hood;
        m_speed = speedIn;
        addRequirements(m_hood);
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
        double hoodSpeed = m_speed.getAsDouble();
        
        // This code is for running manually with a stick
        if (hoodSpeed < 0.1 && hoodSpeed > -0.1)
        {
            hoodSpeed = 0.0;
        }
        m_hood.runShooterHood(hoodSpeed);


        // This code is for running with Position PID control
        /*
        final double MAX_POSITION = 300;
        final double MIN_POSITION = 0.0;
        int targetPosition = (int)((MAX_POSITION - MIN_POSITION) * hoodSpeed);
        if (targetPosition < 0) TargetPosition = 0;
        m_hood.positionShooterHood(targetPosition);
        */
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted)
    {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
