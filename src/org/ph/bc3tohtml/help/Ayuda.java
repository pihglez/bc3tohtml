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
package org.ph.bc3tohtml.help;

import org.ph.bc3tohtml.Bc3tohtml;

/**
 *
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class Ayuda {
    /**
     * Informaci�n del autor
     */
    public static final String CREDITOS = ""
            + "(c) 2016 Pedro I. Hern�ndez G. :: arquitecto &T :: pihglez@gmail.com\n";
    
    /**
     * Cr�ditos y descripci�n del funcionamiento b�sico del software
     */
    public static final String INFO = "\nbc3tohtml " + Bc3tohtml.BC3TOHTMLVERSION + "\n"
            + "" + CREDITOS + "\n"
            + "Este software transforma un archivo BC3 en uno HTML.\n"
            + "Uso: java -jar bc3tohtml archivo.bc3\n"
            + "Esto generar� como salida 'archivo.html', que podr� ser visualizado con un navegador web standard como Firefox, Opera, Internet Explorer o Safari.\n"
            + "Ejecute 'java -jar bc3tohtml -?' para una informaci�n m�s detallada.\n";
    
    /**
     * Informaci�n detallada del funcionamiento del software
     */
    public static final String DETAILEDINFO = INFO + "\nUso avanzado y opciones largas:\n"
            + "   -?  muestra esta ayuda." + "\n"
            + "   -b  genera un presupuesto ciego (blind)." + "\n"
//            + "   -i  muestra informaci�n del sistema." + "\n"
//            + "   -c  " + "\n"
            + "   -d  s�lo incluye en el archivo generado los precios descompuestos." + "\n"
            + "   -e  s�lo incuye en el archivo generado los precios elementales." + "\n"
            + "   -f  establece el archivo (file, BC3) de entrada:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3" + "\n"
//            + "   -h  " + "\n"
            + "   -l  establece el archivo de volcado (log) en el que se guardar� informaci�n" + "\n"
            + "       acerca del proceso de conversi�n:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -l archivolog.log" + "\n"
            + "   -m  al realizar la conversi�n, incluye tambi�n las mediciones." + "\n"
//            + "       Implica '-p'." + "\n"
            + "   -o  establece el archivo HTML de salida (output) en el que se guardar� la informaci�n" + "\n"
            + "       contenida en el archivo BC3 de origen. En el caso de no especificarse, el archivo" + "\n"
            + "       de salida tendr� el mismo nombre que el de origen con la extensi�n '.html'" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -o nuevoarchivo.htm" + "\n"
            + "           generar� nuevoarchivo.htm" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3" + "\n"
            + "           generar� archivo.html" + "\n"
            + "   -p  s�lo incluye en el archivo generado el presupuesto." + "\n"
            + "   -r  s�lo incluye en el archivo generado el resumen del presupuesto." + "\n"
            + "   -s  muestra estad�sticas (statistics) en pantalla al finalizar la conversi�n." + "\n"
            + "   -t  establece el archivo (template, HTML) de plantilla para la conversi�n:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -t plantilla.html" + "\n"
//            + "   -v  muestra en pantalla informaci�n acerca del proceso de conversi�n." + "\n"
            + "   -y  asume una respuesta positiva a cualquier pregunta al usuario que" + "\n"
            + "       se pueda plantear:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -o archivo.html" + "\n"
            + "           El archivo \"archivo.html\" existe en el sistema." + "\n"
            + "           �Desea sobreescribirlo [S] S� | [N] No | [R] Renombrar?" + "\n"
            + "           Con la opci�n '-y' se asume respuesta positiva a la sobreescritura del archivo." + "\n"
            ;
}