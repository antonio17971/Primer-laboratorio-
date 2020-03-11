/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CursoModel;
import Vista.MainVista;

public class CursoControlador {
    
    private final CursoModel cursoModel;
    private final MainVista mainVista;
    
    public CursoControlador(MainVista mainVista, CursoModel cursoModel){
        this.cursoModel = cursoModel;
        this.mainVista = mainVista;
        this.mainVista.setModel(cursoModel);
        this.mainVista.setController(this);
    }
   
}
