package com.mobile.frontend.data.model;

import android.annotation.SuppressLint;

import java.io.CharArrayReader;
import java.util.ArrayList;

public final class CarreraModel {

    private int codigo;
    private String nombre;
    private String titulo;
    private ArrayList<CursoModel> cursos;

    public CarreraModel(int codigo, String nombre, String titulo, ArrayList<CursoModel> cursos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = cursos;
    }

    public CarreraModel(int codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = new ArrayList<CursoModel>();
    }

    public CarreraModel() {
        this.codigo = 0;
        this.nombre = "";
        this.titulo = "";
        this.cursos = new ArrayList<CursoModel>();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public CarreraModel setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getNombre() {
        return this.nombre;
    }

    public CarreraModel setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public CarreraModel setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public ArrayList<CursoModel> getCuros(){
        return this.cursos;
    }

    public CarreraModel setCursos(ArrayList<CursoModel> cursos){
        this.cursos = cursos;
        return this;
    }

    public CarreraModel addCurso(CursoModel curso){
        this.cursos.add(curso);
        return this;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("Carrera{codigo=%d, nombre=%s, titulo=%s}",
                this.getCodigo(),
                this.getNombre(),
                this.getTitulo());
    }

}
