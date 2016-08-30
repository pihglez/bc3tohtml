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
public class Registro_M_mediciones {
    
    // ~M | [ CODIGO_PADRE \ ] CODIGO_HIJO | { POSICION \ } | MEDICION_TOTAL | 
    // { TIPO \ COMENTARIO { # ID_BIM } \ UNIDADES \ LONGITUD \ LATITUD \ ALTURA \ } 
    // | [ ETIQUETA ] |
    
    private String codigoPadre, codigoHijo, etiqueta;

    int[] posicion;
    
    private double medicionTotal;
    
    private ArrayList <LineaMedicion> mediciones;
    
    private static int numeroLineasMedicion = 0;
    
    public Registro_M_mediciones (String[] linea) {
        String codigos[] = StrUtils.split(linea[1], '\\');
        codigoPadre = codigos[0];
        codigoHijo  = (codigos.length > 1) ? codigos[1] : "";
        
        String posiciones[] = StrUtils.split(linea[2], '\\');
        
        posicion = new int[posiciones.length];
        for (int i = 0; i < posiciones.length; i++) {
            posicion[i] = Integer.parseInt(posiciones[i]);
        }
        
        medicionTotal = Double.parseDouble(linea[3]);
        mediciones = new ArrayList <LineaMedicion>();
        
        //  { TIPO \ COMENTARIO { # ID_BIM } \ UNIDADES \ LONGITUD \ LATITUD \ ALTURA \ }
        String lineasMedicion[] = StrUtils.split(linea[4], '\\');
        
        // tipo, descripcion, unidades, longitud, latitud, altura
        // [0]   0,  1,  2,  3,  4,  5
        // [1]   6,  7,  8,  9, 10, 11
        // [2]  12, 13, 14, 15, 16, 17
        // [3]  18, 19, 20, 21, 22, 23
        // [4]  24, 25, 26, 27, 28, 29
        for (int j = 0; j < ((lineasMedicion.length - 5) / 6); j++) {
            mediciones.add(new LineaMedicion(
                    ((!(lineasMedicion[j * 6 + 0] == null) && (lineasMedicion[j * 6 + 0].length() > 0)) ? Integer.parseInt(lineasMedicion[j * 6 + 0]) : 0),
                    ((!(lineasMedicion[j * 6 + 1] == null) && (lineasMedicion[j * 6 + 1].length() > 0)) ? lineasMedicion[j * 6 + 1] : ""),
                    ((!(lineasMedicion[j * 6 + 2] == null) && (lineasMedicion[j * 6 + 2].length() > 0)) ? Double.parseDouble(lineasMedicion[j * 6 + 2]) : 0d),
                    ((!(lineasMedicion[j * 6 + 3] == null) && (lineasMedicion[j * 6 + 3].length() > 0)) ? Double.parseDouble(lineasMedicion[j * 6 + 3]) : 0d), 
                    ((!(lineasMedicion[j * 6 + 4] == null) && (lineasMedicion[j * 6 + 4].length() > 0)) ? Double.parseDouble(lineasMedicion[j * 6 + 4]) : 0d), 
                    ((!(lineasMedicion[j * 6 + 5] == null) && (lineasMedicion[j * 6 + 5].length() > 0)) ? Double.parseDouble(lineasMedicion[j * 6 + 5]) : 0d)
            ));
//            System.out.println("[" + j + "]"
//                    + " | " + mediciones.get(j).getTipo()
//                    + " | " + mediciones.get(j).getComentario()
//                    + " | " + mediciones.get(j).getUnidades()
//                    + " | " + mediciones.get(j).getLatitud()
//                    + " | " + mediciones.get(j).getLongitud()
//                    + " | " + mediciones.get(j).getAltura());
        }
        
        etiqueta = (linea.length > 5) ? linea[5] : "";
        
        numeroLineasMedicion++;
    }

    public String getCodigoPadre() {
        return codigoPadre;
    }

    public String getCodigoHijo() {
        return codigoHijo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public double getMedicionTotal() {
        return medicionTotal;
    }

    public ArrayList <LineaMedicion> getMediciones() {
        return mediciones;
    }
    
    public static int getNumeroLineasMedicion() {
        return numeroLineasMedicion;
    }
}
