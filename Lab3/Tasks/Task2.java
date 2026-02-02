package Lab3.Tasks;

import java.util.Scanner;

public class Task2
{
    private static final int EXIT_CHOICE = 0;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: ");
        int initialBalance = scanner.nextInt();
        Account account = new Account(initialBalance);

        int input;
        do
        {
            printTUI();
            System.out.print("Enter your choice: ");
            input = scanner.nextInt();
            executeChoice(scanner, input, account);
        } while (input != EXIT_CHOICE);

        scanner.close();
    }

    private static void printTUI()
    {
        System.out.println("\n--- Bank Account Menu ---");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println(EXIT_CHOICE + ". Exit");
    }

    private static void executeChoice(Scanner scanner, int choice, Account account)
    {
        switch (choice)
        {
            case 1 -> // Deposit
            {
                System.out.print("Enter amount to deposit: ");
                int amount = scanner.nextInt();
                if (account.deposit(amount))
                    System.out.println("Deposit successful.");
                else
                    System.out.println("Deposit failed. Amount must be positive.");
            }
            case 2 -> // Withdraw
            {
                System.out.print("Enter amount to withdraw: ");
                int amount = scanner.nextInt();
                if (account.withdraw(amount))
                    System.out.println("Withdrawal successful.");
                else
                    System.out.println("Withdrawal failed. Check amount and balance.");
            }
            case 3 -> // Check Balance
            {
                System.out.println("Current balance: " + account.checkBalance());
            }
            case EXIT_CHOICE -> // Exit
            {
                System.out.println("------------------------------");
                System.out.println("Exiting...");
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
        // wait for user to press Enter
        System.out.print("Press Enter to continue...");
        try
        {
            System.in.read();
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    private static final class Account
    {
        private int balance;

        public Account(int initialBalance)
        {
            this.balance = initialBalance;
        }

        public boolean deposit(int amount)
        {
            if (amount > 0)
            {
                this.balance += amount;
                return true;
            }
            return false;
        }

        public boolean withdraw(int amount)
        {
            if (amount > 0 && amount <= balance)
            {
                this.balance -= amount;
                return true;
            }
            return false;
        }

        public int checkBalance()
        {
            return balance;
        }
    }
}
