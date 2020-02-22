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
    private final  DoubleSupplier m_leftBeltSpeed;
    private final  DoubleSupplier m_rightBeltSpeed;

    public IntakeDefault(final IntakeSubsystem subsystem, final DoubleSupplier leftBeltSpeed, final DoubleSupplier rightBeltSpeed)
    {
        m_intake = subsystem;
        m_leftBeltSpeed = leftBeltSpeed;
        m_rightBeltSpeed = rightBeltSpeed;
        addRequirements(m_intake);
    }

    @Override
    public void execute()
    {
        if (Math.abs(m_rightBeltSpeed.getAsDouble() - m_leftBeltSpeed.getAsDouble()) > 0.2) {
            m_intake.driveBelts(m_rightBeltSpeed.getAsDouble() - m_leftBeltSpeed.getAsDouble());
        }
    }   
}
