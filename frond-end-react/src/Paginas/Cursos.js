import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import Tabla from '../Componentes/Tabla'

class Cursos extends React.Component{
    render(){
        return (
            <React.Fragment>
                <div class="container">
                    <p></p>
                    <Tabla top={["Nombre","ID","Creditos", "Horas"]}/>
                    <p>Cursos</p>
                </div>
            </React.Fragment>
        )
    }
}

export default Cursos;