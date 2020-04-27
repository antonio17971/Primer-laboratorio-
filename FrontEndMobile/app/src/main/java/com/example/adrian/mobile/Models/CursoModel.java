package com.example.adrian.mobile.Models;

import java.io.Serializable;

public final class CursoModel implements Serializable {

    // Atributos privados de la clase Curso
    private int codigo;
    private int creditos;
    private int horas;

    private String nombre;
    private String anho;
    private String ciclo;

    public CursoModel(int codigo, int creditos, int horas, String nombre, String anho, String ciclo) {
        this.codigo = codigo;
        this.horas = horas;
        this.creditos = creditos;
        this.nombre = nombre;
        this.anho = anho;
        this.ciclo = ciclo;
    }

    public CursoModel(int codigo, int creditos, int horas, String nombre) {
        this.codigo = codigo;
        this.horas = horas;
        this.creditos = creditos;
        this.nombre = nombre;
        this.anho = "";
        this.ciclo = "";
    }

    public CursoModel() {
        this.codigo = 0;
        this.horas = 0;
        this.creditos = 0;
        this.nombre = new String();
        this.anho = new String();
        this.ciclo = new String();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public CursoModel setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public int getCreditos() {
        return this.creditos;
    }

    public CursoModel setCreditos(int creditos) {
        this.creditos = creditos;
        return this;
    }

    public int getHoras() {
        return this.horas;
    }

    public CursoModel setHoras(int horas) {
        this.horas = horas;
        return this;
    }

    public String getNombre() {
        return this.nombre;
    }

    public CursoModel setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getAnho() {
        return this.anho;
    }

    public CursoModel setAnho(String anho) {
        this.anho = anho;
        return this;
    }

    public String getCiclo() {
        return this.ciclo;
    }

    public CursoModel setCiclo(String ciclo) {
        this.ciclo = ciclo;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Curso{codigo=%d, creditos=%d, horas=%d, nombre=%s, año=%s, ciclo=%s}",
                this.getCodigo(),
                this.getCreditos(),
                this.getHoras(),
                this.getNombre(),
                this.getAnho(),
                this.getCiclo());
    }

}