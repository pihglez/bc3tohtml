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

import java.math.BigDecimal;
import java.util.HashSet;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3ToHtmlStatistics {
    private int numeroTotalConversiones;
    private BigDecimal importePresupuestoTotal;
    private HashSet<DatosGenericosConversion> conjuntoElementosProcesados;
    
    public Bc3ToHtmlStatistics(){}

    public int getNumeroTotalConversiones() {
        return numeroTotalConversiones;
    }

    public BigDecimal getImportePresupuestoTotal() {
        return importePresupuestoTotal;
    }

    public void setNumeroTotalConversiones(int numeroTotalConversiones) {
        this.numeroTotalConversiones = numeroTotalConversiones;
    }

    public void setImportePresupuestoTotal(BigDecimal importePresupuestoTotal) {
        this.importePresupuestoTotal = importePresupuestoTotal;
    }

    public HashSet<DatosGenericosConversion> getConjuntoElementosProcesados() {
        return conjuntoElementosProcesados;
    }
    
    public void addDatosAlSet(String hashInformativo, BigDecimal importePresupuesto) {
        boolean added;
        added = conjuntoElementosProcesados.add(new DatosGenericosConversion(hashInformativo, importePresupuesto));
        
        if (added) {    // se añadido el elemento de manera correcta
            numeroTotalConversiones++;
            importePresupuestoTotal = importePresupuestoTotal.add(importePresupuesto);
        } else {        // el elemento NO se ha añadido
            if(LineaComandos.modoVerbose) System.out.println("La conversión ya se había realizado con anterioridad.");
        }
    }
    
    public double getImporteMedioCalculado() {
        return importePresupuestoTotal.divide(BigDecimal.valueOf(numeroTotalConversiones)).doubleValue();
    }
}

class DatosGenericosConversion {
    private String hashInformativo;
    private BigDecimal importePresupuesto;
    
    public DatosGenericosConversion (String hashInformativo, BigDecimal importePresupuesto) {
        this.hashInformativo = hashInformativo;
        this.importePresupuesto = importePresupuesto;
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