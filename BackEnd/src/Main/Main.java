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
import com.mobiles.backend.Control.Control;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author jose1
 */
public class Main {

    public static void main(String[] args) throws GlobalException, NoDataException, SQLException {

        Control control;
        Collection cursos;

        control = Control.getInstance();

        control.buscarCursoCodigo(1).forEach((curso) -> {
            System.out.println(curso.toString());
        });

    }
}
