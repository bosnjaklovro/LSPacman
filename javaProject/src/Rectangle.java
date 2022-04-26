public class Rectangle {
    public double xx;
    public double yy;
    public double widthh;
    public double heightt;

    public Rectangle(double x, double y, double width, double height) {
        this.xx = x;
        this.yy = y;
        this.widthh = width;
        this.heightt = height;
    }

    public boolean overlaps(Rectangle other) {
        boolean noIntersection = (this.xx + this.widthh < other.xx) || (other.xx + other.widthh < this.xx)
                || (this.yy + this.heightt < other.yy) || (other.yy + other.heightt < this.yy);
        return !noIntersection;
    }

}
