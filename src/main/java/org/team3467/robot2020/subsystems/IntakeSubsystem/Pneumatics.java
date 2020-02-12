/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import org.team3467.robot2020.Constants.PneumaticConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pneumatics extends SubsystemBase {

	public static Compressor scorpionCompressor;
	
	// Solenoids
 	private DoubleSolenoid intakePiston;

	// Pneumatics is a singleton
	private static Pneumatics instance = new Pneumatics();

	public static Pneumatics getInstance() {
		return Pneumatics.instance;
	}

	/*
	 * Pneumatics Class Constructor
	 *
	 * The singleton instance is created statically with
	 * the instance static member variable.
	 */
	protected Pneumatics() {
				
		scorpionCompressor = new Compressor();

		initSolenoids();
		
		scorpionCompressor.setClosedLoopControl(true);
		
	}
	
	private void initSolenoids() {
		intakePiston = new DoubleSolenoid(PneumaticConstants.intake_Piston_Forward, PneumaticConstants.intake_Piston_Reverse);
	}
	
	/*
	 * Custom Pneumatics Helper methods
	 */
		

	public void deployIntake()
    {
        intakePiston.set(Value.kForward);
    }
    public void intakeIn() 
    {
        intakePiston.set(Value.kReverse);

    }

	
	/*
	 * Standard Pneumatics methods	
	 */
	
	public void compressorStop() {
		scorpionCompressor.stop();
	}
	
	public void compressorStart() {
		scorpionCompressor.start();
	}

	
}

