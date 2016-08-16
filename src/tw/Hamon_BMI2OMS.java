package tw;

import oms_bmi.OMSComponent;
import org.junit.Test;

public class Hamon_BMI2OMS {

    @Test
    public void bmi2oms() throws Exception {

        Object o = OMSComponent.create(new Hamon_BMI());
        ((OMSComponent) o).init("inputs.properties");
        ((OMSComponent) o).run();
        ((OMSComponent) o).done();

    }
}
