package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Helper.AutoBot;


@Autonomous(name = "StoneBlueAuto")
public class StoneBlueAuto extends OpMode {

    AutoBot robot;

    @Override
    public void init() {
        telemetry.addLine("init");
        robot = new AutoBot(hardwareMap, telemetry);
        robot.initTensor();
        telemetry.update();


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
        telemetry.addLine("end of start");
        telemetry.update();
    }
    @Override
    public void loop() {
        int count =  robot.count;
        int backwardEncoder = robot.backwardEncoder;
        telemetry.addLine("loop " + count);
        if(count == 0) {
            robot.strafeUntilSkystoneVadim(robot.loopTensorVadim());
        }
        else if(count == 1) {
            robot.autoCorrect(robot.loopTensorVadim());
        }
        else if(count==2) {
            robot.forwardCM(61/2);
        }
        else if(count==3) {
            robot.rotateLeftDegrees(90);
        }
        else if(count==4) {
            robot.forwardCM(36); //36 = robotLen/2 + blockLen/2 + 1
        }
        else if(count==5) {
            robot.strafeRightCM(61/2*3);
        }
        else if(count==6) {
            robot.turnOnIntake();
        }
        else if(count==7) {
            robot.backwardCM(10);
        }
        else if(count==8) {
            robot.turnOffIntake();
        }
        else if(count==9) {
            robot.strafeLeftCM(61/2);
        }
        else if(count==10) {
            robot.forwardCM(0);
        }

        /*
        else if(count == 2){
            robot.forwardCos(1000);
        }*/

        telemetry.addLine("count: "+count);
        robot.printEncoders();
    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
        robot.stopTensor();
    }

}
