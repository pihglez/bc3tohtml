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

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class XmlLoader {
    
    private DOMImplementationRegistry   registry;
    private DOMImplementation           domImpl;
    private DOMImplementationLS         implLS;
    private LSParser                    parser;
    private DOMConfiguration            config;
    private DOMErrorHandlerImpl         errorHandler;
    private Document                    document;
    
    XmlLoader(String xmlFilePath){
        // http://www.oracle.com/technetwork/articles/entarch/load-save-dom-085736.html
        //          DOMImplementationSourceImpl as;   // para localizar la clase 
        //          -> se realiza la importación automáticamente y se añade a la
        //          siguiente línea como cadena
        
        System.setProperty(DOMImplementationRegistry.PROPERTY, "com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");

        try {
            registry    = DOMImplementationRegistry.newInstance();
            domImpl     = registry.getDOMImplementation("LS 3.0");
            implLS      = (DOMImplementationLS)domImpl;
            parser      = implLS.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, "http://www.w3.org/2001/XMLSchema");
            config      = parser.getDomConfig();
            errorHandler=new DOMErrorHandlerImpl();
            
            config.setParameter("error-handler", errorHandler);
            config.setParameter("validate" , Boolean.FALSE);
            // config.setParameter("validate" , Boolean.TRUE);
            // config.setParameter("schema-type" , "http://www.w3.org/2001/XMLSchema");
            // config.setParameter("validate-if-schema" , Boolean.TRUE);
            // config.setParameter("schema-location"  ,"tesauro.xsd");
            // document = parser.parseURI("tesauro.xml");
            
            document = parser.parseURI(xmlFilePath);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error DOM-XML (clase): "               + ex.getLocalizedMessage());
        } catch (InstantiationException ex) {
            System.out.println("Error DOM-XML (instanciación): "       + ex.getLocalizedMessage());
        } catch (IllegalAccessException ex) {
            System.out.println("Error DOM-XML (acceso ilegal): "       + ex.getLocalizedMessage());
        } catch (ClassCastException ex) {
            System.out.println("Error DOM-XML (conversión de clase): " + ex.getLocalizedMessage());
        }
        
    }
    
    public Document getXMLDocument() {
        return document;
    }
    
    private class DOMErrorHandlerImpl implements DOMErrorHandler{
        @Override
        public boolean handleError(DOMError error){
            System.out.println("Error de proceso DOM-XML: " + error.getMessage());
            
            return (error.getSeverity() ==  DOMError.SEVERITY_WARNING);
        }
    }
    
    /*
    String archivo = "./tesauro.xml";

    File f = new File(archivo);
    System.out.println("El archivo existe: " + f.exists());

    XmlLoader xl = new XmlLoader(archivo);
    XmlSaver xs;
    System.out.println("archivo " + "tesauro.xml" + " cargado correctamente.");

    xs = new XmlSaver(xl.getXMLDocument());
    int j = xl.getXMLDocument().getChildNodes().item(1).getChildNodes().getLength() - 3;
    NodeList nodes = xl.getXMLDocument().getChildNodes().item(1).getChildNodes();

    for (int i = 3; i < nodes.getLength() -3 ; i+=2) {
        System.out.println("nodo " + nodes.item(i).getNodeName() + " | " +
            nodes.item(i).getAttributes().item(0).getTextContent() + "  ::  " +
            nodes.item(i).getAttributes().item(1).getTextContent()
        );
    }

    xs.writeXML();
    System.out.println("archivo guardado correctamente.");
    */
}

