// ds used for points in 2D, 3D, 4D
public class Point {

    private final double x;     // x-coordinate of this point
    private double y;           // y-coordinate of this point
    private final double z;
    private final double w;

    public Point(double x, double y, double z, double w) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Point(double x, double y) {
        this(x, y, 0.0, 0.0);
    }

    public Point(double x, double y, double z) {
        this(x, y, z, 0.0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setY(double newY) {
        y = newY;
    }
}
