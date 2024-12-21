package iyte.edu.tr.hw2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class VehicleGenerator implements Generatable {

	@Override
	public JunctionMediator generateVehicles() {
		JunctionMediator junctionMediator = new JunctionMediator();
		Random rng = new Random();
		for (int i = 0; i < 20; i++) {
			Vehicle vehicle = new Vehicle();

			int randValue = rng.nextInt(1, 101);

			if (randValue >= 1 && randValue <= 35) {
				// Car generated
				vehicle.setType("Car");
				vehicle.setMotorType("Motor Vehicle");
				vehicle.setPriority(2);
			}

			else if (randValue >= 36 && randValue <= 45) {
				// Bus generated
				vehicle.setType("Bus");
				vehicle.setMotorType("Motor Vehicle");
				vehicle.setPriority(2);
			}

			else if (randValue >= 46 && randValue <= 55) {
				// Truck generated
				vehicle.setType("Truck");
				vehicle.setMotorType("Motor Vehicle");
				vehicle.setPriority(2);
			}

			else if (randValue >= 56 && randValue <= 70) {
				// Motorcycle generated
				vehicle.setType("Motorcycle");
				vehicle.setMotorType("Motor Vehicle");
				vehicle.setPriority(2);
			}

			else if (randValue >= 71 && randValue <= 80) {
				// Tracktor generated
				vehicle.setType("Tractor");
				vehicle.setMotorType("Motor Vehicle");
				vehicle.setPriority(1);
			}

			else if (randValue >= 81 && randValue <= 85) {
				// Engineering vehicle generated

				vehicle.setType("Engineering Vehicle");
				vehicle.setMotorType("Motor Vehicle");
				vehicle.setPriority(1);
			}

			else if (randValue >= 86 && randValue <= 95) {
				// Bicycle generated
				vehicle.setType("Bicycle");
				vehicle.setMotorType("Non-motorized Vehicle");
				vehicle.setPriority(0);
			}

			else if (randValue >= 96 && randValue <= 100) {
				// Horse-drawn vehicle generated
				vehicle.setType("Horse-drawn Vehicle");
				vehicle.setMotorType("Non-motorized Vehicle");
				vehicle.setPriority(0);
			}

			vehicle.setMediator(junctionMediator); // setting the mediator for each vehicle
			randomlyAssignRoad(vehicle, rng);
			randomlyAssignDirection(vehicle, rng);

			// add generated car to corresponding ArrayList
			// representing road number in range 1-4
			saveGeneratedVehicleInformationToMediator(vehicle);
		}

		saveGenerationInfoToFile(junctionMediator);

		return junctionMediator;
	}

	private void saveGenerationInfoToFile(JunctionMediator mediator) {

		File simulationResults = null;

		try {

			simulationResults = new File("SimResult.txt");

			boolean isFileCreated = simulationResults.createNewFile();
			if (!isFileCreated) {
				System.out.println("File already exists");
			}

			else {
				FileWriter writer = new FileWriter(simulationResults);
				writer.write(mediator.toString() + "\n");
				writer.close();
			}
		}

		catch (IOException e) {
			System.out.println("Error occured while creating " + simulationResults.getName());
			System.exit(0);
		}

	}

	private void randomlyAssignRoad(Vehicle vehicle, Random rng) {
		int roadNumber = rng.nextInt(1, 5);
		vehicle.setRoadNumber(roadNumber);
		vehicle.setRightOfWayVehicleRoadNumber();
		vehicle.setVehicleHavingPriorityOver();

	}

	private void randomlyAssignDirection(Vehicle vehicle, Random rng) {
		int direction = rng.nextInt(1, 4);

		switch (direction) {
		case 1:
			vehicle.setDirection("Turn Right");
			break;
		case 2:
			vehicle.setDirection("Turn Left");
			break;
		case 3:
			vehicle.setDirection("Go Straight");
			break;
		}

	}

	private void saveGeneratedVehicleInformationToMediator(Vehicle vehicle) {
		vehicle.moveToJunction();
	}

}
