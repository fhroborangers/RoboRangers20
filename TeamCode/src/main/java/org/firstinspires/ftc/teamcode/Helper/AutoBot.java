package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class AutoBot extends Robot{

    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private static final String VUFORIA_KEY = "ATMeJeb/////AAAAGaZ47DzTRUyOhcXnfJD+z89ATBWAF+fi+oOutLvXaf0YT/RPuf2mu6VJsJowCDiWiOzGMHUjXKsLBqA4Ziar76oZY/juheUactiQaY6Z3qPHnGmchAMlYuqgKErvggTuqmFca8VvTjtB6YOheJmAbllTDTaCudODpnIDkuFNTa36WCTr4L8HcCnIsB7bjF8pZoivYEBwPkfCVtfAiEpqxbyDAZgNXnuCyp6v/oi3FYZbp7JkFVorcM182+q0PVN5gIr14SKEMlDcDFDiv/sQwNeQOs5iNBd1OSkCoTe9CYbdmtE0gUXxKN2w9NqwATYC6YRJP9uoopxqmr9zkepI10peh2/RnU6pHOmR0KKRAVh8";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    public int count = 0;

    public AutoBot(HardwareMap hardwareMap, Telemetry tele) {
        super(hardwareMap, tele);
    }

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

    public void forward(int ticks){
        telemetry.addLine(""+(Math.abs(topLeft.getCurrentPosition()) < ticks));
        if(Math.abs(topLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(-1.00);
            botLeft.setPower(-1.00);
            topRight.setPower(1.00);
            botRight.setPower(1.00);
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

    //param is negative number
    public void backward(int ticks) {
        telemetry.addLine(""+(Math.abs(topLeft.getCurrentPosition()) < ticks));
        if(Math.abs(topLeft.getCurrentPosition()) < ticks) {
            topLeft.setPower(1.00);
            botLeft.setPower(1.00);
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
        double ticksPerCM = 730 /(4*Math.PI);
        int ticks = (int)ticksPerCM * cm;
        forward(ticks);
    }

    public void backwardCM(int cm){
        double ticksPerCM = 730 /(4*Math.PI);
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

    public void strafeLeft(int ticks)
    {
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

    public void resetEncoders(){
        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        botLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        botRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        botLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        botRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Need to add liftMotor once second Expansion Hub is added
    }

    public void printEncoders(){
        telemetry.addLine("TopLeft Encoders: " + topLeft.getCurrentPosition());
        telemetry.addLine("BotLeft Encoders: " + botLeft.getCurrentPosition());
        telemetry.addLine("TopRight Encoders: " + topRight.getCurrentPosition());
        telemetry.addLine("BotRight Encoders: " + botRight.getCurrentPosition());
        //Need to add liftMotor once second Expansion Hub is added
    }

    public void initVu(){
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
    }

    public void loopVu(){
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                if(updatedRecognitions.size() == 1){
                    telemetry.addLine("YAYAAAAAAAAAAAAY");
                }
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

    public int[] countStones() {
        int[] stones = {0,0,0,0,0,0};
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    for(int i = 0; i < stones.length; i++){
                        if(stones[i] == 0){
                            if(recognition.getLabel().equals(LABEL_FIRST_ELEMENT)){
                                stones[i] = 1;
                            }
                            else if(recognition.getLabel().equals(LABEL_SECOND_ELEMENT)){
                                stones[i] = 2;
                            }
                        }
                    }
                }
            }
        }
        return stones;
    }

    public boolean SkyStonesLocation1(){
        boolean output = false;
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    if(recognition.getLabel().equals(LABEL_SECOND_ELEMENT)){
                        float middlePointBlock = (recognition.getLeft() + recognition.getRight())/2;
                        float middlePointImage = (recognition.getImageWidth()/2);
                        //Middle of detection rectangle should within a range of 10 of the middle of image
                        if(middlePointBlock > middlePointImage - 10 && middlePointBlock < middlePointImage + 10){
                            telemetry.addLine("FOUND SKYSTONE ---- STOOOOOOP :)");
                            output = true;
                        }
                        else{
                            telemetry.addLine("KEEEP GOING :(");
                            output = false;
                        }
                    }
                }
            }
        }
        return output;
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hwm.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hwm.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.74;//this will change (.74-.75)
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }



}
