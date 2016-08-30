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
public class Registro_L_pliegos {

    public static int getNumeroPliegos() {
        return numeroPliegos;
    }
    // ~L | CODIGO_CONCEPTO | { CODIGO_SECCION_PLIEGO \ TEXTO_SECCION_PLIEGO \ } |
    // { CODIGO_SECCION_PLIEGO \ ARCHIVO_TEXTO_RTF \ } |
    // { CODIGO_SECCION_PLIEGO \ ARCHIVO_TEXTO_HTM \ } |
    
    private String                      codigo_concepto;
    private ArrayList <PliegoSeccion>   seccionesPliego;
//    private String                      rotulo_seccion_pliego;
    
    private String                      archivo_texto_rtf;
    private String                      archivo_texto_htm;
    private static int numeroPliegos = 0;
    
    public Registro_L_pliegos (String[] linea) {
        numeroPliegos++;
//        if (numeroPliegos == 1) System.out.println("Línea de pliego: " + Arrays.toString(linea));
        codigo_concepto = linea[1];
        seccionesPliego = new ArrayList<PliegoSeccion>();
        setSeccionesPliego(linea[2]);
        
        if (linea.length > 3) {
            setArchivoRTF(linea[3]);
        } else {setArchivoRTF("");}
        
        if (linea.length > 4) {
            setArchivoRTF(linea[4]);
        } else {setArchivoHTM("");}
    }
    
    private void setSeccionesPliego(String seccionesPliego) {
        String[] sec = StrUtils.split(seccionesPliego, '\\');
        for (int i = 0; i < (sec.length / 2); i += 2) {
            this.seccionesPliego.add(new PliegoSeccion(sec[i], sec[i+1]));
        }
    }
    
    private void setArchivoRTF (String archivo) {
        archivo_texto_rtf = archivo;
    }
    
    private void setArchivoHTM (String archivo) {
        archivo_texto_htm = archivo;
    }

    public String getCodigo_concepto() {
        return codigo_concepto;
    }

    public ArrayList <PliegoSeccion> getSeccionesPliego() {
        return seccionesPliego;
    }

    public String getArchivo_texto_rtf() {
        return archivo_texto_rtf;
    }

    public String getArchivo_texto_htm() {
        return archivo_texto_htm;
    }
}
