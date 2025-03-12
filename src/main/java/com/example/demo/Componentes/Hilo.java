package com.example.demo.Componentes;

import java.util.Random;



public class Hilo extends Thread
{//Este metodo se encarga de la logica del hilo

    public Hilo(String nombre)
    {
        super(nombre);
    }

    @Override
    public void run() {
        super.run();
        //el hilo se detenra aqui 10 vecess
        for (int i = 1; i <= 10 ; i++)
        {
            try {
                sleep((long)(Math.random()*3000));//esto si se da conu n tiempo gijo, se dentran todos los procesops
            //aqui se durmio el hijlo para que duerma de uno a 3 segundos
            }catch(RuntimeException  | InterruptedException e)
            {

            }
            System.out.println(this.getName()+" llego a "+i);
        }
    }
}
