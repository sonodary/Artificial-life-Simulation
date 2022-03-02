
public abstract class Organism {
	private int energy;
	
	//Zero argument constructor
	//Set energy as 0
	public Organism( ) {
		energy = 0;
	}
	
	//Increment the energy
	public void update() {
		energy++;
	}
	
	//Accessor for energy
	public int getEnergy() {
		return energy;
	}
	
	//Set energy as 0
	public void resetEnergy() {
		energy = 0;
	}
	
	//Increment the energy
	public void incrementEnergy() {
		energy++;
	}
	
	//Decrement the energy
	public void decrementEnergy() {
		energy--;
	}
	
	//Accessor for type
	public abstract String getType();
	
	//Set the energy to zero and return its offspring
	public abstract Organism reproduce();
	
	//Accessor for cooperation probability
	public abstract double getCooperationProbability();
	
	//Return whether the organism cooperates or not
	public abstract boolean cooperates();

}
