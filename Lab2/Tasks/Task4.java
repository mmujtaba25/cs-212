package Lab2.Tasks;

import java.util.Scanner;

public class Task4
{
    public static void main(String[] args)
    {
        double distance, initialDistance, initialVelocity, acceleration, time;

        initialDistance = getRealNumber("Enter initial Distance (m): ");
        initialVelocity = getRealNumber("Enter initial Velocity (m/s): ");
        acceleration = getRealNumber("Enter Acceleration (m/s^2): ");
        time = getRealNumber("Enter time (s): ");
        distance = initialDistance + initialVelocity //
                * time + (acceleration * Math.pow(time, 2) / 2);

        System.out.printf("Distance (m): %.2f", distance);
    }

    private static double getRealNumber(String prompt)
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.print(prompt);
        while (true)
        {
            userInput = scanner.nextLine();
            if (isRealNumber(userInput))
                try { return Double.parseDouble(userInput); }
                catch (NumberFormatException ignored) { }

            System.out.println("Please input a real number.");
        }
    }

    private static boolean isRealNumber(String userInput)
    {
        // [0-9] -> regex for 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        // + for more than 1 digit
        // . to allow . as well
        // - to allow -
        return !userInput.isBlank() && userInput.matches("[0-9.-]+");
    }
}
