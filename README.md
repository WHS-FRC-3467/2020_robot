## Infinite Recharge
Robot for 2020 season

This is the repository for all official, working, and completed builds for the Team 3467 2020 robot.

|Subsystem|PDP Slots|Controllers|CAN IDs| Motor|Sensors|
|---------|---------|-----------|-------|------|-------|
|[Drivebase](#subsystem-drivebase)|4|4 Falcon FX (2 control, 2 slaves)|1->4|Falcon 500|Integrated Encoder inside each of the controlling Falcon 500s |
|[PCShooter](#subsystem-pcshooter)|3|2 SparkMax, 1 Victor SPX|5->7|2 Neo (shooter), 1 BAG (hood control)|1 Rev Through-bore Encoder on Shooter, 1 VP EncoderSlice on hood control|
|[PCIntake](#subsystem-pcintake)|?|||||
|[PCIndexer](#subsystem-pcindexer)|?|||||
|[Climber](#subsystem-climber)|?||||
|[DiscJockey](#subsystem-discjockey)|?||||
|[Limelight](#subsystem-limelight)|1|||||
|[LEDs](#subsystem-leds)|?||||
|[Lidar](#subsystem-lidar)|?|||||               1
|[Gyro](#subsystem-gyro)|0||1||Pigeon IMU on CAN - attached to Talon or direct to CANbus|               1
|Totals:|??||||

## Subsystem: Drivebase

*Methods:*

*Commands:*

    DriveBot() - teleoperated driving
    <other commands as needed>

## Subsystem: 

*Methods:*

*Commands:*


## Subsystem: Limelight

*Methods:*

    getTx()
    getTy()
    getTa()
    hasTarget()
    getLED()

*Commands:*

    enableLED()
    disableLED()


## Subsystem: LEDs

*Methods:*

    isEnabled()

*Commands:*

    enable()
    disable()
    setEffect()


## Subsystem: Lidar

*Methods:*

    getDistance(int side)

*Commands:*

    zeroDistance(int side)


## Subsystem: Gyro

*Methods:*
    
*Commands:*

    getCurrentAngle()
    zeroAngle()
