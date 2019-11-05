package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TeleBot extends Robot {

    public TeleBot(HardwareMap hwm, Telemetry tele){
        super(hwm, tele);
    }

    public void setUpWheels() {
        try {
            topLeft = hwm.get(DcMotor.class, "topLeft");
            topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            topLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("topLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("topLeft : ERROR");
        }

        try {
            topRight = hwm.get(DcMotor.class, "topRight");
            topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            topRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("topRight : OK");
        } catch (Exception e) {
            telemetry.addLine("topRight : ERROR");
        }

        try {
            botLeft = hwm.get(DcMotor.class, "botLeft");
            botLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            botLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("botLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("botLeft : ERROR");
        }

        try {
            botRight = hwm.get(DcMotor.class, "botRight");
            botRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            botRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("botRight : OK");
        } catch (Exception e) {
            telemetry.addLine("botRight : ERROR");
        }
    }

    public void setUpLiftMotor(){
        try{
            liftMotor = hwm.get(DcMotor.class, "liftMotor");
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("liftMotor : OK");
        } catch (Exception e) {
            telemetry.addLine("liftMotor : ERROR");
        }
    }

    public void setUpServos()
    {
        try {
            claw = hwm.get(Servo.class, "claw");
            claw.setPosition(0.5);
        } catch (Exception e) {
            telemetry.addLine("claw : ERROR");
        }
        try {
            movingClaw = hwm.get(Servo.class, "movingClaw");
            movingClaw.setPosition(0.5);
        } catch (Exception e) {
            telemetry.addLine("movingClaw : ERROR");
        }
    }

    public void move(Gamepad gamepad) {
        Power power = new Power(gamepad.left_stick_x,gamepad.left_stick_y,gamepad.right_stick_x);
        topLeft.setPower(power.topLeft);
        topRight.setPower(power.topRight);
        botLeft.setPower(power.botLeft);
        botRight.setPower(power.botRight);
    }

    public void move2(Gamepad gamepad){
        if(gamepad.dpad_up){
            topLeft.setPower(-0.3);
            botLeft.setPower(-0.3);
            botRight.setPower(0.3);
            topRight.setPower(0.3);
        }
        else if(gamepad.dpad_down){
            topLeft.setPower(0.3);
            botLeft.setPower(0.3);
            botRight.setPower(-0.3);
            topRight.setPower(-0.3);
        }
        else if(gamepad.dpad_left){
            topLeft.setPower(0.5);
            botLeft.setPower(-0.5);
            botRight.setPower(-0.5);
            topRight.setPower(0.5);
        }
        else if(gamepad.dpad_right){
            topLeft.setPower(-0.5);
            botLeft.setPower(0.5);
            botRight.setPower(0.5);
            topRight.setPower(-0.5);
        }
        else{
            topLeft.setPower(0);
            botLeft.setPower(0);
            botRight.setPower(0);
            topRight.setPower(0);
        }
    }

    public void moveWithAngle(Gamepad gamepad)
    {
        //Getting Direction
        //Possible Values
        // U = Up, D = Down, L = Left, R = Right, N = None/No Movement
        char direction;
        double angle = Math.abs(Math.atan(gamepad.left_stick_y/gamepad.left_stick_x)); //in radians
        telemetry.addLine("Angle in Degrees: " + Math.toDegrees(angle));
        if(angle < Math.PI/4){
            if(gamepad.left_stick_x > 0){
                direction = 'R';
            }
            else{
                direction = 'L';
            }
        }
        else if(angle > Math.PI/4){
            if(gamepad.left_stick_y > 0){
                direction = 'U';
            }
            else{
                direction = 'D';
            }
        }
        else{
            direction = 'N';
        }

        //Using triggers to accelerate


    }

    public void moveClaw(Gamepad gamepad)
    {
        if(gamepad.a){
            claw.setPosition(0);
        }
        else if(gamepad.b){
            claw.setPosition(1);
        }

        if(gamepad.right_bumper){
            movingClaw.setPosition(0);
        }
        else if(gamepad.left_bumper) {
            movingClaw.setPosition(.5);
        }

    }

    public void moveLiftMotor(Gamepad gamepad) {
        if (gamepad.left_trigger > 0 && gamepad.right_trigger == 0) {
            if (gamepad.left_trigger < .3) {
                System.out.println(gamepad.left_trigger);
                liftMotor.setPower(.4);
                telemetry.addLine("up");
            } else if (gamepad.left_trigger < .5) {
                liftMotor.setPower(.6);
                telemetry.addLine("up+");
            } else if (gamepad.left_trigger < .7) {
                liftMotor.setPower(.8);
                telemetry.addLine("up++");
            } else if (gamepad.left_trigger <= 1) {
                liftMotor.setPower(1);
                telemetry.addLine("up max");
            }
        }
        else if(gamepad.left_trigger == 0){
            liftMotor.setPower(0);
            telemetry.addLine("LEFT 0");
        }
        if (gamepad.right_trigger > 0 && gamepad.left_trigger == 0) {
            if (gamepad.right_trigger < .3) {
                liftMotor.setPower(-.4);
                telemetry.addLine("down");
            } else if (gamepad.right_trigger < .5) {
                liftMotor.setPower(-.6);
                telemetry.addLine("down-");
            } else if (gamepad.right_trigger < .7) {
                liftMotor.setPower(-.8);
                telemetry.addLine("down--");
            } else if (gamepad.right_trigger <= 1) {
                liftMotor.setPower(-1);
                telemetry.addLine("down max");
            }
        }
        else{
            liftMotor.setPower(0);
            telemetry.addLine("RIGHT 0");
        }
    }
}