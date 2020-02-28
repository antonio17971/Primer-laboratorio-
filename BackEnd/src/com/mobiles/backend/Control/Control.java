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

    private Control() {
        this.servicioCarrera = new ServicioCarrera();
        this.servicioCurso = new ServicioCurso();
    }
    
    private ServicioCarrera getServicioCarrera(){
        return this.servicioCarrera;
    }
    
    private ServicioCurso getServicioCurso(){
        return this.servicioCurso;
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }
    
    public Collection listarCursos() throws GlobalException, NoDataException{
        return this.getServicioCurso().listar_curso();
    }
    
    public Collection buscarCursoCodigo(int codigoCurso) throws GlobalException, NoDataException{
        return this.getServicioCurso().buscar_curso(codigoCurso);
    }
    
    public void borrarCurso(int codigoCurso) throws GlobalException, NoDataException{
        this.getServicioCurso().borrar_curso(codigoCurso);
    }
    
    public Collection buscarCursoNombre(String nombreCodigo) throws GlobalException, NoDataException{
        return this.getServicioCurso().buscar_curso_nombre(nombreCodigo);
    }
    
    public Curso insertarCurso(Curso curso) throws GlobalException, NoDataException{
        return this.getServicioCurso().insertar_curso(curso);
    }

}
