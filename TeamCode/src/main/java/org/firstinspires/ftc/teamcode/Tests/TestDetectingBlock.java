package org.firstinspires.ftc.teamcode.Tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Helper.AutoBot;

@Autonomous(name = "TestDetectingBlock")
public class TestDetectingBlock extends OpMode {

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
        telemetry.addLine("loop " + count);
        displayArray(robot.countStones());
        if(count == 0) {
            //robot.resetEncoders();
            robot.SkyStonesLocation1();
           //continuousBackward(robot.SkyStonesLocation1());
        }
        else if(count == 1){

        }
        robot.printEncoders();
        telemetry.update();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
    }


    public void continuousBackward(boolean found) {
        if(!found) {
            robot.topLeft.setPower(0.5);
            robot.botLeft.setPower(0.5);
            robot.topRight.setPower(-0.5);
            robot.botRight.setPower(-0.5);
        }
        else{
            robot.topLeft.setPower(0);
            robot.botLeft.setPower(0);
            robot.topRight.setPower(0);
            robot.botRight.setPower(0);
            robot.resetEncoders();
            robot.count++;
        }
    }

    public void displayArray(int[] array){
        String display = "Stones : [ ";
        for(int x: array){
            display = display + x + " ";
        }
        display = display + "]";
        telemetry.addLine(display);
    }

}
