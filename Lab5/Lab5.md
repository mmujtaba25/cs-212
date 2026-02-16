## Program Output

```
Program execution failed or requires input:
/Users/obscure/Library/CloudStorage/OneDrive-seecs.edu.pk/_NUST/Semester 2/CS-212 Object Oriented Programming/_scripts/java_to_md.sh: line 95: timeout: command not found
```


# Lab 5 - Task 2

```java
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
```

## Program Output

```
Program execution failed or requires input:
/Users/obscure/Library/CloudStorage/OneDrive-seecs.edu.pk/_NUST/Semester 2/CS-212 Object Oriented Programming/_scripts/java_to_md.sh: line 95: timeout: command not found
```


# Lab 5 - Task 3

```java
package Lab5.Tasks;

public class Task3
{
    public static void main(String[] args)
    {
        BankAccount aliBankAccount = new BankAccount("Ali", 500);

        aliBankAccount.deposit(1000);
        aliBankAccount.withdraw(600);

        System.out.println("Final Balance: " + aliBankAccount.getBalance());
    }

    private static class BankAccount
    {
        private final String holderName;
        private int balance = 0;

        public BankAccount(String holderName, int balance)
        {
            this.holderName = holderName;
            this.balance = balance;
        }

        public BankAccount(String holderName)
        {
            this.holderName = holderName;
        }

        /* METHODS */

        public void deposit(int amount)
        {
            if (amount > 0) balance += amount;
        }

        public void withdraw(int amount)
        {
            if (balance > amount) balance -= amount;
        }

        /* GETTERS */

        public int getBalance()
        {
            return balance;
        }
    }
}
```

## Program Output

```
Program execution failed or requires input:
/Users/obscure/Library/CloudStorage/OneDrive-seecs.edu.pk/_NUST/Semester 2/CS-212 Object Oriented Programming/_scripts/java_to_md.sh: line 95: timeout: command not found
```


# Lab 5 - Task 4

```java
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
```

## Program Output

```
Program execution failed or requires input:
/Users/obscure/Library/CloudStorage/OneDrive-seecs.edu.pk/_NUST/Semester 2/CS-212 Object Oriented Programming/_scripts/java_to_md.sh: line 95: timeout: command not found
```


# Lab 5 - Task 5

```java
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
```

## Program Output

```
Program execution failed or requires input:
/Users/obscure/Library/CloudStorage/OneDrive-seecs.edu.pk/_NUST/Semester 2/CS-212 Object Oriented Programming/_scripts/java_to_md.sh: line 95: timeout: command not found
```


