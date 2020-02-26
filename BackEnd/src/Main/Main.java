/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import com.mobiles.backend.AccesoADatos.ServicioCurso;
import com.mobiles.backend.LogicaDeNegocio.Curso;

/**
 *
 * @author jose1
 */
public class Main {
         public static void main(String[] args) throws GlobalException, NoDataException {
         ServicioCurso test;
         test = new ServicioCurso();
         Curso curso = new Curso();
         curso.setCodigo(0);
         curso.setNombre("MATEMATICAS 2");
         curso.setCreditos(10);
         curso.setHoras(6);
         //test.update_curso(curso);
         //test.borrar_curso(0);
            System.out.println(test.buscar_curso_nombre("MOVILES"));
     }
}
