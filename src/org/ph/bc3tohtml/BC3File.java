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
import java.text.ParseException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.ph.System.FileManage;
import org.ph.bc3Format.CodigosHijos;
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
import org.ph.errors.ErrorInFormatException;
import org.ph.loggerToFile.Bc3ParserLogger;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class BC3File {
    private final String                                bc3FileToProcess;
    
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
     * @param fileName <code>String</code> ruta del archivo a leer
     */
    public BC3File (String fileName) {
        this.bc3FileToProcess = fileName;
    }
    
    public boolean procesaBC3() throws ErrorInFormatException {
        if (LineaComandos.mantenerArchivoLog) log = new Bc3ParserLogger();
        boolean conversion = false, procesar = false;
        if (FileManage.isFileAvailable(bc3FileToProcess)) {
            try {
                if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Procesando " + bc3FileToProcess);
                String lineaLeida;      // almacenamiento de la línea leída
                int numLineaLeida = 0;  // Número de línea leída
//                boolean mostrarLineas = false; int mLdesde = 1090, mLhasta = 1100; // para bucle de control
                
                // primero averiguamos la codificación del archivo a no ser que se fuerce codificación Cp1252
                String codificacion;
                if (!LineaComandos.forzarCodificacionWindows) {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(bc3FileToProcess));
                    codificacion = isr.getEncoding();
                    if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Detectada codificación '" + codificacion + "'");
                    if (LineaComandos.modoVerbose) System.out.println("Codificación detectada: " + codificacion);
                    isr.close();
                } else {
                    codificacion = "Cp1252";
                    if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Forzando lectura de archivo BC3 en '" + codificacion + "'");
                    if (LineaComandos.modoVerbose) System.out.println("Codificación forzada: " + codificacion);
                }
                
                BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream (bc3FileToProcess), codificacion));
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
                    // se deben ir añadiendo líneas al registro hasta que se encuentre el carácter '~' de nuevo
                    
                    numLineaLeida++;
                    if (lineaLeida.length() > 1) {
                        if(String.valueOf(lineaLeida.charAt(0)).equals(ConstantesTexto.inicioConcepto)) {
                            procesar = true;
                        } else {
                            sb.append(lineaLeida);
                        }
                    } else {
                        sb.append(lineaLeida);
                    }
                    
                    // comprobación inicial de que el archivo tiene el formato
                    // BC3 (los dos primeros bytes deben ser "~V")
                    if (numLineaLeida == 1 && !(lineaLeida.substring(0, 2).equals("~V"))) throw new ErrorInFormatException(""
                                + "El archivo de origen no parece que tenga el formato BC3 adecuado.");
                    
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
                } // final de la ectura del archivo bc3
                // </editor-fold>
                
                if (LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Lectura finalizada.");
                
                t1 = System.nanoTime();
                tt = (t1 - t0) / 1000000l;  // duración en milisegundos
                
                
                br.close();
                if (LineaComandos.mostrarEstadisticas) {
                    StringBuilder statistics = new StringBuilder();
                    
                    statistics.append("ESTADÍSTICAS:\n");
                    statistics.append("El proceso de lectura ha durado ").append(String.format("%2.3f", tt)).append(" ms.\n");
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
                System.out.println("El archivo " + bc3FileToProcess + " no se encuentra.");
            } catch (IOException ex) {
                System.out.println("El archivo " + bc3FileToProcess + " no se puede leer.");
            } catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("Se ha producido un error en los límites de la matriz... ¿de cuál? " + ex.getCause().getMessage());
            }
        
        
        // <editor-fold defaultstate="expanded" desc=" lectura del archivo html de plantilla">
            String plantillaHTML;
            if (!LineaComandos.usarPlantillaExterna) {
                plantillaHTML = "/org/ph/htmlFormat/mainTemplate.html";
            } else {
                System.out.println("Opción de utilización de plantilla externa no implementada. Utilizando plantilla por defecto.");
                plantillaHTML = "/org/ph/htmlFormat/mainTemplate.html";
            }

            InputStream is = this.getClass().getResourceAsStream(plantillaHTML);

            conversion = generaHTML(is);
        
        // </editor-fold>
        }
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
        if (LineaComandos.modoVerbose)          System.out.println("Registro de coeficientes procesado.");
        if (LineaComandos.mantenerArchivoLog)   {
            log.appendTimedLogLine("DN: "       + rCoeficientes.getDN());
            log.appendTimedLogLine("DD: "       + rCoeficientes.getDD());
            log.appendTimedLogLine("DS: "       + rCoeficientes.getDS());
            log.appendTimedLogLine("DR: "       + rCoeficientes.getDR());
            log.appendTimedLogLine("DI: "       + rCoeficientes.getDI());
            log.appendTimedLogLine("DP: "       + rCoeficientes.getDP());
            log.appendTimedLogLine("DC: "       + rCoeficientes.getDC());
            log.appendTimedLogLine("DM: "       + rCoeficientes.getDM());
            log.appendTimedLogLine("DIVISA: "   + rCoeficientes.getDIVISA ());
            
            log.appendTimedLogLine("CI: "       + rCoeficientes.getCI());
            log.appendTimedLogLine("GG: "       + rCoeficientes.getGG());
            log.appendTimedLogLine("BI: "       + rCoeficientes.getBI());
            log.appendTimedLogLine("BAJA: "     + rCoeficientes.getBAJA());
            log.appendTimedLogLine("IVA: "      + rCoeficientes.getIVA());
            log.appendTimedLogLine("DRC: "      + rCoeficientes.getDRC());
            
            log.appendTimedLogLine("DCP: "      + rCoeficientes.getDCP());
            log.appendTimedLogLine("DFS: "      + rCoeficientes.getDFS());
            log.appendTimedLogLine("DRS: "      + rCoeficientes.getDRS());
            
            log.appendTimedLogLine("DUO: "      + rCoeficientes.getDUO());
            log.appendTimedLogLine("DII: "      + rCoeficientes.getDII());
            log.appendTimedLogLine("DES: "      + rCoeficientes.getDES());
            log.appendTimedLogLine("DNM: "      + rCoeficientes.getDNM());
            log.appendTimedLogLine("DDM: "      + rCoeficientes.getDDM());
            log.appendTimedLogLine("DSM: "      + rCoeficientes.getDSM());
            log.appendTimedLogLine("DSP: "      + rCoeficientes.getDSP());
            log.appendTimedLogLine("DEC: "      + rCoeficientes.getDEC());
            log.appendTimedLogLine("DIVISA2: "  + rCoeficientes.getDIVISA2());
        }
    }
    
    /**
     * Procesa una línea de tipo concepto del archivo BC3
     * @param linea <code>String[]</code> matriz que almacena cada pieza de la línea en una celda de la matriz
     */
    private void procesa_C (String[] linea) {
        try {
            rCodigos.add(new Registro_C_concepto(linea));
        } catch (ParseException ex) {
            if (LineaComandos.modoVerbose) System.out.println("Error: " + ex.getMessage());
        }
    }
    
    private void procesa_D (String[] linea) {
        rDescompuestos.add(new Registro_D_descomposicion(linea));
    }
    
    private void procesa_T (String[] linea) {
        rTextos.add(new Registro_T_texto(linea));
    }
    
    private void procesa_M (String[] linea) {
        rMediciones.add(new Registro_M_mediciones(linea));
    }
    
    private void procesa_L (String [] linea) {
        rPliegos.add(new Registro_L_pliegos(linea));
    }
    
    private void procesa_G (String [] linea) {
        rInformaGrafica.add(new Registro_G_informacionGrafica(linea));
    }
    
    private void procesa_E (String[] linea) {
        rEntidades.add(new Registro_E_entidad(linea));
    }
    
    /**
     * Método que genera el archivo HTML a partir de un archivo de plantilla.
     * Depende de la librería <a href="https://jsoup.org">jsoup</a>.
     * @param plantilla <code>InputStream</code> El archivo de plantilla a utilizar
     * @return <code>true</code> en caso de que la generación se realice correctamente y <code>false</code> en caso contrario.
     */
    private boolean generaHTML (InputStream plantilla) throws ErrorInFormatException {
        boolean trabajoRealizadoOK = false;
        try {
            // Implementación del uso de jsoup
            Document archivoHtml = Jsoup.parse(plantilla, "UTF-8", "http://www.google.com");
            if(LineaComandos.mantenerArchivoLog) log.appendTimedLogLine("Documento de plantilla analizado correctamente.");
            
            // elementos comunes
            Element tituloDocumento = archivoHtml.getElementById("titulo-documento");
            String tituloBBDD = getTituloBBDD();
            tituloDocumento.text("bc3tohtml :: " + tituloBBDD);
            Element propiedadDocumento = archivoHtml.getElementById("propiedad-archivo");
            propiedadDocumento.appendText(rPropiedad.getPROPIEDAD_ARCHIVO());
            
            Element encabezado = archivoHtml.getElementById("encabezado-principal");
            
            Element cuerpoTabla = archivoHtml.getElementById("cuerpotabla");
            Element filaDatos   = archivoHtml.getElementById("filadatos");
            Element nuevaFilaDatos, dato;
            
            if (LineaComandos.incluirResumen || LineaComandos.incluirPresupuesto || LineaComandos.salidaSoloDescompuestos) {
                if (!LineaComandos.salidaConMediciones) {
                    dato = archivoHtml.getElementById("n");
                    dato.text("");
                    dato = archivoHtml.getElementById("longitud");
                    dato.text("");
                    dato = archivoHtml.getElementById("anchura");
                    dato.text("");
                    dato = archivoHtml.getElementById("altura");
                    dato.text("");
                    dato = archivoHtml.getElementById("parcial");
                    dato.text("");
                    dato = archivoHtml.getElementById("resumen");
                    dato.attr("width", "70%");
                }
                
                for(Registro_C_concepto registro : rCodigos) {
                    if(registro.getCodigo().contains("##")){                        // código raíz
                        cuerpoTabla.prependChild(filaDatos.clone());
                        
                        nuevaFilaDatos = archivoHtml.getElementById("filadatos").firstElementSibling();
                        dato = nuevaFilaDatos.getElementById("codigo_0");
                        dato.append("<h3>" + registro.getCodigo().replace("##", "") + "</h3>");
                        dato = nuevaFilaDatos.getElementById("resumen_0");
                        dato.append("<h3>" + registro.getResumen() + "</h3>");
                        if (!LineaComandos.salidaSoloDescompuestos) {
                            dato = nuevaFilaDatos.getElementById("imppres_0");
                            dato.append("<h3>" + String.format("%1$,.2f", registro.getPrecio()) + "</h3>");                // formato del número
                        }
                    }

                    if(!(registro.getCodigo().contains("##")) && registro.getCodigo().contains("#")){   // capítulo normal
                        cuerpoTabla.appendChild(filaDatos.clone());

                        nuevaFilaDatos = archivoHtml.getElementById("filadatos").lastElementSibling();
                        dato = nuevaFilaDatos.getElementById("codigo_0");
                        dato.append("<h4>" + registro.getCodigo().replace("#", "") + "</h4>");
                        dato = nuevaFilaDatos.getElementById("resumen_0");
                        dato.append("<h4>" + registro.getResumen() + "</h4>");
                        if (!LineaComandos.salidaSoloDescompuestos) {
                            dato = nuevaFilaDatos.getElementById("imppres_0");
                            dato.append("<h4>" + String.format("%1$,.2f", registro.getPrecio()) + "</h4>");
                        }
                        
                        if(LineaComandos.incluirPresupuesto) {
                            double rendimiento = 0d, precio = 0d, importe = 0d;
                            StringBuilder sb = new StringBuilder();
                            for (Registro_D_descomposicion desc : rDescompuestos) {
                                if (desc.getCodigoPadre().equals(registro.getCodigo())) {   // coincide con el código padre
                                    
                                    for(CodigosHijos codigosHijo : desc.getCodigosHijos()) {
                                        // se añaden los descompuestos
                                        cuerpoTabla.appendChild(filaDatos.clone());
                                        rendimiento = codigosHijo.getRendimiento();
                                        precio      = getPrecioDeCodigo(codigosHijo.getCodigoHijo());
                                        importe     = rendimiento * precio;

                                        nuevaFilaDatos = archivoHtml.getElementById("filadatos").lastElementSibling();

                                        dato = nuevaFilaDatos.getElementById("codigo_0");
                                        dato.text(codigosHijo.getCodigoHijo());
                                        
                                        dato = nuevaFilaDatos.getElementById("ud_0");
                                        dato.text(getUnidadDeCodigo(codigosHijo.getCodigoHijo()));
                                        
                                        dato = nuevaFilaDatos.getElementById("resumen_0");
                                        dato.append("<strong>" + getResumenDeCodigo(codigosHijo.getCodigoHijo()) + "</strong>" + "".concat(
                                                (sb.append(getTextoDeCodigo(codigosHijo.getCodigoHijo())).length() > 0) ?
                                                        "<br/>" + sb.toString() :
                                                        ""
                                        ));
                                        
                                        dato = nuevaFilaDatos.getElementById("canpres_0");
                                        dato.text(String.format("%1$,.2f", rendimiento));
                                        
                                        dato = nuevaFilaDatos.getElementById("prpres_0");
                                        dato.text(String.format("%1$,.2f", precio));
                                        
                                        dato = nuevaFilaDatos.getElementById("imppres_0");
                                        dato.text(String.format("%1$,.2f", importe));
                                        sb.setLength(0);
                                    }                                    

                                }
                            }
                        }
                    }
                }
            }
            
            if(LineaComandos.salidaSoloElementales && (rEntidades.size() > 0)) {
                // TODO: habría que quitar u ocultar los encabezados
                
                encabezado.text("Entidades");
                for(Registro_E_entidad registro : rEntidades) {
                    cuerpoTabla.prependChild(filaDatos.clone());
                        
                    nuevaFilaDatos = archivoHtml.getElementById("filadatos").firstElementSibling();
                    dato = nuevaFilaDatos.getElementById("codigo_0");
                    dato.appendText(registro.getCodigo_entidad());
                    dato = nuevaFilaDatos.getElementById("resumen_0");
                    String entidad  = registro.getNombre()
                                    + ((registro.getCif()   != null) ? "<br/>" + registro.getCif()    : "")
                                    + ((registro.getNombre()!= null) ? "<br/>" + registro.getResumen(): "")
                                    + ((registro.getWeb()   != null) ? "<br/>" + registro.getWeb()    : "")
                                    + ((registro.getWeb()   != null) ? "<br/>" + registro.getEmail()  : "");
                    dato.append(entidad);
                    
                    Element elemento;
                    elemento = archivoHtml.getElementById("ud");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("n");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("longitud");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("anchura");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("altura");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("parcial");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("canpres");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("prpres");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("imppres");
                    elemento.text("");
                    elemento = archivoHtml.getElementById("resumen");
                    elemento.attr("width", "80%");
                }
            }
            
            filaDatos.remove();
            
            
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LineaComandos.nombreArchivoSalida), "UTF-8"));
            writer.append(archivoHtml.outerHtml());
            writer.close();
            
            trabajoRealizadoOK = true;
        } catch (IOException ex) {
            String er = "Error: " + ex.getLocalizedMessage();
            log.appendTimedLogLine(er);
            System.out.println(er);
            throw new ErrorInFormatException("Error en el análisis del archivo de plantilla.");
        }
        
        return trabajoRealizadoOK;
    }
    
    private String getHtmlFileName(String fileName) {
        int punto = fileName.lastIndexOf(".");
//        String fn = fileName.substring(0, punto);
        
        return fileName.substring(0, punto);
    }
    
    private String getTituloBBDD() {
        String s = "";
        for(Registro_C_concepto registro : rCodigos) {
            if(registro.getCodigo().contains("##")){
                s = registro.getResumen();
                break;
            }
        }
        return s;
    }
    
    private String getTextoDeCodigo(String codigo) {
        StringBuilder s = new StringBuilder();
        for (Registro_T_texto rtt : rTextos) {
            if (rtt.getCodigoConcepto().equals(codigo)) {
                s.append(rtt.getTextoDescriptivo());
                break;
            }
        }
        return s.toString();
    }
    
    private String getResumenDeCodigo(String codigo) {
        String s = "";
        for (Registro_C_concepto rcc : rCodigos){
            if (codigo.equals(rcc.getCodigo())) {
                s = rcc.getResumen();
                break;
            }
        }
        return s;
    }
    
    private double getMedicionTotalDeCodigo(String codigo) {
        double d = 0d;
        for (Registro_M_mediciones med : rMediciones) {
            if (codigo.equals(med.getCodigoPadre())) {
                d = med.getMedicionTotal();
                break;
            }
        }
        return d;
    }
    
    private double getPrecioDeCodigo(String codigo) {
        double d = 0d;
        for (Registro_C_concepto concepto : rCodigos) {
            if(codigo.equals(concepto.getCodigo())) {
                d = concepto.getPrecio();
                break;
            }
        }
        return d;
    }
    
    private String getUnidadDeCodigo (String codigo) {
        String s = "";
        for (Registro_C_concepto rcc : rCodigos){
            if (codigo.equals(rcc.getCodigo())) {
                s = rcc.getUnidad();
                break;
            }
        }
        return s;
    }
}