package frc.robot;

// import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around. -- This is a standard created by the previous team (thanks!).
 */

public interface RobotMap { // Change this to an enum
    public static final int ID_DRIVE_FR = 0;
    public static final int ID_DRIVE_FL = 1;
    public static final int ID_DRIVE_BR = 2;
    public static final int ID_DRIVE_BL = 3;

    public static final double DRIVE_SPEED_MULT = 0.6;

    //This is the mapping of the buttons to the various functions of the robot
    public static final Hand THROTTLE = Hand.kRight; //Throttle mapped to the HAND SIDE
    public static final Hand REVERSE = Hand.kLeft;
    public static final Hand STEER_SIDE = Hand.kLeft;
    public static final int  DRIVE_PORT = 0;

    // Motor Control Brake Modes
        //false = coast; true = brake
    public static final boolean BRAKE_MODE_DRIVE = true; //when the controller is moved back to a neutral position, the motors will STOP
}
