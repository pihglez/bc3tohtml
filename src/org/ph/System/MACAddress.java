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
package org.ph.System;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.TreeMap;

/**
 * Clase que permite calcular la dirección MAC de los distintos dispositivos
 * conectados al sistema
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class MACAddress {
    private final Enumeration<NetworkInterface>         NETS;
    private final InetAddress                           ADDRESS;
    private final NetworkInterface                      NI;
    private byte[]                                      mac;
    private final StringBuffer                          SB;
    private final TreeMap<String, ArrayList<String>>    networkInterfaces;
    
    public MACAddress() throws UnknownHostException, SocketException {
        networkInterfaces   = new   TreeMap<String, ArrayList<String>>();
        NETS                =       NetworkInterface.getNetworkInterfaces();
        ADDRESS             =       InetAddress.getLocalHost();
        NI                  =       NetworkInterface.getByInetAddress(ADDRESS);
        SB                  = new   StringBuffer();
        
        setNetworkInterfaces();
        
        if (NI != null) {
            mac = NI.getHardwareAddress();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    SB.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
            } else {
                SB.append("Dirección mac n/a");
            }
        } else {
            SB.append("Interfaz de red n/a");
        }
    }
    
    public String getMACAddress() {
        return SB.toString();
    }
    
    /**
     * Método que devuelve un <code>ArrayList</code> con las interfaces
     * y las direcciones MAC de las mismas existentes en el sistema
     * @return <code>ArrayList</code> interfaces y direcciones MAC de las
     * mismas
     */
    private void setNetworkInterfaces(){
        Enumeration<InetAddress> inetAddresses;
        String tmp;
        
        for(NetworkInterface ni : Collections.list(NETS)){
            tmp = ni.getDisplayName();
            inetAddresses = ni.getInetAddresses();
            ArrayList<String> as = new ArrayList<String>();
            
            
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                as.add(inetAddress.getHostAddress());
            }

            networkInterfaces.put(tmp, as);
        }
    }
    
    public int getNetworkInterfacesSize() {
        return networkInterfaces.size();
    }
    
    public ArrayList<String> getNetworkInterface(String key) {
        return networkInterfaces.get(key);
    }
    
    public ArrayList<String> getNetworkInterfacesNames() {
        ArrayList<String> al = new ArrayList<String>();
        
        for (String s: networkInterfaces.keySet()) {
            al.add(s);
        }
        
        return al;
    }
}