package banking;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BankManager {

    private HashMap<String, BankAccount> accounts = new HashMap<>();
    private final String FILE_NAME = "account_report.csv";

    public void createAccount(String username, String password) {
        if (!accounts.containsKey(username)) {
            accounts.put(username, new BankAccount(username, password));
            saveToCSV();
        }
    }

    public BankAccount login(String username, String password) {
        BankAccount acc = accounts.get(username);
        if (acc != null && acc.checkPassword(password)) {
            return acc;
        }
        return null;
    }

    public BankAccount getAccount(String username) {
        return accounts.get(username);
    }

    public void transfer(String from, String to, double amount) {
        BankAccount sender = accounts.get(from);
        BankAccount receiver = accounts.get(to);
        if (sender != null && receiver != null && sender.getBalance() >= amount) {
            sender.withdraw(amount);
            receiver.deposit(amount);
            saveToCSV();
        }
    }

    public void loadFromCSV() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                // Skip header if present
                if (isFirstLine && line.toLowerCase().contains("username")) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length != 3) continue;

                String username = data[0];
                String password = data[1];
                double balance = Double.parseDouble(data[2]);

                BankAccount account = new BankAccount(username, password, balance);
                accounts.put(username, account);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to load accounts: " + e.getMessage());
        }
    }


    public void saveToCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
                BankAccount acc = entry.getValue();
                writer.println(acc.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Failed to save accounts: " + e.getMessage());
        }
    }
}


