/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3467.robot2020.Constants.CanConstants;

public class IntakeSubsystem extends SubsystemBase
{
    private TalonSRX intakeMotor = new TalonSRX(CanConstants.ground_intake);

    private TalonSRX shooterBeltMotor = new TalonSRX(CanConstants.shooter_belt);

    private TalonSRX centerBeltMotor = new TalonSRX(CanConstants.center_belt);
    private TalonSRX sideBeltMotors = new TalonSRX(CanConstants.side_belts);

    public IntakeSubsystem() 
    {
        sideBeltMotors.setInverted(true);
    }

    public void driveIntake(double speed)
    {
        intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void driveBelts(double speed)
    {
        sideBeltMotors.set(ControlMode.PercentOutput, speed);
        centerBeltMotor.set(ControlMode.PercentOutput, speed);
        shooterBeltMotor.set(ControlMode.PercentOutput, speed);
    }
    

}