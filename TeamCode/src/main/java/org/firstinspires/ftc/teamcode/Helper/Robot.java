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
    public DcMotor liftL,liftR;
    public DcMotor intakeL,intakeR;
    public Servo plate1,plate2, movingClaw, potato;
    public CRServo arm1,arm2;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

    }

}