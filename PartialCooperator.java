import java.util.Random;

public class PartialCooperator extends Organism{
	private String type;
	private double cooperationProbability;
	private Random random;
	
	//Constructor
	//Set type as Defector and cooperation probability to 0.5
	//Initialize Random object
	public PartialCooperator () {
		super();
		type = "Partial cooperator";
		cooperationProbability = 0.5;
		random = new Random();
		//random.setSeed(9);
	}
	
	//Accessor for type
	@Override
	public String getType() {
		return type;
	}
	
	//Set the energy to zero and return its offspring (Partial Cooperator)
	@Override
	public Organism reproduce() {
		this.resetEnergy();
		return new PartialCooperator();
	}
	
	//Accessor for cooperation probability
	@Override
	public double getCooperationProbability() {
		return cooperationProbability;
	}
	
	//Return whether the organism cooperates. Since partial cooperator cooperates with 50 % probability,
	//it return true with 50 % of time and false with 50% of time
	@Override
	public boolean cooperates() {
		return random.nextBoolean();
	}

}

