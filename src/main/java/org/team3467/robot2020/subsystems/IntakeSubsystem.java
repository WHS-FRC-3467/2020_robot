/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IntakeSubsystem extends SubsystemBase {
    private TalonSRX intakeMotor = new TalonSRX(5);

    private TalonSRX ShooterBeltMotor = new TalonSRX(7);

    private TalonSRX sideBeltMotors = new TalonSRX(9);
    private TalonSRX centerBeltMotor = new TalonSRX(8);

    public IntakeSubsystem() {
        sideBeltMotors.setInverted(true);
    }

    
    public void driveIntake(double speed) {
        intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void driveBelts(double speed, Boolean on) {
        if (on) {
            sideBeltMotors.set(ControlMode.PercentOutput, speed);
            centerBeltMotor.set(ControlMode.PercentOutput, speed);
            ShooterBeltMotor.set(ControlMode.PercentOutput, speed);
        } else {
            sideBeltMotors.set(ControlMode.PercentOutput, 0.0);
            centerBeltMotor.set(ControlMode.PercentOutput, 0.0);
            ShooterBeltMotor.set(ControlMode.PercentOutput, 0.0);
        }
    }
  
}