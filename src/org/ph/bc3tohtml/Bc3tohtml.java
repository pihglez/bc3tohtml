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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ph.System.FileManage;
import org.ph.System.MACAddress;
import org.ph.System.SerialNumber;
import org.ph.System.SystemProperties;
import org.ph.xmlFormat.XMLObjectEncoderDecoder;
import org.ph.bc3tohtml.help.Ayuda;
import org.ph.errors.ErrorInArgumentsException;
import org.ph.errors.ErrorInFormatException;

/**
 *
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class Bc3tohtml {
    /**
     * En esta constante se almacena la versi�n actual del software
     */
    public static final String  BC3TOHTMLVERSION    = "v0.5.1.0";
    /**
     * En esta constante se almacena el nombre original del software
     */
    public static final String  APPNAME             = "bc3tohtml";
    /**
     * Opciones de la l�nea de comandos.
     */
    private static Options      opciones;

    /**
     * @param args Argumentos de la l�nea de comandos
     * @throws org.ph.errors.ErrorInArgumentsException
     */
    public static void main(String[] args) throws ErrorInArgumentsException {
        opciones = new Options();
        setCommandLineOptions();
        
        testArgs(args);
    }
    
    /**
     * M�todo que comprueba los datos introducidos por el usuario en la l�nea de comandos
     * @param args Array en el que cada elemento es un argumento de la l�nea de comandos introducido por el usuario
     * @throws ErrorInArgumentsException Error irrecuperable en la introducci�n de comandos por parte del usuario
     */
    private static void testArgs(String[] args) throws ErrorInArgumentsException {
        // se gestiona la opci�n oculta (no documentada) de instalar el software 
        // en el sistema local
        // esta opci�n dar� la posibilidad de configurar el comportamiento por
        // defecto de la aplicaci�n en los casos en los que el usuario realice
        // una ejecuci�n directa, del tipo <bc3tohtml archivo.bc3>
        boolean installation = false;
        
        if (args.length == 1 && args[0].equals("-install")) {
            //<editor-fold defaultstate="collapsed" desc="Gesti�n de instalaci�n">
            installation = true;
            
            String instLocation;
            SystemProperties sp = new SystemProperties();
            String dirConfig = sp.getUserRootFolder() + sp.getFileSeparator() + ".config"; // carpeta de usuario/.config (Linux)
            if(FileManage.folderExists(dirConfig)) {
                instLocation = dirConfig + sp.getFileSeparator() + "bc3tohtml";
            } else {
                instLocation = sp.getUserRootFolder() + sp.getFileSeparator() + ".bc3tohtml";
            }
            
            int c = FileManage.createFolderStructure(instLocation);
            switch (c) {
                case -1:    // la localizaci�n se crea correctamente
                            // no se introduce break puesto que se debe continuar
                            // la ejecuci�n hacia case 0 ;-)
                    
                case 0:     // la localizaci�n existe
                    File xmlConfig = new File(instLocation + sp.getFileSeparator() + "bc3tohtml.config.xml");
                    Bc3ToHtmlConfig config = new Bc3ToHtmlConfig();
//                    config.setDefaults();
                    
                    
//                    XMLObjectEncoderDecoder.encode(config, xmlConfig);
                    XMLObjectEncoderDecoder.marshal(config, xmlConfig);
                    break;
                case 1:     // la localizaci�n NO se ha creado �ya existe? �error?
                    break;
                default:
                    break;
            }
            
            
            if (c == -1) {
                // la ruta se crea correctamente
                // aqu�, especificar las distintas opciones
                System.out.println("Instalaci�n realizada correctamente en " + instLocation);
            }
            
            try {
                String sn = SerialNumber.getSerialNumber();
                System.out.println("s/n: " + sn);
                System.out.println("installation location: " + instLocation);
                try {
//                    int i = FileManage.createFolderStructure("");
                    MACAddress mac = new MACAddress();
                    System.out.println("Direcci�n mac: " + mac.getMACAddress());
                    System.out.println("Adaptadores: " + Arrays.toString(mac.getNetworkInterfacesNames().toArray()));
                    /*
                    en windows  -> Controla*
                    en linux    -> eth*
                    */
                    System.out.println("              " + mac.getNetworkInterface("lo"));
                } catch (UnknownHostException ex) {
                    System.out.println("No host");
                } catch (SocketException ex) {
                    System.out.println("No socket");
                }
                
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("No se ha podido calcular el n�mero de serie.\n" +
                        ex.getLocalizedMessage());
            }
            //</editor-fold>
        }
        
        
        if (!installation) {
            //<editor-fold defaultstate="collapsed" desc="Gesti�n avanzada de la l�nea de comandos">
            try {
                CommandLine cmd = new DefaultParser().parse(opciones, args);
                LineaComandos.abrirWeb = abrirWeb(cmd);
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
//                    case 2:
//                        // situaci�n en la que, un elemento es el archivo y se considera una �nica opci�n adicional
//                        if (cmd.hasOption("y")) {
//                            String archivo;
//                            LineaComandos.asumirRespuestaPositiva = true;
//                            if(args[0].toLowerCase().contains("y") && args[0].length() == 1 ) {
//                                    archivo = args[1];
//                            } else  archivo = args[0];
//                            if (argumentIsFileName(archivo))            procesarArchivo(archivo);
//                        } else {
//                            throw new ErrorInArgumentsException("Parece que ha introducido un n�mero incorrecto de argumentos. Por favor, verifique la ayuda.");
//                        }
                    default:
                        if (args.length > 2) {
                            gestionaArgumentos(cmd);
                        } else {
                            System.out.println(Ayuda.INFO + Ayuda.DESCRIPCION);
                            System.out.println("Parece que ha introducido un n�mero incorrecto de argumentos. Por favor, verifique la ayuda.");
                        }
                        break;
                }
            } catch (ParseException ex) {
                System.out.println("Error al leer la l�nea de comandos: " + ex.getLocalizedMessage());
            }
            //</editor-fold>
        }
    }
    
    /**
     * Este m�todo devuelve <code>true</code> en el caso de que se deba abrir
     * la web del programa y <code>false</code> en caso contrario.
     * @param args <code>String[]</code> La lista de argumentos introducida.
     * @return <code>boolean</code> True en caso de que se pueda abrir la web y False en el contrario.
     */
    private static boolean abrirWeb(CommandLine cmd) {
        return !cmd.hasOption("noweb");
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
     * Este m�todo establece las opciones (<code>argumentos</code>) reconocibles
     * a trav�s de la l�nea de comandos.
     */
    private static void setCommandLineOptions(){
        opciones.addOption("?", false,  "Muestra esta ayuda");                                                      // ayuda
        opciones.addOption("b", false,  "Genera un presupuesto ciego (sin precios ni importes)");                   // presupuesto ciego
        opciones.addOption("d", false,  "Genera un documento solo con los precios descompuestos");                  // solo descompuestos
        opciones.addOption("e", false,  "Genera un documento solo con las entidades de la BBDD");                   // solo elementales
        opciones.addOption("f", true,   "Especifica a continuacion el archivo de entrada (.bc3)");                  // archivo (file) de entrada
        opciones.addOption("l", true,   "Especifica a continuacion el archivo de volcado (.log)");                  // archivo (log) de volcado
        opciones.addOption("m", false,  "Incluye las mediciones en el presupuesto");                                // incluir mediciones
        opciones.addOption("o", true,   "Especifica a continuacion el archivo de salida (.html)");                  // archivo (output) de salida
        opciones.addOption("p", false,  "Genera el presupuesto (opcion por defecto con un solo argumento)");        // incluir presupuesto
        opciones.addOption("r", false,  "Genera el resumen de presupuesto");                                        // incluir resumen
        opciones.addOption("s", false,  "Muestra estadisticas al terminar el proceso");                             // mostrar estadisticas
        opciones.addOption("t", true,   "Especifica a continuacion el archivo de plantilla a utilizar (.html)");    // archivo (template) a utilizar
        opciones.addOption("v", false,  "Muestra la version del sofware");                                          // muestra la version del software
        opciones.addOption("w", false,  "Activa el modo de informacion adicional (verbose)");                       // muestra la version del software
        opciones.addOption("y", false,  "Asume una respuesta positiva a las posibles preguntas");                   // asumir si (yes) a cualquier pregunta
        opciones.addOption("z", false,  "Muestra la licencia del software");                                        // muestra la licencia
        opciones.addOption("i", false,  "Muestra informacion del sistema");                                         // muestra informacion del sistema
        opciones.addOption("c", false,  "Fuerza la lectura de archivos en codificacion Windows\"Cp1252\"");         // fuerza lectura archivo bc3 codificacion Windows Cp1252
        opciones.addOption("noweb", false,  "Evita la apertura del navegador durante la transformacion.");          // evita que la utilizacion del software abra una ventana hacia la web principal del mismo.
//        opciones.addOption("install", false,  "Realiza la instalaci�n de la aplicaci�n en carpeta local.");         // realiza la instalaci�n de la aplicaci�n en carpeta local.
    }
    
    /**
     * Este m�todo muestra la ayuda avanzada del software.
     */
    private static void showCmdHelp() {
        HelpFormatter formatter = new HelpFormatter();
        System.out.print(Ayuda.INFO);
        formatter.printHelp(APPNAME + " " + BC3TOHTMLVERSION, opciones, true);
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
    
    /**
     * Muestra la informaci�n de versi�n del software
     */
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
    
    /**
     * Este m�todo se apoya en la clase est�tica LineaComandos para procesar el
     * archivo de entrada (BC3) en funci�n de los distintos argumentos de la
     * l�nea de comandos introducidos por el usuario.
     */
    private static void procesarArchivo() {
        // ver por qu� se/ da el error que se est� dando cuando se pretende procesar un archivo
        // cuya extensi�n no es bc3
        try {
            // el archivo de entrada debe existir y ser accesible
            if (!(FileManage.isFileAvailable(LineaComandos.nombreArchivoAProcesar))) {
                // no ok
                throw new ErrorInArgumentsException("El archivo de origen no puede ser le�do.");
            }
            
            // el archivo de salida NO debe existir
            if ((FileManage.isFileAvailable(LineaComandos.nombreArchivoSalida))) {
                // no ok
                if (LineaComandos.asumirRespuestaPositiva) {
                    System.out.println("Se sobeescribir� el archivo " + LineaComandos.nombreArchivoSalida + ".");
                } else {
                    throw new ErrorInArgumentsException("El archivo de salida existe. Debe especificar un nombre v�lido.");
                }
            }
            
            // deben establecerse alguno de los argumentos de proceso v�lidos (-p o -r)
            if (!(  LineaComandos.generarResumen            ||
                    LineaComandos.generarPresupuesto        || 
                    LineaComandos.salidaSoloDescompuestos   ||
                    LineaComandos.salidaSoloEntidades)) {
                
                throw new ErrorInArgumentsException(""
                        + "Los argumentos de proceso establecidos no son v�lidos.\n"
                        + "Debe elegir uno o varios de los elementos de salida:\n"
                        + "resumen, presupuesto, descompuestos o elementales");
            }
            
            // se cumplen las condiciones --> se realiza el proceso del archivo .bc3
            System.out.println("Procesando archivo " + LineaComandos.nombreArchivoAProcesar +
                    " --> " + LineaComandos.nombreArchivoSalida);
            
            
            if (LineaComandos.abrirWeb) WebOficialBc3ToHtml.open(WebOficialBc3ToHtml.direccion.WEB);     // el hilo debe informar si la web se abre correctamente y entonces pasa algo en caso contrario
            
            BC3File bc3f = new BC3File(LineaComandos.nombreArchivoAProcesar);
            if (bc3f.procesaBC3()) {
                System.out.println("Proceso finalizado correctamente.");
                if (LineaComandos.modoVerbose) System.out.println("Archivo de salida: " + LineaComandos.nombreArchivoSalida);
            }
        } catch (ErrorInFormatException ex) { // catch (ErrorInFormatException|ErrorInArgumentsException ex)
            System.out.println("Error irrecuperable: " + ex.getMessage());
        } catch (ErrorInArgumentsException ex) {
            System.out.println("Error irrecuperable: " + ex.getMessage());
        }
    }
    
    /**
     * Ejecuci�n en el caso de que el usuario introduzca un �nico par�metro
     * un �nico par�metro que debe coincidir con el nombre del archivo a procesar
     * @param nombreArchivo <code>String</code> Nombre del archivo a procesar
     */
    private static void procesarArchivo (String nombreArchivo) {
        // Error en determinados casos... p.e. cuando se introducen dos argumentos: uno el nombre de archivo y otro "-y"
        // a estudiar y arreglar
        LineaComandos.nombreArchivoAProcesar        = (FileManage.isFileAvailable(nombreArchivo)) ? nombreArchivo : null;
        String archivoSalida                        = FileManage.getNameFromBase(LineaComandos.nombreArchivoAProcesar, "html");
        LineaComandos.nombreArchivoSalida           = (!FileManage.isFileAvailable(archivoSalida) || 
                                                       LineaComandos.asumirRespuestaPositiva) ? 
                                                       archivoSalida : null;
        
        if (LineaComandos.nombreArchivoSalida == null) 
            try {
            throw new ErrorInArgumentsException("El nombre del archivo salida no es utilizable.");
        } catch (ErrorInArgumentsException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        // hay que establecer los valores por defecto de las variables de trabajo
        // que se establecen en gestionaArgumentos seg�n la entrada del usuario
        LineaComandos.generarResumen                = false;
        LineaComandos.generarPresupuesto            = true;
        
        procesarArchivo();
    }
    
    /**
     * Este m�todo gestiona los argumentos introducidos por el usuario
     * ayud�ndose de la librer�a de Apache Commons CLI, definiendo distintas
     * variables accesibles a trav�s de la clase est�tica <code>LineaComandos</code>.
     * @param cmd <code>CommandLine</code> La l�nea de comandos introducida por 
     * el usuario.
     */
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
            LineaComandos.salidaSoloEntidades       = cmd.hasOption("e");
            LineaComandos.incluirMediciones         = cmd.hasOption("m");
            LineaComandos.generarResumen            = cmd.hasOption("r");
            LineaComandos.generarPresupuesto        = cmd.hasOption("p");
            
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

/*
~ $ java -jar ./dist/bc3tohtml.jar cenzano.html
Error: El nombre del archivo salida no es utilizable.
Exception in thread "main" java.lang.NullPointerException
	at java.io.File.<init>(File.java:277)
	at org.ph.System.FileManage.isFileAvailable(FileManage.java:34)
	at org.ph.bc3tohtml.Bc3tohtml.procesarArchivo(Bc3tohtml.java:251)
	at org.ph.bc3tohtml.Bc3tohtml.procesarArchivo(Bc3tohtml.java:317)
	at org.ph.bc3tohtml.Bc3tohtml.testArgs(Bc3tohtml.java:83)
	at org.ph.bc3tohtml.Bc3tohtml.main(Bc3tohtml.java:59)

La cuesti�n es que se ha presupuesto de manera err�nea que los archivos de entrada
iban a tener extensi�n bc3 (y estar bien formados) por lo que se genera un puntero a 
null cuando resulta,
como es el caso que no se da esta circunstancia. -> arreglar
*/