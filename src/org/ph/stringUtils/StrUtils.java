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
package org.ph.stringUtils;

import java.util.ArrayList;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class StrUtils {
    private static final int LONGITUDRESUMEN = 3;
    
    /**
     * Aproximación al método split de la clase String que utiliza una variable adicional
     * para poder seleccionar el carácter que delimita la división.
     * @param stringToSplit La cadena a dividir
     * @param div El carácter que divide la cadena en varias partes
     * @return String[] <code>String Array</code> en el que están almacenadas cada
     * una de las partes en que se divide la cadena original
     */
    public static String[] split(String stringToSplit, char div){
        String[] returnedArray;
        String a = String.valueOf((char) 255);        

        StringBuilder sb = new StringBuilder();
        
        if(stringToSplit.contains(String.valueOf(div))) {
            for (int i = 0; i < stringToSplit.length(); i++) {
                sb.append(String.valueOf(
                        stringToSplit.charAt(i) == div ? a : stringToSplit.charAt(i))
                );
                
            }
            
            return sb.toString().split(a);
            
        } else {
            returnedArray = new String[1];
            returnedArray[0] = stringToSplit;
            return returnedArray;
        }
    }
    
    /**
     * Método estático que, a partir de una cadena y un carácter de división,
     * devuelve un ArrayList&#60;String&#62; de las distintas cadenas que componen la
     * cadena a dividir y están divididas por el carácter de división.
     * En el caso de que no se encuentre el carácter de división, se devuelve el
     * tipo previsto con un único elemento: la cadena <code>stringToSplit</code> completa.
     * @param stringToSplit <code>String</code> Cadena a dividir.
     * @param div <code>char</code> El carácter de división.
     * @return <code>ArrayList&#60;String&#62;</code> El ArrayList con en la que cada elemento de la lista
     * es una pieza de la cadena original dividida por el elemento divisor provisto.
     */
    public static ArrayList<String> toStringArrayList (String stringToSplit, char div) {
        ArrayList<String> al = new ArrayList<String>();
        StringBuilder sb = new StringBuilder().append(stringToSplit);
        
        if (sb.charAt(0) == div) {
            do {
                sb.replace(0, 1, "");
                
            } while ((sb.charAt(0) == div));
        }
        
        if(sb.charAt(sb.length() - 1) == div) {
            do {
                sb.setLength(sb.length() - 1);
            } while (sb.charAt(sb.length() - 1) == div);
        }
        
        int leido = 0;
        StringBuilder aux = new StringBuilder();
        while (leido < sb.length()) {
            if ((sb.charAt(leido) == div) || (leido == sb.length() - 1)) {
                if (leido == sb.length() - 1) aux.append(sb.charAt(leido));
                al.add(aux.toString());
                aux.setLength(0);
            } else {
                aux.append(sb.charAt(leido));
            }
            leido++;
        }
        
        return al;
    }
    
    /**
     * Método que comprueba si una variable de cadena apunta a <code>null</code>
     * o está vacía.
     * @param myString <code>String</code> La variable de cadena a comprobar
     * @return <code>true</code> en el caso de que la variable apunte a <code>null</code>
     * o esté vacía y <code>false</code> en caso contrario.
     */
    public static boolean isNullOrEmpty(String myString)
    {
         return myString == null || "".equals(myString);
    }
    
    /**
     * Método que devuelve, a partir del nombre de un capítulo, su correspondiente
     * resumen de tres caracteres alfanuméricos
     * @param capitulo <code>String</code> La variable de cadena de cuya longitud
     * se extrae el resumen
     * @return <code>String</code> con la codificación calculada
     */
    public static String get3CharBriefing(String capitulo) {
        // LONGITUDRESUMEN = 3;
        String charBriefing = "";
//        String s = StringChapters.ACABADO.name();
        return charBriefing;
    }
}
