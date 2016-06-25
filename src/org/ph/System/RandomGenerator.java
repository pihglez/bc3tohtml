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
    private static final SecureRandom rnd   = new SecureRandom();
    
    public static String getRandomAlphaNumericString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
            sb.append(AB1.charAt(rnd.nextInt(AB1.length())));
        return sb.toString();
    }
    
    public static String getRandomUpperLowerString( int len ){
        StringBuilder sb = new StringBuilder( len );
        
        for( int i = 0; i < len; i++ ) 
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    
    public static String getRandomUpperString( int len ){
        StringBuilder sb = new StringBuilder( len );
        
        for( int i = 0; i < len; i++ ) 
            sb.append(AUPPER.charAt(rnd.nextInt(AUPPER.length())));
        return sb.toString();
    }
    
    public static String getRandomLowerString( int len ){
        StringBuilder sb = new StringBuilder( len );
        
        for( int i = 0; i < len; i++ ) 
            sb.append(ALOWER.charAt(rnd.nextInt(ALOWER.length())));
        return sb.toString();
    }
}