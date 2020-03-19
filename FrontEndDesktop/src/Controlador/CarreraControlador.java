/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.mobiles.backend.Control.Control;
import com.mobiles.backend.Entidades.Carrera;
import Main.Main;
import Modelo.CarreraModel;
import Modelo.CarrerasModel;
import Vista.CarreraVista;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CarreraControlador {
    Control control;
    CarreraVista view;
    CarreraModel model;
    
    public CarreraControlador(CarreraVista view, CarreraModel model, Control control) 
    {
        //model.init(domainModel.getTipoInstrumentos().toArray(new TipoInstrumento[0]));
        model.init();
        this.control= control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Control getControl() {
        return control;
    }
    
    
     public void guardar(){
        CarrerasModel carrerasModel = Main.CARRERAS_VIEW.getModel();

        Carrera nueva = new Carrera();
        model.clearErrors();
        
        nueva.setCodigo(view.codigoFld.getText());
        if (view.codigoFld.getText().length()==0){
            model.getErrores().put("Codigo", "Codigo requerido");
        }
        nueva.setNombre(view.nombreFld.getText());
        if (view.nombreFld.getText().length()==0){
            model.getErrores().put("Nombre", "Nombre requerido");
        }
        nueva.setTitulo(view.tituloFld.getText());
        if (view.tituloFld.getText().length()==0){
            model.getErrores().put("Titulo", "Titulo requerido");
        }          

        
       // nueva.setTipoInstrumento((TipoInstrumento) view.tipoCmbBox.getSelectedItem());
        
        List<Carrera> carreras;
        if (model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                    case Main.MODO_AGREGAR:
                        control.insertarCarrera(nueva);
                        model.setMensaje("CARRERA AGREGADA");
                        model.setCurrent(new Carrera());                       
                        carreras = (List<Carrera>) control.listarCarreras();
                        carrerasModel.setCarreras(carreras);
                       // carreras = control.buscarCarrera(carrerasModel.getFilter());
                       // carrerasModel.setCarreras(carreras);   
                        view.setVisible(false);
                        break;
                    
                    case Main.MODO_EDITAR:
                        control.modificarCarrera(nueva);
                        model.setMensaje("CARRERA MODIFICADO");
                        model.setCurrent(nueva);
                        carreras = (List<Carrera>) control.listarCarreras();
                        carrerasModel.setCarreras(carreras);
                        //instrumento = domainModel.searchInstrumento(instrumentosModel.getFilter());
                        //instrumentosModel.setInstrumentos(instrumento);
                        view.setVisible(false);
                        break;
                }
            }
            catch(Exception e){
                model.getErrores().put("codigo", "CARRERA YA EXISTE");
                model.setMensaje("CARRERA YA EXISTE");
                model.setCurrent(nueva);
            }
        }
        else{
            model.setMensaje("ESPACIOS INCOMPLETOS.");
            model.setCurrent(nueva);
        }
    }
    
}
