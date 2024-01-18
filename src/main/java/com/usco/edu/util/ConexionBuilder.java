package com.usco.edu.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ConexionBuilder {

    // OBTENER LA URL Y EL CONTROLADOR DE LA BASE DE DATOS DESDE EL ARCHIVO DE PROPIEDADES
    @Value("${spring.datasource.url}")
    private String urlProperties;

    @Value("${spring.datasource.driver-class-name}")
    private String driverProperties;

    private DataSource dataSource;

    // MÉTODO PARA CONSTRUIR Y OBTENER UN DATASOURCE A PARTIR DE UN USUARIO Y UNA CLAVE
    public DataSource construir(String usuario, String clave) {
        // CREAR UN CONSTRUCTOR DE DATASOURCE
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        // ESTABLECER EL CONTROLADOR JDBC Y LA URL CONSTRUIDA
        dataSourceBuilder.driverClassName(driverProperties);
        dataSourceBuilder.url(buildUrl(usuario, clave));

        // CONSTRUIR Y OBTENER EL DATASOURCE
        dataSource = dataSourceBuilder.build();
        return dataSource;
    }

    // MÉTODO PARA CONSTRUIR LA URL DE LA BASE DE DATOS CON EL USUARIO Y LA CLAVE PROPORCIONADOS
    private String buildUrl(String usuario, String clave) {
        // DIVIDIR LA URL ORIGINAL EN PARTES USANDO ";" COMO DELIMITADOR
        String urlDivision[] = urlProperties.split(";");
        // CREAR UN CONSTRUCTOR DE LA URL
        StringBuilder urlBuilder = new StringBuilder();

        // ITERAR SOBRE LAS PARTES DE LA URL ORIGINAL
        for (int i = 0; i < urlDivision.length; i++) {
            String cadena = urlDivision[i];
            // MODIFICAR LAS PARTES QUE CONTIENEN "USER=" Y "PASSWORD=" CON EL USUARIO Y LA CLAVE PROPORCIONADOS
            if (cadena.contains("user=")) {
                cadena = "user=" + usuario;
            } else if (cadena.contains("password=")) {
                cadena = "password=" + clave;
            }

            // CONSTRUIR LA URL MODIFICADA
            urlBuilder.append(cadena).append(";");
        }
        
        // ELIMINAR EL ÚLTIMO ";" DE LA URL CONSTRUIDA
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        System.out.println(urlBuilder.toString());
        return urlBuilder.toString();
    }
}
