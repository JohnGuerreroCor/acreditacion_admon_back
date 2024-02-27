package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CineDetallado implements Serializable {
	
	private int codigo;
	private String nombre;
	private String cine;
	private int amplioCodigo;
	private String amplioCine;
	private String amplio;
	private int especificoCodigo;
	private String especificoCine;
	private String especifico;
	private int estado;
	
	private static final long serialVersionUID = 1L;

}
