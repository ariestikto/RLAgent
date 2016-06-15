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
	private double unit_price;
	/**
	 * 
	 */
	public ElectricityBundle() {
		// TODO Auto-generated constructor stub
		this.amount = 0;
		this.unit_price = 0;
	}
	public ElectricityBundle(double amount, double unit_price) {
		// TODO Auto-generated constructor stub
		this.amount = amount;
		this.unit_price = unit_price;
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
	 * @return the unit_price
	 */
	public double getUnitPrice() {
		return unit_price;
	}
	/**
	 * @param price the price to set
	 */
	public void setUnitPrice(double unit_price) {
		this.unit_price = unit_price;
	}
}
