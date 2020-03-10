/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.LEDSubsystsem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDSubsystem extends SubsystemBase {
  AddressableLED m_LED = new AddressableLED(0);
  AddressableLEDBuffer m_buffer = new AddressableLEDBuffer(22);
  public LEDSubsystem() {
    m_LED.setLength(m_buffer.getLength());
    m_LED.setData(m_buffer);
    m_LED.start();
  }

  public void setAll(int red, int green, int blue) {
    for (var i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, red, green, blue);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
