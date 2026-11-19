package org.firstinspires.ftc.teamcode.Intake.servo;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class IntakeServoCommand extends CommandBase {
    private IntakeServoSubsystem servoSubsystem;
    GamepadEx gamepadEx;
    public IntakeServoCommand(IntakeServoSubsystem servoSubsystem, GamepadEx gamepadEx){
        this.servoSubsystem = servoSubsystem;
        this.gamepadEx = gamepadEx;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        servoSubsystem.start();
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        servoSubsystem.stop();
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}