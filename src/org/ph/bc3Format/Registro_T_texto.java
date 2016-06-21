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
public class Registro_T_texto {
    private final String codigoConcepto;
    private final String textoDescriptivo;

    public Registro_T_texto(String[] linea) {
        this.codigoConcepto = linea[1];
        this.textoDescriptivo = linea[2];
    }
    
    public String getCodigoConcepto() {
        return codigoConcepto;
    }

    public String getTextoDescriptivo() {
        return textoDescriptivo;
    }
}