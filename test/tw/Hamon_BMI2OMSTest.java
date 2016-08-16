/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sidereus
 */
public class Hamon_BMI2OMSTest {
    
    public Hamon_BMI2OMSTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of bmi2oms method, of class Hamon_BMI2OMS.
     */
    @Test
    public void testBmi2oms() throws Exception {
        System.out.println("bmi2oms");
        Hamon_BMI2OMS instance = new Hamon_BMI2OMS();
        instance.bmi2oms();
    }
    
}
