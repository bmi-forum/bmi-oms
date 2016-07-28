package tw;

import org.junit.Test;

import oms_bmi.OMSComponent;

public class OMSwrapperTest {

    @Test
    public void omsWrapper() throws Exception {

        Object o = OMSComponent.create(new Hamon_BMI());
        ((OMSComponent) o).init("inputs.properties");
        ((OMSComponent) o).run();
        ((OMSComponent) o).done();

    }
}
