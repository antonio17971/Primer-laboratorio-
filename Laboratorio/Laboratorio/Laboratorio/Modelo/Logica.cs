using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace Laboratorio.Modelo
{
    class Logica : INotifyPropertyChanged
    {
        public string letra;
         
        public string Letra
        {
            get { return letra; }
            set { letra = value; }
        }
    
        public event PropertyChangedEventHandler PropertyChanged;

    }
}
