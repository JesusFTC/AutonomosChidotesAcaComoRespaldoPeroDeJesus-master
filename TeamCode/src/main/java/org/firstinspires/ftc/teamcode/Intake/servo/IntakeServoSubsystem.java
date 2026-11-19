package org.firstinspires.ftc.teamcode.Intake.servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

public class IntakeServoSubsystem extends SubsystemBase {
    private ServoEx servoLeft;
    private ServoEx servoRight;
    private ServoExGroup ServoGroup;
    public IntakeServoSubsystem(HardwareMap hwMap){
        servoLeft = new ServoEx(hwMap, "ServoLeft");
        servoRight = new ServoEx(hwMap, "ServoRight");

        servoLeft.setInverted(true);

        ServoGroup = new ServoExGroup(servoLeft, servoRight);
    }
    public void start(){
        ServoGroup.set(1);
    }
    public void stop(){
        ServoGroup.set(0);
    }
    public double getPosition(){
        return servoLeft.getRawPosition();
    }
    public double getPort(){
        return servoLeft.getPortNumber();
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}