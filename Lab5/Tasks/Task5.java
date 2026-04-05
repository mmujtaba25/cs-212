package Lab5.Tasks;

public class Task5
{
    public static void main(String[] args)
    {
        Employee ahmad = new Employee("Ahmad", 10_000);
        System.out.println("Ahmad Yearly Salary is: " + ahmad.getYearlySalary());
    }

    private static class Employee
    {
        private final String name;
        private final int monthlySalary;

        public Employee(String name, int monthlySalary)
        {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public int getYearlySalary()
        {
            return monthlySalary * 12;
        }
    }
}
