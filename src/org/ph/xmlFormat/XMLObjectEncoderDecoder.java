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
import java.util.Arrays;
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
     * Método que serializa un objeto a un archivo en formato XML legible por el
     * usuario mediante JAXB
     * @param o <code>Object</code> el objeto a serializar
     * @param xmlFile <code>File</code> el archivo en el que se serializará el
     * objeto
     * @return <code>boolean</code> <b><code>true</code></b> en el caso de que la
     * serialización se realice adecuadamente y <code>false</code> en caso contrario
     */
    public static boolean marshal (Object o, File xmlFile) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(o.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // formateado e indentado
            jaxbMarshaller.marshal(o, xmlFile);
        } catch (JAXBException ex) {
            System.out.println("ERROR: en la creación del archivo XML de configuración " + xmlFile.getName() + "\n" + 
                    ex.getLocalizedMessage() + "\n" +
                    Arrays.toString(ex.getStackTrace()));
            return false;
        }
        
        return true;
    }
    
    /**
     * Método que carga un objeto desde un archivo XML mediante JAXB
     * @param o <code>Object</code> El tipo de objeto que se va a cargar desde
     * el archivo XML
     * @param xmlFile <code>File</code> El archivo XML que se quiere deserializar
     * @return <code>Object</code> El objeto que se ha codificado con anterioridad
     */
    public static Object unmarshal (Object o, File xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException ex) {
            System.out.println("ERROR: no se ha podiduo proceder a la descodificación del archivo " + xmlFile.getName() + "\n" + 
                    ex.getLocalizedMessage() + "\n" +
                    Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }
}
