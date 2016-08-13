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

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class SystemProperties {
    public final String                     javaVendor;
    public final String                     javaVendorURL;
    public final String                     javaVersion;
    
    public final String                     fileSep;
    public final String                     lineSeparator;
    public final String                     oSName;
    public final String                     oSArch;
    public final String                     oSVersion;
    public final String                     pathSeparator;
    
    public final String                     userName;
    public final String                     userDir;
    public final String                     userHome;
    
    public final SystemEnvironmentVariables envVars;
    public final String                     envVarsString;
    
    public SystemProperties() {
        javaVendor =    System.getProperty("java.vendor");
        javaVendorURL = System.getProperty("java.vendor.url");
        javaVersion =   System.getProperty("java.version");
        
        fileSep =       System.getProperty("file.separator");
        lineSeparator = System.getProperty("line.separator");
        oSName =        System.getProperty("os.name");
        oSArch =        System.getProperty("os.arch");
        oSVersion =     System.getProperty("os.version");
        pathSeparator = System.getProperty("path.separator");
        
        userName =      System.getProperty("user.name");
        userDir =       System.getProperty("user.dir");
        userHome =      System.getProperty("user.home");
        
        envVars         =   new SystemEnvironmentVariables();
        envVarsString   =   envVars.getAllEnvironmentVariables();
    }
    
    public String toConsoleString(boolean conVariablesEntorno) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Información del sistema: corriendo en entorno ");
        sb.append(((oSName.toLowerCase().contains("win")) ? "Windows" : "*nix"));
        sb.append(((oSName.toLowerCase().contains("mac")) ? " (mac OS)" : ""));
        sb.append("\n\n");
        
        sb.append("Vendedor de java: ");                sb.append(javaVendor);      sb.append("\n");
        sb.append("URL del vendedor de java: ");        sb.append(javaVendorURL);   sb.append("\n");
        sb.append("Versión de java: ");                 sb.append(javaVersion);     sb.append("\n\n");
        sb.append("Sistema operativo: ");               sb.append(oSName);          sb.append("\n");
        sb.append("Arquitectura: ");                    sb.append(oSArch);          sb.append("\n");
        sb.append("Version: ");                         sb.append(oSVersion);       sb.append("\n");
        sb.append("Separador de ruta: ");               sb.append(pathSeparator);   sb.append("\n");
        sb.append("Separador de archivo: ");            sb.append(fileSep);         sb.append("\n");
        sb.append("Separador de línea: ");              sb.append(lineSeparator);   sb.append("\n\n");
        sb.append("Nombre de usuario: ");               sb.append(userName);        sb.append("\n");
        sb.append("Carpeta de actual de usuario: ");    sb.append(userDir);         sb.append("\n");
        sb.append("Carpeta raíz de usuario: ");         sb.append(userHome);        sb.append("\n");
        
        if (conVariablesEntorno) {
            sb.append("\nListado de variables de entorno:\n"
                + "");                                  sb.append(envVarsString);   sb.append("\n");
        }
        return sb.toString();
    }
}