/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.williecubed.frc.team5318;

import com.williecubed.frc.team5318.commands.SquareCommand;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import com.williecubed.frc.team5318.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    public static final double DEFAULT_MULTIPLIER = 0.5;

    public static final double DAMPENER = (double) 1 / 3;

    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    //    public static OI oi;

    private boolean isInArcadeMode = false;

    private Command autonomousCommand;

    private DifferentialDrive robotDrive;
    private Joystick joystickOne;
    private Joystick joystickTwo;

    @Override
    public void robotInit() {
        initCamera();
        Talon leftTalon = new Talon(0);
        Talon rightTalon = new Talon(1);
        robotDrive = new DifferentialDrive(leftTalon, rightTalon);

        joystickOne = new Joystick(1);
        joystickTwo = new Joystick(2);

        autonomousCommand = new SquareCommand(robotDrive);

        Button buttonFive = new JoystickButton(joystickOne, 5);
        buttonFive.whenPressed(autonomousCommand);
    }

    private void initCamera() {
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    public void startCompetition() {
        super.startCompetition();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
//        double multiplier = DEFAULT_MULTIPLIER;
        if (isInArcadeMode) {

        } else {
            double twist = joystickTwo.getTwist();
            double leftThrottleValue = joystickOne.getRawAxis(2) + 1;
            double leftMultiplier = sigmoid(leftThrottleValue * DAMPENER);
            double rightThrottleValue = joystickTwo.getRawAxis(3) + 1;
            double rightMultiplier = sigmoid(rightThrottleValue * DAMPENER);
            System.out.println("Multiplier value: " + leftMultiplier);
            robotDrive.tankDrive(leftMultiplier * joystickOne.getY(),
                    rightMultiplier * joystickTwo.getY());
        }
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
        autonomousCommand.start();
    }

    public static double sigmoid(double beta) {
        return 1.0 / (1.0 + Math.exp(-beta));
    }

// Here be dragons

//    @Override
//    public void startCompetition() {
//
//    }

//    /**
//     * This function is run when the robot is first started up and should be
//     * used for any initialization code.
//     */
//    @Override
//    public void robotInit() {
//        oi = new OI();
//        chooser.addDefault("Default Auto", new SquareCommand());
//        // chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);
//    }
//
//    /**
//     * This function is called once each time the robot enters Disabled mode.
//     * You can use it to reset any subsystem information you want to clear when
//     * the robot is disabled.
//     */
//    @Override
//    public void disabledInit() {
//
//    }
//
//
//
//    @Override
//    public void disabledPeriodic() {
//        Scheduler.getInstance().run();
//    }
//
//    /**
//     * This autonomous (along with the chooser code above) shows how to select
//     * between different autonomous modes using the dashboard. The sendable
//     * chooser code works with the Java SmartDashboard. If you prefer the
//     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
//     * getString code to get the auto name from the text box below the Gyro
//     *
//     * <p>You can add additional auto modes by adding additional commands to the
//     * chooser code above (like the commented example) or additional comparisons
//     * to the switch structure below with additional strings & commands.
//     */
//    @Override
//    public void autonomousInit() {
//        autonomousCommand = chooser.getSelected();
//
//        /*
//         * String autoSelected = SmartDashboard.getString("Auto Selector",
//         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
//         * = new MyAutoCommand(); break; case "Default Auto": default:
//         * autonomousCommand = new SquareCommand(); break; }
//         */
//
//        // schedule the autonomous command (example)
//        if (autonomousCommand != null) {
//            autonomousCommand.start();
//        }
//    }
//
//    /**
//     * This function is called periodically during autonomous.
//     */
//    @Override
//    public void autonomousPeriodic() {
//        Scheduler.getInstance().run();
//    }
//
//
//    /**
//     * This function is called periodically during operator control.
//     */
//    @Override
//    public void teleopPeriodic() {
//        Scheduler.getInstance().run();
//    }
//
//    /**
//     * This function is called periodically during test mode.
//     */
//    @Override
//    public void testPeriodic() {
//
//    }
}
