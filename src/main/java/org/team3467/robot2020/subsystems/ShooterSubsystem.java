/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3467.robot2020.Constants.CanConstants;

public class ShooterSubsystem extends SubsystemBase {
    private PWMSparkMax shooterLeftMotor = new PWMSparkMax(0);
    private PWMSparkMax shooterRightMotor = new PWMSparkMax(1);

    private TalonSRX shooter_intake = new TalonSRX(CanConstants.shooter_intake);

    public ShooterSubsystem() {
        shooterLeftMotor.setInverted(true);
        shooter_intake.setInverted(true);
    }

    
    public void ShooterIntake(double speed, boolean on) {
        if (on) {
            shooter_intake.set(ControlMode.PercentOutput, speed);
        } else {
            shooter_intake.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void SpinShooter(double speed, boolean on) {
        if (on) {
            shooterRightMotor.set(speed);
            shooterLeftMotor.set(speed);

            SmartDashboard.putNumber("Right speed", shooterRightMotor.getSpeed());
            SmartDashboard.putNumber("Left speed", shooterLeftMotor.getSpeed());
        } else {
            shooterRightMotor.set(0.0);
            shooterLeftMotor.set(0.0);

            SmartDashboard.putNumber("Right speed", shooterRightMotor.getSpeed());
            SmartDashboard.putNumber("Left speed", shooterLeftMotor.getSpeed());
        }
    }
  
}