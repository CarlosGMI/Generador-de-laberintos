package Codigo;

/**
* Codigo.Proyecto
* 
* La clase Proyecto del paquete Codigo es la encargada de mantener la lista de las corridas al mismo tiempo que asigna a cada corrida
* todos sus datos especificados por el usuario incluyendo el tiempo de generación, calculado en base a cuánto dura esta implementación
* de cada algoritmo.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

//Librerías
import java.io.Serializable; //Librería para serializar el objeto y guardarlo en un archivo.
import java.util.ArrayList; //Librería para poder utilizar todos los métodos de un ArrayList.

public class Proyecto implements Serializable
{
    //Atributos
    ArrayList<Corrida> listaCorridas = new ArrayList<Corrida>(); //La lista de corridas que mantiene cada proyecto.
    
    //Constructor por defecto
    public Proyecto()
    {
        
    }
    
    /**
     * iniciar
     * 
     * Este método es el encargado de dar inicio a la generación del laberinto al mismo tiempo que asigna a cada corrida sus datos
     * correspondientes incluyendo el tiempo de ejecución del algoritmo de generación.
     * 
     * @param numAlgoritmo es el algoritmo de generación que será usado para generar el laberinto.
     * @param filas es el número de filas que tendrá el laberinto.
     * @param columnas es el número de columnas que tendrá el laberinto.
     * @param anchoCeldas es el ancho de la celda en píxeles que tendrá el laberinto.
     */
    public void iniciar(int numAlgoritmo, int filas, int columnas, int anchoCeldas)
    {
        //Genero una nueva corrida y le asigno todos sus datos.
        Corrida corrida = new Corrida(numAlgoritmo, filas, columnas, anchoCeldas);
        corrida.setTipoAlgoritmo(numAlgoritmo);
        corrida.setFilasLaberinto(filas);
        corrida.setColumnasLaberinto(columnas);
        corrida.inicializarPanel(); //Inicializo la matriz que será manipulada para generar el laberinto.
        long tiempoInicio = System.currentTimeMillis(); //Inicia el conteo del tiempo de ejecución del algoritmo.
        corrida.iniciarGeneracion(); //Inicia la generación del laberinto.
        long tiempoFinal = System.currentTimeMillis(); //Finaliza el conteo del tiempo de ejecución del algoritmo.
        corrida.setTiempoGeneracion((tiempoFinal-tiempoInicio)*0.001); //Asigno a la corrida su tiempo de generación.
        listaCorridas.add(corrida); //Añado la corrida actual a la lista de corridas.
    }

    //Getters y setters
    public ArrayList<Corrida> getListaCorridas() {
        return listaCorridas;
    }

    public void setListaCorridas(ArrayList<Corrida> listaCorridas) {
        this.listaCorridas = listaCorridas;
    }
}
