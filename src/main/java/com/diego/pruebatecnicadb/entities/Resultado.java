package com.diego.pruebatecnicadb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cadenaIngresada;
    private boolean resultado;


    public Resultado() {
    }

    public Resultado(String cadenaIngresada, boolean resultado) {
        this.cadenaIngresada = cadenaIngresada;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCadenaIngresada() {
        return cadenaIngresada;
    }

    public void setCadenaIngresada(String cadenaIngresada) {
        this.cadenaIngresada = cadenaIngresada;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
}
