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
package wrappers.oms2bmImplementation.grid;

import edu.colorado.csdms.bmi.BmiGridUnstructured;

import oms3.ComponentAccess;

/**
 *
 *
 * @author sidereus
 * @date August 08, 2016
 */
public class Oms2BmiGridUnstructured extends Oms2BmiGrid implements BmiGridUnstructured {

    public Oms2BmiGridUnstructured(final Object omsComponent) {
        super.omsComponent = new ComponentAccess(omsComponent);
    }

    @Override
    public double[] getGridX(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double[] getGridY(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double[] getGridZ(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int[] getGridConnectivity(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int[] getGridOffset(int gridId) {
        throw new UnsupportedOperationException(message);
    }

}
