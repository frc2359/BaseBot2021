package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;
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


public class DriveTrain implements Subsystem {
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.ID_DRIVE_FL);
    WPI_VictorSPX frontRight = new WPI_VictorSPX(RobotMap.ID_DRIVE_FR);
    WPI_VictorSPX backLeft = new WPI_VictorSPX(RobotMap.ID_DRIVE_BR);
    WPI_VictorSPX backRight = new WPI_VictorSPX(RobotMap.ID_DRIVE_BL);
    Timer time = new Timer(); // timer for controlling timedDrive
    // Setup Differential Drive based on Master Motor Controllers
    private DifferentialDrive drive = new DifferentialDrive(frontLeft,frontRight);

    //--IMPORTED FROM FRC_2021--

    public void init() {
        /* Motor controllers default motor safety OFF.
            WPI drive trains default motor safety ON.
            Experiment with different enables below.... */
        //m_LeftDrive_1.setSafetyEnabled(true);
        //m_RightDrive_1.setSafetyEnabled(true);
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
        frontLeft.setNeutralMode(RobotMap.BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        backLeft.setNeutralMode(RobotMap.BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        frontRight.setNeutralMode(RobotMap.BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        backRight.setNeutralMode(RobotMap.BRAKE_MODE_DRIVE ? NeutralMode.Brake : NeutralMode.Coast);
        

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

    public void initDefaultCommand() {
        // smartDrive.setSafetyEnabled(false);
    }

    public void setDriveOrientation (boolean _flag){    // true = forward; false = reverse
        driveOrientationFwd = _flag;
    }

    public void drive(double l, double r) { 

        int inverse = driveOrientationFwd ? 1 : -1;

        frontLeft.pidWrite(l * RobotMap.DRIVE_THROTTLE * inverse);
        frontRight.pidWrite(r * RobotMap.DRIVE_THROTTLE * inverse);

    }

    public void stopMotors() {
        //for (int index = 0; index < driveBase.length; index++) {
        //    driveBase[index].stopMotor();
        //}
        frontLeft.stopMotor();
        backLeft.stopMotor();
    }

    public void log() {
        SmartDashboard.putNumber("Encoders Distance", getDistanceTraveled());
        //SmartDashboard.putNumber("Encoders Index", encoders.length);
    }

    public double deadBand (double _val) {
        if (_val > 1) {
            _val = 1;
        }
        if (_val < -1) {
            _val = -1;
        } 
        if (_val <= RobotMap.DEADBAND_VALUE_DRIVER && _val >= -RobotMap.DEADBAND_VALUE_DRIVER) {
            return 0;
        } else {
            return _val;
        }
    }
}

