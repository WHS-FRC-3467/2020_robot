/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.sensors.BeamSensor;

import edu.wpi.first.wpilibj.DigitalInput;

public class BeamSensor
{
    // Class to be used with IR sensors to sense if the beam is still connected or broken
    private DigitalInput rdg; // Reading from RoboRIO DIO port
    private Boolean lStat; // Value that we get from the RIO port
    private Boolean lInvert; // Flag to indicate if logic will be inverted

    public BeamSensor(int iPort, boolean lFlip)
    {
        /* Constructor indicates which DIO port will be used which is given by iPort
        whether or not the logic is inverted given by lFlip */
        rdg = new DigitalInput(iPort);
        lInvert = lFlip;
    }

    public Boolean GetStatus()
    {
        // Getting status from the IR sensors to determine if the beam is connected or broken
        lStat = rdg.get();
        if (lInvert == true)
        {
            lStat = !lStat;
        }
        
        return lStat;
    }

}
