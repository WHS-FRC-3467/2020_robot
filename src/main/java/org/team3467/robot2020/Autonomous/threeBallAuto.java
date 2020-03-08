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
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathSubsystem;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Subsystem;
import org.team3467.robot2020.subsystems.CommandGroups.*;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ThreeBallAuto extends SequentialCommandGroup
{
  /**
   * Creates a new threeBallDriveBack.
   */
  FlyWheelSubsystem m_flyWheel;
  DriveSubsystem m_drive;
  GateSubsystem m_gate;
  SPathSubsystem m_SPath;
  CD7Subsystem m_CD7;

  public ThreeBallAuto(FlyWheelSubsystem flyWheelSubsys, DriveSubsystem driveSubsys, GateSubsystem gateSubsys, SPathSubsystem SPathSubsys,
      CD7Subsystem CD7Subsys)
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_flyWheel = flyWheelSubsys;
    m_drive = driveSubsys;
    m_gate = gateSubsys;
    m_CD7 = CD7Subsys;
    m_SPath = SPathSubsys;
    addRequirements(m_flyWheel);
    addRequirements(m_drive);
    addRequirements(m_gate);
    addRequirements(m_SPath);
    addRequirements(m_CD7);

    addCommands(
      new driveTime(m_drive, 3.0, -0.25),
      //shoot 3 balls
      new ProcessBalls(m_CD7, m_SPath).withTimeout(6),
      new AutoShoot(m_flyWheel, m_gate, ShooterConstants.kWallShotVelocity),
      new AutoShoot(m_flyWheel, m_gate, ShooterConstants.kWallShotVelocity),
      new AutoShoot(m_flyWheel, m_gate, ShooterConstants.kWallShotVelocity),
      new AutoShoot(m_flyWheel, m_gate, ShooterConstants.kWallShotVelocity),
      new AutoShoot(m_flyWheel, m_gate, ShooterConstants.kWallShotVelocity),
      new InstantCommand(m_flyWheel::stopShooter)
    );
  }
}
