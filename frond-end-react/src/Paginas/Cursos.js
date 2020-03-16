import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import Tabla from '../Componentes/Tabla'

class Cursos extends React.Component{
    render(){
        return (
            <React.Fragment>
                <div class="container">
                    <p></p>
                    <Tabla top={["Filas","ID","Creditos", "Horas","Nombre", "Anno","Ciclo","Opciones"]} URL='http://localhost:8080/ServerWeb/listarCursos' />
                </div>
            </React.Fragment>
        )
    }
}

export default Cursos;