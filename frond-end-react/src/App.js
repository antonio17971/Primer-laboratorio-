import React from 'react';
import logo from './logo.svg';
import {BrowserRouter, Route} from 'react-router-dom'
import Home from './Paginas/Home';
import Carreras from './Paginas/Carrera';
import Cursos from './Paginas/Cursos';

function App() {
  return (
    <BrowserRouter>
    <switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/carreras" component={Carreras} />
      <Route exact path="/cursos" component={Cursos} />
    </switch>
    
    </BrowserRouter>
  );
}

export default App;
