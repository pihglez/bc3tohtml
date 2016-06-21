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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.ph.stringUtils.StrUtils;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Registro_C_concepto {
//    public enum TIPOCODIGO {SIN_CLASIFICAR, MANO_DE_OBRA, MAQUINARIA_Y_MEDIOS_AUXILIARES, MATERIALES, COMPONENTES_ADICIONALES_DE_RESIDUO,
//    CLASIFICACION_DE_RESIDUO}
    
    private final String codigo, codigo2, unidad, resumen;      // codigo debe ser una matriz y, por tanto, codigo2 deja de tener sentido
    private final double precio;                                // debe ser una matriz / ArrayList
    private Date fecha;                                         // debe ser una matriz / ArrayList
    private final int tipo;

    public Registro_C_concepto(String [] codigo) throws ParseException {
        if (!codigo[1].contains("\\")){
            this.codigo = codigo[1];
            this.codigo2 = "";
        } else {
            String[] cod = StrUtils.split(codigo[1], '\\');
            this.codigo = cod[0];
            this.codigo2 = cod[1];
        }
        this.unidad = codigo[2];
        this.resumen = codigo[3];
        this.precio = Double.parseDouble(codigo[4]);
        this.fecha = setFecha(codigo[5]);
        this.tipo = Integer.parseInt(codigo[6]);
    }

    private Date setFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf;
        switch (fecha.length()) {
            case 1:
                sdf = new SimpleDateFormat("y");
                break;
            case 2:
                sdf = new SimpleDateFormat("yy");
                break;
            case 3:
                sdf = new SimpleDateFormat("Myy");
                break;
            case 4:
                sdf = new SimpleDateFormat("MMyy");
                break;
            case 5:
                sdf = new SimpleDateFormat("dMMyy");
                break;
            case 6:
                sdf = new SimpleDateFormat("ddMMyy");
                break;
            case 7:
                sdf = new SimpleDateFormat("dMMyyyy");
                break;
            case 8:
                sdf = new SimpleDateFormat("ddMMyyyy");
                break;
            default:
                sdf = new SimpleDateFormat("ddMMyyyy");
                break;
        }
        
        Date date = sdf.parse(fecha);
        
        return date;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getCodigo2() {
        return codigo2;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getResumen() {
        return resumen;
    }

    public double getPrecio() {
        return precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getTipo() {
        return tipo;
    }
}