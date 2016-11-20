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

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class FileManage {
//    private static File file;
    
    /**
     * Comprueba si el archivo de nombre 'filename' puede ser leído, es un archivo, existe y no es un directorio.
     * @param fileName <code>String</code> la ruta completa del archivo
     * @return <code>true</code> en el caso de que se cumplan las condiciones y <code>false</code> en caso contrario
     */
    public static boolean isFileAvailable (String fileName) {
        File f = new File(fileName);
        return isFileAvailable(f);
    }
    
    /**
     * Comprueba si el archivo <code>file</code> puede ser leído, es un archivo, existe y no es un directorio.
     * @param file <code>File</code>la referencia al archivo
     * @return <code>true</code> en el caso de que se cumplan las condiciones y <code>false</code> en caso contrario
     */
    public static boolean isFileAvailable (File file) {
        return ((file.exists() && file.canRead() && file.isFile() && !file.isDirectory()));
    }
    
    /**
     * Comprueba si el archivo de nombre 'filename' NO existe y NO es un directorio, en cuyo caso, devuelve true
     * @param fileName <code>String</code> El nombre del archivo
     * @return <code>boolean</code>
     */
    public static boolean fileNameIsUsable (String fileName) {
        File f = new File(fileName);
        
        return !(f.exists() || f.isDirectory());
    }
    
    /**
     * Método que, a partir del nombre completo de un archivo archivo.extension,
     * devuelve el nuevo nombre completo con la nueva extensión.
     * @param nombreArchivoBase <code>String</code> El nombre completo utilizado como base.
     * @param nuevaExtensionArchivo <code>String</code> La extensión que sustituirá la extensión del archivo de base.
     * @return <code>String</code> Nombre completo del archivo con la nueva extension
     */
    public static String getNameFromBase(String nombreArchivoBase, String nuevaExtensionArchivo) {
        int n = nombreArchivoBase.lastIndexOf(".");
        return nombreArchivoBase.substring(0, n).concat(".").concat(nuevaExtensionArchivo);
    }
    
    /**
     * Método que comprueba si una ruta en concreto existe y es un directorio/carpeta
     * @param absolutePath <code>String</code> la ruta completa a comprobar
     * @return <code>boolean</code> <code>true</code> si la carpeta existe y es una carpeta
     * (y o un archivo) y <code>false</code> en caso contrario
     */
    public static boolean folderExists(String absolutePath) {
        File f2 = new File(absolutePath);
        
        return (f2.exists() && f2.isDirectory());
    }
    
    /**
     * Método que crea la estructura de carpetas dada por la ruta que se
     * pasa a partir de un <code>String</code>
     * @param absolutePath <code>String</code> la ruta completa a crear, con o
     * sin carpetas anidadas.
     * @return <code>int</code>  El código que indica como ha ido la operación:<br/>
     * -1: la ruta se ha creado correctamente<br/>
     * -0: la ruta existe<br/>
     * +1: la ruta no se ha podido crear
     */
    public static int createFolderStructure(String absolutePath) {
        File f2 = new File(absolutePath);
        if(f2.exists()){
            System.out.println("La ruta ya existe.");
            return      -0;
        } else {
            if(f2.mkdirs()){
                System.out.println("La ruta se ha creado correctamente.");
                return  -1;
            } else {
                System.out.println("La ruta no se ha podido crear.");
                return  +1;
            }
        }
    }
}
