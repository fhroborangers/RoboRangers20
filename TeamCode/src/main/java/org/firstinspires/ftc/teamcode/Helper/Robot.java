package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot{

	public final HardwareMap hwm;
    public final Telemetry telemetry;
    public DcMotor topLeft, topRight, botLeft, botRight;
    private VoltageSensor voltageSensor;
	
	public Robot(HardwareMap hardwareMap, Telemetry tele){
        hwm = hardwareMap;
        telemetry = tele;
    }

    public void getVoltage(){
	    try {
            voltageSensor = hwm.get(VoltageSensor.class, "voltageSensor");
            double voltage = voltageSensor.getVoltage();
            if (voltage < 5.0) {
                telemetry.addLine("ROBOT BATTERY IS LOW");
            }
        }
	    catch(Exception e){
            telemetry.addLine("Voltage Sensor: ERROR");
	    }
    }

}