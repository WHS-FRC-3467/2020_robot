package org.team3467.robot2020.subsystems.ShooterGateSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team3467.robot2020.Constants.CanConstants;
import org.team3467.robot2020.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;


public class GateSubsystem extends SubsystemBase
{
    
    TalonSRX m_shooterGate = new TalonSRX(CanConstants.shooter_gate);
    
    boolean m_useFalcons = ShooterConstants.kUseFalcons;

    public GateSubsystem()
    {

        // Decide which motors to use for shooting
        m_shooterGate.setNeutralMode(NeutralMode.Brake);
        SmartDashboard.putNumber("ShooterGateSpeed", ShooterConstants.kShooterGateSpeed);
    }


    /*
     *
     *  Shooter Gate control
     * 
     */

    /**
     * void runShooterGate() - run the shooter gate wheel at the speed given on the SmartDashboard
     */
    public void runShooterGate()
    {
        m_shooterGate.set(ControlMode.PercentOutput, (SmartDashboard.getNumber("ShooterGateSpeed", ShooterConstants.kShooterGateSpeed)));
    }

    /**
     * void runShooterGate() - run the shooter gate wheel at the speed given on the SmartDashboard
     */
    public void runShooterGate(double speed)
    {
        m_shooterGate.set(ControlMode.PercentOutput, speed);
    }

    /**
     * void stopShooterGate() - set Shooter Gate velocity to 0.0
     */
    public void stopShooterGate()
    {
        runShooterGate(0.0);
    }
}