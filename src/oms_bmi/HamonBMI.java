/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms_bmi;

import bmi.BMI;
import java.util.Arrays;
import tw.HamonET;

/**
 *
 * @author od
 */
public class HamonBMI {

    public static void main(String[] args) throws Exception {

        BMI bmi = BMIComponent.create(new HamonET());

        System.out.println("The name of the component is " + bmi.getComponentName());
        System.out.println("The input variable names are " + Arrays.toString(bmi.getInputVarNames()));
        System.out.println("The output variable names are " + Arrays.toString(bmi.getOutputVarNames()));
        System.out.println("The input variable count is " + bmi.getInputVarNameCount());
        System.out.println("The output variable count is " + bmi.getOutputVarNameCount());

        bmi.setValue("temp", 25.3);
        bmi.setValue("daylen", 8);
        bmi.setValue("time", "04-03-2014");

        bmi.initialize(null);
        bmi.update();

        System.out.println(bmi.getValue("potET"));
    }
}
