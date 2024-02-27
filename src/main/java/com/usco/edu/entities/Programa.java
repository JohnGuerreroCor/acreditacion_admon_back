package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Programa implements Serializable {

	private int codigo;
	private int snies;
	private String nombre;
	private String titulo;
	private int nivelAcademicoCodigo;
	private String nivelAcademico;
	private int nivelFormacionCodigo;
	private String nivelFormacion;
	private int estadoSniesCodigo; 
	private String estadoSnies;
	private int ciclosCodigo;
	private String ciclos;
	private int modalidadCodigo;
	private String modalidad;
	private int areaConocimientoCodigo;
	private String areaConocimiento;
	private int nbcCodigo;
	private String nbc;
	private String sede;
	private int creditos;
	private int tipoDuracionCodigo;
	private String tipoDuracion;
	private int duracion;
	private int tipoAdmisionCodigo;
	private String tipoAdmision;
	private int cupos;
	private String sitioWeb;
	private int normaCodigo;
	private String norma;
	private int convenio;
	private Date fechaCreacion;
	private Date fechaRegistroSnies;
	private String institucionNombre;
	private String institucionNit;
	private int institucionIes;
	private int institucionIesPadre;
	private int estado;
	
	private static final long serialVersionUID = 1L;
	
}
