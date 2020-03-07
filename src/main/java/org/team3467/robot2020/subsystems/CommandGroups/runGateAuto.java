/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.CommandGroups;

import java.util.function.DoubleSupplier;

import org.team3467.robot2020.Constants;
import org.team3467.robot2020.subsystems.ShooterFlyWheelSubsystem.FlyWheelSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.GateSubsystem;
import org.team3467.robot2020.subsystems.ShooterGateSubsystem.runShooterGate;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class runGateAuto extends SequentialCommandGroup {
  /**
   * Creates a new runGateAuto.
   */
  private final GateSubsystem m_gateSub;
  private final FlyWheelSubsystem m_flyWheel;
  private final DoubleSupplier m_runGate;
  private final DoubleSupplier m_runGateMultiple;
  
  public runGateAuto(final GateSubsystem gateSubsys, FlyWheelSubsystem flyWheelSubsys, final DoubleSupplier runGate, final DoubleSupplier runGateMultiple) {
    m_runGate = runGate;
    m_gateSub = gateSubsys;
    m_runGateMultiple = runGateMultiple;
    m_flyWheel = flyWheelSubsys;
    addRequirements(gateSubsys);
    if (m_runGate.getAsDouble() > 0.3) {
      addCommands(
        new runShooterGate(m_gateSub, m_flyWheel, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime),
        new WaitCommand(2));
      
    } 
    else if (m_runGateMultiple.getAsDouble() > 0.3) {
      addCommands(
        new runShooterGate(m_gateSub, m_flyWheel, 1.0).withTimeout(Constants.ShooterConstants.kShooterGateRunTime),
        new WaitCommand(0.25));
      
    }
    else {
      m_gateSub.runShooterGate(0.0);
    }
  }
}
