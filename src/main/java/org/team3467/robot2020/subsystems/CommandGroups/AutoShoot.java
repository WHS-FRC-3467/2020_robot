/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CommandGroups;

import org.team3467.robot2020.Constants;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.runShooterGate;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends SequentialCommandGroup
{
    FlyWheelSubsystem m_FlyWheel;
    GateSubsystem m_gate;
    double m_targetVelocity;

    public <m_FlyWheel> AutoShoot(FlyWheelSubsystem flyWheelSubsys, GateSubsystem gateSubsys, double targetVelocity)
    {
        m_FlyWheel = flyWheelSubsys;
        m_gate = gateSubsys;
        m_targetVelocity = targetVelocity;

        addCommands(
            new PrepareShot(m_FlyWheel, targetVelocity).withTimeout(0.5),
            new runShooterGate(m_gate, m_FlyWheel, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime)
        );
    }
}
