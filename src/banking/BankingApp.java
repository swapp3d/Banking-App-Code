package banking;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankManager bankManager = new BankManager();
        bankManager.loadFromCSV("account_report.csv");
        BankAccount currentAccount = null;

        while (true) {
            System.out.println("\n--- Banking System ---");
            if (currentAccount == null) {
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                System.out.print("Choose: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Username: ");
                        String user = scanner.nextLine();
                        System.out.print("Password: ");
                        String pass = scanner.nextLine();
                        if (bankManager.createAccount(user, pass)) {
                            System.out.println("Account created!");
                        } else {
                            System.out.println("Account already exists.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Username: ");
                        String user = scanner.nextLine();
                        System.out.print("Password: ");
                        String pass = scanner.nextLine();
                        BankAccount acc = bankManager.login(user, pass);
                        if (acc != null) {
                            currentAccount = acc;
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed.");
                        }
                    }
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                }
            } else {
                System.out.println("Logged in as: " + currentAccount.getUsername());
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Check Balance");
                System.out.println("5. Export All Accounts to CSV");
                System.out.println("6. Logout");
                System.out.print("Choose: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> {
                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();
                        currentAccount.deposit(amount);
                        System.out.println("Deposited.");
                    }
                    case 2 -> {
                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();
                        if (currentAccount.withdraw(amount)) {
                            System.out.println("Withdrawn.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Recipient username: ");
                        String toUser = scanner.nextLine();
                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();
                        if (bankManager.transfer(currentAccount.getUsername(), toUser, amount)) {
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Transfer failed.");
                        }
                    }
                    case 4 -> System.out.printf("Balance: $%.2f%n", currentAccount.getBalance());
                    case 5 -> bankManager.exportToCSV("account_report.csv");
                    case 6 -> {
                        currentAccount = null;
                        System.out.println("Logged out.");
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
}



