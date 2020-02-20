/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiles.backend.AccesoADatos;

public class ServicioCurso extends Servicio{
    
    private static final String INSERTAR_CURSO = "{call SP_INSERTACURSOS(?,?,?,?)}";
    private static final String MODIFICAR_CURSO = "{call SP_UPDATECURSOS(?,?,?,?)}";
    
    // Validar, aun no exisen en la BD
    private static final String BUSCAR_CURSO = "{?=call SP_BUSCARCURSO(?,?)}";
    private static final String LISTAR_CURSOS = "{?=call SP_LISTARCURSOS()}";
    
    private static final String BORRAR_CURSO = "{call SP_BORRARCURSO(?)}";
    
}
