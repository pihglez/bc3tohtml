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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class XMLObjectEncoderDecoder {
    
    /**
     * <a href="https://docs.oracle.com/javase/tutorial/javabeans/advanced/persistence.html">
     * documentación de Oracle acerca de la persistencia</a>
     * Se serializan todos los campos/atributos salvo los estáticos <code>static</code>
     * y los transitorios <code>transient</code>
     * @param o <code>Object</code> Objeto o clase que se quiere serializar en un
     * archivo XML
     * @param xmlFile <code>File</code> Archivo en el que se serializa el objeto
     * <b>o</b>
     * @return <code>boolean</code> <code>true</code> en el caso de que la codificación del
     * objeto se haya realizado correctamente y <code>false</code> en caso contrario
     */
    public static boolean encode (Object o, File xmlFile) {
        // http://www.oracle.com/technetwork/java/persistence4-140124.html
        XMLEncoder encoder = null;
        System.out.println("Escribiendo configuración desde " + o.getClass().getName());
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(xmlFile)));
            encoder.writeObject(o);
        }catch(FileNotFoundException ex){
            System.out.println("ERROR: en la creación del archivo XML de configuración " + xmlFile.getName() + "\n" + 
                    ex.getLocalizedMessage());
            encoder.close();
            return false;
        }
        
        encoder.close();
        
        return true;
    }
    
    /**
     * Método que carga un objeto desde un archivo XML
     * @param xmlFile <code>File</code> El archivo xml que se quiere descodificar
     * @return <code>Object</code> El objeto que se ha codificado con anterioridad
     */
    public static Object decode(File xmlFile) {
        XMLDecoder decoder = null;
            try {
                decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(xmlFile)));
            } catch (FileNotFoundException ex) {
                System.out.println("ERROR: no se ha podiduo proceder a la descodificación del archivo " + xmlFile.getName() + "\n" + 
                    ex.getLocalizedMessage());
            }
        return decoder.readObject();
    }
    
    
    public static boolean marshal (Object o, File xmlFile) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(o.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(o, xmlFile);
        } catch (JAXBException ex) {
            System.out.println("ERROR: en la creación del archivo XML de configuración " + xmlFile.getName() + "\n" + 
                    ex.getLocalizedMessage() + "\n" +
                    ex.getStackTrace());
            return false;
        }
        
        return true;
    }
    
    
    public static Object unmarshal (Object o, File xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException ex) {
            System.out.println("ERROR: no se ha podiduo proceder a la descodificación del archivo " + xmlFile.getName() + "\n" + 
                    ex.getLocalizedMessage() + "\n" +
                    ex.getStackTrace());
        }
        return null;
    }
}
