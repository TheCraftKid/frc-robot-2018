package com.williecubed.frctest

import edu.wpi.first.wpilibj.TimedRobot

/**
 * A robot that uses tank drive.
 */
class Robot : TimedRobot() {

    private lateinit var drive: DifferentialDrive

    private val controller = XboxController(0)
    
    override fun robotInit() {
        println("Hello World from Kotlin!")
        val leftTalon = Talon(0)
        val rightTalon = Talon(1)
        drive = DifferentialDrive(lefttalon, rightTalon)
    }

    override fun teleopPeriodic() {
        val leftY = controller.getY(GenericHID.Hand.kLeft) * 0.5
        val rightY = controller.getY(GenericHID.Hand.kRight) * 0.5
        drive.tankDrive(leftY, rightY)
    }
}
