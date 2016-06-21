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
package org.ph.bc3Format;

import java.util.ArrayList;
import java.util.Arrays;
import org.ph.stringUtils.StrUtils;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class EntidadDatos {

    // { [ TIPO ] \ [ SUBNOMBRE ] \ [ DIRECCIÓN ] \ [ CP ] \ [ LOCALIDAD ] \ [ PROVINCIA ] \ [ PAIS ] \ { TELEFONO ; } \ { FAX ; } \ { PERSONA_CONTACTO ; } \ }
    private String tipo;
    private String subnombre;
    private String direccion;
    private String codigoPostal;
    private String localidad;
    private String provincia;
    private String pais;
    
    private ArrayList <String> telefonos;
    private ArrayList <String> faxes;
    private ArrayList <String> personasContacto;
    
    private static int numeroEntidades = 0;
    
    public EntidadDatos (String[] datos) {
//        System.out.println(numeroEntidades + ": " + Arrays.toString(datos));
//        System.out.println("Número de piezas: " + datos.length);
        
        telefonos       = new ArrayList <String>();
        faxes           = new ArrayList <String>();
        personasContacto= new ArrayList <String>();
        
        tipo            = (datos.length > 0)? datos[0] : "";
        subnombre       = (datos.length > 1)? datos[1] : "";
        direccion       = (datos.length > 2)? datos[2] : "";
        codigoPostal    = (datos.length > 3)? datos[3] : "";
        localidad       = (datos.length > 4)? datos[4] : "";
        provincia       = (datos.length > 5)? datos[5] : "";
        pais            = (datos.length > 6)? datos[6] : "";
        
        if (datos.length > 7) {
            if (datos[7].contains(";")) {
                String[] tlfs = StrUtils.split(datos[7], ';');
                telefonos.addAll(Arrays.asList(tlfs));
            } else {
                String[] tlfs = {datos[7]};
                telefonos.addAll(Arrays.asList(tlfs));
            }
        } else {
//            telefonos.add("");
        }
        
        if (datos.length > 8) {
            if (datos[8].contains(";")) {
                String[] faxx = StrUtils.split(datos[8], ';');
                faxes.addAll(Arrays.asList(faxx));
            } else {
                String[] faxx = {datos[8]};
                faxes.addAll(Arrays.asList(faxx));
            }
        }
        
        if (datos.length > 9) {
            if (datos[9].contains(";")) {
                String[] pers = StrUtils.split(datos[9], ';');
                personasContacto.addAll(Arrays.asList(pers));
            } else {
                String[] pers = {datos[9]};
                personasContacto.addAll(Arrays.asList(pers));
            }
        }
        
//        System.out.println(numeroEntidades + ": " + Arrays.toString(datos));
        numeroEntidades++;
    }
    
    public EntidadDatos() {
        telefonos       = new ArrayList <String>();
        faxes           = new ArrayList <String>();
        personasContacto= new ArrayList <String>();
        
        tipo            = "";
        subnombre       = "";
        direccion       = "";
        codigoPostal    = "";
        localidad       = "";
        provincia       = "";
        pais            = "";
        
        telefonos.add("");
        faxes.add("");
        personasContacto.add("");
    }

    public String getTipo() {
        return tipo;
    }

    public String getSubnombre() {
        return subnombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPais() {
        return pais;
    }

    public ArrayList <String> getTelefonos() {
        return telefonos;
    }

    public ArrayList <String> getFaxes() {
        return faxes;
    }

    public ArrayList <String> getPersonasContacto() {
        return personasContacto;
    }
    
    public static int getNumeroEntidades() {
        return numeroEntidades;
    }
}