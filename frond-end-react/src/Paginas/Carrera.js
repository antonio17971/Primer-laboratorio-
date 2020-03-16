import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import Tabla from '../Componentes/Tabla'

class Carreras extends React.Component{
    render(){
        return (
            <React.Fragment>
                <div class="container">
                    <p></p>
                    <Tabla top={["Fila","ID","Nombre","Titulo","Cursos", "Opciones"] } URL='http://localhost:8080/ServerWeb/listarCarreras'/>
                </div>
            </React.Fragment>
        )
    }
}

export default Carreras;