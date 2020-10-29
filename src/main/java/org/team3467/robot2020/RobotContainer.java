/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import org.team3467.robot2020.Autonomous.SimpleDrive;
import org.team3467.robot2020.Autonomous.ThreeBallAuto;
import org.team3467.robot2020.Autonomous.ThreeBallSide;
import org.team3467.robot2020.Autonomous.ThreeBallWAssist;
import org.team3467.robot2020.Autonomous.ThreeBallWIntake;
import org.team3467.robot2020.Constants.DriveConstants;
import org.team3467.robot2020.Constants.OIConstants;
import org.team3467.robot2020.Constants.ShooterConstants;
import org.team3467.robot2020.subsystems.DriveSubsystem.SplitArcadeDrive;
import org.team3467.robot2020.subsystems.DriveSubsystem.TankDrive;
import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Default;
import org.team3467.robot2020.subsystems.CD7Subsystem.CD7Subsystem;
import org.team3467.robot2020.subsystems.ClimberSubsystem.ClimberDefault;
import org.team3467.robot2020.subsystems.ClimberSubsystem.ClimberSubsystem;
import org.team3467.robot2020.subsystems.ClimberSubsystem.ToggleClimber;
//import org.team3467.robot2020.subsystems.DriveSubsystem.AutoLineup;
import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.Pneumatics;
import org.team3467.robot2020.subsystems.IntakeSubsystem.RunIntake;
import org.team3467.robot2020.subsystems.IntakeSubsystem.ToggleIntake;
import org.team3467.robot2020.subsystems.IntakeSubsystem.ToggleIntakeDrive;
import org.team3467.robot2020.subsystems.IntakeSubsystem.deployIntake;
import org.team3467.robot2020.subsystems.IntakeSubsystem.retractIntake;
//import org.team3467.robot2020.subsystems.LEDSubsystsem.LEDSubsystem;
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathDefault;
import org.team3467.robot2020.subsystems.SPathSubsystem.SPathSubsystem;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.runManual;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.runShooterGate;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.runShooterGateReverse;
import org.team3467.robot2020.subsystems.CommandGroups.*;
import org.team3467.robot2020.subsystems.DriveSubsystem.RocketSpinDrive;
import org.team3467.robot2020.control.XBoxControllerDPad;
import org.team3467.robot2020.control.XBoxControllerTrigger;
import org.team3467.robot2020.control.XboxController;
import org.team3467.robot2020.control.XboxControllerButton;
import org.team3467.robot2020.sensors.Limelight.Limelight;

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
    private final SPathSubsystem m_sPath = new SPathSubsystem();
    private final CD7Subsystem m_CD7 = new CD7Subsystem();
    private final ClimberSubsystem m_climber = new ClimberSubsystem();

    //Autonomous command objects
    private final ThreeBallAuto m_threeBallAuto = new ThreeBallAuto(m_flyWheelsub, m_robotDrive, m_gateSub, m_sPath, m_CD7);
    private final SimpleDrive m_simpleDrive = new SimpleDrive(m_robotDrive);
    private final ThreeBallSide m_threeBallSide = new ThreeBallSide(m_flyWheelsub, m_robotDrive, m_gateSub, m_sPath, m_CD7);
    private final ThreeBallWIntake m_threeBallWIntake = new ThreeBallWIntake(m_flyWheelsub, m_robotDrive, m_gateSub, m_sPath, m_CD7, m_intakeSub);
    private final ThreeBallWAssist m_threeBallWAssist = new ThreeBallWAssist(m_flyWheelsub, m_robotDrive, m_gateSub, m_sPath, m_CD7);
    // private final LEDSubsystem m_led = new LEDSubsystem();

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

    /*
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {  
        // Initialize Pneumatics (start Compressor)
        Pneumatics.getInstance();

        // Initialize Limelight
        Limelight.initialize();
        Limelight.setDriverMode();

        // Configure the button bindings
        configureButtonBindings();

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
    
        // Set the Default command for the ShooterSubsystem
        // Control Shooter Gate using Right Stick Y-Axis
        
        //S path is based on left Y axis
        m_sPath.setDefaultCommand(
            new SPathDefault(m_sPath,
                () -> m_operatorController.getLeftY()));

        //run CD7 in using left trigger, run CD7 out using right trigger
        m_CD7.setDefaultCommand(
            new CD7Default(m_CD7, 
                () -> m_operatorController.getLeftTrigger(),
                () -> m_operatorController.getRightTrigger()));

        m_climber.setDefaultCommand(
            new ClimberDefault(m_climber, 
                ()-> m_operatorController.getRightY()));
        // Add commands to the autonomous command chooser
        // m_chooser.addOption("Simple Auto", m_simpleAuto);
        // m_chooser.addOption("Complex Auto", m_complexAuto);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("DriveDash").add(m_chooser);
        m_chooser.addOption("Three Ball-Wall Shot", m_threeBallAuto);
        m_chooser.addOption("Initiation line drive", m_simpleDrive);
        m_chooser.addOption("Three Ball-Wall Shot with trench Intake", m_threeBallWIntake);
        m_chooser.addOption("Three Ball-Wall Shot from side", m_threeBallSide);
        m_chooser.addOption("Three Ball-Wall Shot with partner assist", m_threeBallWAssist);
    }

    /**
     * Use this method to define your button->command mappings.
     */
    private void configureButtonBindings()
    {
        /*
         * Operator controller
         */
        //Run intake in while held. bumper Left
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperLeft)
            .whileHeld(new RunIntake(m_intakeSub, 1.0));

        //Run Intake out whileheld bumper right
        new XboxControllerButton(m_operatorController, XboxController.Button.kBumperRight)
            .whileHeld(new RunIntake(m_intakeSub, -1.0));

        new XboxControllerButton(m_operatorController, XboxController.Button.kY)
            .whileHeld(new runManual(m_flyWheelsub));

        //Rev shooter for trench shot, button X
        new XboxControllerButton(m_operatorController, XboxController.Button.kX)
            .whileHeld(new PrepareShotCB(m_flyWheelsub, ShooterConstants.kTrenchShotVelocity));
        
        //Rev Shooter for auto line shot, button A
        new XboxControllerButton(m_operatorController, XboxController.Button.kA)
            .whileHeld(new PrepareShotCB(m_flyWheelsub, ShooterConstants.kInitLineShotVelocity));
        
        //Rev Shooter for wall shot, button B
        new XboxControllerButton(m_operatorController, XboxController.Button.kB)
            .whileHeld(new PrepareShotCB(m_flyWheelsub, ShooterConstants.kWallShotVelocity));
        

        // Deploys/Retracts intake

        new XboxControllerButton(m_operatorController, XboxController.Button.kBack)
            .whenPressed(new ToggleIntake(m_intakeSub));

        //Deploys/retracts climber
        new XboxControllerButton(m_operatorController, XboxController.Button.kStart)
            .whenPressed(new ToggleClimber(m_climber));

        //Deploy intake
        new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadUp)
            .whileActiveOnce(new deployIntake(m_intakeSub));
        //Retract intake
        new XBoxControllerDPad(m_operatorController, XboxController.DPad.kDPadDown)
            .whileActiveOnce(new retractIntake(m_intakeSub));
        /*
         * Driver Controller
         */

        // Toggle Intake Deployed/On and Retracted/Off
        new XboxControllerButton(m_driverController, XboxController.Button.kBack)
            .whenPressed(new ToggleIntakeDrive(m_intakeSub));

        new XBoxControllerTrigger(m_driverController, XboxController.Hand.kRight)
            .whileActiveContinuous(new runShooterGate(m_gateSub, m_flyWheelsub, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime).andThen(new WaitCommand(0.25)));

        new XBoxControllerTrigger(m_driverController, XboxController.Hand.kLeft)
            .whileActiveContinuous(new runShooterGate(m_gateSub, m_flyWheelsub,1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime));

        // emergency gate wheel backup
        new XboxControllerButton(m_driverController, XboxController.Button.kBumperRight)
            .whileActiveContinuous(new runShooterGateReverse(m_gateSub, -0.5));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        return m_chooser.getSelected();
    }
}
