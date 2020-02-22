/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CD7Subsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;
 
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class CD7Default extends CommandBase
{
    private final CD7Subsystem m_CD7;
    private final  DoubleSupplier m_speedIn, m_speedOut;

    public CD7Default(final CD7Subsystem CD7subsys, final DoubleSupplier speedIn, DoubleSupplier speedOut)
    {
        m_CD7 = CD7subsys;
        m_speedIn = speedIn;
        m_speedOut = speedOut;
        addRequirements(m_CD7);
    } 

    @Override
    public void execute()
    {
        if ( Math.abs(m_speedIn.getAsDouble()) > 0.2){
            m_CD7.driveBelts(m_speedIn.getAsDouble());
        }
        else{
            m_CD7.driveBelts(0.0);
        }
        if ( Math.abs(m_speedOut.getAsDouble()) > 0.2){
            m_CD7.driveBelts(m_speedOut.getAsDouble());
        }
        else{
            m_CD7.driveBelts(0.0);
        }
    }   
}
