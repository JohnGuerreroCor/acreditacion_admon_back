package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "uaa", schema = "dbo")
@Data
@NoArgsConstructor
public class Uaa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uaa_codigo", columnDefinition = "integer")
    private Long codigo;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "uat_codigo")
    @NotFound(action = NotFoundAction.IGNORE)
    private UaaTipo uaaTipo;

    @Column(name = "uaa_nombre")
    private String nombre;

    @Column(name = "uaa_nombre_corto")
    private String nombreCorto;

    @Column(name = "uaa_nombre_impresion")
    private String nombreImpresion;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "sed_codigo")
    @NotFound(action = NotFoundAction.IGNORE)
    private Sede sede;

    @Column(name = "uaa_dependencia")
    private int uaa_dependencia;

    @Column(name = "uaa_telefono")
    private String telefono;

    @Column(name = "uaa_email")
    private String email;

    @Column(name = "uaa_direccion")
    private String direccion;

    @Column(name = "uaa_pagina")
    private String pagina;

    @Column(name = "uaa_jefe")
    private String jefe;

    @Column(name = "uaa_acronimo")
    private String acronimo;

    @Column(name = "uaa_centro_costos")
    private String centro_costos;

    @Column(name = "uaa_estado")
    private int estado;

    private static final long serialVersionUID = 1L;
}
