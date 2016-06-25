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
    public static final String INFO = "\nbc3tohtml " + Bc3tohtml.BC3TOHTMLVERSION + "\n"
            + "" + CREDITOS + "\n"
            + "Este software transforma un archivo BC3 en uno HTML.\n"
            + "Uso: java -jar bc3tohtml archivo.bc3\n"
            + "Esto generará como salida 'archivo.html', que podrá ser visualizado con un navegador web standard como Firefox, Opera, Internet Explorer o Safari.\n"
            + "Ejecute 'java -jar bc3tohtml -?' para una información más detallada.\n";
    
    /**
     * Información detallada del funcionamiento del software
     */
    public static final String DETAILEDINFO = INFO + "\nUso avanzado y opciones largas:\n"
            + "   -?  muestra esta ayuda." + "\n"
            + "   -b  genera un presupuesto ciego (blind)." + "\n"
//            + "   -i  muestra información del sistema." + "\n"
//            + "   -c  " + "\n"
            + "   -d  sólo incluye en el archivo generado los precios descompuestos." + "\n"
            + "   -e  sólo incuye en el archivo generado los precios elementales." + "\n"
            + "   -f  establece el archivo (file, BC3) de entrada:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3" + "\n"
//            + "   -h  " + "\n"
            + "   -l  establece el archivo de volcado (log) en el que se guardará información" + "\n"
            + "       acerca del proceso de conversión:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -l archivolog.log" + "\n"
            + "   -m  al realizar la conversión, incluye también las mediciones." + "\n"
//            + "       Implica '-p'." + "\n"
            + "   -o  establece el archivo HTML de salida (output) en el que se guardará la información" + "\n"
            + "       contenida en el archivo BC3 de origen. En el caso de no especificarse, el archivo" + "\n"
            + "       de salida tendrá el mismo nombre que el de origen con la extensión '.html'" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -o nuevoarchivo.htm" + "\n"
            + "           generará nuevoarchivo.htm" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3" + "\n"
            + "           generará archivo.html" + "\n"
            + "   -p  sólo incluye en el archivo generado el presupuesto." + "\n"
            + "   -r  sólo incluye en el archivo generado el resumen del presupuesto." + "\n"
            + "   -s  muestra estadísticas (statistics) en pantalla al finalizar la conversión." + "\n"
            + "   -t  establece el archivo (template, HTML) de plantilla para la conversión:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -t plantilla.html" + "\n"
//            + "   -v  muestra en pantalla información acerca del proceso de conversión." + "\n"
            + "   -y  asume una respuesta positiva a cualquier pregunta al usuario que" + "\n"
            + "       se pueda plantear:" + "\n"
            + "       java -jar bc3tohtml -f archivo.bc3 -o archivo.html" + "\n"
            + "           El archivo \"archivo.html\" existe en el sistema." + "\n"
            + "           ¿Desea sobreescribirlo [S] Sí | [N] No | [R] Renombrar?" + "\n"
            + "           Con la opción '-y' se asume respuesta positiva a la sobreescritura del archivo." + "\n"
            ;
}