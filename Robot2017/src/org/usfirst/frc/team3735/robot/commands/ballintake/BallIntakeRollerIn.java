package org.usfirst.frc.team3735.robot.commands.ballintake;

import org.usfirst.frc.team3735.robot.Constants;
import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BallIntakeRollerIn extends Command {

	private double target;
	private String key = "Ball Intake Speed";
    public BallIntakeRollerIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballIntake);
    	target = Constants.BallIntake.rollerInSpeed;
    	SmartDashboard.putNumber(key, Constants.BallIntake.rollerInSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ballIntake.setRollerCurrent(Constants.BallIntake.rollerInSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	target = SmartDashboard.getNumber(key, Constants.BallIntake.rollerInSpeed);
    	Robot.ballIntake.setRollerCurrent(target);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ballIntake.setRollerCurrent(0);
    }
}
