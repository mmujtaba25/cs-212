package Lab9.Part1;

public class ProductInvoice extends InvoiceItem
{
    private int quantity;
    private double unitPrice;

    public ProductInvoice(String description) { super(description); }

    @Override
    public double invoiceAmount() { return quantity * unitPrice; }

    /* GETTERS & SETTERS */

    public int getQuantity() { return quantity; }

    public ProductInvoice setQuantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public double getUnitPrice() { return unitPrice; }

    public ProductInvoice setUnitPrice(double unitPrice)
    {
        this.unitPrice = unitPrice;
        return this;
    }
}
