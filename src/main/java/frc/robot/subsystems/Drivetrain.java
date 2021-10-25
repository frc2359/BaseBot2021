package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.CANifier.*;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import static frc.robot.RobotMap.*;
import frc.robot.IO;


public class Drivetrain implements Subsystem {
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(ID_DRIVE_FL);
    WPI_VictorSPX frontRight = new WPI_VictorSPX(ID_DRIVE_FR);
    WPI_VictorSPX backLeft = new WPI_VictorSPX(ID_DRIVE_BR);
    WPI_VictorSPX backRight = new WPI_VictorSPX(ID_DRIVE_BL);
    Timer time = new Timer(); // timer for controlling timedDrive
    
    // Setup Differential Drive based on Master Motor Controllers
    private DifferentialDrive drive = new DifferentialDrive(frontLeft, frontRight);

    //--IMPORTED FROM FRC_2021--

    public void init() {
        /* Motor controllers default motor safety OFF.
            WPI drive trains default motor safety ON.
            Experiment with different enables below.... */
        //frontLeft.setSafetyEnabled(true);
        //frontRight.setSafetyEnabled(true);
        //drive.setSafetyEnabled(false);


        //Reset Motor Controllers to Factory Configuration
        frontLeft.configFactoryDefault();
        frontRight.configFactoryDefault();
        backLeft.configFactoryDefault();
        backRight.configFactoryDefault();
        
        //Set motors that are on the same side to follow each other (both left together, both right together)
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);


        //Set Motor Direction and Encoder Sensor Phase
        frontLeft.setInverted(false);      // Positive is forward
        backLeft.setInverted(InvertType.FollowMaster);     
        frontRight.setInverted(true);      // Invert so positive is forward
        backRight.setInverted(InvertType.FollowMaster);

        frontLeft.setSensorPhase(false); // Check
        frontRight.setSensorPhase(true); // Check

        //Set Brake/Coast Options
        frontLeft.setNeutralMode(BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        backLeft.setNeutralMode(BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        frontRight.setNeutralMode(BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        backRight.setNeutralMode(BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        

        //Set Math.clamp Switch Positions
        final int kTimeoutMs = 30;  // Move to RobotMap??

        frontLeft.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, kTimeoutMs);
        frontRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, kTimeoutMs);
        
        /*
        * diff drive assumes (by default) that right side must be negative to move
        * forward. Change to 'false' so positive/green-LEDs moves robot forward
        */
        drive.setRightSideInverted(false); // do not change this
    }
    
    // public DifferentialDrive smartDrive = new DifferentialDrive(driveBase[0], driveBase[1]);

    public void initDefaultCommand() {}

    public void drive() { 
        //convert the x-axis value given by the controller into a multiplier
        double lMult = 1; //speed multiplier
        double rMult = 1; //speed multiplier
        if(IO.getDriveXAxis() < 0) { //This is for steering. We will need to check the functionality of this 
            rMult = 0.5;
        } else {
            lMult = 0.5; 
        }
        if(IO.reverseTriggerIsPressed() && IO.throttleTriggerIsPressed()) { 
            //When both triggers are pressed, stop the robot. This is mostly to avoid potential issues that arise if there is no conditional here.
            frontLeft.stopMotor();
            frontRight.stopMotor();
        } else if(IO.throttleTriggerIsPressed()) {
            frontLeft.pidWrite(IO.getDriveTrigger() * lMult);
            frontRight.pidWrite(IO.getDriveTrigger() * rMult);
        } else if(IO.reverseTriggerIsPressed()) {
            frontLeft.pidWrite(IO.getReverseTrigger() * lMult);
            frontRight.pidWrite(IO.getReverseTrigger() * rMult);
        }
    }

    public void stopMotors() {
        frontLeft.stopMotor();
        frontRight.stopMotor();
        //should not be needed
        backRight.stopMotor();
        backLeft.stopMotor();
    }
}

