package Lab2.Tasks;

import java.util.Scanner;

public class Task2
{
    public static void main(String[] args)
    {
        String userInput = "";

        System.out.print("Input any number: ");
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            userInput = scanner.nextLine();
            if (onlyNumbers(userInput))
                break;

            System.out.println("Please input only numbers");
        }
        scanner.close();

        for (char c : userInput.toCharArray())
        {
            System.out.printf("%c ", c);
        }
    }

    private static boolean onlyNumbers(String userInput)
    {
        // [0-9] -> regex for 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        // + for more than 1 digit
        return !userInput.isBlank() && userInput.matches("[0-9]+");
    }
}
