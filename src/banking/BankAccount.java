package banking;

public class BankAccount {
    private final String username;
    private final String password;
    private double balance;

    public BankAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transferTo(BankAccount other, double amount) {
        if (withdraw(amount)) other.deposit(amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}

