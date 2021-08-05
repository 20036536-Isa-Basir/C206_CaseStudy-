public class Currency {
	
	private String iso;
	
	private String name;
	
	private double buyRate;
	
	private double sellRate;
	
	private boolean isAvailable;

	public Currency(String iso, String name, double buyRate, double sellRate) {
		this.iso = iso;
		this.name = name;
		this.buyRate = buyRate;
		this.sellRate = sellRate;
		this.isAvailable = true;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
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
	
	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}

