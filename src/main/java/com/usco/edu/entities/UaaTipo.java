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
@Table(name = "uaa_tipo", schema = "dbo")
@Data
@NoArgsConstructor
public class UaaTipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uat_codigo", columnDefinition = "integer")
    private Long codigo;

    @Column(name = "uat_nombre")
    private String nombre;

    public UaaTipo(String nombre) {
        this.nombre = nombre;
    }

    private static final long serialVersionUID = 1L;
}
