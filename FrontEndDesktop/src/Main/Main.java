/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.CarreraControlador;
import Controlador.CursosControlador;
import Modelo.CarrerasModel;
import Modelo.CursosModel;
import Vista.CarrerasVista;
import Vista.CursoVista;
import Vista.CursosVista;
import Vista.MainVista;
import com.mobiles.backend.Control.Control;

/**
 *
 * @author adria
 */
public class Main {
    
    public static CarrerasVista CARRERAS_VISTA;
    public static CursosVista CURSOS_VISTA;
    public static CursoVista CURSO_VISTA;
    
    public static CarrerasVista getCarrerasVista(){
        return CARRERAS_VISTA;
    }
    
    public static CursosVista getCursosVista(){
        return CURSOS_VISTA;
    }
    
    public static CursoVista getCursoVista(){
        return CURSO_VISTA;
    }
   
    public static void main(String[] args){
        
        // ------------------- INICIAMOS EL CONTROL DEL BACKEND ----------------
        Control contro = Control.getInstance();
        
        
        // ------------------- INICIOAMOS COMPONENTES DE CARRERAS --------------
        CarrerasVista carrerasVista = new CarrerasVista();
        CarrerasModel carrerasModel = new CarrerasModel();
        CarrerasController carrerasController = new CarrerasController(contro, carrerasVista, carrerasModel);
        CARRERAS_VISTA = carrerasVista;
        
        // ------------------- INICIOAMOS COMPONENTES DE CURSOS ----------------
        CursosVista cursosVista = new CursosVista();
        CursosModel cursosModel = new CursosModel();
        CursosControlador cursosController = new CursosControlador(cursosVista, cursosModel);
        CURSOS_VISTA = cursosVista;
        
        MainVista mainVista = new MainVista(carrerasVista, cursosVista);
        mainVista.setVisible(true);
        
//        CursosVista mainView = new CursosVista();
//        CursosModel cursoModel = new CursosModel();
//        CursosControlador cursoController = new CursosControlador(mainView, cursoModel);
//        mainView.setVisible(true);
    }
}
