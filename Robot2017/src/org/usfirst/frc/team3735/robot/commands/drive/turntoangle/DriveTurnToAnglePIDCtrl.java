package org.usfirst.frc.team3735.robot.commands.drive.turntoangle;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.PIDCtrl;
import org.usfirst.frc.team3735.robot.util.Setting;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnToAnglePIDCtrl extends Command{
	
    private double targetAngle;
    
	private double finishTime = .5;
	private double timeOnTarget = 0;
	
    public static Setting iZone = new Setting("Turning IZone", 10);
    public static Setting actingI = new Setting("Acting I Value", 0.004);
    
	public DriveTurnToAnglePIDCtrl(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.navigation);
    	this.targetAngle = angle;

    }
	
	


    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.navigation.getController().setSetpoint(targetAngle);
    	Robot.navigation.getController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.navigation.getController().setIZone(iZone.getValue());
    	Robot.navigation.getController().updateI(actingI.getValue());
    	
    	if(Robot.navigation.getController().onTarget()){
    		timeOnTarget += .02;
    	}else{
    		timeOnTarget = 0;
    	}
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.navigation.getController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}
