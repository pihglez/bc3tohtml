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
 * @author Pedro I. HernÃ¡ndez G. <pihglez@gmail.com>
 */
public class StrUtils {
    /**
     * Aproximación al método split de la clase String que utiliza una variable adicional
     * para poder 
     * @param stringToSplit La cadena a dividir
     * @param div El carácter que divide la cadena en varias partes
     * @return String[]
     */
    public static String[] split(String stringToSplit, char div){
        String[] returnedArray;
        String a = String.valueOf((char) 255);
        
//        ArrayList <String> al = new ArrayList <String>();
        StringBuilder sb = new StringBuilder();
        
        if(stringToSplit.contains(String.valueOf(div))) {
//            System.out.println("El elemento divisor está contenido.");
            for (int i = 0; i < stringToSplit.length(); i++) {
                sb.append(String.valueOf(
                        stringToSplit.charAt(i) == div ? a : stringToSplit.charAt(i))
                );
                
            }
            
            return sb.toString().split(a);
            
        } else {
//            System.out.println("El elemento divisor '" + div + "' NO estÃ¡ contenido.");
            returnedArray = new String[1];
            returnedArray[0] = stringToSplit;
            return returnedArray;
        }
    }
    
    public static ArrayList<String> toStringArrayList (String stringToSplit, char div) {
        ArrayList<String> al = new ArrayList<String>();
        
        // to develop
        
        return al;
    }
    
    public static boolean isNullOrEmpty(String myString)
    {
         return myString == null || "".equals(myString);
    }
}
