/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CommandGroups;

import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;
import org.team3467.robot2020.subsystems.DriveSubsystem.driveTime;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.RunIntake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeDrive extends ParallelCommandGroup {

  private final IntakeSubsystem m_intake;
  private final DriveSubsystem m_drive;
  public IntakeDrive(IntakeSubsystem IntakeSubsys, DriveSubsystem DriveSubsys) {
    m_intake = IntakeSubsys;
    m_drive = DriveSubsys;
    // addRequirements(m_intake);
    // addRequirements(m_drive);

    addCommands(
      new RunIntake(m_intake, 0.75),
      new driveTime(m_drive, 2, 0.25)
    );
  }
}
