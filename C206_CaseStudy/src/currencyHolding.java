
public class currencyHolding {

	private String currencyISO;
	private double amtofHoldings;
	private boolean isAvailable;
	private String name;
	private double buyRate;
	private double sellRate;
	
	public currencyHolding(String currencyISO, double amtofHoldings) {
		
		this.currencyISO = currencyISO;
		this.amtofHoldings = amtofHoldings;
		this.isAvailable = true;
		this.name = name;
		this.buyRate = buyRate;
		this.sellRate = sellRate;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(double buyRate) {
		this.buyRate = buyRate;
	}

	public double getSellRate() {
		return sellRate;
	}

	public void setSellRate(double sellRate) {
		this.sellRate = sellRate;
	}

}
