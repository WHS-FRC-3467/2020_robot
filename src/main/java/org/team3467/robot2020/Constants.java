/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the constants are needed, to reduce verbosity.
 */
public final class Constants
{
    public static final class CanConstants
    {
        // all the ID's for the CAN devices

        // drivebase
        public static final int left_drivebase_1 = 2;
        public static final int left_drivebase_2 = 3;
        public static final int right_drivebase_1 = 4;
        public static final int right_drivebase_2 = 5;

        // else
        public static final int ground_intake = 6;
        public static final int shooter_gate = 7;
        public static final int shooter_belt = 8;
        public static final int center_belt = 9;
        public static final int side_belts = 10;

        // Shooter motors
        public static final int shooter_motor1 = 11;
        public static final int shooter_motor2 = 12;
    }
    public static final class PneumaticConstants {
        public static final int intake_Piston_Forward = 0;       
        public static final int intake_Piston_Reverse = 1;
    }

    public static final class DriveConstants
    {
        // encoder ports
        public static final int[] kLeftEncoderPorts = new int[] { 0, 1 };
        public static final int[] kRightEncoderPorts = new int[] { 2, 3 };
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

    
    public static final class AutoConstants
    {
        public static final double kAutoDriveDistanceInches = 60;
        public static final double kAutoBackupDistanceInches = 20;
        public static final double kAutoDriveSpeed = 0.5;
    }

    public static final class OIConstants
    {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
    }

    public static final class ShuffleboardConstants
    {
        public static final String kControlVarsTab = "Control Variables";
    }

    public static final class ShooterConstants
    {
        // Specify if shooter motors are Falcons. If not, we assume they are NEOs
        public static final boolean kUseFalcons = false;

        // Default Falcon PIDF Gains
        // 	                                    			 kP    kI   kD   kF    Iz  PeakOut */
        public final static Gains kGains_Falcon = new Gains( 0.25, 0.0, 0.0, 0.045, 0,  1.00);

        // Default NEO PIDF Gains
        // 	                                    		  kP       kI   kD   kF     Iz  PeakOut */
        public final static Gains kGains_NEO = new Gains( 0.00055, 0.0, 0.0, 0.0002, 0,  1.00);

        // Shooter Velocity Tolerance
        public final static int kTolerance = 50;

        // Default ShooterGate Speed
        public final static double kShooterGateSpeed = 1.0;

        // Default ShooterGate run time
        public final static double kShooterGateRunTime = 0.4;
    }
}
