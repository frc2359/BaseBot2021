package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around. -- This is a standard created by the previous team (thanks!).
 */

public interface RobotMap { // Change this to an enum
    //--NOTE: These numbers are placeholders until we find the correct IDs--
    public static final int ID_DRIVE_FL = 00;
    public static final int ID_DRIVE_FR = 01;
    public static final int ID_DRIVE_BR = 02;
    public static final int ID_DRIVE_BL = 03;

    //This is the mapping of the buttons to the various functions of the robot
    public static final Hand THROTTLE = Hand.kRight; //Throttle mapped to the HAND SIDE
    public static final Hand REVERSE = Hand.kLeft;
    public static final Hand STEER_SIDE = Hand.kLeft;

    // Motor Control Brake Modes
        //false = coast; true = brake
    public static final boolean BRAKE_MODE_DRIVE = true; //when the controller is moved back to a neutral position, the motors will STOP
}
