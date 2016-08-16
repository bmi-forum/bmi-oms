package tw;

import java.io.FileInputStream;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import bmi.BMI;

public class Hamon_BMI implements BMI {

    public static double temp;
    public static double daylen;
    public Calendar time;
    public double potET;

    @Override
    public void initialize(String config_file) throws Exception {
        if (config_file != null) {
            Properties props;
            try (FileInputStream fis = new FileInputStream(config_file)) {
                props = new Properties();
                props.load(fis);
                temp = Double.parseDouble(props.getProperty("temp"));
                daylen = Double.parseDouble(props.getProperty("daylen"));
                setInputDate(props.getProperty("time"));
            }
        }
    }

    private void setInputDate(final String date) {

        if (date.indexOf('T') != -1) setDateAndTime(date);
        else setDate(date);

    }

    private void setDateAndTime(final String date) {

        String[] dateTime = date.split("T");
        String[] dateVec = dateTime[0].split("-");
        String[] timeVec = dateTime[1].split(":");

        if (timeVec.length < 3)
            time = new GregorianCalendar(Integer.parseInt(dateVec[0]),
                                         Integer.parseInt(dateVec[1])-1, // Months numbering starts from 0
                                         Integer.parseInt(dateVec[2]),
                                         Integer.parseInt(timeVec[0]),
                                         Integer.parseInt(timeVec[1]));
        else
            time = new GregorianCalendar(Integer.parseInt(dateVec[0]),
                                         Integer.parseInt(dateVec[1])-1, // Months numbering starts from 0
                                         Integer.parseInt(dateVec[2]),
                                         Integer.parseInt(timeVec[0]),
                                         Integer.parseInt(timeVec[1]),
                                         Integer.parseInt(timeVec[2]));

    }

    private void setDate(final String date) {

         String[] dateVec = date.split("-");
         time = new GregorianCalendar(Integer.parseInt(dateVec[0]),
                                      Integer.parseInt(dateVec[1])-1, // Months numbering starts from 0
                                      Integer.parseInt(dateVec[2]));

    }

    @Override
    public void update() {

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
        potET *= 25.4;    //System.out.println("Update2");
    }


    @Override
    public void finalize() {
        System.out.println(potET);
    }


    @Override
    public String getComponentName() {
        return "Hamon";
    }


    @Override
    public int getInputVarNameCount() {
        return 3;
    }


    @Override
    public int getOutputVarNameCount() {
        return 1;
    }


    @Override
    public String[] getInputVarNames() {
        return new String[]{"temp", "daylen", "time"};
    }


    @Override
    public String[] getOutputVarNames() {
        return new String[]{"potET"};
    }


    @Override
    public Object getValue(String name) throws Exception {
        if (name.equals("potet")) {
            return potET;
        }
        throw new IllegalArgumentException(name);
    }


    @Override
    public void setValue(String name, Object value) throws Exception {
        if (name.equals("temp")) {
            temp = Double.parseDouble(value.toString());
        }
        if (name.equals("daylen")) {
            daylen = Double.parseDouble(value.toString());
        } else {
            throw new IllegalArgumentException(name);
        }
    }


    @Override
    public String getVarType(String name) {
        return "double";
    }


    @Override
    public long getVarItemsize(String name) {
        // this is more involved here ... 
        return 0;
    }


    @Override
    public String getVarUnits(String name) {
        if (name.equals("temp")) {
            return "C";
        } else if (name.equals("daylen")) {
            return "h";
        }
        return "Variable not present";
    }


    @Override
    public void getCurrentTime(double time) {
    }


    @Override
    public void getStartTime(double time) {
    }


    @Override
    public void getEndTime(double end) {
    }


    @Override
    public void getTimeStep(double dt) {
    }


    @Override
    public void getTimeUnits(String units) {
    }

    @Override
    public String getGridType() {
        return "scalar";
    }
}
