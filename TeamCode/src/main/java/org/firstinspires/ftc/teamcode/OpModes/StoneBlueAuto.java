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
        robot.initVuforia();
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
        robot.setUpClawServos();
        telemetry.update();
    }

    @Override
    public void loop() {
        int count = robot.count;
        int backwardEncoder = robot.backwardEncoder;
        telemetry.addLine("loop " + count);

        telemetry.addLine("Should Robot Stop: " + robot.loopVuforia());

        if(count == 0){
            robot.booleanBackward(robot.loopVuforia());
        }
        /*
        if(count == 0) {
            robot.booleanBackward(robot.loopVuforia());
        }
        else if(count == 1) {
           robot.rotateRightDegrees(90);
        }
        else if(count == 2) {
            robot.forwardCM(40);
        }
        else if (count == 3){
            robot.moveLiftUp(500);
        }
        else if(count == 4){
            robot.openClaw();
        }
        else if(count == 5){
            robot.openClawArm();
        }
        else if(count == 6){
            robot.moveLiftDown(500);
        }
        else if(count == 7){
            robot.closeClaw();
        }
        else if(count == 8){
            robot.moveLiftUp(500);
        }
        else if(count == 9) {
            robot.backwardCM(40);
        }
        else if(count == 10){
            robot.rotateLeftDegrees(90);
        }
        else if(count == 11){
            robot.forward(backwardEncoder + 40);
        }
        else if(count == 12){
            robot.openClaw();
        }
        robot.printEncoders();
        telemetry.update();
        */
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
        robot.stopVuforia();
    }

}
