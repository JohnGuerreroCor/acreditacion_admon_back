package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sede", schema = "dbo")
@Data
@NoArgsConstructor
public class Sede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sed_codigo", columnDefinition = "integer")
    private Long codigo;

    @Column(name = "sed_nombre")
    private String nombre;

    @Column(name = "SedNombre_corto", columnDefinition = "nchar")
    private String nombreCorto;

    @Column(name = "sed_estado", columnDefinition = "nchar")
    private String estado;

    public Sede(String nombre, String nombreCorto, String estado) {
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.estado = estado;
    }

    private static final long serialVersionUID = 1L;
}
