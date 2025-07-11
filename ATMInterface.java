import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Transaction failed.");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> handleWithdraw();
                case 2 -> handleDeposit();
                case 3 -> handleCheckBalance();
                case 4 -> {
                    System.out.println("Exiting ATM. Thank you!");
                    exit = true;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== ATM MENU ===");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void handleCheckBalance() {
        System.out.println("Current Balance: ₹" + account.getBalance());
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(1000.0); // Starting with ₹1000
        ATM atm = new ATM(myAccount);
        atm.start();
    }
}
