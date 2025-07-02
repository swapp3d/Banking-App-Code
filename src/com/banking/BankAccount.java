package com.banking;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance;

    public BankAccount() {
        this.balance = BigDecimal.ZERO;
    }

    public BankAccount(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }

    public void printBalance() {
        System.out.println("Current balance: " + balance);
    }

    public void transfer(BankAccount toAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to another account.");
        } else {
            System.out.println("Transfer failed due to invalid amount or insufficient funds.");
        }
    }
}
