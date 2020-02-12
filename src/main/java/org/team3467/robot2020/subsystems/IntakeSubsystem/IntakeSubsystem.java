/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3467.robot2020.Constants.CanConstants;
import org.team3467.robot2020.Constants.PneumaticConstants;

public class IntakeSubsystem extends SubsystemBase
{
    // PC Intake motor
    private TalonSRX m_intakeMotor = new TalonSRX(CanConstants.ground_intake);

    // Motor driving shooter "belt" (i.e. PC transport path)
    private TalonSRX m_shooterBeltMotor = new TalonSRX(CanConstants.shooter_belt);

    // Motors driving belts on CD7 intake
    private TalonSRX m_centerBeltMotor = new TalonSRX(CanConstants.center_belt);
    private TalonSRX m_sideBeltMotors = new TalonSRX(CanConstants.side_belts);

    // Intake Deploy/Retract solenoids
 	private DoubleSolenoid m_intakePiston;

    public IntakeSubsystem() 
    {
        m_sideBeltMotors.setInverted(true);
        m_intakePiston = new DoubleSolenoid(PneumaticConstants.kIntakePistonDeploy, PneumaticConstants.kIntakePistonRetract);
    }
    
    public void deployIntake()
    {
        m_intakePiston.set(Value.kForward);
    }
    public void retractIntake() 
    {
        m_intakePiston.set(Value.kReverse);
    }
    
    public void driveIntake(double speed)
    {
        m_intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void driveBelts(double speed)
    {
        m_sideBeltMotors.set(ControlMode.PercentOutput, speed);
        m_centerBeltMotor.set(ControlMode.PercentOutput, speed);
        m_shooterBeltMotor.set(ControlMode.PercentOutput, speed);
    }
    

}