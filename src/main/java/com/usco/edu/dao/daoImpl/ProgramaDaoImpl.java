package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.usco.edu.dao.IProgramaDao;
import com.usco.edu.entities.Programa;
import com.usco.edu.resultSetExtractor.ProgramaSetExtractor;

public class ProgramaDaoImpl implements IProgramaDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Programa> obtenerListadoProgramas() {
		
		String sql = "select p.pro_codigo, p.pro_registro_snies, u.uaa_codigo, p.pro_fecha_creacion, "
				+ "p.pro_creacion_snies, u.uaa_nombre, s.sed_codigo, s.sed_nombre, j.jor_nombre, "
				+ "nat.nat_nombre, na.nia_nombre, fa.uaa_nombre, f.for_nombre from programa p"
				+ "inner join uaa u on p.uaa_codigo = u.uaa_codigo "
				+ "inner join sede s on p.sed_codigo = s.sed_codigo "
				+ "inner join modalidad m on p.mod_codigo = m.mod_codigo "
				+ "inner join nivel_academico na on p.nia_codigo = na.nia_codigo "
				+ "inner join nivel_academico_tipo nat on na.nat_codigo = nat.nat_codigo "
				+ "inner join formalidad f on na.for_codigo = f.for_codigo "
				+ "inner join uaa fa on u.uaa_dependencia = fa.uaa_codigo "
				+ "inner join jornada j on p.jor_codigo = j.jor_codigo"
				+ "where f.for_codigo = 1 and p.pro_estado = 1";
		return jdbcTemplate.query(sql, new ProgramaSetExtractor());
		
	}

}
