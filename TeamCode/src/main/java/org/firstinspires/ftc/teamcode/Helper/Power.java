package org.firstinspires.ftc.teamcode.Helper;

public class Power {

    public final double topLeft;
    public final double topRight;
    public final double botLeft;
    public final double botRight;

    public static boolean reduce = false;
    private static Timer time = new Timer();

    public Power(double lX, double lY, double rX) {
        boolean right = false;
        double vD = Math.min(Math.sqrt(Math.pow(lX, 2)) + Math.pow(lY, 2), 1);
        double theta = -(Math.atan2(-lX, -lY)) + Math.PI / 4;

        if (right && time.hasReached(250)) {
            reduce = !reduce;
            time.reset();
        }

        double reduction;

        if (reduce) reduction = 0.35;
        else reduction = 1;

        topLeft = -1 * (vD * Math.sin(theta) + rX) * reduction;
        topRight = (vD * Math.cos(theta) - rX) * reduction;
        botLeft = -1 * (vD * Math.cos(theta) + rX) * reduction;
        botRight = (vD * Math.sin(theta) - rX) * reduction;

    }
}