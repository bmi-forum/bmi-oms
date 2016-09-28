/*
 * $Id$
 *
 * This file is part of the Object Modeling System (OMS),
 * 2007-2016, Olaf David and others, Colorado State University.
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
package tw;

import org.junit.Test;

import wrappers.OMSComponent;

public class OMSwrapperTest {

    @Test
    public void omsWrapper() throws Exception {

        Object o = OMSComponent.create(new Hamon_BMI());
        ((OMSComponent) o).init("inputs.properties");
        ((OMSComponent) o).run();
        ((OMSComponent) o).done();

    }
}
