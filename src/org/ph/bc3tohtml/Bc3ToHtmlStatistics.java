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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.ph.xmlFormat.XMLObjectEncoderDecoder;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
@XmlRootElement(name = "Bc3ToHtmlStatistics")
@XmlType(propOrder = {"serialVersionUID", "numeroTotalConversiones", "importePresupuestoTotal",
"conjuntoElementosProcesados"})
public class Bc3ToHtmlStatistics implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigInteger numeroTotalConversiones;
    private BigDecimal importePresupuestoTotal;
    private HashSet<DatosGenericosConversion> conjuntoElementosProcesados;
    
    private final File ARCHIVOESTADISTICAS;
    
    public Bc3ToHtmlStatistics(File archivoEstadistica){
        this.ARCHIVOESTADISTICAS = archivoEstadistica;
    }

    public BigInteger getNumeroTotalConversiones() {
        return numeroTotalConversiones;
    }

    public BigDecimal getImportePresupuestoTotal() {
        return importePresupuestoTotal;
    }

    public void setNumeroTotalConversiones(BigInteger numeroTotalConversiones) {
        this.numeroTotalConversiones = numeroTotalConversiones;
    }

    public void setImportePresupuestoTotal(BigDecimal importePresupuestoTotal) {
        this.importePresupuestoTotal = importePresupuestoTotal;
    }

    public HashSet<DatosGenericosConversion> getConjuntoElementosProcesados() {
        return conjuntoElementosProcesados;
    }
    
    public void addDataToSet(String hashInformativo, BigDecimal importePresupuesto) {
        boolean added;
        added = conjuntoElementosProcesados.add(new DatosGenericosConversion(hashInformativo, importePresupuesto));
        
        if (added) {    // se añadido el elemento de manera correcta
            numeroTotalConversiones = numeroTotalConversiones.add(BigInteger.valueOf(1l));
            importePresupuestoTotal = importePresupuestoTotal.add(importePresupuesto);
        } else {        // el elemento NO se ha añadido
            if(LineaComandos.modoVerbose) System.out.println("La conversión ya se había realizado con anterioridad.");
        }
    }
    
    public void addDataToSet(String hashInformativo, BigDecimal importePresupuesto, ArrayList<Map<String,BigDecimal>> presupuesto) {
        boolean added;
        added = conjuntoElementosProcesados.add(new DatosGenericosConversion(hashInformativo, importePresupuesto, presupuesto));
        
        if (added) {    // se añadido el elemento de manera correcta
            numeroTotalConversiones = numeroTotalConversiones.add(BigInteger.valueOf(1l));
            importePresupuestoTotal = importePresupuestoTotal.add(importePresupuesto);
        } else {        // el elemento NO se ha añadido
            if(LineaComandos.modoVerbose) System.out.println("La conversión ya se había realizado con anterioridad.");
        }
    }
    
    public BigDecimal getImporteMedioCalculado() {
        return importePresupuestoTotal.divide(new BigDecimal(numeroTotalConversiones));
    }
    
    public void guardaEstadistica() {
        XMLObjectEncoderDecoder.marshal(this, ARCHIVOESTADISTICAS);
    }
}

class DatosGenericosConversion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String hashInformativo;
    private BigDecimal importePresupuesto;
    private ArrayList<Map<String,BigDecimal>> presupuesto;
    
    public DatosGenericosConversion (String hashInformativo, BigDecimal importePresupuesto) {
        this.hashInformativo = hashInformativo;
        this.importePresupuesto = importePresupuesto;
    }
    
    public DatosGenericosConversion (String hashInformativo, 
            BigDecimal importePresupuesto,
            ArrayList<Map<String,BigDecimal>> presupuesto) {
        this.hashInformativo = hashInformativo;
        this.importePresupuesto = importePresupuesto;
        this.presupuesto = presupuesto;
    }

    public String getHashInformativo() {
        return hashInformativo;
    }

    public void setHashInformativo(String hashInformativo) {
        this.hashInformativo = hashInformativo;
    }

    public BigDecimal getImportePresupuesto() {
        return importePresupuesto;
    }

    public void setImportePresupuesto(BigDecimal importePresupuesto) {
        this.importePresupuesto = importePresupuesto;
    }
}