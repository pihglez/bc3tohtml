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

import java.util.Map;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class SystemEnvironmentVariables {
    Map <String, String> env;
    
    public SystemEnvironmentVariables() {
        env = System.getenv();
    }
    
    public String getAllEnvironmentVariables () {
        StringBuilder sb = new StringBuilder();
        
        for (String s : env.keySet()) {
            sb.append(String.format("%s: %s%n", s, env.get(s)));
        }
        
        return sb.toString();
    }
    
    public String getVariable(String variableName) {
        return env.get(variableName);
    }
}
