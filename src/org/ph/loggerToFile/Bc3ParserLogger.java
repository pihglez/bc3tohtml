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
    /**
     * El mecanismo que permite la escritura en el archivo
     */
    private BufferedWriter      bw;
    
    /**
     * Variable que almacena el log
     */
    private final StringBuilder log;
    
    /**
     * La variable que almacena el número máximo de líneas a escribir simultáneamente
     */
    private int maxLines, 
            /**
             * Variable que almacena el número de líneas almacenadas actualmente en memoria
             */
            actualLines;
    
    /**
     * Variable que almacena el tiempo medido inicial
     */
    private Date                t0;
    
    /**
     * Variable que almacena el formato de almacenaje (String) del log
     */
    private String              formato;
    
    /**
     * Método que establece los valores por defecto de las variables utilizadas:<br/>
     * <code>formato</code>: El formato en el que se guarda la fecha y hora en el archivo log<br/>
     * <code>maxLines</code>: El número máximo de líneas que se guardan a la vez (5)<br/>
     * <code>actualLines</code>: El número actual de líneas almacenadas antes de proceder a su guardad (0)<br/>
     */
    private void setDefaults(){
        formato = "%1$td/%1$tb/%1$tY\t%1$tH:%1$tM:%1$tS.%1$tL\t%2$s\n";
        maxLines = 5;
        actualLines = 0;
    }
    
    /**
     * Constructor por defecto
     */
    public Bc3ParserLogger() {
        t0  = new Date();
        log = new StringBuilder();
        setDefaults();
    }
    
    /**
     * Constructor en el que se adoptan los valores por defecto y se introduce
     * directamente el nombre del archivo log
     * @param logFile 
     */
    public Bc3ParserLogger(String logFile) {
        t0  = new Date();
        log = new StringBuilder();
        setDefaults();
        this.setLogFile(logFile);
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
            bw = new BufferedWriter(new FileWriter(new File(file), true)); // true indicates appending instead of a whole new file
        } catch (IOException ex) {
            System.out.println("Error en la apertura del archivo para escritura.");
        }
    }
    
    private void writeLog() {
        try {
            bw.append(log.toString());
            log.setLength(0);
            actualLines = 0;
        } catch (IOException ex) {
            System.out.println("Error en la escritura del log... " + ex.getLocalizedMessage());
        }
    }
    
    public void closeFile() throws IOException {
        if (actualLines > 0) writeLog();
        bw.close();
    }
    
    public void setMaxLinesToWrite(int maxLines) {
        this.maxLines = maxLines;
    }
}