/**
 * 
 */
package userSimulation;

/**
 * @author ariestikto
 *
 */
public class ElectricityBundle {

	private double amount;
	private double price;
	/**
	 * 
	 */
	public ElectricityBundle(double amount, double price) {
		// TODO Auto-generated constructor stub
		this.amount = amount;
		this.price = price;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
