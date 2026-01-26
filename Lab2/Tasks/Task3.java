package Lab2.Tasks;

import java.util.Scanner;

public class Task3
{
    public static void main(String[] args)
    {
        double x = getRealNumber();
        double y = 12.3 * Math.pow(x, 4) - 9.1 * Math.pow(x, 3) //
                + 19.3 * Math.pow(x, 2) - 4.6 * x + 34.2;

        System.out.printf("12.3 x^4 - 9.1 x^3 + 19.3 x^2 - 4.6 x + 34.2 = %.2f", y);
    }

    private static double getRealNumber()
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.print("Input x: ");
        while (true)
        {
            userInput = scanner.nextLine();
            if (isRealNumber(userInput))
                return Double.parseDouble(userInput);
            System.out.println("Please input a real number.");
        }
    }

    private static boolean isRealNumber(String userInput)
    {
        // [0-9] -> regex for 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        // + for more than 1 digit
        // . to allow . as well
        return !userInput.isBlank() && userInput.matches("[0-9.]+");
    }
}
