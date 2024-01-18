package com.usco.edu.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SegundaClave {
    
    // MAPA PARA ALMACENAR CLAVES ASOCIADAS A USUARIOS
    private Map<String, String> mapClave = new HashMap<>();
    
    // MAPA PARA ALMACENAR USUARIOS ASOCIADOS A SESIONES
    private Map<String, String> mapUser = new HashMap<>();
    
    // MÉTODO PARA ESTABLECER LA SEGUNDA CLAVE ASOCIADA A UN USUARIO
    public void setClave(String usuario, String clave) {
        mapClave.put(usuario, clave);
    }
    
    // MÉTODO PARA OBTENER LA SEGUNDA CLAVE ASOCIADA A UN USUARIO
    public String getClave(String usuario) {
        String clave = mapClave.get(usuario);
        if (clave == null) {
            throw new NullPointerException("No tiene segunda clave en sesión");
        }
        return clave;
    }
    
    // MÉTODO PARA ESTABLECER EL USUARIO ASOCIADO A UNA SESIÓN
    public void setUser(String usuario, String user) {
        mapUser.put(usuario, user);
    }
    
    // MÉTODO PARA OBTENER EL USUARIO ASOCIADO A UNA SESIÓN
    public String getUser(String usuario) {
        String user = mapUser.get(usuario);
        if (user == null) {
            throw new NullPointerException("No tiene usuario en sesión");
        }
        return user;
    }
}
