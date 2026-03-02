package Lab7.Tasks;

import java.util.Date;
import java.util.Scanner;

public class Task1
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        double width = getDouble("Enter width: ");
        double height = getDouble("Enter height: ");
        String color = getString("Enter color: ");
        boolean filled = getBoolean("Is filled (true/false): ");

        Rectangle rectangle = new Rectangle(width, height, color, filled);

        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Perimeter: " + rectangle.getPerimeter());
        System.out.println("Color: " + rectangle.getColor());
        System.out.println("Filled: " + rectangle.isFilled());
    }

    private static boolean getBoolean(String message)
    {
        System.out.print(message);
        return scanner.nextBoolean();
    }

    private static String getString(String message)
    {
        scanner.nextLine(); // consume the newline character
        System.out.print(message);
        return scanner.nextLine();
    }

    private static double getDouble(String message)
    {
        System.out.print(message);
        return scanner.nextDouble();
    }
}

class GeometricObject
{
    String color;
    boolean filled;
    Date dateCreated;

    public GeometricObject()
    {
        this.color = "white";
        this.filled = true;
        this.dateCreated = new Date();
    }

    public GeometricObject(String color, boolean filled)
    {
        this.color = color;
        this.filled = filled;
        this.dateCreated = new Date();
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + ": color = " + color + ", filled = " + filled;
    }

    /* GETTERS AND SETTERS */

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public boolean isFilled()
    {
        return filled;
    }

    public void setFilled(boolean filled)
    {
        this.filled = filled;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }
}

class Rectangle extends GeometricObject
{
    double width;
    double height;

    public Rectangle()
    {
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(double width, double height)
    {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled)
    {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    public double getArea()
    {
        return width * height;
    }

    public double getPerimeter()
    {
        return 2 * (width + height);
    }

    @Override
    public String toString()
    {
        return "Rectangle: width = " + width + " height = " + height;
    }

    /* GETTERS AND SETTERS */

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }
}
