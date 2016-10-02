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
package org.ph.loggerToFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class Bc3ParserLogger {
    private BufferedWriter      bw;
    
    /**
     * Variable que almacena el log
     */
    private final StringBuilder log;
    
    /**
     * N�mero m�ximo de l�neas a guardar en el archivo de manera simult�nea
     */
    private int                 maxLines;
    
    /**
     * N�mero actual de l�neas almacenadas
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
     * el n�mero m�ximo de l�neas por defecto a guardar en el archivo y el n�mero
     * actual de l�neas almacenadas en el <code>StringBuilder</code> del log.
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
     * L�nea a incluir en el log
     * @param lineToLog String La l�nea a incluir en el log
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
     * el comportamiento en memoria de la aplicaci�n
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
     * M�todo que permite cerrar el archivo abierto para introducir la informaci�n
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
     * Devuelve el n�mero m�ximo simult�neo de l�neas a guardar en
     * el archivo log
     * @return <code>int</code> N�mero m�ximo de l�neas a guardar
     */
    public int getMaxLinesToWrite() {
        return this.maxLines;
    }
    
    /**
     * M�todo que permite establecer el n�mero m�ximo de l�neas que se guardar�n
     * en el archivo log de manera simult�nea
     * @param maxLines <code>int</code> N�mero m�ximo de l�neas a guardar
     */
    public void setMaxLinesToWrite(int maxLines) {
        this.maxLines = maxLines;
    }
}