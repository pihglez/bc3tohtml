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

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class LineaComandos {
    /**
     * Activa la opción de test del software
     */
    public static boolean opcionTest                = false;    // /te$t
    /**
     * Activa la opción de mostrar estadísticas del software
     */
    public static boolean mostrarEstadisticas       = false;    // -s
    /**
     * Activa la opción 'verbose' del software
     */
    public static boolean modoVerbose               = false;    // -v
    /**
     * Activa la opción de utilización de plantilla externa del software
     */
    public static boolean usarPlantillaExterna      = false;    // -h
    /**
     * Activa la opción de modificar el nombre del archivo de salida
     */
    public static boolean nombrarArchivoSalida      = false;    // -o
    /**
     * Activa la opción de generar un presupuesto ciego
     */
    public static boolean salidaApresupuestoCiego   = false;    // -b
    /**
     * Activa la opción de salida únicamenente de los descompuestos del presupuesto/BBDD
     */
    public static boolean salidaSoloDescompuestos   = false;    // -d
    /**
     * Activa la opción de salida únicamente de los elementales del presupuesto/BBDD 
     */
    public static boolean salidaSoloElementales     = false;    // -e
    /**
     * Activa la salida del presupuesto con mediciones
     */
    public static boolean salidaConMediciones       = false;    // -m
    /**
     * Activa la salida del resumen de presupuesto
     */
    public static boolean incluirResumen            = false;    // -r
    /**
     * Activa la opción de mantener un log que se guarda en un archivo
     */
    public static boolean mantenerArchivoLog        = false;    // -l
    /**
     * Activa la opción de asumir como positivas todas las preguntas al usuario que pueda realizar el programa
     */
    public static boolean asumirRespuestaPositiva   = false;    // -y
    /**
     * Activa la salida del presupuesto
     */
    public static boolean incluirPresupuesto        = true;     // -p
    /**
     * Nombre de la plantilla html a utilizar (distinta de la de defecto)
     */
    public static String nombrePlantillaHtml;
    /**
     * Nombre del archivo a procesar
     */
    public static String nombreArchivoAProcesar;
    /**
     * Nombre del archivo de salida (distinto al de defecto)
     */
    public static String nombreArchivoSalida;
}