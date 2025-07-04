package banking;

public class BankAccount {
    private String username;
    private String password;
    private double balance;

    public BankAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public BankAccount(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public void transfer(BankAccount receiver, double amount) {
        if (receiver != null && amount > 0 && amount <= balance) {
            withdraw(amount);
            receiver.deposit(amount);
        }
    }

    public void printBalance() {
        System.out.printf("Balance for %s: $%.2f%n", username, balance);
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String toCSV() {
        return username + "," + password + "," + balance;
    }
}


