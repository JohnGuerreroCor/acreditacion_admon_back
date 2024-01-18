package com.usco.edu.util;

import org.springframework.security.authentication.BadCredentialsException;

// CLASE DE EXCEPCIÓN PERSONALIZADA PARA REPRESENTAR CREDENCIALES DESHABILITADAS
public class DisabledException extends BadCredentialsException {

    // CONSTRUCTOR QUE ACEPTA UN MENSAJE DE ERROR
    public DisabledException(String msg) {
        super(msg);
    }

    // NÚMERO DE VERSIÓN PARA LA SERIALIZACIÓN
    private static final long serialVersionUID = 1L;
}
