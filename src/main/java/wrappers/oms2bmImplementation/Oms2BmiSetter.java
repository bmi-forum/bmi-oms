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

import java.lang.reflect.Field;

import edu.colorado.csdms.bmi.BmiSetter;

import oms3.Access;
import oms3.ComponentAccess;
import oms3.Conversions;

/**
 *
 *
 * @author sidereus
 * @date August 08, 2016
 */
public class Oms2BmiSetter implements BmiSetter {

    ComponentAccess omsComponent;
    String message = "Method not implemented yet";

    public Oms2BmiSetter(final Object omsComponent) {
        this.omsComponent = new ComponentAccess(omsComponent);
    }

    @Override
    public void setValue(String name, double[] src) {
        if (src.length == 1) setValue(name, src[0]);
        else genericSetValue(name, src);
    }

    @Override
    public void setValue(String name, int[] src) {
        if (src.length == 1) setValue(name, src[0]);
        else genericSetValue(name, src);
    }

    @Override
    public void setValue(String name, String[] src) {
        if (src.length ==1) setValue(name, src[0]);
        else genericSetValue(name, src);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, double[] src) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, int[] src) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, String[] src) {
        throw new UnsupportedOperationException(message);
    }

    private void genericSetValue(String name, Object src) {
        Access access = omsComponent.input(name);

        try {
            Field field = access.getField();
            field.set(omsComponent.getComponent(), Conversions.convert(src, access.getField().getType()));
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void setValue(String name, double src) {
        genericSetValue(name, src);
    }

    private void setValue(String name, int src) {
        genericSetValue(name, src);
    }

    private void setValue(String name, String src) {
        genericSetValue(name, src);
    }

}
