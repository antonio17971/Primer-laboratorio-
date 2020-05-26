/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Control;

import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import com.mobiles.backend.Entidades.*;
import com.mobiles.backend.Modelo.ModeloCarrera;
import com.mobiles.backend.Modelo.ModeloCurso;
import java.util.Collection;

public class Control {

    private static Control instance;
    private final ModeloCarrera modeloCarrera;
    private final ModeloCurso modeloCurso;

    private Control() {
        this.modeloCarrera = ModeloCarrera.getInstance();
        this.modeloCurso = ModeloCurso.getInstance();
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    //****************************************************
    // INICIO BLOQUE PARA CONTROL DE CURSOS
    //****************************************************
    public Collection listarCursos() throws GlobalException, NoDataException {
        return this.modeloCurso.listarCursos();
    }

    public Collection buscarCurso(int codigoCurso) throws GlobalException, NoDataException {
        return this.modeloCurso.buscarCurso(codigoCurso);
    }

    public Collection buscarCurso(String nombreCodigo) throws GlobalException, NoDataException {
        return this.modeloCurso.buscarCurso(nombreCodigo);
    }

    public void borrarCurso(int codigoCurso) throws GlobalException, NoDataException {
        this.modeloCurso.borrarCurso(codigoCurso);
    }

    public Curso insertarCurso(Curso curso) throws GlobalException, NoDataException {
        return this.modeloCurso.insertarCurso(curso);
    }

    public Curso actualizarCurso(Curso curso) throws GlobalException, NoDataException {
        return this.modeloCurso.actualizarCurso(curso);
    }
    //****************************************************
    // FIN BLOQUE PARA CONTROL DE CURSOS
    //****************************************************

    //****************************************************
    // INICIO BLOQUE PARA CONTROL DE CARRERAS
    //****************************************************
    public Collection listarCarreras() throws GlobalException, NoDataException {
        return this.modeloCarrera.listarCarreras();
    }

    public Collection listarCursosCarrera(int codigoCarrera) throws GlobalException, NoDataException {
        return this.modeloCarrera.listarCursosCarrera(codigoCarrera);
    }

    public Collection buscarCarrera(int codigoCarrera) throws GlobalException, NoDataException {
        return this.modeloCarrera.buscarCarrera(codigoCarrera);
    }

    public Collection buscarCarrera(String nombreCarrera) throws GlobalException, NoDataException {
        return this.modeloCarrera.buscarCarrera(nombreCarrera);
    }

    public void borrarCarrera(int codigoCarrera) throws GlobalException, NoDataException {
        this.modeloCarrera.borrarCarrera(codigoCarrera);
    }

    public void borrarCarreraCurso(int codCarrera, int codCurso) throws GlobalException, NoDataException {
        this.modeloCarrera.borrarCarreraCurso(codCarrera, codCurso);
    }

    public Carrera insertarCarrera(Carrera carrera) throws GlobalException, NoDataException {
        return this.modeloCarrera.insertarCarrera(carrera);
    }

    public Carrera actualizarCarrera(Carrera carrera) throws GlobalException, NoDataException {
        return this.modeloCarrera.actualizarCarrera(carrera);
    }
    //****************************************************
    // FIN BLOQUE PARA CONTROL DE CARRERAS
    //****************************************************
}
