/*
 * Copyright (C) 2016 Pedro I. Hern�ndez G. <pihglez@gmail.com>
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
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class ConstantesTexto {
    /**
     * Inicio (y final -no inclu�do-) de registro
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
     * Matriz de constantes de texto de encabezado de l�nea seg�n la codificaci�n
     * del formato BC3
     * "~V": // propiedad y version
     * "~K": // coeficientes
     * "~C": // concepto
     * "~D": // descomposici�n
     * "~Y": // a�adir descomposici�n
     * "~R": // descomposici�n de residuos
     * "~T": // texto
     * "~P": // param�trica
     * "~L": // pliegos
     * "~Q": // pliegos (modelo 2)
     * "~J": // pliegos (modelo 2+)
     * "~W": // �mbito geogr�fico
     * "~G": // informaci�n gr�fica
     * "~E": // entidad
     * "~O": // relaci�n comercial
     * "~X": // informaci�n t�cnica
     * "~M": // mediciones
     * "~N": // a�adir mediciones
     * "~A": // claves
     * "~B": // cambio de c�digo
     * "~F": // documento adjunto
     */
    public static final String[] bc3Codes = {"~V", "~K", "~C", "~D", "~Y", "~R",
        "~T", "~P", "~L", "~Q", "~J", "~W", "~G", "~E", "~O", "~X", "~M", "~N", 
        "~A", "~B", "~F"};
}
