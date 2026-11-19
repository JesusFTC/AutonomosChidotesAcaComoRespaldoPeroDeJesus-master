package org.firstinspires.ftc.teamcode.Intake.servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

public class IntakeServoSubsystem extends SubsystemBase {
    private ServoEx servoLeft;
    private ServoEx servoRight;
    private ServoExGroup servoGroup;
    public IntakeServoSubsystem(HardwareMap hwMap){

        servoLeft = new ServoEx(hwMap, "ServoLeft");
        servoRight = new ServoEx(hwMap, "ServoRight");

        servoLeft.setInverted(true);
        servoGroup = new ServoExGroup(servoLeft, servoRight);
    }
    public void start(){
        servoGroup.set(1);
    }
    public void stop(){
        servoGroup.set(0);
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}