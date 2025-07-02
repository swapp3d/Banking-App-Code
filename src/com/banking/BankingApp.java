package com.banking;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        while (true) {
            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Deposit to Account 1");
            System.out.println("2. Withdraw from Account 1");
            System.out.println("3. Print Account 1 Balance");
            System.out.println("4. Transfer to Account 2");
            System.out.println("5. Print Account 2 Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    BigDecimal depositAmount = scanner.nextBigDecimal();
                    account1.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    BigDecimal withdrawAmount = scanner.nextBigDecimal();
                    account1.withdraw(withdrawAmount);
                    break;
                case 3:
                    account1.printBalance();
                    break;
                case 4:
                    System.out.print("Enter amount to transfer to Account 2: ");
                    BigDecimal transferAmount = scanner.nextBigDecimal();
                    account1.transfer(account2, transferAmount);
                    break;
                case 5:
                    account2.printBalance();
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}


