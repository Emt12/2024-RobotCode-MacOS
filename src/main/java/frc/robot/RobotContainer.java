// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.geometry.Translation2d;
  import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.TurnToAngleCmd;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.subsystems.ElevatorSubsystem;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Limelight m_Camera = new Limelight();
  private final Dashboard dashboard = new Dashboard();
  private final Shooter shooter = new Shooter();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  
  // The driver's controller
  //XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  Joystick js =  new Joystick(0);
  Robot hey = new Robot();
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //PutOnDashboard();
    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                //-MathUtil.applyDeadband(m_driverController.getLeftY()/4, OIConstants.kDriveDeadband),
                //-MathUtil.applyDeadband(m_driverController.getLeftX()/4, OIConstants.kDriveDeadband),
                //-MathUtil.applyDeadband(m_driverController.getRightX()/4, OIConstants.kDriveDeadband),

                //MathUtil.applyDeadband(js.getRawAxis(0), OIConstants.kDriveDeadband)/20,
                -MathUtil.applyDeadband(js.getRawAxis(1)/4, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(js.getRawAxis(0)/4, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(js.getRawAxis(3)/4, OIConstants.kDriveDeadband),
                

                true, true),
            m_robotDrive));
    
    
}


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
   //button config
    /*new JoystickButton(m_driverController, Button.kR1.value)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));
    new JoystickButton(m_driverController, 1)
        .whileTrue(new TurnToAngleCmd(m_robotDrive,m_Camera)); // feeding horizontal angle value from limelight to PID controller*/

    new JoystickButton(js, 8)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));

    new JoystickButton(js, 4)
        .whileTrue(new TurnToAngleCmd(m_robotDrive,m_Camera)); // feeding horizontal angle value from limelight to PID controller
    
    new JoystickButton(js, 5)
        .whileTrue(new RunCommand( () -> m_robotDrive.resetRelative(), m_robotDrive));
    
    new JoystickButton(js, 2)
        .whileTrue(new RunCommand(
            () -> elevatorSubsystem.testStart(-0.05), 
            elevatorSubsystem));
        //.whileTrue(new TurnToAngleCmd(m_robotDrive, m_Camera));
    
    new JoystickButton(js, 7)
        .whileTrue(new RunCommand(
            () -> shooter.shoot(),
            shooter));
        /* .whileFalse(new RunCommand(
            () -> shooter.stop(),
            shooter));*/
    
    new JoystickButton(js, 3)
        .whileTrue(new RunCommand(
            () -> elevatorSubsystem.testStart(js.getRawAxis(5)/1.5) , 
            elevatorSubsystem));
    
    new JoystickButton(js, 1)
        .whileTrue(new RunCommand(
            () -> elevatorSubsystem.testStop(),
            elevatorSubsystem));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(DriveConstants.kDriveKinematics);

    // An example trajectory to follow. All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        config);

    var thetaController = new ProfiledPIDController(
        AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
        exampleTrajectory,
        m_robotDrive::getPose, // Functional interface to feed supplier
        DriveConstants.kDriveKinematics,

        // Position controllers
        new PIDController(AutoConstants.kPXController, 0, 0),
        new PIDController(AutoConstants.kPYController, 0, 0),
        thetaController,
        m_robotDrive::setModuleStates,
        m_robotDrive);

    // Reset odometry to the starting pose of the trajectory.
    m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false, false));
  }
}
