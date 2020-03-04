/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Control;

import com.mobiles.backend.Entidades.*;
import com.mobiles.backend.AccesoADatos.*;
import java.util.Collection;

public class Control {

    private static Control instance;
    private final ServicioCarrera servicioCarrera;
    private final ServicioCurso servicioCurso;

    public Control() {
        this.servicioCarrera = new ServicioCarrera();
        this.servicioCurso = new ServicioCurso();
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    //****************************************************
    // INICION METODOS GET PARA LOS SERVICIOS
    //****************************************************
    private ServicioCarrera getServicioCarrera() {
        return this.servicioCarrera;
    }

    private ServicioCurso getServicioCurso() {
        return this.servicioCurso;
    }
    //****************************************************
    // FIN METODOS GET PARA LOS SERVICIOS
    //****************************************************

    //****************************************************
    // INICIO BLOQUE PARA CONTROL DE CURSOS
    //****************************************************
    public Collection listarCursos() throws GlobalException, NoDataException {
        return this.getServicioCurso().listar_curso();
    }

    public Collection buscarCurso(int codigoCurso) throws GlobalException, NoDataException {
        return this.getServicioCurso().buscar_curso(codigoCurso);
    }

    public Collection buscarCurso(String nombreCodigo) throws GlobalException, NoDataException {
        return this.getServicioCurso().buscar_curso(nombreCodigo);
    }

    public void borrarCurso(int codigoCurso) throws GlobalException, NoDataException {
        this.getServicioCurso().borrar_curso(codigoCurso);
    }

    public Curso insertarCurso(Curso curso) throws GlobalException, NoDataException {
        return this.getServicioCurso().insertar_curso(curso);
    }

    public Curso actualizarCurso(Curso curso) throws GlobalException, NoDataException {
        return this.getServicioCurso().update_curso(curso);
    }
    //****************************************************
    // FIN BLOQUE PARA CONTROL DE CURSOS
    //****************************************************

    //****************************************************
    // INICIO BLOQUE PARA CONTROL DE CARRERAS
    //****************************************************
    public Collection liscarCarreras() throws GlobalException, NoDataException {
        return this.getServicioCarrera().listar_carrera();
    }

    public Collection listarCursosCarrera(int codigoCarrera) throws GlobalException, NoDataException {
        return this.getServicioCarrera().buscar_carrera_cursos(codigoCarrera);
    }

    public Collection buscarCarrera(int codigoCarrera) throws GlobalException, NoDataException {
        return this.getServicioCarrera().buscar_carrera(codigoCarrera);
    }

    public Collection buscarCarrera(String nombreCarrera) throws GlobalException, NoDataException {
        return this.getServicioCarrera().buscar_carrera(nombreCarrera);
    }

    public void borrarCarrera(int codigoCarrera) throws GlobalException, NoDataException {
        this.getServicioCarrera().borrar_carrera(codigoCarrera);
    }

    public void borrarCarreraCurso(int codCarrera, int codCurso) throws GlobalException, NoDataException {
        this.getServicioCarrera().borrar_carrera_curso(codCarrera, codCurso);
    }

    public Carrera insertarCarrera(Carrera carrera) throws GlobalException, NoDataException {
        return this.getServicioCarrera().insertar_carrera(carrera);
    }

    public Carrera actualizarCarrera(Carrera carrera) throws GlobalException, NoDataException {
        return this.getServicioCarrera().update_carrera(carrera);
    }
    //****************************************************
    // FIN BLOQUE PARA CONTROL DE CARRERAS
    //****************************************************

}
