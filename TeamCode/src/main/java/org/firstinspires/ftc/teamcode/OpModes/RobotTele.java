package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Helper.TeleBot;

@TeleOp(name = "RobotTele -- Driving")
public class RobotTele extends OpMode {

    TeleBot robot;

    @Override
    public void init() {
        telemetry.addLine("init");
        robot = new TeleBot(hardwareMap, telemetry);
    }

    public void init_loop() {
        telemetry.addLine("initLoop");

    }


    @Override
    public void start() {
        telemetry.addLine("start");
        robot.setUpWheels();
    }

    @Override
    public void loop() {
        telemetry.addLine("loop");
        robot.getVoltage();
        robot.move(gamepad1);
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
    }

}
