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

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3ParserLogger {

    /**
     * Variable que almacena el log
     */
    private final StringBuilder log;
    /**
     * Variable que almacena el tiempo medido inicial
     */
    private static Date         t0;
    /**
     * Variable que almacena el formato de almacenaje (String) del log
     */
    private static String formato;
    
    public Bc3ParserLogger() {
        t0  = new Date();
        log = new StringBuilder();
        formato  = "%-5s %40s%n";
        log.append(String.format(formato, t0, "Inicio del proceso de Log."));
    }
    
    /**
     * Línea a incluir en el log
     * @param lineToLog String La línea a incluir en el log
     */
    public void appendTimedLogLine(String lineToLog) {
        Date t1 = new Date();
        Timestamp ts    = new Timestamp(t1.getTime());
        log.append(String.format(formato, ts, lineToLog));
    }

    /**
     * Devuelve un StringBuilder con el log al completo
     * @return 
     */
    public StringBuilder getLog() {
        return log;
    }
}