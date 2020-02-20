/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.Autonomous;

import org.team3467.robot2020.Constants.ShooterConstants;
import org.team3467.robot2020.subsystems.DriveSubsystem.DriveDistance;
import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.RunBelts;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterGroups.AutoShootGroup;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class threeBallDriveBack extends SequentialCommandGroup {
  /**
   * Creates a new threeBallDriveBack.
   */
  FlyWheelSubsystem m_flyWheel;
  DriveSubsystem m_drive;
  IntakeSubsystem m_intake;
  public threeBallDriveBack(FlyWheelSubsystem flyWheelSubsys, DriveSubsystem driveSubsys, IntakeSubsystem intakeSubsys) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_flyWheel = flyWheelSubsys;
    m_drive = driveSubsys;
    m_intake = intakeSubsys;
    addRequirements(m_flyWheel);
    addRequirements(m_drive);
    addRequirements(m_intake);

    addCommands(
      //shoot 3 balls
      new AutoShootGroup(m_flyWheel, null, ShooterConstants.kInitLineShotVelocity),
      new RunBelts(m_intake, 0.5).withTimeout(0.5),
      new AutoShootGroup(m_flyWheel, null,ShooterConstants.kInitLineShotVelocity),
      new RunBelts(m_intake, 0.5).withTimeout(0.5),
      new AutoShootGroup(m_flyWheel, null, ShooterConstants.kInitLineShotVelocity),
      //drive back 6 feet
      new DriveDistance(m_drive, 72.0)
    );
  }
}
