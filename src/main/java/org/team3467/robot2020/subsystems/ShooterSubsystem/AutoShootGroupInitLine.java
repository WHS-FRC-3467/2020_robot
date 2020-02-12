/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterSubsystem;

import org.team3467.robot2020.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoShootGroupInitLine extends SequentialCommandGroup {
  ShooterSubsystem m_shooter;
  /**
   * Creates a new AutoShootGroup.
   */
  public AutoShootGroupInitLine(ShooterSubsystem shooterSubsys) {
    m_shooter = shooterSubsys;
    addRequirements(m_shooter);
    
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(new AutoShootInitLine(m_shooter), new PCShoot(m_shooter).withTimeout(ShooterConstants.kShooterGateRunTime), new WaitCommand(1), new InstantCommand(m_shooter::stopShooter, m_shooter)); 
  }
}
