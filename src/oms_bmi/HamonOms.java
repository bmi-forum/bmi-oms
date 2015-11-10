package oms_bmi;

import tw.HamonEtp;

public class HamonOms {

    public static void main(String[] args) throws Exception {

        Object o = OMSComponent.create(new HamonEtp());
        ((OMSComponent) o).run();
        
        
    }
}
