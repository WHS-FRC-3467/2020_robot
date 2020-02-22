/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import java.util.Map;

import org.team3467.robot2020.Autonomous.SimpleDrive;
//import org.team3467.robot2020.Autonomous.threeBallDriveBack;
import org.team3467.robot2020.Constants.DriveConstants;
import org.team3467.robot2020.Constants.OIConstants;
import org.team3467.robot2020.Constants.ShooterConstants;
import org.team3467.robot2020.subsystems.DriveSubsystem.SplitArcadeDrive;
import org.team3467.robot2020.subsystems.DriveSubsystem.TankDrive;
import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Default;
import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Subsystem;
import org.team3467.robot2020.subsystems.DriveSubsystem.AutoLineup;
import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.Pneumatics;
import org.team3467.robot2020.subsystems.IntakeSubsystem.RunIntake;
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathDefault;
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathSubsystem;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.runManual;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateDefault;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import org.team3467.robot2020.subsystems.ShooterGroups.AutoShootGroup;
import org.team3467.robot2020.subsystems.ShooterHoodSubsystem.HoodSubsystem;
import org.team3467.robot2020.subsystems.DriveSubsystem.RocketSpinDrive;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeDefault;
import org.team3467.robot2020.control.XBoxControllerDPad;
import org.team3467.robot2020.control.XboxController;
import org.team3467.robot2020.control.XboxControllerButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very little robot logic should actually be
 * handled in the {@link Robot} periodic methods (other than the scheduler calls). Instead, the structure of the robot (including subsystems, commands, and
 * button mappings) should be declared here.
 */
public class RobotContainer
{

    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final IntakeSubsystem m_intakeSub = new IntakeSubsystem();
    private final FlyWheelSubsystem m_flyWheelsub = new FlyWheelSubsystem();
    private final GateSubsystem m_gateSub = new GateSubsystem();
    private final HoodSubsystem m_hoodSub = new HoodSubsystem();
    private final SPathSubsystem m_sPath = new SPathSubsystem();
    private final CD7Subsystem m_CD7 = new CD7Subsystem();

    private HttpCamera limelightFeed;



    // The autonomous routines
    // A simple auto routine that drives forward a specified distance, and then stops.
    // private final Command m_simpleAuto =
    // new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);

    // A complex auto routine that drives forward, drops a hatch, and then drives backward.
    // private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_hatchSubsystem);

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    // The Driver and Operator controllers
    public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    public static XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {  
        //fieldCamera = new FieldCamera();
        Pneumatics.getInstance();
        Pneumatics.scorpionCompressor.setClosedLoopControl(true);

        limelightFeed = new HttpCamera("limelight", "http://limelight.local:5800/stream.mjpg");
        ShuffleboardTab dashboardTab = Shuffleboard.getTab("Dash");
        dashboardTab.add("LL", limelightFeed).withPosition(0,0).withSize(15, 8).withProperties(Map.of("Show Crosshair", true, "Show Controls", false));
        // Configure the button bindings
        configureButtonBindings();
        // m_pneumatics.compressorStart();
        // Configure default commands
        // Set the default drive command to split-stick arcade drive
        switch (DriveConstants.m_driveMode)
        {
        case DriveConstants.driveMode_Tank:
            m_robotDrive.setDefaultCommand(
                new TankDrive(m_robotDrive,
                    () -> m_driverController.getLeftY(),
                    () -> m_driverController.getRightY()));
            break;

        default:
        case DriveConstants.driveMode_SplitArcade:
            m_robotDrive.setDefaultCommand(
                // A split-stick arcade command, with forward/backward controlled by the left hand,
                // and turning controlled by the right.
                new SplitArcadeDrive(m_robotDrive,
                    () -> m_driverController.getLeftY(),
                    () -> m_driverController.getRightX()));
            break;

        case DriveConstants.driveMode_RocketSpin:
            m_robotDrive.setDefaultCommand(
                new RocketSpinDrive(m_robotDrive,
                    () -> m_driverController.getLeftX(),
                    () -> m_driverController.getLeftTrigger(),
                    () -> m_driverController.getRightTrigger()));
            break;
        }

        // Set the Default command for the IntakeSubsystem
        // Provide controls to 1) Run CD7 & Shooter Belts, 2) Run Intake, 3) Reverse Intake
        m_intakeSub.setDefaultCommand(
                new IntakeDefault(m_intakeSub,
                    () -> m_operatorController.getLeftY(),
                    () -> m_operatorController.getLeftTrigger(),
                    () -> m_operatorController.getRightTrigger()));

        // Set the Default command for the ShooterSubsystem
        // Control Shooter Gate using Right Stick Y-Axis
        
        m_sPath.setDefaultCommand(
            new SPathDefault(m_sPath,
                () -> m_operatorController.getLeftY()));

        m_CD7.setDefaultCommand(
            new CD7Default(m_CD7, 
                () -> m_operatorController.getLeftTrigger(),
                () -> m_operatorController.getRightTrigger()));

        m_gateSub.setDefaultCommand(
            new GateDefault(m_gateSub,
            () -> m_driverController.getRightTrigger(),
            () -> m_driverController.getLeftTrigger()));

        // Add commands to the autonomous command chooser
        // m_chooser.addOption("Simple Auto", m_simpleAuto);
        // m_chooser.addOption("Complex Auto", m_complexAuto);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add(m_chooser);
    }

    /**
     * Use this method to define your button->command mappings.
     */
    private void configureButtonBindings()
    {
        /*
         * Operator controller
         */

        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperLeft)
            .whenPressed(new RunIntake(m_intakeSub, 1.0))
            .whenReleased(new RunIntake(m_intakeSub, 0.0));

        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperRight)
            .whenPressed(new RunIntake(m_intakeSub, -1.0))
            .whenReleased(new RunIntake(m_intakeSub, 0.0));

        // Do an Autonomous shot from the Trench when the 'A' button is pressed
        new XboxControllerButton(m_operatorController, XboxController.Button.kA)
            .whenPressed(new AutoShootGroup(m_flyWheelsub, m_gateSub, ShooterConstants.kTrenchShotVelocity));
        
        // Do an Autonomous shot from the Init Line when the 'Y' button is pressed
        new XboxControllerButton(m_operatorController, XboxController.Button.kX)
            .whenPressed(new AutoShootGroup(m_flyWheelsub, m_gateSub, ShooterConstants.kInitLineShotVelocity));
 
        //Don't use these until PIDF is tuned
        new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadUp)
            .whenActive(new InstantCommand(m_hoodSub::positionManualHood));
            
        new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadDown)
            .whenActive(new InstantCommand(m_hoodSub::dropShooterHood));
        
        /*
         * Driver controller
         */
        new XboxControllerButton(m_driverController, XboxController.Button.kBumperRight)
            .whenPressed(new InstantCommand(m_intakeSub::deployIntake));

        new XboxControllerButton(m_driverController, XboxController.Button.kBumperRight)
            .whileHeld(new AutoLineup(m_robotDrive));

        new XboxControllerButton(m_operatorController, XboxController.Button.kB)
            .whileHeld(new runManual(m_flyWheelsub).withTimeout(5));

        new XboxControllerButton(m_operatorController, XboxController.Button.kB)
            .whileHeld(new runManual(m_flyWheelsub).withTimeout(5));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        return new SimpleDrive(m_robotDrive);
    }
}
