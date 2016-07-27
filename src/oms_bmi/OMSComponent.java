/*
 * $Id$
 *
 * This file is part of the Object Modeling System (OMS),
 * 2007-2012, Olaf David and others, Colorado State University.
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
package oms_bmi;

import edu.colorado.csdms.bmi.BMI;

import oms3.annotations.Execute;
import oms3.annotations.Finalize;
import oms3.annotations.Initialize;

/**
 * OMS wrapper for a BMI component.
 *
 * @author od
 */
public class OMSComponent {

    public BMI comp;

    public OMSComponent(BMI bmicomp) {
        this.comp = bmicomp;
    }


    public static OMSComponent create(BMI comp) {
        return new OMSComponent(comp);
    }


    @Initialize
    public void init(String conf_file) throws Exception {
        comp.initialize(conf_file);
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
