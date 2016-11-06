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

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Clase que implementa la apertura del explorador web del sistema para visitar
 * la web de la aplicación o bien el sitio github de la misma
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class WebOficialBc3ToHtml {
    /**
     * Dirección web de la aplicación
     */
    private static final String     DIRECCION           = "http://happservicios.wordpress.com/";
    /**
     * Dirección github de la aplicación
     */
    private static final String     DIRECCIONGITHUB     = "https://github.com/pihglez/bc3tohtml/branches/all";
    private static boolean          errorAbriendoWeb    = false;
    
    /**
     * Método que gestiona la apertura del explorador web por defecto del sistema
     * para visitar la web o el sitio github de la aplicación
     * @param dir <code>enum</code> La dirección de la web a visitar
     * @return <code>boolean</code> <code>True</code> si se abre correctamente el explorador
     * y <code>False</code> en cualquier otro caso.
     */
    public static boolean open(direccion dir) {
        boolean retorno = false;
        switch (dir) {
            case WEB:
                retorno = openSelectedWeb(DIRECCION);
                break;
            case GITHUB:
                retorno = openSelectedWeb(DIRECCIONGITHUB);
                break;
            default:
                break;
        }
        return retorno;
    }
    
    /**
     * Método que abre el explador por defecto del sistema para visitar la web
     * configurada en la clase. Esta operación se realiza en un hilo aparte por
     * lo que no espera a su finalización para continuar con la ejecución del
     * programa.
     * @param direccion <code>String</code> La dirección web a abrir por el explorador
     * por defecto del sistema
     * @return <code>boolean</code> <code>True</code> en caso de que la ejecución sea exitosa
     * y <code>False</code> en cualquier otro caso.
     */
    private static boolean openSelectedWeb(String direccion) {
//        final String DIRECCION = direccion;
        Thread hiloWeb = new Thread(){
            @Override
            public void run(){
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI uri = new URI( DIRECCION );
                    desktop.browse(uri);
//                    hay que evitar que la ejecució del explorador mande información
//                    a la pantalla de la aplicación en Linux
//                    System.exit(0); // Runtime.getRuntime().exit(0);
                } catch (IOException ex) {
                    System.out.println("No se ha podido abrir la página. "  + ex.getLocalizedMessage());
                    System.out.println("Por favor, diríjase a la página "   + getDIRECCIONWEB() + 
                                       " para más información.");
                    errorAbriendoWeb = true;
                } catch (URISyntaxException ex) {
                    System.out.println("No se ha podido abrir la página. "  + ex.getLocalizedMessage());
                    System.out.println("Por favor, diríjase a la página "   + getDIRECCIONWEB() + 
                                       " para más información.");
                    errorAbriendoWeb = true;
                }
            }
        };

        hiloWeb.start();
        
        return errorAbriendoWeb;
    }

    /**
     * Método que devuelve la dirección web principal de la aplicación
     * @return <code>String</code> Dirección web de la aplicación
     */
    public static String getDIRECCIONWEB() {
        return DIRECCION;
    }
    
    /**
     * Método que devuelve la dirección web github de la aplicación
     * en la que se encuentran todas las ramas del mismo
     * @return <code>String</code> Dirección web de las ramas de la aplicación
     * en github
     */
    public static String getDIRECCIONGITHUB() {
        return DIRECCIONGITHUB;
    }
    
    /**
     * Constantes enumeradas de las distintas web que pueden ser abiertas desde esta clase
     */
    public static enum direccion {
        WEB, GITHUB
    }
    
    /**
     * Método que comprueba la versión actual del software en la web github
     * @param currentVersion <code>String</code> La versión actual del software
     * @return <code>boolean</code> True en caso de que haya una nueva versión y
     * False en caso contrario
     * @throws MalformedURLException <code></code> En el caso de que la dirección
     * prevista sea errónea.
     * @throws IOException <code></code> en el caso de que no se pueda acceder a
     * la página
     */
    public static boolean checkAppVersion(String currentVersion) throws MalformedURLException, IOException {
        boolean toReturn = false;
        
        //<editor-fold defaultstate="collapsed" desc="esto debería ir en un hilo propio">
        URL url = new URL( DIRECCIONGITHUB );
        InputStream html = null;
        
        html = url.openStream();
        
        int c = 0;
        
        StringBuilder buffer = new StringBuilder();
        while(c != -1) {
            c = html.read();
            buffer.append((char)c);
        }
        
        // se busca la última versión
        String bf = buffer.toString();
        int dir = buffer.toString().indexOf("master_");
        String newVersion = bf.substring(dir, 15).replace("master_", "");
        
        //</editor-fold>
        
        toReturn = newVersionAvailable(currentVersion, newVersion);

        return toReturn;
    }
    
    /**
     * Método que compara dos cadenas cuyo formato debe ser la marca de versión
     * del software, con formato "v0.5.1.0"
     * @param actual <code>String</code> La versión actual del software
     * @param nueva <code>String</code> La versión más actualizada en la web
     * @return <code>boolean</code> True si la versión actual es mayor y False
     * en aso contrario
     */
    private static boolean newVersionAvailable(String actual, String nueva) {
        boolean toReturn = false;
        if(nueva.equals(actual)) return false;
        
        String[] act = actual.split(".");
        String[] fi = nueva.replace("_", ".").split(".");
        
        return toReturn;
    }
}