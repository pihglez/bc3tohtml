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
public class ConstantesTexto {
    /**
     * Inicio (y final -no incluído-) de registro
     */
    public static final String inicioConcepto = "~";
    /**
     * Separador entre elementos de distinto tipo
     */
    public static final String separador = "\\|";
    
    /**
     * Divisor entre elementos del mismo tipo
     */
    public static final String divisor = "\\\\";
    
    /**
     * Matriz de constantes de texto de encabezado de línea según la codificación
     * del formato BC3
     * "~V": // propiedad y version
     * "~K": // coeficientes
     * "~C": // concepto
     * "~D": // descomposición
     * "~Y": // añadir descomposición
     * "~R": // descomposición de residuos
     * "~T": // texto
     * "~P": // paramétrica
     * "~L": // pliegos
     * "~Q": // pliegos (modelo 2)
     * "~J": // pliegos (modelo 2+)
     * "~W": // ámbito geográfico
     * "~G": // información gráfica
     * "~E": // entidad
     * "~O": // relación comercial
     * "~X": // información técnica
     * "~M": // mediciones
     * "~N": // añadir mediciones
     * "~A": // claves
     * "~B": // cambio de código
     * "~F": // documento adjunto
     */
    public static final String[] bc3Codes = {"~V", "~K", "~C", "~D", "~Y", "~R",
        "~T", "~P", "~L", "~Q", "~J", "~W", "~G", "~E", "~O", "~X", "~M", "~N", 
        "~A", "~B", "~F"};
}
