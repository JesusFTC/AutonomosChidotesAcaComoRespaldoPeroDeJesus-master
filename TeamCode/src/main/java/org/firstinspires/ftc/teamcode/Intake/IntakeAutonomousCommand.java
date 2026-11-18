package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;

public class IntakeAutonomousCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    ElapsedTime timer;
    ElapsedTime reversetimer;

    public IntakeAutonomousCommand(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        timer = new ElapsedTime();
        reversetimer = new ElapsedTime();
        addRequirements(intakeSubsystem,shooterSubsystem);
    }


    @Override
    public void initialize() {
        reversetimer.reset();
        timer.reset();
    }

    @Override
    public void execute(){
        if (timer.milliseconds() < 3000) {
            intakeSubsystem.start();
            shooterSubsystem.disablePID();
            shooterSubsystem.setManualPower(-0.3);
        }
        else {
            if (reversetimer.milliseconds() < 100){
                intakeSubsystem.reverse();
                //reversetimer.reset();
            }
            else {
                intakeSubsystem.stop();
                shooterSubsystem.disablePID();
                shooterSubsystem.setManualPower(0);
                intakeSubsystem.stop();
                //timer.reset();
            }
        }
    }
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}