import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'


class Tabla extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading : true,
            error : null,
            data : [],
        }
    
      }



    componentDidMount(){
        this.fetchData();
    }
    setsetState(datos){
        this.state.data = datos 
    }
    handlerDelete(id){
        var props = this.props.delete+"?ID="+id  
        var config = {
            headers: {'Access-Control-Allow-Origin': "*"},
            proxy: {
                host: 'http://127.0.0.1:8080',
                port: 8080
              }
        };
        console.log(props)
          axios.delete(props,config).then(res => {
            const datos = res.data;
            this.state.data = datos;
            //console.log(this.state.data)
            this.setState({ datos });
          })
    }


    fetchData = (URL,options = {}) =>{

        var props = this.props.URL  
        var config = {
            headers: {'Access-Control-Allow-Origin': "*"},
            proxy: {
                host: 'http://127.0.0.1:8080',
                port: 8080
              }
        };
        //console.log(props)
          axios.get(props,config).then(res => {
            const datos = res.data;
            this.state.data = datos;
            //console.log(this.state.data)
            this.setState({ datos });
          })
    }
    columnas(objeto){
        var td =[];
        for (const prop in objeto) {
            td.push( objeto[prop] )
        }
        return td.map((table, index) => {
            return (<td>{table}</td>)
        });
    }
    registros () {
        
        return this.state.data.map((reg,index) =>{
            console.log(reg)
            //console.log(reg)
            return ( <tr scope="row" > 
                        <td>{index}</td> 
                        {this.columnas(reg)}
                        <td>
                            <button type="button" class="btn btn-outline-danger" onClick={()=>this.handlerDelete(reg.codigo)}>Eliminar </button> &nbsp;
                            <a href= {this.props.actualizar+'?ID='+reg.codigo} type="button" class="btn btn-outline-info">Actualizar</a>
                        </td>
                    </tr>)
        })
    }
     headers (){ 
        var prop = this.props.top 
        //console.log(prop)  
        return prop.map((head,index) =>{
            return (<th scope="col">{head}</th>);
            }
        )
    }

    render() {
        return (
            <React.Fragment>
                <a href= {this.props.form} type="button" class="btn btn-outline-success">Crear</a> <p></p>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                {this.headers()}
                            </tr>
                        </thead>
                        <tbody>
                            {this.registros()}
                        </tbody>
                    </table>
            </React.Fragment>
        )
    }
}

export default Tabla;