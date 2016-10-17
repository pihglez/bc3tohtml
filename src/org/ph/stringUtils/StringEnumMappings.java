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
package org.ph.stringUtils;

import java.util.ArrayList;

/**
 *
 * @author Pedro I. HernÓndez G. <pihglez@gmail.com>
 */
public class StringEnumMappings {
//    private Map<StringChapters, String> MAPPINGS;
    private final Mapp MAPPINGS;
    private int numeroElementosIrreconocibles, numeroElementosReconocidos;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor de la clase">
    public StringEnumMappings() {
        // Mejorar: esto sería interesante cargarlo desde un diccionario
        // tipo XML o similar para tener la posibilidad de mejorar
        // fácilmente el tesauro
        MAPPINGS = new Mapp();
        MAPPINGS.add(StringChapters.ACABADO,            "ACA");
        MAPPINGS.add(StringChapters.ACERO,              "ACE");
        MAPPINGS.add(StringChapters.AISLAMIENTO,        "AIS");
        MAPPINGS.add(StringChapters.ALBAÑILERIA,        "ALB");
        MAPPINGS.add(StringChapters.ALBAÑILERÍA,        "ALB");
        MAPPINGS.add(StringChapters.ALICATADO,          "ALI");
        MAPPINGS.add(StringChapters.ALUMINIO,           "ALU");
        MAPPINGS.add(StringChapters.APARATO,            "APA");
        MAPPINGS.add(StringChapters.APLACADO,           "APL");
        MAPPINGS.add(StringChapters.BAJA,               "BAJ");
        MAPPINGS.add(StringChapters.TENSION,            "TEN");
        MAPPINGS.add(StringChapters.TENSIÓN,            "TEN");
        MAPPINGS.add(StringChapters.CARPINTERIA,        "CAR");
        MAPPINGS.add(StringChapters.CARPINTERÍA,        "CAR");
        MAPPINGS.add(StringChapters.CERRAMIENTO,        "CER");
        MAPPINGS.add(StringChapters.CERRAJERIA,         "CRJ");
        MAPPINGS.add(StringChapters.CERRAJERÍA,         "CRJ");
        MAPPINGS.add(StringChapters.CIMIENTO,           "CIM");
        MAPPINGS.add(StringChapters.CLIMATIZACION,      "CLI");
        MAPPINGS.add(StringChapters.CLIMATIZACIÓN,      "CLI");
        MAPPINGS.add(StringChapters.COMUN,              "COM");
        MAPPINGS.add(StringChapters.CONTENCION,         "CON");
        MAPPINGS.add(StringChapters.CONTENCIÓN,         "CON");
        MAPPINGS.add(StringChapters.CONTRA,             "COT");
        MAPPINGS.add(StringChapters.CUBIERTA,           "CUB");
        MAPPINGS.add(StringChapters.DEMOLICION,         "DEM");
        MAPPINGS.add(StringChapters.DEMOLICIÓN,         "DEM");
        MAPPINGS.add(StringChapters.DEPORTIVO,          "DEP");
        MAPPINGS.add(StringChapters.DERIVADO,           "DER");
        MAPPINGS.add(StringChapters.DESAGÜE,            "DES");
        MAPPINGS.add(StringChapters.ELECTRICIDAD,       "ELE");
        MAPPINGS.add(StringChapters.ELEVADOR,           "ELV");
        MAPPINGS.add(StringChapters.ENERGÍA,            "ENE");
        MAPPINGS.add(StringChapters.ENERGIA,            "ENE");
        MAPPINGS.add(StringChapters.ENSAYO,             "ENS");
        MAPPINGS.add(StringChapters.EQUIPAMIENTO,       "EQU");
        MAPPINGS.add(StringChapters.ESTRUCTURA,         "EST");
        MAPPINGS.add(StringChapters.EVACUACIÓN,         "EVA");
        MAPPINGS.add(StringChapters.EVACUACION,         "EVA");
        MAPPINGS.add(StringChapters.FACHADA,            "FAC");
        MAPPINGS.add(StringChapters.FALSO,              "FAL");
        MAPPINGS.add(StringChapters.FONTANERÍA,         "FON");
        MAPPINGS.add(StringChapters.FONTANERIA,         "FON");
        MAPPINGS.add(StringChapters.GAS,                "GAS");
        MAPPINGS.add(StringChapters.GESTION,            "GES");
        MAPPINGS.add(StringChapters.GESTIÓN,            "GES");
        MAPPINGS.add(StringChapters.GRIFERIA,           "GRI");
        MAPPINGS.add(StringChapters.GRIFERÍA,           "GRI");
        MAPPINGS.add(StringChapters.HORMIGON,           "HOR");
        MAPPINGS.add(StringChapters.HORMIGÓN,           "HOR");
        MAPPINGS.add(StringChapters.HUMO,               "HUM");
        MAPPINGS.add(StringChapters.IMPERMEABILIZACION, "IMP");
        MAPPINGS.add(StringChapters.IMPERMEABILIZACIÓN, "IMP");
        MAPPINGS.add(StringChapters.INCENDIO,           "INC");
        MAPPINGS.add(StringChapters.INSTALACION,        "INS");
        MAPPINGS.add(StringChapters.INSTALACIÓN,        "INS");
        MAPPINGS.add(StringChapters.MADERA,             "MAD");
        MAPPINGS.add(StringChapters.MARITIMO,           "MAR");
        MAPPINGS.add(StringChapters.MARÍTIMO,           "MAR");
        MAPPINGS.add(StringChapters.METALICA,           "MET");
        MAPPINGS.add(StringChapters.METÁLICA,           "MET");
        MAPPINGS.add(StringChapters.MOVIMIENTO,         "MOV");
        MAPPINGS.add(StringChapters.PAVIMENTO,          "PAV");
        MAPPINGS.add(StringChapters.PELDAÑO,            "PEL");
        MAPPINGS.add(StringChapters.PETROLEO,           "PET");
        MAPPINGS.add(StringChapters.PETRÓLEO,           "PET");
        MAPPINGS.add(StringChapters.PINTURA,            "PIN");
        MAPPINGS.add(StringChapters.PROTECCION,         "PRO");
        MAPPINGS.add(StringChapters.PROTECCIÓN,         "PRO");
        MAPPINGS.add(StringChapters.PVC,                "PVC");
        MAPPINGS.add(StringChapters.RAYO,               "RAY");
        MAPPINGS.add(StringChapters.REHABILITACION,     "REH");
        MAPPINGS.add(StringChapters.REHABILITACIÓN,     "REH");
        MAPPINGS.add(StringChapters.RESIDUO,            "RES");
        MAPPINGS.add(StringChapters.REVESTIMIENTO,      "REV");
        MAPPINGS.add(StringChapters.SALUD,              "SAL");
        MAPPINGS.add(StringChapters.SANEAMIENTO,        "SAN");
        MAPPINGS.add(StringChapters.SANITARIO,          "SNT");
        MAPPINGS.add(StringChapters.SEGURIDAD,          "SEG");
        MAPPINGS.add(StringChapters.SOLAR,              "SOL");
        MAPPINGS.add(StringChapters.TECHO,              "TEC");
        MAPPINGS.add(StringChapters.TELECOMUNICACION,   "TEL");
        MAPPINGS.add(StringChapters.TIERRA,             "TIE");
        MAPPINGS.add(StringChapters.URBANIZACION,       "URB");
        MAPPINGS.add(StringChapters.URBANIZACIÓN,       "URB");
        MAPPINGS.add(StringChapters.URBANO,             "UBN");
        MAPPINGS.add(StringChapters.VENTILACION,        "VEN");
        MAPPINGS.add(StringChapters.VENTILACIÓN,        "VEN");
        MAPPINGS.add(StringChapters.VIDRIO,             "VID");
        MAPPINGS.add(StringChapters.ZONA,               "ZON");
        MAPPINGS.add(StringChapters.PLAN,               "PLA");
        MAPPINGS.add(StringChapters.CONTROL,            "CON");
        MAPPINGS.add(StringChapters.CALIDAD,            "CAL");
        MAPPINGS.add(StringChapters.VARIOS,             "VAR");
        
        numeroElementosIrreconocibles   = 0;
        numeroElementosReconocidos      = 0;
    }
    //</editor-fold>
    
