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
public class Registro_D_descomposicion {
    // ~D | Código padre | Código hijo 1 \ Factor 1 \ Rendimiento 1 \ Código hijo 2 \ Factor 2 \ Rendimiento 2 \... |
    private final String codigoPadre;
    private final ArrayList <CodigosHijos> descomp;

    public String getCodigoPadre() {
        return codigoPadre;
    }

    public ArrayList <CodigosHijos> getCodigosHijos() {
        return descomp;
    }
    
    public Registro_D_descomposicion (String[] linea){
        this.codigoPadre = linea[1];
        this.descomp = new ArrayList<>();
        
        String[] hijosDatos = StrUtils.split(linea[2], '\\');
        
        for (int i = 0; i < hijosDatos.length - 2; i += 3) {
            descomp.add(new CodigosHijos(hijosDatos[i], 
                    Double.parseDouble(hijosDatos[i+1]), 
                    Double.parseDouble(hijosDatos[i+2]))
            );
            
        }
    }
}