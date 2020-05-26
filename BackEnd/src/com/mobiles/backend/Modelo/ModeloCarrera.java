/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Modelo;

import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import com.mobiles.backend.AccesoADatos.ServicioCarrera;
import com.mobiles.backend.Entidades.Carrera;
import java.util.Collection;

/**
 *
 * @author jose1
 */
public class ModeloCarrera {
    
    private static ModeloCarrera instance;
    private ServicioCarrera carrera;

    public ModeloCarrera(ServicioCarrera carrera) {
        this.carrera = carrera;
    }

    public ModeloCarrera() {
         this.carrera = new ServicioCarrera();
    }
    
    public static ModeloCarrera getInstance() {
        if (instance == null) {
            instance = new ModeloCarrera();
        }
        return instance;
    }
    
    
     private ServicioCarrera getServicioCarrera() {
        return this.carrera;
    }
     
     //****************************************************
    // INICIO BLOQUE PARA CONTROL DE CARRERAS
    //****************************************************
    public Collection listarCarreras() throws GlobalException, NoDataException {
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
}
