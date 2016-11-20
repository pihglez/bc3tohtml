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

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.ph.System.SerialNumber;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
@XmlRootElement(name = "Bc3ToHtmlConfig")
@XmlType(propOrder = {"SERIALVERSIONUUID", "opcionTest", "mostrarEstadisticas", 
    "modoVerbose", "usarPlantillaExterna", "nombrarArchivoSalida", 
    "salidaApresupuestoCiego", "salidaSoloDescompuestos", "salidaSoloEntidades",
    "incluirMediciones", "generarResumen", "mantenerArchivoLog", 
    "asumirRespuestaPositiva", "generarPresupuesto", "forzarCodificacionWindows",
    "abrirWeb", "nombrePlantillaHtml"})
public class Bc3ToHtmlConfig implements Serializable {
    @XmlTransient
    private static final long serialVersionUID = 1L;
    private String  SERIALVERSIONUUID        ;    // la versión del archivo de configuración
    private String  SOFTWARESERIALNUMBER     ;    // el número de serie de la versión del software
    private String  platform                 ;    // la plataforma en la que se ha instalado el software
    private boolean opcionTest               ;    // /te$t
    private boolean mostrarEstadisticas      ;    // -s
    private boolean modoVerbose              ;    // -v
    private boolean usarPlantillaExterna     ;    // -h
    private boolean nombrarArchivoSalida     ;    // -o
    private boolean salidaApresupuestoCiego  ;    // -b
    private boolean salidaSoloDescompuestos  ;    // -d
    private boolean salidaSoloEntidades      ;    // -e
    private boolean incluirMediciones        ;    // -m
    private boolean generarResumen           ;    // -r
    private boolean mantenerArchivoLog       ;    // -l
    private boolean asumirRespuestaPositiva  ;    // -y
    private boolean generarPresupuesto       ;    // -p
    private boolean forzarCodificacionWindows;    // -c
    private String  nombrePlantillaHtml      ;
    private String  nombreArchivoAProcesar   ;
    private String  nombreArchivoSalida      ;
    private boolean abrirWeb                 ;
    
    
    public Bc3ToHtmlConfig() {
        setDefaults();
    }
    
    /**
     * Valores por defecto:<br/>
     * NO se utiliza la opción de test<br/>
     * NO se muestran estadísticias<br/>
     * NO se utiliza el modo verbose<br/>
     * NO se utilizará plantilla externa<br/>
     * NO se nombra el archivo de salida de manera explícita<br/>
     * NO se generará un prespupuesto ciego<br/>
     * NO se incluirán los precios descompuestos<br/>
     * NO se generará un documento únicamente con las entidades<br/>
     * NO se incluirán las mediciones<br/>
     * NO se incluirá el resumen en el documento generado<br/>
     * NO se generará ni mantendrá un archivo .log de información de la conversión<br/>
     * NO se asumirá como positiva por defecto la respuesta a cualquier pregunta que se pueda realizar<br/>
     * SI se generará el presupuesto<br/>
     * SI se forzará la lectura del archivo de entrada en codificación de Windows
     * SI se forzará la apertura del navegador una vez finalizada la conversión<br/>
     * 
     */
    public void setDefaults() {
        SERIALVERSIONUUID           = Bc3tohtml.BC3TOHTMLVERSION;
        SOFTWARESERIALNUMBER        = SerialNumber.getSerialNumber();
        opcionTest                  = false;
        mostrarEstadisticas         = false;
        modoVerbose                 = false;
        usarPlantillaExterna        = false;
        nombrarArchivoSalida        = false;
        salidaApresupuestoCiego     = false;
        salidaSoloDescompuestos     = false;
        salidaSoloEntidades         = false;
        incluirMediciones           = false;
        generarResumen              = false;
        mantenerArchivoLog          = false;
        asumirRespuestaPositiva     = false;
        generarPresupuesto          = true;
        forzarCodificacionWindows   = true;
        abrirWeb                    = true;
    }
    
    public Bc3ToHtmlConfig(File xmlConfig) {
        SERIALVERSIONUUID           = Bc3tohtml.BC3TOHTMLVERSION;
        // ...
    }

    public boolean isOpcionTest() {
        return opcionTest;
    }

    public void setOpcionTest(boolean opcionTest) {
        this.opcionTest = opcionTest;
    }

    public boolean isMostrarEstadisticas() {
        return mostrarEstadisticas;
    }

    public void setMostrarEstadisticas(boolean mostrarEstadisticas) {
        this.mostrarEstadisticas = mostrarEstadisticas;
    }

    public boolean isModoVerbose() {
        return modoVerbose;
    }

    public void setModoVerbose(boolean modoVerbose) {
        this.modoVerbose = modoVerbose;
    }

    public boolean isUsarPlantillaExterna() {
        return usarPlantillaExterna;
    }

