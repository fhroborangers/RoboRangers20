package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot{

	public final HardwareMap hwm;
    public final Telemetry t;
    public DcMotor topLeft, topRight, botLeft, botRight;
	
	public Robot(HardwareMap hardwareMap, Telemetry tele){
        hwm = hardwareMap;
        t = tele;
    }
	
	public void move(Gamepad gamepad) {
		Power power = new Power(gamepad.left_stick_x,gamepad.left_stick_y,gamepad.right_stick_x);
        topLeft.setPower(power.topLeft);
        topRight.setPower(power.topRight);
        botLeft.setPower(power.botLeft);
        botRight.setPower(power.botRight);
    }

}