/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mobiles.backend.Entidades.Carrera;
import com.mobiles.backend.Entidades.Curso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Usuario1
 */
public class CursoModel extends java.util.Observable{
    Curso current;
    ComboBoxModel<Carrera> carreras;
    HashMap<String,String> errores;
    String mensaje;
    int modo;
    
    public void init(){
        Carrera[] rows = new Carrera[0] ;
        setCarreras(rows);
        setCurrent(new Curso());
        clearErrors();
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<String,String>());
        setMensaje("");
        
    }
    public Curso getCurrent() {
        return current;
    }

    public void setCurrent(Curso current) {
        this.current = current;
        setChanged();
        notifyObservers();        
    }

    public ComboBoxModel<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(Carrera[] carreras) {
        this.carreras = new DefaultComboBoxModel(carreras);
        setChanged();
        notifyObservers();        
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
    
}