    public String getResumedChapter(String s) {
        String toReturn;

        ArrayList<String> als = new ArrayList<String>();
        
        // se da por supuesto que las palabras se dividen por espacios
        // esto quizá podría mejorarse porque en ocasiones, la división se puede
        // realizar mediante puntos, guiones, etc.
        String[] splitted = s.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            for (int j = 0; j < MAPPINGS.size(); j++) {
                if(splitted[i].toUpperCase().contains(MAPPINGS.getSCElement(j).name())){
                    als.add(MAPPINGS.getStringElement(j));
                    numeroElementosReconocidos++;
                }
            }
        }
        
        //<editor-fold defaultstate="collapsed" desc="switch de gestión de montaje de acrónimo">
        switch (als.size()) {
            case 0:
                numeroElementosIrreconocibles++;
                toReturn = "IND";
                break;
            case 1:
                toReturn = als.toArray()[0].toString();
                break;
            case 2:
                toReturn = als.toArray()[0].toString().substring(0, 1) +
                        als.toArray()[1].toString().substring(0, 1) +
                        als.toArray()[1].toString().substring(1, 2).toLowerCase();
                break;
            case 3:
                toReturn = als.toArray()[0].toString().substring(0, 1) +
                        als.toArray()[1].toString().substring(0, 1) +
                        als.toArray()[2].toString().substring(0, 1);
                
                break;
            default:
//                toReturn = "IN" + als.size();
                toReturn = als.toArray()[0].toString().substring(0, 1) +
                        als.toArray()[1].toString().substring(0, 1) +
                        "+";
                break;
        }
        //</editor-fold>
        
        return toReturn;
    }
    
    public int getNumeroElementosIrreconocibles() {
        return numeroElementosIrreconocibles;
    }
    
    public int getNumeroElementosReconocidos() {
        return numeroElementosReconocidos;
    }
    
    private class enumValor{
        // En realidad, es una pareja del tipo Map<StringChapters, String>
        // que se gestiona mediante ArrayList para mantenerlos ordenados
        private final StringChapters    SC;
        private final String            S;
        
        public enumValor(StringChapters sc, String s){
            this.SC = sc;
            this.S  = s;
        }
        
        public StringChapters getStringChapter() {
            return SC;
        }
        
        public String getString() {
            return S;
        }
    }
    
    private class Mapp{
//        private final ArrayList<StringChapters>   sc;
//        private final ArrayList<String>           s;
        private final ArrayList<enumValor> claves;
        
        public Mapp() {
//            sc  = new ArrayList<StringChapters>();
//            s   = new ArrayList<String>();
            claves = new ArrayList<enumValor>();
        }
        
        public void add(StringChapters sc, String s) {
            claves.add(new enumValor(sc,s));
        }
        
        public StringChapters getSCElement(int index) {
            
            return claves.get(index).getStringChapter();
        }
        
        public String getStringElement(int index) {
            return claves.get(index).getString();
        }
        
        public int size() {
            return claves.size();
        }
    }
}