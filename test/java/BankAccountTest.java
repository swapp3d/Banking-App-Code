package banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("john", "pass");
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), 0.01);
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount("john", "pass");
        account.deposit(200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    void testTransfer() {
        BankAccount acc1 = new BankAccount("user1", "1234");
        BankAccount acc2 = new BankAccount("user2", "abcd");
        acc1.deposit(300.0);
        acc1.transfer(acc2, 100.0);

        assertEquals(200.0, acc1.getBalance(), 0.01);
        assertEquals(100.0, acc2.getBalance(), 0.01);
    }

    @Test
    void testPasswordCheck() {
        BankAccount account = new BankAccount("john", "secure");
        assertTrue(account.checkPassword("secure"));
        assertFalse(account.checkPassword("wrongpass"));
    }

    @Test
    void testToCSV() {
        BankAccount acc = new BankAccount("user", "pass", 250.75);
        assertEquals("user,pass,250.75", acc.toCSV());
    }
}

