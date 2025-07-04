package banking;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankManager bankManager = new BankManager();
        bankManager.loadFromCSV();

        BankAccount currentAccount = null;

        while (true) {
            System.out.println("\n==== Banking Menu ====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Show Balance");
            System.out.println("7. Exit");

            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String user = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String pass = scanner.nextLine();
                    bankManager.createAccount(user, pass);
                    System.out.println(" Account created.");
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    currentAccount = bankManager.login(loginUser, loginPass);
                    if (currentAccount != null) {
                        System.out.println(" Login successful.");
                    } else {
                        System.out.println(" Login failed.");
                    }
                    break;

                case 3:
                    if (currentAccount != null) {
                        System.out.print("Amount to deposit: ");
                        double dep = Double.parseDouble(scanner.nextLine());
                        currentAccount.deposit(dep);
                        bankManager.saveToCSV();
                        System.out.println(" Deposit successful.");
                    } else {
                        System.out.println(" Please login first.");
                    }
                    break;

                case 4:
                    if (currentAccount != null) {
                        System.out.print("Amount to withdraw: ");
                        double wit = Double.parseDouble(scanner.nextLine());
                        currentAccount.withdraw(wit);
                        bankManager.saveToCSV();
                        System.out.println(" Withdrawal successful.");
                    } else {
                        System.out.println(" Please login first.");
                    }
                    break;

                case 5:
                    if (currentAccount != null) {
                        System.out.print("Send to username: ");
                        String toUser = scanner.nextLine();
                        System.out.print("Amount: ");
                        double amt = Double.parseDouble(scanner.nextLine());
                        bankManager.transfer(currentAccount.getUsername(), toUser, amt);
                        System.out.println(" Transfer attempted.");
                    } else {
                        System.out.println(" Please login first.");
                    }
                    break;

                case 6:
                    if (currentAccount != null) {
                        currentAccount.printBalance();
                    } else {
                        System.out.println(" Please login first.");
                    }
                    break;

                case 7:
                    System.out.println(" Exiting app, - bye!");
                    return;

                default:
                    System.out.println(" Invalid option.");
            }
        }
    }
}




