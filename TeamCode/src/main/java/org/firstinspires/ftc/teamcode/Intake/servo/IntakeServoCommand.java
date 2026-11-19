package org.firstinspires.ftc.teamcode.Intake.servo;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class IntakeServoCommand extends CommandBase {
    private final IntakeServoSubsystem servoSubsystem;
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
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}