package Modelo;

import com.mobiles.backend.Entidades.Curso;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class CursoModel extends java.util.Observable {

    private CursosTableModel cursos;
    private String mensaje;
    private Curso filter;
    private HashMap<String, String> errores;

    public CursoModel() {
    }

    private void changed() {
        this.setChanged();
        this.notifyObservers();
    }

    public void setCursos(List<Curso> cursos) {
        int[] cols = {
            CursosTableModel.CODIGO,
            CursosTableModel.NOMBRE,
            CursosTableModel.CREDITOS,
            CursosTableModel.HORAS
        };
        this.cursos = new CursosTableModel(cols, cursos);
        this.changed();
    }

    public CursosTableModel getCursos() {
        return this.cursos;
    }

    public void setFilter(Curso filter) {
        this.filter = filter;
    }

    public Curso getFilter() {
        return this.filter;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }

    public HashMap<String, String> getErrores() {
        return this.errores;
    }

    public void clearErrores() {
        this.setErrores(new HashMap<>());
        this.setMensaje("");
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.changed();
    }

}
