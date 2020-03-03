/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Entidades;

/**
 *
 * @author adria
 */
public class Curso {

    // Atributos privados de la clase Curso
    private int codigo;
    private int creditos;
    private int horas;

    private String nombre;
    private String anho;
    private String ciclo;

    public Curso(int codigo, int creditos, int horas, String nombre, String anho, String ciclo) {
        this.codigo = codigo;
        this.horas = horas;
        this.creditos = creditos;
        this.nombre = nombre;
        this.anho = anho;
        this.ciclo = ciclo;
    }

    public Curso() {
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

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return this.creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return this.horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnho() {
        return this.anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getCiclo() {
        return this.ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
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
