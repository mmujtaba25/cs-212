package Lab5.Tasks;

public class Task2
{
    public static void main(String[] args)
    {
        Rectangle rectangle = new Rectangle(10, 20);

        System.out.println("Rectangle Length: " + rectangle.getLength());
        System.out.println("Rectangle Width : " + rectangle.getWidth());
        System.out.println("Rectangle Area  : " + rectangle.getArea());
    }

    private static class Rectangle
    {
        private final int length;
        private final int width;

        public Rectangle(int length, int width)
        {
            this.length = length;
            this.width = width;
        }

        public int getArea()
        {
            return this.length * this.width;
        }

        /* GETTERS */

        public int getWidth()
        {
            return width;
        }

        public int getLength()
        {
            return length;
        }
    }
}
