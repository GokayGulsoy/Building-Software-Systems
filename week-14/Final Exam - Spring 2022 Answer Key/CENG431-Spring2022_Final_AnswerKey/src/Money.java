
public class Money {
	
	private double value;
	
	public Money() {
		value = 0.0;
	}
	
	public Money(double value) {
		this. value = value;
	}

	public double getValue() {
		return value;
	}
	
	public Money add(Money addend) {
		double newValue = this.value + addend.getValue();
		return new Money(newValue);
	}
	
	public Money minus(Money subtrahend) {
		double newValue = this.value - subtrahend.getValue();
		return new Money(newValue);
	}
	
	public Money times(int quantity) {
		double newValue = 0;
		newValue += (this.value * quantity);
		return new Money(newValue);
	}
	



	
}
