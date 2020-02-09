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
import org.team3467.robot2020.Constants.ShooterConstants;
import org.team3467.robot2020.subsystems.DriveSubsystem.SplitArcadeDrive;
import org.team3467.robot2020.subsystems.DriveSubsystem.TankDrive;
import org.team3467.robot2020.subsystems.DriveSubsystem.DriveSubsystem;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;
import org.team3467.robot2020.subsystems.ShooterSubsystem.ShooterSubsystem;
import org.team3467.robot2020.subsystems.DriveSubsystem.RocketSpinDrive;
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeDefault;
import org.team3467.robot2020.subsystems.ShooterSubsystem.AutoShoot;
import org.team3467.robot2020.subsystems.ShooterSubsystem.PCShoot;
import org.team3467.robot2020.subsystems.ShooterSubsystem.RunManualShooter;
import org.team3467.robot2020.subsystems.ShooterSubsystem.ShooterDefault;
import org.team3467.robot2020.control.XboxController;
// import org.team3467.robot2020.control.XboxControllerButton;
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
    private final IntakeSubsystem m_intakeDrive = new IntakeSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

    // The autonomous routines
    // A simple auto routine that drives forward a specified distance, and then stops.
    // private final Command m_simpleAuto =
    // new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);

    // A complex auto routine that drives forward, drops a hatch, and then drives backward.
    // private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_hatchSubsystem);

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    // The driver's controller
    public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    public static XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

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
                    new TankDrive(m_robotDrive, () -> m_driverController.getY(GenericHID.Hand.kLeft), () -> m_driverController.getY(GenericHID.Hand.kRight)));
            break;

        default:
        case DriveConstants.driveMode_SplitArcade:
            m_robotDrive.setDefaultCommand(
                    // A split-stick arcade command, with forward/backward controlled by the left hand,
                    // and turning controlled by the right.
                    new SplitArcadeDrive(m_robotDrive, () -> m_driverController.getY(GenericHID.Hand.kLeft),
                            () -> m_driverController.getX(GenericHID.Hand.kRight)));
            break;

        case DriveConstants.driveMode_RocketSpin:
            m_robotDrive.setDefaultCommand(new RocketSpinDrive(m_robotDrive, () -> m_driverController.getX(GenericHID.Hand.kLeft),
                    () -> m_driverController.getTriggerAxis(GenericHID.Hand.kLeft), () -> m_driverController.getTriggerAxis(GenericHID.Hand.kRight)));
            break;
        }

        m_intakeDrive.setDefaultCommand(new IntakeDefault(m_intakeDrive, m_operatorController, () -> m_operatorController.getY(GenericHID.Hand.kLeft)));

        m_shooterSubsystem.setDefaultCommand(new ShooterDefault(m_shooterSubsystem));

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
        // Run the Shooter Wheel while the 'B' button is pressed.
        new XboxControllerButton(m_operatorController, XboxController.Button.kB)
            .whileHeld(new RunManualShooter(m_shooterSubsystem));

        // Trigger the ShooterGate (shoot a Power Cell) with the 'Y' button
        new XboxControllerButton(m_operatorController, XboxController.Button.kY)
            .whenPressed(new PCShoot(m_shooterSubsystem).withTimeout(ShooterConstants.kShooterGateRunTime));

        new XboxControllerButton(m_operatorController, XboxController.Button.kX)
            .whileHeld(new AutoShoot(m_shooterSubsystem));

        
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
