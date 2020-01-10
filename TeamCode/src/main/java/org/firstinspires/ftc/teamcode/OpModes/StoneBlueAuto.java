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
            robot.strafeRightUntilSkystone(robot.loopTensor());
        }
        else if(count == 1) {
            robot.resetEncoders();
        }
        else if(count == 2){
            robot.forwardCM(50);
        }


        /*if(count == 0){
            robot.forwardCM(51);
        }

        else if(count == 1){
            //robot.rotateLeftCos(810); //1100 ticks for 90 degrees
            robot.rotateLeft(2200);
        }
        else if(count==2)
        {
            robot.backwardCM(15);
        }
        else if(count==3)
        {
            robot.rotateLeft(2200);
        }
        else if(count==4)
            robot.backwardCM(30);

        /*
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
robot.printEncoders();
         */

    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
        robot.stopTensor();
    }

}
