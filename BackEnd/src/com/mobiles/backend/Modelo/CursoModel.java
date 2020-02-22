/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Modelo;
import com.mobiles.backend.LogicaDeNegocio.Cursos;
import java.util.*;
/**
 *
 * @author jose1
 */
public class CursoModel extends Observable{
    Cursos current;
    HashMap<String,String> errores;
    String mensaje;
    int modo;  

    public Cursos getCurrent() {
        return current;
    }

    public void setCurrent(Cursos current) {
        this.current = current;
        setChanged();
        notifyObservers(); 
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
     @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        setChanged();
        notifyObservers();
    } 
    
}
