/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written explicitly for pedagogical purposes - actual code should
 * inline a command this simple with {@link edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class SplitArcadeDrive extends CommandBase
{

    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;

    /**
     * Creates a new DefaultDrive.
     *
     * @param subsystem The drive subsystem this command wil run on.
     * @param forward   The control input for driving forwards/backwards
     * @param rotation  The control input for turning
     */
    public SplitArcadeDrive(final DriveSubsystem subsystem, final DoubleSupplier forward, final DoubleSupplier rotation)
    {
        m_drive = subsystem;
        m_forward = forward;
        m_rotation = rotation;
        addRequirements(m_drive);
    }

    @Override
    public void execute()
    {
        m_drive.arcadeDrive((-1) * m_forward.getAsDouble(), m_rotation.getAsDouble());
    }

}
