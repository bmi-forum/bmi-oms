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
package wrappers.oms2bmImplementation;

import edu.colorado.csdms.bmi.BmiInfo;

import oms3.Access;
import oms3.ComponentAccess;
import oms3.annotations.Name;

/**
 *
 *
 * @author sidereus
 * @date August 08, 2016
 */
public class Oms2BmiInfo implements BmiInfo {

    ComponentAccess omsComponent;
    String message = "Method not implemented yet";

    public Oms2BmiInfo(final Object omsComponent) {
        this.omsComponent = new ComponentAccess(omsComponent);
    }

    @Override
    public String getComponentName() {
        Name name = omsComponent.getComponent().getClass().getAnnotation(Name.class);
        return name != null ? name.value() : "<none>";
    }

    @Override
    public String[] getInputVarNames() {
        Access[] access = omsComponent.inputs().toArray(new Access[0]);
        return toString(access);
    }

    @Override
    public int getInputVarNameCount() {
        return omsComponent.inputs().size();
    }

    @Override
    public String[] getOutputVarNames() {
        Access[] access = omsComponent.outputs().toArray(new Access[0]);
        return toString(access);
    }

    @Override
    public int getOutputVarNameCount() {
        return omsComponent.outputs().size();
    }

    private String[] toString(Access[] a) {
        String[] s = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            s[i] = a[i].getField().getName();
        }
        return s;
    }

}
