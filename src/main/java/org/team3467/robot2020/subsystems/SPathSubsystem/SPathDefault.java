/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.SPathSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

public class SPathDefault extends CommandBase
{
    private final SPathSubsystem m_sPath;
    private final DoubleSupplier m_pathSpeed;
    private final DoubleSupplier m_gateRevSpeed;

    public SPathDefault(final SPathSubsystem sPathSubsys, final DoubleSupplier pathSpeed, final DoubleSupplier gateReverseSpeed)
    {
        m_sPath = sPathSubsys;
        m_pathSpeed = pathSpeed;
        m_gateRevSpeed = gateReverseSpeed;
        addRequirements(m_sPath);
    }

    @Override
    public void execute()
    {
        if (Math.abs(m_pathSpeed.getAsDouble()) > 0.2)
        {
            m_sPath.driveSPath(m_pathSpeed.getAsDouble());
        }
        else
        {
            m_sPath.driveSPath(0.0);
        }

        if (Math.abs(m_gateRevSpeed.getAsDouble()) > 0.2)
        {
            m_sPath.driveGate((-1.0) * m_gateRevSpeed.getAsDouble());
        }
        else
        {
            m_sPath.driveGate(0.0);
        }

    }
}