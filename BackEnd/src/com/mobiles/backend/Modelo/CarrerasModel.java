/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Modelo;
import com.mobiles.backend.LogicaDeNegocio.Carreras;
import java.util.*;
/**
 *
 * @author jose1
 */
public class CarrerasModel extends Observable{
    Carreras filter;
    CarreraTableModel carreras;
    HashMap<String,String> errores;
    String mensaje;
    
    public CarrerasModel(){
    }

    public void init(){
        
        filter = new Carreras();
        List<Carreras> filas = new ArrayList<>();
        this.setCarrera(filas);
        clearErrors();
    }
    
    public void setCarrera(List<Carreras> carreras){
        
        int[] cols={CarreraTableModel.CODIGO_CARRERA, CarreraTableModel.NOMBRE, CarreraTableModel.TITULO};
        this.carreras = new CarreraTableModel(cols, carreras);  
        setChanged();
        notifyObservers();        
    }
    
    public Carreras getFilter(){
        return filter;
    }
    
    public void setFilter(Carreras filter){
        this.filter = filter;
    }
    
     public CarreraTableModel getCarreras(){
        return carreras;
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }

    public String getMensaje(){
        return mensaje;
    }

    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores(){
        return errores;
    }

    public void setErrores(HashMap<String, String> errores){
        this.errores = errores;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<>());
        setMensaje(""); 
    }
}
