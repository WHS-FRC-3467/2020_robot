/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ClimberSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ClimberDefault extends CommandBase {
  private final ClimberSubsystem m_climber;
  private final  DoubleSupplier m_speed;

    public ClimberDefault(final ClimberSubsystem climberSubsys, final DoubleSupplier speed)
    {
        m_climber = climberSubsys;
        m_speed = speed;

        addRequirements(m_climber);
    } 

    @Override
    public void execute()
    {
      if ( Math.abs(m_speed.getAsDouble()) > 0.1){
        m_climber.runWinch(m_speed.getAsDouble());
      }
      else {
      m_climber.runWinch(0.0);
      }
    }   
}
