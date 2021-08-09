
public class BuyTransaction {
	private String date;
	private String iso_out;
	private String iso;
	private double amount_out;
	private int id;

	public BuyTransaction(int id, String iso, String iso_out, double amount_out, String date) {
		super();
		this.id = id;
		this.date = date;
		this.iso_out = iso_out;
		this.iso = iso;
		this.amount_out = amount_out;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIso_out() {
		return iso_out;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public double getAmount_out() {
		return amount_out;
	}

	public void setAmount_out(double amount_out) {
		this.amount_out = amount_out;
	}

}
