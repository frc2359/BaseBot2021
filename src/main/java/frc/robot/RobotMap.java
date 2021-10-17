package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around. -- This is a standard created by the previous team (thanks!).
 */

public class RobotMap {
    //--NOTE: These numbers are placeholders until we find the correct IDs--
    public static final int ID_DRIVE_FL = 00;
    public static final int ID_DRIVE_FR = 00;
    public static final int ID_DRIVE_BR = 00;
    public static final int ID_DRIVE_BL = 00;

    // Motor Control Brake Modes
        //false = coast; true = brake
    public static final boolean BRAKE_MODE_DRIVE = true; //when the controller is moved back to a neutral position, the motors will STOP
}
