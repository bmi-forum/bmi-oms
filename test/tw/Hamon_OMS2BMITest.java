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
public class Hamon_OMS2BMITest {
    
    public Hamon_OMS2BMITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of oms2bmi method, of class Hamon_OMS2BMI.
     */
    @Test
    public void testOms2bmi() throws Exception {
        System.out.println("oms2bmi");
        Hamon_OMS2BMI instance = new Hamon_OMS2BMI();
        instance.oms2bmi();
    }
    
}
