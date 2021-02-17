package ie.gmit.sw;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class Tipper {
    public double getTip(double service, double food) {
        String FILE = "fcl/tipper.txt";
        FIS fis = FIS.load(FILE, true);

        FunctionBlock fb = fis.getFunctionBlock("tipper");
        JFuzzyChart.get().chart(fb);
        fis.setVariable("service", service);
        fis.setVariable("food", food);
        fis.evaluate();

        Variable tip = fb.getVariable("tip");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);

        return tip.getValue();
    }
    public static void main(String[] args) {
        Tipper t = new Tipper();
        double tip = t.getTip(7, 3);
        System.out.println(tip);
    }
}