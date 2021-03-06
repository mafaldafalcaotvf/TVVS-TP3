package Bank.BankArtifact;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Bank.BankArtifact.Bank;
import Bank.BankArtifact.Client;

public class BankIT {
	public final float floatTolerance = 0.0001f;
	private Bank bank;
	
	@Before
    public void setUp() {
		// this is a simple abstraction from what would be a database connection
		// testing several systems with one simple test
		
		bank = new Bank();
		Client carlos = new Client("Carlos");
		Client melo = new Client("Melo");
		Client rui = new Client("Rui");
		
		bank.addClient(carlos);	bank.addClient(melo); bank.addClient(rui);
    }
	

	@Test
	public void testDepositAmount() {
		// use the functions depositAccount(Client,float) & getClientByName(String) from Bank 
		bank.depositAccount(bank.getClientByName("Carlos"), 130);
		float saldo = bank.getClientByName("Carlos").getAccount().getAmount();
		assertEquals(130, saldo,floatTolerance);
	}
	
	@Test	
	public void testWithdrawAmount() {	
		// use the functions depositAccount(Client), getClientByName(String) & withdrawClientAccount(Client) from Bank
		bank.depositAccount(bank.getClientByName("Melo"), 150);
		bank.withdrawClientAccount(bank.getClientByName("Melo"), 50);
		float saldo = bank.getClientByName("Melo").getAccount().getAmount();
		assertEquals(100, saldo, floatTolerance);
	}
	
	@Test
	public void testTransactionBetweenUsers() {
		// use the functions transfer(Client,Client,float) & getClientByName(String) from Bank
		bank.depositAccount(bank.getClientByName("Carlos"), 200);
		bank.depositAccount(bank.getClientByName("Melo"), 150);
		bank.transfer(bank.getClientByName("Carlos"), bank.getClientByName("Melo"), 100);
		float saldo = bank.getClientByName("Melo").getAccount().getAmount();
		assertEquals(250, saldo, floatTolerance);
	}

}
