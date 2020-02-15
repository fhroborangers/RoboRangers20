package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Helper.AutoBot;

@Autonomous(name = "SafetyAutoForward")
public class SafetyAutoForward extends OpMode {

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
        robot.resetEncoders();
        telemetry.addLine("end of start");
        telemetry.update();
    }

    @Override
    public void loop() {
        int count =  robot.count;
        telemetry.addLine("loop " + count);
        if(count == 0){
            robot.forwardCM(61/2);
        }
    }

    @Override
    public void stop(){
        telemetry.addLine("stop");
    }
}
