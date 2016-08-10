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

import edu.colorado.csdms.bmi.BmiGetter;

import oms3.Access;
import oms3.ComponentAccess;

/**
 *
 *
 * @author sidereus
 * @date August 08, 2016
 */
public class Oms2BmiGetter implements BmiGetter {

    ComponentAccess omsComponent;
    String message = "Method not implemented yet";

    public Oms2BmiGetter(final Object omsComponent) {
        this.omsComponent = new ComponentAccess(omsComponent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getValue(final String varName) {
        Access access = omsComponent.output(varName);
        Object returningObject = null;

        try {
            returningObject = access.getField().get(omsComponent.getComponent());
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return returningObject;
    }

    @Override
    public <T> T getValueRef(String varName) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <T> T getValueAtIndices(String varName, int[] indices) {
        throw new UnsupportedOperationException(message);
    }

}
