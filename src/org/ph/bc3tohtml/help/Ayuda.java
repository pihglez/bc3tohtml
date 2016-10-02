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
package org.ph.bc3tohtml.help;

import org.ph.bc3tohtml.Bc3tohtml;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Ayuda {
    /**
     * Información del autor
     */
    public static final String CREDITOS = ""
            + "(c) 2016 Pedro I. Hernández G. :: arquitecto &T :: pihglez@gmail.com\n";
    
    /**
     * Créditos y descripción del funcionamiento básico del software
     */
    public static final String INFO = "\n" + Bc3tohtml.APPNAME + " " + Bc3tohtml.BC3TOHTMLVERSION + "\n"
            + "" + CREDITOS + "\n";
    
    /**
     * Información básica de uso del software
     */
    public static final String DESCRIPCION = ""
            + "Este software transforma un archivo BC3 en uno HTML.\n"
            + "Uso: java -jar bc3tohtml.jar archivo.bc3\n"
            + "Esto generará como salida 'archivo.html', que podrá ser visualizado con un navegador web standard como Firefox, Opera, Internet Explorer o Safari.\n\n"
            + "Ejecute 'java -jar bc3tohtml.jar -?' para una información más detallada.\n";
}
