package com.usco.edu.dao;

public interface IDocumentoDao {

	String obtenerTokenDocumento(String atributos, String user);

	String obtenerTokenDocumentoVisualizar(String atributos, String user);

}
