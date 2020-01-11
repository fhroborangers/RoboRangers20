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
        robot.initTensor();

    }

    public void init_loop() {
        telemetry.addLine("initLoop");

    }

    @Override
    public void start() {
        telemetry.addLine("start");
        robot.setUpWheels();
        //robot.setUpLiftMotor();
        //robot.setUpClawServos();
        robot.resetEncoders();
        telemetry.update();
    }
    @Override
    public void loop() {
        int count =  robot.count;
        int backwardEncoder = robot.backwardEncoder;
        telemetry.addLine("loop " + count);
        if(count == 0) {
            robot.strafeUntilSkystone(robot.loopTensor());
        }
        else if(count == 1){
            robot.forwardCM(50);
        }
        robot.printEncoders();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
        robot.stopTensor();
    }

}
