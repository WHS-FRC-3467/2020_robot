/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;
import frc.robot.commands.SplitArcadeDrive;


public class DriveSubsystem extends SubsystemBase {

  private int m_drive = SplitArcadeDrive;

  left_talon_1 = new WPI_TalonFX(RobotGlobal.DRIVEBASE_TALON_L1);
  left_talon_2 = new WPI_TalonFX(RobotGlobal.DRIVEBASE_TALON_L2);
  right_talon_1 = new WPI_TalonFX(RobotGlobal.DRIVEBASE_TALON_R1);
  right_talon_2 = new WPI_TalonFX(RobotGlobal.DRIVEBASE_TALON_R2);

  // The motors on the left side of the drive.
  private final WPI_TalonFX left_talon_1, left_talon_2;
  private final WPI_TalonFX right_talon_1, right_talon_2;

  left_talon_1.configFactoryDefault();
  left_talon_2.configFactoryDefault();
  right_talon_1.configFactoryDefault();
  right_talon_2.configFactoryDefault();

  // Slave the extra Talons on each side
  left_talon_2.follow(left_talon_1);
  right_talon_2.follow(right_talon_1);


  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
                  DriveConstants.kLeftEncoderReversed);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1],
                  DriveConstants.kRightEncoderReversed);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    // Sets the distance per pulse for the encoders
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param speed the commanded forward movement
   * @param curve the commanded rotation
   */
  

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }
  public WPI_TalonFX getLeftTalon()
    {
        return left_talon_1;
    }

  public WPI_TalonFX getRightTalon()
  {
      return right_talon_1;
  }
  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  
}
