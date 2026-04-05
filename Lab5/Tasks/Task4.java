package Lab5.Tasks;

public class Task4
{
    public static void main(String[] args)
    {
        Car toyotaCorolla = new Car("Toyota", "Corolla", 2020);
        Car toyotaCivic = new Car("Toyota", "Civic", 2018);

        System.out.println("------------------------");
        toyotaCorolla.displayInfo();
        System.out.println("------------------------");
        toyotaCivic.displayInfo();
        System.out.println("------------------------");
    }

    private static class Car
    {
        private final String brand;
        private final String model;
        private final int year;

        public Car(String brand, String model, int year)
        {
            this.brand = brand;
            this.model = model;
            this.year = year;
        }

        public void displayInfo()
        {
            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Year : " + year);
        }
    }
}
