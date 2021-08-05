
public class currencyHolding {

	private String currencyISO;
	private double amtofHoldings;
	private boolean isAvailable;
	
	public currencyHolding(String currencyISO, double amtofHoldings) {
		
		this.currencyISO = currencyISO;
		this.amtofHoldings = amtofHoldings;
		this.isAvailable = true;
	}

	public String getCurrencyISO() {
		return currencyISO;
	}

	public void setCurrencyISO(String currencyISO) {
		this.currencyISO = currencyISO;
	}

	public double getAmtofHoldings() {
		return amtofHoldings;
	}

	public void setAmtofHoldings(double amtofHoldings) {
		this.amtofHoldings = amtofHoldings;
	}
	
	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
