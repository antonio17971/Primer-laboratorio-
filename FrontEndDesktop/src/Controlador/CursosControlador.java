package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import com.mobiles.backend.Control.Control;
import com.mobiles.backend.Entidades.Curso;
import Main.FrontEndDesktop;
import Model.CursosModel;
import View.CursosView;
import java.util.List;


public class CursosControlador {

    Control control;
    CursosVista view;
    CursosModel model;
    
    public CursosControlador(CursosView view, CursosModel model, Control control) {
        model.init();
        this.control= control;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void preAgregar(){       
        FrontEndDesktop.CURSO_VIEW.getModel().clearErrors();
        FrontEndDesktop.CURSO_VIEW.getModel().setModo(FrontEndDesktop.MODO_AGREGAR);
        FrontEndDesktop.CURSO_VIEW.getModel().setCurrent(new Curso());
        FrontEndDesktop.CURSO_VIEW.setVisible(true);
        FrontEndDesktop.CURSO_VIEW.toFront();
        
    }
        
    public void editar(int row){
        FrontEndDesktop.CURSO_VIEW.getModel().clearErrors();
        Curso seleccionada = model.getCursos().getRowAt(row); 
        FrontEndDesktop.CURSO_VIEW.getModel().setModo(FrontEndDesktop.MODO_EDITAR);
        FrontEndDesktop.CURSO_VIEW.getModel().setCurrent(seleccionada);
        FrontEndDesktop.CURSO_VIEW.setVisible(true);
        FrontEndDesktop.CURSO_VIEW.toFront();
    }
        
    public void listar() throws GlobalException, NoDataException{
          List<Curso> rows = (List<Curso>) control.listarCursos();
          model.setCursos(rows);
      }
    
    public void buscarCursoCodigo() throws GlobalException, NoDataException{
        model.getFilter().setCodigo(view.busFld.getText());
        model.clearErrors();
        List<Curso> rows = control.buscarCurso(model.getFilter().getCodigo());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCursos(rows);
    }
    
    public void buscarCursosNombre() throws GlobalException, NoDataException{
        model.getFilter().setNombre(view.busFld.getText());
        model.clearErrors();
        List<Curso> rows = control.buscarCursoNombre(model.getFilter().getNombre());
        if (rows == null)
        {
            model.getErrores().put("busFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCursos(rows);
    }

    
    public void eliminarCurso(int row) throws GlobalException, NoDataException
    {
        try 
        {
            control.eliminarCurso(model.getCursos().getRowAt(row).getCodigo()); 
        } 
        catch (Exception ex) { }
        this.listar();
    }
    
}
