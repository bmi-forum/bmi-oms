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
package wrappers;


import edu.colorado.csdms.bmi.*;
import wrappers.oms2bmImplementation.*;
import wrappers.oms2bmImplementation.grid.*;

/**
 * BMI wrapper for an OMS component.
 *
 * @author od, archita, sidereus
 */
public class BMIComponent implements BMI {

    BmiBase bmibaseDelegate;
    BmiGetter bmigetterDelegate;
    BmiGrid bmigridDelegate;
    BmiInfo bmiinfoDelegate;
    BmiSetter bmisetterDelegate;
    BmiTime bmitimeDelegate;
    BmiVars bmivarsDelegate;
    String message = "Method not implemented yet";

    BMIComponent(Object omscomp) {
        bmiAllocations(omscomp);
    }

    BMIComponent(Object omscomp, String gridType) {
        bmiAllocations(omscomp);
        bmigridDelegate = createBmiGrid(gridType, omscomp);
    }

    private void bmiAllocations(Object omscomp) {
        bmibaseDelegate = new Oms2BmiBase(omscomp);
        bmigetterDelegate = new Oms2BmiGetter(omscomp);
        bmiinfoDelegate = new Oms2BmiInfo(omscomp);
        bmisetterDelegate = new Oms2BmiSetter(omscomp);
        bmitimeDelegate = new Oms2BmiTime(omscomp);
        bmivarsDelegate = new Oms2BmiVars(omscomp);
    }

    private Oms2BmiGrid createBmiGrid(String gridType, final Object omscomp) {
        if (gridType.equals("rectilinear")) {
            return new Oms2BmiGridRectilinear(omscomp);
        } else if (gridType.equals("structuredQuad")) {
            return new Oms2BmiGridStructuredQuad(omscomp);
        } else if (gridType.equals("uniformRectilinear")) {
            return new Oms2BmiGridUniformRectilinear(omscomp);
        } else if (gridType.equals("unstructured")) {
            return new Oms2BmiGridUnstructured(omscomp);
        }

        throw new UnsupportedOperationException(gridType + " doesn't exist");

    }

    public static BMI create(Object comp) {
        return new BMIComponent(comp);
    }

    public static BMI create(Object comp, String gridType) {
        return new BMIComponent(comp, gridType);
    }

    // BMI BASE

    @Override
    public void initialize(String config_file) {
        bmibaseDelegate.initialize(config_file);
    }

    @Override
    public void initialize() {
        bmibaseDelegate.initialize();
    }

    @Override
    public void update() {
        bmibaseDelegate.update();
    }

    @Override
    public void updateUntil(double time) {
        bmibaseDelegate.updateUntil(time);
    }

    @Override
    public void updateFrac(double timeFrac) {
        bmibaseDelegate.updateFrac(timeFrac);
    }

    @Override
    public void finalize() {
        bmibaseDelegate.finalize();
    }

    // BMI GETTERS

    @SuppressWarnings("unchecked")
    @Override
    public Object getValue(String varName) {
        return bmigetterDelegate.getValue(varName);
    }

    @Override
    public <T> T getValueRef(String varName) {
        return bmigetterDelegate.getValueRef(varName);
    }

    @Override
    public <T> T getValueAtIndices(String varName, int[] indices) {
        return bmigetterDelegate.getValueAtIndices(varName, indices);
    }

    // BMI GRID

    @Override
    public int getGridRank(int gridId) {
        return bmigridDelegate.getGridRank(gridId);
    }

    @Override
    public int getGridSize(int gridId) {
        return bmigridDelegate.getGridSize(gridId);
    }

    @Override
    public String getGridType(final int gridId) {
        return bmigridDelegate.getGridType(gridId);
    }

    @Override
    public int[] getGridShape(int gridId) {

        if (bmigridDelegate instanceof BmiGridRectilinear)
            return ((BmiGridRectilinear) bmigridDelegate).getGridShape(gridId);
        else if (bmigridDelegate instanceof BmiGridStructuredQuad)
            return ((BmiGridStructuredQuad) bmigridDelegate).getGridShape(gridId);
        else if (bmigridDelegate instanceof BmiGridUniformRectilinear)
            return ((BmiGridUniformRectilinear) bmigridDelegate).getGridShape(gridId);

        else
            throw new UnsupportedOperationException();

    }

    @Override
    public double[] getGridX(int gridId) {

        if (bmigridDelegate instanceof BmiGridRectilinear)
            return ((BmiGridRectilinear) bmigridDelegate).getGridX(gridId);
        else if (bmigridDelegate instanceof BmiGridStructuredQuad)
            return ((BmiGridStructuredQuad) bmigridDelegate).getGridX(gridId);
        else if (bmigridDelegate instanceof BmiGridUnstructured)
            return ((BmiGridUnstructured) bmigridDelegate).getGridX(gridId);

        else
            throw new UnsupportedOperationException();

    }

