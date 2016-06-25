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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import org.ph.System.FileManage;
import org.ph.bc3Format.ConstantesTexto;
import org.ph.bc3Format.Registro_C_concepto;
import org.ph.bc3Format.Registro_D_descomposicion;
import org.ph.bc3Format.Registro_E_entidad;
import org.ph.bc3Format.Registro_G_informacionGrafica;
import org.ph.bc3Format.Registro_K_coeficientes;
import org.ph.bc3Format.Registro_L_pliegos;
import org.ph.bc3Format.Registro_M_mediciones;
import org.ph.bc3Format.Registro_T_texto;
import org.ph.bc3Format.Registro_V_prpdad;
import org.ph.loggerToFile.Bc3ParserLogger;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class BC3File {
    private final String                                fileName;
    
    private Registro_V_prpdad                           rPropiedad;
    private Registro_K_coeficientes                     rCoeficientes;
    private ArrayList <Registro_C_concepto>             rCodigos;
    private ArrayList <Registro_D_descomposicion>       rDescompuestos;
    private ArrayList <Registro_T_texto>                rTextos;
    private ArrayList <Registro_M_mediciones>           rMediciones;
    private ArrayList <Registro_L_pliegos>              rPliegos;
    private ArrayList <Registro_G_informacionGrafica>   rInformaGrafica;
    private ArrayList <Registro_E_entidad>              rEntidades;
    
    private Bc3ParserLogger                             log;
    
    /**
     * Archivo a leer
     * @param fileName ruta del archivo a leer
     */
    public BC3File (String fileName) {
        this.fileName = fileName;
    }
    
    public  boolean inspect() {
        return FileManage.isFileAvailable(fileName);
    }
    
    public boolean bc3tohtml() {
        if (LineaComandos.mantenerArchivoLog) log = new Bc3ParserLogger();
        boolean conversion = false, procesar = false;
        if (inspect()) {
            try {
                if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Procesando " + fileName);
                String lineaLeida;      // almacenamiento de la línea leída
                int numLineaLeida = 1;  // Número de línea leída
//                boolean mostrarLineas = false; int mLdesde = 1090, mLhasta = 1100; // para bucle de control
                
                // se lee el archivo línea a línea
//                BufferedReader br = new BufferedReader(new FileReader(new File(fileName))); // original
                
                
                // leyendo archivos (ANSI (Cp1252))
                // primero averiguamos la codificación del archivo
                InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
                String codificacion = isr.getEncoding();
                isr.close();
                
                BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream (fileName), codificacion));
                // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(fileName), "Cp1252"));
                // Alternativas: 850 ("Cp850") DOS | 437 ("Cp437") DOS-US
                
                StringBuilder sb    = new StringBuilder();
                
                rCodigos            = new ArrayList <Registro_C_concepto>();
                rDescompuestos      = new ArrayList <Registro_D_descomposicion>();
                rTextos             = new ArrayList <Registro_T_texto>();
                rMediciones         = new ArrayList <Registro_M_mediciones>();
                rPliegos            = new ArrayList <Registro_L_pliegos>();
                rInformaGrafica     = new ArrayList <Registro_G_informacionGrafica>();
                rEntidades          = new ArrayList <Registro_E_entidad>();
                
                if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Variables de almacenamiento establecidas.");
                
                double tt;                          // variables para medir la duración del proceso
                long t0 = System.nanoTime(), t1;
                
                if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Iniciando la lectura...");
                
                // <editor-fold defaultstate="expanded" desc=" Lectura línea a línea del archivo BC3 "> // http://wiki.netbeans.org/SurroundWithCodeFolding
                
                while ((lineaLeida = br.readLine()) != null) {
                    // realmente el separador de conceptos en el formato BC3 es el carácter '~' (ASCII 126)
                    // este carácter se ha almacenado en ConstantesTexto.inicioConcepto
                    // se deben ir añadiendo líneas al registro hasta que se encuentre el carácter '~' de nuevo

                    numLineaLeida++;
                    if (lineaLeida.length() > 1) {
                        if(String.valueOf(lineaLeida.charAt(0)).contains(ConstantesTexto.inicioConcepto)) {
                            procesar = true;
                        } else {
                            sb.append(lineaLeida);
                        }
                    } else {
                        sb.append(lineaLeida);
                    }
                    
                    
                    if (procesar) {
                        String[] datosLinea;
//                        if (String.valueOf(sb.charAt(sb.length() - 1)).equals(ConstantesTexto.separador)) sb.append(" ");   // Propósito de prueba
                        datosLinea = sb.toString().split(ConstantesTexto.separador);
                        
                        // <editor-fold defaultstate="expanded" desc=" switch de proceso de cabecera de línea ">
                        switch (datosLinea[0].toUpperCase()) {
                            case "~V": // propiedad y version
                                procesa_V(datosLinea);
                                break;
                            case "~K": // coeficientes
                                procesa_K(datosLinea);
                                break;
                            case "~C": // concepto
                                procesa_C(datosLinea);
                                break;
                            case "~D": // descomposición
                                procesa_D(datosLinea);
                                break;
                            case "~Y": // añadir descomposición
                                break;
                            case "~R": // descomposición de residuos
                                break;
                            case "~T": // texto
                                procesa_T(datosLinea);
                                break;
                            case "~P": // paramétrica
                                break;
                            case "~L": // pliegos
                                procesa_L(datosLinea);
                                break;
                            case "~Q": // pliegos (modelo 2)
                                break;
                            case "~J": // pliegos (modelo 2+)
                                break;
                            case "~W": // ámbito geográfico
                                break;
                            case "~G": // información gráfica
                                procesa_G(datosLinea);
                                break;
                            case "~E": // entidad
                                procesa_E(datosLinea);
                                break;
                            case "~O": // relación comercial
                                break;
                            case "~X": // información técnica
                                break;
                            case "~M": // mediciones
                                procesa_M(datosLinea);
                                break;
                            case "~N": // añadir mediciones
                                break;
                            case "~A": // claves
                                break;
                            case "~B": // cambio de código
                                break;
                            case "~F": // documento adjunto
                                break;
                            default:
                                       // ¿?
                        }
                        // </editor-fold>
                        sb.setLength(0);
                        sb.append(lineaLeida);
                        procesar = false;
                    }
                }
                // </editor-fold>
                
                if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Lectura finalizada.");
                
                t1 = System.nanoTime();
                tt = (t1 - t0) / 1000000000l;
                
                
                br.close();
                if (LineaComandos.mostrarEstadisticas) {
                    StringBuilder statistics = new StringBuilder();
                    
                    statistics.append("ESTADÍSTICAS:\n");
                    statistics.append("El proceso de lectura ha durado ").append(String.format("%2.4f", tt)).append(" segundos.\n");
                    statistics.append("  Líneas leídas: ").append(numLineaLeida).append(".\n");
                    statistics.append("    ").append(rCodigos.size()).append(" conceptos.\n");
                    statistics.append("    ").append(rDescompuestos.size()).append(" registros de descomposición.\n");
                    statistics.append("    ").append(rMediciones.size()).append(" partidas con mediciones.\n");
                    statistics.append("    ").append(rTextos.size()).append(" campos de texto.\n");
                    statistics.append("    ").append(rPliegos.size()).append(" pliegos.\n");
                    statistics.append("    ").append(rInformaGrafica.size()).append(" registros gráficos.\n");
                    statistics.append("    ").append(rEntidades.size()).append(" entidades.");
                    
                    System.out.println(statistics);
                }
                
            } catch (FileNotFoundException ex) {
                System.out.println("El archivo " + fileName + " no se encuentra.");
            } catch (IOException ex) {
                System.out.println("El archivo " + fileName + " no se puede leer.");
            } catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("Se ha producido un error en los límites de la matriz... ¿de cuál? " + ex.getCause().getMessage());
            }
        }
        
        // <editor-fold defaultstate="expanded" desc=" lectura línea a línea del archivo html ">
        String plantillaHTML = "";
        if (!LineaComandos.usarPlantillaExterna) {
            plantillaHTML = "/org/ph/htmlFormat/mainTemplate.html";
        } else {
            System.out.println("Opción de utilización de plantilla externa no implementada. Utilizando plantilla por defecto.");
            plantillaHTML = "/org/ph/htmlFormat/mainTemplate.html";
        }

        InputStream is = this.getClass().getResourceAsStream(plantillaHTML);
        
        conversion = generaHTML(is);
        
        // </editor-fold>
        
