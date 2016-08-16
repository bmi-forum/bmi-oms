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

import edu.colorado.csdms.bmi.BmiTime;

import oms3.ComponentAccess;

/**
 *
 *
 * @author sidereus
 * @date August 08, 2016
 */
public class Oms2BmiTime implements BmiTime {

    ComponentAccess omsComponent;
    String message = "Method not implemented yet";

    public Oms2BmiTime(final Object omsComponent) {
        this.omsComponent = new ComponentAccess(omsComponent);
    }

    @Override
    public double getStartTime() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getCurrentTime() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getEndTime() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getTimeStep() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public String getTimeUnits() {
        throw new UnsupportedOperationException(message);
    }

}
