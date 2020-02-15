/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;
 
import org.team3467.robot2020.subsystems.IntakeSubsystem.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeDefault extends CommandBase
{
    private final IntakeSubsystem m_intake;
    private final  DoubleSupplier m_left_intakeSpeed;
    private final  DoubleSupplier m_right_intakeSpeed;
    private final  DoubleSupplier m_beltSpeed;

    public IntakeDefault(final IntakeSubsystem subsystem, final DoubleSupplier beltSpeed,
                        final DoubleSupplier left_intakeSpeed, final DoubleSupplier right_intakeSpeed)
    {
        m_intake = subsystem;
        m_beltSpeed = beltSpeed;
        m_left_intakeSpeed = left_intakeSpeed;
        m_right_intakeSpeed = right_intakeSpeed;
        addRequirements(m_intake);
    }

    @Override
    public void execute()
    {
        m_intake.driveIntake( m_right_intakeSpeed.getAsDouble() - m_left_intakeSpeed.getAsDouble());
        if ( Math.abs(m_beltSpeed.getAsDouble()) > 0.2){
            m_intake.driveBelts(-m_beltSpeed.getAsDouble());
        }
        else{
            m_intake.driveBelts(0.0);
        }
    }   
}
