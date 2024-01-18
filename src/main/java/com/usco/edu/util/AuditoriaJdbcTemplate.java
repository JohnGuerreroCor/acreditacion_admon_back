package com.usco.edu.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaJdbcTemplate {
	
	@Autowired
	private SegundaClave segundaClave;
	
	@Autowired
	private ConexionBuilder conexionBuilder;
	
	// MÉTODO PARA CONSTRUIR UN NAMEDPARAMETERJDBCTEMPLATE A PARTIR DEL USUARIO
	public NamedParameterJdbcTemplate construirTemplate(String usuario) {
		// CONSTRUIR EL DATASOURCE USANDO LA SEGUNDA CLAVE DEL USUARIO
		DataSource dataSource = this.construirDataSourceDeUsuario(usuario);
		// CREAR Y DEVOLVER UN NAMEDPARAMETERJDBCTEMPLATE USANDO EL DATASOURCE
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	// MÉTODO PARA CONSTRUIR UN NAMEDPARAMETERJDBCTEMPLATE A PARTIR DE UN DATASOURCE
	public NamedParameterJdbcTemplate construirTemplatenew(DataSource dataSource) {
		// CREAR Y DEVOLVER UN NAMEDPARAMETERJDBCTEMPLATE USANDO EL DATASOURCE PROPORCIONADO
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	// MÉTODO PARA CONSTRUIR UN DATASOURCE A PARTIR DEL USUARIO
	public DataSource construirDataSourceDeUsuario(String usuario) {
		// CONSTRUIR EL DATASOURCE USANDO LA SEGUNDA CLAVE DEL USUARIO Y SU NOMBRE DE USUARIO
		return conexionBuilder.construir(segundaClave.getUser(usuario), segundaClave.getClave(usuario));
	}
}
