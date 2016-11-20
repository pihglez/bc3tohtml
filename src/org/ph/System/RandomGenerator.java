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
package org.ph.System;

import java.security.SecureRandom;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class RandomGenerator {
    private static final String AUPPER      = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALOWER      = "abcdefghijklmnopqrstuvwxyz";
    private static final String A1          = "0123456789";
    private static final String AB          = AUPPER + ALOWER;
    private static final String AB1         = A1 + AB;
    private static final SecureRandom RND   = new SecureRandom();
    
    /**
     * Genera una cadena alfanumérica aleatoria de longitud dada (mayúsculas y minúsculas)
     * @param len <b>int</b> La longitud de la cadena a generar
     * @return 
     */
    public static String getRandomAlphaNumericString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
            sb.append(AB1.charAt(RND.nextInt(AB1.length())));
        return sb.toString();
    }
    
    /**
     * Genera una cadena alfabética aleatoria de longitud dada (mayúsculas y minúsculas)
     * @param len <b>int</b> La longitud de la cadena a generar
     * @return 
     */
    public static String getRandomUpperLowerString( int len ){
        StringBuilder sb = new StringBuilder( len );
        
        for( int i = 0; i < len; i++ ) 
            sb.append(AB.charAt(RND.nextInt(AB.length())));
        return sb.toString();
    }
    
    /**
     * Genera una cadena alfabética aleatoria de longitud dada (mayúsculas)
     * @param len <b>int</b> La longitud de la cadena a generar
     * @return 
     */
    public static String getRandomUpperString( int len ){
        StringBuilder sb = new StringBuilder( len );
        
        for( int i = 0; i < len; i++ ) 
            sb.append(AUPPER.charAt(RND.nextInt(AUPPER.length())));
        return sb.toString();
    }
    
    /**
     * Genera una cadena alfabética aleatoria de longitud dada (minúsculas)
     * @param len <b>int</b> La longitud de la cadena a generar
     * @return 
     */
    public static String getRandomLowerString( int len ){
        StringBuilder sb = new StringBuilder( len );
        
        for( int i = 0; i < len; i++ ) 
            sb.append(ALOWER.charAt(RND.nextInt(ALOWER.length())));
        return sb.toString();
    }
}
