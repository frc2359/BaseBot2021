package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.Joystick;
import static frc.robot.RobotMap.*;

public class IO {
    private static XboxController driver = new XboxController(DRIVE_PORT); //this is a beta feature that was not used in the original code

    //Returns whether or not the trigger mapped to the throttle is pressed.
    public static boolean throttleTriggerIsPressed() {
        return driver.getTriggerAxis(RobotMap.THROTTLE) > 0 ? true : false;
    }
    
    //Returns the current value of the trigger mapped to the throttle.
    public static double getDriveTrigger() {
        return driver.getTriggerAxis(RobotMap.THROTTLE);
    }

    //Returns whether or not the trigger mapped to reverse is pressed.
    public static boolean reverseTriggerIsPressed() {
        return driver.getTriggerAxis(RobotMap.REVERSE) > 0 ? true : false;
    }

    //Returns the current value of the trigger mapped to the reverse.
    public static double getReverseTrigger() {
        return driver.getTriggerAxis(RobotMap.REVERSE);
    }

    public static double getDriveXAxis() {
        return driver.getX(RobotMap.STEER_SIDE);
    }
}
