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

import java.util.Scanner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ph.System.FileManage;
import org.ph.System.SystemProperties;
import org.ph.bc3tohtml.help.Ayuda;
import org.ph.errors.ErrorInArgumentsException;
import org.ph.errors.ErrorInFormatException;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3tohtml {
    /**
     * En esta constante se almacena la versión actual del software
     */
    public static final String  BC3TOHTMLVERSION    = "v.0.3.7.0";
    /**
     * En esta constante se almacena el nombre original del software
     */
    public static final String  APPNAME             = "bc3tohtml";
    /**
     * Opciones de la línea de comandos.
     */
    private static Options opciones;

    /**
     * @param args Argumentos de la línea de comandos
     * @throws org.ph.errors.ErrorInArgumentsException
     */
    public static void main(String[] args) throws ErrorInArgumentsException {
        
        
        opciones = new Options();
        setCommanLineOptions();
        
        testArgs(args);
    }
    
    /**
     * Método que comprueba los datos introducidos por el usuario en la línea de comandos.
     * @param args Array en el que cada elemento es un argumento de la línea de comandos introducido por el usuario
     * @throws ErrorInArgumentsException Error irrecuperable en la introducción de comandos por parte del usuario
     */
    private static void testArgs(String[] args) throws ErrorInArgumentsException {
        
        try {
            CommandLine cmd = new DefaultParser().parse(opciones, args);
            switch (args.length) {
                case 0:
                    System.out.println(Ayuda.INFO + Ayuda.DESCRIPCION);
                    break;
                case 1:
                    if (cmd.hasOption("?") || (args.length == 0))   showCmdHelp();
                    if (cmd.hasOption("i"))                         showSimpleSystemInfo();
                    if (cmd.hasOption("v"))                         showAppVersion();
                    if (cmd.hasOption("z"))                         showLicense();
                    
                    if (argumentIsFileName(args[0]))                procesarArchivo(args[0]);
                    break;
                default:
                    if (args.length > 2) {
                        // bc3tohtml -f archivo.bc3 -o archivo.html
                        // bc3tohtml -f archivo.bc3 -p                          // se presupone equivalente a --> bc3tohtml archivo.bc3 (implementar)
                        gestionaArgumentos(cmd);
                    } else {
                        System.out.println(Ayuda.INFO + Ayuda.DESCRIPCION);
                        System.out.println("Parece que ha introducido un número incorrecto de argumentos. Por favor, verifique la ayuda.");
                    }
                    break;
            }
        } catch (ParseException ex) {
            System.out.println("Error al leer la línea de comandos: " + ex.getLocalizedMessage());
        }
        
    }
    
    /**
     * Plantea una pregunta al usuario a la que sólo tiene que responder sí o no.
     * Para deducir la respuesta, se adopta únicamente la primera letra de los
     * datos introducidos por el usuario.
     * La pregunta se repite hasta que el usuario introduce datos válidos.
     * El método no es sensible a mayúsculas y/o minúsculas.
     * @param pregunta La pregunta cuya respuesta debe ser sí o no.
     * @return boolean true en caso de que la respuesta haya sido afirmativa y false en caso contrario
     */
    private static boolean preguntarUsuarioSiNo (String pregunta) {
        String respuesta;
        Scanner s = new Scanner(System.in);
        
        do {
            System.out.print(pregunta + " [S]í o [N]o: ");
            respuesta = s.next().toLowerCase().substring(0, 1);
        } while (!respuesta.equals("s") && !respuesta.equals("n"));
        
        return (respuesta.equals("s"));
    }
    
    /**
     * Partiendo del archivo de entrada (*.bc3), genera el nombre del archivo de
     * salida (*.html)
     * En el caso de que el archivo tenga extensión .bc3, se cambia por .html
     * En el caso de que la extensión sea otra, se añade .html al final del todo
     * @param archivoBase El nombre del archivo de entrada (*.bc3)
     * @return String El nombre del archivo de salida (*.html)
     */
    private static String setNombreArchivoSalida(String archivoBase) {
        return (archivoBase.contains(".bc3") && (archivoBase.indexOf("bc3") == archivoBase.length() - 3)) ? 
                archivoBase.replace("bc3", "html") : 
                archivoBase + ".html" ;
    }
    
    /**
     * Este método establece las opciones reconocibles a través de la línea de comandos.
     */
    private static void setCommanLineOptions(){
        opciones.addOption("?", false,  "Muestra esta ayuda");                                                      // ayuda
        opciones.addOption("b", false,  "Genera un presupuesto ciego (sin precios ni importes)");                   // presupuesto ciego
        opciones.addOption("d", false,  "Genera un documento sólo con los precios descompuestos");                  // solo descompuestos
        opciones.addOption("e", false,  "Genera un documento sólo con las entidades de la BBDD");                   // solo elementales
        opciones.addOption("f", true,   "Especifica a continuación el archivo de entrada (.bc3)");                  // archivo (file) de entrada
        opciones.addOption("l", true,   "Especifica a continuación el archivo de volcado (.log)");                  // archivo (log) de volcado
        opciones.addOption("m", false,  "Incluye las mediciones en el presupuesto");                                // incluir mediciones
        opciones.addOption("o", true,   "Especifica a continuación el archivo de salida (.html)");                  // archivo (output) de salida
        opciones.addOption("p", false,  "Incluye el presupuesto (opción por defecto con un solo argumento)");       // incluir presupuesto
        opciones.addOption("r", false,  "Incluye el resumen de presupuesto");                                       // incluir resumen
        opciones.addOption("s", false,  "Muestra estadísticas");                                                    // mostrar estadísticas
        opciones.addOption("t", true,   "Especifica a continuación el archivo de plantilla a utilizar (.html)");    // archivo (template) a utilizar
        opciones.addOption("v", false,  "Muestra la versión del sofware");                                          // muestra la versión del software
        opciones.addOption("w", false,  "Activa el modo de información adicional (verbose)");                       // muestra la versión del software
        opciones.addOption("y", false,  "Asume una respuesta positiva a las posibles preguntas");                   // asumir sí (yes) a cualquier pregunta
        opciones.addOption("z", false,  "Muestra la licencia del software");                                        // muestra la licencia
        opciones.addOption("i", false,  "Muestra información del sistema");                                         // muestra información del sistema
        opciones.addOption("c", false,  "Fuerza la lectura de archivos en codificación Windows\"Cp1252\"");         // fuerza lectura archivo bc3 codificación Windows Cp1252
    }
    
    /**
     * Este método muestra la ayuda avanzada del software.
     */
    private static void showCmdHelp() {
        HelpFormatter formatter = new HelpFormatter();
        System.out.print(Ayuda.INFO);
        formatter.printHelp(APPNAME + " " + BC3TOHTMLVERSION, opciones);
    }
    
    /**
     * Este método muestra en pantalla la licencia del software, sin paginación.
     */
    private static void showLicense() {
        System.out.println(Ayuda.INFO);
        Bc3ToHtmlLicense lic = new Bc3ToHtmlLicense();
        System.out.println(lic.getLICENSE());
    }
    
    /**
     * Muestra información básica del sistema SIN variables de entorno.
     */
    private static void showSimpleSystemInfo() {
        System.out.println(Ayuda.INFO);
        SystemProperties sp = new SystemProperties();
        System.out.println(sp.toConsoleString(false));
    }
    
    private static void showAppVersion() {
        System.out.println(""
                + "Nombre:    " + APPNAME + "\n"
                + "Productor: " + Ayuda.CREDITOS
                + "Versión:   " + BC3TOHTMLVERSION + "\n");
    }
    
    /**
     * Este método comprueba si el argumento pasado debería ser un archivo.
     * Se considera que esto se da cuando no coincide con ninguno de los argumentos
     * previstos para su entrada en la línea de comandos.
     * @param argument El argumento a comprobar si está entre las opciones previstas
     * @return true en el caso de que el argumento pueda ser un archivo
     */
    private static boolean argumentIsFileName(String argument) {
        boolean figureOutArchive = false;
        if(!opciones.hasOption(argument)) figureOutArchive = true;
        if (figureOutArchive) {
            if (!(FileManage.isFileAvailable(argument))) figureOutArchive = false;
        }
        return figureOutArchive;
    }
    
    private static void procesarArchivo() {
        try {
            // el archivo de entrada debe existir y ser accesible
            if (!(FileManage.isFileAvailable(LineaComandos.nombreArchivoAProcesar))) {
                // no ok
                throw new ErrorInArgumentsException("El archivo de origen no puede ser leído.");
            }
            
            // el archivo de salida NO debe existir
            if ((FileManage.isFileAvailable(LineaComandos.nombreArchivoSalida))) {
                // no ok
                throw new ErrorInArgumentsException("El archivo de salida existe. Debe especificar un nombre válido.");
            }
            
            // deben establecerse alguno de los argumentos de proceso válidos (-p o -r)
            if (!(  LineaComandos.incluirResumen            ||
                    LineaComandos.incluirPresupuesto        || 
                    LineaComandos.salidaSoloDescompuestos   ||
                    LineaComandos.salidaSoloElementales)) {
                
                throw new ErrorInArgumentsException(""
                        + "Los argumentos de proceso establecidos no son válidos.\n"
                        + "Debe elegir uno o varios de los elementos de salida:\n"
                        + "resumen, presupuesto, descompuestos o elementales");
            }
            
            // se cumplen las condiciones --> se realiza el proceso del archivo .bc3
            System.out.println("Procesando archivo " + LineaComandos.nombreArchivoAProcesar +
                    " --> " + LineaComandos.nombreArchivoSalida);
            
            BC3File bc3f = new BC3File(LineaComandos.nombreArchivoAProcesar);
            if (bc3f.procesaBC3()) {
                System.out.println("Proceso finalizado correctamente.");
                System.out.println("Archivo de salida: " + LineaComandos.nombreArchivoSalida);
            }
        } catch (ErrorInFormatException|ErrorInArgumentsException ex) {
            System.out.println("Error irrecuperable: " + ex.getMessage());
        }
    }
    
    /**
     * Ejecución en el caso de que el usuario introduzca un único parámetro
     * un único parámetro que debe coincidir con el nombre del archivo a procesar
     * @param nombreArchivo <strong>String</strong> Nombre del archivo a procesar
     */
    private static void procesarArchivo (String nombreArchivo) {
        LineaComandos.nombreArchivoAProcesar        = (FileManage.isFileAvailable(nombreArchivo)) ? nombreArchivo : null;
        String archivoSalida                        = FileManage.getNameFromBase(LineaComandos.nombreArchivoAProcesar, "html");
        LineaComandos.nombreArchivoSalida           = (!FileManage.isFileAvailable(archivoSalida)) ? archivoSalida : null;
        
        // hay que establecer los valores por defecto de las variables de trabajo
        // que se establecen en gestionaArgumentos según la entrada del usuario
        LineaComandos.incluirResumen                = true;
        LineaComandos.incluirPresupuesto            = true;
        
        procesarArchivo();
    }
    
    private static void gestionaArgumentos(CommandLine cmd) {
        if(cmd.hasOption("f")){
            LineaComandos.nombreArchivoAProcesar    = cmd.getOptionValue("f");
            
            LineaComandos.nombreArchivoSalida       =(cmd.hasOption("o"))    ?
                                                      cmd.getOptionValue("o"):
                                                      setNombreArchivoSalida(LineaComandos.nombreArchivoAProcesar);

            LineaComandos.nombrarArchivoSalida      = true;
            
            LineaComandos.mostrarEstadisticas       = cmd.hasOption("s");
            LineaComandos.modoVerbose               = cmd.hasOption("w");
            
//            // implementar uso de plantilla externa
//            LineaComandos.usarPlantillaExterna      = cmd.hasOption("t");
//            if(LineaComandos.usarPlantillaExterna) {
//                // Trabajo a realizar
//            }
            
            LineaComandos.salidaApresupuestoCiego   = cmd.hasOption("b");
            LineaComandos.salidaSoloDescompuestos   = cmd.hasOption("d");
            LineaComandos.salidaSoloElementales     = cmd.hasOption("e");
            LineaComandos.salidaConMediciones       = cmd.hasOption("m");
            LineaComandos.incluirResumen            = cmd.hasOption("r");
            LineaComandos.incluirPresupuesto        = cmd.hasOption("p");
            
//            // implementar mantenimiento de archivo log
//            LineaComandos.mantenerArchivoLog        = cmd.hasOption("l");
//            if(LineaComandos.mantenerArchivoLog){
//                // implementar uso de archivo log
//            }
            
            LineaComandos.asumirRespuestaPositiva   = cmd.hasOption("y");
            
            LineaComandos.forzarCodificacionWindows = cmd.hasOption("c");
            procesarArchivo();
        }
    }
}