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
import oms3.Access;
import oms3.ComponentAccess;
import oms3.annotations.*;
import wrappers.oms2bmImplementation.*;

/**
 * BMI wrapper for an OMS component.
 *
 * @author od, archita, sidereus
 */
public class BMIComponent implements BMI {

    ComponentAccess comp;
    BmiBase bmibaseDelegate;
    BmiGetter bmigetterDelegate;

    BmiInfo bmiinfoDelegate;
    BmiSetter bmisetterDelegate;
    String message = "Method not implemented yet";

    BMIComponent(Object omscomp) {
        comp = new ComponentAccess(omscomp);
        bmibaseDelegate = new Oms2BmiBase(omscomp);
        bmigetterDelegate = new Oms2BmiGetter(omscomp);

        bmiinfoDelegate = new Oms2BmiInfo(omscomp);
        bmisetterDelegate = new Oms2BmiSetter(omscomp);
    }

    public static BMI create(Object comp) {
        return new BMIComponent(comp);
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
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getGridSize(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public String getGridType(final int gridId) {
        throw new UnsupportedOperationException(message);
    }

    // BMI GRID RECTILINEAR
    // BMI GRID STRUCTURED QUAD

    @Override
    public int[] getGridShape(int gridId) {
        throw new UnsupportedOperationException(message);
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

    // BMI GRID UNIFORM RECTILINEAR

    @Override
    public double[] getGridSpacing(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double[] getGridOrigin(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    // BMI GRID UNSTRUCTURED

    @Override
    public int[] getGridConnectivity(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int[] getGridOffset(int gridId) {
        throw new UnsupportedOperationException(message);
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

    // BMI VARS

    @Override
    public String getVarType(String name) {
        Access a = comp.output(name);
        if (a == null) {
            throw new IllegalArgumentException("No such name " + name);
        }
        return a.getField().getType().toString();
    }

    @Override
    public String getVarUnits(String name) {
        Access a = comp.output(name);
        if (a == null) {
            throw new IllegalArgumentException("No such name " + name);
        }
        Unit u = a.getField().getAnnotation(Unit.class);
        return u != null ? u.value() : "";
    }

    @Override
    public int getVarItemsize(String name) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getVarNbytes(String varName) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getVarGrid(String varName) {
        throw new UnsupportedOperationException(message);
    }

}
