package ie.gmit.sw;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

// Fuzzy Logic Project Staffing
public class Staffing {
    public double getRisk(double funding, double staffing) {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/staffing.fcl", true);

        // 'FUNCTION_BLOCK staffing' from staffing.fcl
        FunctionBlock fb = fis.getFunctionBlock("staffing");
        // Display the linguistic variables and terms
        JFuzzyChart.get().chart(fb);
        // Apply a value to a variable
        fis.setVariable("funding", funding);
        fis.setVariable("staffing", staffing);
        // Execute the fuzzy inference engine
        fis.evaluate();

        Variable risk = fb.getVariable("risk");
        // Use 'Center Of Gravity' defuzzification method
        JFuzzyChart.get().chart(risk, risk.getDefuzzifier(), true);
        return risk.getValue();
    }

    public static void main(String[] args) {
        Staffing s = new Staffing();
        double risk = s.getRisk(60, 14);
        System.out.println(risk);
    }

}
