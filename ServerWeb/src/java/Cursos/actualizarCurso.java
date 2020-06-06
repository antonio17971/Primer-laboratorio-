/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos;

import com.google.gson.Gson;
import com.mobiles.backend.Control.Control;
import com.mobiles.backend.Entidades.Curso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author jose1
 */
@WebServlet(name = "actualizarCurso", urlPatterns = {"/actualizarCurso"})
public class actualizarCurso extends HttpServlet {

    private Control control = Control.getInstance();
    private String cursoJsonString;
    Curso curso;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        curso = new Curso();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        
        
        StringBuilder jsonBuff = new StringBuilder();
        String line = null;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            jsonBuff.append(line);
        
        if(jsonBuff.toString().equals("")){ 
            String nombre = (String) request.getParameter("nombre");
            int id =  Integer.parseInt(request.getParameter("ID"));
            int creditos =  Integer.parseInt(request.getParameter("creditos"));
            int horas =  Integer.parseInt(request.getParameter("horas"));
            curso.setCodigo(id);
            curso.setNombre(nombre);
            curso.setCreditos(creditos);
            curso.setHoras(horas);
        }else{
            JSONObject jsonObject =  new JSONObject(jsonBuff.toString());
            curso = gson.fromJson(jsonObject.toString(), Curso.class);
        }
        
        cursoJsonString = gson.toJson(curso);
        try {
            control.actualizarCurso(curso);
        } catch (Exception e) {
        }
         try {
            out.println(cursoJsonString);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/
     @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
