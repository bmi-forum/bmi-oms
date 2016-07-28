/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.colorado.csdms.bmi.BMI;
import oms_bmi.BMIComponent;

/**
 *
 * @author od
 */
public class BMIwrapperTest {

    @Test
    public void bmiWrapper() {

        BMI bmi = BMIComponent.create(new Hamon_OMS());

        System.out.println("The name of the component is " + bmi.getComponentName());
        System.out.println("The input variable names are " + Arrays.toString(bmi.getInputVarNames()));
        System.out.println("The output variable names are " + Arrays.toString(bmi.getOutputVarNames()));
        System.out.println("The input variable count is " + bmi.getInputVarNameCount());
        System.out.println("The output variable count is " + bmi.getOutputVarNameCount());

        double[] temp = {25.3};
        int[] daylen = {8};
        String[] time = {"04-03-2014"};

        bmi.setValue("temp", temp);
        bmi.setValue("daylen", daylen);
        bmi.setValue("time", time);

        bmi.initialize(null);
        bmi.update();

        Double result = bmi.getValue("potET");
        Double correct = 44.25625552713055;
        System.out.println(result);
        assertEquals(result, correct);
    }
}