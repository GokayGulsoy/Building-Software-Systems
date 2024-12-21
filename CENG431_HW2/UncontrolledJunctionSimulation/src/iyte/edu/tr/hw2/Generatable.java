package iyte.edu.tr.hw2;

public interface Generatable {

	/**
	 * method that generates vehicles to be mediated by mediator which arrive at
	 * uncontrolled junction
	 * 
	 * @return JunctionMediator which mediates the traffic at uncontrolled junction
	 */
	public JunctionMediator generateVehicles();
}
