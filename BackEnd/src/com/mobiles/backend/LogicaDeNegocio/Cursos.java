/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.LogicaDeNegocio;

/**
 *
 * @author jose1
 */
public class Cursos {
    private int codigo,creditos,horas;
    private String nombre;
    
    public Cursos(int codigo, int horas, String nombre) {
        this.codigo = codigo;
        this.horas = horas;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cursos{" + "codigo=" + codigo + ", creditos=" + creditos + ", horas=" + horas + ", nombre=" + nombre + '}';
    }
    
}
