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
	
	private ArrayList<Currency> currencyList;
	private ArrayList<currencyHolding> currencyHoldingList;
	
	public C206_CaseStudyTest() {
		super();
	}
	

	@Before
	public void setUp() throws Exception {
		// prepare test data
		c1 = new Currency("SGD", "Singapore", 1.00, 3.00);
		c2 = new Currency("MYR", "Malaysia", 0.32, 3.13);
		h1 = new currencyHolding("SGD", 1);
		h2 = new currencyHolding("MYR",3);

		currencyList= new ArrayList<Currency>();
		currencyHoldingList = new ArrayList<currencyHolding>();
	}

	
	@Test
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
	}
	
	@Test
	public void retrieveAllCurrencyTest() {
		// Test if Item list is not null but empty -boundary
		assertNotNull("Test if there is valid Camcorder arraylist to retrieve item", currencyList);
		
		//test if the list of currency retrieved from the CurrencyList is empty - boundary
		String allCurrency= C206_CaseStudy.retrieveAllCurrency(currencyList);
		String testOutput = "";
		assertEquals("Check that ViewAllCurrencylist", testOutput, allCurrency);
		
		//Given an empty list, after adding 2 items, test if the size of the list is 2 - normal
		C206_CaseStudy.addCurrency(currencyList, c1);
		C206_CaseStudy.addCurrency(currencyList, c2);
		assertEquals("Test that Currency arraylist size is 2", 2, currencyList.size());
		
		//test if the expected output string same as the list of currency retrieved from the SourceCentre	
		allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);
		testOutput = String.format("%-20s %-20s %-10.2f %-12.2f \n", "SGD", "Singapore", 1.00, 3.00);
		testOutput += String.format("%-20s %-20s %-10.2f %-12.2f \n", "MYR", "Malaysia", 0.32, 3.13);
	
		assertEquals("Test that ViewAllCamcorderlist", testOutput, allCurrency);
		
	}
	
	@Test
	public void addCurrencyTest() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Camcorder arraylist to add to", currencyList);
		//Given an empty list, after adding 1 item, the size of the list is 1 - normal
		//The item just added is as same as the first item of the list
		C206_CaseStudy.addCurrency(currencyList, c1);
		assertEquals("Check that Camcorder arraylist size is 1", 1, currencyList.size());
		assertSame("Check that Camcorder is added", c1, currencyList.get(0));
		
		//Add another item. test The size of the list is 2? -normal
		//The item just added is as same as the second item of the list
		C206_CaseStudy.addCurrency(currencyList, c2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, currencyList.size());
		assertSame("Check that Camcorder is added", c2, currencyList.get(1));
	}
	
	@Test
	public void doDeleteCurrencyTest() {
		//boundary
		assertNotNull("test if there is valid Camcorder arraylist to loan from", currencyList);
		
		C206_CaseStudy.addCurrency(currencyList, c1);
		// normal
		Boolean ok = C206_CaseStudy.doDeleteCurrency(currencyList, "SGD");
		assertTrue("Test if an available item is ok to delete?", ok);
		//error condition
		ok = C206_CaseStudy.doDeleteCurrency(currencyList, "MYR");
		assertFalse("Test if an same item is NOT ok to delete again?", ok);	
		//error condition
		C206_CaseStudy.addCurrency(currencyList, c2);	
		c2.setIsAvailable(false);
		ok = C206_CaseStudy.doDeleteCurrency(currencyList, "SGD");
		assertFalse("Test that un-available item is NOT ok to delete?", ok);
		//error condition
		ok = C206_CaseStudy.doDeleteCurrency(currencyList, "MYR");
		assertFalse("Test that non-esiting item is NOT ok to delete?", ok);
		
	}
	
	@Test
	public void doUpdateCurrencyTest() {
		//boundary
		assertNotNull("Test if there is valid Currency arraylist to add to", currencyList);
		C206_CaseStudy.addCurrency(currencyList, c1);
		//error
		Boolean isUpdated = C206_CaseStudy.doUpdateCurrency(currencyList, "SGD");
		assertFalse("Test if available currency SGD is updated -false?", isUpdated);		
		//normal
		C206_CaseStudy.addCurrency(currencyList, c1);
		c1.setIsAvailable(false);
		isUpdated = C206_CaseStudy.doUpdateCurrency(currencyList, "SGD");
		assertTrue("Test if currency SGD is updated- true", isUpdated);
		//error
		isUpdated = C206_CaseStudy.doUpdateCurrency(currencyList, "MYR");
		assertFalse("Test if non-existing currency MYR is updated - false?", isUpdated);
		
	}
	@Test
	public void retrieveAllCurrencyHoldingTest() {
		// Test if Item list is not null but empty -boundary
		assertNotNull("Test if there is valid currencyHolding arraylist to retrieve item", currencyHoldingList);
		
		//test if the list of currency retrieved from the CurrencyList is empty - boundary
		String allCurrencyHolding= C206_CaseStudy.retrieveAllCurrencyHolding(currencyHoldingList);
		String testOutput = "";
		assertEquals("Check that ViewAllCurrencyHoldinglist", testOutput, allCurrencyHolding);
		
		//Given an empty list, after adding 2 items, test if the size of the list is 2 - normal
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h1);
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h2);
		assertEquals("Test that Currency Holding arraylist size is 2", 2, currencyHoldingList.size());
		
		//test if the expected output string same as the list of currency retrieved from the SourceCentre	
		allCurrencyHolding = C206_CaseStudy.retrieveAllCurrencyHolding(currencyHoldingList);
		testOutput += String.format("%-20s %-12.2f \n", "SGD", 1.00);
		testOutput += String.format("%-20s %-12.2f \n", "MYR", 3.00);
		
	
		assertEquals("Test that ViewAllCamcorderlist", testOutput, allCurrencyHolding);
		
	}
	
	@Test
	public void addCurrencyHoldingTest() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Camcorder arraylist to add to", currencyList);
		//Given an empty list, after adding 1 item, the size of the list is 1 - normal
		//The item just added is as same as the first item of the list
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h1);
		assertEquals("Check that Camcorder arraylist size is 1", 1, currencyHoldingList.size());
		assertSame("Check that Camcorder is added", h1, currencyHoldingList.get(0));
		
		//Add another item. test The size of the list is 2? -normal
		//The item just added is as same as the second item of the list
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, currencyHoldingList.size());
		assertSame("Check that Camcorder is added", h2, currencyHoldingList.get(1));
	}
	
	@Test
	public void doDeleteCurrencyHoldingTest() {
		//boundary
		assertNotNull("test if there is valid Camcorder arraylist to loan from", currencyList);
		
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h1);
		// normal
		Boolean ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "SGD");
		assertTrue("Test if an available item is ok to delete?", ok);
		//error condition
		ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "MYR");
		assertFalse("Test if an same item is NOT ok to delete again?", ok);	
		//error condition
		C206_CaseStudy.addCurrencyHolding(currencyHoldingList, h2);	
		h2.setIsAvailable(false);
		ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "SGD");
		assertFalse("Test that un-available item is NOT ok to delete?", ok);
		//error condition
		ok = C206_CaseStudy.doDeleteCurrencyHolding(currencyHoldingList, "MYR");
		assertFalse("Test that non-esiting item is NOT ok to delete?", ok);
		
	}
	
	
	@After
	public void tearDown() throws Exception {
		c1 = null;
		c2 = null;
		h1 = null;
		h2 = null;
		currencyList = null;
		currencyHoldingList = null;
	}

}