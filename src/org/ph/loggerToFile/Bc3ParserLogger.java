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
package org.ph.loggerToFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3ParserLogger {
    private BufferedWriter      bw;
    
    /**
     * Variable que almacena el log
     */
    private final StringBuilder log;
    
    /**
     * Número máximo de líneas a guardar en el archivo de manera simultánea
     */
    private int                 maxLines;
    
    /**
     * Número actual de líneas almacenadas
     */
    private int                 actualLines;
    
    /**
     * Variable que almacena el tiempo medido inicial
     */
    private Date                t0;
    
    /**
     * Variable que almacena el formato de almacenaje (String) del log
     */
    private String              formato;
    
    /**
     * Constructor de la clase en el que se establece el t0, el <code>StringBuilder</code>
     * que almacena el log, el formato de almacenamiento del <code>Timestamp</code>,
     * el número máximo de líneas por defecto a guardar en el archivo y el número
     * actual de líneas almacenadas en el <code>StringBuilder</code> del log.
     */
    public Bc3ParserLogger() {
        t0  = new Date();
        log = new StringBuilder();
//        formato  = "%-5s %70s%n";
        formato = "%1$td/%1$tb/%1$tY\t%1$tH:%1$tM:%1$tS.%1$tL\t%2$s\n";
//        log.append(String.format(formato, t0, "Inicio del proceso de Log."));
        maxLines = 5;
        actualLines = 0;
    }
    
    /**
     * Línea a incluir en el log
     * @param lineToLog String La línea a incluir en el log
     */
    public void appendTimedLogLine(String lineToLog) {
        Date t1 = new Date();
        Timestamp ts    = new Timestamp(t1.getTime());
        log.append(String.format(formato, ts, lineToLog));
        actualLines++;
        if ((actualLines == maxLines) && (bw != null)) {
            writeLog();
        }
    }

    /**
     * Devuelve un StringBuilder con el log al completo
     * @return 
     */
    public StringBuilder getLog() {
        return log;
    }
    
    /**
     * Establece el archivo en el que se vuelca el log
     * @param file 
     */
    public void setLogFile(String file) {
        try {
            bw = new BufferedWriter(new FileWriter(new File(file)));
        } catch (IOException ex) {
            System.out.println("Error en la apertura del archivo para escritura.");
        }
    }
    
    /**
     * Escribe al archivo especificado el log y resetea las variables para mejorar
     * el comportamiento en memoria de la aplicación
     */
    private void writeLog() {
        try {
            bw.append(log.toString());
            log.setLength(0);
            actualLines = 0;
        } catch (IOException ex) {
            System.out.println("Error en la escritura del log... " + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Método que permite cerrar el archivo abierto para introducir la información
     * de log.
     */
    public void closeFile() {
    try {
        bw.close();
    } catch (IOException ex) {
        System.out.println("Error en el cierre del archivo de log ");
    }
    }
    
    /**
     * Devuelve el número máximo simultáneo de líneas a guardar en
     * el archivo log
     * @return <code>int</code> Número máximo de líneas a guardar
     */
    public int getMaxLinesToWrite() {
        return this.maxLines;
    }
    
    /**
     * Método que permite establecer el número máximo de líneas que se guardarán
     * en el archivo log de manera simultánea
     * @param maxLines <code>int</code> Número máximo de líneas a guardar
     */
    public void setMaxLinesToWrite(int maxLines) {
        this.maxLines = maxLines;
    }
}