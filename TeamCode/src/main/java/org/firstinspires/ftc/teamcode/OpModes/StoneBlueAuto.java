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
        robot.initVu();
        telemetry.update();
    }

    public void init_loop() {
        telemetry.addLine("initLoop");

    }

    @Override
    public void start() {
        telemetry.addLine("start");
        robot.setUpWheels();
        robot.setUpLiftMotor();
        telemetry.update();
    }

    @Override
    public void loop() {
        int count = robot.count;
        telemetry.addLine("loop "+count);
        if(count == 0) {
            robot.loopVu();
            robot.resetEncoders();
            robot.count++;
        }
        else if(count == 1) {
            robot.forwardCM(100);
            robot.resetEncoders();
        }
        else if(count == 2) {
            //robot.backwardCM(100);
        }
        robot.printEncoders();
        telemetry.update();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
    }

}