    public void setUsarPlantillaExterna(boolean usarPlantillaExterna) {
        this.usarPlantillaExterna = usarPlantillaExterna;
    }

    public boolean isNombrarArchivoSalida() {
        return nombrarArchivoSalida;
    }

    public void setNombrarArchivoSalida(boolean nombrarArchivoSalida) {
        this.nombrarArchivoSalida = nombrarArchivoSalida;
    }

    public boolean isSalidaApresupuestoCiego() {
        return salidaApresupuestoCiego;
    }

    public void setSalidaApresupuestoCiego(boolean salidaApresupuestoCiego) {
        this.salidaApresupuestoCiego = salidaApresupuestoCiego;
    }

    public boolean isSalidaSoloDescompuestos() {
        return salidaSoloDescompuestos;
    }

    public void setSalidaSoloDescompuestos(boolean salidaSoloDescompuestos) {
        this.salidaSoloDescompuestos = salidaSoloDescompuestos;
    }

    public boolean isSalidaSoloEntidades() {
        return salidaSoloEntidades;
    }

    public void setSalidaSoloEntidades(boolean salidaSoloEntidades) {
        this.salidaSoloEntidades = salidaSoloEntidades;
    }

    public boolean isIncluirMediciones() {
        return incluirMediciones;
    }

    public void setIncluirMediciones(boolean incluirMediciones) {
        this.incluirMediciones = incluirMediciones;
    }

    public boolean isGenerarResumen() {
        return generarResumen;
    }

    public void setGenerarResumen(boolean generarResumen) {
        this.generarResumen = generarResumen;
    }

    public boolean isMantenerArchivoLog() {
        return mantenerArchivoLog;
    }

    public void setMantenerArchivoLog(boolean mantenerArchivoLog) {
        this.mantenerArchivoLog = mantenerArchivoLog;
    }

    public boolean isAsumirRespuestaPositiva() {
        return asumirRespuestaPositiva;
    }

    public void setAsumirRespuestaPositiva(boolean asumirRespuestaPositiva) {
        this.asumirRespuestaPositiva = asumirRespuestaPositiva;
    }

    public boolean isGenerarPresupuesto() {
        return generarPresupuesto;
    }

    public void setGenerarPresupuesto(boolean generarPresupuesto) {
        this.generarPresupuesto = generarPresupuesto;
    }

    public boolean isForzarCodificacionWindows() {
        return forzarCodificacionWindows;
    }

    public void setForzarCodificacionWindows(boolean forzarCodificacionWindows) {
        this.forzarCodificacionWindows = forzarCodificacionWindows;
    }

    public String getNombrePlantillaHtml() {
        return nombrePlantillaHtml;
    }

    public void setNombrePlantillaHtml(String nombrePlantillaHtml) {
        this.nombrePlantillaHtml = nombrePlantillaHtml;
    }

    @XmlTransient
    public String getNombreArchivoAProcesar() {
        return nombreArchivoAProcesar;
    }

    public void setNombreArchivoAProcesar(String nombreArchivoAProcesar) {
        this.nombreArchivoAProcesar = nombreArchivoAProcesar;
    }

    @XmlTransient
    public String getNombreArchivoSalida() {
        return nombreArchivoSalida;
    }

    public void setNombreArchivoSalida(String nombreArchivoSalida) {
        this.nombreArchivoSalida = nombreArchivoSalida;
    }

    public boolean isAbrirWeb() {
        return abrirWeb;
    }

    public void setAbrirWeb(boolean abrirWeb) {
        this.abrirWeb = abrirWeb;
    }

    public String getSERIALVERSIONUUID() {
        return SERIALVERSIONUUID;
    }

    public void setSERIALVERSIONUUID(String SERIALVERSIONUUID) {
        this.SERIALVERSIONUUID = SERIALVERSIONUUID;
    }

    @XmlAttribute
    public String getSOFTWARESERIALNUMBER() {
        return SOFTWARESERIALNUMBER;
    }
    
    /**
     * Devuelve el listado de métodos "get..." de la clase
     * @return <code>String</code> Listado de métodos get de la clase y sus valores en
     * el momento de la consulta.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Métodos de " + this.getClass().getName() + ":\n");
        for(Method m : Bc3ToHtmlConfig.class.getMethods()) {
            try {
                if((m.getName().startsWith("get") || m.getName().startsWith("is")) && !m.getName().equals("getClass")) {
                    sb.append("     "); sb.append(m.getName()); sb.append(": ");
                    sb.append(m.invoke(this));
                    sb.append("\n");
                }
            } catch (IllegalAccessException ex) {
                System.out.println("Error de acceso a la clase: "       + ex.getLocalizedMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Argumento ilegal: "                 + ex.getLocalizedMessage());
            } catch (InvocationTargetException ex) {
                System.out.println("Error de invocación de destino: "   + ex.getLocalizedMessage());
            }
        }
        
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}