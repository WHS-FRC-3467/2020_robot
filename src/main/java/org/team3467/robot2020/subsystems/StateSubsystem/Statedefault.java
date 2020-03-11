/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.StateSubsystem;

import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Subsystem;
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Statedefault extends CommandBase
{
  public final int initial = 1;
  public final int waiting = 2;
  public final int admit_one = 3;
  public final int run_Spath = 4;
  public final int wait_to_shoot = 5;

  public final CD7Subsystem m_CD7;
  public final SPathSubsystem m_SPath;

  public int state;
  public int PC_count;

  /**
   * Creates a new Statedefault.
   */
  public Statedefault(CD7Subsystem CD7_sub, SPathSubsystem SPath_sub) {
    m_CD7 = CD7_sub;
    m_SPath = SPath_sub;
    addRequirements(m_CD7, m_SPath);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = initial;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (state) {
      case initial:
        PC_count = 0;
        state = waiting;
      case waiting:
        m_SPath.driveBelts(0);
        m_CD7.driveBelts(0);
      case admit_one:
      case run_Spath:
      case wait_to_shoot: 
    }
  }
}
