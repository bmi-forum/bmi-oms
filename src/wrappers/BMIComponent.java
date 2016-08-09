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

import java.lang.reflect.Field;

import edu.colorado.csdms.bmi.BMI;
import edu.colorado.csdms.bmi.BmiBase;
import oms3.Access;
import oms3.ComponentAccess;
import oms3.Conversions;
import oms3.annotations.*;
import wrappers.oms2bmImplementation.Oms2BmiBase;

/**
 * BMI wrapper for an OMS component.
 *
 * @author od, archita, sidereus
 */
public class BMIComponent implements BMI {

    ComponentAccess comp;
    BmiBase bmibaseDelegate;
    String message = "Method not implemented yet";

    BMIComponent(Object omscomp) {
        comp = new ComponentAccess(omscomp);
        bmibaseDelegate = new Oms2BmiBase(omscomp);
    }

    public static BMI create(Object comp) {
        return new BMIComponent(comp);
    }

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

    @Override
    public String getComponentName() {

        Name n = comp.getComponent().getClass().getAnnotation(Name.class);
        return n != null ? n.value() : "<none>";
    }

    @Override
    public int getInputVarNameCount() {
        return comp.inputs().size();
    }

    @Override
    public int getOutputVarNameCount() {
        return comp.outputs().size();
    }

    @Override
    public String[] getInputVarNames() {
        Access[] a = comp.inputs().toArray(new Access[0]);
        return toString(a);
    }

    @Override
    public String[] getOutputVarNames() {
        Access[] a = comp.outputs().toArray(new Access[0]);
        return toString(a);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getValue(String name) {
        Access a = comp.output(name);
        Object returningObject = null;

        try {
            returningObject = a.getField().get(comp.getComponent());
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return returningObject;
    }

    private void genericSetValue(String name, Object src) {
        Access a = comp.input(name);

        try {
            Field field = a.getField();
            field.set(comp.getComponent(), Conversions.convert(src, a.getField().getType()));
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void setValue(String name, double src) {
        genericSetValue(name, src);
    }

    private void setValue(String name, int src) {
        genericSetValue(name, src);
    }

    private void setValue(String name, String src) {
        genericSetValue(name, src);
    }

    @Override
    public void setValue(String name, double[] src) {
        if (src.length == 1) setValue(name, src[0]);
        else genericSetValue(name, src);
    }

    @Override
    public void setValue(String name, int[] src) {
        if (src.length == 1) setValue(name, src[0]);
        else genericSetValue(name, src);
    }

    @Override
    public void setValue(String name, String[] src) {
        if (src.length ==1) setValue(name, src[0]);
        else genericSetValue(name, src);
    }

    private String[] toString(Access[] a) {
        String[] s = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            s[i] = a[i].getField().getName();
        }
        return s;
    }

    @Override
    public String getVarType(String name) {
        Access a = comp.output(name);
        if (a == null) {
            throw new IllegalArgumentException("No such name " + name);
        }
        return a.getField().getType().toString();
    }

    @Override
    public int getVarItemsize(String name) {
        throw new UnsupportedOperationException(message);
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
    public double getCurrentTime() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getStartTime() {
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

    @Override
    public String getGridType(final int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int[] getGridConnectivity(int gridId) {
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

    @Override
    public int[] getGridOffset(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getGridRank(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getGridSize(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, int[] src) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, double[] src) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void setValueAtIndices(String varName, int[] indices, String[] src) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int[] getGridShape(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <T> T getValueRef(String varName) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <T> T getValueAtIndices(String varName, int[] indices) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getVarGrid(String varName) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int getVarNbytes(String varName) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double[] getGridOrigin(int gridId) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double[] getGridSpacing(int gridId) {
        throw new UnsupportedOperationException(message);
    }

}
