/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.AccesoADatos;

import com.mobiles.backend.Entidades.Curso;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioCurso extends Servicio {

    private static final String INSERTAR_CURSO = "{call SP_INSERTACURSOS(?,?,?,?)}";
    private static final String MODIFICAR_CURSO = "{call SP_UPDATECURSOS(?,?,?,?)}";
    private static final String BUSCAR_CURSO = "{?=call BUSCAR_CURSO(?)}";
    private static final String BUSCAR_CURSO_NOMBRE = "{?=call BUSCAR_CURSO_NOMBRE(?)}";
    private static final String LISTAR_CURSOS = "{?=call LISTAR_CURSOS()}";
    private static final String BORRAR_CURSO = "{call SP_DELETECURSOS(?)}";

    public ServicioCurso() {
        super();
    }

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
        Curso elCurso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_CURSOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(
                        rs.getInt("CODIGO"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS"),
                        rs.getString("NOMBRE"),
                        rs.getString("ANHO"),
                        rs.getString("CICLO"));

                coleccion.add(elCurso);
            }
        } catch (SQLException e) {
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
        if (coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public Curso insertar_curso(Curso curso) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTAR_CURSO);
            pstmt.setInt(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }

        } catch (SQLException e) {
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return curso;
    }

    public Collection buscar_curso(int id) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso elCurso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(BUSCAR_CURSO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(
                        rs.getInt("CODIGO"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS"),
                        rs.getString("NOMBRE"),
                        rs.getString("ANHO"),
                        rs.getString("CICLO"));
                coleccion.add(elCurso);
            }
        } catch (SQLException e) {
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
        if (coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public Collection buscar_curso(String nombre) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso elCurso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(BUSCAR_CURSO_NOMBRE);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(
                        rs.getInt("CODIGO"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS"),
                        rs.getString("NOMBRE"),
                        rs.getString("ANHO"),
                        rs.getString("CICLO"));
                coleccion.add(elCurso);
            }
        } catch (SQLException e) {
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
        if (coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public Curso update_curso(Curso curso) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(MODIFICAR_CURSO);
            pstmt.setInt(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras());
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return curso;
    }

    public void borrar_curso(int id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(BORRAR_CURSO);
            pstmt.setInt(1, id);

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
}
