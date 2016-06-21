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
public class Registro_G_informacionGrafica {
    private String codigo_concepto;
    private ArrayList <String> informacionGrafica;
    private String url_ext;
    
    private static int numeroRegistrosGraficos = 0;
    
    public Registro_G_informacionGrafica (String[] linea) {
        informacionGrafica = new ArrayList <String> ();
        codigo_concepto = linea [1];
        
        
        if (linea.length > 1) {
            String[] infoG = StrUtils.split(linea[2], '\\');
            informacionGrafica.addAll(Arrays.asList(infoG));
        }
        
        if (linea.length > 3) {
            url_ext = linea[3];
        } else url_ext = "";
        
        numeroRegistrosGraficos++;
    }

    public String getCodigo_concepto() {
        return codigo_concepto;
    }

    public ArrayList <String> getInformacionGrafica() {
        return informacionGrafica;
    }

    public String getUrl_ext() {
        return url_ext;
    }
    
    public static int getNumeroRegistrosGraficos() {
        return numeroRegistrosGraficos;
    }
}