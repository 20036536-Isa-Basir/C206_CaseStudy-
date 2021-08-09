import java.util.ArrayList;

public class C206_CaseStudy {

	private static final int OPTION_QUIT = 15;
	private static final BuyTransaction BuyTransaction = null;
	private static final SellTransaction SellTransaction = null;

	public static void main(String[] args) {

		ArrayList<Currency> currencyList = new ArrayList<Currency>();
		ArrayList<currencyHolding> currencyHoldingList = new ArrayList<currencyHolding>();
		ArrayList<currencyHoldingRate> currencyHoldingRate = new ArrayList<currencyHoldingRate>();
		ArrayList<BuyTransaction> buyTransactionList = new ArrayList<BuyTransaction>();
		ArrayList<SellTransaction> sellTransactionList = new ArrayList<SellTransaction>();

		currencyList.add(new Currency("SGD", "Singapore", 1.00, 3.00));
		currencyList.add(new Currency("MYR", "Malaysia", 0.32, 3.13));
		currencyList.add(new Currency("USD", "United States Dollar", 1.36, 1.34));
		currencyList.add(new Currency("JPY", "Japanese Yen", 1.25, 1.22));

		currencyHoldingList.add(new currencyHolding("Singapore", 200000.00));
		currencyHoldingList.add(new currencyHolding("Malaysia", 100000.00));
		currencyHoldingList.add(new currencyHolding("United States Dollar", 100000.00));
		currencyHoldingList.add(new currencyHolding("Japanese Yen", 100000.00));

		currencyHoldingRate.add(new currencyHoldingRate("SGD", 100000.00, "Singapore", 1.00, 3.00));
		currencyHoldingRate.add(new currencyHoldingRate("MYR", 100000.00, "Malaysia", 0.32, 3.13));
		currencyHoldingRate.add(new currencyHoldingRate("USD", 100000.00, "United States Dollar", 0.32, 3.13));
		currencyHoldingRate.add(new currencyHoldingRate("JPY", 100000.00, "Japanese Yen", 0.32, 3.13));

		buyTransactionList.add(new BuyTransaction(1, "MYR", "SGD", 100.00, "09/08/2021"));
		buyTransactionList.add(new BuyTransaction(2, "MYR", "SGD", 500.00, "12/08/2021"));

//		buyTransactionList.add(new BuyTransaction(1, "09/08/2021", "buy", "SGD", 90));

		sellTransactionList.add(new SellTransaction(1, "SGD", "MYR", 300.00, "09/08/2021"));
		sellTransactionList.add(new SellTransaction(2, "SGD", "MYR", 500.00, "12/08/2021"));

		int option = 0;

		while (option != OPTION_QUIT) {

			C206_CaseStudy.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == 1) {
				// View all Currency
				C206_CaseStudy.viewAllCurrency(currencyList);

			} else if (option == 2) {
				// Add a new Currency
				C206_CaseStudy.setHeader("ADD");

			} else if (option == 3) {
				// Delete Currency
				C206_CaseStudy.setHeader("DELETE");

			} else if (option == 4) {
				// Update Currency
				C206_CaseStudy.setHeader("UPDATE");

			} else if (option == 5) {
				// View all currency holdings
				C206_CaseStudy.viewAllCurrencyHolding(currencyHoldingList);

			} else if (option == 6) {
				C206_CaseStudy.setHeader("ADD");

			} else if (option == 7) {
				C206_CaseStudy.setHeader("DELETE");
			} else if (option == 8) {
				C206_CaseStudy.viewAllcurrencyHoldingRate(currencyHoldingRate);

				C206_CaseStudy.setHeader("SEARCH");
			} else if (option == 9) {
				// trygve search
			} else if (option == 10) {
				C206_CaseStudy.searchCurrencyRate(currencyList);
			} else if (option == 11) {
				C206_CaseStudy.setHeader("Currency Converter");
				itemTypeMenu();

				int itemType = Helper.readInt("Enter option to select item type > ");
				if (itemType == 1) {
					// Sell Option
					C206_CaseStudy.ConvertCurrencySell(currencyList);

				} else if (itemType == 2) {
					// Buy Option
					C206_CaseStudy.ConvertCurrencyBuy(currencyList);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 12) {
				C206_CaseStudy.setHeader("ADD BUY TRANSACTION RECORD");
				BuyTransaction bt = inputBuy(BuyTransaction, currencyList, currencyHoldingList);
				C206_CaseStudy.addBuyingTransaction(buyTransactionList, bt);

			} else if (option == 13) {

				C206_CaseStudy.setHeader("ADD SELL TRANSACTION RECORD");
				SellTransaction st = inputSell(SellTransaction, currencyList, currencyHoldingList);
				C206_CaseStudy.addSellingTransaction(sellTransactionList, st);

			} else if (option == 14) {

				C206_CaseStudy.viewAllBuyTransaction(buyTransactionList);

			}

			else if (option == OPTION_QUIT) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}

	public static void itemTypeMenu() {

		C206_CaseStudy.setHeader("ITEM TYPES");
		System.out.println("1. SELL Currency");
		System.out.println("2. BUY Currency");
		System.out.println(" ");
	}

	public static void menu() {
		C206_CaseStudy.setHeader("Currency APP");
		System.out.println("1. Display Currency");
		System.out.println("2. Add Currency");
		System.out.println("3. Delete Currency");
		System.out.println("4. Update Currency");
		System.out.println("5. Display Currency Holdings");
		System.out.println("6. Add Currency Holdings");
		System.out.println("7. Delete Currency Holdings");
		System.out.println("8. Display the Currency Holdings converted SGD value based on SELL RATE");
		System.out.println("9. Search for the holding of a currency and its SGD value.");
		System.out.println("10. Search for Buy / Sell Rate of a currency.");
		System.out.println("11. Currency Converter Calculator");
		System.out.println("12. Add BUY transaction");
		System.out.println("13. Add SELL transaction");
		System.out.println("14. View BUY transaction");

		System.out.println("15. Quit");
		Helper.line(80, "-");

	}

	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} else {
			avail = "No";
		}
		return avail;
	}

	// ================================= Option 1 View (CRUD - Read)
	// =================================
	public static String retrieveAllCurrency(ArrayList<Currency> currencyList) {
		String output = "";

		for (int i = 0; i < currencyList.size(); i++) {

			output += String.format("%-20s %-20s %-10.2f %-12.2f \n", currencyList.get(i).getIso(),
					currencyList.get(i).getName(), currencyList.get(i).getBuyRate(), currencyList.get(i).getSellRate());
		}
		return output;
	}

	public static void viewAllCurrency(ArrayList<Currency> currencyList) {
		C206_CaseStudy.setHeader("Currency LIST");
		String output = String.format("%-20s %-20s %-10s %-12s \n", "CURRENCY ISO", "NAME", "BUY RATE", "SELL RATE");
		output += retrieveAllCurrency(currencyList);
		System.out.println(output);
	}

	// ================================= Option 2 Add (CRUD -
	// Create)=================================

	public static Currency inputCurrency() {
		String iso = Helper.readString("Enter Currency ISO > ");
		String name = Helper.readString("Enter Currency Name > ");
		double buy = Helper.readDouble("Enter Buy Rate > ");
		double sell = Helper.readDouble("Enter Sell Rate > ");

		Currency c = new Currency(iso, name, buy, sell);
		return c;

	}

	public static void addCurrency(ArrayList<Currency> currencyList, Currency c) {

		currencyList.add(c);
		System.out.println("Currency added");
	}

	// ================================= Option 3 Delete (CURD- Delete)
	// =================================
	public static boolean doDeleteCurrency(ArrayList<Currency> currencyList, String iso) {

		boolean isDelete = false;

		for (int i = 0; i < currencyList.size(); i++) {

			String currencyIso = currencyList.get(i).getIso();

			if (iso.equalsIgnoreCase(iso) && currencyList.get(i).getIsAvailable() == true) {

				currencyList.get(i).setIsAvailable(false);

				isDelete = true;

			}
		}
		return isDelete;
	}

	public static void DeleteCurrency(ArrayList<Currency> currencyList) {
		C206_CaseStudy.viewAllCurrency(currencyList);
		String iso = Helper.readString("Enter Currency Iso > ");

		Boolean isDeleted = doDeleteCurrency(currencyList, iso);
		if (isDeleted == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Currency " + iso + " is deleted");
		}
	}

	// ================================= Option 4 Update (CURD-
	// Update)=================================
	public static boolean doUpdateCurrency(ArrayList<Currency> currencyList, String iso) {
		boolean isUpdated = false;

		for (int i = 0; i < currencyList.size(); i++) {
			String currencyIso = currencyList.get(i).getIso();
			if (iso.equalsIgnoreCase(currencyIso) && currencyList.get(i).getIsAvailable() == false) {
				currencyList.get(i).setIsAvailable(true);
				currencyList.get(i).setIso("");
				currencyList.get(i).setName("");
				// currencyList.get(i).setBuyRate();
				// currencyList.get(i).setSellRate();
				isUpdated = true;

			}
		}
		return isUpdated;

	}

	public static void updateCurrency(ArrayList<Currency> currencyList) {
		C206_CaseStudy.viewAllCurrency(currencyList);
		String currencyIso = Helper.readString("Enter Currency Iso > ");
		Boolean isUpdated = doUpdateCurrency(currencyList, currencyIso);

		if (isUpdated == false) {
			System.out.println("Invalid Currency Iso");
		} else {
			System.out.println("Currency " + currencyIso + " updated");
		}
	}

	public static String retrieveAllCurrencyHolding(ArrayList<currencyHolding> currencyHoldingList) {
		String output = "";

		for (int i = 0; i < currencyHoldingList.size(); i++) {

			output += String.format("%-20s %-12.2f \n", currencyHoldingList.get(i).getCurrencyISO(),
					currencyHoldingList.get(i).getAmtofHoldings());
		}
		return output;
	}

	public static void viewAllCurrencyHolding(ArrayList<currencyHolding> currencyHoldingList) {
		C206_CaseStudy.setHeader("Currency Holding LIST");
		String output = String.format("%-20s %-12s \n", "ISO", "HOLDINGS AMOUNT");
		output += retrieveAllCurrencyHolding(currencyHoldingList);
		System.out.println(output);
	}

	public static currencyHolding inputCurrencyHolding() {
		String ISO = Helper.readString("Enter Currency ISO > ");
		double amtofHoldings = Helper.readDouble("Enter Amount of Holdings > ");

		currencyHolding ch = new currencyHolding(ISO, amtofHoldings);
		return ch;

	}

	public static currencyHoldingRate inputcurrencyHoldingRate() {
		String ISO = Helper.readString("Enter Currency ISO > ");
		double amtofHoldings = Helper.readDouble("Enter Amount of Holdings > ");
		String name = Helper.readString("Enter Currency Name > ");
		double buy = Helper.readDouble("Enter Buy Rate > ");
		double sell = Helper.readDouble("Enter Sell Rate > ");

		currencyHoldingRate chr = new currencyHoldingRate(ISO, amtofHoldings, name, buy, sell);
		return chr;

	}

	public static void addCurrencyHolding(ArrayList<currencyHolding> currencyHoldingList, currencyHolding ch) {

		currencyHoldingList.add(ch);
		System.out.println("Currency Holdings added");

	}

	public static void addCurrencyHoldingRate(ArrayList<currencyHoldingRate> currencyHoldingRate,
			currencyHoldingRate chr) {

		currencyHoldingRate.add(chr);
		System.out.println("Currency Holdings added");

	}

	public static boolean doDeleteCurrencyHolding(ArrayList<currencyHolding> currencyHoldingList, String iso) {

		boolean isDelete = false;

		for (int i = 0; i < currencyHoldingList.size(); i++) {

			String currencyIso = currencyHoldingList.get(i).getCurrencyISO();

			if (iso.equalsIgnoreCase(iso) && currencyHoldingList.get(i).getIsAvailable() == true) {

				currencyHoldingList.get(i).setIsAvailable(false);

				isDelete = true;

			}
		}
		return isDelete;
	}

	public static void DeleteCurrencyHolding(ArrayList<currencyHolding> currencyHoldingList) {
		C206_CaseStudy.viewAllCurrencyHolding(currencyHoldingList);
		String iso = Helper.readString("Enter Currency Iso > ");

		Boolean isDeleted = doDeleteCurrencyHolding(currencyHoldingList, iso);
		if (isDeleted == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Currency " + iso + " is deleted");
		}
	}
	// View how much money company is currently holding in various currencies &
	// display its converted SGD value based on SELL RATE

	public static String retrieveAllcurrencyHoldingRate(ArrayList<currencyHoldingRate> currencyHoldingRate) {
		String output = "";

		for (int i = 0; i < currencyHoldingRate.size(); i++) {

			output += String.format("%-20s %-20.2f %-12.2f \n", currencyHoldingRate.get(i).getCurrencyISO(),
					currencyHoldingRate.get(i).getAmtofHoldings(), currencyHoldingRate.get(i).getSellRate());
		}
		return output;
	}

	public static void viewAllcurrencyHoldingRate(ArrayList<currencyHoldingRate> currencyHoldingRate) {
		C206_CaseStudy.setHeader("Holding List with Sell Rates");
		String output = String.format("%-20s %-20s %-12s \n", "CURRENCY ISO", "HOLDING RATE", "SELL RATE");
		output += retrieveAllcurrencyHoldingRate(currencyHoldingRate);
		System.out.println(output);
	}

	public static String inputSearchCurrency() {
		String searchCountry = Helper.readString("Enter country ISO (e.g. USD) > ");
		return searchCountry;
	}

	public static void searchCurrency(ArrayList<Currency> currencyList, String searchCountry) {

		for (int i = 0; i < currencyList.size(); i++) {

			if (currencyList.get(i) != null && currencyList.get(i).getIso().equalsIgnoreCase(searchCountry)) {
				double buyRate = currencyList.get(i).getBuyRate();
				double sellRate = currencyList.get(i).getSellRate();

				System.out.println("The BUY rate of this currency is " + buyRate);
				System.out.println("The SELL rate of this currency is " + sellRate);
			}
		}

	}

	// ================================== Option 10 Search Rate
	// =====================================================
	public static boolean dosearchCurrencyRate(ArrayList<Currency> currencyList, String searchStr) {

		boolean isFound = false;
		if (!currencyList.isEmpty()) {

			String output = String.format("%-20s %-20s %-10s %-12s \n", "CURRENCY ISO", "NAME", "BUY RATE",
					"SELL RATE");

			for (int i = 0; i < currencyList.size(); i++) {

				if (currencyList.get(i).getIso().equalsIgnoreCase(searchStr)) {

					output += String.format("%-20s %-20s %-10.2f %-12.2f \n", currencyList.get(i).getIso(),
							currencyList.get(i).getName(), currencyList.get(i).getBuyRate(),
							currencyList.get(i).getSellRate());

					isFound = true;

				}

			}
			System.out.println(output);

		}
		return isFound;

	}

	public static void searchCurrencyRate(ArrayList<Currency> currencyList) {

		String searchStr = Helper.readString("Enter Currency ISO > ");

		boolean isFound = dosearchCurrencyRate(currencyList, searchStr);

		if (isFound == false) {
			System.out.println("There is no currency containing this keyword > " + searchStr);

		}

	}

	// ================================== Option 11 Calculator
	// =====================================================

	public static boolean doconvertCurrencySell(ArrayList<Currency> currencyList, String searchStr, double sellAmount) {
		boolean isFound = false;

		String output = String.format("%-20s%-15s%-15s%-15s%-15s\n", "CURRENCY ISO", "NAME", "SELL RATE", "Sell Amount",
				"Converted Amount");

		for (int i = 0; i < currencyList.size(); i++) {

			if (currencyList.get(i).getIso().equalsIgnoreCase(searchStr)) {

				double Conversion = currencyList.get(i).getSellRate() * sellAmount;

				output += String.format("%-20s%-15s%-15.2f%-15.2f%-15.2f\n", currencyList.get(i).getIso(),
						currencyList.get(i).getName(), currencyList.get(i).getSellRate(), sellAmount, Conversion);

				isFound = true;
			}

		}
		System.out.println(output);

		return isFound;

	}

	public static void ConvertCurrencySell(ArrayList<Currency> currencyList) {

		String searchStr = Helper.readString("Enter Currency ISO > ");
		double sellAmount = Helper.readDouble("Enter Sell Amount > ");

		boolean isFound = doconvertCurrencySell(currencyList, searchStr, sellAmount);

		if (isFound == false) {
			System.out.println("There is no currency containing this keyword > " + searchStr);

		}

	}

	public static boolean doconvertCurrencyBuy(ArrayList<Currency> currencyList, String searchStr, double BuyAmount) {
		boolean isFound = false;

		String output = String.format("%-20s%-15s%-15s%-15s%-15s\n", "CURRENCY ISO", "NAME", "Buy Rate", "Buy Amount",
				"Converted Amount");

		for (int i = 0; i < currencyList.size(); i++) {

			if (currencyList.get(i).getIso().equalsIgnoreCase(searchStr)) {

				double Conversion = currencyList.get(i).getBuyRate() * BuyAmount;

				output += String.format("%-20s%-15s%-15.2f%-15.2f%-15.2f\n", currencyList.get(i).getIso(),
						currencyList.get(i).getName(), currencyList.get(i).getBuyRate(), BuyAmount, Conversion);

				isFound = true;
			}

		}
		System.out.println(output);

		return isFound;

	}

	public static void ConvertCurrencyBuy(ArrayList<Currency> currencyList) {

		String searchStr = Helper.readString("Enter Currency ISO > ");
		double BuyAmount = Helper.readDouble("Enter Buy Amount > ");

		boolean isFound = doconvertCurrencyBuy(currencyList, searchStr, BuyAmount);

		if (isFound == false) {
			System.out.println("There is no currency containing this keyword > " + searchStr);

		}

	}

	// option 12 Record Buy Transaction
	public static BuyTransaction inputBuy(BuyTransaction bt, ArrayList<Currency> currencyList,
			ArrayList<currencyHolding> HoldingList) {

		String iso = Helper.readString("Enter foreign currency iso to convert > ");
		while (iso.equals("SGD") || iso.equals("sgd")) {
			System.out.println("ERROR! " + iso + " is a local currency. Please enter a valid foreign currency");

			String iso1 = Helper.readString("Enter currency iso to convert >  ");
			iso = iso1;
		}

		for (int i = 0; i < currencyList.size(); i++) {
			if (currencyList.get(i) != null && currencyList.get(i).getIso().equals(iso.toUpperCase())) {

				int id = Helper.readInt("Enter a new transaction ID to add into records > ");

				while (id == 1 || id == 2) {
					System.out.println("ERROR! ID already exists enter a new transaction ID!");
					int id1 = Helper.readInt("Enter a new transaction ID to add into records > ");
					id = id1;
				}

				String date = Helper.readString("Enter date (dd/mm/yyyy) > ");

				String iso_out = "SGD";
				double amount = Helper.readDouble("Enter amount received > ");
				double conversion = currencyList.get(i).getBuyRate() * amount;
				System.out.println("Currency bought in " + iso.toUpperCase() + " is converted to SGD $"
						+ String.format("%.2f", conversion));
				double Holding = (HoldingList.get(i).getAmtofHoldings() + amount);

				System.out.println("Deducted holding From Singapore dollar To purchase " + iso.toUpperCase());
				System.out.println("Total holding amount of " + iso.toUpperCase() + " increased to: $" + Holding);
				HoldingList.get(i).setAmtofHoldings(Holding);

				bt = new BuyTransaction(id, iso.toUpperCase(), iso_out, conversion, date);

			}

		}
		return bt;

	}

	public static void addBuyingTransaction(ArrayList<BuyTransaction> buyTransactionList, BuyTransaction bt) {
		if (bt == null) {
			System.out.println(
					"Foreign currency ISO does not exist! Please update currency list to add the particular foreign currency!");

		} else {
			buyTransactionList.add(bt);
			System.out.println("New 'Buy' transaction added!");
		}
	}

	// option 13 Record Buy Transaction
	public static SellTransaction inputSell(SellTransaction st, ArrayList<Currency> currencyList,
			ArrayList<currencyHolding> HoldingList) {

		String iso = Helper.readString("Enter foreign currency iso to convert > ");
		while (iso.equals("SGD") || iso.equals("sgd")) {
			System.out.println("ERROR! " + iso + " is a local currency. Please enter a valid foreign currency");

			String iso1 = Helper.readString("Enter foreign currency iso to sell >  ");
			iso = iso1;
		}

		for (int i = 0; i < currencyList.size(); i++) {
			if (currencyList.get(i) != null && currencyList.get(i).getIso().equals(iso.toUpperCase())) {

				int id = Helper.readInt("Enter a new transaction ID to add into records > ");

				while (id == 1 || id == 2) {
					System.out.println("ERROR! ID already exists enter a new transaction ID!");
					int id1 = Helper.readInt("Enter a new transaction ID to add into records > ");
					id = id1;
				}

				String date = Helper.readString("Enter date (dd/mm/yyyy) > ");

				String iso_in = "SGD";
				double amount = Helper.readDouble("Enter amount received > ");
				double conversion = currencyList.get(i).getSellRate() * amount;
				System.out.println("Currency received in SGD is sold at " + iso.toUpperCase() + " $"
						+ String.format("%.2f", conversion));
				double Holding = (HoldingList.get(i).getAmtofHoldings() - amount);

				System.out.println("Deducted holdings From " + iso.toUpperCase() + " to purchase SGD");
				System.out.println("Total holding amount of " + iso.toUpperCase() + " decreased to: $" + Holding);
				HoldingList.get(i).setAmtofHoldings(Holding);

				st = new SellTransaction(id, iso.toUpperCase(), iso_in, conversion, date);

			}

		}
		return st;
	}

	public static void addSellingTransaction(ArrayList<SellTransaction> sellTransactionList, SellTransaction st) {
		if (st == null) {
			System.out.println(
					"Foreign currency ISO does not exist! Please update currency list to add the particular foreign currency!");

		} else {

			sellTransactionList.add(st);
			System.out.println("New 'Sell' transaction added!");
		}
	}

	public static String retrieveAllBuyTransaction(ArrayList<BuyTransaction> buyTransactionList) {
		String output = "";

		for (int i = 0; i < buyTransactionList.size(); i++) {

			output += String.format("%-10d %-10s %-10s %-15.2f %-10s \n", buyTransactionList.get(i).getId(),
					buyTransactionList.get(i).getIso(), buyTransactionList.get(i).getIso_out(),
					buyTransactionList.get(i).getAmount_out(), buyTransactionList.get(i).getDate());
		}
		return output;
	}

	
	// option 14 View Buy Transaction
	public static void viewAllBuyTransaction(ArrayList<BuyTransaction> buyTransactionList) {

		C206_CaseStudy.setHeader("ALL TRANSACTION RECORDS FOR 'BUY'");
		String output = String.format("%-10s %-10s %-10s %-15s %-10s \n", "ID", "CCY_IN", "CCY_OUT", "AMOUNT_OUT",
				"DATE");
		output += retrieveAllBuyTransaction(buyTransactionList);
		System.out.println(output);
	}

}
