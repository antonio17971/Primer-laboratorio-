using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Laboratorio
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {


        private String [,] arr = new String[4,5];
        private Button[] listaBotones = new Button[2];
        private int contParejas = 0;
        private int totalParejas = 10; 

        public void Shuffle(char[] list, Random rnd)
        {
            for (var i = list.Length; i > 0; i--)
                this.Swap(list,0, rnd.Next(0, i));
        }

        public void Swap(char[] list, int i, int j)
        {
            var temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }

        private void initializeArr()
        {
            String chars = "ABCDEFGHIJABCDEFGHIJ";
            char [] temporal = chars.ToCharArray();

            this.Shuffle(temporal, new Random());
            int contador = 0;
            for (int x = 0; x < 4; x++)
            {
                for (int y = 0; y < 5; y++)
                {
                    arr[x,y] = Char.ToString(temporal[contador]);
                    contador++;
                }
            }
        }
        public MainPage()
        {
            InitializeComponent();
            initializeArr();
            this.listaBotones[0] = null;
            this.listaBotones[1] = null;

        }
        public Boolean sonIguales()
        {

            if (this.listaBotones[0] != null && this.listaBotones[1] != null)
            {
                return this.listaBotones[0].Text.Equals(this.listaBotones[1].Text);
            }
            return false;
        }
        private void OnClick(object sender, EventArgs args,int posX, int posY)
        {
            Button boton = (sender as Button);
            if (boton.Text.Equals(""))
            {
                if (this.listaBotones[0] != null && this.listaBotones[1] != null)
                {
                    //Thread.Sleep(10000);
                    this.listaBotones[0].Text = "";
                    this.listaBotones[1].Text = "";
                    this.listaBotones[0] = null;
                    this.listaBotones[1] = null;
                }
                    if (this.listaBotones[0]!=null&&this.listaBotones[1]==null)
                {
                    this.listaBotones[1] = boton;
                    boton.Text = arr[posX, posY];

                    
                   if (this.sonIguales())
                    {
                        this.contParejas++;
                        this.listaBotones[0].BackgroundColor = Color.FromHex("#00A86B");
                        this.listaBotones[1].BackgroundColor = Color.FromHex("#00A86B");
                        this.listaBotones[0] = null;
                        this.listaBotones[1] = null;
                    }

                    //fin del juego 
                    if (this.contParejas == this.totalParejas)
                    {
                        DisplayAlert("Felicidades", "A completado el juego", "OK");
                    }

                    /// lispiar las lista 
                   
                }
                else
                {
                    this.listaBotones[0] = boton;
                    boton.Text = arr[posX, posY];
                }
            }
            
        }

        public void click_0_0(object sender, EventArgs args)
        {
            int posX = 0;
            int posY = 0;
            OnClick(sender, args, posX, posY);
        }
        public void click_0_1(object sender, EventArgs args)
        {
            int posX = 0;
            int posY = 1;
            OnClick(sender, args, posX, posY);
        }
        public void click_0_2(object sender, EventArgs args)
        {
            int posX = 0;
            int posY = 2;
            OnClick(sender, args, posX, posY);
        }
        public void click_0_3(object sender, EventArgs args)
        {
            int posX = 0;
            int posY = 3;
            OnClick(sender, args, posX, posY);
        }
        public void click_0_4(object sender, EventArgs args)
        {
            int posX = 0;
            int posY = 4;
            OnClick(sender, args, posX, posY);
        }
        public void click_1_0(object sender, EventArgs args)
        {
            int posX = 1;
            int posY = 0;
            OnClick(sender, args, posX, posY);
        }
        public void click_1_1(object sender, EventArgs args)
        {
            int posX = 1;
            int posY = 1;
            OnClick(sender, args, posX, posY);
        }
        public void click_1_2(object sender, EventArgs args)
        {
            int posX = 1;
            int posY = 2;
            OnClick(sender, args, posX, posY);
        }
        public void click_1_3(object sender, EventArgs args)
        {
            int posX = 1;
            int posY = 3;
            OnClick(sender, args, posX, posY);
        }
        public void click_1_4(object sender, EventArgs args)
        {
            int posX = 1;
            int posY = 4;
            OnClick(sender, args, posX, posY);
        }
        public void click_2_0(object sender, EventArgs args)
        {
            int posX = 2;
            int posY = 0;
            OnClick(sender, args, posX, posY);
        }
        public void click_2_1(object sender, EventArgs args)
        {
            int posX = 2;
            int posY = 1;
            OnClick(sender, args, posX, posY);

        }
        public void click_2_2(object sender, EventArgs args)
        {
            int posX = 2;
            int posY = 2;
            OnClick(sender, args, posX, posY);
        }
        public void click_2_3(object sender, EventArgs args)
        {
            int posX = 2;
            int posY = 3;
            OnClick(sender, args, posX, posY);
        }
        public void click_2_4(object sender, EventArgs args)
        {
            int posX = 2;
            int posY = 4;
            OnClick(sender, args, posX, posY);
        }
        public void click_3_0(object sender, EventArgs args)
        {
            int posX = 3;
            int posY = 0;
            OnClick(sender, args, posX, posY);
        }
        public void click_3_1(object sender, EventArgs args)
        {
            int posX = 3;
            int posY = 1;
            OnClick(sender, args, posX, posY);
        }
        public void click_3_2(object sender, EventArgs args)
        {
            int posX = 3;
            int posY = 2;
            OnClick(sender, args, posX, posY);
        }
        public void click_3_3(object sender, EventArgs args)
        {
            int posX = 3;
            int posY = 3;
            OnClick(sender, args, posX, posY);
        }
        public void click_3_4(object sender, EventArgs args)
        {
            int posX = 3;
            int posY = 4;
            OnClick(sender, args, posX, posY);
        }
    }
}
