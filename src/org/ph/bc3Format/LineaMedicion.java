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

import org.ph.stringUtils.StrUtils;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class LineaMedicion {
    // { TIPO \ COMENTARIO { # ID_BIM } \ UNIDADES \ LONGITUD \ LATITUD \ ALTURA \ }
    private int     tipo;
    private String  comentario;
    private String  id_bim;
    private double  unidades, longitud, latitud, altura;
    
    public LineaMedicion (String linea) {
        
        String[] lineaM = StrUtils.split(linea, '\\');
        tipo        = ((lineaM[0].length() > 0) ? Integer.parseInt(lineaM[0]) : 0);
        comentario  = lineaM[1];
        id_bim      = "";    // a implementar
        unidades    = ((lineaM[2].length() > 0) ? Double.parseDouble(lineaM[2]) : 0);
        longitud    = ((lineaM[3].length() > 0) ? Double.parseDouble(lineaM[3]) : 0);
        latitud     = ((lineaM[4].length() > 0) ? Double.parseDouble(lineaM[4]) : 0);
        altura      = ((lineaM[5].length() > 0) ? Double.parseDouble(lineaM[5]) : 0);
    }
    
    public LineaMedicion (int tipo, String comentario, double unidades, double longitud, double latitud, double altura) {
        this.tipo       = tipo;
        this.comentario = comentario;
        id_bim          = "";   // a implementar
        this.unidades   = unidades;
        this.longitud   = longitud;
        this.latitud    = latitud;
        this.altura     = altura;
    }

    public int getTipo() {
        return tipo;
    }

    public String getComentario() {
        return comentario;
    }

    public String getId_bim() {
        return id_bim;
    }

    public double getUnidades() {
        return unidades;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getAltura() {
        return altura;
    }
}