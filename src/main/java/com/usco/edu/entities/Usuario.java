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
@Table(name = "dbo.usuario_graduado_admin_login")
@Data
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private int id;

    @Column(name = "us", unique = true)
    private String username;

    @Column(name = "uwd2")
    private String password;

    @Column(name = "sys")
    private String userdb;

    @Column(name = "state")
    private boolean state;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "uaa_codigo")
    @NotFound(action = NotFoundAction.IGNORE)
    private Uaa uaa;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "per_codigo")
    @NotFound(action = NotFoundAction.IGNORE)
    private Persona persona;

    @Column(name = "gru_id")
    private String role;

    public String getUserdb() {
        return userdb;
    }

    public void setUserdb(String userdb) {
        this.userdb = userdb;
    }

    private static final long serialVersionUID = 1L;
}
