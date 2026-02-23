package Lab6.Tasks;

public class Task1
{
    public static void main(String[] args)
    {
        Rectangle firstRectangle = new Rectangle(4, 40);
        Rectangle secondRectangle = new Rectangle(3.5, 35.9);

        System.out.println("First Rectangle:");
        firstRectangle.printInfo();
        System.out.println(); // blank line
        System.out.println("Second Rectangle:");
        secondRectangle.printInfo();
    }

    private static class Rectangle
    {
        private final double width;
        private final double height;

        public Rectangle()
        {
            this.width = 1;
            this.height = 1;
        }

        public Rectangle(double width, double height)
        {
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

        public void printInfo()
        {
            System.out.println("Width: " + width);
            System.out.println("Height: " + height);
            System.out.println("Area: " + getArea());
            System.out.println("Perimeter: " + getPerimeter());
        }
    }
}
