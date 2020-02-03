/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class CanConstants {
    // all the ID's for the CAN devices

    // drivebase
    public static final int left_drivebase_1 = 1;
    public static final int left_drivebase_2 = 2;
    public static final int right_drivebase_1 = 3;
    public static final int right_drivebase_2 = 4;

    // else
    public static final int ground_intake = 5;
    public static final int shooter_intake = 6;
    public static final int shooter_belt = 7;
    public static final int center_belt = 8;
    public static final int side_belts = 9;
  }
  
  public static final class DriveConstants {
    // encoder ports
    public static final int[] kLeftEncoderPorts = new int[]{0, 1};
    public static final int[] kRightEncoderPorts = new int[]{2, 3};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    // drivemeode
    public static final int driveMode_Tank = 0;
    public static final int driveMode_SplitArcade = 1;
    public static final int driveMode_RocketSpin = 2;

    public static final int m_driveMode = driveMode_SplitArcade;

    // other
    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
  }

  public static final class HatchConstants {
    public static final int kHatchSolenoidModule = 0;
    public static final int[] kHatchSolenoidPorts = new int[]{0, 1};
  }

  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 60;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.5;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOpperatorControllerPort = 1;
  }
}
