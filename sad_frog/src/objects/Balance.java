package objects;

public class Balance {
	private double amount;
	private long lastWorked;
	//create getters and setters for ^
	
	
	public Balance() {
		amount = 0;
		lastWorked = 0;
		
	}
	
	public void subtractLateFees(long timeOverdue){
		if(timeOverdue < 0)
			amount += 1 * timeOverdue / 1000;
	}
	
	
	public boolean canWork(long time) {
		if (time - lastWorked > 10000) return true;
		return false;
	}
	
	public String earnMoney(long time) {
		if(canWork(time)){ 
			this.amount += 5;
			this.lastWorked = time;
			return "Work done, earned $5!";
		}	
		else return "Can't do double shift, wait untill the first job is done!";
		
	}

	public double getAmount() {
		return Math.round(amount * 100.0) / 100.0;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getLastWorked() {
		return lastWorked;
	}

	public void setLastWorked(long lastWorked) {
		this.lastWorked = lastWorked;
	}
}