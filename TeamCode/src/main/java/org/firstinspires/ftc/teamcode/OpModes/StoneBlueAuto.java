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
        //robot.initTensor();
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
        robot.resetEncoders();
        telemetry.update();
    }
    @Override
    public void loop() {
       int count =  robot.count;
        int backwardEncoder = robot.backwardEncoder;
        telemetry.addLine("loop " + count);
        //robot.loopTensor();
        if(count == 0){
            robot.openClaw();
            robot.moveLiftUp(2800);
        }
        else if(count == 1){
           // robot.openClaw();
            //robot.openClawArm();
        }
        /*
        else if(count == 2){
            robot.forwardCM(61);
            //robot.rotateLeft(830); 90 degrees
        }
        else if(count ==3){
            robot.moveLiftDown(1000);
        }
        else if(count==4){
            robot.closeClaw();
        }
        else if(count==5){
            robot.moveLiftUp(1000);
        }
        else if(count==6){
            robot.backwardCM(30);
        }
        else if(count==7){
            robot.movingClawRight();
        }
        else if(count==8){
            robot.moveLiftDown(1000);
        }
        else if(count==9){
            robot.openClaw();
        }
        else if(count==10){
            robot.moveLiftUp(1000);
        }
        else if(count==11){
            robot.forwardCM(6);
        }
        else if(count==12){
            robot.moveLiftDown(1000);
        }
        else if(count==13){
            robot.rotateLeft(830);
        }
        else if(count==14){
            robot.forwardCM(20);
        }

         */
        robot.printEncoders();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
        //robot.stopTensor();
    }

}
