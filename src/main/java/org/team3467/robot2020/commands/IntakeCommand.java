/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import org.team3467.robot2020.control.XboxController;
import org.team3467.robot2020.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeCommand extends CommandBase {
  private final IntakeSubsystem m_intake;
  private final DoubleSupplier m_speed;
  private final XboxController m_controller;

  public IntakeCommand(final IntakeSubsystem subsystem, final XboxController controller, final DoubleSupplier speed) {
    m_intake = subsystem;
    m_speed = speed;
    m_controller = controller;
    addRequirements(m_intake);
  }

  @Override
  public void execute() {
    m_intake.driveIntake(-1.0*m_speed.getAsDouble());
    m_intake.driveBelts(SmartDashboard.getNumber("Belt Velocity", 0), m_controller.getTriggerAxis(Hand.kLeft) > 0.1);
  }
}
