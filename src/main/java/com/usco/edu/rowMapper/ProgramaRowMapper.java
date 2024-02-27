package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Programa;


public class ProgramaRowMapper implements RowMapper<Programa>{

	@Override
	public Programa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Programa programa = new Programa();
		programa.setCodigo(rs.getInt("pro_codigo"));
		programa.setSnies(rs.getInt("pro_codigo_snies"));
		programa.setNombre(rs.getString("pro_nombre"));
		programa.setTitulo(rs.getString("pro_titulo_otorgado"));
		programa.setNivelAcademicoCodigo(rs.getInt("nia_codigo"));
		programa.setNivelAcademico(rs.getString("nia_nombre"));
		programa.setNivelFormacionCodigo(rs.getInt("nif_codigo"));
		programa.setNivelFormacion(rs.getString("nif_nombre"));
		programa.setEstadoSniesCodigo(rs.getInt("ess_codigo"));
		programa.setEstadoSnies(rs.getString("ess_nombre"));
		programa.setCiclosCodigo(rs.getInt("cip_codigo"));
		programa.setCiclos(rs.getString("cip_nombre"));
		programa.setModalidadCodigo(rs.getInt("mod_codigo"));
		programa.setModalidad(rs.getString("mod_nombre"));
		programa.setAreaConocimientoCodigo(rs.getInt("arc_codigo"));
		programa.setAreaConocimiento(rs.getString("arc_nombre"));
		programa.setNbcCodigo(rs.getInt("nbc_codigo"));
		programa.setNbc(rs.getString("nbc_nombre"));
		programa.setSede(rs.getString("sed_nombre"));
		programa.setCreditos(rs.getInt("pro_numero_creditos"));
		programa.setTipoDuracionCodigo(rs.getInt("tid_codigo"));
		programa.setTipoDuracion(rs.getString("tid_nombre"));
		programa.setDuracion(rs.getInt("pro_duracion"));
		programa.setTipoAdmisionCodigo(rs.getInt("tia_codigo"));
		programa.setTipoAdmision(rs.getString("tia_nombre"));
		programa.setCupos(rs.getInt("pro_numero_cupos"));
		programa.setSitioWeb(rs.getString("pro_sitio_web"));
		programa.setNormaCodigo(rs.getInt("nor_codigo"));
		programa.setNorma(rs.getString("not_nombre")+" "+rs.getString("nor_numero")+" "+rs.getString("nor_nombre"));
		programa.setConvenio(rs.getInt("pro_convenio"));
		programa.setFechaCreacion(rs.getDate("pro_fecha_creacion"));
		programa.setFechaRegistroSnies(rs.getDate("pro_fecha_registro_snies"));
		programa.setEstado(rs.getInt("pro_estado"));
		
		return programa;
	}
}
