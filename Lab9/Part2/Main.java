package Lab9.Part2;


import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        final ArrayList<Employee> employees = new ArrayList<>();
        final ArrayList<InvoiceItem> invoices = new ArrayList<>();

        employees.add(new FixedSalaryEmployee("Ali").setBaseSalary(3000).setAllowance(500));
        employees.add(new HourlyEmployee("Talal").setHourlyRate(20).setHoursWorked(45));

        invoices.add(new ProductInvoice("Laptop").setQuantity(2).setUnitPrice(1200));
        invoices.add(new ServiceInvoice("Consulting").setHours(10).setRatePerHour(150));

        printTitle("Employees");

        for (Employee employee : employees)
            System.out.printf(
                    "%-16s %-24s Rs. %.2f + Rs. %.2f tax%n",
                    employee.getName(),
                    employee.getClass().getSimpleName(),
                    employee.monthlyEarnings(),
                    employee.calculateTax()
            );

        printTitle("Invoices");
        for (InvoiceItem invoice : invoices)
            System.out.printf(
                    "%-16s %-24s Rs. %.2f + Rs. %.2f tax%n",
                    invoice.getDescription(),
                    invoice.getClass().getSimpleName(),
                    invoice.invoiceAmount(),
                    invoice.calculateTax()
            );
    }

    private static void printTitle(String Employees)
    {
        System.out.println();
        System.out.println(getCentered(Employees));
        System.out.println();
    }

    private static String getCentered(String text)
    {
        final int width = 69;
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, padding));
    }
}
