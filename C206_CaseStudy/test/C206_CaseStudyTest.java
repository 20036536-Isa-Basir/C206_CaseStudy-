import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {

	private Currency c1;
	private Currency c2;
	private currencyHolding h1;
	private currencyHolding h2;
	private currencyHoldingRate chr1;
	private currencyHoldingRate chr2;
	private BuyTransaction bt1;
	private BuyTransaction bt2;
	private SellTransaction st1;
	private SellTransaction st2;

	private ArrayList<Currency> currencyList;
	private ArrayList<currencyHolding> currencyHoldingList;
	private ArrayList<currencyHoldingRate> currencyHoldingRate = new ArrayList<currencyHoldingRate>();
	private ArrayList<BuyTransaction> buyTransactionList = new ArrayList<BuyTransaction>();
	private ArrayList<SellTransaction> sellTransactionList = new ArrayList<SellTransaction>();

	public C206_CaseStudyTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		// prepare test data
		c1 = new Currency("SGD", "Singapore", 1.00, 3.00);
		c2 = new Currency("MYR", "Malaysia", 0.32, 3.13);
		h1 = new currencyHolding("SGD", 1);
		h2 = new currencyHolding("MYR", 3);
		chr1 = new currencyHoldingRate("SGD", 100000.00, "Singapore", 1.00, 3.00);
		chr2 = new currencyHoldingRate("MYR", 100000.00, "Malaysia", 0.32, 3.13);
		bt1 = new BuyTransaction(1, "MYR", "SGD", 100.00, "09/08/2021");
		bt2 = new BuyTransaction(2, "MYR", "SGD", 500.00, "12/08/2021");

		st1 = new SellTransaction(1, "SGD", "MYR", 100.00, "09/08/2021");
		st2 = new SellTransaction(2, "SGD", "MYR", 500.00, "12/08/2021");

		currencyList = new ArrayList<Currency>();
		currencyHoldingList = new ArrayList<currencyHolding>();
		currencyHoldingRate = new ArrayList<currencyHoldingRate>();
		buyTransactionList = new ArrayList<BuyTransaction>();
		sellTransactionList = new ArrayList<SellTransaction>();

	}

	@Test
	public void c206_test() {
		// fail("Not yet implemented");
		assertTrue("C206_CaseStudy_SampleTest ", true);
	}

	@Test
	public void retrieveAllCurrencyTest() {
		// Test if Item list is not null but empty -boundary
		assertNotNull("Test if there is valid Camcorder arraylist to retrieve item", currencyList);

		// test if the list of currency retrieved from the CurrencyList is empty -
		// boundary
		String allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);
		String testOutput = "";
		assertEquals("Check that ViewAllCurrencylist", testOutput, allCurrency);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		// - normal
		C206_CaseStudy.addCurrency(currencyList, c1);
		C206_CaseStudy.addCurrency(currencyList, c2);
		assertEquals("Test that Currency arraylist size is 2", 2, currencyList.size());

		// test if the expected output string same as the list of currency retrieved
		// from the SourceCentre
		allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);
		testOutput = String.format("%-20s %-20s %-10.2f %-12.2f \n", "SGD", "Singapore", 1.00, 3.00);
		testOutput += String.format("%-20s %-20s %-10.2f %-12.2f \n", "MYR", "Malaysia", 0.32, 3.13);

		assertEquals("Test that ViewAllCamcorderlist", testOutput, allCurrency);

	}

	@Test
	public void addCurrencyTest() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Camcorder arraylist to add to", currencyList);
		// Given an empty list, after adding 1 item, the size of the list is 1 - normal
		// The item just added is as same as the first item of the list
		C206_CaseStudy.addCurrency(currencyList, c1);
		assertEquals("Check that Camcorder arraylist size is 1", 1, currencyList.size());
		assertSame("Check that Camcorder is added", c1, currencyList.get(0));

		// Add another item. test The size of the list is 2? -normal
		// The item just added is as same as the second item of the list
		C206_CaseStudy.addCurrency(currencyList, c2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, currencyList.size());
		assertSame("Check that Camcorder is added", c2, currencyList.get(1));
	}

	@Test
	public void doDeleteCurrencyTest() {
		// boundary
		assertNotNull("test if there is valid Camcorder arraylist to loan from", currencyList);

		C206_CaseStudy.addCurrency(currencyList, c1);
		// normal
		Boolean ok = C206_CaseStudy.doDeleteCurrency(currencyList, "SGD");
		assertTrue("Test if an available item is ok to delete?", ok);
		// error condition
		ok = C206_CaseStudy.doDeleteCurrency(currencyList, "MYR");
		assertFalse("Test if an same item is NOT ok to delete again?", ok);
		// error condition
		C206_CaseStudy.addCurrency(currencyList, c2);
		c2.setIsAvailable(false);
		ok = C206_CaseStudy.doDeleteCurrency(currencyList, "SGD");
		assertFalse("Test that un-available item is NOT ok to delete?", ok);
		// error condition
		ok = C206_CaseStudy.doDeleteCurrency(currencyList, "MYR");
		assertFalse("Test that non-esiting item is NOT ok to delete?", ok);

	}

	@Test
	public void doUpdateCurrencyTest() {
		// boundary
		assertNotNull("Test if there is valid Currency arraylist to add to", currencyList);
		C206_CaseStudy.addCurrency(currencyList, c1);
		// error
		Boolean isUpdated = C206_CaseStudy.doUpdateCurrency(currencyList, "SGD");
		assertFalse("Test if available currency SGD is updated -false?", isUpdated);
		// normal
		C206_CaseStudy.addCurrency(currencyList, c1);
		c1.setIsAvailable(false);
		isUpdated = C206_CaseStudy.doUpdateCurrency(currencyList, "SGD");
		assertTrue("Test if currency SGD is updated- true", isUpdated);
		// error
		isUpdated = C206_CaseStudy.doUpdateCurrency(currencyList, "MYR");
		assertFalse("Test if non-existing currency MYR is updated - false?", isUpdated);

	}

	@Test
	public void retrieveAllCurrencyHoldingTest() {
		// Test if Item list is not null but empty -boundary
		assertNotNull("Test if there is valid currencyHolding arraylist to retrieve item", currencyHoldingList);

		// test if the list of currency retrieved from the CurrencyList is empty -
		// boundary
		String allCurrencyHolding = C206_CaseStudy.retrieveAllCurrencyHolding(currencyHoldingList);
		String testOutput = "";
		assertEquals("Check that ViewAllCurrencyHoldinglist", testOutput, allCurrencyHolding);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		// - normal
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h1);
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h2);
		assertEquals("Test that Currency Holding arraylist size is 2", 2, currencyHoldingList.size());

		// test if the expected output string same as the list of currency retrieved
		// from the SourceCentre
		allCurrencyHolding = C206_CaseStudy.retrieveAllCurrencyHolding(currencyHoldingList);
		testOutput += String.format("%-20s %-12.2f \n", "SGD", 1.00);
		testOutput += String.format("%-20s %-12.2f \n", "MYR", 3.00);

		assertEquals("Test that ViewAllCamcorderlist", testOutput, allCurrencyHolding);

	}

	@Test
	public void addCurrencyHoldingTest() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Camcorder arraylist to add to", currencyList);
		// Given an empty list, after adding 1 item, the size of the list is 1 - normal
		// The item just added is as same as the first item of the list
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h1);
		assertEquals("Check that Camcorder arraylist size is 1", 1, currencyHoldingList.size());
		assertSame("Check that Camcorder is added", h1, currencyHoldingList.get(0));

		// Add another item. test The size of the list is 2? -normal
		// The item just added is as same as the second item of the list
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, currencyHoldingList.size());
		assertSame("Check that Camcorder is added", h2, currencyHoldingList.get(1));
	}

	@Test
	public void doDeleteCurrencyHoldingTest() {
		// boundary
		assertNotNull("test if there is valid Camcorder arraylist to loan from", currencyList);

		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h1);
		// normal
		Boolean ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "SGD");
		assertTrue("Test if an available item is ok to delete?", ok);
		// error condition
		ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "MYR");
		assertFalse("Test if an same item is NOT ok to delete again?", ok);
		// error condition
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h2);
		h2.setIsAvailable(false);
		ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "SGD");
		assertFalse("Test that un-available item is NOT ok to delete?", ok);
		// error condition
		ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "MYR");
		assertFalse("Test that non-esiting item is NOT ok to delete?", ok);

	}

	@Test
	public void retrieveAllcurrencyHoldingRate() {
		// Test if Item list is not null but empty -boundary
		assertNotNull("Test if there is valid Camcorder arraylist to retrieve item", currencyHoldingRate);

		// test if the list of currency retrieved from the CurrencyList is empty -
		// boundary
		String allCurrency = C206_CaseStudy.retrieveAllcurrencyHoldingRate(currencyHoldingRate);
		String testOutput = "";
		assertEquals("Check that ViewAllCurrencylist", testOutput, allCurrency);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		// - normal
		C206_CaseStudy.addCurrencyHoldingRate(currencyHoldingRate, chr1);
		C206_CaseStudy.addCurrencyHoldingRate(currencyHoldingRate, chr2);
		assertEquals("Test that Currency arraylist size is 2", 2, currencyHoldingRate.size());

		// test if the expected output string same as the list of currency retrieved
		// from the SourceCentre
		allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);

		assertEquals("Test that ViewAllCamcorderlist", testOutput, allCurrency);
	}

	@Test
	public void dosearchCurrencyRate() {
		// Test if Item list is not null -boundary

		assertNotNull("Test if there is valid Currency arraylist to retrieve keyword", currencyList);

	}

	@Test
	public void doconvertCurrencySell() {
		assertNotNull("Test if there is valid Currency arraylist to retrieve keyword", currencyList);
	}

	@Test
	public void doconvertCurrencyBuy() {
		assertNotNull("Test if there is valid Currency arraylist to retrieve keyword", currencyList);
	}

	@Test
	public void addBuyingTransactionTest() {
		// Transaction list is not null, so that can add a new transaction record -
		// boundary
		assertNotNull("Check if there is valid buy transaction arraylist to add to", buyTransactionList);

		// Given an empty list, after adding 1 transaction, the size of the list is 1 -
		// normal
		C206_CaseStudy.addBuyingTransaction(buyTransactionList, bt1);
		assertEquals("Check that buy transaction arraylist size is 1", 1, buyTransactionList.size());
		// The transaction record just added is as same as the first transaction of the
		// list
		assertSame("Check that buy transaction records is added", bt1, buyTransactionList.get(0));

		// Add another transaction. test The size of the list is 2? -normal
		C206_CaseStudy.addBuyingTransaction(buyTransactionList, bt2);
		assertEquals("Check that BuyTransaction arraylist size is 2", 2, buyTransactionList.size());
		// The transaction just added is as same as the second transaction of the list
		assertSame("Check that BuyTransaction is added", bt2, buyTransactionList.get(1));
	}

	@Test
	public void addSellingTransactionTest() {
		// Transaction list is not null, so that can add a new transaction - boundary
		assertNotNull("Check if there is valid sell transaction arraylist to add to", sellTransactionList);
		// Given an empty list, after adding 1 transaction, the size of the list is 1 -
		// normal
		C206_CaseStudy.addSellingTransaction(sellTransactionList, st1);
		assertEquals("Check that sell transaction arraylist size is 1", 1, sellTransactionList.size());
		// The transaction just added is as same as the first transaction of the list
		assertSame("Check that sell transaction record is added", st1, sellTransactionList.get(0));

		// Add another transaction. test The size of the list is 2? -normal
		C206_CaseStudy.addSellingTransaction(sellTransactionList, st2);
		assertEquals("Check that SellTransaction arraylist size is 2", 2, sellTransactionList.size());
		// The transaction just added is as same as the second transaction of the list
		assertSame("Check that SellTransaction is added", st2, sellTransactionList.get(1));
	}

	@Test
	public void retrieveAllBuyTransaction() {
		// Test if Transaction list is not null but empty -boundary
		assertNotNull("Test if there is valid Buy Transaction arraylist to retrieve transaction record",
				buyTransactionList);

		// test if the list of currency retrieved from the buyTransactionList is empty -
		// boundary
		String allBuyTransaction = C206_CaseStudy.retrieveAllBuyTransaction(buyTransactionList);
		String testOutput1 = "";
		assertEquals("Check that ViewAllBuyingTransaction", testOutput1, allBuyTransaction);

		// Given an empty list, after adding 2 transactions, test if the size of the
		// list is 2
		// - normal
		C206_CaseStudy.addBuyingTransaction(buyTransactionList, bt1);
		C206_CaseStudy.addBuyingTransaction(buyTransactionList, bt2);
		assertEquals("Test that Buy Transaction arraylist size is 2", 2, buyTransactionList.size());

		// test if the expected output string is same as the list of Buy Transactions
		// retrieved from the Money Exchange System
		allBuyTransaction = C206_CaseStudy.retrieveAllBuyTransaction(buyTransactionList);
		testOutput1 = String.format("%-10d %-10s %-10s %-15.2f %-10s \n", 1, "MYR", "SGD", 100.00, "09/08/2021");
		testOutput1 += String.format("%-10d %-10s %-10s %-15.2f %-10s \n", 2, "MYR", "SGD", 500.00, "12/08/2021");

		assertEquals("Test that ViewAllBuyingTransaction", testOutput1, allBuyTransaction);

	}

	@After
	public void tearDown() throws Exception {
		c1 = null;
		c2 = null;
		h1 = null;
		h2 = null;
		chr1 = null;
		chr2 = null;
		bt1 = null;
		bt2 = null;

		st1 = null;
		st2 = null;

		currencyList = null;
		currencyHoldingList = null;
		currencyHoldingRate = null;
		buyTransactionList = null;
		sellTransactionList = null;

	}

}