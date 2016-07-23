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

import java.util.Scanner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ph.System.FileManage;
//import org.ph.System.RandomGenerator;
import org.ph.System.SystemProperties;
import org.ph.bc3tohtml.help.Ayuda;
import org.ph.errors.ErrorInArgumentsException;

/**
 *
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class Bc3tohtml {
    /**
     * En esta constante se almacena la versi�n actual del software
     */
    public static final String  BC3TOHTMLVERSION    = "v.0.3.5.0";
    public static final String  APPNAME             = "bc3tohtml";
    private static Options opciones;

    /**
     * @param args Argumentos de la l�nea de comandos
     * @throws org.ph.errors.ErrorInArgumentsException
     */
    public static void main(String[] args) throws ErrorInArgumentsException {
        
        
        opciones = new Options();
        setCommanLineOptions();
        
        testArgs(args);
    }
    
    /**
     * M�todo que comprueba los datos introducidos por el usuario en la l�nea de comandos.
     * @param args Array en el que cada elemento es un argumento de la l�nea de comandos introducido por el usuario
     * @throws ErrorInArgumentsException Error irrecuperable en la introducci�n de comandos por parte del usuario
     */
    private static void testArgs(String[] args) throws ErrorInArgumentsException {
        // antes de comenzar con la ejecuci�n, deber�an procesarse antes todos los
        // switches con el objetivo de saber, por ejemplo, si se emplea el modificador
        // -y, el -l o el -v
//        LineaComandos.asumirRespuestaPositiva   = lookForAnswerInArrayNoCaseSensitive(args, "-y");
//        LineaComandos.modoVerbose               = lookForAnswerInArrayNoCaseSensitive(args, "-v");
//        LineaComandos.mantenerArchivoLog        = lookForAnswerInArrayNoCaseSensitive(args, "-l");
        
//        CommandLineParser parser = new DefaultParser();
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
                    if (args.length > 3) {
                        // bc3tohtml -f archivo.bc3 -o archivo.html
                        gestionaArgumentos(cmd);
                    } else {
                        System.out.println(Ayuda.INFO + Ayuda.DESCRIPCION);
                        System.out.println("Parece que ha introducido un n�mero incorrecto de argumentos. Por favor, verifique la ayuda.");
                    }
                    break;
            }
        } catch (ParseException ex) {
            System.out.println("Error al leer la l�nea de comandos.\n" + ex.getLocalizedMessage());
        }
        
    }
    
    /**
     * Plantea una pregunta al usuario a la que s�lo tiene que responder s� o no.
     * Para deducir la respuesta, se adopta �nicamente la primera letra de los
     * datos introducidos por el usuario.
     * La pregunta se repite hasta que el usuario introduce datos v�lidos.
     * El m�todo no es sensible a may�sculas y/o min�sculas.
     * @param pregunta La pregunta cuya respuesta debe ser s� o no.
     * @return boolean true en caso de que la respuesta haya sido afirmativa y false en caso contrario
     */
    private static boolean preguntarUsuarioSiNo (String pregunta) {
        String respuesta;
        Scanner s = new Scanner(System.in);
        
        do {
            System.out.print(pregunta + " [S]� o [N]o: ");
            respuesta = s.next().toLowerCase().substring(0, 1);
        } while (!respuesta.equals("s") && !respuesta.equals("n"));
        
        return (respuesta.equals("s"));
    }
    
    /**
     * Partiendo del archivo de entrada (*.bc3), genera el nombre del archivo de
     * salida (*.html)
     * En el caso de que el archivo tenga extensi�n .bc3, se cambia por .html
     * En el caso de que la extensi�n sea otra, se a�ade .html al final del todo
     * @param archivoBase El nombre del archivo de entrada (*.bc3)
     * @return String El nombre del archivo de salida (*.html)
     */
    private static String setNombreArchivoSalida(String archivoBase) {
        return (archivoBase.contains(".bc3") && (archivoBase.indexOf("bc3") == archivoBase.length() - 3)) ? 
                archivoBase.replace("bc3", "html") : 
                archivoBase + ".html" ;
    }
    
    /**
     * Este m�todo busca ocurrencias de una determinada cadena
     * @param args String[] La matriz de cadenas en la que buscar ocurrencias
     * @param searched String La cadena a buscar
     * @return boolean El m�todo devuelve true si se encuentran coincidencias y false en cualquier otro caso.
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
     * Este m�todo establece las opciones utilizables a trav�s de la l�nea de comandos.
     */
    private static void setCommanLineOptions(){
        opciones.addOption("?", false,  "Muestra esta ayuda");                                                      // ayuda
        opciones.addOption("b", false,  "Genera un presupuesto ciego (sin precios ni importes)");                   // presupuesto ciego
        opciones.addOption("d", false,  "Genera un documento s�lo con los precios descompuestos");                  // solo descompuestos
        opciones.addOption("e", false,  "Genera un documento s�lo con los precios elementales");                    // solo elementales
        opciones.addOption("f", true,   "Especifica a continuaci�n el archivo de entrada (.bc3)");                  // archivo (file) de entrada
        opciones.addOption("l", true,   "Especifica a continuaci�n el archivo de volcado (.log)");                  // archivo (log) de volcado
        opciones.addOption("m", false,  "Incluye las mediciones en el presupuesto");                                // incluir mediciones
        opciones.addOption("o", true,   "Especifica a continuaci�n el archivo de salida (.html)");                  // archivo (output) de salida
        opciones.addOption("p", false,  "Incluye el presupuesto (opci�n por defecto)");                             // incluir presupuesto
        opciones.addOption("r", false,  "Incluye el resumen de presupuesto");                                       // incluir resumen
        opciones.addOption("s", false,  "Muestra estad�sticas");                                                    // mostrar estad�sticas
        opciones.addOption("t", true,   "Especifica a continuaci�n el archivo de plantilla a utilizar (.html)");    // archivo (template) a utilizar
        opciones.addOption("v", false,  "Muestra la versi�n del sofware");                                          // muestra la versi�n del software
        opciones.addOption("w", false,  "Activa el modo de informaci�n adicional (verbose)");                       // muestra la versi�n del software
        opciones.addOption("y", false,  "Asume una respuesta positiva a las posibles preguntas");                   // asumir s� (yes) a cualquier pregunta
        opciones.addOption("z", false,  "Muestra la licencia del software");                                        // muestra la licencia
        opciones.addOption("i", false,  "Muestra informaci�n del sistema");                                         // muestra informaci�n del sistema
    }
    
    /**
     * Este m�todo muestra la ayuda avanzada del software.
     */
    private static void showCmdHelp() {
        HelpFormatter formatter = new HelpFormatter();
        System.out.print(Ayuda.INFO);
        formatter.printHelp(APPNAME + " " + BC3TOHTMLVERSION, opciones);
    }
    
    /**
     * Este m�todo muestra en pantalla la licencia del software, sin paginaci�n.
     */
    private static void showLicense() {
        System.out.println(Ayuda.INFO);
        Bc3ToHtmlLicense lic = new Bc3ToHtmlLicense();
        System.out.println(lic.getLICENSE());
    }
    
    /**
     * Muestra informaci�n b�sica del sistema SIN variables de entorno.
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
                + "Versi�n:   " + BC3TOHTMLVERSION + "\n");
    }
    
    /**
     * Este m�todo comprueba si el argumento pasado deber�a ser un archivo.
     * Se considera que esto se da cuando no coincide con ninguno de los argumentos
     * previstos para su entrada en la l�nea de comandos.
     * @param argument El argumento a comprobar si est� entre las opciones previstas
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
        System.out.println("El archivo a procesar es: " + LineaComandos.nombreArchivoAProcesar);
        System.out.println("El archivo de salida es: "  + LineaComandos.nombreArchivoSalida);
    }
    
    /**
     * Ejecuci�n en el caso de que el usuario introduzca un �nico par�metro
     * un �nico par�metro que debe coincidir con el nombre del archivo a procesar
     * @param nombreArchivo <strong>String</strong> Nombre del archivo a procesar
     */
    private static void procesarArchivo (String nombreArchivo) {
        LineaComandos.nombreArchivoAProcesar    = (FileManage.isFileAvailable(nombreArchivo)) ? nombreArchivo : null;
        String archivoSalida                    = FileManage.getNameFromBase(LineaComandos.nombreArchivoAProcesar, "html");
        LineaComandos.nombreArchivoSalida       = (!FileManage.isFileAvailable(archivoSalida)) ? archivoSalida : null;
        
        // hay que establecer los valores por defecto de las variables de trabajo
        // que se establecen en gestionaArgumentos seg�n la entrada del usuario
        
        procesarArchivo();
    }
    
    private static void gestionaArgumentos(CommandLine cmd) {
        if(opciones.hasOption("f") && opciones.hasOption("o")){
            LineaComandos.nombrarArchivoSalida      = true;
            
            LineaComandos.nombreArchivoAProcesar    = cmd.getOptionValue("f");
            LineaComandos.nombreArchivoSalida       = cmd.getOptionValue("o");
            
            LineaComandos.mostrarEstadisticas       = opciones.hasOption("s");
            LineaComandos.modoVerbose               = opciones.hasOption("w");
            LineaComandos.usarPlantillaExterna      = opciones.hasOption("t");
            
            if(LineaComandos.usarPlantillaExterna) {
                // implementar uso de plantilla externa
            }
            
            LineaComandos.salidaApresupuestoCiego   = opciones.hasOption("b");
            LineaComandos.salidaSoloDescompuestos   = opciones.hasOption("d");
            LineaComandos.salidaSoloElementales     = opciones.hasOption("e");
            LineaComandos.salidaConMediciones       = opciones.hasOption("m");
            LineaComandos.incluirResumen            = opciones.hasOption("r");
            
            LineaComandos.mantenerArchivoLog        = opciones.hasOption("l");
            if(LineaComandos.mantenerArchivoLog){
                // implementar uso de archivo log
            }
            
            LineaComandos.asumirRespuestaPositiva   = opciones.hasOption("y");
            
            procesarArchivo();
        }
    }
}