package Lab7.Activities;

public class Activity4
{
    public static void main(String[] args)
    {
        B b = new B(5, 10);
        System.out.println("Area = " + b.getArea());
    }
}

class Circle
{
    private double radius;

    public Circle(double radius)
    {
        // radius = radius; : ERROR: we need to use `this.`
        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }

    public double getArea()
    {
        return radius * radius * Math.PI;
    }
}

class B extends Circle
{
    private double length;

    // B(double radius, double length) : ERROR: constructor needs to be public to be called in `main`
    public B(double radius, double length)
    {
        // Circle(radius); : ERROR: to call constructor of parent class, we use `super`
        super(radius);
        // length = length; : ERROR: we need to use `this.`
        this.length = length;
    }

    /**
     * Override getArea()
     */
    public double getArea()
    {
        // return getArea() * length; : ERROR: this line will call itself recursively, we need to use super
        return super.getArea() * length;
    }
}
