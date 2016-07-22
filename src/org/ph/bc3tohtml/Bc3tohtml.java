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
import org.ph.System.RandomGenerator;
import org.ph.System.SystemProperties;
import org.ph.bc3tohtml.help.Ayuda;
import org.ph.errors.ErrorInArgumentsException;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3tohtml {
    /**
     * En esta constante se almacena la versión actual del software
     */
    public static final String  BC3TOHTMLVERSION    = "v.0.3.5.0";
    public static final String  APPNAME             = "bc3tohtml";
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
        // antes de comenzar con la ejecución, deberían procesarse antes todos los
        // switches con el objetivo de saber, por ejemplo, si se emplea el modificador
        // -y, el -l o el -v
        LineaComandos.asumirRespuestaPositiva   = lookForAnswerInArrayNoCaseSensitive(args, "-y");
        LineaComandos.modoVerbose               = lookForAnswerInArrayNoCaseSensitive(args, "-v");
        LineaComandos.mantenerArchivoLog        = lookForAnswerInArrayNoCaseSensitive(args, "-l");
        
//        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = new DefaultParser().parse(opciones, args);
            switch (args.length) {
                case 0:
                    System.out.println(Ayuda.INFO + Ayuda.DESCRIPCION);
                    break;
                case 1:
                    if (cmd.hasOption("?") || (args.length == 0))   showCmdHelp();
                    if (cmd.hasOption("z"))                         showLicense();
                    // hay que commprobar si el argumento es o no un archivo
                    if (testIfArgumentShouldBeFile(args[0])) System.out.println("El argumento debe ser un archivo que procesar :-P");;
                    break;
                default:
                    break;
            }
        } catch (ParseException ex) {
            System.out.println("Error al leer la línea de comandos.\n" + ex.getLocalizedMessage());
        }   
                
        String s;
        // <editor-fold defaultstate="expanded" desc=" COMMAND LINE SWITCHES SELECTION "> 
