package Lab9.Part1;

public class FixedSalaryEmployee extends Employee
{
    private double baseSalary;
    private double allowance;

    protected FixedSalaryEmployee(String name) { super(name); }

    @Override
    public double monthlyEarnings() { return baseSalary + allowance; }

    /* GETTERS & SETTERS */

    public double getBaseSalary() { return baseSalary; }

    public FixedSalaryEmployee setBaseSalary(double baseSalary)
    {
        this.baseSalary = baseSalary;
        return this;
    }

    public double getAllowance() { return allowance; }

    public FixedSalaryEmployee setAllowance(double allowance)
    {
        this.allowance = allowance;
        return this;
    }
}
