package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class BankManager {
    private final HashMap<String, BankAccount> accounts = new HashMap<>();

    public boolean createAccount(String username, String password) {
        if (accounts.containsKey(username)) return false;
        accounts.put(username, new BankAccount(username, password));
        return true;
    }

    public BankAccount login(String username, String password) {
        BankAccount account = accounts.get(username);
        if (account != null && account.authenticate(password)) {
            return account;
        }
        return null;
    }

    public boolean transfer(String fromUser, String toUser, double amount) {
        BankAccount from = accounts.get(fromUser);
        BankAccount to = accounts.get(toUser);
        if (from != null && to != null && from != to) {
            from.transferTo(to, amount);
            return true;
        }
        return false;
    }

    public void printAllBalances() {
        System.out.println("--- Account Balances ---");
        for (BankAccount acc : accounts.values()) {
            System.out.printf("%s: $%.2f%n", acc.getUsername(), acc.getBalance());
        }
    }

    public void exportToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Username,Password,Balance\n");  // Include password
            for (BankAccount acc : accounts.values()) {
                writer.write(acc.getUsername() + "," + acc.getPassword() + "," + acc.getBalance() + "\n");
            }
            System.out.println("Exported to " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting: " + e.getMessage());
        }
    }
    public void loadFromCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    BankAccount acc = new BankAccount(username, password);
                    acc.deposit(balance);  // sets initial balance
                    accounts.put(username, acc);
                }
            }
            System.out.println("Accounts loaded from " + filename);
        } catch (Exception e) {
            System.out.println("No previous account data found or error loading: " + e.getMessage());
        }
    }
}

