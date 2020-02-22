/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterGroups;

import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.BringShooterToSpeed;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShootGroup extends SequentialCommandGroup
{
    FlyWheelSubsystem m_FlyWheel;
    GateSubsystem m_gate;
    double m_targetVelocity;

    public <m_FlyWheel> AutoShootGroup(FlyWheelSubsystem flyWheelSubsys, GateSubsystem gateSubsys, double targetVelocity)
    {
        m_FlyWheel = flyWheelSubsys;
        m_gate = gateSubsys;
        m_targetVelocity = targetVelocity;
        addRequirements(m_FlyWheel);

        addCommands(
            new BringShooterToSpeed(m_FlyWheel, m_targetVelocity),
            new InstantCommand(m_FlyWheel::stopShooter, m_FlyWheel)
        );
    }
}
