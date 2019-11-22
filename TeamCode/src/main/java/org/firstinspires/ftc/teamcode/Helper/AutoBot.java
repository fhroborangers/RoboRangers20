package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class AutoBot extends Robot{

    //private static final String VUFORIA_KEY =
    //        "ATMeJeb/////AAAAGaZ47DzTRUyOhcXnfJD+z89ATBWAF+fi+oOutLvXaf0YT/RPuf2mu6VJsJowCDiWiOzGMHUjXKsLBqA4Ziar76oZY/juheUactiQaY6Z3qPHnGmchAMlYuqgKErvggTuqmFca8VvTjtB6YOheJmAbllTDTaCudODpnIDkuFNTa36WCTr4L8HcCnIsB7bjF8pZoivYEBwPkfCVtfAiEpqxbyDAZgNXnuCyp6v/oi3FYZbp7JkFVorcM182+q0PVN5gIr14SKEMlDcDFDiv/sQwNeQOs5iNBd1OSkCoTe9CYbdmtE0gUXxKN2w9NqwATYC6YRJP9uoopxqmr9zkepI10peh2/RnU6pHOmR0KKRAVh8";
    public int count = 0;
    public int backwardEncoder = 0;

    public AutoBot(HardwareMap hardwareMap, Telemetry tele) {
        super(hardwareMap, tele);
    }

    //SetUp Methods
    public void setUpWheels() {
        try {
            topLeft = hwm.get(DcMotor.class, "topLeft");
            topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("topLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("topLeft : ERROR");
        }

        try {
            topRight = hwm.get(DcMotor.class, "topRight");
            topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("topRight : OK");
        } catch (Exception e) {
            telemetry.addLine("topRight : ERROR");
        }

        try {
            botLeft = hwm.get(DcMotor.class, "botLeft");
            botLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("botLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("botLeft : ERROR");
        }

        try {
            botRight = hwm.get(DcMotor.class, "botRight");
            botRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("botRight : OK");
        } catch (Exception e) {
            telemetry.addLine("botRight : ERROR");
        }
    }

    public void setUpLiftMotor() {
        try {
            liftMotor = hwm.get(DcMotor.class, "liftMotor");
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("liftMotor : OK");
        } catch (Exception e) {
            telemetry.addLine("liftMotor : ERROR");
        }
    }

    public void setUpClawServos(){
        try {
            claw = hwm.get(Servo.class, "claw");

        } catch (Exception e) {
            telemetry.addLine("claw : ERROR");
        }
        try {
            movingClaw = hwm.get(Servo.class, "movingClaw");

        } catch (Exception e) {
            telemetry.addLine("movingClaw : ERROR");
        }

        try{
            potato = hwm.get(Servo.class, "potato");
            potato.setDirection(Servo.Direction.REVERSE);
            potato.setPosition(0);
        }
        catch(Exception e ){
            telemetry.addLine("potato : error");
        }

    }

    //Mechanical Movement Methods
    public void forward(int ticks){
        telemetry.addLine(""+(Math.abs(topLeft.getCurrentPosition()) < ticks));
        if(Math.abs(topLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(-.60);
            botLeft.setPower(-.60);
            topRight.setPower(.60);
            botRight.setPower(.60);
        }
        else{
            telemetry.addLine("here lmao");
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void backward(int ticks) {
        telemetry.addLine(""+(Math.abs(topLeft.getCurrentPosition()) < ticks));
        if(Math.abs(topLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(.60);
            botLeft.setPower(.60);
            topRight.setPower(-.60);
            botRight.setPower(-.60);
        }
        else {
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void rotateLeft(int ticks){
        if(Math.abs(topLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(1.00);
            botLeft.setPower(1.00);
            topRight.setPower(1.00);
            botRight.setPower(1.00);
        }
        else {
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void rotateRight(int ticks){
        if(Math.abs(topLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(-1.00);
            botLeft.setPower(-1.00);
            topRight.setPower(-1.00);
            botRight.setPower(-1.00);
        }
        else {
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void forwardCM(int cm){
        double ticksPerCM = 730 /(10.16*Math.PI);
        int ticks = (int)ticksPerCM * cm;
        forward(ticks);
    }

    public void backwardCM(int cm){
        double ticksPerCM = 730 /(10.16*Math.PI);
        int ticks = (int)ticksPerCM * cm;
        backward(ticks);
    }

    public void rotateLeftDegrees(int degrees){
        //THIS IS AN UNTESTED, RANDOM CONVERSION, PLEASE CHANGE THIS GOD PLEASE
        //temporary bootleg conversion: 1 degree = 10 ticks
        rotateLeft(degrees*10);
    }

    public void rotateRightDegrees(int degrees){
        //same dealio with rotateLeftDegrees
        rotateRight(degrees*10);
    }

    public void strafeLeft(int ticks) {
        if(topLeft.getCurrentPosition() < ticks) {
            topLeft.setPower(1.00);
            botLeft.setPower(-1.00);
            topRight.setPower(1.00);
            botRight.setPower(-1.00);
        }
        else {
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void strafeRight(int ticks) {
        if(topLeft.getCurrentPosition() > -ticks) {
            topLeft.setPower(-1.00);
            botLeft.setPower(1.00);
            topRight.setPower(-1.00);
            botRight.setPower(1.00);
        }
        else {
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void booleanForward(boolean detected){
        if (!detected) {
            topLeft.setPower(-.60);
            botLeft.setPower(-.60);
            topRight.setPower(.60);
            botRight.setPower(.60);
        }
        else{
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void booleanBackward(boolean detected){
        if (!detected) {
            topLeft.setPower(.60);
            botLeft.setPower(.60);
            topRight.setPower(-.60);
            botRight.setPower(-.60);
        }
        else{
            topLeft.setPower(0);
            botLeft.setPower(0);
            topRight.setPower(0);
            botRight.setPower(0);
            backwardEncoder = topLeft.getCurrentPosition();
            resetEncoders();
            count++;
        }
    }

    public void moveLiftUp(int encoder){
        if(Math.abs(liftMotor.getCurrentPosition()) < encoder){
            liftMotor.setPower(0.5);
        }
        else{
            liftMotor.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void moveLiftDown(int encoder){
        if(Math.abs(liftMotor.getCurrentPosition()) < encoder){
            liftMotor.setPower(-0.5);
        }
        else{
            liftMotor.setPower(0);
            resetEncoders();
            count++;
        }
    }

    public void openClaw(){
        claw.setPosition(1);
    }

    public void closeClaw(){
        claw.setPosition(0);
    }

    public void openClawArm(){
        potato.setPosition(1);
    }

    public void closeClawArm(){
        potato.setPosition(0);
    }

    //Handle Encoders
    public void resetEncoders(){
        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        botLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        botRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        botLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        botRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void printEncoders(){
        telemetry.addLine("TopLeft Encoders: " + topLeft.getCurrentPosition());
        telemetry.addLine("BotLeft Encoders: " + botLeft.getCurrentPosition());
        telemetry.addLine("TopRight Encoders: " + topRight.getCurrentPosition());
        telemetry.addLine("BotRight Encoders: " + botRight.getCurrentPosition());
        telemetry.addLine("Lift Encoders: " + liftMotor.getCurrentPosition());
    }

    //Vuforia Methods
    public void initVuforia(){

    }

    public boolean loopVuforia(){
        return true;
    }

    public void stopVuforia(){

    }
}
