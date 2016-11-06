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
package org.ph.stringUtils;

import java.util.ArrayList;

/**
 *
 * @author Pedro I. Hern�ndez G. <pihglez@gmail.com>
 */
public class StringEnumMappings {
//    private Map<StringChapters, String> MAPPINGS;
    private final Mapp MAPPINGS;
    private int numeroElementosIrreconocibles, numeroElementosReconocidos;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor de la clase">
    public StringEnumMappings() {
        // Mejorar: esto ser�a interesante cargarlo desde un diccionario
        // tipo XML o similar para tener la posibilidad de mejorar
        // f�cilmente el tesauro
        MAPPINGS = new Mapp();
        MAPPINGS.add(StringChapters.ACABADO,            "ACA");
        MAPPINGS.add(StringChapters.ACERO,              "ACE");
        MAPPINGS.add(StringChapters.AISLAMIENTO,        "AIS");
        MAPPINGS.add(StringChapters.ALBA�ILERIA,        "ALB");
        MAPPINGS.add(StringChapters.ALBA�ILER�A,        "ALB");
        MAPPINGS.add(StringChapters.ALICATADO,          "ALI");
        MAPPINGS.add(StringChapters.ALUMINIO,           "ALU");
        MAPPINGS.add(StringChapters.APARATO,            "APA");
        MAPPINGS.add(StringChapters.APLACADO,           "APL");
        MAPPINGS.add(StringChapters.BAJA,               "BAJ");
        MAPPINGS.add(StringChapters.TENSION,            "TEN");
        MAPPINGS.add(StringChapters.TENSI�N,            "TEN");
        MAPPINGS.add(StringChapters.CARPINTERIA,        "CAR");
        MAPPINGS.add(StringChapters.CARPINTER�A,        "CAR");
        MAPPINGS.add(StringChapters.CERRAMIENTO,        "CER");
        MAPPINGS.add(StringChapters.CERRAJERIA,         "CRJ");
        MAPPINGS.add(StringChapters.CERRAJER�A,         "CRJ");
        MAPPINGS.add(StringChapters.CIMIENTO,           "CIM");
        MAPPINGS.add(StringChapters.CIMENTACION,        "CIM");
        MAPPINGS.add(StringChapters.CLIMATIZACION,      "CLI");
        MAPPINGS.add(StringChapters.CLIMATIZACI�N,      "CLI");
        MAPPINGS.add(StringChapters.COLECTIVA,          "COL");
        MAPPINGS.add(StringChapters.COMUN,              "COM");
        MAPPINGS.add(StringChapters.CONTENCION,         "CON");
        MAPPINGS.add(StringChapters.CONTENCI�N,         "CON");
        MAPPINGS.add(StringChapters.CONTRA,             "COT");
        MAPPINGS.add(StringChapters.CONTROL,            "CON");
        MAPPINGS.add(StringChapters.CUBIERTA,           "CUB");
        MAPPINGS.add(StringChapters.DEMOLICION,         "DEM");
        MAPPINGS.add(StringChapters.DEMOLICI�N,         "DEM");
        MAPPINGS.add(StringChapters.DEPORTIVO,          "DEP");
        MAPPINGS.add(StringChapters.DERIVADO,           "DER");
        MAPPINGS.add(StringChapters.DESAG�E,            "DES");
        MAPPINGS.add(StringChapters.ELECTRICIDAD,       "ELE");
        MAPPINGS.add(StringChapters.ELEVADOR,           "ELV");
        MAPPINGS.add(StringChapters.ENERG�A,            "ENE");
        MAPPINGS.add(StringChapters.ENERGIA,            "ENE");
        MAPPINGS.add(StringChapters.ENSAYO,             "ENS");
        MAPPINGS.add(StringChapters.EQUIPAMIENTO,       "EQU");
        MAPPINGS.add(StringChapters.ESTRUCTURA,         "EST");
        MAPPINGS.add(StringChapters.EVACUACI�N,         "EVA");
        MAPPINGS.add(StringChapters.EVACUACION,         "EVA");
        MAPPINGS.add(StringChapters.FACHADA,            "FAC");
        MAPPINGS.add(StringChapters.FALSO,              "FAL");
        MAPPINGS.add(StringChapters.FONTANER�A,         "FON");
        MAPPINGS.add(StringChapters.FONTANERIA,         "FON");
        MAPPINGS.add(StringChapters.GAS,                "GAS");
        MAPPINGS.add(StringChapters.GESTION,            "GES");
        MAPPINGS.add(StringChapters.GESTI�N,            "GES");
        MAPPINGS.add(StringChapters.GRIFERIA,           "GRI");
        MAPPINGS.add(StringChapters.GRIFER�A,           "GRI");
        MAPPINGS.add(StringChapters.HORMIGON,           "HOR");
        MAPPINGS.add(StringChapters.HORMIG�N,           "HOR");
        MAPPINGS.add(StringChapters.HUMO,               "HUM");
        MAPPINGS.add(StringChapters.IMPERMEABILIZACION, "IMP");
        MAPPINGS.add(StringChapters.IMPERMEABILIZACI�N, "IMP");
        MAPPINGS.add(StringChapters.INCENDIO,           "INC");
        MAPPINGS.add(StringChapters.INFRAESTRUCTURA,    "INF");
        MAPPINGS.add(StringChapters.INSTALACION,        "INS");
        MAPPINGS.add(StringChapters.INSTALACI�N,        "INS");
        MAPPINGS.add(StringChapters.MADERA,             "MAD");
        MAPPINGS.add(StringChapters.MARITIMO,           "MAR");
        MAPPINGS.add(StringChapters.MAR�TIMO,           "MAR");
        MAPPINGS.add(StringChapters.METALICA,           "MET");
        MAPPINGS.add(StringChapters.MET�LICA,           "MET");
        MAPPINGS.add(StringChapters.MOVIMIENTO,         "MOV");
        MAPPINGS.add(StringChapters.PAVIMENTO,          "PAV");
        MAPPINGS.add(StringChapters.PELDA�O,            "PEL");
        MAPPINGS.add(StringChapters.PETROLEO,           "PET");
        MAPPINGS.add(StringChapters.PETR�LEO,           "PET");
        MAPPINGS.add(StringChapters.PINTURA,            "PIN");
        MAPPINGS.add(StringChapters.PROTECCION,         "PRO");
        MAPPINGS.add(StringChapters.PROTECCI�N,         "PRO");
        MAPPINGS.add(StringChapters.PVC,                "PVC");
        MAPPINGS.add(StringChapters.RAYO,               "RAY");
        MAPPINGS.add(StringChapters.REHABILITACION,     "REH");
        MAPPINGS.add(StringChapters.REHABILITACI�N,     "REH");
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
        MAPPINGS.add(StringChapters.URBANIZACI�N,       "URB");
        MAPPINGS.add(StringChapters.URBANO,             "UBN");
        MAPPINGS.add(StringChapters.VENTILACION,        "VEN");
        MAPPINGS.add(StringChapters.VENTILACI�N,        "VEN");
        MAPPINGS.add(StringChapters.VIDRIO,             "VID");
        MAPPINGS.add(StringChapters.ZONA,               "ZON");
        MAPPINGS.add(StringChapters.PLAN,               "PLA");
        MAPPINGS.add(StringChapters.CALIDAD,            "CAL");
        MAPPINGS.add(StringChapters.VARIOS,             "VAR");
        MAPPINGS.add(StringChapters.PRESURIZACION,      "PRE");
        MAPPINGS.add(StringChapters.PRESURIZACI�N,      "PRE");
        MAPPINGS.add(StringChapters.VESTIBULO,          "VES");
        MAPPINGS.add(StringChapters.VEST�BULO,          "VES");
        MAPPINGS.add(StringChapters.EXTRACCION,         "EXT");
        MAPPINGS.add(StringChapters.EXTRACCI�N,         "EXT");
        MAPPINGS.add(StringChapters.CAMPANA,            "CAM");
        MAPPINGS.add(StringChapters.CUADRO,             "CUA");
        MAPPINGS.add(StringChapters.LINEA,              "LIN");
        MAPPINGS.add(StringChapters.L�NEA,              "LIN");
        MAPPINGS.add(StringChapters.GENERAL,            "GEN");
        MAPPINGS.add(StringChapters.RED,                "RED");
        MAPPINGS.add(StringChapters.HIDRAULICA,         "HID");
        MAPPINGS.add(StringChapters.EXTINCION,          "XTN");
        MAPPINGS.add(StringChapters.EXTINCI�N,          "XTN");
        MAPPINGS.add(StringChapters.DETECCION,          "DET");
        MAPPINGS.add(StringChapters.DETECCI�N,          "DET");
        MAPPINGS.add(StringChapters.ALARMA,             "ALA");
        MAPPINGS.add(StringChapters.ACCESO,             "ACC");
        MAPPINGS.add(StringChapters.SERVICIO,           "SER");
        MAPPINGS.add(StringChapters.RADIO,              "RAD");
        MAPPINGS.add(StringChapters.TELEVISION,         "TV" );
        MAPPINGS.add(StringChapters.TELEVISI�N,         "TV" );
        MAPPINGS.add(StringChapters.TELEFONO,           "TLF");
        MAPPINGS.add(StringChapters.TEL�FONO,           "TLF");
        MAPPINGS.add(StringChapters.PORTERO,            "POR");
        MAPPINGS.add(StringChapters.DIGITAL,            "DIG");
        MAPPINGS.add(StringChapters.SE�AL,              "SE�");
        MAPPINGS.add(StringChapters.PROVISIONAL,        "PRV");
        MAPPINGS.add(StringChapters.MANO,               "MAN");
        MAPPINGS.add(StringChapters.OBRA,               "OBR");
        MAPPINGS.add(StringChapters.GARAJE,             "GAR");
        MAPPINGS.add(StringChapters.MEDIDA,             "MED");
        MAPPINGS.add(StringChapters.PROTECCION,         "PRT");
        MAPPINGS.add(StringChapters.PROTECCI�N,         "PRT");
        MAPPINGS.add(StringChapters.INDIVIDUAL,         "INL");
        MAPPINGS.add(StringChapters.TERMICA,            "TRM");
        MAPPINGS.add(StringChapters.T�RMICA,            "TRM");
        
        numeroElementosIrreconocibles   = 0;
        numeroElementosReconocidos      = 0;
    }
    //</editor-fold>
    
