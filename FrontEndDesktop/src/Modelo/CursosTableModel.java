/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mobiles.backend.Entidades.Curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CursosTableModel extends AbstractTableModel {

    // ------------------------ INICIO ATRIBUTOS -------------------------------
    public static final int CODIGO = 0;
    public static final int NOMBRE = 1;
    public static final int CREDITOS = 2;
    public static final int HORAS = 3;
    private final List<Curso> rows;
    private final String[] colNames = new String[4];
    private final int[] cols;
    // ------------------------ FIN ATRIBUTOS ----------------------------------

    // ------------------------ INICIO METODOS PRIVADOS ------------------------
    private void setColName(int index, String name) {
        this.colNames[index] = name;
    }

    private void initColNames() {
        this.setColName(CODIGO, "CODIGO");
        this.setColName(NOMBRE, "NOMBRE");
        this.setColName(CREDITOS, "CREDITOS");
        this.setColName(HORAS, "HORAS");
    }
    // ------------------------ FIN METODOS PRIVADOS ---------------------------

    // ------------------------ INICIO METODOS PUBLICOS ------------------------
    public CursosTableModel(int[] cols, List<Curso> rows) {
        this.cols = cols;
        this.rows = rows;
        this.initColNames();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    public String getColName(int columnIndex) {
        return this.colNames[this.cols[columnIndex % this.cols.length]];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = rows.get(rowIndex);
        switch (cols[columnIndex]) {
            case CODIGO:
                return curso.getCodigo();
            case NOMBRE:
                return curso.getNombre();
            case CREDITOS:
                return curso.getCreditos();
            case HORAS:
                return curso.getHoras();
            default:
                return null;
        }
    }
    // ------------------------ FIN METODOS PUBLICOS ---------------------------
}
