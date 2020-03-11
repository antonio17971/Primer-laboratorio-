import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'




class Tabla extends React.Component {
     headers (){ 
        var prop = this.props.top   
        return prop.map((key,index) =>{
            return (<th scope="col">{key}</th>);
            }
        )
    }
    render() {
        return (
            <React.Fragment>
                <div class="container">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                {this.headers()}
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td>Otto</td>
                                <td>@mdo</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Jacob</td>
                                <td>Thornton</td>
                                <td>@fat</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Larry</td>
                                <td>the Bird</td>
                                <td>@twitter</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </React.Fragment>
        )
    }
}

export default Tabla;