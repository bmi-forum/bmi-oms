/*
 * $Id$
 *
 * This file is part of the Object Modeling System (OMS),
 * 2007-2016, Olaf David and others, Colorado State University.
 *
 * OMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 2.1.
 *
 * OMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OMS.  If not, see <http://www.gnu.org/licenses/lgpl.txt>.
 */
package tw;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.colorado.csdms.bmi.BMI;
import wrappers.BMIComponent;

/**
 *
 * @author od
 */
public class BMIwrapperTest {

    @Test
    public void bmiWrapper() {

        BMI bmi = BMIComponent.create(new Hamon_OMS());

        testBmiInfo(bmi);
        testBmiBase(bmi);

    }

    private void testBmiInfo(final BMI bmi) {
        final String componentNameCorrect = "Hamon PotET";
        final String inputVarNamesCorrect = "[temp, daylen, time]";
        final int inputVarNameCountCorrect = 3;
        final String outputVarNamesCorrect = "[potET]";
        final int outputVarNameCountCorrect = 1;

        final String componentName = bmi.getComponentName();
        final String inputVarNames = Arrays.toString(bmi.getInputVarNames());
        final String outputVarNames = Arrays.toString(bmi.getOutputVarNames());
        final int inputVarNameCount = bmi.getInputVarNameCount();
        final int outputVarNameCount = bmi.getOutputVarNameCount();

        System.out.println("The name of the component is " + componentName);
        System.out.println("The input variable names are " + inputVarNames);
        System.out.println("The output variable names are " + outputVarNames);
        System.out.println("The input variable count is " + inputVarNameCount);
        System.out.println("The output variable count is " + outputVarNameCount);

        assertEquals(componentName, componentNameCorrect);
        assertEquals(inputVarNames, inputVarNamesCorrect);
        assertEquals(inputVarNameCount, inputVarNameCountCorrect);
        assertEquals(outputVarNames, outputVarNamesCorrect);
        assertEquals(outputVarNameCount, outputVarNameCountCorrect);
    }

    private void testBmiBase(final BMI bmi) {
        noInputFile(bmi);
        withInputFile(bmi);
    }

    private void noInputFile(final BMI bmi) {
        final Double correct = 45.73146404470157;
        double[] temp = {25.3};
        int[] daylen = {8};
        String[] time = {"2014-03-04"};

        bmi.setValue("temp", temp);
        bmi.setValue("daylen", daylen);
        bmi.setValue("time", time);

        bmi.initialize();
        bmi.update();

        Double result = bmi.getValue("potET");
        System.out.println(result);
        assertEquals(result, correct);
    }

    private void withInputFile(final BMI bmi) {
        final Double correct = 45.73146404470157;

        bmi.initialize("inputs.properties");
        bmi.update();

        Double result = bmi.getValue("potET");
        System.out.println(result);
        assertEquals(result, correct);
    }

}
