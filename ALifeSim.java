/*
* Authors: Ryuichiro Sonoda
* Assignment 4: Artificial Life
* 12/10/2021
* Sources used: none
* Help received: none
*
* I confirm that the above list of sources is complete
* AND that I have not talked to anyone else (e.g., CSC 207 students)
* about the solution to this problem.”
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ALifeSim {
	public static void main (String args[]) {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		try {
			//Based on the user's input, assign the number of iterations
			int numIterations = Integer.parseInt(args[0]);
			//Based on the user's input, assign the population of Cooperators, Defectors, and Partial Cooperators.
			int numCooperators = Integer.parseInt(args[1]);
			int numDefectors = Integer.parseInt(args[2]);
			int numPartialCooperators = Integer.parseInt(args[3]);
			
			//Create a map that stores the number of population for each type of organism
			counts.put("Cooperator", numCooperators);
			counts.put("Defector", numDefectors);
			counts.put("Partial cooperator", numPartialCooperators);
			
			//Initialize the Population object according to the user input
			Population population = new Population(counts);
			//Update the population numIterations times
			for (int i = 0; i < numIterations; ++i) {
				population.update();
			}
			
			//Display the number of iterations, the population of each organism, and mean cooperation probability
			System.out.println("After " + numIterations + " ticks:");
			System.out.println("Cooperators = " + population.getPopulationCounts().get("Cooperator"));
			System.out.println("Defectors = " + population.getPopulationCounts().get("Defector"));
			System.out.println("Partial cooperators = " + population.getPopulationCounts().get("Partial cooperator"));
			System.out.println("Mean Cooperation Probability = " + population.calculateCooperationMean());
		}
		catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong number of arguments.");
            System.err.println("Usage: java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>");
        }
        catch (NumberFormatException e) {
            System.err.println("The argument you specified must be an integer.");
        }
	}

}
