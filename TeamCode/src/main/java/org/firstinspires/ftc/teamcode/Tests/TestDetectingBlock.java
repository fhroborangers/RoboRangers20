package org.firstinspires.ftc.teamcode.Tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Helper.AutoBot;

@Autonomous(name = "TestDetectingBlock")
public class TestDetectingBlock extends OpMode {

    AutoBotVu robot;

    @Override
    public void init() {
        telemetry.addLine("init");
        robot = new AutoBotVu(hardwareMap, telemetry);
        //robot.initVu();
        telemetry.update();
    }

    public void init_loop() {
        telemetry.addLine("initLoop");

    }

    @Override
    public void start() {
        telemetry.addLine("start");
        //robot.setUpWheels();
        robot.initVu();
        //robot.setUpLiftMotor();
        telemetry.update();
    }

    @Override
    public void loop() {
        int count = robot.count;
        telemetry.addLine("loop \n Count: " + count);
        robot.loopVuUpdated();
        telemetry.update();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
    }


}
