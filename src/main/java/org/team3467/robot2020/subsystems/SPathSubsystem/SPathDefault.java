/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.SPathSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SPathDefault extends CommandBase
{
    private final SPathSubsystem m_sPath;
    private final  DoubleSupplier m_beltSpeed;

    public SPathDefault(final SPathSubsystem sPathSubsys, final DoubleSupplier beltSpeed)
    {
        m_sPath = sPathSubsys;
        m_beltSpeed = beltSpeed;
        addRequirements(m_sPath);
    }

    @Override
    public void execute()
    {
        if ( Math.abs(m_beltSpeed.getAsDouble()) > 0.2){
            m_sPath.driveBelts(-m_beltSpeed.getAsDouble());
        }
        else{
            m_sPath.driveBelts(0.0);
        }
    }   
}