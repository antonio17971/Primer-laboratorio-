/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviles.backend.Modelo;

import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import com.mobiles.backend.AccesoADatos.ServicioCurso;
import com.mobiles.backend.Entidades.Curso;
import java.util.Collection;

/**
 *
 * @author jose1
 */
public class ModeloCurso {
    
    private static ModeloCurso instance;
    private ServicioCurso curso;

    public ModeloCurso() {
        this.curso = new ServicioCurso();
    }

    public ModeloCurso(ServicioCurso curso) {
        this.curso = curso;
    }
    
    public static ModeloCurso getInstance() {
        if (instance == null) {
            instance = new ModeloCurso();
        }
        return instance;
    }
    
     private ServicioCurso getServicioCurso() {
        return this.curso;
    }
     
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
    
}
