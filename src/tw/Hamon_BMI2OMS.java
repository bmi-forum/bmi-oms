package tw;

import oms_bmi.OMSComponent;

public class Hamon_BMI2OMS {

    public static void main(String[] args) throws Exception {

        Object o = OMSComponent.create(new Hamon_BMI());
        ((OMSComponent) o).init("inputs.properties");
        ((OMSComponent) o).run();
        ((OMSComponent) o).done();

    }
}
