package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Helper.AutoBot;

@Autonomous(name = "SafetyAutoForwardLeft")
public class SafetyAutoForwardLeft extends OpMode {

    AutoBot robot;

    @Override
    public void init() {
        telemetry.addLine("init");
        robot = new AutoBot(hardwareMap, telemetry);
        robot.initTensor();
        telemetry.update();
    }

    @Override
    public void start() {
        telemetry.addLine("start");
        robot.setUpWheels();
        robot.resetEncoders();
        telemetry.update();
    }

    @Override
    public void loop() {
        int count =  robot.count;
        int backwardEncoder = robot.backwardEncoder;
        telemetry.addLine("loop " + count);
        if(count==0) {
            robot.forwardCM(61);
        }
        else if(count==1) {
            robot.strafeLeftCM(61);
        }
    }

    @Override
    public void stop() {
        telemetry.addLine("stop");
        robot.stopTensor();
    }
}
