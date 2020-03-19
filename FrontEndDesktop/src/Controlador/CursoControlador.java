/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.mobiles.backend.Control.Control;
import com.mobiles.backend.Entidades.Carrera;
import com.mobiles.backend.Entidades.Curso;
import Main.Main;
import Modelo.CursoModel;
import Modelo.CursosModel;
import Vista.CursoVista;
import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import java.util.List;

/**
 *
 * @author Usuario1
 */
public class CursoControlador {

    Control control;
    CursoVista view;
    CursoModel model;

    public CursoControlador(CursoVista view, CursoModel model, Control control) throws GlobalException, NoDataException {
        model.init();
        this.control = control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Control getControl() {
        return control;
    }

    public void guardar() {
        CursosModel cursosModel = Main.getCursosVista().getModel();

        Curso nueva = new Curso();
        model.clearErrors();

        nueva.setCodigo(view.codigoFld.getText());
        if (view.codigoFld.getText().length() == 0) {
            model.getErrores().put("CODIGO", "Codigo requerido");
        }
        nueva.setNombre(view.nombreFld.getText());
        if (view.nombreFld.getText().length() == 0) {
            model.getErrores().put("NOMBRE", "Nombre requerido");
        }
        nueva.setCreditos(Integer.parseInt(view.creditosFld.getText()));
        if (view.creditosFld.getText().length() == 0) {
            model.getErrores().put("CREDITOS", "Creditos requerido");
        }
        nueva.setHoras(view.horasFld.getText());
        if (view.horasFld.getText().length() == 0) {
            model.getErrores().put("HORAS", "Horas requerido");
        }

        nueva.setCodigo_carrera((Carrera) view.carreraCmbBox.getSelectedItem());
        nueva.setCodigo_ciclo((Ciclo) view.cicloCmbBox.getSelectedItem());

        List<Curso> cursos;
        if (model.getErrores().isEmpty()) {
            try {
                switch (model.getModo()) {
                    case Main.MODO_AGREGAR:
                        control.insertarCurso(nueva);
                        model.setMensaje("CURSO AGREGADO");
                        model.setCurrent(new Curso());
                        cursos = control.listarCursos();
                        cursosModel.setCursos(cursos);
                        //instrumento = domainModel.searchInstrumento(instrumentosModel.getFilter());
                        //instrumentosModel.setInstrumentos(instrumento);   
                        view.setVisible(false);
                        break;
                    case FrontEndDesktop.MODO_EDITAR:
                        control.modificarCurso(nueva);
                        model.setMensaje("CURSO MODIFICADO");
                        model.setCurrent(nueva);
                        cursos = control.listarCursos();
                        cursosModel.setCursos(cursos);
                        //instrumento = domainModel.searchInstrumento(instrumentosModel.getFilter());
                        //instrumentosModel.setInstrumentos(instrumento);
                        view.setVisible(false);
                        break;
                }
            } catch (Exception e) {
                model.getErrores().put("id", "CURSO YA EXISTE");
                model.setMensaje("CURSO YA EXISTE");
                model.setCurrent(nueva);
            }
        } else {
            model.setMensaje("ESPACIOS INCOMPLETOS.");
            model.setCurrent(nueva);
        }
    }

    public void refreshComboBox() throws GlobalException, NoDataException {
        model.init(control.listarCarreras().toArray(new Carrera[0]), control.listarCiclos().toArray(new Ciclo[0]));
    }

}
