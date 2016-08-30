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

import org.ph.bc3tohtml.LineaComandos;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Registro_V_prpdad {
    private boolean MALFORMADO;
    private String  PROPIEDAD_ARCHIVO;                  // Redactor de la BBDD u obra
    private String  VERSION_FORMATO;                    // Versión del formato del archivo. Actual: FIEBDC-3/2016
    private String  VERSION_FORMATO_FECHA;              // DDMMAAAA (consultar formato para su gestión)
    private String  PROGRAMA_EMISION;                   // Programa y/o empresa que genera el archivo BC3
    private String  CABECERA;                           // Título general de los ROTULOS_IDENTIFICACION
    private String  CABECERA_ROTULO_IDENTIFICACION;     // 
    private String  JUEGO_CARACTERES;                   // Juego de caracteres (omitido, se asume el 850)
    private String  COMENTARIO;                         // Comentario al contenido del archivo
    private String  TIPO_INFORMACION;                   // 1: BBDD, 2: Pres, 3: Certif., 4: actualización de la BBDD
    private String  NUMERO_CERTIFICACION;               // Nº de certificación
    private String  FECHA_CERTIFICACION;                // Fecha de la certificación
    private String  URL_BASE;                           // Url a partir de la que se encuentran los documentos y gráficos

    public Registro_V_prpdad(String[] lineaRegistroV){
        if (lineaRegistroV.length > 0) {
            try {
                PROPIEDAD_ARCHIVO               = lineaRegistroV[1];
                VERSION_FORMATO                 = lineaRegistroV[2];
                VERSION_FORMATO_FECHA           = "";
                PROGRAMA_EMISION                = lineaRegistroV[3];
                CABECERA                        = lineaRegistroV[4];
                CABECERA_ROTULO_IDENTIFICACION  = "";
                
                if (lineaRegistroV.length > 5) {
                    JUEGO_CARACTERES            = lineaRegistroV[5];
                    COMENTARIO                  = lineaRegistroV[6];
                    TIPO_INFORMACION            = lineaRegistroV[7];
                    NUMERO_CERTIFICACION        = lineaRegistroV[8];
                    FECHA_CERTIFICACION         = lineaRegistroV[9];
                    URL_BASE                    = lineaRegistroV[10];
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                if (LineaComandos.modoVerbose) System.out.println("  Ha habido un error en la lectura de datos extendida.");
            }
            MALFORMADO = false;
        } else {
            MALFORMADO = true;
        }
    }    
    
    public String getPROPIEDAD_ARCHIVO() {
        return PROPIEDAD_ARCHIVO;
    }

    public String getVERSION_FORMATO() {
        return VERSION_FORMATO;
    }

    public String getVERSION_FORMATO_FECHA() {
        return VERSION_FORMATO_FECHA;
    }

    public String getPROGRAMA_EMISION() {
        return PROGRAMA_EMISION;
    }

    public String getCABECERA() {
        return CABECERA;
    }

    public String getROTULO_IDENTIFICACION() {
        return CABECERA_ROTULO_IDENTIFICACION;
    }

    public String getJUEGO_CARACTERES() {
        return JUEGO_CARACTERES;
    }

    public String getCOMENTARIO() {
        return COMENTARIO;
    }

    public String getTIPO_INFORMACION() {
        return TIPO_INFORMACION;
    }

    public String getNUMERO_CERTIFICACION() {
        return NUMERO_CERTIFICACION;
    }

    public String getFECHA_CERTIFICACION() {
        return FECHA_CERTIFICACION;
    }

    public String getURL_BASE() {
        return URL_BASE;
    }
}
