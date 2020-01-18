/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team3467.robot2020.subsystems.DriveSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class RocketSpinDrive extends CommandBase {
  
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_LeftStickX;
  private final DoubleSupplier m_RightTrigger;
  private final DoubleSupplier m_LeftTrigger;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public RocketSpinDrive(final DriveSubsystem subsystem, final DoubleSupplier LeftStickX, final DoubleSupplier RightTrigger, final DoubleSupplier LeftTrigger) {
    m_drive = subsystem;
    m_LeftStickX = LeftStickX;
    m_RightTrigger = RightTrigger;
    m_LeftTrigger = LeftTrigger;
    addRequirements(m_drive);
  }


  @Override
  public void execute() {
    double speed = 0.0;

    double backSpeed = m_LeftTrigger.getAsDouble();
    double fwdSpeed = m_RightTrigger.getAsDouble();
    double curve = m_LeftStickX.getAsDouble();

    curve = (-1.0) * curve;

    if (backSpeed != 0.0 && fwdSpeed != 0.0)
    {
      speed = 0.0;
    }
    else if (fwdSpeed > 0.0)
    {
      speed = fwdSpeed;
    }
    else if (backSpeed > 0.0)
    {
      speed = (-1.0) * backSpeed;
    }

    m_drive.rocketDrive(speed, curve);
  }

}
