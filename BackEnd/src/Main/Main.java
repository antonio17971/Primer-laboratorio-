/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import com.mobiles.backend.AccesoADatos.ServicioCurso;

/**
 *
 * @author jose1
 */
public class Main {
         public static void main(String[] args) throws GlobalException, NoDataException {
         ServicioCurso test;
         test = new ServicioCurso();
         test.listar_curso();
             System.out.println("Main.Main.main()");
     }
}
