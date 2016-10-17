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
package org.ph.stringUtils;

import java.util.ArrayList;

/**
 *
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class StrUtils {
    private static final int LONGITUDRESUMEN = 3;
    
    /**
     * Aproximaci�n al m�todo split de la clase String que utiliza una variable adicional
     * para poder seleccionar el car�cter que delimita la divisi�n.
     * @param stringToSplit La cadena a dividir
     * @param div El car�cter que divide la cadena en varias partes
     * @return String[] <code>String Array</code> en el que est�n almacenadas cada
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
     * M�todo est�tico que, a partir de una cadena y un car�cter de divisi�n,
     * devuelve un ArrayList&#60;String&#62; de las distintas cadenas que componen la
     * cadena a dividir y est�n divididas por el car�cter de divisi�n.
     * En el caso de que no se encuentre el car�cter de divisi�n, se devuelve el
     * tipo previsto con un �nico elemento: la cadena <code>stringToSplit</code> completa.
     * @param stringToSplit <code>String</code> Cadena a dividir.
     * @param div <code>char</code> El car�cter de divisi�n.
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
     * M�todo que comprueba si una variable de cadena apunta a <code>null</code>
     * o est� vac�a.
     * @param myString <code>String</code> La variable de cadena a comprobar
     * @return <code>true</code> en el caso de que la variable apunte a <code>null</code>
     * o est� vac�a y <code>false</code> en caso contrario.
     */
    public static boolean isNullOrEmpty(String myString)
    {
         return myString == null || "".equals(myString);
    }
    
    /**
     * M�todo que devuelve, a partir del nombre de un cap�tulo, su correspondiente
     * resumen de tres caracteres alfanum�ricos
     * @param capitulo <code>String</code> La variable de cadena de cuya longitud
     * se extrae el resumen
     * @return <code>String</code> con la codificaci�n calculada
     */
    public static String get3CharBriefing(String capitulo) {
        // LONGITUDRESUMEN = 3;
        String charBriefing = "";
//        String s = StringChapters.ACABADO.name();
        return charBriefing;
    }
}
