
public class Cooperator extends Organism{
	private String type;
	private double cooperationProbability;
	
	//Constructor
	//Set type as Cooperator and cooperation probability to one
	public Cooperator () {
		super();
		type = "Cooperator";
		cooperationProbability = 1;
	}
	
	//Accessor for type
	@Override
	public String getType() {
		return type;
	}
	
	//Set the energy to zero and return its offspring (Cooperator)
	@Override
	public Organism reproduce() {
		this.resetEnergy();
		return new Cooperator();
	}
	
	//Accessor for cooperation probability
	@Override
	public double getCooperationProbability() {
		return cooperationProbability;
	}
	
	//Return whether the organism cooperates. Since Cooperator always cooperates, return true 
	@Override
	public boolean cooperates() {
		return true;
	}
	

}
