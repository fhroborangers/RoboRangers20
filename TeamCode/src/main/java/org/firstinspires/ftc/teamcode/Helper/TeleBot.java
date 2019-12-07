package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.ExecutionException;

public class TeleBot extends Robot {

    public TeleBot(HardwareMap hardwareMap, Telemetry tele){
        super(hardwareMap, tele);
    }

    public void setUpWheels() {
        try {
            topLeft = hardwareMap.get(DcMotor.class, "topLeft");
            topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            topLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("topLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("topLeft : ERROR");
        }

        try {
            topRight = hardwareMap.get(DcMotor.class, "topRight");
            topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            topRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("topRight : OK");
        } catch (Exception e) {
            telemetry.addLine("topRight : ERROR");
        }

        try {
            botLeft = hardwareMap.get(DcMotor.class, "botLeft");
            botLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            botLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("botLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("botLeft : ERROR");
        }

        try {
            botRight = hardwareMap.get(DcMotor.class, "botRight");
            botRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            botRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("botRight : OK");
        } catch (Exception e) {
            telemetry.addLine("botRight : ERROR");
        }
    }

    public void setUpLiftMotor(){
        try{
            liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("liftMotor : OK");
        } catch (Exception e) {
            telemetry.addLine("liftMotor : ERROR");
        }
    }

    public void setUpServos() {
        try {
            claw = hardwareMap.get(Servo.class, "claw");

        } catch (Exception e) {
            telemetry.addLine("claw : ERROR");
        }
        try {
            movingClaw = hardwareMap.get(Servo.class, "movingClaw");
            movingClaw.setPosition(.3);

        } catch (Exception e) {
            telemetry.addLine("movingClaw : ERROR");
        }

        try{
            potato = hardwareMap.get(Servo.class, "potato");
            potato.setDirection(Servo.Direction.REVERSE);
            potato.setPosition(0);
        }
        catch(Exception e ){
            telemetry.addLine("potato : error");
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
            //topLeft.setPower(0);
            botLeft.setPower(0.8);
            botRight.setPower(0.8);
            //topRight.setPower(0);
        }
        else if(gamepad.dpad_down){
            topLeft.setPower(0);
            botLeft.setPower(-0.8);
            botRight.setPower(-0.8);
            topRight.setPower(0);
        }
        else if(gamepad.dpad_left){
            //topLeft.setPower(0.8);
            //botLeft.setPower(-0.8);
            //botRight.setPower(-0.8);
            //topRight.setPower(0.8);
        }
        else if(gamepad.dpad_right){
            topLeft.setPower(-0.8);
            botLeft.setPower(0.8);
            botRight.setPower(0.8);
            topRight.setPower(-0.8);
        }
        else{
            topLeft.setPower(0);
            botLeft.setPower(0);
            botRight.setPower(0);
            topRight.setPower(0);
        }
    }

    public void moveWithAngle(Gamepad gamepad) {
        //Getting Direction
        //Possible Values
        // U = Up, D = Down, L = Left, R = Right, N = None/No Movement, r = rotate right, l = rotate left
        char direction;
        if(gamepad.left_stick_y > 0){
            direction = 'u';
        }
        else if(gamepad.left_stick_y < 0){
            direction = 'd';
        }
        else if(gamepad.right_stick_x>0) //rotate right
        {
            direction = 'r';
        }
        else if(gamepad.right_stick_x<0) //rotate left
        {
            direction = 'l';
        }
        else {
            direction = 'n';
        }


        if(direction == 'u'){
            topLeft.setPower(gamepad.right_trigger);
            botLeft.setPower(gamepad.right_trigger);
            botRight.setPower(-gamepad.right_trigger);
            topRight.setPower(-gamepad.right_trigger);
        }
        else if(direction == 'd'){
            topLeft.setPower(-gamepad.right_trigger);
            botLeft.setPower(-gamepad.right_trigger);
            botRight.setPower(gamepad.right_trigger);
            topRight.setPower(gamepad.right_trigger);
        }
        else if(direction=='r') {
            topLeft.setPower(-gamepad.right_trigger);
            botLeft.setPower(-gamepad.right_trigger);
            botRight.setPower(-gamepad.right_trigger);
            topRight.setPower(-gamepad.right_trigger);
        }
        else if(direction=='l') {
            topLeft.setPower(gamepad.right_trigger);
            botLeft.setPower(gamepad.right_trigger);
            botRight.setPower(gamepad.right_trigger);
            topRight.setPower(gamepad.right_trigger);
        }
        else if(direction == 'n') {
            topLeft.setPower(0);
            botLeft.setPower(0);
            botRight.setPower(0);
            topRight.setPower(0);
        }

        //Using triggers to accelerate


    }

    public void moveClaw(Gamepad gamepad) {
        if(gamepad.a){
            claw.setPosition(0);
        }
        else if(gamepad.x){
            claw.setPosition(1);
        }
        if(gamepad.left_bumper){
            movingClaw.setPosition(.3);
        }
        else if(gamepad.right_bumper) {
            movingClaw.setPosition(.85);
        }
        if(gamepad.dpad_up){
            movingClaw.setPosition(.85);
            claw.setPosition(0);
        }
        if(gamepad.dpad_down){
            movingClaw.setPosition(.3);
            claw.setPosition(1);
        }

    }

    public void potatoServo(Gamepad gamepad) {
        if (gamepad.y)
        {

            potato.setPosition(1);

        }
        if(gamepad.b){
            potato.setPosition(0);
        }
    }

    public void moveLiftMotor(Gamepad gamepad) {

        if (gamepad.left_trigger != 0 && gamepad.right_trigger == 0) {
            liftMotor.setPower(-gamepad.left_trigger);
        }
        else if (gamepad.right_trigger != 0 && gamepad.left_trigger == 0) {
            liftMotor.setPower(gamepad.right_trigger);
        }
        else if(gamepad.right_trigger == 0 && gamepad.left_trigger == 0){
            liftMotor.setPower(0);
            telemetry.addLine("RIGHT 0");
        }





    }

}