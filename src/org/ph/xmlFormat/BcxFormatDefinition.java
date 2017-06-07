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
package org.ph.xmlFormat;

import java.io.File;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.ph.System.RandomGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class BcxFormatDefinition {
    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    Document doc;
    
    /**
     * Definición del formato BCX
     */
    public BcxFormatDefinition () {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            
            doc = docBuilder.newDocument();
            Element grupo = doc.createElement(              "grupo");
            doc.appendChild(grupo);
            
            Element codigo_grupo = doc.createElement(       "codigo-grupo");
            codigo_grupo.setTextContent(RandomGenerator.getRandomAlphaNumericString(13));
            grupo.appendChild(codigo_grupo);
            Element descripcion = doc.createElement(        "descripcion");
            grupo.appendChild(descripcion);
            Element fecha_grupo = doc.createElement(        "fecha-grupo");
            grupo.appendChild(fecha_grupo);
            Element obra = doc.createElement(               "obra");
            grupo.appendChild(obra);
            
            // <editor-fold defaultstate="expanded" desc=" DATOS-GENERALES ">
            // datos generales
            Element datos_generales = doc.createElement(    "datos-generales");
            obra.appendChild(datos_generales);
            Element codigo_obra = doc.createElement(        "codigo");
                datos_generales.appendChild(codigo_obra);
            Element propiedad_obra = doc.createElement(     "propiedad");
                datos_generales.appendChild(propiedad_obra);
            Element version_obra = doc.createElement(       "version-formato");
                datos_generales.appendChild(version_obra);
            Element version_formato = doc.createElement(    "version");
                    version_obra.appendChild(version_formato);
            Element fecha_formato = doc.createElement(      "fecha");
                    version_obra.appendChild(fecha_formato);
            Element programa_obra = doc.createElement(      "programa-emision");
                datos_generales.appendChild(programa_obra);
            Element cabecera_obra = doc.createElement(      "cabecera");
                datos_generales.appendChild(cabecera_obra);
            Element rotulo_obra = doc.createElement(        "rotulo-identificacion");
                datos_generales.appendChild(rotulo_obra);
            Element juego_obra = doc.createElement(         "juego-caracteres");
                datos_generales.appendChild(juego_obra);
            Element comentario_obra = doc.createElement(    "comentario");
                datos_generales.appendChild(comentario_obra);
            Element tipoinfo_obra = doc.createElement(      "tipo-informacion");
                datos_generales.appendChild(tipoinfo_obra);
            Element numcert_obra = doc.createElement(       "numero-certificacion");
                datos_generales.appendChild(numcert_obra);
            Element fechacert_obra = doc.createElement(     "fecha-certificacion");
                datos_generales.appendChild(fechacert_obra);
            Element urlbase_obra = doc.createElement(       "url-base");
                datos_generales.appendChild(urlbase_obra);
            // coeficientes
            // <editor-fold defaultstate="collapsed" desc=" COEFICIENTES ">
            Element coeficientes_obra = doc.createElement(  "coeficientes");
                datos_generales.appendChild(coeficientes_obra);
            Element dn_coeficiente = doc.createElement(       "dn");
                coeficientes_obra.appendChild(dn_coeficiente);
            Element dd_coeficiente = doc.createElement(       "dd");
                coeficientes_obra.appendChild(dd_coeficiente);
            Element ds_coeficiente = doc.createElement(       "ds");
                coeficientes_obra.appendChild(ds_coeficiente);
            Element dr_coeficiente = doc.createElement(       "dr");
                coeficientes_obra.appendChild(dr_coeficiente);
            Element di_coeficiente = doc.createElement(       "di");
                coeficientes_obra.appendChild(di_coeficiente);
            Element dp_coeficiente = doc.createElement(       "dp");
                coeficientes_obra.appendChild(dp_coeficiente);
            Element dc_coeficiente = doc.createElement(       "dc");
                coeficientes_obra.appendChild(dc_coeficiente);
            Element dm_coeficiente = doc.createElement(       "dm");
                coeficientes_obra.appendChild(dm_coeficiente);
            Element divisa_coeficiente = doc.createElement(   "divisa");
                coeficientes_obra.appendChild(divisa_coeficiente);
            Element ci_coeficiente = doc.createElement(       "ci");
                coeficientes_obra.appendChild(ci_coeficiente);
            Element gg_coeficiente = doc.createElement(       "gg");
                coeficientes_obra.appendChild(gg_coeficiente);
            Element bi_coeficiente = doc.createElement(       "bi");
                coeficientes_obra.appendChild(bi_coeficiente);
            Element baja_coeficiente = doc.createElement(      "baja");
                coeficientes_obra.appendChild(baja_coeficiente);
            Element iva_coeficiente = doc.createElement(       "iva");
                coeficientes_obra.appendChild(iva_coeficiente);
            Element drc_coeficiente = doc.createElement(       "drc");
                coeficientes_obra.appendChild(drc_coeficiente);
            Element ddc_coeficiente = doc.createElement(       "ddc");
                coeficientes_obra.appendChild(ddc_coeficiente);
            Element dfs_coeficiente = doc.createElement(       "dfs");
                coeficientes_obra.appendChild(dfs_coeficiente);
            Element drs_coeficiente = doc.createElement(       "drs");
                coeficientes_obra.appendChild(drs_coeficiente);
            Element duo_coeficiente = doc.createElement(       "duo");
                coeficientes_obra.appendChild(duo_coeficiente);
            Element ddi_coeficiente = doc.createElement(       "ddi");
                coeficientes_obra.appendChild(ddi_coeficiente);
            Element des_coeficiente = doc.createElement(       "des");
                coeficientes_obra.appendChild(des_coeficiente);
            Element ddn_coeficiente = doc.createElement(       "ddn");
                coeficientes_obra.appendChild(ddn_coeficiente);
            Element ddd_coeficiente = doc.createElement(       "ddd");
                coeficientes_obra.appendChild(ddd_coeficiente);
            Element dsp_coeficiente = doc.createElement(       "dsp");
                coeficientes_obra.appendChild(dsp_coeficiente);
            Element dec_coeficiente = doc.createElement(       "dec");
                coeficientes_obra.appendChild(dec_coeficiente);
            Element divisa2_coeficiente = doc.createElement(   "divisa2");
                coeficientes_obra.appendChild(divisa2_coeficiente);
            Element n_coeficiente = doc.createElement(         "n");
                coeficientes_obra.appendChild(n_coeficiente);
            // </editor-fold>
            // </editor-fold>
            
            // <editor-fold defaultstate="expanded" desc=" CONCEPTOS ">
            // conceptos
            Element conceptos = doc.createElement(          "conceptos");
            obra.appendChild(conceptos);
            Element concepto = doc.createElement(           "concepto");
            conceptos.appendChild(concepto);
            //concepto
            Element codigo_concepto = doc.createElement(    "codigo");
            concepto.appendChild(codigo_concepto);
            Element orden_concepto = doc.createElement(     "orden");
            concepto.appendChild(orden_concepto);
            Element unidad_concepto = doc.createElement(    "unidad");
            concepto.appendChild(unidad_concepto);
            Element resumen_concepto = doc.createElement(   "resumen");
            concepto.appendChild(resumen_concepto);
            Element esquema_concepto = doc.createElement(   "esquema-precios");
            concepto.appendChild(esquema_concepto);
            Element precio_concepto = doc.createElement(    "precio");
            concepto.appendChild(precio_concepto);
            Element fecha_concepto = doc.createElement(     "fecha");
            concepto.appendChild(fecha_concepto);
            Element tipo_concepto = doc.createElement(      "tipo");
            concepto.appendChild(tipo_concepto);
            Element descomposicion_concepto = doc.createElement("descomposicion");
            concepto.appendChild(descomposicion_concepto);
                Element codigos_padre = doc.createElement(      "codigos-padre");
                descomposicion_concepto.appendChild(codigos_padre);
                    Element codigo_padre = doc.createElement(   "codigo-padre");
                    codigos_padre.appendChild(codigo_padre);
                Element codigos_hijo = doc.createElement(       "codigos-hijo");
                descomposicion_concepto.appendChild(codigos_hijo);
                    Element codigo_hijo = doc.createElement(    "codigo-hijo");
                    codigos_hijo.appendChild(codigo_hijo);
                    Element factor_hijo = doc.createElement(    "factor");
                    codigos_hijo.appendChild(factor_hijo);
                    Element rendimiento_hijo = doc.createElement("rendimiento");
                    codigos_hijo.appendChild(rendimiento_hijo);
            Element desresiduos_concepto = doc.createElement(   "descomposicion-residuos");
            concepto.appendChild(desresiduos_concepto);
                Element codigores_padre = doc.createElement(    "codigo-padre");
                desresiduos_concepto.appendChild(codigores_padre);
                Element tipodescomp_res = doc.createElement(    "tipo-descomposicion");
                desresiduos_concepto.appendChild(tipodescomp_res);
                Element codigores_hijo = doc.createElement(     "codigo-hijo");
                desresiduos_concepto.appendChild(codigores_hijo);
                Element propiedadres = doc.createElement(       "propiedad");
                desresiduos_concepto.appendChild(propiedadres);
                Element valorres = doc.createElement(           "valor");
                desresiduos_concepto.appendChild(valorres);
                Element unidad_medidares = doc.createElement(   "unidad-medida");
                desresiduos_concepto.appendChild(unidad_medidares);
            Element texto_concepto = doc.createElement(         "texto");
            concepto.appendChild(texto_concepto);
            
            Element entidades_concepto = doc.createElement("entidades");
            concepto.appendChild(entidades_concepto);
            Element entidad_concepto = doc.createElement("entidad-asociada");
            entidades_concepto.appendChild(entidad_concepto);
            
            Element graficos_concepto = doc.createElement("graficos");
            concepto.appendChild(graficos_concepto);
            Element archivo_grafico = doc.createElement("archivo-grafico");
            graficos_concepto.appendChild(archivo_grafico);
            Element url_externa = doc.createElement("url-externa");
            graficos_concepto.appendChild(url_externa);
            // información técnica
            Element infotecnica_concepto = doc.createElement("informacion-tecnica");
            concepto.appendChild(infotecnica_concepto);
            Element codigoit_concepto = doc.createElement("codigo-it");
            infotecnica_concepto.appendChild(codigoit_concepto);
            Element descripcionit_concepto = doc.createElement("descripcion-it");
            infotecnica_concepto.appendChild(descripcionit_concepto);
            Element valorit_concepto = doc.createElement("valor-it");
            infotecnica_concepto.appendChild(valorit_concepto);
            // mediciones
            Element mediciones_concepto = doc.createElement(    "mediciones");
            concepto.appendChild(mediciones_concepto);
                Element mediciontotal_concepto = doc.createElement("medicion-total");
                mediciones_concepto.appendChild(mediciontotal_concepto);
                Element linea_medicion = doc.createElement(     "linea-medicion");
                mediciones_concepto.appendChild(linea_medicion);
                    Element posicion_linea = doc.createElement( "posicion");
                    linea_medicion.appendChild(posicion_linea);
                    Element tipo_linea = doc.createElement(     "tipo");
                    linea_medicion.appendChild(tipo_linea);
                    Element comentario_linea = doc.createElement("comentario");
                    linea_medicion.appendChild(comentario_linea);
                    Element idbim_linea = doc.createElement(    "id-bim");
                    linea_medicion.appendChild(idbim_linea);
                    Element unidades_linea = doc.createElement( "unidades");
                    linea_medicion.appendChild(unidades_linea);
                    Element longitud_linea = doc.createElement( "longitud");
                    linea_medicion.appendChild(longitud_linea);
                    Element latitud_linea = doc.createElement(  "latitud");
                    linea_medicion.appendChild(latitud_linea);
                    Element altura_linea = doc.createElement(   "altura");
                    linea_medicion.appendChild(altura_linea);
                    Element etiqueta_linea = doc.createElement("etiqueta");
                    linea_medicion.appendChild(etiqueta_linea);
                    Element referencias_linea = doc.createElement("referencias");
                    linea_medicion.appendChild(referencias_linea);
                        // definir las referencias
                    Element arbolprecios_linea = doc.createElement("arbol-precios");
                    linea_medicion.appendChild(arbolprecios_linea);
            // pliego
            Element pliego_concepto = doc.createElement(            "pliego");
            concepto.appendChild(pliego_concepto);
                Element codigoseccion_pliego = doc.createElement(   "codigo-seccion");
                pliego_concepto.appendChild(codigoseccion_pliego);
                Element rotuloseccion_pliego = doc.createElement(   "rotulo-seccion");
                pliego_concepto.appendChild(rotuloseccion_pliego);
                Element textoseccion_pliego = doc.createElement(    "texto-seccion");
                pliego_concepto.appendChild(textoseccion_pliego);
                Element descripcion_pliego = doc.createElement(     "descripcion");
                pliego_concepto.appendChild(descripcion_pliego);
                Element requisitos_pliego = doc.createElement(      "requisitos");
                pliego_concepto.appendChild(requisitos_pliego);
                Element componentes_pliego = doc.createElement(     "componentes");
                pliego_concepto.appendChild(componentes_pliego);
                Element ejecucion_pliego = doc.createElement(       "ejecucion");
                pliego_concepto.appendChild(ejecucion_pliego);
                Element normativa_pliego = doc.createElement(       "normativa");
                pliego_concepto.appendChild(normativa_pliego);
                Element aceptacionseccion_pliego = doc.createElement("control-aceptacion");
                pliego_concepto.appendChild(aceptacionseccion_pliego);
                Element seguridad_pliego = doc.createElement(       "seguridad-higiene");
                pliego_concepto.appendChild(seguridad_pliego);
                Element valora_pliego = doc.createElement(          "valoracion-medicion");
                pliego_concepto.appendChild(valora_pliego);
                Element mantenimiento_pliego = doc.createElement(   "mantenimiento");
                pliego_concepto.appendChild(mantenimiento_pliego);
                Element varios_pliego = doc.createElement(          "varios");
                pliego_concepto.appendChild(varios_pliego);
                Element rtf_pliego = doc.createElement(             "archivo-rtf");
                pliego_concepto.appendChild(rtf_pliego);
                Element htm_pliego = doc.createElement(             "archivo-htm");
                pliego_concepto.appendChild(htm_pliego);
                Element ambitogeografico_pliego = doc.createElement("ambito-geografico");
                pliego_concepto.appendChild(ambitogeografico_pliego);
                    Element abreviatura_ambito = doc.createElement( "abreviatura");
                    ambitogeografico_pliego.appendChild(abreviatura_ambito);
                    Element ambito = doc.createElement(             "ambito");
                    ambitogeografico_pliego.appendChild(ambito);
            
            // documentos adjuntos
            Element adjuntos_concepto = doc.createElement(                      "documentos-adjuntos");
            concepto.appendChild(adjuntos_concepto);
                Element archivo_adjunto = doc.createElement(                    "archivo");
                adjuntos_concepto.appendChild(archivo_adjunto);
                    Element archivo_adjunto_tipo = doc.createElement(           "tipo");
                    archivo_adjunto.appendChild(archivo_adjunto_tipo);
                    Element archivo_adjunto_nombre = doc.createElement(         "nombre-archivo");
                    archivo_adjunto.appendChild(archivo_adjunto_nombre);
                    Element archivo_adjunto_descripcion = doc.createElement(    "descripcion");
                    archivo_adjunto.appendChild(archivo_adjunto_descripcion);
                    Element archivo_adjunto_url = doc.createElement(            "url-exterior");
                    archivo_adjunto.appendChild(archivo_adjunto_url);
            
            
            // </editor-fold>
            
            // <editor-fold defaultstate="expanded" desc=" ENTIDADES ">
            // entidades
            Element entidades = doc.createElement(          "entidades");
            obra.appendChild(entidades);
            
            // entidad
            Element entidad = doc.createElement(            "entidad");
            entidades.appendChild(entidad);
            Element codigo_entidad = doc.createElement(     "codigo");
            entidad.appendChild(codigo_entidad);
            Element resumen_entidad = doc.createElement(    "resumen");
            entidad.appendChild(resumen_entidad);
            Element nombre_entidad = doc.createElement(     "nombre");
            entidad.appendChild(nombre_entidad);
            Element tipo_entidad = doc.createElement(       "tipo");
            entidad.appendChild(tipo_entidad);
            Element subnombre_entidad = doc.createElement(  "subnombre");
            entidad.appendChild(subnombre_entidad);
            Element direccion_entidad = doc.createElement(  "direccion");
            entidad.appendChild(direccion_entidad);
            Element cp_entidad = doc.createElement(         "cp");
            entidad.appendChild(cp_entidad);
            Element localidad = doc.createElement(          "localidad");
            entidad.appendChild(localidad);
            Element provincia_entidad = doc.createElement(  "provincia");
            entidad.appendChild(provincia_entidad);
            Element pais_entidad = doc.createElement(       "pais");
            entidad.appendChild(pais_entidad);
            Element telefono_entidad = doc.createElement(   "telefono");
            entidad.appendChild(telefono_entidad);
            Element fax_entidad = doc.createElement(        "fax");
            entidad.appendChild(fax_entidad);
            Element contacto_entidad = doc.createElement(   "persona-contacto");
            entidad.appendChild(contacto_entidad);
            Element cif_entidad = doc.createElement(        "cif");
            entidad.appendChild(cif_entidad);
            Element web_entidad = doc.createElement(        "web");
            entidad.appendChild(web_entidad);
            Element email_entidad = doc.createElement(      "email");
            entidad.appendChild(email_entidad);
            // </editor-fold>
        
        
            // write the content into xml file
            TransformerFactory transformerFactory   = TransformerFactory.newInstance();
            Transformer transformer                 = transformerFactory.newTransformer();
            DOMSource source                        = new DOMSource(doc);
            StreamResult result                     = new StreamResult(new File("grupo.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
            
        } catch (ParserConfigurationException  ex) { // NO (ParserConfigurationException | TransformerConfigurationException ex) para compatibilidad con Java 6
            System.out.println("Error XML: " + ex.getLocalizedMessage());
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error XML: " + ex.getLocalizedMessage());
        }catch (TransformerException ex) {
            System.out.println("Error de transformación de XML: " + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Método que permite escribir el formato BCX en el archivo que se pasa como
     * parámetro
     * @param fileName <code>String</code> Nombre del archivo
     * @return <code>true</code> en el caso de que la escritura se realice correctamente
     * y <code>false</code> en caso contrario
     */
    public boolean writeBcx(String fileName) {
        boolean writeOk = false;
        TransformerFactory transformerFactory   = TransformerFactory.newInstance();
        try {
            Transformer transformer             = transformerFactory.newTransformer();
            DOMSource source                    = new DOMSource(doc);
            StreamResult result                 = new StreamResult(new File(fileName));
            
            transformer.transform(source, result);
            writeOk = true;
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error de configuración en el transformador:  " + ex.getLocalizedMessage());
        } catch (TransformerException ex) {
            System.out.println("Error de transformación XML." + ex.getLocalizedMessage());
        }
        
        
        return writeOk;
    }    
    
    // Estudiar la introducción de los datos generales: modo, raíz, etc.
    public boolean setDatosGenerales(   String  nombreElemento,
                                        String  codigo, 
                                        String  propiedad, 
                                        String  versionFormato,
                                        String  version,
                                        Date    fecha,
                                        String  programaEmision,
                                        String  cabecera,
                                        String  rotuloIdentificacion,
                                        String  juegoCaracteres,
                                        String  comentario,
                                        String  tipoInformacion,
                                        int     numeroCertificacion,
                                        Date    fechaCertificacion,
                                        String  urlBase) {
        
        Element elemento = doc.getElementById(nombreElemento);
        
        return true;
    }
    
}