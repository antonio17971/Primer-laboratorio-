import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'


class actualizarCarreras extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            error: null,
            data: [],
        }
        this.data = {};

    }

    componentDidMount() {
        this.fetchData();
    }
    setsetState(datos) {
        this.state.data = datos
        //this.data = datos[0];
    }
    fetchData = (URL, options = {}) => {
        
        var props = 'http://localhost:8080/ServerWeb/buscarCarrera?ID='+this.getParams(window.location.href).ID
        var config = {
            headers: { 'Access-Control-Allow-Origin': "*" },
            proxy: {
                host: 'http://127.0.0.1:8080',
                port: 8080
            }
        };
        //console.log(this.getParams(window.location.href))
        
        //console.log(props)
        axios.get(props, config).then(res => {
            const datos = res.data;
            this.state.data = datos;
            //console.log(this.state.data)
            this.setState({ datos });
        })
    }
    getParams = function (url) {
        var params = {};
        var parser = document.createElement('a');
        parser.href = url;
        var query = parser.search.substring(1);
        var vars = query.split('&');
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split('=');
            params[pair[0]] = decodeURIComponent(pair[1]);
        }
        return params;
    };


    render() {
        //console.log(this.state.data)
        var codigo = 0;
        var nombre = "";
        var titulo = "";
        //console.log(objeto)
        this.state.data.forEach(function (elemento, indice, array) {
            console.log(elemento, indice);
            codigo = elemento.codigo;
            nombre = elemento.nombre;
            titulo = elemento.titulo;
        });
        return (
            <React.Fragment>
            <div className='container'>
                <form action='http://localhost:8080/ServerWeb/actualizarCarrera' method="POST">
                <div class="form-group">
                    <h1>Crear o actualizar Cursos</h1>
                </div>
                <div class="form-group row">
                    <label for="id">ID</label>
                    <div class="col-sm-10">
                        <input type='number' class="form-control" id="ID" name="ID" value={codigo}></input>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="nombre">Nombre</label>
                    <div class="col-sm-10">
                        <input type='text' class="form-control" id="nombre" name="nombre" value={nombre}></input>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="titulo">Titulo</label>
                    <div class="col-sm-10">
                        <input type='text' class="form-control" id="nombre" name="titulo" value={titulo}></input>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                 </form>
            </div>
            </React.Fragment>
        )
    }
}

export default actualizarCarreras;