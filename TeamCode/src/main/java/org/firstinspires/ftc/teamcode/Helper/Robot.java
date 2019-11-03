package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {

    public final HardwareMap hwm;
    public final Telemetry telemetry;
    public DcMotor topLeft, topRight, botLeft, botRight;
    public DcMotor liftMotor;
    public Servo claw, movingClaw;

    public Robot(HardwareMap hardwareMap, Telemetry tele) {
        hwm = hardwareMap;
        telemetry = tele;
    }

}