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
import org.ph.System.FileManage;
import org.ph.System.RandomGenerator;
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
    public static final String  BC3TOHTMLVERSION    = "v.0.1.0.0";

    /**
     * @param args Argumentos de la l�nea de comandos
     * @throws org.ph.errors.ErrorInArgumentsException
     */
    public static void main(String[] args) throws ErrorInArgumentsException {
        testArgs(args);
        
//        System.out.println("Plantilla html: "       + LineaComandos.nombrePlantillaHtml);
//        System.out.println("Archivo a procesar: "   + LineaComandos.nombreArchivoAProcesar);
//        System.out.println("Archivo de salida: "    + LineaComandos.nombreArchivoSalida);
        
//        switch (args.length) {
//            case 0: System.out.println(Ayuda.INFO);
//                break;
//            case 1: 
//                if (args[0].equalsIgnoreCase("-i")) {
////                    SystemProperties sp = new SystemProperties();
////                    System.out.println(sp.toConsoleString());
//                } else {
//                    /*
//                    ** Esto hay que hacerlo de otra manera...
//                    ** al introducir los datos el usuario puede hacerlo, incialmente,
//                    ** �nicamente de dos maneras...
//                    **  1. manera resumida o directa: java -jar bc3tohtml archivo.bc3
//                    **  2. manera larga con argumentos en la l�nea de comando: ...
//                    */
//                    
////////                    BC3File fip = new BC3File(args[0]);
////////                    
////////                    if (fip.inspect()) {
////////                        if (!(fip.bc3tohtml())) System.out.println("Conversi�n NO realizada o realizada parcialmente.");
////////                        else System.out.println("Conversi�n finalizada.");
////////                    } else {
////////                        System.out.println("\nEl archivo referenciado " + args[0] + " NO es procesable"
////////                                + " (no se puede leer, no es un archivo, no existe o es una carpeta).");
////////                    }
//                }
//                break;
//        }
    }
    
    /**
     * M�todo que comprueba los datos introducidos por el usuario en la l�nea de comandos.
     * @param args Array en el que cada elemento es un argumento de la l�nea de comandos introducido por el usuario
     * @throws ErrorInArgumentsException Error irrecuperable en la introducci�n de comandos por parte del usuario
     */
    private static void testArgs(String[] args) throws ErrorInArgumentsException {
        String s;
        // <editor-fold defaultstate="expanded" desc=" COMMAND LINE SWITCHES SELECTION "> 
        for (int j = 0; j < args.length; j++) {
            s = args[j];
            switch (s.toLowerCase()) {
                case "-?":  // ayuda
                    System.out.println(Ayuda.DETAILEDINFO);
                    break;
                case "/t3st":   // test
                    System.out.println("�Modo de prueba seleccionado!");
                    LineaComandos.opcionTest = true;
                    break;
                case "-b":  // blind: presupuesto ciego
                    LineaComandos.salidaApresupuestoCiego = true;
                    break;
                case "-i":  // informaci�n del sistema
                    System.out.println(Ayuda.INFO);
                    SystemProperties sp = new SystemProperties();
                    System.out.println(sp.toConsoleString());
                    break;
                case "-d":  // solo descompuestos
                    LineaComandos.salidaSoloDescompuestos = true;
                    break;
                case "-e":  // solo elementales
                    LineaComandos.salidaSoloElementales = true;
                    break;
                case "-f":  // a continuaci�n se establece el archivo de entrada, a procesar
                    String archivoAProcesar = (s.equals(args[j]) && args.length > j) ? args[j + 1] : args[j];
                    
                    if(FileManage.isFileAvailable(archivoAProcesar)) {
                        LineaComandos.nombreArchivoAProcesar = archivoAProcesar;
                        LineaComandos.nombrarArchivoSalida = true;
                    } else {
                        throw new ErrorInArgumentsException("El archivo " + archivoAProcesar + " falta o no es utilizable.");
                    }
                    break;
                case "-l":  // generar archivo log
                    LineaComandos.mantenerArchivoLog = true;
                    break;
                case "-p":  // solo presupuesto
                    break;
                case "-m":  // con mediciones
                    LineaComandos.salidaConMediciones = true;
                    break;
                case "-t":  // utilizar plantilla html
                    if((s.equals(args[j])) && (args.length > j)){
                        LineaComandos.nombrePlantillaHtml = (s.equals(args[j])) ? args[j + 1] : "";
                    } else {
                        throw new ErrorInArgumentsException("No se ha designado el archivo de plantilla de manera correcta.");
                    }
                    break;
                case "-r":  // resumen
                    LineaComandos.mostrarResumen = true;
                    break;
                case "-s":  // estad�sticas
                    LineaComandos.mostrarEstadisticas = true;
                    break;
                case "-o":  // a continuaci�n se establece el archivo de salida, obviando el nombre por defecto
                    if((s.equals(args[j])) && (args.length > j)){
                        String nombreArchivoSalida = args[j + 1];
                        if(FileManage.fileNameIsUsable(nombreArchivoSalida)) {
                            LineaComandos.nombreArchivoSalida = nombreArchivoSalida;
                            LineaComandos.nombrarArchivoSalida = true;
                        } else {
                            // otras opciones que se podr�an considerar...
                            // sobreescribir el archivo, etc.
                            if (!preguntarUsuarioSiNo("�Sobreescribir el archivo " + nombreArchivoSalida + "?")) {
                                LineaComandos.nombreArchivoSalida = nombreArchivoSalida;
                                LineaComandos.nombrarArchivoSalida = true;
                            } else
                            throw new ErrorInArgumentsException("El archivo " + nombreArchivoSalida + " falta o no es utilizable.");
                        }
                        
                    } else {
//                        LineaComandos.nombreArchivoSalida   = "";
                        LineaComandos.nombrarArchivoSalida  = false;
                        throw new ErrorInArgumentsException("El n�mero de argumentos relacionados con el nombre de archivo de salida no es correcto.");
                    }
                    break;
                case "-v":  // modo verbose... se muestran en pantalla todos los pasos... :-P
                    LineaComandos.modoVerbose = true;
                    break;
                case "-y":
                    LineaComandos.asumirRespuestaPositiva = true;
                    break;
                
                default:    // se procesa todo lo que expl�citamente no entre dentro de uno de los casos previstos
                    switch (args.length) {
                        case 1:
                            if (args.length == 1) {
                                if(FileManage.isFileAvailable(args[j])) {
                                    LineaComandos.nombreArchivoAProcesar = args[j];
                                    LineaComandos.nombreArchivoSalida = setNombreArchivoSalida(args[j]);
                                    boolean sobreescribir = false;
                                    while (!FileManage.fileNameIsUsable(LineaComandos.nombreArchivoSalida) && !sobreescribir) {
                                        if (!preguntarUsuarioSiNo("El archivo " + LineaComandos.nombreArchivoSalida + ""
                                                + " existe. �Desea sobreescribirlo?")) {
                                            
                                            LineaComandos.nombreArchivoSalida = LineaComandos.nombreArchivoSalida.replace(".html", 
                                                    "." + RandomGenerator.getRandomAlphaNumericString(6) + ".html");
                                            System.out.println("Se ha generado autom�ticamente el nombre " + LineaComandos.nombreArchivoSalida);
                                        } else {
                                            sobreescribir = true;
                                        }
                                    }
                                        
                                    LineaComandos.nombrePlantillaHtml = "{default}";
                                    
                                } else {
                                    // otras opciones que se podr�an considerar...
                                    // sobreescribir el archivo, etc.
                                    throw new ErrorInArgumentsException("El archivo " + args[j] + " falta o no se puede leer.");
                                }
                            }
                            break;
                    }
                    break;
            }
        }
        if (args.length == 0) {
            System.out.println(Ayuda.INFO);
        }
        // </editor-fold>
    }
    
    /**
     * Plantea una pregunta al usuario a la que s�lo tiene que responder s� o no.
     * Para deducir la respuesta, se adopta �nicamente la primera letra de los
     * datos introducidos por el usuario.
     * La pregunta se repite hasta que el usuario introduce datos v�lidos.
     * El m�todo no es sensible a may�sculas y min�sculas.
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
}