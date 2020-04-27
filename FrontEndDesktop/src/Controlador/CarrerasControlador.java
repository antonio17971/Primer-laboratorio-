/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.mobiles.backend.Control.Control;
import com.mobiles.backend.Entidades.Carrera;
import Main.Main;
import Modelo.CarrerasModel;
import Vista.CarreraVista;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CarrerasControlador {
    Control control;
    CarreraVista view;
    CarrerasModel model;
 
    public CarrerasControlador(CarreraVista view, CarrerasModel model, Control control) {
        model.init();
        this.control= control;
        
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void preAgregar(){       
        Main.CARRERA_VIEW.getModel().clearErrors();
        Main.CARRERA_VIEW.getModel().setModo(FrontEndDesktop.MODO_AGREGAR);
        Main.CARRERA_VIEW.getModel().setCurrent(new Carrera());
        Main.CARRERA_VIEW.setVisible(true);
        Main.CARRERA_VIEW.toFront();
        
    }
        
    public void editar(int row){
        Main.CARRERA_VIEW.getModel().clearErrors();
        Carrera seleccionada = model.getCarreras().getRowAt(row); 
        Main.CARRERA_VIEW.getModel().setModo(FrontEndDesktop.MODO_EDITAR);
        Main.CARRERA_VIEW.getModel().setCurrent(seleccionada);
        Main.CARRERA_VIEW.setVisible(true);
        Main.CARRERA_VIEW.toFront();
    }
        
    public void Listar() throws GlobalException, NoDataException{
          List<Carrera> rows = (List<Carrera>) control.listarCarreras();
          model.setCarreras(rows);
      }
    
    public void buscarCarreraCodigo() throws GlobalException, NoDataException{
        model.getFilter().setCodigo(view.busFld.getText());
        model.clearErrors();
        List<Carrera> rows = control.buscarCarreraCodigo(model.getFilter().getCodigo());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCarreras(rows);
    }
    
    public void buscarCarreraNombre() throws GlobalException, NoDataException{
        model.getFilter().setNombre(view.busFld.getText());
        model.clearErrors();
        List<Carrera> rows = control.buscarCarreraNombre(model.getFilter().getNombre());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCarreras(rows);
    }

    
    public void eliminarCarrera(int row) throws GlobalException, NoDataException
    {
        try 
        {
            control.eliminarCarrera(model.getCarreras().getRowAt(row).getCodigo()); 
        } 
        catch (Exception ex) { }
        this.Listar();
    }
}
