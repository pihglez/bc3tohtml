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
package org.ph.bc3tohtml;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import org.ph.System.SerialNumber;
import org.ph.System.SystemProperties;

/**
 * Mediante esta clase de controla la opci�n de instalaci�n del software mediante
 * l�nea de comandos
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
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
     * M�todo que realiza las labores de instalaci�n
     * @return <code>boolean</code> <code>True</code> en el caso de que la instalaci�n se
     * realice correctamente y <code>False</code> en caso contrario
     */
    private boolean initInstall() {
        boolean inst = false;
        
        // proceso de copia de archivos e instalaci�n
        if(!checklocalInstallationFolder()) {
            File f = new File("DEFAULTINSTALLATIONPATH");
            
            f.mkdirs();
        } else {
            // la instalaci�n ya existe �?
            // comprobar los distintos datos que se guardan en la carpeta
        }
        
        
        
        return inst;
    }
    
    /**
     * M�todo que realiza las labores de desinstalaci�n
     * @return <code>boolean</code> True en el caso de que la desinstalaci�n se
     * realice correctamente y False en caso contrario
     * 
     */
    private boolean initUninstall() {
        boolean uninst = false;
        
        // proceso de borrado de archivos y desinstalaci�n
        
        
        return uninst;
    }
    
    public boolean checkLocalInstallation(){
        boolean localInst = false;
        
        // comprobaci�n de la instalaci�n local
        
        return localInst;
    }
    
    /**
     * M�todo que comprueba si el directorio de instalaci�n local existe
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