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

import org.ph.stringUtils.StrUtils;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Registro_K_coeficientes {
    private int DN;             // Decimales del campo número de partes iguales (2)
    private int DD;             // Decimales de dimensiones de las tres magnitudes de la hoja de mediciones (2)
    private int DS;             // Decimales de la línea de subtotal o total de mediciones (2)
    private int DR;             // Decimales de rendimiento y factor en una desomposición (3)
    private int DI;             // Decimales del importe resultante de multiplicar rendimiento x precio del concepto (2)
    private int DP;             // Decimales del importe resultante del sumatorio de los costes directos del concepto (2)
    private int DC;             // Decimales del importe total del concepto [CD + DI] (2)
    private int DM;             // D. del importe resultante de multiplicar medición total del concepto por su precio (2)
    private String DIVISA;      // Divisa
    
    private int CI;             // Costes indirectos expresados en porcentaje
    private int GG;             // Gastos generales de la empresa expresados en pocentaje
    private int BI;             // Beneficio industrial del contratista, expresado en porcentaje
    private int BAJA;           // Coeficiente de baja o alza de un presupuesto de adjudicación, expresado en porcentaje
    private int IVA;            // Impuesto del Valor Añadido, expresado en porcentaje
    private int DRC;            // Decimales del rendimiento y del factor de rendimiento de un presupuesto (3)
    
    private int DCP;            // D. del importe de un presupuesto (2)
    private int DFS;            // D. de los factores de rendimiento de las uds. de obra y de los elementos compuestos (3)
    private int DRS;            // D. de los rendimientos de las unidades de obra y de los elementos compuestos
                                // y del resultado de la multimplicación de los rendimientos por sus respectivos factores (3)
    private int DUO;            // D. del coste total de las unidades de obra (2)
    private int DII;            // D. de los sumatorios sobre los que se aplican los porcentajes (2)
    private int DES;            // D. del importe de los elementos simples (2)
    private int DNM;            // D. del campo número de pares iguales de la hoja de mediciones (2)
    private int DDM;            // D. de dimensiones de las tres magnitudes de la hoja de mediciones (2)
    private int DSM;            // D. del total de mediciones (2)
    private int DSP;            // D. de la línea de subtotal de mediciones (2)
    private int DEC;            // D. del importe de los elementos compuestos (2)
    private String DIVISA2;     // Divisa 2
    
    public Registro_K_coeficientes(String[] coeficientes) {
        if (coeficientes.length == 0) {
            setTodosCoeficientesDefecto();
        } else {
//            try {
                if ((coeficientes.length > 1) /* && (checkBC3Format(coeficientes[1])) */) {
//                    String[] k = coeficientes[1].split(ConstantesTexto.divisor);
                    String[] k = StrUtils.split(coeficientes[1], '\\');
                    DIVISA  = k[k.length - 1];
                    DM      = Integer.parseInt(k[k.length - 2]);
                    DC      = Integer.parseInt(k[k.length - 3]);
                    DP      = Integer.parseInt(k[k.length - 4]);
                    DI      = Integer.parseInt(k[k.length - 5]);
                    DR      = Integer.parseInt(k[k.length - 6]);
                    DS      = Integer.parseInt(k[k.length - 7]);
                    DD      = Integer.parseInt(k[k.length - 8]);
                    
                    if (!(k.length - 9 < 0)) {
                        String aka = k[k.length - 9];
                        DN = Integer.parseInt((aka.length() == 0 ? "0" : aka));
                    } else { // Formato FIEBDC-3/2002
                        DN = 0;
                    } 
                    
                }
//            } /* catch (MalformedKBC3Exception ex) { */
//                System.out.println(ex.getMessage());
//                System.out.println("Asignando coeficientes por defecto...");
//                setCoeficientesDefecto();
//            }
            setCoeficienesDefectoFaltantes();
        }
        
    }
    
    private boolean checkBC3Format (String lineaCoeficientes) throws MalformedKBC3Exception {
        if (!lineaCoeficientes.contains(ConstantesTexto.divisor)) throw new MalformedKBC3Exception(""
                + "Error de formato de coeficientes en el archivo BC3.");
        return true;
    }
    
    /**
     * Establece los valores por defecto de los coeficientes (salvo la divisa, que no tiene especificación y se asume "EUR"
     */
    private void setTodosCoeficientesDefecto() {
        DN = 2; DD = 2; DS = 2; DR = 3; DI = 2; DP = 2; DC = 2; DM = 2;
        DIVISA = "EUR";
        
        CI = 0; GG = 0; BI = 0; BAJA = 0; IVA = 21; DRC = 3;
        
        DCP = 2; DFS = 3; DRS = 3; DUO = 2; DII = 2; DES = 2; DNM = 2;
        DDM = 2; DSM = 2; DSP = 2; DEC = 2; DIVISA2 = "";
    }
    
    private void setCoeficienesDefectoFaltantes() {
        if (DN == 0)      DN=2;
        if (DD == 0)      DD=2;
        if (DS == 0)      DS=2;
        if (DR == 0)      DR=3;
        if (DI == 0)      DI=2;
        if (DP == 0)      DP=2;
        if (DC == 0)      DC=2;
        if (DM == 0)      DM=2;
        if (DIVISA == "") DIVISA = "EUR";

//        if (CI == 0)      CI=0;
//        if (GG == 0)      GG=0;
//        if (BI == 0)      BI=0;
//        if (BAJA == 0)    BAJA=0;
        if (IVA == 0)     IVA=21;
        if (DRC == 0)     DRC=3;

        if (DCP == 0)     DCP=2;
        if (DFS == 0)     DFS=3;
        if (DRS == 0)     DRS=3;
        if (DUO == 0)     DUO=2;
        if (DII == 0)     DII=2;
        if (DES == 0)     DES=2;
        if (DNM == 0)     DNM=2;
        if (DDM == 0)     DDM=2;
        if (DSM == 0)     DSM=2;
        if (DSP == 0)     DSP=2;
        if (DEC == 0)     DEC=2;
//        DIVISA2 = "";
    }

    public int getDN() {
        return DN;
    }

    public int getDD() {
        return DD;
    }

    public int getDS() {
        return DS;
    }

    public int getDR() {
        return DR;
    }

    public int getDI() {
        return DI;
    }

    public int getDP() {
        return DP;
    }

    public int getDC() {
        return DC;
    }

    public int getDM() {
        return DM;
    }

    public String getDIVISA() {
        return DIVISA;
    }

    public int getCI() {
        return CI;
    }

    public int getGG() {
        return GG;
    }

    public int getBI() {
        return BI;
    }

    public int getBAJA() {
        return BAJA;
    }

    public int getIVA() {
        return IVA;
    }

    public int getDRC() {
        return DRC;
    }

    public int getDCP() {
        return DCP;
    }

    public int getDFS() {
        return DFS;
    }

    public int getDRS() {
        return DRS;
    }

    public int getDUO() {
        return DUO;
    }

    public int getDII() {
        return DII;
    }

    public int getDES() {
        return DES;
    }

    public int getDNM() {
        return DNM;
    }

    public int getDDM() {
        return DDM;
    }

    public int getDSM() {
        return DSM;
    }

    public int getDSP() {
        return DSP;
    }

    public int getDEC() {
        return DEC;
    }

    public String getDIVISA2() {
        return DIVISA2;
    }
}
