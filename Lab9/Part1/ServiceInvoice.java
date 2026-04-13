package Lab9.Part1;

public class ServiceInvoice extends InvoiceItem
{
    private double hours;
    private double ratePerHour;
    private double serviceFee;

    public ServiceInvoice(String description) { super(description); }

    @Override
    public double invoiceAmount() { return (hours * ratePerHour) + serviceFee; }

    /* GETTERS & SETTERS */

    public double getHours() { return hours; }

    public ServiceInvoice setHours(double hours)
    {
        this.hours = hours;
        return this;
    }

    public double getRatePerHour() { return ratePerHour; }

    public ServiceInvoice setRatePerHour(double ratePerHour)
    {
        this.ratePerHour = ratePerHour;
        return this;
    }

    public double getServiceFee() { return serviceFee; }

    public ServiceInvoice setServiceFee(double serviceFee)
    {
        this.serviceFee = serviceFee;
        return this;
    }
}
