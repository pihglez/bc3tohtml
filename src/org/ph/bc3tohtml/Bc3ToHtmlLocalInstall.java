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
package org.ph.bc3tohtml;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import org.ph.System.SerialNumber;
import org.ph.System.SystemProperties;

/**
 * Mediante esta clase de controla la opción de instalación del software mediante
 * línea de comandos
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3ToHtmlLocalInstall {
    private final String DEFAULTINSTALLATIONPATH;
    private final String INSTALLATIONSERIALNUMBER;
    
    /**
     * Constructor
     * @param desinstalacion <code>boolean</code>
     */
    public Bc3ToHtmlLocalInstall(boolean desinstalacion) throws NoSuchAlgorithmException {
        SystemProperties sp = new SystemProperties();
        DEFAULTINSTALLATIONPATH = sp.getUserRootFolder() + "/.bc3tohtml/";
        INSTALLATIONSERIALNUMBER = SerialNumber.getSerialNumber();
        
        if (!desinstalacion) initInstall();
    }
    
    /**
     * Método que realiza las labores de instalación
     * @return <code>boolean</code> <code>True</code> en el caso de que la instalación se
     * realice correctamente y <code>False</code> en caso contrario
     */
    private boolean initInstall() {
        boolean inst = false;
        
        // proceso de copia de archivos e instalación
        if(!checklocalInstallationFolder()) {
            File f = new File("DEFAULTINSTALLATIONPATH");
            
            f.mkdirs();
        } else {
            // la instalación ya existe ¿?
            // comprobar los distintos datos que se guardan en la carpeta
        }
        
        
        
        return inst;
    }
    
    /**
     * Método que realiza las labores de desinstalación
     * @return <code>boolean</code> True en el caso de que la desinstalación se
     * realice correctamente y False en caso contrario
     * 
     */
    private boolean initUninstall() {
        boolean uninst = false;
        
        // proceso de borrado de archivos y desinstalación
        
        
        return uninst;
    }
    
    public boolean checkLocalInstallation(){
        boolean localInst = false;
        
        // comprobación de la instalación local
        
        return localInst;
    }
    
    /**
     * Método que comprueba si el directorio de instalación local existe
     * @return <code>boolean</code> True en el caso de que exista y False en caso
     * contrario
     */
    private boolean checklocalInstallationFolder() {
        boolean lif = false;
        
        File f = new File(DEFAULTINSTALLATIONPATH);
        if (f.exists()) lif = true;
        
        return lif;
    }
}