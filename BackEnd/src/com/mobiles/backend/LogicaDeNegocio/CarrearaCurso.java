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
public class CarrearaCurso {
    private int codCarrera,codCurso;
    private String ano,ciclo;

    public int getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(int codCarrera) {
        this.codCarrera = codCarrera;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "CarrearaCurso{" + "codCarrera=" + codCarrera + ", codCurso=" + codCurso + ", ano=" + ano + ", ciclo=" + ciclo + '}';
    }
    
}
