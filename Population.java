import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Population {
	
	//Declare an array that holds organisms in the population, 
	//map that stores the population counts for each organism
	//random object, total population of organisms
	private Organism orgArray[];
	private Map<String, Integer> everyPopulationCounts;
	private Random r;
	private int populationCounts;
	
	//Constructor
	public Population (Map<String, Integer> counts) {
		int cooperatorCounts = 0;
		int defectorCounts = 0;
		int partialCooperatorCounts = 0;
		//Constructs a population of organisms dictated 
		//by the given set of pairs that associate (case-sensitive) names of organisms 
		//to counts of that type of organism
		//Throws an IllegalArgumentException if the mapping mentions 
		//organism types that do not exist in the program
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
				String orgName = entry.getKey();
				if (orgName == "Cooperator") {
					cooperatorCounts = entry.getValue();
				}
				else if (orgName == "Defector") {
					defectorCounts = entry.getValue();
				}
				else if (orgName == "Partial cooperator") {
					partialCooperatorCounts = entry.getValue();
				}
				else {
					throw new IllegalArgumentException();
				}
		}
		//Initialize the initial total population
		populationCounts = cooperatorCounts + defectorCounts + partialCooperatorCounts;
		//Initialize an array that holds the organisms
		orgArray = new Organism[populationCounts];
		//Initialize instances of each organism based on input
		for (int i = 0; i < cooperatorCounts; ++i) {
			orgArray[i] = new Cooperator();
		}
		for (int i = cooperatorCounts; i < cooperatorCounts + defectorCounts; ++i) {
			orgArray[i] = new Defector();
		}
		for (int i = cooperatorCounts + defectorCounts; i < populationCounts; ++i) {
			orgArray[i] = new PartialCooperator();
		}
		//Initialize random generator
		r = new Random();
		//r.setSeed(9);

		//Shuffle the array randomly so that when we use update,  
		//replace the organism when one reproduces
		for (int i = populationCounts - 1; i > 0; --i) {
			int swapped = r.nextInt(i + 1);
			Organism temp = orgArray[swapped];
			orgArray[swapped] = orgArray[i];
			orgArray[i] = temp;
		}
		//Initialize the map that holds organisms in the population according to the input
		everyPopulationCounts = new HashMap<String, Integer>();
		everyPopulationCounts.put("Cooperator", cooperatorCounts);
		everyPopulationCounts.put("Defector", defectorCounts);
		everyPopulationCounts.put("Partial cooperator", partialCooperatorCounts);
	}
	
	public void update() {
		//After the organism cooperates, this Hashset stores the organism itself
		//and organisms that received energy so that they will not receive multiple times 
		//from one organism's update
		Set<Integer> choosenOrg = new HashSet<Integer>();	

		for (Organism org: orgArray) {
			//Update the organism
			org.update();
			//If the organism cooperates, decrements its energy and randomly give other eight organisms energy
			if (org.cooperates()) {
				org.decrementEnergy();
				//Choose 8 organisms randomly from the population
				//If the organism itself is chosen, choose other organism form the population
				for (int i = 0; i < 8; ++i) {
				int energyGivenOrg = r.nextInt(populationCounts);
				//If the organism itself is chosen, put it into the HashSet
					if (org == orgArray[energyGivenOrg]) {
						choosenOrg.add(energyGivenOrg);
					}
					//If the organism is already given energy from one update, choose an other 
					//organism to give the energy
					while (choosenOrg.contains(energyGivenOrg)) {
						energyGivenOrg = r.nextInt(populationCounts);
					}
					choosenOrg.add(energyGivenOrg);
					orgArray[energyGivenOrg].incrementEnergy();
					}
				//Reset the HashMap
				choosenOrg.clear();
			}
		}
		
		//If the organism has at least 10 energy, reproduces the offspring and 
		//the offspring randomly replaces other organism from the population 
		for (Organism org: orgArray) {
			if (org.getEnergy() >= 10) {
				Organism newOrganism = org.reproduce();
				everyPopulationCounts.replace(newOrganism.getType(), everyPopulationCounts.get(newOrganism.getType()) + 1);
				int replacedOrgIdx = r.nextInt(populationCounts);
				Organism replacedOrganism = orgArray[replacedOrgIdx];
				everyPopulationCounts.replace(replacedOrganism.getType(), everyPopulationCounts.get(replacedOrganism.getType()) - 1);
				orgArray[replacedOrgIdx] = newOrganism;
			}
		}
	}
	
	//Return the mean of cooperation probability in the population
	public double calculateCooperationMean() {
		return (everyPopulationCounts.get("Cooperator") + everyPopulationCounts.get("Partial cooperator")*0.5) / populationCounts;
	}
	
	//Accessor for total population
	public Map<String, Integer> getPopulationCounts() {
	    return everyPopulationCounts;
	}
	

}
