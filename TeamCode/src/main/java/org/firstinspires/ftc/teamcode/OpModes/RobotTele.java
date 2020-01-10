package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Helper.TeleBot;

@TeleOp(name = "RobotTele -- Driving")
public class RobotTele extends OpMode {

    TeleBot robot;

    @Override
    public void init() {
        telemetry.addLine("init");
        robot = new TeleBot(hardwareMap, telemetry);
    }

    public void init_loop() {
        telemetry.addLine("initLoop");

    }


    @Override
    public void start() {
        telemetry.addLine("start");
        robot.setUpWheels();
        //robot.setUpServos();
        //robot.setUpLiftMotor();
    }

    @Override
    public void loop() {
        telemetry.addLine("loop");
        robot.move(gamepad1);
        //robot.move2(gamepad1);
        //robot.moveWithAngle(gamepad1);
        //robot.moveClaw(gamepad2);
        //robot.moveLiftMotor(gamepad2);
        //robot.potatoServo(gamepad2);


    }


    @Override
    public void stop() {
        telemetry.addLine("stop");
    }

}
