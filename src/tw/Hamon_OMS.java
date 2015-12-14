/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tw;

import oms3.annotations.*;
import java.util.Calendar;

@Name("Hamon PotET ")
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
    // the number of days per months
    final static int[] DAYS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    
    @In public double temp;
    @In public double daylen;
    @In public Calendar time;
    @Out public double potET;

    @Execute
    public void execute() {
        int month = time.get(Calendar.MONTH);

        double Wt = 4.95 * Math.exp(0.062 * temp) / 100.;
        double D2 = (daylen / 12.0) * (daylen / 12.0);
        potET = 0.55 * DAYS[month] * D2 * Wt;
        if (potET <= 0.0) {
            potET = 0.0;
        }
        if (temp <= -1.0) {
            potET = 0.0;
        }
        potET *= 25.4;
    }
}