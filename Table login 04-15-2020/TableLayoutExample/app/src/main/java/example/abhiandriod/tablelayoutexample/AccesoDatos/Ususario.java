package example.abhiandriod.tablelayoutexample.AccesoDatos;

import java.util.List;

public class Ususario {
    private  String nombre;
    private  String correo;
    private String contracena;

    private List<String> listaUsuarios;

    public void setNombre(String vNombre){
        this.nombre = vNombre;
    }
    public void setCorreo(String vCorreo){
        this.correo = vCorreo;
    }
    public void setContracena(String vContraccena){
        this.contracena = vContraccena;
    }
    public String getNombre(){
        return  this.nombre;
    }
    public String getContracena(){
        return  this.contracena;
    }
    public String getcorreo(){
        return  this.correo;
    }


}
