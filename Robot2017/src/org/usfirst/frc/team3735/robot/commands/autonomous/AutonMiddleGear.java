package org.usfirst.frc.team3735.robot.commands.autonomous;

import org.usfirst.frc.team3735.robot.commands.drive.Move;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveMoveDistanceExpNavx;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveBrake;
import org.usfirst.frc.team3735.robot.commands.sequences.GearIntakeDropOff;
import org.usfirst.frc.team3735.robot.triggers.Bumped;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonMiddleGear extends CommandGroup {

    public AutonMiddleGear(){
    	addSequential(new Move(75).addT(new Bumped()));
    	addSequential(new GearIntakeDropOff(),4);
     }
}
