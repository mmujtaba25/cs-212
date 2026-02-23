package Lab6.Tasks;

public class Task2
{
    public static void main(String[] args)
    {
        Employee firstEmployee = new Employee("Muhammad", "Ali", 3000);
        Employee secondEmployee = new Employee("Muhammad", "Ahmad", 3500);

        firstEmployee.printInfo();
        System.out.println(); // blank line
        secondEmployee.printInfo();

        // Give each employee a 10% raise
        firstEmployee.setMonthlySalary(firstEmployee.getMonthlySalary() * 1.10);
        secondEmployee.setMonthlySalary(secondEmployee.getMonthlySalary() * 1.10);

        System.out.println(); // blank line
        System.out.println("After 10% raise:");
        firstEmployee.printInfo();
        System.out.println(); // blank line
        secondEmployee.printInfo();
    }

    private static class Employee
    {
        private String firstName;
        private String lastName;
        private double monthlySalary;

        public Employee(String firstName, String lastName, double monthlySalary)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.monthlySalary = Math.max(0, monthlySalary);
        }

        public void printInfo()
        {
            System.out.printf("%s %s:\n", firstName, lastName);
            System.out.printf("Monthly Salary: %.2f\n", monthlySalary);
        }

        /* GETTERS & SETTERS */

        public String getFirstName()
        {
            return firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public double getMonthlySalary()
        {
            return monthlySalary;
        }

        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }

        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }

        public void setMonthlySalary(double monthlySalary)
        {
            this.monthlySalary = Math.max(0, monthlySalary);
        }
    }
}
