/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team3467.robot2020.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pneumatics extends SubsystemBase {

	public static Compressor scorpionCompressor;
	
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

		scorpionCompressor.setClosedLoopControl(true);
		
	}
	
	/*
	 * Custom Pneumatics Helper methods
	 */
		
	
	/*
	 * Standard Pneumatics methods	
	 */
	
	// public void compressorStop() {
	// 	scorpionCompressor.stop();
	// }
	
	// public void compressorStart() {
	// 	scorpionCompressor.start();
	// }

	
}

