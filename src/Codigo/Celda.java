package Codigo;

/**
* Codigo.Celda
* 
* La clase Proyecto del paquete Codigo es la encargada de representar cada "cuadrito" del laberinto para poder ser dibujado o no.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
*/

//Librerías
import java.io.Serializable; //Librería para serializar el objeto y guardarlo en un archivo.

public class Celda implements Serializable
{
    //Atributos 
    private boolean lados[] = {true,true,true,true}; //Los cuatros lados de cada celda (arriba, derecha, abajo, izquierda) --> true para que se dibuje, false para que no.
    private boolean puertas[] = {false,false,false,false}; //Vector para el algoritmo de división recursiva
    private boolean visitada = false; //Saber si la celda está visitada
    private Celda vecinoAbajo, vecinoArriba, vecinoDerecho, vecinoIzquierdo; //Los vecinos de cada celda.
    private int posX, posY, anchoCelda; //La coordenada y el ancho en píxeles de cada celda.
    
    /**
     * Constructor con parámetros
     * 
     * @param x es la coordenada x de la celda.
     * @param y es la coordenada y de la celda.
     * @param anchoCelda es el ancho en píxeles de la celda.
     */
    public Celda(int x,int y, int anchoCelda)
    {
        this.anchoCelda = anchoCelda;
        this.posX = x*anchoCelda; 
        this.posY = y*anchoCelda; 
    }

    //Getters y setters
    public boolean[] getLados() {
        return lados;
    }

    public void setLados(boolean[] lados) {
        this.lados = lados;
    }

    public int getPosX() {
        return posX;
    }
    
    public int getPosX2() {
        return posX/this.anchoCelda;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public int getPosY2() {
        return posY/this.anchoCelda;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean[] getPuertas() {
        return puertas;
    }

    public void setPuertas(boolean[] puertas) {
        this.puertas = puertas;
    }

    public boolean isVisitada() {
        return visitada;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public Celda getVecinoAbajo() {
        return vecinoAbajo;
    }

    public void setVecinoAbajo(Celda vecinoAbajo) {
        this.vecinoAbajo = vecinoAbajo;
    }

    public Celda getVecinoArriba() {
        return vecinoArriba;
    }

    public void setVecinoArriba(Celda vecinoArriba) {
        this.vecinoArriba = vecinoArriba;
    }

    public Celda getVecinoDerecho() {
        return vecinoDerecho;
    }

    public void setVecinoDerecho(Celda vecinoDerecho) {
        this.vecinoDerecho = vecinoDerecho;
    }

    public Celda getVecinoIzquierdo() {
        return vecinoIzquierdo;
    }

    public void setVecinoIzquierdo(Celda vecinoIzquierdo) {
        this.vecinoIzquierdo = vecinoIzquierdo;
    }
}
