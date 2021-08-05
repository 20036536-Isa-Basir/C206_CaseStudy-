import java.util.ArrayList;


public class C206_CaseStudy {

	private static final int OPTION_QUIT = 12;
	public static void main(String[] args) {

		ArrayList<Currency> currencyList = new ArrayList<Currency>();
		
		currencyList.add(new Currency("SGD", "Singapore", 1.00, 3.00));
		currencyList.add(new Currency("MYR", "Malaysia", 0.32, 3.13));

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
				

			} else if (option == OPTION_QUIT) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}

	public static void menu() {
		C206_CaseStudy.setHeader("Currency APP");
		System.out.println("1. Display Currency");
		System.out.println("2. Add Currency");
		System.out.println("3. Delete Currency");
		System.out.println("4. Update Currency");
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
	
	//================================= Option 1 View (CRUD - Read) =================================
		public static String retrieveAllCurrency(ArrayList<Currency> currencyList) {
			String output = "";

			for (int i = 0; i < currencyList.size(); i++) {

				output += String.format("%-20s %-20s %-10.2f %-12.2f \n", currencyList.get(i).getIso(),
						currencyList.get(i).getName(), 
						currencyList.get(i).getBuyRate(),currencyList.get(i).getSellRate());
			}
			return output;
		}
		public static void viewAllCurrency(ArrayList<Currency> currencyList) {
			C206_CaseStudy.setHeader("Currency LIST");
			String output = String.format("%-20s %-20s %-10s %-12s \n", "CURRENCY ISO", "NAME",
					"BUY RATE", "SELL RATE");
			 output += retrieveAllCurrency(currencyList);	
			System.out.println(output);
		}


		//================================= Option 2 Add (CRUD - Create)=================================
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
		
		
		//================================= Option 3 Delete (CURD- Delete) =================================
		public static boolean doDeleteCurrency(ArrayList<Currency> currencyList, String iso) {
			
			boolean isDelete = false;

			for (int i = 0; i < currencyList.size(); i++) {
				
				String currencyIso = currencyList.get(i).getIso();
				
				if (iso.equalsIgnoreCase(iso)				
						&& currencyList.get(i).getIsAvailable() == true) {
					
					currencyList.get(i).setIsAvailable(false);
					
					isDelete = true;
					
				}
			}
			return isDelete;
		}

		public static void DeleteCurrency(ArrayList<Currency> currencyList) {
			C206_CaseStudy.viewAllCurrency(currencyList);
			String iso = Helper.readString("Enter Currency Iso > ");

			Boolean isDeleted =doDeleteCurrency(currencyList, iso);
			if (isDeleted == false) {
				System.out.println("Invalid asset tag");
			} else {
				System.out.println("Currency " + iso + " is deleted");
			}
		}
		

		
		//================================= Option 4 Update (CURD- Update)=================================
		public static boolean doUpdateCurrency(ArrayList<Currency> currencyList,String iso) {
			boolean isUpdated = false;

			for (int i = 0; i < currencyList.size(); i++) {
				String currencyIso = currencyList.get(i).getIso();
				if (iso.equalsIgnoreCase(currencyIso)
						&& currencyList.get(i).getIsAvailable() == false) {
					currencyList.get(i).setIsAvailable(true);
					currencyList.get(i).setIso("");
					currencyList.get(i).setName("");
					//currencyList.get(i).setBuyRate();
					//currencyList.get(i).setSellRate();
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
		

}
