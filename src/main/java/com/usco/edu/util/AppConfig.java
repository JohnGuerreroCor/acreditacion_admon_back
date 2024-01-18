package com.usco.edu.util;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Component
public class AppConfig {
	
	@Value("${spring.profiles.active}")
	private String perfilSeleccionado;

	@Value("${spring.datasource.url}")
	private String datasourceLocal;
	
	@Autowired
	private DataSource dataSource;
	
	// CONFIGURACIÓN DE JDBCTEMPLATE PARA CONSULTAS GENERALES
	@Bean(name = "JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplateConsultasjdbc() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}
	
	// CONFIGURACIÓN DE JDBCTEMPLATE PARA EL LOGIN
	@Bean(name = "JDBCTemplateLogin")
	public JdbcTemplate jdbcTemplateLogin() throws Exception {
		DataSource dataSource = null;

		// SELECCIÓN DEL DATASOURCE SEGÚN EL PERFIL ACTIVO
		if (perfilSeleccionado.equals("local")) {
			dataSource = (DataSource) new JndiTemplate().lookup("jboss/datasources/LoginDS");
		} else if (perfilSeleccionado.equals("test") || perfilSeleccionado.equals("produccion")) {
			dataSource = (DataSource) new JndiTemplate().lookup("java:jboss/datasources/DatasourceLogueoCreadoPorDBA");
		}
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}
	
	// CONFIGURACIÓN DE NAMEDPARAMETERJDBCTEMPLATE PARA CONSULTAS CON PARÁMETROS
	@Bean(name = "NamedJDBCTemplateEncuestasConsulta")
	public NamedParameterJdbcTemplate jdbcTemplateConsulta() throws Exception {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
