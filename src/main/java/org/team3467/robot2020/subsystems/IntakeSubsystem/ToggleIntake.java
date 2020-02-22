/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleIntake extends InstantCommand {
  IntakeSubsystem m_intake;
  public ToggleIntake(final IntakeSubsystem intakeSubsys) {
    m_intake = intakeSubsys;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    if (Value.kForward == m_intake.getSolenoidValue()){
      m_intake.retractIntake();
    } else {
      m_intake.deployIntake();
    }
  }

}
