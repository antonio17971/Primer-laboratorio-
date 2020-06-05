/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos;

import com.google.gson.Gson;
import com.mobiles.backend.Control.Control;
import com.mobiles.backend.Entidades.Carrera;
import com.mobiles.backend.Entidades.Curso;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose1
 */
@WebServlet(name = "buscarCurso", urlPatterns = {"/buscarCurso"})
public class buscarCurso extends HttpServlet {

    
    private Control control = Control.getInstance();
    private String cursosJsonString;
    Collection cursos;
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
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String nombre = (String) request.getParameter("nombre");
        int id = 0;
        id = Integer.parseInt(request.getParameter("ID"));
        
        if(!(nombre==null)){
            try {
                 cursos = control.buscarCurso(nombre);
            } catch (Exception e) {
                cursos =  new ArrayList<Curso>();
            }
        }else if(!(id==0)){
            try {
                 cursos = control.buscarCurso(id);
            } catch (Exception e) {
                cursos =  new ArrayList<Curso>();
            }
        }
        
        cursosJsonString = gson.toJson(cursos);
        try {
            out.println(cursosJsonString);
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
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
    }
 */
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
