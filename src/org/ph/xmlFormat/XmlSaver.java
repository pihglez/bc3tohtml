/*
 * Copyright (C) 2016 Pedro I. Hernández G. <pihglez@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ph.xmlFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;


/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class XmlSaver {
    
    private DOMImplementationRegistry   registry;
    private DOMImplementation           domImpl;
    private DOMImplementationLS         implLS;
//    private LSParser                    parser;
//    private DOMConfiguration            config;
//    private DOMErrorHandlerImpl         errorHandler;
    private Document                    document;
    private LSSerializer                dom3Writer;
    private LSOutput                    output;
    private OutputStream                outputStream;
    
    
    XmlSaver(Document xmlDocument){
        // http://www.oracle.com/technetwork/articles/entarch/load-save-dom-092439.html
        System.setProperty(DOMImplementationRegistry.PROPERTY, "com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
        
        try {
            registry    = DOMImplementationRegistry.newInstance();
            domImpl     = registry.getDOMImplementation("LS 3.0");
            implLS      = (DOMImplementationLS) domImpl;
            dom3Writer  = implLS.createLSSerializer();
            output      = implLS.createLSOutput();
            
            outputStream = new FileOutputStream(new File("tesauro_parsed.xml"));
            output.setByteStream(outputStream);
            output.setEncoding("UTF-8");
            
            document = xmlDocument;
            
//            String s = dom3Writer.writeToString(document);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error DOM-XML: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println("Error DOM-XML: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println("Error DOM-XML: " + ex.getMessage());
        } catch (ClassCastException ex) {
            System.out.println("Error DOM-XML: " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("Error DOM-XML: " + ex.getMessage());
        }
        
    }
    
    public boolean writeXML() {
        dom3Writer.write(document, output);
        
        try {
            outputStream.close();
        } catch (IOException ex) {
            System.out.println("Error cerrando el archivo XML");
        }
        return true;
    }
    
    
    private class DOMErrorHandlerImpl implements DOMErrorHandler{

        @Override
        public boolean handleError(DOMError error){
            System.out.println("Error DOM-XML: " + error.getMessage());
            
            return (error.getSeverity() ==  DOMError.SEVERITY_WARNING);
        }

    }
}

