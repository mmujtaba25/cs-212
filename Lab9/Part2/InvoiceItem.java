package Lab9.Part2;

public abstract class InvoiceItem implements Taxable
{
    private static long lastInvoiceNumber = 0;

    private final long invoiceNumber;
    private String description;

    public InvoiceItem(String description)
    {
        this.invoiceNumber = lastInvoiceNumber++;
        this.description = description;
    }

    public abstract double invoiceAmount();

    /* GETTERS AND SETTERS */

    public long getInvoiceNumber() { return invoiceNumber; }

    public String getDescription() { return description; }

    public InvoiceItem setDescription(String description)
    {
        this.description = description;
        return this;
    }
}
