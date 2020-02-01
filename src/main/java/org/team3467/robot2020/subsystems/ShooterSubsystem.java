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

public class ShooterSubsystem extends SubsystemBase {
    private PWMSparkMax shooterLeftMotor = new PWMSparkMax(0);
    private PWMSparkMax shooterRightMotor = new PWMSparkMax(1);

    private TalonSRX shooter_intake_1 = new TalonSRX(6);
    private TalonSRX shooter_intake_2 = new TalonSRX(7);

    public ShooterSubsystem() {
        shooterLeftMotor.setInverted(true);
        shooter_intake_1.setInverted(true);
        shooter_intake_2.setInverted(true);
    }

    
    public void ShooterIntake(double speed) {
        shooter_intake_1.set(ControlMode.PercentOutput, speed);
        shooter_intake_2.set(ControlMode.PercentOutput, speed);
    }

    public void SpinShooter(double speed) {
        shooterRightMotor.set(speed);
        shooterLeftMotor.set(speed);

        SmartDashboard.putNumber("Right speed", shooterRightMotor.getSpeed());
        SmartDashboard.putNumber("Left speed", shooterLeftMotor.getSpeed());
    }
  
}