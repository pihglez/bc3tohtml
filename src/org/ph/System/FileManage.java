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
 * @author pihglez@author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class FileManage {
//    private static File file;
    
    /**
     * Comprueba si el archivo de nombre 'filename' puede ser leído, es un archivo, existe y no es un directorio.
     * @param fileName la ruta completa del archivo
     * @return <code>true</code> en el caso de que se cumplan las condiciones y false en caso contrario
     */
    public static boolean isFileAvailable (String fileName) {
        File f = new File(fileName);
        return isFileAvailable(f);
    }
    
    /**
     * Comprueba si el archivo 'file' puede ser leído, es un archivo, existe y no es un directorio.
     * @param file la referencia al archivo
     * @return <code>true</code> en el caso de que se cumplan las condiciones y <code>false</code> en caso contrario
     */
    public static boolean isFileAvailable (File file) {
        return ((file.canRead() && file.isFile() && file.exists() && !file.isDirectory()));
    }
    
    /**
     * Comprueba si el archivo de nombre 'filename' NO existe y NO es un directorio, en cuyo caso, devuelve true
     * @param fileName
     * @return 
     */
    public static boolean fileNameIsUsable (String fileName) {
        File f = new File(fileName);
        
        return !(f.exists() || f.isDirectory());
    }
}