package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Robot {

    public final HardwareMap hardwareMap;
    public final Telemetry telemetry;
    public DcMotor topLeft, topRight, botLeft, botRight;
    public DcMotor liftMotor1,liftMotor2;
    public Servo claw1,claw2, movingClaw, potato;
    public CRServo arm;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

    }

}