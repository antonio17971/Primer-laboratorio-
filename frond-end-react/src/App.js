import React from 'react';
import logo from './logo.svg';
import {BrowserRouter, Route} from 'react-router-dom'
import Home from './Paginas/Home';
import Carreras from './Paginas/Carrera';
import Cursos from './Paginas/Cursos';
import FormCursos from './Componentes/FormCursos';
import FormCarreras from './Componentes/FormCarreras';
import actualizarCarreras from './Componentes/actualizarCarreras';
import actualizarCursos from './Componentes/actualizarCursos';

function App() {
  return (
    <BrowserRouter>
    <switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/carreras" component={Carreras} />
      <Route exact path="/cursos" component={Cursos} />
      <Route exact path="/cursos/form" component={FormCursos} />
      <Route exact path="/carreras/form" component={FormCarreras} />
      <Route exact path="/cursos/actualizar" component={actualizarCursos} />
      <Route exact path="/carreras/actualizar" component={actualizarCarreras} />
    </switch>
    
    </BrowserRouter>
  );
}

export default App;
