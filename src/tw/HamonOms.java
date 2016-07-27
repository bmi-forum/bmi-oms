package tw;

import oms_bmi.OMSComponent;

public class HamonOms {

    public static void main(String[] args) throws Exception {

        Object o = OMSComponent.create(new Hamon_BMI());
        ((OMSComponent) o).init("inputs.properties");
        ((OMSComponent) o).run();
        ((OMSComponent) o).done();

    }
}
