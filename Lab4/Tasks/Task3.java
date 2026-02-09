package Lab4.Tasks;

import java.util.Scanner;

public class Task3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = new int[5];
        System.out.println("Enter 5 numbers: ");
        for (int i = 0; i < numbers.length; i++)
        {
            numbers[i] = getNumber(scanner);
        }

        System.out.printf("Number of even: %d\n", countEven(numbers));
        System.out.printf("Number of odd : %d\n", countOdd(numbers));
        scanner.close();
    }

    private static int getNumber(Scanner scanner)
    {
        int out = 0;

        boolean validInputFromUser = true;
        System.out.print("Enter a number: ");
        do
        {
            try
            {
                out = scanner.nextInt();
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

    private static int countEven(int[] numbers)
    {
        int evenCount = 0;
        for (int number : numbers)
        {
            if (isEven(number))
                evenCount++;
        }
        return evenCount;
    }

    private static int countOdd(int[] numbers)
    {
        int oddCount = 0;
        for (int number : numbers)
        {
            if (!isEven(number))
                oddCount++;
        }
        return oddCount;
    }

    private static boolean isEven(int number)
    {
        return number % 2 == 0;
    }
}
