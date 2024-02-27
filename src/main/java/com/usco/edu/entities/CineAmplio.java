package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CineAmplio implements Serializable {
	
	private int codigo;
	private String nombre;
	private String cine;
	private int estado;
	
	private static final long serialVersionUID = 1L;

}
