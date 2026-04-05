package Lab5.Tasks;

public class Task3
{
    public static void main(String[] args)
    {
        BankAccount aliBankAccount = new BankAccount("Ali", 500);

        aliBankAccount.deposit(1000);
        aliBankAccount.withdraw(600);

        System.out.println("Final Balance: " + aliBankAccount.getBalance());
    }

    private static class BankAccount
    {
        private final String holderName;
        private int balance = 0;

        public BankAccount(String holderName, int balance)
        {
            this.holderName = holderName;
            this.balance = balance;
        }

        public BankAccount(String holderName)
        {
            this.holderName = holderName;
        }

        /* METHODS */

        public void deposit(int amount)
        {
            if (amount > 0) balance += amount;
        }

        public void withdraw(int amount)
        {
            if (balance > amount) balance -= amount;
        }

        /* GETTERS */

        public int getBalance()
        {
            return balance;
        }
    }
}
