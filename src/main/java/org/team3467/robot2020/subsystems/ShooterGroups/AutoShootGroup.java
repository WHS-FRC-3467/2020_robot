/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterGroups;

import org.team3467.robot2020.Constants;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.runShooterGate;
import org.team3467.robot2020.subsystems.ShooterHoodSubsystem.HoodSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoShootGroup extends SequentialCommandGroup
{
    FlyWheelSubsystem m_FlyWheel;
    GateSubsystem m_gate;
    HoodSubsystem m_hood;
    double m_targetVelocity;

    public <m_FlyWheel> AutoShootGroup(FlyWheelSubsystem flyWheelSubsys, GateSubsystem gateSubsys, HoodSubsystem hoodSubsys, double targetVelocity)
    {
        m_FlyWheel = flyWheelSubsys;
        m_gate = gateSubsys;
        m_hood = hoodSubsys;
        m_targetVelocity = targetVelocity;

        addCommands(
            new PrepareShot(m_FlyWheel, m_hood, targetVelocity),
            new WaitCommand(0.25),
            new runShooterGate(m_gate, m_FlyWheel, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime),
            new InstantCommand(m_FlyWheel::stopShooter)
        );
    }
}
