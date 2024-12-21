package iyte.edu.tr.hw2;

public class Vehicle {

	private String type;
	private String motorType;
	private String direction;
	private int priority;
	private int rightOfWayVehicle;
	private int vehicleHavingPriorityOver;
	private int roadNumber;
	private boolean haveRightToPass;
	private JunctionMediator mediator;

	public Vehicle() {
		type = null;
		motorType = null;
		direction = null;
		priority = 0;
		rightOfWayVehicle = 0;
		roadNumber = 0;
		haveRightToPass = true;
		mediator = null;
	}

	public String getType() {
		return type;
	}

	public String getMotorType() {
		return motorType;
	}

	public String getDirection() {
		return direction;
	}

	public int getRightOfWayVehicleRoadNumber() {
		return rightOfWayVehicle;
	}

	public int getVehicleHavingPriorityOver() {
		return vehicleHavingPriorityOver;
	}

	public int getPriority() {
		return priority;
	}

	public int getRoadNumber() {
		return roadNumber;
	}

	public boolean getHaveRightToPass() {
		return haveRightToPass;
	}

	public String getPassingInformation() {

		String passingMessageDirectionSuffix = null;
		String passingMessage = null;

		if (direction.equals("Go Straight")) {
			passingMessageDirectionSuffix = "went straight";
		}

		else if (direction.equals("Turn Left")) {
			passingMessageDirectionSuffix = "turned left";
		}

		else if (direction.equals("Turn Right")) {
			passingMessageDirectionSuffix = "turned right";
		}

		if (roadNumber == 1) {
			passingMessage = type + " on the " + roadNumber + "st road ";
		}

		else if (roadNumber == 2) {
			passingMessage = type + " on the " + roadNumber + "nd road ";
		}

		else if (roadNumber == 3) {
			passingMessage = type + " on the " + roadNumber + "rd road ";
		}

		else {
			passingMessage = type + " on the " + roadNumber + "th road ";
		}

		passingMessage += passingMessageDirectionSuffix;

		return passingMessage;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMotorType(String motorType) {
		this.motorType = motorType;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setRightOfWayVehicleRoadNumber() {

		if (roadNumber == 1) {
			this.rightOfWayVehicle = 4;
		}

		else if (roadNumber == 2) {
			this.rightOfWayVehicle = 1;
		}

		else if (roadNumber == 3) {
			this.rightOfWayVehicle = 2;
		}

		else { // case where roadNumber is 4
			this.rightOfWayVehicle = 3;
		}

	}

	public void setVehicleHavingPriorityOver() {

		if (roadNumber == 1) {
			this.vehicleHavingPriorityOver = 2;
		}

		else if (roadNumber == 2) {
			this.vehicleHavingPriorityOver = 3;
		}

		else if (roadNumber == 3) {
			this.vehicleHavingPriorityOver = 4;
		}

		else { // case where roadNumber is 4
			this.vehicleHavingPriorityOver = 1;
		}

	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setRoadNumber(int roadNumber) {
		this.roadNumber = roadNumber;
	}

	public void setHaveRightToPass(boolean rightToPass) {
		this.haveRightToPass = rightToPass;
	}

	public void setMediator(JunctionMediator mediator) {
		this.mediator = mediator;
	}

	public void moveToJunction() {
		mediator.addToJunction(this);
	}

	@Override
	public String toString() {
		return type + "(" + direction + ")";
	}

}
