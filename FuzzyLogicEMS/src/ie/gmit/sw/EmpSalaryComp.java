package ie.gmit.sw;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class EmpSalaryComp {
    public double getSalary(int iq, int qualifications, float experience, int screen) {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/salary.fcl", true);

        // 'FUNCTION_BLOCK getSalary' from salary.fcl
        FunctionBlock fb = fis.getFunctionBlock("getSalary");
        // Display the linguistic variables and terms
        JFuzzyChart.get().chart(fb);
        // Apply a value to a variable
        fis.setVariable("screen", screen);
        fis.setVariable("experience", experience);
        fis.setVariable("iq", iq);
        fis.setVariable("qualifications", qualifications);
        // Execute the fuzzy inference engine
        fis.evaluate();

        Variable salary = fb.getVariable("salary");
        // Use 'Center Of Gravity' defuzzification method
        JFuzzyChart.get().chart(salary, salary.getDefuzzifier(), true);
        return salary.getValue();
    }


    public static void main(String[] args) {
        EmpSalaryComp e = new EmpSalaryComp();
        double salary = e.getSalary(120, 5, 7, 1);
        System.out.println(salary);
    }

}
