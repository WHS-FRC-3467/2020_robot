/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

import org.team3467.robot2020.Constants.DriveConstants;
import org.team3467.robot2020.Constants.OIConstants;
import org.team3467.robot2020.commands.SplitArcadeDrive;
import org.team3467.robot2020.commands.TankDrive;
import org.team3467.robot2020.commands.RocketSpinDrive;
import org.team3467.robot2020.commands.IntakeCommand;
import org.team3467.robot2020.commands.ShooterCommand;
import org.team3467.robot2020.subsystems.DriveSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem;
import org.team3467.robot2020.subsystems.ShooterSubsystem;
import org.team3467.robot2020.control.XboxController;
// import org.team3467.robot2020.control.XboxControllerButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very little robot logic should actually be
 * handled in the {@link Robot} periodic methods (other than the scheduler calls). Instead, the structure of the robot (including subsystems, commands, and
 * button mappings) should be declared here.
 */
public class RobotContainer
{

    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final IntakeSubsystem m_intakeDrive = new IntakeSubsystem();
    private final ShooterSubsystem m_shooterDrive = new ShooterSubsystem();


    // The autonomous routines
    // A simple auto routine that drives forward a specified distance, and then stops.
    //private final Command m_simpleAuto =
    //   new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);

    // A complex auto routine that drives forward, drops a hatch, and then drives backward.
    //private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_hatchSubsystem);

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    // The driver's controller
    public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    public static XboxController m_opperatorController = new XboxController(OIConstants.kOpperatorControllerPort);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();

        // Configure default commands
        // Set the default drive command to split-stick arcade drive
        switch (DriveConstants.m_driveMode)
        {
        case DriveConstants.driveMode_Tank:
            m_robotDrive.setDefaultCommand(
                new TankDrive(m_robotDrive,
                    () -> m_driverController.getY(GenericHID.Hand.kLeft),
                    () -> m_driverController.getY(GenericHID.Hand.kRight)));
            break;

        default:
        case DriveConstants.driveMode_SplitArcade:
            m_robotDrive.setDefaultCommand(
                // A split-stick arcade command, with forward/backward controlled by the left hand, 
                // and turning controlled by the right.
                new SplitArcadeDrive(m_robotDrive,
                    () -> m_driverController.getY(GenericHID.Hand.kLeft),
                    () -> m_driverController.getX(GenericHID.Hand.kRight)));
            break;

        case DriveConstants.driveMode_RocketSpin:
            m_robotDrive.setDefaultCommand(
                new RocketSpinDrive(m_robotDrive,
                    () -> m_driverController.getX(GenericHID.Hand.kLeft),
                    () -> m_driverController.getTriggerAxis(GenericHID.Hand.kLeft),
                    () -> m_driverController.getTriggerAxis(GenericHID.Hand.kRight)));
            break;
        }

       m_intakeDrive.setDefaultCommand(
           new IntakeCommand(m_intakeDrive, 
            () -> m_opperatorController.getY(GenericHID.Hand.kRight),
            () -> m_driverController.getTriggerAxis(GenericHID.Hand.kRight)));

        m_shooterDrive.setDefaultCommand(
            new ShooterCommand(m_shooterDrive, m_driverController));

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
        // Run a command when the 'A' button is pressed.
        // new XboxControllerButton(m_driverController, XboxController.Button.).whenPressed();
        
        // Start a command when the 'B' button is pressed, and end it when the button is released, or when it ends naturally.
        // new XboxControllerButton(m_driverController, XboxController.Button.kB).whenPressed);

        // While holding the right shoulder button, run a command repeatedly, restarting the command if necessary
        // new XboxControllerButton(m_driverController, XboxController.Button.kBumperLeft).whenPressed();
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
