package com.example.demo.Componentes;

import javafx.scene.control.ProgressBar;

import java.util.Random;



public class Hilo extends Thread
{//Este metodo se encarga de la logica del hilo
    private ProgressBar ruta;
    public Hilo(String nombre, ProgressBar pgb)
    {
        super(nombre);
        this.ruta = pgb;

    }

    @Override
    public void run() {
        super.run();
        double avance =  0;
       while (avance < 1)
        {
            avance += Math.random()/10;
            this.ruta.setProgress(avance);
            try {
                sleep((long)(Math.random()*2000));
            } catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*@Override
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

     */
}
