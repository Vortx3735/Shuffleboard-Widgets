package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.pipelines.GearPipeline;
import org.usfirst.frc.team3735.robot.pipelines.PegPipelineLSNTest4;
import org.usfirst.frc.team3735.robot.pipelines.PegPipelineLSNTest5;
import org.usfirst.frc.team3735.robot.subsystems.Vision.Pipes;
import org.usfirst.frc.team3735.robot.util.ContoursOutputPipeline;
import org.usfirst.frc.team3735.robot.util.Setting;
import org.usfirst.frc.team3735.robot.util.VisionHandler;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *
 */
public class Vision extends Subsystem {


	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	public static Setting dpp = new Setting("Vision Degrees per Pixel", 0.13125);
	
	private UsbCamera camera1;
	private UsbCamera camera2;
	
	
	VisionHandler pegs;
	VisionHandler gears;
	VisionHandler mainHandler;
	
	public Vision(){
		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		
		camera1.setFPS(16);
		//camera2.setFPS(16);
		
	    camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    camera2.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    
	    pegs = new VisionHandler(new PegPipelineLSNTest5(), camera1, 2, "GRIP/PegTracker");
	    pegs.startThread();
	    
	    gears = new VisionHandler(new GearPipeline(), camera1, 1, "GRIP/GearTracker");
	    gears.startThread();
	    
	    mainHandler = pegs;
	    
	    
	}

	public enum Pipes{
		Gear,
		Peg
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){
		//SmartDashboard.putNumber("CenterX", getCenterX());
		//SmartDashboard.putNumber("CenterY", getCenterY());
//		SmartDashboard.putNumber("Relative CX", getRelativeCX());
//		SmartDashboard.putNumber("height", mainHandler.getHeight());
		SmartDashboard.putNumber("width", mainHandler.getWidth());
		//SmartDashboard.putNumber("area", mainHandler.getArea());
		pegs.publishTarget();
		gears.publishTarget();
		
    }

    public double getRelativeCX(){
    	return mainHandler.getCenterX() - IMG_WIDTH/2;
    }
    public double getWidth(){
    	return mainHandler.getWidth();
    }


	
	public VisionHandler getHandler(Pipes p){
		switch(p){
			case Peg:
				return pegs;
			case Gear:
				return gears;
			default:
				return pegs;
		}
	}
	
	public void setMainHandler(Pipes p){
		mainHandler = getHandler(p);
	}
	
	public void pause(){
		mainHandler.pause();
	}

	public void resume(){
		mainHandler.resume();
	}
    
    


    
    
}


