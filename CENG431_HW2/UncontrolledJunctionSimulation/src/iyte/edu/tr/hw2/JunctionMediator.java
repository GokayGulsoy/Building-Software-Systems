package iyte.edu.tr.hw2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JunctionMediator implements Mediatable {

	private ArrayList<Vehicle> firstRoadVehicles;
	private ArrayList<Vehicle> secondRoadVehicles;
	private ArrayList<Vehicle> thirdRoadVehicles;
	private ArrayList<Vehicle> fourthRoadVehicles;
	private Map<Integer, Vehicle> junctionVehicles;
	private Map<Integer, Integer> crossingPedestrians;
	private int roundNumber;

	public JunctionMediator() {
		firstRoadVehicles = new ArrayList<Vehicle>();
		secondRoadVehicles = new ArrayList<Vehicle>();
		thirdRoadVehicles = new ArrayList<Vehicle>();
		fourthRoadVehicles = new ArrayList<Vehicle>();
		junctionVehicles = new HashMap<Integer, Vehicle>(4);
		junctionVehicles.put(1, null);
		junctionVehicles.put(2, null);
		junctionVehicles.put(3, null);
		junctionVehicles.put(4, null);
		crossingPedestrians = new HashMap<Integer, Integer>();
		crossingPedestrians.put(1, 0);
		crossingPedestrians.put(2, 0);
		crossingPedestrians.put(3, 0);
		crossingPedestrians.put(4, 0);
		roundNumber = 1;
	}

	@Override
	public void mediateUncontrolledJunction() {

		// putting vehicles to junctionVehicles HashMap
		// in order to initialize the HashMap for simulation
		moveVehiclesToJunction();
		displayRoads();

		while (firstRoadVehicles.size() != 0 || secondRoadVehicles.size() != 0 || thirdRoadVehicles.size() != 0
				|| fourthRoadVehicles.size() != 0 || checkForEmptyJunction()) {

			// until all the vehicles have moved in their directions
			// continue to call assignRightToPass method
			assignRightToPass();
		}
	}

	public void addToJunction(Vehicle vehicle) {

		switch (vehicle.getRoadNumber()) {
		case 1:
			firstRoadVehicles.add(vehicle);
			break;
		case 2:
			secondRoadVehicles.add(vehicle);
			break;
		case 3:
			thirdRoadVehicles.add(vehicle);
			break;
		case 4:
			fourthRoadVehicles.add(vehicle);
			break;
		}
	}

	private void displayRoads() {
		System.out.println(this.toString());
	}

	private void moveVehiclesToJunction() {

		Vehicle firstRoadVehicle = null;
		Vehicle secondRoadVehicle = null;
		Vehicle thirdRoadVehicle = null;
		Vehicle fourthRoadVehicle = null;

		// move vehicles to junction in order
		// to initialize the simulation
		if (firstRoadVehicles.size() > 0) {
			firstRoadVehicle = firstRoadVehicles.remove(0);
			junctionVehicles.put(1, firstRoadVehicle);
		}

		if (secondRoadVehicles.size() > 0) {
			secondRoadVehicle = secondRoadVehicles.remove(0);
			junctionVehicles.put(2, secondRoadVehicle);
		}

		if (thirdRoadVehicles.size() > 0) {
			thirdRoadVehicle = thirdRoadVehicles.remove(0);
			junctionVehicles.put(3, thirdRoadVehicle);
		}

		if (fourthRoadVehicles.size() > 0) {
			fourthRoadVehicle = fourthRoadVehicles.remove(0);
			junctionVehicles.put(4, fourthRoadVehicle);
		}
	}

	private void assignRightToPass() {

		// generate pedestrian on random road
		// with possibility of 10%
		int roadNumber = generatePedestrian();

		if (roadNumber != 0) {
			Vehicle vehicle = junctionVehicles.get(roadNumber);
			// set vehicle to have right to false iff given road contains vehicle
			if (vehicle != null) {
				vehicle.setHaveRightToPass(false);
			}

			displayPedestrianWaitingInfo(roadNumber);
		}

		for (int roadId : junctionVehicles.keySet()) {
			// if vehicle exists on the selected
			// road make this check, otherwise not
			if (junctionVehicles.get(roadId) != null && roadNumber != roadId && crossingPedestrians.get(roadId) == 0) {
				Vehicle anyVehicle = junctionVehicles.get(roadId);

				checkForNonMotorizedVehicle(anyVehicle);
				checkForTractorOrEngineeringVehicle(anyVehicle);
				checkRightOfWayVehicle(anyVehicle);
			}

		}

		// if some pedestrian is generated in any
		// of the roads increment their crossing rounds
		determineVehicleToPassAndMoveToNewRound();
		incrementPedestrianCrossingRounds();
	}

	private void checkRightOfWayVehicle(Vehicle vehicle) {
		int vehicleHavingPriorityOverRoad = vehicle.getVehicleHavingPriorityOver();
		int vehicleHavingPriorityOverRoadPedestrianRound = crossingPedestrians.get(vehicleHavingPriorityOverRoad);
		Vehicle vehicleHavingPriorityOver = junctionVehicles.get(vehicleHavingPriorityOverRoad); // if there is already
																									// a pedestrian on
		// the road of right-of-way vehicle
		// no need to check this condition
		String direction = vehicle.getDirection();
		if (vehicleHavingPriorityOver != null && vehicleHavingPriorityOverRoadPedestrianRound == 0
				&& direction.equals("Turn Left") && !vehicleHavingPriorityOver.getDirection().equals("Turn Left")
				&& vehicle.getPriority() == vehicleHavingPriorityOver.getPriority()) {
			vehicle.setHaveRightToPass(false);
		}
	}

	private int generatePedestrian() {
		Random rng = new Random();
		int randomNumber = rng.nextInt(1, 101);
		int randomRoadNumber = 0;

		if (randomNumber <= 10) {
			randomRoadNumber = rng.nextInt(1, 5);
			crossingPedestrians.put(randomRoadNumber, 1);
		}

		return randomRoadNumber;
	}

	private void saveRoundInformationToFile(String passingInformation) {

		File simulationResultsFile = null;

		try {
			simulationResultsFile = new File("SimResult.txt");

			if (!simulationResultsFile.exists()) {
				System.out.println("SimResults.txt file does not exist");
				System.exit(1);
			}

			FileWriter writer = new FileWriter(simulationResultsFile, true);
			writer.write(passingInformation);
			writer.close();
		}

		catch (IOException e) {
			System.out.println("Error occured while writing round information to SimResults.txt file!!");
			System.exit(1);
		}

	}

	private void checkForNonMotorizedVehicle(Vehicle vehicle) {

		if (vehicle.getMotorType().equals("Non-motorized Vehicle")) {
			// if there exists even single motorized vehicle
			// this non-motorized vehicle will have no right to pass
			int higherPriorityVehicle = 0;
			for (Vehicle otherVehicle : junctionVehicles.values()) {
				if (otherVehicle != null && vehicle != otherVehicle) {

					int otherVehicleRoadNumber = otherVehicle.getRoadNumber();
					if (otherVehicle.getMotorType().equals("Motor Vehicle")
							&& !checkIfOtherVehicleIsBlockedByPedestrian(otherVehicleRoadNumber)) {
						higherPriorityVehicle++;
					}
				}
			}

			if (higherPriorityVehicle != 0) {
				vehicle.setHaveRightToPass(false);
			}
		}
	}

	private void checkForTractorOrEngineeringVehicle(Vehicle vehicle) {

		int higherPriorityVehicleCount = 0;
		if (vehicle.getType().equals("Engineering Vehicle") || vehicle.getType().equals("Tractor")) {
			for (Vehicle otherVehicle : junctionVehicles.values()) {
				if (otherVehicle != null && vehicle != otherVehicle) { // don't compare the vechile with itself
					String otherVehicleType = otherVehicle.getType();
					int otherVehicleRoadNumber = otherVehicle.getRoadNumber();

					if (!otherVehicleType.equals("Tractor") && !otherVehicleType.equals("Engineering Vehicle")
							&& !otherVehicle.getMotorType().equals("Non-motorized Vehicle")
							&& !checkIfOtherVehicleIsBlockedByPedestrian(otherVehicleRoadNumber)) {
						higherPriorityVehicleCount++;
					}
				}

			}

			if (higherPriorityVehicleCount != 0) {
				vehicle.setHaveRightToPass(false);
			}
		}
	}

	private void incrementPedestrianCrossingRounds() {
		for (int crossingRoad : crossingPedestrians.keySet()) {
			if (crossingPedestrians.get(crossingRoad) != 0) {
				int currentCrossingRound = crossingPedestrians.get(crossingRoad);
				currentCrossingRound++;
				currentCrossingRound = currentCrossingRound % 3; // pedestrian pass the road in two rounds

				if (currentCrossingRound == 0) {
					displayPedestrianCrossingInfo(crossingRoad);
					String pedestrianCrossingInfo = getPedestrianCrossingInfo(crossingRoad);
					saveRoundInformationToFile(pedestrianCrossingInfo);
				}

				crossingPedestrians.put(crossingRoad, currentCrossingRound);
			}
		}

		// reset haveRigthToPass field of each vehicle
		// at the end of the round if there is no crossing
		// pedestrian on the road of vehicle
		for (int roadNumber : junctionVehicles.keySet()) {
			Vehicle vehicle = junctionVehicles.get(roadNumber);
			if (vehicle != null && crossingPedestrians.get(roadNumber) == 0) {
				vehicle.setHaveRightToPass(true);
			}
		}
	}

	private void determineVehicleToPassAndMoveToNewRound() {

		List<Vehicle> vehiclesHaveRigthToPass = new ArrayList<Vehicle>();
		List<Integer> vehicleRoadsHaveRigthToPass = new ArrayList<Integer>();
		for (Vehicle vehicle : junctionVehicles.values()) {
			if (vehicle != null && vehicle.getHaveRightToPass()) {
				vehiclesHaveRigthToPass.add(vehicle);
				vehicleRoadsHaveRigthToPass.add(vehicle.getRoadNumber());
			}
		}

		if (vehiclesHaveRigthToPass.size() > 0) {

			int roadToRemoveVehicle = vehicleRoadsHaveRigthToPass.get(0);

			if (vehicleRoadsHaveRigthToPass.size() >= 2 && vehicleRoadsHaveRigthToPass.size() <= 3
					&& !checkVehiclesForPerpendicularRoads(vehicleRoadsHaveRigthToPass)) {
				// if there two or three vehicles vehicles on junction
				// rule for right-of-way vehicle is applied (vehicle on the right have priority)
				int roadIndexOfVehicleToRemove = roadToRemoveVehicle;
				int actualRoadIndexOfVehicleToRemove = roadIndexOfVehicleToRemove;

				while (vehicleRoadsHaveRigthToPass.contains(roadIndexOfVehicleToRemove)) {

					Vehicle currentVehicle = null;
					for (Vehicle vehicle : vehiclesHaveRigthToPass) {
						if (vehicle.getRoadNumber() == roadIndexOfVehicleToRemove) {
							currentVehicle = vehicle;
						}
					}

					actualRoadIndexOfVehicleToRemove = roadIndexOfVehicleToRemove;
					roadIndexOfVehicleToRemove = currentVehicle.getRightOfWayVehicleRoadNumber();
				}

				roadToRemoveVehicle = actualRoadIndexOfVehicleToRemove;
			}

			if ((vehiclesHaveRigthToPass.size() == 2 && checkVehiclesForPerpendicularRoads(vehicleRoadsHaveRigthToPass))
					|| vehiclesHaveRigthToPass.size() == 4) {
				int smallestRoadIndex = vehiclesHaveRigthToPass.get(0).getRoadNumber();

				// in case of two vehicle in perpendicular direction
				// or 4 vehicle that have right to pass then vehicle with smallest
				// road index number will be given a right to pass
				for (int i = 1; i < vehiclesHaveRigthToPass.size(); i++) {
					if (vehiclesHaveRigthToPass.get(i).getRoadNumber() < smallestRoadIndex) {
						smallestRoadIndex = vehiclesHaveRigthToPass.get(i).getRoadNumber();
					}
				}

				roadToRemoveVehicle = smallestRoadIndex;
			}

			Vehicle passedVehicle = junctionVehicles.get(roadToRemoveVehicle);
			String passingInformation = passedVehicle.getPassingInformation();
			passingInformation = roundNumber + ". " + passingInformation + "\n";
			saveRoundInformationToFile(passingInformation);
			System.out.print(passingInformation);

			String pedestrianWaitingInfo = getPedestrianWaitingInfo();

			if (pedestrianWaitingInfo != null) {
				saveRoundInformationToFile(pedestrianWaitingInfo);
				System.out.print(pedestrianWaitingInfo);
			}

			// remove the moved vehicle from the junctionVehicles HashMap
			junctionVehicles.put(roadToRemoveVehicle, null);

			Vehicle nextVehicle = null;
			if (roadToRemoveVehicle == 1 && firstRoadVehicles.size() > 0) {
				nextVehicle = firstRoadVehicles.remove(0);
			}

			else if (roadToRemoveVehicle == 2 && secondRoadVehicles.size() > 0) {
				nextVehicle = secondRoadVehicles.remove(0);
			}

			else if (roadToRemoveVehicle == 3 && thirdRoadVehicles.size() > 0) {
				nextVehicle = thirdRoadVehicles.remove(0);
			}

			else if (roadToRemoveVehicle == 4 && fourthRoadVehicles.size() > 0) {
				nextVehicle = fourthRoadVehicles.remove(0);
			}

			junctionVehicles.put(roadToRemoveVehicle, nextVehicle);
		}

		roundNumber++;
	}

	private String getPedestrianWaitingInfo() {

		String pedestrianWaitingInfo = null;
		for (Integer roadNumber : crossingPedestrians.keySet()) {
			// if pedestrian is generated on given road
			if (crossingPedestrians.get(roadNumber) == 1) {
				pedestrianWaitingInfo = getPedestrianRoad(roadNumber, "waiting");
			}
		}

		return pedestrianWaitingInfo;
	}

	private String getPedestrianCrossingInfo(int pedestrianRoad) {
		return getPedestrianRoad(pedestrianRoad, "crossing");
	}

	private String getPedestrianRoad(int roadNumber, String waitingOrCrossing) {

		if (roadNumber == 1) {
			return "A pedestrian is " + waitingOrCrossing + " on the " + roadNumber + "st road\n";
		}

		else if (roadNumber == 2) {
			return "A pedestrian is " + waitingOrCrossing + " on the " + roadNumber + "nd road\n";
		}

		else if (roadNumber == 3) {
			return "A pedestrian is " + waitingOrCrossing + " on the " + roadNumber + "rd road\n";
		}

		else { // case in which road number is 4
			return "A pedestrian is " + waitingOrCrossing + " on the " + roadNumber + "th road\n";
		}

	}

	private void displayPedestrianWaitingInfo(int roadNumber) {

		if (roadNumber == 1) {
			System.out.println("A pedestrian is waiting on the " + roadNumber + "st road");
		}

		else if (roadNumber == 2) {
			System.out.println("A pedestrian is waiting on the " + roadNumber + "nd road");
		}

		else if (roadNumber == 3) {
			System.out.println("A pedestrian is waiting on the " + roadNumber + "rd road");
		}

		else if (roadNumber == 4) {
			System.out.println("A pedestrian is waiting on the " + roadNumber + "th road");
		}

	}

	private void displayPedestrianCrossingInfo(int roadNumber) {

		if (roadNumber == 1) {
			System.out.println("A pedestrian is crossing on the " + roadNumber + "st road");
		}

		else if (roadNumber == 2) {
			System.out.println("A pedestrian is crossing on the " + roadNumber + "nd road");
		}

		else if (roadNumber == 3) {
			System.out.println("A pedestrian is crossing on the " + roadNumber + "rd road");
		}

		else if (roadNumber == 4) {
			System.out.println("A pedestrian is crossing on the " + roadNumber + "th road");
		}

	}

	private boolean checkVehiclesForPerpendicularRoads(List<Integer> vehicleRoads) {

		boolean isPerpendicular;
		if (vehicleRoads.size() == 2) {
			int perpendicularityValue = Math.abs(vehicleRoads.get(0) - vehicleRoads.get(1));
			isPerpendicular = perpendicularityValue == 2 ? true : false;
		}

		else { // if size is greater than 2 than perpendicularity is meaningless
			isPerpendicular = false;
		}

		return isPerpendicular;
	}

	private boolean checkIfOtherVehicleIsBlockedByPedestrian(int roadNumber) {

		for (int roadNum : crossingPedestrians.keySet()) {
			if (roadNum == roadNumber && crossingPedestrians.get(roadNumber) == 0) {
				return false;
			}
		}

		return true;
	}

	private boolean checkForEmptyJunction() {

		for (Vehicle vehicle : junctionVehicles.values()) {
			if (vehicle != null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {

		String carsWaitingForJunction = "Vehicles:\n";
		carsWaitingForJunction += "	1st road\n";
		for (int i = 0; i < firstRoadVehicles.size(); i++) {
			carsWaitingForJunction += "		" + (i + 1) + ". " + firstRoadVehicles.get(i) + "\n";
		}

		carsWaitingForJunction += "	2nd road\n";
		for (int i = 0; i < secondRoadVehicles.size(); i++) {
			carsWaitingForJunction += "		" + (i + 1) + ". " + secondRoadVehicles.get(i) + "\n";
		}

		carsWaitingForJunction += "	3rd road\n";
		for (int i = 0; i < thirdRoadVehicles.size(); i++) {
			carsWaitingForJunction += "		" + (i + 1) + ". " + thirdRoadVehicles.get(i) + "\n";
		}

		carsWaitingForJunction += "	4th road\n";
		for (int i = 0; i < fourthRoadVehicles.size(); i++) {
			carsWaitingForJunction += "		" + (i + 1) + ". " + fourthRoadVehicles.get(i) + "\n";
		}

		return carsWaitingForJunction;
	}

}