//////////        for (int j = 0; j < args.length; j++) {
//////////            s = args[j];
//////////            switch (s.toLowerCase()) {
//////////                case "-?":  // ayuda
//////////                    System.out.println(Ayuda.DETAILEDINFO);
//////////                    break;
////////////                case "/t3st":   // test
////////////                    System.out.println("¡Modo de prueba seleccionado!");
////////////                    LineaComandos.opcionTest = true;
////////////                    break;
//////////                case "-b":  // blind: presupuesto ciego
//////////                    LineaComandos.salidaApresupuestoCiego = true;
//////////                    break;
//////////                case "-i":  // información del sistema
//////////                    System.out.println(Ayuda.INFO);
//////////                    SystemProperties sp = new SystemProperties();
//////////                    System.out.println(sp.toConsoleString());
//////////                    break;
//////////                case "-d":  // solo descompuestos
//////////                    LineaComandos.salidaSoloDescompuestos = true;
//////////                    break;
//////////                case "-e":  // solo elementales
//////////                    LineaComandos.salidaSoloElementales = true;
//////////                    break;
//////////                case "-f":  // a continuación se establece el archivo de entrada, a procesar
//////////                    String archivoAProcesar = (s.equals(args[j]) && args.length > j) ? args[j + 1] : args[j];
//////////                    
//////////                    if(FileManage.isFileAvailable(archivoAProcesar)) {
//////////                        LineaComandos.nombreArchivoAProcesar = archivoAProcesar;
//////////                        LineaComandos.nombrarArchivoSalida = true;
//////////                    } else {
//////////                        throw new ErrorInArgumentsException("El archivo " + archivoAProcesar + " falta o no es utilizable.");
//////////                    }
//////////                    break;
////////////                case "-l":  // generar archivo log
////////////                    LineaComandos.mantenerArchivoLog = true;
////////////                    break;
//////////                case "-p":  // solo presupuesto
//////////                    break;
//////////                case "-m":  // con mediciones
//////////                    LineaComandos.salidaConMediciones = true;
//////////                    break;
//////////                case "-t":  // utilizar plantilla html
//////////                    if((s.equals(args[j])) && (args.length > j)){
//////////                        LineaComandos.nombrePlantillaHtml = (s.equals(args[j])) ? args[j + 1] : "";
//////////                    } else {
//////////                        throw new ErrorInArgumentsException("No se ha designado el archivo de plantilla de manera correcta.");
//////////                    }
//////////                    break;
//////////                case "-r":  // resumen
//////////                    LineaComandos.mostrarResumen = true;
//////////                    break;
//////////                case "-s":  // estadísticas
//////////                    LineaComandos.mostrarEstadisticas = true;
//////////                    break;
//////////                case "-o":  // a continuación se establece el archivo de salida (output), obviando el nombre por defecto
//////////                    if((s.equals(args[j])) && (args.length > j)){
//////////                        String nombreArchivoSalida = args[j + 1];
//////////                        if(FileManage.fileNameIsUsable(nombreArchivoSalida)) {
//////////                            LineaComandos.nombreArchivoSalida = nombreArchivoSalida;
//////////                            LineaComandos.nombrarArchivoSalida = true;
//////////                        } else {
//////////                            // otras opciones que se podrían considerar...
//////////                            // sobreescribir el archivo, etc.
//////////                            if (!LineaComandos.asumirRespuestaPositiva) {
//////////                                    if (!preguntarUsuarioSiNo("¿Sobreescribir el archivo " + nombreArchivoSalida + "?")) {
//////////                                    LineaComandos.nombreArchivoSalida = nombreArchivoSalida;
//////////                                    LineaComandos.nombrarArchivoSalida = true;
//////////                                } else {
//////////                                        throw new ErrorInArgumentsException("El archivo " + nombreArchivoSalida + " falta o no es utilizable.");
//////////                                    }
//////////                            } else {
//////////                                LineaComandos.nombreArchivoSalida = nombreArchivoSalida;
//////////                                LineaComandos.nombrarArchivoSalida = true;
//////////                            }
//////////                            
//////////                        }
//////////                        
//////////                    } else {
////////////                        LineaComandos.nombreArchivoSalida   = "";
//////////                        LineaComandos.nombrarArchivoSalida  = false;
//////////                        throw new ErrorInArgumentsException("El número de argumentos relacionados con el nombre de archivo de salida no es correcto.");
//////////                    }
//////////                    break;
////////////                case "-v":  // modo verbose... se muestran en pantalla todos los pasos... :-P
////////////                    LineaComandos.modoVerbose = true;
////////////                    break;
////////////                case "-y":
////////////                    LineaComandos.asumirRespuestaPositiva = true;
////////////                    break;
//////////                
//////////                case "-z":
//////////                    System.out.println(Ayuda.INFO);
//////////                    Bc3ToHtmlLicense lic = new Bc3ToHtmlLicense();
//////////                    System.out.println(lic.getLICENSE());
//////////                    break;
//////////                default:    // se procesa todo lo que explícitamente no entre dentro de uno de los casos previstos
//////////                    switch (args.length) {
//////////                        case 1:
////////////                            if (args.length == 1) {
//////////                            if(FileManage.isFileAvailable(args[j])) {
//////////                                LineaComandos.nombreArchivoAProcesar = args[j];
//////////                                LineaComandos.nombreArchivoSalida = setNombreArchivoSalida(args[j]);
//////////                                boolean sobreescribir = false;
//////////                                while (!FileManage.fileNameIsUsable(LineaComandos.nombreArchivoSalida) && !sobreescribir) {
//////////                                    if (!preguntarUsuarioSiNo("El archivo " + LineaComandos.nombreArchivoSalida + ""
//////////                                            + " existe. ¿Desea sobreescribirlo?")) {
//////////
//////////                                        LineaComandos.nombreArchivoSalida = LineaComandos.nombreArchivoSalida.replace(".html", 
//////////                                                "." + RandomGenerator.getRandomAlphaNumericString(6) + ".html");
//////////                                        System.out.println("Se ha generado automáticamente el nombre " + LineaComandos.nombreArchivoSalida);
//////////                                    } else {
//////////                                        sobreescribir = true;
//////////                                    }
//////////                                }
//////////
//////////                                LineaComandos.nombrePlantillaHtml = "{default}";
//////////
//////////                            } else {
//////////                                // otras opciones que se podrían considerar...
//////////                                // sobreescribir el archivo, etc.
//////////                                throw new ErrorInArgumentsException("El archivo " + args[j] + " falta o no se puede leer.");
//////////                            }
////////////                            }
//////////                            break;
//////////                    }
//////////                    break;
//////////            }
//////////        }
//////////        if (args.length == 0) {
//////////            System.out.println(Ayuda.INFO);
//////////        }
        // </editor-fold>
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
     * Este método busca ocurrencias de una determinada cadena
     * @param args String[] La matriz de cadenas en la que buscar ocurrencias
     * @param searched String La cadena a buscar
     * @return boolean El método devuelve true si se encuentran coincidencias y false en cualquier otro caso.
     */
    private static boolean lookForAnswerInArrayNoCaseSensitive(String[] args, String searched){
        boolean ret = false;
        
        for (String arg : args) {
            if (arg.equalsIgnoreCase(searched)) {
                ret = true;
                break;
            }
        }
        return ret;
    }
    
    /**
     * Este método establece las opciones utilizables a través de la línea de comandos.
     */
    private static void setCommanLineOptions(){
        opciones.addOption("?", false,  "Muestra esta ayuda");                                          // ayuda
        opciones.addOption("b", false,  "Genera un presupuesto ciego");                                 // presupuesto ciego
        opciones.addOption("d", false,  "Genera un documento sólo con los precios descompuestos");      // solo descompuestos
        opciones.addOption("e", false,  "Genera un documento sólo con los precios elementales");        // solo elementales
        opciones.addOption("f", true,   "Especifica a continuación el archivo de entrada (.bc3)");      // archivo (file) de entrada
        opciones.addOption("l", true,   "Especifica a continuación el archivo de volcado (.log)");      // archivo (log) de volcado
        opciones.addOption("m", false,  "Incluye las mediciones en el presupuesto");                    // incluir mediciones
        opciones.addOption("o", true,   "Especifica a continuación el archivo de salida (.html)");      // archivo (output) de salida
        opciones.addOption("p", false,  "Incluye el presupuesto (opción por defecto)");                 // incluir presupuesto
        opciones.addOption("r", false,  "Incluye el resumen de presupuesto");                           // incluir resumen
        opciones.addOption("s", false,  "Muestra estadísticas");                                        // mostrar estadísticas
        opciones.addOption("t", true,   "Especifica a continuación el archivo de plantilla a utilizar (.html)");      // archivo (template) a utilizar
        opciones.addOption("v", false,  "Muestra la versión del sofware");                              // muestra la versión del software
        opciones.addOption("y", false,  "Asume una respuesta positiva a las posibles preguntas");       // asumir sí (yes) a cualquier pregunta
        opciones.addOption("z", false,  "Muestra la licencia del software");                            // muestra la licencia
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
     * Este método muesra en pantalla la licencia del software sin paginación.
     */
    private static void showLicense() {
        System.out.println(Ayuda.INFO);
        Bc3ToHtmlLicense lic = new Bc3ToHtmlLicense();
        System.out.println(lic.getLICENSE());
    }
    
    /**
     * Este método comprueba si el argumento pasado debería ser un archivo.
     * Se considera que esto se da cuando no coincide con ninguno de los argumentos
     * previstos para su entrada en la línea de comandos.
     * @param argument El argumento a comprobar si está entre las opciones previstas
     * @return true en el caso de que el argumento pueda ser un archivo
     */
    private static boolean testIfArgumentShouldBeFile(String argument) {
        boolean figureArchive = false;
        if(!opciones.hasOption(argument)) figureArchive = true;
        return figureArchive;
    }
}