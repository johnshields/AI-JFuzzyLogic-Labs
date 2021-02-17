package ie.gmit.sw;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

// Fuzzy Logic with Sugeno Inference

/**
 * Mamdani
 * ----------------
 * COG: 53.41%
 * MM: ?
 * LM: 73.4%
 * RM: 99.9%
 *
 * Sugeno
 * ---------------
 * COG: 52.77% (Rule Consequent)
 * COGS: 75.0% (Rule Antecedent)
 */
public class Dapping {
    public double getDappingLevel(int windBeaufort, int tempCelsius) {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/dappingSugeno.fcl", true);
        // 'FUNCTION_BLOCK getDappingLevel' from dappingMamdani.fcl
        FunctionBlock fb = fis.getFunctionBlock("getDappingLevel");
        // Display the linguistic variables and terms
        JFuzzyChart.get().chart(fb);
        // Apply values to variables
        fis.setVariable("wind", windBeaufort);
        fis.setVariable("temperature", tempCelsius);
        // Execute the fuzzy inference engine
        fis.evaluate();

        Variable dapping = fb.getVariable("dapping");
        // Use defuzzification method
        JFuzzyChart.get().chart(dapping.getDefuzzifier(),
                "Dapping Level", true);
        return dapping.getValue();
    }

    public static void main(String[] args) {
        Dapping e = new Dapping();
        double dapping = e.getDappingLevel(8, 10);
        System.out.println(dapping);
    }
}
