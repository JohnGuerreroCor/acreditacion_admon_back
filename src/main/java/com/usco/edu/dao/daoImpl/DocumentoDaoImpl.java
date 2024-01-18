package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDocumentoDao;

@Repository
public class DocumentoDaoImpl implements IDocumentoDao {

    @Autowired
    @Qualifier("JDBCTemplateConsulta")
    public JdbcTemplate jdbcTemplate;

    @Override
    public String obtenerTokenDocumento(String atributos, String user) {
        // OBTENER EL TOKEN DEL DOCUMENTO SEGÚN LOS ATRIBUTOS Y EL USUARIO
        String sql = "SELECT dbo.getTokenDocumento(?)";
        return jdbcTemplate.queryForObject(sql, new Object[] { atributos }, String.class);
    }

    @Override
    public String obtenerTokenDocumentoVisualizar(String atributos, String user) {
        // OBTENER EL TOKEN DEL DOCUMENTO PARA VISUALIZACIÓN SEGÚN LOS ATRIBUTOS Y EL USUARIO
        String sql = "SELECT dbo.getTokenDocumento(?) as token";
        return jdbcTemplate.queryForObject(sql, new Object[] { atributos }, String.class);
    }
}
