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
         curso.setCreditos(5);
         curso.setNombre("MATEMATICAS");
         curso.setCreditos(2);
         curso.setHoras(6);
         curso.setAnho("2020");
         curso.setCiclo("I ciclo");
         test.insertar_curso(curso);
     }
}
