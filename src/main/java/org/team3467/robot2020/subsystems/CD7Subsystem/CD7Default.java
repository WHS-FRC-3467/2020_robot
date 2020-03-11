/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CD7Subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

public class CD7Default extends CommandBase
{
    Timer time;
    private final CD7Subsystem m_CD7;
    private final DoubleSupplier m_speedIn;

    public CD7Default(final CD7Subsystem CD7subsys, final DoubleSupplier speedIn)
    {
        time = new Timer();
        m_CD7 = CD7subsys;
        m_speedIn = speedIn;
        addRequirements(m_CD7);
    }

    @Override
    public void execute()
    {
        if (Math.abs(m_speedIn.getAsDouble()) > 0.2)
        {
            if ((Timer.getFPGATimestamp() % 1) < 0.5)
            {

                m_CD7.driveBelts(m_speedIn.getAsDouble());
            }
            else
            {
                m_CD7.driveBelts(-m_speedIn.getAsDouble());
            }
        }
        else
        {
            m_CD7.driveBelts(0.0);
        }
    }
}
