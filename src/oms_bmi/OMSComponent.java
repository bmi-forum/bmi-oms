package oms_bmi;

import bmi.BMI;
import oms3.annotations.Execute;
import oms3.annotations.Finalize;
import oms3.annotations.Initialize;

public class OMSComponent {

    public BMI comp;

    public OMSComponent(BMI bmicomp) {
        this.comp = bmicomp;
    }


    public static OMSComponent create(BMI comp) {
        return new OMSComponent(comp);
    }


    @Initialize
    public void init() throws Exception {
        comp.initialize("inputs.properties");
    }


    @Execute
    public void run() throws Exception {
        comp.update();
    }


    @Finalize
    public void done() throws Exception {
        comp.finalize();
    }
}
