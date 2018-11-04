package cs3220.model;

public class CurrencyRate {

	String name;
	double rate;
	public CurrencyRate(String name, double rate) {
		super();
		this.name = name;
		this.rate = rate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
