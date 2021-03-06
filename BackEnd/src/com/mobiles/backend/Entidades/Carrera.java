/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Entidades;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author adria
 */
public class Carrera {

    private int codigo;
    private String nombre;
    private String titulo;
    private Collection cursos;

    public Carrera(int codigo, String nombre, String titulo, Collection cursos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = cursos;
    }
    
    public Carrera(int codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = new ArrayList();
    }

    public Carrera() {
        this.codigo = 0;
        this.nombre = new String();
        this.titulo = new String();
        this.cursos = new ArrayList() {
        };
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public Collection getCuros(){
        return this.cursos;
    }
    
    public void setCursos(Collection cursos){
        this.cursos = cursos;
    }
    
    public void addCurso(Curso curso){
        this.cursos.add(curso);
    }

    @Override
    public String toString() {
        return String.format("Carrera{codigo=%d, nombre=%s, titulo=%s}",
                this.getCodigo(),
                this.getNombre(),
                this.getTitulo());
    }

}
