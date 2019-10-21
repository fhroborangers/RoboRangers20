package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.AutoBot;
import org.firstinspires.ftc.teamcode.Helper.TeleBot;

@Autonomous(name = "StoneBlue -- Auto")
public class StoneBlueAuto extends OpMode {

    AutoBot robot;

    @Override
    public void init() {
        telemetry.addLine("init");
        robot = new AutoBot(hardwareMap, telemetry);
        telemetry.update();
    }

    public void init_loop() {
        telemetry.addLine("initLoop");

    }


    @Override
    public void start() {
        telemetry.addLine("start");
        robot.setUpWheels();
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addLine("loop");
        //[robot.getVoltage();
        robot.resetEncoders();
        //robot.forward(4000);
        robot.printEncoders();
        telemetry.update();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
    }

}
