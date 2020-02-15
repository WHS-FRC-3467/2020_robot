/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.Autonomous;

import org.team3467.robot2020.Constants.ShooterConstants;
import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;
import org.team3467.robot2020.subsystems.DriveSubsystem.driveTime;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.RunBelts;
import org.team3467.robot2020.subsystems.ShooterSubsystem.AutoShootGroup;
import org.team3467.robot2020.subsystems.ShooterSubsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class threeBallDriveBackSec extends SequentialCommandGroup {
  /**
   * Creates a new threeBallDriveBack.
   */
  ShooterSubsystem m_shooter;
  DriveSubsystem m_drive;
  IntakeSubsystem m_intake;
  public threeBallDriveBackSec(ShooterSubsystem shooterSubsys, DriveSubsystem driveSubsys, IntakeSubsystem intakeSubsys) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_shooter = shooterSubsys;
    m_drive = driveSubsys;
    m_intake = intakeSubsys;
    addRequirements(m_shooter);
    addRequirements(m_drive);
    addRequirements(m_intake);

    addCommands(
      //shoot 3 balls
      new AutoShootGroup(m_shooter, ShooterConstants.kInitLineShotVelocity),
      new RunBelts(m_intake, 0.5).withTimeout(0.5),
      new AutoShootGroup(m_shooter, ShooterConstants.kInitLineShotVelocity),
      new RunBelts(m_intake, 0.5).withTimeout(0.5),
      new AutoShootGroup(m_shooter, ShooterConstants.kInitLineShotVelocity),
      //drive back 6 feet
      new driveTime(m_drive, 2.0)
    );
  }
}
