package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Facultad implements Serializable {
	
	private int codigo;
	private Sede sede;
	private String nombre;
	private String decano;
	private String correo;
	private String telefono;
	private Date fechaCreacion;
	private int estado;
	
	private static final long serialVersionUID = 1L;

}
