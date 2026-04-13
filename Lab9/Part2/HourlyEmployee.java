package Lab9.Part2;

public class HourlyEmployee extends Employee
{
    private double hourlyRate = 15.0;
    private double hoursWorked;

    protected HourlyEmployee(String name) { super(name); }

    @Override
    public double monthlyEarnings() { return (hourlyRate * hoursWorked) * (hoursWorked > 40 ? 1.5 : 1); }

    @Override
    public double calculateTax() { return 0.08 * monthlyEarnings(); }

    /* GETTERS & SETTERS */

    public double getHourlyRate() { return hourlyRate; }

    public HourlyEmployee setHourlyRate(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
        return this;
    }

    public double getHoursWorked() { return hoursWorked; }

    public HourlyEmployee setHoursWorked(double hoursWorked)
    {
        this.hoursWorked = hoursWorked;
        return this;
    }
}
