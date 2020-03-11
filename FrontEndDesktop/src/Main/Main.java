/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.CursoControlador;
import Modelo.CursoModel;
import Vista.MainVista;

/**
 *
 * @author adria
 */
public class Main {
   
    public static void main(String[] args){
        MainVista mainView = new MainVista();
        mainView.setVisible(true);
        CursoModel cursoModel = new CursoModel();
        CursoControlador cursoController = new CursoControlador(mainView, cursoModel);
        
    }
}
