/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * trygv, Aug 5, 2021 3:55:48 PM
 */

/**
 * @author trygve
 *
 */
public class currencyHoldingRate extends currencyHolding {
	

	private String name;
	private double buyRate;
	private double sellRate;
	
	


	
	public currencyHoldingRate(String currencyISO, double amtofHoldings, String name, double buyRate, double sellRate) {
		super(currencyISO, amtofHoldings);
		this.name = name;
		this.buyRate = buyRate;
		this.sellRate = sellRate;
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


