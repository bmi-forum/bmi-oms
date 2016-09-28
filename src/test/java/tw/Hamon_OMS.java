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

import oms3.annotations.*;
import java.util.Calendar;

@Name("Hamon PotET")
@Description
    ("Hamon Potential Evapotranspiration." +
    "Climatic demand for water relative to the available energy, after Hamon.")
@Author
    (name= "Jo Scientist", contact= "jos@research-org.edu")
@Keywords
    ("Hydrology, Potential Evapotranspiration")
@Bibliography
    ("Hamon, W.R., 1961, Estimating potential evapotranspiration: Journal of the Hydraulics Division " +
     "Proceedings of the American Society of Civil Engineers, v. 87, p. 107-120.")
@VersionInfo
    ("$Id: HamonET.java 3c0d92aa1e9e 2013-10-14 11:05 -0600 odavid <odavid@colostate.edu> $")
@SourceInfo
    ("$HeadURL: http://svn.javaforge.com/svn/oms/branches/oms3.prj.thornthwaite/src/tw/HamonET.java $")
@License
    ("http://www.gnu.org/licenses/gpl-2.0.html")

public class Hamon_OMS {

    @In public double temp;
    @In public double daylen;
    @In public Calendar time;
    @Out public double potET;

    @Execute
    public void execute() {

        double Wt = 4.95 * Math.exp(0.062 * temp) / 100.;
        double D2 = (daylen / 12.0) * (daylen / 12.0);
        int daysInMonth = time.getActualMaximum(Calendar.DAY_OF_MONTH);
        potET = 0.55 * daysInMonth * D2 * Wt;
        if (potET <= 0.0) {
            potET = 0.0;
        }
        if (temp <= -1.0) {
            potET = 0.0;
        }
        potET *= 25.4;
    }

}
