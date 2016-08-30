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
import org.ph.stringUtils.StrUtils;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Registro_E_entidad {
    /*
    ~E | CODIGO_ENTIDAD | [ RESUMEN ] | [ NOMBRE ] |
    { [ TIPO ] \ [ SUBNOMBRE ] \ [ DIRECCIÓN ] \ [ CP ] \ [ LOCALIDAD ] \ [ PROVINCIA ] \ [ PAIS ] \ { TELEFONO ; } \ { FAX ; } \ { PERSONA_CONTACTO ; } \ } |
    [ CIF ] \ [ WEB ] \ [ EMAIL ] \ |
    */
    
    private String                      codigo_entidad;
    private String                      resumen;
    private String                      nombre;
    
    private ArrayList <EntidadDatos>    datosEntidad;
    private String                      cif;
    private String                      web;
    private String                      email;
    
    private static int numeroEntidades = 0;
    
    public Registro_E_entidad (String[] linea) {
//        if (numeroEntidades > 32) System.out.print("<<<<" + Arrays.toString(linea) + ">>>> [" + linea.length + "]");
        datosEntidad = new ArrayList <EntidadDatos>();
        String[] datosEntid;
        
        switch (linea.length) {
            case 2:
                codigo_entidad  = (linea.length > 1) ? linea[1] : "";
                resumen         = "";
                nombre          = "";
                datosEntidad.add(new EntidadDatos());
                cif             = "";
                web             = "";
                email           = "";
                break;
            case 3:
                codigo_entidad  = (linea.length > 1) ? linea[1] : "";
                resumen         = (linea.length > 2) ? linea[2] : "";
                nombre          = "";
                datosEntidad.add(new EntidadDatos());
                cif             = "";
                web             = "";
                email           = "";
                break;
            case 4:
                codigo_entidad  = (linea.length > 1) ? linea[1] : "";
                resumen         = (linea.length > 2) ? linea[2] : "";
                nombre          = (linea.length > 3) ? linea[3] : "";
                datosEntidad.add(new EntidadDatos());
                break;
            case 5:
                codigo_entidad  = (linea.length > 1) ? linea[1] : "";
                resumen         = (linea.length > 2) ? linea[2] : "";
                nombre          = (linea.length > 3) ? linea[3] : "";
                datosEntid      = StrUtils.split(linea[4], '\\');
                datosEntidad.add(new EntidadDatos(datosEntid));
                break;
            case 6:
                codigo_entidad  = (linea.length > 1) ? linea[1] : "";
                
                resumen         = (linea.length > 2) ? linea[2] : "";
                
                nombre          = (linea.length > 3) ? linea[3] : "";
                
                datosEntid      = StrUtils.split(linea[4], '\\');
                datosEntidad.add(new EntidadDatos(datosEntid));
                if (linea[5].contains("\\")) {
                    String[] contacto = StrUtils.split(linea[5], '\\');
                    switch (contacto.length) {
                        case 1:
                            cif     = contacto[0];
                            web     = "";
                            email   = "";
                            break;
                        case 2:
                            cif     = contacto[0];
                            web     = contacto[1];
                            email   = "";
                            break;
                        case 3:
                            cif     = contacto[0];
                            web     = contacto[1];
                            email   = contacto[2];
                            break;
                    }
                } else {
                    cif     = "";
                    web     = "";
                    email   = "";
                }
                break;
        }
        
        
        
        numeroEntidades++;
//        if (numeroEntidades > 32) System.out.println("     <<< Ok >>>");
    }

    public String getCodigo_entidad() {
        return codigo_entidad;
    }

    public String getResumen() {
        return resumen;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList <EntidadDatos> getDatosEntidad() {
        return datosEntidad;
    }

    public String getCif() {
        return cif;
    }

    public String getWeb() {
        return web;
    }

    public String getEmail() {
        return email;
    }
}
