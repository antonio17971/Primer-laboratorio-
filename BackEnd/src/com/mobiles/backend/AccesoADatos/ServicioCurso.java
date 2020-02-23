/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.AccesoADatos;

import com.mobiles.backend.LogicaDeNegocio.Cursos;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioCurso extends Servicio{
    
    private static final String INSERTAR_CURSO = "{call SP_INSERTACURSOS(?,?,?,?)}";
    private static final String MODIFICAR_CURSO = "{call SP_UPDATECURSOS(?,?,?,?)}";
    
    // Validar, aun no exisen en la BD
    private static final String BUSCAR_CURSO = "{?=call SP_BUSCARCURSO(?,?)}";
    private static final String LISTAR_CURSOS = "{?=call SP_LISTARCURSOS()}";
    
    private static final String BORRAR_CURSO = "{call SP_BORRARCURSO(?)}";
   
     /*Listar alumnos*/
    public Collection listar_curso() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Cursos elCurso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_CURSOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Cursos(rs.getInt("CODIGO"),
                        rs.getInt("HORAS"),
                        rs.getString("NOMBRE"));
                coleccion.add(elCurso);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
}
