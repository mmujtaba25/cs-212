package Lab9.Part2;

public class FixedSalaryEmployee extends Employee
{
    private double baseSalary;
    private double allowance;

    protected FixedSalaryEmployee(String name) { super(name); }

    @Override
    public double monthlyEarnings() { return baseSalary + allowance; }

    @Override
    public double calculateTax() { return 0.12 * monthlyEarnings(); }

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
