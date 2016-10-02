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
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class WebOficialBc3ToHtml {
    private static final String DIRECCION = "http://happservicios.wordpress.com/";
    private static boolean errorAbriendoWeb = false;
    
    public static boolean open() {
        Thread hiloWeb = new Thread(){
            @Override
            public void run(){
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI uri = new URI( DIRECCION );
                    desktop.browse(uri);
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

    public static String getDIRECCIONWEB() {
        return DIRECCION;
    }
}
