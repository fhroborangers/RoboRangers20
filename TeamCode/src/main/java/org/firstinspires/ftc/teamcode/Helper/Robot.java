package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot{

	public final HardwareMap hwm;
    public final Telemetry t;
    public DcMotor topLeft, topRight, botLeft, botRight;
    private VoltageSensor voltageSensor;
	
	public Robot(HardwareMap hardwareMap, Telemetry tele){
        hwm = hardwareMap;
        t = tele;
    }

    public void getVoltage(){
	    try {
            voltageSensor = hwm.get(VoltageSensor.class, "voltageSensor");
            double voltage = voltageSensor.getVoltage();
            if (voltage < 5.0) {
                t.addLine("ROBOT BATTERY IS LOW");
            }
        }
	    catch(Exception e){
            t.addLine("Voltage Sensor: ERROR");
	    }
    }
	
	public void move(Gamepad gamepad) {
		Power power = new Power(gamepad.left_stick_x,gamepad.left_stick_y,gamepad.right_stick_x);
        topLeft.setPower(power.topLeft);
        topRight.setPower(power.topRight);
        botLeft.setPower(power.botLeft);
        botRight.setPower(power.botRight);
    }

}