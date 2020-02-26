/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import com.mobiles.backend.AccesoADatos.ServicioCarrera;
import com.mobiles.backend.AccesoADatos.ServicioCurso;
import com.mobiles.backend.LogicaDeNegocio.Carrera;
import com.mobiles.backend.LogicaDeNegocio.Curso;
import java.sql.SQLException;

/**
 *
 * @author jose1
 */
public class Main {
         public static void main(String[] args) throws GlobalException, NoDataException, SQLException {
         ServicioCurso test;
         test = new ServicioCurso();
         Curso curso = new Curso();
         curso.setCodigo(0);
         curso.setNombre("MATEMATICAS 2");
         curso.setCreditos(10);
         curso.setHoras(6);
         //test.update_curso(curso);
         //test.borrar_curso(0);
         //System.out.println(test.buscar_curso_nombre("MOVILES"));
         
         ServicioCarrera test2 = new ServicioCarrera();
         
         Carrera carrera = new Carrera(3,"MATEMATICAS", "LINCIA");
            //test2.insertar_carrera(carrera);
            //test2.update_carrera(carrera);
            //test2.borrar_carrera(3);
            System.out.println(test2.listar_carrera());
         
     }
}
