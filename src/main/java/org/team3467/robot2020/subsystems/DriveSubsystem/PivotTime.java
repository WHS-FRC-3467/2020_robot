
package org.team3467.robot2020.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotTime extends CommandBase {
  DriveSubsystem m_drive;
  double m_seconds;
  boolean finished;
  double time;
  double startTime;
  double m_speed;
  boolean m_rightTurn;

  public PivotTime(DriveSubsystem driveSubsys, double seconds, double speed, boolean rightTurn) {
    m_seconds = seconds;
    m_drive = driveSubsys;
    m_speed = speed;
    m_rightTurn = rightTurn;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time = Timer.getFPGATimestamp();
    if ((time - startTime) < m_seconds){
      if (m_rightTurn){
        m_drive.getRightTalon().set(ControlMode.PercentOutput, m_speed);
        m_drive.getLeftTalon().set(ControlMode.PercentOutput,  m_speed);
        finished = false;
      }
      else{
        m_drive.getRightTalon().set(ControlMode.PercentOutput, -m_speed);
        m_drive.getLeftTalon().set(ControlMode.PercentOutput,  m_speed);
        finished = false;
      }
    }
    else{
      m_drive.getRightTalon().set(ControlMode.PercentOutput, 0.0);
      m_drive.getLeftTalon().set(ControlMode.PercentOutput, 0.0);
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
