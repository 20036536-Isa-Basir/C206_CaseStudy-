import java.util.ArrayList;

public class C206_CaseStudy {

	private static final int OPTION_QUIT = 12;

	public static void main(String[] args) {

		ArrayList<Currency> currencyList = new ArrayList<Currency>();
		ArrayList<currencyHolding> currencyHoldingList = new ArrayList<currencyHolding>();
		ArrayList<currencyHoldingRate> currencyHoldingRate = new ArrayList<currencyHoldingRate>();

		currencyList.add(new Currency("SGD", "Singapore", 1.00, 3.00));
		currencyList.add(new Currency("MYR", "Malaysia", 0.32, 3.12));

		currencyHoldingList.add(new currencyHolding("Singapore", 100000.00));
		currencyHoldingList.add(new currencyHolding("Malaysia", 100000.00));

		currencyHoldingRate.add(new currencyHoldingRate("SGD", 100000.00, "Singapore", 1.00, 3.00));
		currencyHoldingRate.add(new currencyHoldingRate("MYR", 100000.00, "Malaysia", 0.32, 3.13));

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

			} else if (option == 9) {

			} else if (option == 10) {
				C206_CaseStudy.searchCurrencyRate(currencyList);
			}
			else if(option == 11) {
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
		System.out.println("12. Quit");
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

	// ================================= Option 4 Update
	// (CURD-Update)=================================
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

//================================== Option 10 Search Rate =====================================================
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
	
	
	
	//================================== Option 11 Calculator =====================================================
	
	
	public static boolean doconvertCurrencySell (ArrayList<Currency>currencyList , String searchStr , double sellAmount) {
		boolean isFound = false;
		
		String output = String.format("%-20s%-15s%-15s%-15s%-15s\n", "CURRENCY ISO", "NAME", "SELL RATE",
				"Sell Amount" , "Converted Amount");
		
		for (int i = 0; i < currencyList.size(); i++) {

		if (currencyList.get(i).getIso().equalsIgnoreCase(searchStr)) {

			double Conversion = currencyList.get(i).getSellRate() * sellAmount;
		
	
			output += String.format("%-20s%-15s%-15.2f%-15.2f%-15.2f\n",currencyList.get(i).getIso(),
					currencyList.get(i).getName(), currencyList.get(i).getSellRate(),sellAmount,Conversion);
			
			
			
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

	public static boolean doconvertCurrencyBuy (ArrayList<Currency>currencyList , String searchStr , double BuyAmount) {
		boolean isFound = false;
		
		String output = String.format("%-20s%-15s%-15s%-15s%-15s\n", "CURRENCY ISO", "NAME", "Buy Rate",
				"Buy Amount" , "Converted Amount");
		
		for (int i = 0; i < currencyList.size(); i++) {

		if (currencyList.get(i).getIso().equalsIgnoreCase(searchStr)) {

			double Conversion = currencyList.get(i).getBuyRate() * BuyAmount;
		
	
			output += String.format("%-20s%-15s%-15.2f%-15.2f%-15.2f\n",currencyList.get(i).getIso(),
					currencyList.get(i).getName(), currencyList.get(i).getBuyRate(),BuyAmount,Conversion);
			
			
			
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






















}
