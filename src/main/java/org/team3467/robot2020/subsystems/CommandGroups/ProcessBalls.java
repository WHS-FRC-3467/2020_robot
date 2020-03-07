/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CommandGroups;

import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Subsystem;
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ProcessBalls extends SequentialCommandGroup {
  /**
   * Creates a new ProcessBalls.
   */
  private final CD7Subsystem m_CD7;
  private final SPathSubsystem m_SPath;
  public ProcessBalls(CD7Subsystem CD7Subsys, SPathSubsystem SPathSubsys) {
    m_CD7 = CD7Subsys;
    m_SPath = SPathSubsys;
    addRequirements(m_CD7);
    addRequirements(m_SPath);

    addCommands(
      new InstantCommand(m_CD7::runBelts),
      new InstantCommand(m_SPath::runSPath)
    );
  }
}
