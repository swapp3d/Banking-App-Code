package banking;

import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class BankManagerTest {

    private BankManager manager;

    @BeforeEach
    public void setUp() {
        // Delete CSV file before test (for a clean state)
        File file = new File("account_report.csv");
        if (file.exists()) file.delete();

        manager = new BankManager();
        manager.createAccount("swap", "1111");
        manager.createAccount("swap1", "2222");

        manager.getAccount("swap").deposit(100.0);
        manager.getAccount("swap1").deposit(200.0);
    }

    @Test
    public void testLogin() {
        BankAccount swap = manager.login("swap", "1111");
        assertNotNull(swap);
        assertEquals("swap", swap.getUsername());

        assertNull(manager.login("swap", "wrong"));
        assertNull(manager.login("nonexistent", "1234"));
    }

    @Test
    public void testTransfer() {
        manager.transfer("swap", "swap1", 50.0);
        assertEquals(50.0, manager.getAccount("swap").getBalance(), 0.01);
        assertEquals(250.0, manager.getAccount("swap1").getBalance(), 0.01);
    }

    @Test
    public void testSaveAndLoadCSV() {
        manager.saveToCSV();

        BankManager newManager = new BankManager();
        newManager.loadFromCSV();

        BankAccount swap = newManager.getAccount("swap");
        BankAccount swap1 = newManager.getAccount("swap1");

        assertNotNull(swap);
        assertNotNull(swap1);
        assertEquals(100.0, swap.getBalance(), 0.01);
        assertEquals(200.0, swap1.getBalance(), 0.01);
    }
}

