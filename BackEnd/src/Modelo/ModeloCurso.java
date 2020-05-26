/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mobiles.backend.AccesoADatos.ServicioCurso;

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
    
}
