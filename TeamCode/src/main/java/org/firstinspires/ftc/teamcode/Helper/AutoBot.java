package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;


public class AutoBot extends Robot{

    //Tensor Variables
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private static final String VUFORIA_KEY =
            "ATyCPNT/////AAABmUcK7WJSFEtbvICLlZK2chE6REd6emSDzHsJ0c8fml6HkSE8PrITq4Jq2zJjzsSNcjkIrxQP4B2BCcNvqDU8di8TW8S3sOYrXakm4KIDqGO7S3cIhfVL21FAbKDfD3IkpP87+YhT5JcxpFguOYMjDiW97/UyMAqLsddkbO3e8r8ES/30gQUnwCxqgMY+5X+UV7L6e7If8WdDFHnteszwkaHAhDD5aWSi9mSSpQy2TG+jKKggEwJh++vBtSBvN/GO5Yj4V/nqfLX5y0kipl/MD1Pzve+tfcGufSNK8idlzFhQ86gtVzB62Bm5PnE/9BbdxjNOwlB781EXsB2yTs6j54Pd8YC4EoAXEGLR9YXMGUCj";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    //Auto Variables
    public int count = 0;
    public int backwardEncoder = 0;

    public AutoBot(HardwareMap hardwareMap, Telemetry tele) {
        super(hardwareMap, tele);
    }

    //SetUp Methods
    public void setUpWheels() {
        try {

            topLeft = hardwareMap.get(DcMotor.class, "topLeft");
            topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("topLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("topLeft : ERROR");
        }

        try {
            topRight = hardwareMap.get(DcMotor.class, "topRight");
            topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("topRight : OK");
        } catch (Exception e) {
            telemetry.addLine("topRight : ERROR");
        }

        try {
            botLeft = hardwareMap.get(DcMotor.class, "botLeft");
            botLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("botLeft : OK");
        } catch (Exception e) {
            telemetry.addLine("botLeft : ERROR");
        }

        try {
            botRight = hardwareMap.get(DcMotor.class, "botRight");
            botRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("botRight : OK");
        } catch (Exception e) {
            telemetry.addLine("botRight : ERROR");
        }
    }

    public void setUpLiftMotor() {
        try {
            liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addLine("liftMotor : OK");
        } catch (Exception e) {
            telemetry.addLine("liftMotor : ERROR");
        }
    }

    public void setUpClawServos(){
        try {
            claw = hardwareMap.get(Servo.class, "claw");

        } catch (Exception e) {
            telemetry.addLine("claw : ERROR");
        }
        try {
            movingClaw = hardwareMap.get(Servo.class, "movingClaw");

        } catch (Exception e) {
            telemetry.addLine("movingClaw : ERROR");
        }

        try{
            potato = hardwareMap.get(Servo.class, "potato");
            potato.setDirection(Servo.Direction.REVERSE);
            //potato.setPosition(0);
        }
        catch(Exception e ){
            telemetry.addLine("potato : error");
        }

    }

    //Mechanical Movement Methods

    public void forward(int ticks){
        //telemetry.addLine(""+(Math.abs(topLeft.getCurrentPosition()) < ticks));
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
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
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
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
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
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
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
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

    public void forwardCos(int ticks){
        telemetry.addLine(""+(Math.abs(botLeft.getCurrentPosition()) < ticks));
        double power;
        if(ticks - botLeft.getCurrentPosition() > (730 * 2)) {
           power = (Math.cos((9.87 * botLeft.getCurrentPosition())/(ticks * Math.PI)) / 4) + 0.75;
        }
        else{
            power = 0.5;
        }
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(-power);
            botLeft.setPower(-power);
            topRight.setPower(power);
            botRight.setPower(power);
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

    public void backwardCos(int ticks){
        telemetry.addLine(""+(Math.abs(botLeft.getCurrentPosition()) < ticks));
        double power;
        if(ticks - botLeft.getCurrentPosition() > (730 * 2)) {
            power = (Math.cos((9.87 * botLeft.getCurrentPosition())/(ticks * Math.PI)) / 4) + 0.75;
        }
        else{
            power = 0.5;
        }
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(power);
            botLeft.setPower(power);
            topRight.setPower(-power);
            botRight.setPower(-power);
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

    public void rotateLeftCos(int ticks){
        telemetry.addLine(""+(Math.abs(botLeft.getCurrentPosition()) < ticks));
        double power;
        if(ticks - botLeft.getCurrentPosition() > (200)) {
            power = (Math.cos((9.87 * botLeft.getCurrentPosition())/(ticks * Math.PI)) / 5) + 0.3;
        }
        else{
            power = 0.1;
        }
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(power);
            botLeft.setPower(power);
            topRight.setPower(power);
            botRight.setPower(power);
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

    public void rotateRightCos(int ticks){
        telemetry.addLine(""+(Math.abs(botLeft.getCurrentPosition()) < ticks));
        double power;
        if(ticks - botLeft.getCurrentPosition() > (100)) {
            power = (Math.cos((9.87 * botLeft.getCurrentPosition())/(ticks * Math.PI)) / 5) + 0.4;
        }
        else{
            power = 0.2;
        }
        if(Math.abs(botLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(-power);
            botLeft.setPower(-power);
            topRight.setPower(-power);
            botRight.setPower(-power);
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

    public void forwardCM(int cm){
        double ticksPerCM = 730 /(10.16*Math.PI);
        int ticks = (int)ticksPerCM * cm;
        forwardCos(ticks);
    }

    public void backwardCM(int cm){
        double ticksPerCM = 730 /(10.16*Math.PI);
        int ticks = (int)ticksPerCM * cm;
        backwardCos(ticks);
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
        if(botLeft.getCurrentPosition() < ticks) {
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
        if(botLeft.getCurrentPosition() > -ticks) {
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
        count++;
    }

    public void closeClaw(){
        if(claw.getPosition() != 0) {
            claw.setPosition(0);
        }
        else{
            count++;
        }
    }

    public void closeClawArm(){
        potato.setPosition(1);
    }

    public void openClawArm(){
        potato.setPosition(0);
        count++;
    }

    public void movingClawRight(){
        if(movingClaw.getPosition() != 0.85){
            movingClaw.setPosition(0.85);
        }
        else{
            count++;
        }
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



    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    public void initTensor(){
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
    }

    public void loopTensor() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                // step through the list of recognitions and display boundary info.
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                    telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                            recognition.getRight(), recognition.getBottom());
                }
                telemetry.update();
            }
        }
    }

    public void stopTensor(){
        tfod.shutdown();
    }
}
