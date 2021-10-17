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
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class Drivetrain implements Subsystem {
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.ID_DRIVE_FL);
    WPI_VictorSPX frontRight = new WPI_VictorSPX(RobotMap.ID_DRIVE_FR);
    WPI_VictorSPX backLeft = new WPI_VictorSPX(RobotMap.ID_DRIVE_BR);
    WPI_VictorSPX backRight = new WPI_VictorSPX(RobotMap.ID_DRIVE_BL);
}
