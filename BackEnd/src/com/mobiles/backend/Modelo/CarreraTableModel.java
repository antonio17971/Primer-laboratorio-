/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.Modelo;
import com.mobiles.backend.LogicaDeNegocio.Carrera;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author jose1
 */
public class CarreraTableModel extends AbstractTableModel{
    
    List<Carrera> rows;
    int[] cols;

    public static final int CODIGO_CARRERA = 0;
    public static final int NOMBRE = 1;
    public static final int TITULO = 2;

    String[] colNames = new String[3];

    public CarreraTableModel(int[] cols, List<Carrera> rows) {
        this.cols = cols;
        this.rows = rows;
        initColNames();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[cols[col]];
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Carrera carrera = rows.get(row);
        switch (cols[col]) {
            case CODIGO_CARRERA:
                return carrera.getCodigo();
            case NOMBRE:
                return carrera.getNombre();
            case TITULO:
                return carrera.getTitulo();
            default:
                return "";
        }
    }

    public Carrera getRowAt(int row) {
        return rows.get(row);
    }

    private void initColNames() {
        colNames[CODIGO_CARRERA] = "Codigo Carrera";
        colNames[NOMBRE] = "Nombre";
        colNames[TITULO] = "Titulo";
    }
}
