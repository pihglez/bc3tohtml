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

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class PliegoSeccion {
    private String codigo_seccion_pliego,       // DEFINICION del CODIGO asociado a cada pliego.
            texto_seccion_pliego;               // TEXTO_SECCION_PLIEGO: Texto asignado a cada faceta o SECCION del pliego de condiciones del concepto.
    
    public PliegoSeccion (String codigoSeccion, String textoSeccion) {
        codigo_seccion_pliego   = codigoSeccion;
        texto_seccion_pliego    = textoSeccion;
    }

    public String getCodigo_seccion_pliego() {
        return codigo_seccion_pliego;
    }

    public String getTexto_seccion_pliego() {
        return texto_seccion_pliego;
    }   
}