/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RunBelts extends InstantCommand {
  IntakeSubsystem m_intake;
  double m_speed;
  public RunBelts(IntakeSubsystem intakeSubsys, double speed) {
    m_intake = intakeSubsys;
    m_speed = speed;
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_speed > 0.1){
      m_intake.driveBelts(m_speed);
    }
    else{
      m_intake.driveBelts(0);
    }
  }
}
