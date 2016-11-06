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

import java.io.File;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class SerialNumber {
    /**
     * Se adopta como elemento base para el cálculo del número de serie la carpeta
     * de usuario del sistema. Se crea una instancia de la clase <code>File</code> con el mismo y
     * a partir de la misma, se extraen distintas informaciones
     */
    private static final File      F       = new File(System.getProperty("user.home"));
    /**
     * La constante utilizada para el cálculo del número de serie: la ruta completa
     * de la carpeta de usuario y la fecha (<code>long</code>) de la última 
     * modificación de la misma separados por un guión bajo "_"
     */
    private static final String    USERSTR = F.getAbsolutePath() + "_" + F.lastModified();
    
    private static String calculateSecurityHash(String stringInput, String algorithmName) throws java.security.NoSuchAlgorithmException {
        String hexMessageEncode = "";
        byte[] buffer = stringInput.getBytes();
//        if(algorithmName.equals("SHA256")) algorithmName = "SHA-256";
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance(algorithmName);
        messageDigest.update(buffer);
        byte[] messageDigestBytes = messageDigest.digest();
        for (int index=0; index < messageDigestBytes.length ; index ++) {
            int countEncode = messageDigestBytes[index] & 0xff;
            if (Integer.toHexString(countEncode).length() == 1) hexMessageEncode = hexMessageEncode + "0";
            hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
        }
        return hexMessageEncode;
    }
    
    /**
     * Método que devuelve el hash MD2 de una cadena dada
     * @param stringInput <code>String</code> de la que se calcula el hash MD2
     * @return <code>String</code> el hash MD2 calculado
     * @throws NoSuchAlgorithmException <code>Exception</code> en caso de que no se encuentre el algoritmo MD2
     */
    public static String calculateMD2SH(String stringInput) throws NoSuchAlgorithmException {
        return calculateSecurityHash(stringInput, algorithms.MD2.name());
    }
    
    private static String calculateMD2SH() throws NoSuchAlgorithmException {
        return calculateMD2SH(USERSTR);
    }
    
    /**
     * Método que devuelve el hash MD5 de una cadena dada
     * @param stringInput <code>String</code> de la que se calcula el hash MD5
     * @return <code>String</code> el hash MD5 calculado
     * @throws NoSuchAlgorithmException <code>Exception</code> en caso de que no se encuentre el algoritmo MD5
     */
    public static String calculateMD5SH(String stringInput) throws NoSuchAlgorithmException {
        return calculateSecurityHash(stringInput, algorithms.MD5.name());
    }
    
    private static String calculateMD5SH() throws NoSuchAlgorithmException {
        return calculateMD5SH(USERSTR);
    }
    
    /**
     * Método que devuelve el hash SHA1 de una cadena dada
     * @param stringInput <code>String</code> de la que se calcula el hash SHA1
     * @return <code>String</code> el hash SHA1 calculado
     * @throws NoSuchAlgorithmException <code>Exception</code> en caso de que no se encuentre el algoritmo SHA1
     */
    public static String calculateSHA1SH(String stringInput) throws NoSuchAlgorithmException {
        return calculateSecurityHash(stringInput, algorithms.SHA1.name());
    }
    
    private static String calculateSHA1SH() throws NoSuchAlgorithmException {
        return calculateSHA1SH(USERSTR);
    }
    
    /**
     * Método que devuelve el hash SHA256 de una cadena dada
     * @param stringInput <code>String</code> de la que se calcula el hash SHA256
     * @return <code>String</code> el hash SHA256 calculado
     * @throws NoSuchAlgorithmException <code>Exception</code> en caso de que no se encuentre el algoritmo SHA256
     */
    public static String calculateSHA256SH(String stringInput) throws NoSuchAlgorithmException {
        return calculateSecurityHash(stringInput, "SHA-256");
    }
    
    private static String calculateSHA256SH() throws NoSuchAlgorithmException {
        return calculateSHA256SH(USERSTR);
    }
    
    /**
     * Método que devuelve el número de serie de la aplicación a partir de una
     * cadena de caracteres. Esta cadena debe tener, al menos, 130 caracteres.
     * @param serialNumberEncoded <code>String</code> La cadena codificada a 
     * partir de la que se extrae el número de serie
     * @return <code>String</code> El número de serie codificado en la forma
     * <b>XXXX-XXXXX-XXXX-XXXXX</b>
     */
    private static String getSN(String serialNumberEncoded) {
        return ""
        + serialNumberEncoded.charAt(31)
        + serialNumberEncoded.charAt(75)
        + serialNumberEncoded.charAt(130)
        + serialNumberEncoded.charAt(52)
        + "-"
        + serialNumberEncoded.charAt(2)
        + serialNumberEncoded.charAt(93)
        + serialNumberEncoded.charAt(73)
        + serialNumberEncoded.charAt(71)
        + serialNumberEncoded.charAt(99)
        + "-"
        + serialNumberEncoded.charAt(45)
        + serialNumberEncoded.charAt(63)
        + serialNumberEncoded.charAt(11)
        + serialNumberEncoded.charAt(84)
        + "-"
        + serialNumberEncoded.charAt(17)
        + serialNumberEncoded.charAt(53)
        + serialNumberEncoded.charAt(122)
        + serialNumberEncoded.charAt(15)
        + serialNumberEncoded.charAt(99);
    }

    /**
     * Algoritmos utilizables (java 1.6)
     */
    private static enum algorithms {
        /**
         * Algoritmo <b>MD2</b>
         */
        MD2, 
        /**
         * Algoritmo <b>MD5</b>
         */
        MD5, 
        /**
         * Algoritmo <b>SHA1</b>
         */
        SHA1, 
        /**
         * Algoritmo <b>SHA256</b>
         */
        SHA256
    }
    
    /**
     * Método que devuelve el número de serie de la aplicación
     * @return <code>String</code> El número de serie asociado a la aplicación
     * en formato (hexadecimal) <b>XXXX-XXXXX-XXXX-XXXXX</b>
     * @throws NoSuchAlgorithmException <code>Exception</code> En el caso de que no se encuentre el algoritmo de generación
     */
    public static String getSerialNumber() throws NoSuchAlgorithmException {
        return getSN(calculateMD2SH() +
                     calculateMD5SH() + 
                     calculateSHA1SH() + 
                     calculateSHA256SH());
    }
}
