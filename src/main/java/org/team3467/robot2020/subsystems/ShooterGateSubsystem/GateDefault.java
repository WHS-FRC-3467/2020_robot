/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.ShooterGateSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;

public class GateDefault extends CommandBase
{
    private final GateSubsystem m_gateSub;
    private final DoubleSupplier m_gateSpeed;
  
  
    public GateDefault(final GateSubsystem gateSubsys, final DoubleSupplier gateSpeed) {
      m_gateSpeed = gateSpeed;
      m_gateSub = gateSubsys;

      addRequirements(gateSubsys);
    }
  
    // Called when the command is initially scheduled.
    @Override
      public void execute()
      {
        if (Math.abs(m_gateSpeed.getAsDouble()) > 0.1){
          m_gateSub.runShooterGate(m_gateSpeed.getAsDouble());
        }
        else{
          m_gateSub.runShooterGate(0.0);
        }
      }
}
