
public class Defector extends Organism{
	private String type;
	private double cooperationProbability;
	
	//Constructor
	//Set type as Defector and cooperation probability to zero
	public Defector () {
		super();
		type = "Defector";
		cooperationProbability = 0;
	}
	
	//Accessor for type
	@Override
	public String getType() {
		return type;
	}
	
	//Set the energy to zero and return its offspring (Defector)
	@Override
	public Organism reproduce() {	
		this.resetEnergy();
		return new Defector();
	}
	
	//Accessor for cooperation probability
	@Override
	public double getCooperationProbability() {
		return cooperationProbability;
	}
	
	//Return whether the organism cooperates. Since Defector always cooperates, return false
	@Override
	public boolean cooperates() {
		return false;
	}

}
