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
@Table(name = "persona", schema = "dbo")
@Data
@NoArgsConstructor
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_codigo", columnDefinition = "integer")
    private Long codigo;

    @Column(name = "per_nombre")
    private String nombre;

    @Column(name = "per_apellido")
    private String apellido;

    @Column(name = "per_identificacion")
    private String identificacion;

    @Column(name = "per_email_interno")
    private String emailInterno;

    private static final long serialVersionUID = 1L;
}