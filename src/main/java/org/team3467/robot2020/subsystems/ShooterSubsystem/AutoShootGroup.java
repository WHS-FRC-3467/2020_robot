/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import org.team3467.robot2020.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoShootGroup extends SequentialCommandGroup
{
    ShooterSubsystem m_shooter;
    double m_targetVelocity;

    /**
     * Creates a new AutoShootGroup.
     */
    public AutoShootGroup(ShooterSubsystem shooterSubsys, double targetVelocity)
    {
        m_shooter = shooterSubsys;
        m_targetVelocity = targetVelocity;
        addRequirements(m_shooter);

        addCommands(
            new BringShooterToSpeed(m_shooter, m_targetVelocity),
            new runShooterGate(m_shooter, 1)
                .withTimeout(ShooterConstants.kShooterGateRunTime),
            new WaitCommand(1),
            new InstantCommand(m_shooter::stopShooter, m_shooter)
        );
    }
}
