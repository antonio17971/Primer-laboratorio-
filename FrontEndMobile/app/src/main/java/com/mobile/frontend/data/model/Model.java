package com.mobile.frontend.data.model;

import java.io.Serializable;
import java.util.ArrayList;

public final class Model implements Serializable {

    private UserModel loggedUser;
    private ArrayList<UserModel> users;
    private ArrayList<CursoModel> cursos;
    private ArrayList<CarreraModel> carreras;

    public Model(UserModel loggedUser, ArrayList<UserModel> users, ArrayList<CursoModel> cursos, ArrayList<CarreraModel> carreras){
        this.loggedUser = loggedUser;
        this.users = users;
        this.cursos = cursos;
        this.carreras = carreras;
    }

    public Model(){
        this.loggedUser = null;
        this.initUsers();
        this.initCursos();
        this.initCarreras();
    }

    public UserModel getLoggedUser(){
        return this.loggedUser;
    }

    public UserModel setLoggedUser(UserModel loggedUser){
        this.loggedUser = loggedUser;
        return this.loggedUser;
    }

    public ArrayList<UserModel> getUsers() {
        return this.users;
    }

    public  UserModel getUser(int index){
        return this.users.get(index % this.users.size());
    }

    public UserModel addUser(UserModel user){
        this.users.add(user);
        return user;
    }

    public ArrayList<CursoModel> getCursos(){
        return this.cursos;
    }

    public CursoModel getCurso(int index){
        return this.getCursoIndex(index);
    }

    public CursoModel addCurso(CursoModel curso){
        this.cursos.add(curso);
        return curso;
    }

    public ArrayList<CarreraModel> getCarreras(){
        return this.carreras;
    }

    public CarreraModel getCarrera(int index){
        return this.carreras.get(index % this.carreras.size());
    }

    public CarreraModel addCarrera(CarreraModel carrera){
        this.carreras.add(carrera);
        return carrera;
    }

    private CursoModel getCursoIndex(int index){
        return this.cursos.get(index % this.cursos.size());
    }

    private void initUsers(){
        this.users = new ArrayList<UserModel>(){
            {
                add(new UserModel("adriangc", "Adrian Ch.", "adrian@gmail.com", "adrian123", 1));
                add(new UserModel("antonio","Antonio Q.","antonio@gmail.com","antonio123",1));
            }
        };
    }

    private void initCursos(){
        this.cursos = new ArrayList<CursoModel>(){
            {
                add(new CursoModel(1, 4, 5,"Programación 1"));
                add(new CursoModel(2, 3, 4,"Inglés 3"));
                add(new CursoModel(3, 5, 5,"Diseño Movil"));
            }
        };
    }

    private void initCarreras(){
        ArrayList<CarreraModel> carreras = new ArrayList<CarreraModel>();
        CursoModel curso = new CursoModel();

        // Creacion de la primer carrera
        CarreraModel carrera = new CarreraModel(1, "Ingenieria","Bachillerato");
        curso = this.getCursoIndex(0);
        curso.setAnho("Primero").setCiclo("Ciclo II");
        carrera.addCurso(curso);

        curso = this.getCursoIndex(2);
        curso.setAnho("Segundo").setCiclo("Ciclo I");
        carrera.addCurso(curso);
        carreras.add(carrera);

        // Creacion de la segunda carrera
        carrera = new CarreraModel(2, "Ingles", "Bachillerato");
        curso = this.getCursoIndex(1);
        curso.setAnho("Tercer").setCiclo("Ciclo I");
        carrera.addCurso(curso);
        carreras.add(carrera);

        this.carreras = carreras;
    }
}
