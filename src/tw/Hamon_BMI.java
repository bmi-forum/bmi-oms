package tw;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import edu.colorado.csdms.bmi.BMI;

public class Hamon_BMI implements BMI {

    public static double temp;
    public static double daylen;
    public Calendar time;
    public double potET;

    @Override
    public void initialize(String config_file) {
        if (config_file != null) {
            Properties props;
            try (FileInputStream fis = new FileInputStream(config_file)) {
                props = new Properties();
                props.load(fis);
                temp = Double.parseDouble(props.getProperty("temp"));
                daylen = Double.parseDouble(props.getProperty("daylen"));
                setInputDate(props.getProperty("time"));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
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
                                         Integer.parseInt(dateVec[1])-1,
                                         Integer.parseInt(dateVec[2]),
                                         Integer.parseInt(timeVec[0]),
                                         Integer.parseInt(timeVec[1]));
        else
            time = new GregorianCalendar(Integer.parseInt(dateVec[0]),
                                         Integer.parseInt(dateVec[1])-1,
                                         Integer.parseInt(dateVec[2]),
                                         Integer.parseInt(timeVec[0]),
                                         Integer.parseInt(timeVec[1]),
                                         Integer.parseInt(timeVec[2]));

    }

    private void setDate(final String date) {

         String[] dateVec = date.split("-");
         time = new GregorianCalendar(Integer.parseInt(dateVec[0]),
                                      Integer.parseInt(dateVec[1])-1,
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

    @SuppressWarnings("unchecked")
    @Override
    public Object getValue(String name) {
        // try (name.equals("potet")) {
        //     return potET;
        // } catch(IOException exception) {
        //     System.out.println(exception.getMessage());
        // }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(String name, double[] value) {
        if (name.equals("temp")) {
            temp = value[0];
        }
    }

    @Override
    public void setValue(String name, int[] value) {
        if (name.equals("daylen")) {
            daylen = value[0];
        }
    }

    @Override
    public String getVarType(String name) {
        return "double";
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
    public void initialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateFrac(double arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUntil(double arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getCurrentTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getEndTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getStartTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getTimeStep() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getTimeUnits() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getVarGrid(String arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getVarNbytes(String arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T> T getValueAtIndices(String arg0, int[] arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T getValueRef(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setValue(String arg0, String[] arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValueAtIndices(String arg0, int[] arg1, double[] arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValueAtIndices(String arg0, int[] arg1, int[] arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValueAtIndices(String arg0, int[] arg1, String[] arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public int[] getGridShape(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double[] getGridX(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double[] getGridY(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double[] getGridZ(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getGridRank(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getGridSize(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getGridType(int arg0) {
        // TODO Auto-generated method stub
        return "scalar";
    }

    @Override
    public double[] getGridOrigin(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double[] getGridSpacing(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getGridConnectivity(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getGridOffset(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getVarItemsize(String arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
