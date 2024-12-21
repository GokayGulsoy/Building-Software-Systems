package iyte.edu.tr.hw2;

public class UncontrolledJunctionApp {

	public static void main(String[] args) {
		VehicleGenerator vehicleGenerator = new VehicleGenerator();
		JunctionMediator mediator = vehicleGenerator.generateVehicles();

		mediator.mediateUncontrolledJunction();
	}

}
