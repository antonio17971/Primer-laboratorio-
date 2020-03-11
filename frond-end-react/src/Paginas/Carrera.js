import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import Tabla from '../Componentes/Tabla'

class Carreras extends React.Component{
    render(){
        return (
            <React.Fragment>
                <div class="container">
                    <p></p>
                    <Tabla top={["Nombre","ID","Titulo"]}/>
                    <p>Carreras</p>
                </div>
            </React.Fragment>
        )
    }
}

export default Carreras;