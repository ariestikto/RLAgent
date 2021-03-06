/**
 * 
 */
package userSimulation;

/**
 * @author pa1g15
 *
 */
public class Car {
	private int type;
	private double consumption; // kWh/mile
	private double batteryCapacity;
	private String carName;
	
	public Car(int type) {
		this.setType(type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.consumption = 0;
			this.batteryCapacity = 0;
			this.carName = "Non EV User";
			break;
		case 1:
			this.consumption = 0.207;
			this.batteryCapacity = 18.8;
			this.carName = "BMW i3";
			break;
		
		case 2:
			this.consumption = 0.286;
			this.batteryCapacity = 90;
			this.carName = "Tesla Model S (P90D)";
			break;
			
		case 3:
			this.consumption = 0.188;
			this.batteryCapacity = 18.7;
			this.carName = "Volkswagen e-up!";
			break;
			
		case 4:
			this.consumption = 0.193; 
			this.batteryCapacity = 30;
			this.carName = "Nissan Leaf (30 kWh Acenta)";
			break;
			
		case 5:
			this.consumption = 0.204; 
			this.batteryCapacity = 24.2;
			this.carName = "Volkswagen e-golf";
			break;
			
		case 6:
			this.consumption = 0.204; 
			this.batteryCapacity = 27;
			this.carName = "KIA Soul EV";
			break;
			
		case 7:
			this.consumption = 0.146; 
			this.batteryCapacity = 22;
			this.carName = "Renault ZOE (Dynamique Nav)";
			break;
		}
	}

	public double getConsumption() {
		return consumption;
	}

	public double getBatteryCapacity() {
		return batteryCapacity;
	}

	public String getCarName() {
		return carName;
	}	
}
