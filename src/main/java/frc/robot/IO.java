package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.Joystick;

public class IO {
    private static XboxController driver = new XboxController(0); //this is a beta feature that was not used in the original FLC

    public static boolean driverTriggerIsPressed() {
        boolean isPressed = driver.getTriggerAxis(Hand.kRight) > 0 ? true : false;
        return isPressed;
    }
}
