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
package org.ph.bc3Format;

import java.util.ArrayList;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Bc3ConceptoPres {
    private String codigo, unidad, resumen, texto;
    private double canPres, prPres, impPres;
    
    private ArrayList<Bc3ConceptoPres> conceptosHijos;

    public Bc3ConceptoPres(String codigo, String unidad, String resumen, String texto, double canPres, double prPres, double impPres, ArrayList<Bc3ConceptoPres> conceptosHijos) {
        this.codigo = codigo;
        this.unidad = unidad;
        this.resumen = resumen;
        this.texto = texto;
        this.canPres = canPres;
        this.prPres = prPres;
        this.impPres = impPres;
        this.conceptosHijos = conceptosHijos;
    }

    public Bc3ConceptoPres(String codigo, String unidad, String resumen, String texto, double canPres, double prPres, double impPres) {
        this.codigo = codigo;
        this.unidad = unidad;
        this.resumen = resumen;
        this.texto = texto;
        this.canPres = canPres;
        this.prPres = prPres;
        this.impPres = impPres;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public double getCanPres() {
        return canPres;
    }

    public void setCanPres(double canPres) {
        this.canPres = canPres;
    }

    public double getPrPres() {
        return prPres;
    }

    public void setPrPres(double prPres) {
        this.prPres = prPres;
    }

    public double getImpPres() {
        return impPres;
    }

    public void setImpPres(double impPres) {
        this.impPres = impPres;
    }

    public ArrayList<Bc3ConceptoPres> getConceptosHijos() {
        return conceptosHijos;
    }

    public void setConceptosHijos(ArrayList<Bc3ConceptoPres> conceptosHijos) {
        this.conceptosHijos = conceptosHijos;
    }
    
    public void addConceptoHijo (Bc3ConceptoPres conceptoHijo) {
        conceptosHijos.add(conceptoHijo);
    }
}