//        conversion = true;
        return conversion;
    }
    
    /**
     * Procesa la línea de propiedad del archivo BC3
     * @param linea <code>String[]</code> matriz que almacena cada pieza de la línea en una celda de la matriz
     */
    private void procesa_V (String[] linea) {
        // aquí deberemos comenzar a crear el DOM
        // http://docs.oracle.com/javase/tutorial/jaxp/xslt/generatingXML.html
        // ...
        rPropiedad = new Registro_V_prpdad(linea);
        if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Registro de propiedad procesado.");
        if (LineaComandos.modoVerbose) {
            System.out.println("Datos de propiedad y versión:");
            System.out.println("    Propiedad del archivo: "    + rPropiedad.getPROPIEDAD_ARCHIVO());
            System.out.println("    Versión:               "    + rPropiedad.getVERSION_FORMATO());
            System.out.println("    Programa de emisión:   "    + rPropiedad.getPROGRAMA_EMISION());
            System.out.println("    Cabecera:              "    + rPropiedad.getCABECERA());
            System.out.println("    Juego de caracteres:   "    + rPropiedad.getJUEGO_CARACTERES());
        }
    }
    
    /**
     * Procesa la línea de coeficientes del archivo BC3
     * @param linea <code>String[]</code> matriz que almacena cada pieza de la línea en una celda de la matriz
     */
    private void procesa_K (String[] linea) {
        rCoeficientes = new Registro_K_coeficientes(linea);
        if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Registro de coeficientes procesado.");
        if (LineaComandos.modoVerbose) {
            System.out.println("DN: " + rCoeficientes.getDN());
            System.out.println("DD: " + rCoeficientes.getDD());
            System.out.println("DS: " + rCoeficientes.getDS());
            System.out.println("DR: " + rCoeficientes.getDR());
            System.out.println("DI: " + rCoeficientes.getDI());
            System.out.println("DP: " + rCoeficientes.getDP());
            System.out.println("DC: " + rCoeficientes.getDC());
            System.out.println("DM: " + rCoeficientes.getDM());
            System.out.println("DIVISA: " + rCoeficientes.getDIVISA ());

            System.out.println("CI: " + rCoeficientes.getCI());
            System.out.println("GG: " + rCoeficientes.getGG());
            System.out.println("BI: " + rCoeficientes.getBI());
            System.out.println("BAJA: " + rCoeficientes.getBAJA());
            System.out.println("IVA: " + rCoeficientes.getIVA());
            System.out.println("DRC: " + rCoeficientes.getDRC());

            System.out.println("DCP: " + rCoeficientes.getDCP());
            System.out.println("DFS: " + rCoeficientes.getDFS());
            System.out.println("DRS: " + rCoeficientes.getDRS());

            System.out.println("DUO: " + rCoeficientes.getDUO());
            System.out.println("DII: " + rCoeficientes.getDII());
            System.out.println("DES: " + rCoeficientes.getDES());
            System.out.println("DNM: " + rCoeficientes.getDNM());
            System.out.println("DDM: " + rCoeficientes.getDDM());
            System.out.println("DSM: " + rCoeficientes.getDSM());
            System.out.println("DSP: " + rCoeficientes.getDSP());
            System.out.println("DEC: " + rCoeficientes.getDEC());
            System.out.println("DIVISA2: " + rCoeficientes.getDIVISA2());
        }
    }
    
    /**
     * Procesa una línea de tipo concepto del archivo BC3
     * @param linea <code>String[]</code> matriz que almacena cada pieza de la línea en una celda de la matriz
     */
    private void procesa_C (String[] linea) {
        try {
            rCodigos.add(new Registro_C_concepto(linea));
//        if (rCodigos.size() == 1) {
//            // imprimir por pantalla la primera ocurrencia
//            System.out.println("Código: "   + rCodigos.get(0).getCodigo());
//            System.out.println("Código2: "  + rCodigos.get(0).getCodigo2());
//            System.out.println("Tipo: "     + rCodigos.get(0).getTipo());
//            System.out.println("Unidad: "   + rCodigos.get(0).getUnidad());
//            System.out.println("Resumen: "  + rCodigos.get(0).getResumen());
//            System.out.println("Fecha: "    + rCodigos.get(0).getFecha());
//            System.out.println("Precio: "   + rCodigos.get(0).getPrecio());
//        }
        } catch (ParseException ex) {
            if (LineaComandos.modoVerbose) System.out.println("Error: " + ex.getMessage());
        }
    }
    
    private void procesa_D (String[] linea) {
        rDescompuestos.add(new Registro_D_descomposicion(linea));
        
//        if (rDescompuestos.size() == 1) {
//            System.out.println("Código padre             : " + rDescompuestos.get(0).getCodigoPadre());
//            System.out.println("Número de hijos          : " + rDescompuestos.get(0).getCodigosHijos().size());
//            System.out.println("Código hijo              : " + rDescompuestos.get(0).getCodigosHijos().get(4).getCodigoHijo());
//            System.out.println("Código hijo - factor     : " + rDescompuestos.get(0).getCodigosHijos().get(4).getFactor());
//            System.out.println("Código hijo - rendimiento: " + rDescompuestos.get(0).getCodigosHijos().get(4).getRendimiento());
//        }
    }
    
    private void procesa_T (String[] linea) {
        rTextos.add(new Registro_T_texto(linea));
        
//        if (rTextos.size() == 1) {
//            System.out.println("código: " + rTextos.get(0).getCodigoConcepto());
//            System.out.println("============TEXTO============\n" + rTextos.get(0).getTextoDescriptivo());
//        }
        
    }
    
    private void procesa_M (String[] linea) {
        rMediciones.add(new Registro_M_mediciones(linea));
        
//        if (rMediciones.size() == 1) {
//            System.out.println("Se procesa...");
//            System.out.println("Código padre  : " + rMediciones.get(0).getCodigoPadre());
//            System.out.println("Código hijo   : " + rMediciones.get(0).getCodigoHijo());
//            System.out.println("Medición total: " + rMediciones.get(0).getMedicionTotal());
//            System.out.println("Etiqueta      : " + rMediciones.get(0).getEtiqueta());
//            System.out.println("Posición      : " + rMediciones.get(0).getPosicion()[0]);
//            System.out.print("Línea         : " + rMediciones.get(0).getMediciones().get(0).getUnidades());
//            System.out.print("\t" + rMediciones.get(0).getMediciones().get(0).getLatitud());
//            System.out.print("\t" + rMediciones.get(0).getMediciones().get(0).getLongitud());
//            System.out.println("\t" + rMediciones.get(0).getMediciones().get(0).getAltura());
//        }
    }
    
    private void procesa_L (String [] linea) {
        rPliegos.add(new Registro_L_pliegos(linea));
        
//        if (rPliegos.size() == 1) {
//            System.out.println("Código del concepto del pliego: " + rPliegos.get(0).getCodigo_concepto());
//            System.out.println("Código de sección del pliego: " + rPliegos.get(0).getSeccionesPliego().get(0).getCodigo_seccion_pliego());
//            System.out.println("Texto de la sección del pliego: " + rPliegos.get(0).getSeccionesPliego().get(0).getTexto_seccion_pliego());
//            System.out.println("Archivo RTF: " + rPliegos.get(0).getArchivo_texto_rtf());
//            System.out.println("Archivo HTM: " + rPliegos.get(0).getArchivo_texto_htm());
//        }
    }
    
    private void procesa_G (String [] linea) {
        rInformaGrafica.add(new Registro_G_informacionGrafica(linea));
        
//        if (rInformaGrafica.size() == 1) {
//            System.out.println("Código     : " + rInformaGrafica.get(0).getCodigo_concepto());
//            System.out.println("Información: " + rInformaGrafica.get(0).getInformacionGrafica().get(0));
//            System.out.println("URL externa: " + rInformaGrafica.get(0).getUrl_ext());
//        }
    }
    
    private void procesa_E (String[] linea) {
        rEntidades.add(new Registro_E_entidad(linea));
        
//        if (rEntidades.size() == 1) {
//            if (rEntidades.size() > 33) System.out.print("Código de entidad: " + rEntidades.get(rEntidades.size() - 1).getCodigo_entidad() + " ");
//        }
    }
    
    private boolean generaHTML (InputStream plantilla) {
        boolean trabajoRealizadoOK = false;
        try {
            BufferedReader br   = new BufferedReader(new InputStreamReader(plantilla));
            String lineaLeida;
            StringBuilder sb    = new StringBuilder();                                      // el cojunto del html
            StringBuilder st    = new StringBuilder();                                      // la estructura tabulada del presupuesto
            
            while ((lineaLeida = br.readLine()) != null) {
                
                if(lineaLeida.contains("{$propiedadArchivo}")) {
                    lineaLeida = lineaLeida.replace("{$propiedadArchivo}", rPropiedad.getPROPIEDAD_ARCHIVO() + " :: " + rPropiedad.getVERSION_FORMATO() + ""
                            + " :: " + rPropiedad.getPROGRAMA_EMISION());
                    sb.append(lineaLeida);      // aquí se almacena el HTML -en principio-
                    lineaLeida = "";
                }
                
                
                if(lineaLeida.contains("{$codigo}")) {                                      // aquí está la estructura tabulada
                    String lineaLeidaOriginal = lineaLeida;
                    
//                    StringBuilder ll = new StringBuilder();
//                    ll.append(lineaLeida);
                    boolean raiz = false;
                    for (int i = 0; i < rCodigos.size(); i++) {
                        if(rCodigos.get(i).getCodigo().contains("#")) {                     // se trata de un capítulo
                            if (rCodigos.get(i).getCodigo().contains("##")) raiz = true;    // código o capítulo raíz
                                
                            
                            lineaLeida = lineaLeida.replace("{$codigo}", "<b>" + rCodigos.get(i).getCodigo().replace("#", "") + "</b>");
                            lineaLeida = lineaLeida.replace("{$ud}", "");
                            lineaLeida = lineaLeida.replace("{$resumen}", "<b>" + rCodigos.get(i).getResumen() + "</b>");
                            lineaLeida = lineaLeida.replace("{$n}", "");
                            lineaLeida = lineaLeida.replace("{$longitud}", "");
                            lineaLeida = lineaLeida.replace("{$anchura}", "");
                            lineaLeida = lineaLeida.replace("{$altura}", "");
                            lineaLeida = lineaLeida.replace("{$parcial}", "");
                            lineaLeida = lineaLeida.replace("{$canpres}", "");
                            lineaLeida = lineaLeida.replace("{$prpres}", "");
                            lineaLeida = lineaLeida.replace("{$imppres}", "<b>" + String.format("%1$,3.02f", rCodigos.get(i).getPrecio()) + "</b>");
                            
                        } else {
//                            lineaLeida = lineaLeida.replace("{$codigo}", rCodigos.get(i).getCodigo());
//                            lineaLeida = lineaLeida.replace("{$ud}", rCodigos.get(i).getUnidad());
//                            lineaLeida = lineaLeida.replace("{$resumen}", rCodigos.get(i).getResumen());
//                            lineaLeida = lineaLeida.replace("{$prpres}", String.format("%1$,3.02f", rCodigos.get(i).getPrecio()));
                        }
                        
                        
                        if (!lineaLeida.equals(lineaLeidaOriginal)){
                            if (!raiz) st.append(lineaLeida + "</tr>\n<tr>"); else {
                                // concepto raíz
                                st.insert(0,lineaLeida + "</tr>\n<tr>");
                                raiz = false;
                            }
                        }
                        lineaLeida = lineaLeidaOriginal;
                    }
                    lineaLeida = "";
                }
                
                sb.append(st);
                sb.append(lineaLeida);
                st.delete(0, st.length());
                lineaLeida = "";
            }
            
            String archivoHtmlSalida = getHtmlFileName(fileName) + ".html";
//            System.out.println("El archivo HTML se llamará... \"" + archivoHtmlSalida + "\"");
            
            
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(archivoHtmlSalida), "UTF-8"));
            
            System.out.println("Escribiendo en " + archivoHtmlSalida + "...");
            writer.append(sb);
            writer.close();
            System.out.println("Escritura finalizada correctamente.");
            
            trabajoRealizadoOK = true;
        } catch (UnsupportedEncodingException ex) {
            // la codificación no es la correcta
            System.out.println("Error de codificación de plantilla.");
        } catch (FileNotFoundException ex) {
            // no se encuentra el archivo
            System.out.println("No se encuentra el archivo de plantilla. \n" + ex.getLocalizedMessage());
        } catch (IOException ex) {
            // no se puede leer
            System.out.println("Error de E/S con el archivo de plantilla.");
        }
        
        return trabajoRealizadoOK;
    }
    
    private String getHtmlFileName(String fileName) {
        int punto = fileName.lastIndexOf(".");
//        String fn = fileName.substring(0, punto);
        
        return fileName.substring(0, punto);
    }
}