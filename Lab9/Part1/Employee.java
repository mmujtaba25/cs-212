package Lab9.Part1;

public abstract class Employee
{
    private static long lastID = 0;

    private final long employeeID;
    private String name;

    protected Employee(String name)
    {
        this.employeeID = lastID++;
        this.name = name;
    }

    public abstract double monthlyEarnings();

    /* GETTERS AND SETTERS */

    public long getEmployeeID() { return employeeID; }

    public String getName() { return name; }

    public Employee setName(String name)
    {
        this.name = name;
        return this;
    }
}
