
# Infinite Recharge

Robot for 2020 season

This is the repository for all official, working, and completed builds for the Team 3467 2020 robot.

|Subsystem|PDP Slots|Controllers|CAN IDs| Motor|Sensors|
|---------|---------|-----------|-------|------|-------|
|[DriveSubsystem](#subsystem-drivesubsystem)|4|4 Falcon FX (2 control, 2 slaves)|1->4|Falcon 500|Integrated Encoder inside each of the controlling Falcon 500s |
|[IntakeSubsystem](#subsystem-intakesubsystem)|1|||||
|[PCPathSubsystem](#subsystem-pcpathsubsystem)|3|||||
|[ShooterSubsystem](#subsystem-shootersubsystem)|4|2 SparkMax, 1 Victor SPX|5->7|2 Neo (shooter), 1 BAG (hood control)|Integrated NEO Encoders on Shooter motors, 1 VP EncoderSlice on hood control|
|[ClimberSubsystem](#subsystem-climbersubsystem)|1||||
|[Shuffleboard3467](#subsystem-shuffleboard3467)|0||||
|[DiscJockeySubsystem](#subsystem-discjockeysubsystem)|1||||
|[Limelight](#sensors-limelight)|1|||||
|[LEDs](#sensors-leds)|0||||
|[Lidar](#sensors-lidar)|0|||||               1
|[Gyro](#sensors-gyro)|0||1||Pigeon IMU on CAN - attached to Talon or direct to CANbus|               1
|Totals:|15||||

## Subsystem: DriveSubsystem

*Shuffleboard:*

* Shuffleboard Tab: Control Variables
* Shuffleboard Column: DriveSubsystem
* Drive Mode Selection (SplitArcade / Tank / Rocket) _kComboBoxChooser_
* Current Drive Control (Manual / Auto / PathFollow) _kTextView_
* Left Encoder Value
* Right Encoder Value
* PIDF Driving constants
* _Cmd:_ Autonomous Command butttons

*Methods:*

    Constructor: public DriveSubsystem() - read mode selection setting from Shuffleboard

    void drive(Double speed, Double rotation)

    void updateNTEs() - update this subsystem's member variables that are linked to the Network Tables

*Commands:*

    DriveDefault()
    * Run drivebase in specified Drive Mode using controller sticks, triggers, and buttons
    * Check for Shuffleboard value changes in loop, and change member variables if necessary

    DriveByTime(Double time, Double speed, Double rotation)
    * Drive at speed and rotation for specified time
    * If rotation = 0, drive straight using Gyro PID
    * Stop driving on exit

    DriveByDistance(Double distance, Double speed, Double rotation)
    * Drive at speed and rotation for specified distance (encoder clicks?)
    * Use MotionMagic to control drive speed
    * If rotation = 0, drive straight using Gyro PID
    * Stop driving on exit

    DriveTurn(Double angle, Double maxSpeed)
    * Turn in-place to desired angle
    * Use MotionMagic and Gyro to turn to desired angle, never exceeding maxSpeed
    * Stop driving on exit

    Stop():
    * Stop driving (override current command)

    ResetDriveEncoders():
    * Reset encoder values to zero

    SetBrakeMode(boolean On):
    * Turn on/off brake mode on drive controllers

## Subsystem: IntakeSubsystem

*Shuffleboard:*

* Shuffleboard Tab: Control Variables
* Shuffleboard Column: IntakeSubsystem
* Intake Position (retracted/deployed) _kToggleSwitch_
* Intake Speed (0.0 .. 1.0) _kNumberSlider_
* Intake Direction (in/out) _kToggleSwitch_
* Cmd: RunIntake
* Cmd: DeployIntake
* Cmd: RetractIntake

*Methods:*

    Constructor: public IntakeSubsystem() - read speed, direction, and deploy settings from Shuffleboard
    
    void retractIntake()
    
    void deployIntake()

    void runIntake()

    void stopIntake()

    void setIntakeSpeed(double)

    void setIntakeDirection(boolean)

    void updateNTEs() - update this subsystem's member variables that are linked to the Network Tables

*Commands:*

    IntakeDefault():
    * Check "Run Intake" controller toggle button
    * If "Run", use m_speed and m_direction values
    * Check "Deploy Intake" controller toggle button
    * Activate solenoid for desired position
    * Check for Shuffleboard value changes in loop, and change member variables if necessary

    RunIntake():
    * Start Intake using m_speed and m_direction values
    * Check for Shuffleboard value changes in loop, and change member variables if necessary
    * Stop Intake on exit

    StopIntake():
    * Stop Intake

    ReverseIntake():
    * Run Intake in reverse
    * Stop Intake on exit

    RetractIntake():
    * Bring Intake in

    DeployIntake():
    * Push Intake out

## Subsystem: PCPathSubsystem

*Shuffleboard:*

* Shuffleboard Tab: Control Variables
* Shuffleboard Column: PCPathSubsystem
* Path Speed (0.0 .. 1.0) _kNumberSlider_
* Path Direction (in/out) _kToggleSwitch_
* CD7 Center Speed (0.0 .. 1.0) _kNumberSlider_
* CD7 Side Speed (0.0 .. 1.0) _kNumberSlider_
* Cmd: RunPath

*Methods:*

    Constructor: public PCPathSubsystem() - read speeds and direction from Shuffleboard

    void runPath() - run Path & CD7 belts

    void stopPath()

    void setPathSpeed(double)

    void setPathDirection(boolean)

    void setCD7Speeds(double, double)

    void updateNTEs() - update this subsystem's member variables that are linked to the Network Tables

*Commands:*

    PCPathDefault():
    * Check "Run Path" controller toggle button
    * If "Run", use m_speed, m_direction, m_cd7side & m_cd7center values
    * If "Stop", stop all belts
    * Check for Shuffleboard value changes in loop, and change member variables if necessary

    RunPath():
    * Start Path using m_speed and m_direction values
    * Start CD7 using m_cd7side & m_cd7center values
    * Check for Shuffleboard value changes in loop
    * Stop everything on exit

    StopPath():
    * Stop everything

    EjectPath():
    * Run Path in the reverse direction
    * DO NOT run CD7
    * Stop Path on exit

## Subsystem: ShooterSubsystem

*Shuffleboard:*

* Shuffleboard Tab: Control Variables
* ShuffleboardColumn: ShooterSubsystem
* Wheel Mode: (Manual / Tracking) _kToggleSwitch_
* Target Wheel Speed
* Actual Wheel Speed
* Cmd: WheelStart
* Cmd: Shoot
* Wheel PIDF Constants

*Methods:*

    Constructor: public ShooterSubsystem() - read mode and target speed from Shuffleboard

    void runManual() - run Shooter Wheel at speed commanded by Shuffleboard

    void runTracking() - run Shooter Wheel at speed commanded by Limelight tracking

    boolean isWheelAtSpeed() - return TRUE when wheel is equal to target, or within tolerance

    updateNTEs() - update this subsystem's member variables that are linked to the Network Tables

*Commands:*

    ShooterDefault():
    * Check "Run Wheel" controller toggle button
    * If "Run", use m_speed value
    * If "Stop", stop Wheel
    * Check for Shuffleboard value changes in loop, and change member variables if necessary

    RunManual():
    * Start Wheel using m_speed value
    * Check for Shuffleboard value changes in loop
    * Stop everything on exit

    RunTracking():
    * If Limelight sees target, start Wheel using velocity value calculated from Distance
    * Check for Shuffleboard value changes in loop
    * Stop everything on exit

    StopWheel():
    * Stop running wheel.

    ShootPCatSpeed():
    * If Wheel is at target speed, run the Shooter Gate long enough to release one ball to Shooter
    * Wait up to X seconds for Wheel to come up to speed

    ShootPC():
    * Regardless of Wheel speed, run the Shooter Gate long enough to release one ball to Shooter

## Subsystem: ClimberSubsystem

*Shuffleboard:*

*Methods:*

*Commands:*

## Subsystem: Shuffleboard3467

*Methods:*

    Constructor:
    public Shuffleboard3467()
    * Sets up the Shuffleboard page(s), columns, and widgets, and populates them using values from the Constants.java file
    * Builds NetworkTableEntries and puts them in a lookup table indexed by the String representation of the Constants variable name.

    NetworkTableEntry GetNTE(String name) - return NTE associated with name

## Subsystem: DiscJockeySubsystem

*Shuffleboard:*

*Methods:*

*Commands:*

## Sensors: Limelight

*Shuffleboard:*

*Methods:*

    getTx()
    getTy()
    getTa()
    hasTarget()
    getLED()

*Commands:*

    enableLED()
    disableLED()

## Sensors: LEDs

*Shuffleboard:*

*Methods:*

    isEnabled()

*Commands:*

    enable()
    disable()
    setEffect()

## Sensors: Lidar

*Shuffleboard:*

*Methods:*

    getDistance(int side)

*Commands:*

    zeroDistance(int side)

## Sensors: Gyro

*Shuffleboard:*

*Methods:*

*Commands:*

    getCurrentAngle()
    zeroAngle()
