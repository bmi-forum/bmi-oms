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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import edu.colorado.csdms.bmi.BmiBase;

import oms3.Access;
import oms3.ComponentAccess;
import oms3.Conversions;
import oms3.annotations.Execute;
import oms3.annotations.Finalize;
import oms3.annotations.Initialize;

/**
 *
 *
 * @author sidereus
 * @date August 08, 2016
 */
public class Oms2BmiBase implements BmiBase {

    ComponentAccess omsComponent;
    String message = "Method not implemented yet";

    public Oms2BmiBase(final Object omsComponent) {
        this.omsComponent = new ComponentAccess(omsComponent);
    }

    private Reader readerFactory(final String fileName) {
        try {
            return new FileReader(fileName);
        } catch(FileNotFoundException exception) {
            exception.printStackTrace(System.err);
            System.exit(1);
            return null;
        }
    }

    @Override
    public void initialize(String fileName) {

        if (fileName != null) {
            Reader reader = readerFactory(fileName);

            Properties properties = new Properties();
            try {
                properties.load(reader);
                for (String propertyName : properties.stringPropertyNames()) {
                    Access access = omsComponent.input(propertyName);
                    Object object = Conversions.convert(properties.getProperty(propertyName), access.getField().getType());
                    access.setFieldValue(object);
                }
                reader.close();
            } catch(IOException exception) {
                System.out.println(exception.getMessage());
            } catch(Exception exception) {
                System.out.println(exception.getMessage());
            }

        }
        ComponentAccess.callAnnotated(omsComponent.getComponent(), Initialize.class, true);

    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void update() {
        ComponentAccess.callAnnotated(omsComponent.getComponent(), Execute.class, false);
    }

    @Override
    public void updateUntil(double time) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void updateFrac(double timeFrac) {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void finalize() {
        ComponentAccess.callAnnotated(omsComponent.getComponent(), Finalize.class, true);
    }

}