    @Override
    public double[] getGridY(int gridId) {

        if (bmigridDelegate instanceof BmiGridRectilinear)
            return ((BmiGridRectilinear) bmigridDelegate).getGridY(gridId);
        else if (bmigridDelegate instanceof BmiGridStructuredQuad)
            return ((BmiGridStructuredQuad) bmigridDelegate).getGridY(gridId);
        else if (bmigridDelegate instanceof BmiGridUnstructured)
            return ((BmiGridUnstructured) bmigridDelegate).getGridY(gridId);

        else
            throw new UnsupportedOperationException();

    }

    @Override
    public double[] getGridZ(int gridId) {

        if (bmigridDelegate instanceof BmiGridRectilinear)
            return ((BmiGridRectilinear) bmigridDelegate).getGridZ(gridId);
        else if (bmigridDelegate instanceof BmiGridStructuredQuad)
            return ((BmiGridStructuredQuad) bmigridDelegate).getGridZ(gridId);
        else if (bmigridDelegate instanceof BmiGridUnstructured)
            return ((BmiGridUnstructured) bmigridDelegate).getGridZ(gridId);

        else
            throw new UnsupportedOperationException();

    }

    @Override
    public double[] getGridSpacing(int gridId) {

        if (bmigridDelegate instanceof BmiGridUniformRectilinear)
            return ((BmiGridUniformRectilinear) bmigridDelegate).getGridSpacing(gridId);

        else throw new UnsupportedOperationException();

    }

    @Override
    public double[] getGridOrigin(int gridId) {

        if (bmigridDelegate instanceof BmiGridUniformRectilinear)
            return ((BmiGridUniformRectilinear) bmigridDelegate).getGridSpacing(gridId);

        else throw new UnsupportedOperationException();

    }

    @Override
    public int[] getGridConnectivity(int gridId) {

        if (bmigridDelegate instanceof BmiGridUnstructured)
            return ((BmiGridUnstructured) bmigridDelegate).getGridConnectivity(gridId);

        else throw new UnsupportedOperationException();

    }

    @Override
    public int[] getGridOffset(int gridId) {

        if (bmigridDelegate instanceof BmiGridUnstructured)
            return ((BmiGridUnstructured) bmigridDelegate).getGridOffset(gridId);

        else throw new UnsupportedOperationException();

    }

    // BMI INFO

    @Override
    public String getComponentName() {
        return bmiinfoDelegate.getComponentName();
    }

    @Override
    public String[] getInputVarNames() {
        return bmiinfoDelegate.getInputVarNames();
    }

    @Override
    public int getInputVarNameCount() {
        return bmiinfoDelegate.getInputVarNameCount();
    }

    @Override
    public String[] getOutputVarNames() {
        return bmiinfoDelegate.getOutputVarNames();
    }

    @Override
    public int getOutputVarNameCount() {
        return bmiinfoDelegate.getOutputVarNameCount();
    }

    // BMI SETTER

    @Override
    public void setValue(String name, double[] src) {
        bmisetterDelegate.setValue(name, src);
    }

    @Override
    public void setValue(String name, int[] src) {
        bmisetterDelegate.setValue(name, src);
    }

    @Override
    public void setValue(String name, String[] src) {
        bmisetterDelegate.setValue(name, src);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, double[] src) {
        bmisetterDelegate.setValueAtIndices(varName, indices, src);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, int[] src) {
        bmisetterDelegate.setValueAtIndices(varName, indices, src);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, String[] src) {
        bmisetterDelegate.setValueAtIndices(varName, indices, src);
    }

    // BMI TIME

    @Override
    public double getStartTime() {
        return bmitimeDelegate.getStartTime();
    }

    @Override
    public double getCurrentTime() {
        return bmitimeDelegate.getCurrentTime();
    }

    @Override
    public double getEndTime() {
        return bmitimeDelegate.getEndTime();
    }

    @Override
    public double getTimeStep() {
        return bmitimeDelegate.getTimeStep();
    }

    @Override
    public String getTimeUnits() {
        return bmitimeDelegate.getTimeUnits();
    }

    // BMI VARS

    @Override
    public String getVarType(String name) {
        return bmivarsDelegate.getVarType(name);
    }

    @Override
    public String getVarUnits(String name) {
        return getVarUnits(name);
    }

    @Override
    public int getVarItemsize(String name) {
        return bmivarsDelegate.getVarItemsize(name);
    }

    @Override
    public int getVarNbytes(String varName) {
        return bmivarsDelegate.getVarNbytes(varName);
    }

    @Override
    public int getVarGrid(String varName) {
        return bmivarsDelegate.getVarGrid(varName);
    }

}