    /**
     * M�todo que a partir de un texto de cap�tulo, devuelve un c�digo de 3 letras
     * que simboliza, en funci�n de un tesauro, el acr�nimo (o las tres primeras
     * letras) del cap�tulo
     * @param s <code>String</code> El texto largo descriptivo del cap�tulo
     * @return <code>String</code> El c�digo de 3 d�gitos resumen del texto descriptivo
     * del cap�tulo
     */
    public String getResumedChapter(String s) {
        String toReturn;

        ArrayList<String> als = new ArrayList<String>();
        
        // se da por supuesto que las palabras se dividen por espacios
        // esto quiz� podr�a mejorarse porque en ocasiones, la divisi�n se puede
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
        
        //<editor-fold defaultstate="collapsed" desc="switch de gesti�n de montaje de acr�nimos">
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
    
    /**
     * M�todo que devuelve el n�mero de elementos no reconocibles seg�n el tesauro
     * @return <code>int</code> El n�mero de elementos no reconocibles
     */
    public int getNumeroElementosIrreconocibles() {
        return numeroElementosIrreconocibles;
    }
    
    /**
     * M�todo que devuelve el n�mero de elementos reconocibles seg�n el tesauro
     * @return <code>int</code> El n�mero de elementos reconocibles
     */
    public int getNumeroElementosReconocidos() {
        return numeroElementosReconocidos;
    }
    
    /**
     * Una dupla de texto completo y su valor reducido a tres cifras
     */
    private class enumValor{
        // En realidad, es una pareja del tipo Map<StringChapters, String>
        // que se gestiona mediante ArrayList para mantenerlos ordenados
        /**
         * <code>StringChapters</code> asociado al tesauro
         */
        private final StringChapters    SC;
        /**
         * <code>String</code> asociado al <code>Enum</code> definido por <code>SC</code>
         */
        private final String            S;
        
        /**
         * Constructor
         * @param sc <code></code>  El c�digo seg�n la <code>Enum</code> tesauro <code>StringChapters</code>
         * @param s <code></code>   El resumen de tres d�gitos asociado a <code>sc</code>
         */
        public enumValor(StringChapters sc, String s){
            this.SC = sc;
            this.S  = s;
        }
        
        /**
         * M�todo que devuelve el <code>StringChapters</code> origen
         * @return <code>StringChapter</code> de origen
         */
        public StringChapters getStringChapter() {
            return    SC;
        }
        
        /**
         * M�todo que devuelve el <code>String</code> asociado al <code>StringChapters</code>
         * @return <code>String</code> Cadena de 3 d�gitos resumen del correspondiente <code>StringChapters</code>
         */
        public String getString() {
            return    S;
        }
    }
    
    /**
     * Un ArrayList de <code>enumValor</code> que contiene el conjunto ordenado 
     * de los datos del tesauro
     */
    private class Mapp{
//        private final ArrayList<StringChapters>   sc;
//        private final ArrayList<String>           s;
        private final ArrayList<enumValor> claves;
        
        /**
         * Constructor
         */
        public Mapp() {
//            sc  = new ArrayList<StringChapters>();
//            s   = new ArrayList<String>();
            claves  = new ArrayList<enumValor>();
        }
        
        /**
         * M�todo que permite a�adir un elemento al mapa de claves entre tesauro
         * de <code>Enum</code> y clave de tres d�gitos <code>String</code>
         * @param sc <code>StringChapters</code> <code>Enum</code> del tesauro
         * @param s <code>String</code> C�digo de tres d�gitos correspondiente a la codificaci�n
         * del resumen del tesauro
         */
        public void add(StringChapters sc, String s) {
            claves.add(new enumValor(sc,s));
        }
        
        /**
         * M�todo que devuelve <code>StringChapters</code> asociado a un determnado �ndice
         * @param index <code>int</code> el �ndice del elemento que se busca
         * @return <code>StringChapters</code> asociado a un determnado �ndice
         */
        public StringChapters getSCElement(int index) {
            return claves.get(index).getStringChapter();
        }
        
        /**
         * M�todo que devuelve <code>String</code> asociado a un determnado �ndice
         * @param index <code>int</code> el �ndice del elemento que se busca
         * @return <code>String</code> asociado a un determnado �ndice
         */
        public String getStringElement(int index) {
            return claves.get(index).getString();
        }
        
        /**
         * M�todo que devuelve el tama�o del mapa de claves <code>StringChapters</code>-<code>String</code>
         * @return <code>int</code> El tama�o del almac�n de claves
         */
        public int size() {
            return claves.size();
        }
    }
}