package Lab4.Tasks;

import java.util.Scanner;

public class Task1
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        double[] numbers = new double[5];
        System.out.println("Enter 5 numbers: ");
        for (int i = 0; i < 5; i++)
        {
            numbers[i] = getNumber(scanner);
        }

        System.out.printf("Sum: %.2f\n", getSum(numbers));
        scanner.close();
    }

    private static double getNumber(Scanner scanner)
    {
        double out = 0.0f;

        boolean validInputFromUser = true;
        System.out.print("Enter a number: ");
        do
        {
            try
            {
                out = scanner.nextDouble();
                validInputFromUser = true;
            }
            catch (Exception ignored)
            {
                scanner.nextLine(); // discard next line
                System.out.println("Please enter a valid decimal number! ");
                validInputFromUser = false;
            }
        } while (!validInputFromUser);

        return out;
    }

    private static double getSum(double[] numbers)
    {
        double sum = 0;
        for (double number : numbers)
        {
            sum += number;
        }
        return sum;
    }
}
