/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class SplitArcadeDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public SplitArcadeDrive(final DriveSubsystem subsystem, final DoubleSupplier forward, final DoubleSupplier rotation) {
  
    double speed = -RobotContainer.m_driverController.getRawAxis(1) * 0.6;
    double curve = -RobotContainer.m_driverController.getRawAxis(4) * 0.3;
    double left = speed + curve;
    double right = -(speed - curve);
    Robot.DriveSubsystem.left_talon_1.set(left);
    Robot.DriveSubsystem.right_talon_1.set(right);
    addRequirements(m_drive);
  }


  @Override
  public void execute() {
	m_drive.SplitArcadeDrive.drive(speed, curve, (m_drive == driveMode_SplitArcade))

  }



}
