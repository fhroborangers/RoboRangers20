package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.ExecutionException;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

public class TeleBot extends Robot {

    public TeleBot(HardwareMap hardwareMap, Telemetry tele){
        super(hardwareMap, tele);
    }

    public void setUpWheels() {
        try {
            topLeft = hardwareMap.get(DcMotor.class, "topLeft");
            topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("topLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("topLeft : ERROR");
        }

        try {
            topRight = hardwareMap.get(DcMotor.class, "topRight");
            topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("topRight : OK");
        } catch (Exception e) {
            telemetry.addLine("topRight : ERROR");
        }

        try {
            botLeft = hardwareMap.get(DcMotor.class, "botLeft");
            botLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            botLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("botLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("botLeft : ERROR");
        }

        try {
            botRight = hardwareMap.get(DcMotor.class, "botRight");
            botRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            botRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("botRight : OK");
        } catch (Exception e) {
            telemetry.addLine("botRight : ERROR");
        }
    }

    public void setUpLiftMotors(){
        try{
            liftL = hardwareMap.get(DcMotor.class, "liftL");
            liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            liftL.setDirection(FORWARD);
            liftR = hardwareMap.get(DcMotor.class, "liftR");
            liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            liftR.setDirection(REVERSE);
            telemetry.addLine("liftMotor : OK");
        } catch (Exception e) {
            telemetry.addLine("liftMotor : ERROR");
        }
    }

    public void setUpIntakeMotors(){
        try{
            intakeL = hardwareMap.get(DcMotor.class, "intakeL");
            intakeL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            intakeL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            intakeR = hardwareMap.get(DcMotor.class, "intakeR");
            intakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            intakeR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addLine("intake : OK");
        } catch(Exception e) {
            telemetry.addLine("intake : ERROR");
        }
    }

    public void setUpServos() {

        try {
            plate1 = hardwareMap.get(Servo.class, "plate1");
            plate1.setPosition(0);
        } catch (Exception e) {
            telemetry.addLine("plate1 : ERROR");
        }
        try{
            plate2=hardwareMap.get(Servo.class,"plate2");
            plate2.setPosition(0);
        }catch(Exception e){
            telemetry.addLine("plate2 : error");
        }



    }
        public void setUpArm(){

        try{
            arm1 = hardwareMap.get(CRServo.class,"armR");
            arm1.setDirection(FORWARD);
            arm2 = hardwareMap.get(CRServo.class,"armL");
            arm2.setDirection(DcMotorSimple.Direction.REVERSE);

        }
        catch(Exception e){
            telemetry.addLine("arm : error");
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
            topLeft.setPower(0);
            botLeft.setPower(0.8);
            botRight.setPower(0.8);
            topRight.setPower(0);
        }
        else if(gamepad.dpad_down){
            topLeft.setPower(0);
            botLeft.setPower(-0.8);
            botRight.setPower(-0.8);
            topRight.setPower(0);
        }
        else if(gamepad.dpad_left){
            topLeft.setPower(0.8);
            botLeft.setPower(-0.8);
            botRight.setPower(-0.8);
            topRight.setPower(0.8);
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

    public void moveBothClaws(Gamepad gamepad) {
        if(gamepad.a){
            plate1.setPosition(1);
            plate2.setPosition(0);
        }
        else if(gamepad.x){
            plate1.setPosition(0);
            plate2.setPosition(1);
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
            liftR.setPower(gamepad.left_trigger);
            liftL.setPower(gamepad.left_trigger);
        }
        else if (gamepad.right_trigger != 0 && gamepad.left_trigger == 0) {
            liftR.setPower(-gamepad.right_trigger);
            liftL.setPower(-gamepad.right_trigger);
        }
        else if(gamepad.right_trigger == 0 && gamepad.left_trigger == 0){
            liftR.setPower(0);
            liftL.setPower(0);
            telemetry.addLine("RIGHT 0");
        }





    }
    public void moveArm(Gamepad gamepad){
        if(gamepad.right_bumper&& !gamepad.left_bumper) {
            arm1.setPower(1);
            arm2.setPower(1);
        }
        else if(gamepad.left_bumper&&!gamepad.right_bumper) {
            arm1.setPower(-1);
            arm2.setPower(-1);
        }
        else {
            arm1.setPower(0);
            arm2.setPower(0);
        }


    }

    public void moveIntake(Gamepad gamepad){
        if(gamepad.y){
            intakeL.setPower(-.5);
            intakeR.setPower(.5);
        }
        else if(gamepad.b){
            intakeL.setPower(0);
            intakeR.setPower(0);
        }
    }

}