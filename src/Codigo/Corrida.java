package Codigo;

/**
* Codigo.Corrida
* 
* La clase Corrida del paquete Codigo es la encargada de realizar los tres algoritmos de generación de laberintos a través de la matriz
* de celdas que representa el laberinto en cuestión.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

//Librerías
import java.io.File; //Librería que permite que cada corrida tenga una imagen del laberinto ya generado.
import java.io.Serializable; //Librería para serializar el objeto y guardarlo en un archivo.
import java.util.ArrayList; //Librería para poder utilizar todos los métodos de un ArrayList.
import java.util.Random; //Librería para generar números random.

public class Corrida implements Serializable
{
    //Atributos
    private int tipoAlgoritmo, filasLaberinto, columnasLaberinto, anchoCelda; //Datos de la corrida especificados por el usuario.
    private double tiempoGeneracion; //El tiempo de ejecución de cada algoritmo de generación (tiempo de corrida).
    private String algoritmo; //El tipo de algoritmo, especificado por el usuario, que se usará para generar el laberinto.
    private Celda[][] celdas; //La matriz de celdas que representa el laberinto y a partir de la cual se generará el mismo.
    private transient File laberinto2; //La imagen del laberinto ya generado que debe tener cada corrida.
    
    /**
     * Constructor con parámetros
     * 
     * @param numAlgoritmo es el tipo de algoritmo de generación especificado por el usuario.
     * @param filas es la cantidad de filas del laberinto, especificadas por el usuario.
     * @param columnas es la cantidad de columnas del laberinto, especificadas por el usuario. 
     * @param anchoCelda es el ancho, en píxeles, de cada celda de la matriz de celdas.
     */
    public Corrida(int numAlgoritmo, int filas, int columnas, int anchoCelda)
    {
        //Inicializo atributos
        this.tipoAlgoritmo = numAlgoritmo;
        this.filasLaberinto = filas;
        this.columnasLaberinto = columnas;
        this.anchoCelda = anchoCelda;
    }
    
    /**
     * inicializarPanel/inicializarCeldas
     * 
     * Este método se encarga de inicializar la matriz de Celdas que representa el laberinto.
     */
    public void inicializarPanel()
    {
        this.inicializarCeldas();
    }
    
    public void inicializarCeldas()
    {
        celdas = new Celda[this.filasLaberinto][this.columnasLaberinto]; //Inicializo la matriz de celdas
        //Para cada fila y columna de la matriz creo una celda con la coordenada y el ancho en píxeles respectivo
        for(int i=0;i<this.filasLaberinto;i++)
        {
            for(int j=0;j<this.columnasLaberinto;j++)
            {
                celdas[i][j] = new Celda(j,i,this.anchoCelda);
            }
        }
        this.asignarVecinos(); //Reviso qué vecinos tiene cada celda.
    }
    
    /**
     * asignarVecinos
     * 
     * Este método se encarga de asignar a cada celda de la matriz, las celdas vecinas que le corresponden.
     */
    public void asignarVecinos()
    {
        //Para cada fila y columna de la matriz
        for(int i=0;i<this.filasLaberinto;i++)
        {
            for(int j=0;j<this.columnasLaberinto;j++)
            {
                if(i > 0) //Si la fila es mayor que 0 se puede asignar un vecino de arriba
                {
                    this.celdas[i][j].setVecinoArriba(this.celdas[i-1][j]);
                }
                if(i < this.filasLaberinto-1) //Si la fila es menor que el total de filas se puede asignar un vecino de abajo
                {
                    this.celdas[i][j].setVecinoAbajo(this.celdas[i+1][j]);
                }
                if(j < this.columnasLaberinto-1) //Si la columna es menor que el total de columnas se puede asignar un vecino derecho
                {
                    this.celdas[i][j].setVecinoDerecho(this.celdas[i][j+1]);
                }
                if(j > 0) //Si la columna es mayor que cero se puede asignar un vecino izquierdo.
                {
                    this.celdas[i][j].setVecinoIzquierdo(this.celdas[i][j-1]);
                }
            }
        }
    }
    
    /**
     * iniciarGeneracion
     * 
     * Este método se encarga de generar el laberinto basado en el tipo de algoritmo especificado por el usuario.
     */
    public void iniciarGeneracion()
    {
        switch (this.getTipoAlgoritmo())
        {
            case 0: //Algoritmo de división recursiva
            {
                this.vaciarPanel(); //El algoritmo inicia con un laberinto sin ninguna línea (celdas sin paredes).
                //Inicio de algoritmo
                this.algoritmoDivisionRecursiva(this.columnasLaberinto, this.filasLaberinto, 0, this.filasLaberinto-1, 0, this.columnasLaberinto-1);
                break;
            }
            case 1: //Algoritmo de Prim
            {
                //Inicio de algoritmo
                this.algoritmoPrim();
                break;
            }
            case 2: //Algoritmo de Aldous-Broder
            {
                //Inicio de algoritmo
                this.algoritmoAldousBroder();
                break;
            }
            default: 
            {
                System.out.println("ERROR!");
            }
        }
    }
    
    //=============================INICIO DEL BLOQUE DE CÓDIGO DEL ALGORITMO DE DIVISIÓN RECURSIVA=============================
    
    /**
     * algoritmoDivisionRecursiva
     * 
     * Este método se encarga de coordinar el algoritmo de la división recursiva. Este algoritmo consiste en:
     * 1- Mientras la altura y la anchura sean mayores o iguales que 2.
     * 2- Si la altura es mayor que el ancho, divide el laberinto verticalmente.
     * 3- Si la anchura es mayor que la altura, divide el laberinto horizontalmente.
     * 4- Si ambos son iguales, divide el laberinto verticalmente (en esta implementación).
     * 5- Haz recursividad sobre los laberintos que se formaron al hacer la división. 
     * 
     * @param ancho es el ancho del laberinto que se va a dividir.
     * @param alto es la altura del laberinto que se va a dividir.
     * @param minFila es la fila de la matriz de celdas donde comienza el laberinto que se va a dividir.
     * @param maxFila es la fila de la matriz de celdas donde termina el laberinto que se va a dividir.
     * @param minCol es la columna de la matriz de celdas donde comienza el laberinto que se va a dividir.
     * @param maxCol es la columna de la matriz de celdas donde termina el laberinto que se va a dividir.
     */
    public void algoritmoDivisionRecursiva(int ancho, int alto, int minFila, int maxFila, int minCol, int maxCol)
    {
        if(ancho >= 2 && alto >= 2) //Si la anchura y altura del laberinto que se va a dividir es mayor o igual que 2
        {
            if("Vertical".equals(this.orientacionDivisionRecursiva(ancho, alto)))//Si la orientación es a dividir verticalmente hazlo.
            {
                this.divisionVertical(ancho, alto, minFila, maxFila, minCol, maxCol);
            }
            else //Si la orientación es a dividir horizontalmente hazlo.
            {
                this.divisionHorizontal(ancho, alto, minFila, maxFila, minCol, maxCol);
            }
        }
    }
    
    /**
     * orientacionDivisionRecursiva
     * 
     * Este método se encarga de retornar la orientación hacia la cual debe dividirse el laberinto dependiendo de su anchura y altura.
     * 
     * @param ancho es la anchura del laberinto que se va a dividir.
     * @param alto es la altura del laberinto que se va a dividir.
     * @return cómo se dividirá el laberinto (vertical u horizontalmente).
     */
    public String orientacionDivisionRecursiva(int ancho, int alto)
    {
        if(alto > ancho) //Si la altura es mayor que el ancho divide el laberinto horizontalmente.
        {
            return "Horizontal";
        }
        else if(ancho > alto) //Si la anchura es mayor que la altura divide el laberinto verticalmente.
        {
            return "Vertical";
        }
        else //En cualquier otro caso divide el laberinto verticalmente (en esta implementación).
        {
            return "Vertical";
        }
    }
    
    /**
     * divisionVertical
     * 
     * Este método se encarga de dividir verticalmente el laberinto dado.
     * 
     * @param ancho es el ancho del laberinto que se va a dividir verticalmente.
     * @param alto es la altura del laberinto que se va a dividir verticalmente.
     * @param minFila es la fila de la matriz de celdas donde comienza el laberinto que se va a dividir verticalmente.
     * @param maxFila es la fila de la matriz de celdas donde termina el laberinto que se va a dividir verticalmente.
     * @param minCol es la columna de la matriz de celdas donde comienza el laberinto que se va a dividir verticalmente.
     * @param maxCol es la columna de la matriz de celdas donde termina el laberinto que se va a dividir verticalmente.
     */
    public void divisionVertical(int ancho, int alto, int minFila, int maxFila, int minCol, int maxCol)
    {
        //Seleccionamos una fila al azar
        int randFila = this.randomInt(minFila, maxFila);
        
        //Seleccionamos una columna al azar mientras esta no tenga su lado derecho pintado o el lado derecho sea una puerta
        int randCol;
        do
        {
            randCol = this.randomInt(minCol, maxCol);
        }while(this.celdas[minFila][randCol].getLados()[1] || this.celdas[minFila][randCol].getPuertas()[1] || randCol == this.columnasLaberinto-1);
        System.out.println("VERTICAAAAL---Ancho:"+ancho+"---Alto:"+alto+" MinFila:"+minFila+"--MaxFila:"+maxFila+"--MinCol:"+minCol+"--MaxCol:"+maxCol);
        System.out.println("randFila: "+randFila+"----randCol: "+randCol);
        
        //Pintamos el lado derecho de la celda seleccionada al azar iterando sobre cada fila (ya que es división vertical)
        for(int i=minFila;i<=maxFila;i++)
        {
            this.celdas[i][randCol].getLados()[1] = true;
        }
        
        //Seleccionamos al azar algún lado derecho de las celdas que serán puertas
        this.celdas[randFila][randCol].getLados()[1] = false;
        this.celdas[randFila][randCol].getPuertas()[1] = true;
        
        //La división vertical divide la matriz en 2 lados: uno lado derecho y un lado izquierdo
        //Establecemos los nuevos parámetros para el lado derecho de la división y llamamos al algoritmo nuevamente
        int nuevoAncho1 = (maxCol+1)-(randCol+1);
        int nuevaMinCol1 = randCol+1;
        int nuevaMaxCol1 = maxCol;
        this.algoritmoDivisionRecursiva(nuevoAncho1, alto, minFila, maxFila, nuevaMinCol1, nuevaMaxCol1);
        
        //Establecemos los nuevos parámetros para el lado izquierdo de la división y llamamos al algoritmo nuevamente
        int nuevoAncho2 = ancho-nuevoAncho1;
        int nuevaMinCol2 = minCol;
        int nuevaMaxCol2 = randCol;
        this.algoritmoDivisionRecursiva(nuevoAncho2, alto, minFila, maxFila, nuevaMinCol2, nuevaMaxCol2);
    }
    
    /**
     * divisionVertical
     * 
     * Este método se encarga de dividir horizontalmente el laberinto dado.
     * 
     * @param ancho es el ancho del laberinto que se va a dividir horizontalmente.
     * @param alto es la altura del laberinto que se va a dividir horizontalmente.
     * @param minFila es la fila de la matriz de celdas donde comienza el laberinto que se va a dividir horizontalmente.
     * @param maxFila es la fila de la matriz de celdas donde termina el laberinto que se va a dividir horizontalmente.
     * @param minCol es la columna de la matriz de celdas donde comienza el laberinto que se va a dividir horizontalmente.
     * @param maxCol es la columna de la matriz de celdas donde termina el laberinto que se va a dividir horizontalmente.
     */
    public void divisionHorizontal(int ancho, int alto, int minFila, int maxFila, int minCol, int maxCol)
    {
        //Seleccionamos una columna al azar
        int randCol = this.randomInt(minCol, maxCol);
        
        //Seleccionamos una fila al azar mientras esta no tenga su lado de abajo pintado o el lado de abajo sea una puerta
        int randFila;
        do
        {
            randFila = this.randomInt(minFila, maxFila);
        }while(this.celdas[randFila][minCol].getLados()[2] || this.celdas[randFila][minCol].getPuertas()[2] || randFila == this.filasLaberinto-1);
        System.out.println("HORIZONTAAAAL---Ancho:"+ancho+"---Alto:"+alto+" MinFila:"+minFila+"--MaxFila:"+maxFila+"--MinCol:"+minCol+"--MaxCol:"+maxCol);
        System.out.println("randFila: "+randFila+"----randCol: "+randCol);
        
        //Pintamos el lado de abajo de la celda seleccionada al azar iterando sobre cada fila (ya que es división horizontal)
        for(int j=minCol;j<=maxCol;j++)
        {
            this.celdas[randFila][j].getLados()[2] = true;
        }
        
        //Seleccionamos al azar algún lado de abajo de las celdas que serán puertas
        this.celdas[randFila][randCol].getLados()[2] = false;
        this.celdas[randFila][randCol].getPuertas()[2] = true;
        
        //La división horizontal divide la matriz en 2 lados: un lado de abajo y un lado de arriba
        //Establecemos los nuevos parámetros para el lado de abajo de la división y llamamos al algoritmo nuevamente
        int nuevoAlto1 = (maxFila+1)-(randFila+1);
        int nuevaMinFila1 = randFila+1;
        int nuevaMaxFila1 = maxFila;
        this.algoritmoDivisionRecursiva(ancho, nuevoAlto1, nuevaMinFila1, nuevaMaxFila1, minCol, maxCol);
        
        //Establecemos los nuevos parámetros para el lado de arriba de la división y llamamos al algoritmo nuevamente
        int nuevoAlto2 = alto-nuevoAlto1;
        int nuevaMinFila2 = minFila;
        int nuevaMaxFila2 = randFila;
        this.algoritmoDivisionRecursiva(ancho, nuevoAlto2, nuevaMinFila2, nuevaMaxFila2, minCol, maxCol);
    }
    
    //=============================FINAL DEL BLOQUE DE CÓDIGO DEL ALGORITMO DE DIVISIÓN RECURSIVA=============================
    
    //=============================INICIO DEL BLOQUE DE CÓDIGO DEL ALGORITMO DE PRIM=============================
    
    /**
     * algoritmoPrim
     * 
     * Este método se encarga de coordinar el algoritmo de Prim. Este algoritmo consiste en:
     * ANTES DE INICIAR:
     * *- Se selecciona al azar una celda de la matriz de laberintos y se marca como visitada.
     * *- Se agregan a una lista los vecinos de esa celda que no han sido visitados.
     * INICIO DEL ALGORITMO:
     * 1- Se selecciona al azar una celda de la lista de vecinos y se marca como visitada.
     * 2- Se agregan los vecinos de dicha celda que no estén visitados a la lista de vecinos.
     * 3- Repetir hasta que la lista de vecinos esté vacía.
     */
    public void algoritmoPrim()
    {
        ArrayList<Celda> listaVecinos = new ArrayList<Celda>(); //Se genera e inicializa la lista de vecinos.
        Celda celdaActual = this.seleccionarCelda(listaVecinos); //Previo al inicio del algoritmo se selecciona una celda al azar.
        Celda celdaObjetivo; //Generamos otra celda que será la celda a la cual nos vamos a mover dentro del laberinto.
        while(!listaVecinos.isEmpty()) //Mientras la lista de vecinos no esté vacía.
        {
            celdaObjetivo = this.seleccionarCelda(listaVecinos); //Seleccionamos la nueva celda de la lista de vecinos.
            this.crearCamino(celdaActual, celdaObjetivo); //Dibujamos el camino entre la celda objetivo y sus vecinos.
            celdaActual = celdaObjetivo; //La nueva celda actual será la celda objetivo creada anteriormente.
        }
    }
    
    /**
     * seleccionarCelda
     * 
     * Este método se encarga de seleccionar una celda al azar de la lista de vecinos para llamar al método que asigna sus vecinos,
     * marcarla como visitada y luego retornarla para poder dibujar el camino en el JPanel.
     * 
     * @param listaVecinos es la lista de vecinos a partir de la cual se seleccionará la nueva celda al azar.
     * @return la celda seleccionada.
     */
    public Celda seleccionarCelda(ArrayList<Celda> listaVecinos)
    {
        Celda celdaObjetivo; //Generamos la celda que será retornada.
        if(listaVecinos.isEmpty()) //Si la lista de vecinos está vacía (previo al algoritmo).
        {
            //Se selecciona una celda de la matriz de celdas al azar.
            celdaObjetivo = this.celdas[this.randomInt(0, this.filasLaberinto-1)][this.randomInt(0, this.columnasLaberinto-1)];
            this.asignarVecinos(listaVecinos, celdaObjetivo); //Se asignan los vecinos de la celda seleccionada.
            celdaObjetivo.setVisitada(true); //Se marca como visitada.
            return celdaObjetivo; //Retornamos la celda seleccionada.
        }
        else //Si hay al menos 1 celda en la lista de vecinos.
        {
            int randomVecino = this.randomInt(0, listaVecinos.size()-1); //Se escoge un número al azar entre 0 y la longitud de la lista.
            celdaObjetivo = listaVecinos.get(randomVecino); //Se selecciona una celda de la lista de vecinos dado el número seleccionado.
            this.asignarVecinos(listaVecinos, celdaObjetivo); //Se asignan los vecinos de la celda seleccionada.
            celdaObjetivo.setVisitada(true); //Se marca como visitada.
            listaVecinos.remove(randomVecino); //La celda seleccionada se remueve de la lista de vecinos (ya que ya está visitada).
            return celdaObjetivo; //Retornamos la celda seleccionada.
        }
    }
    
    /**
     * asignarVecinos
     * 
     * Este método se encarga de agregar a la lista de vecinos los vecinos de la celda seleccionada previamente.
     * 
     * @param listaVecinos es la lista de vecinos que se actualizará al ir agregando los vecinos de la celda actual seleccionada.
     * @param celdaActual es la celda a partir de la cual se revisarán sus vecinos y agregarán a la lista.
     */
    public void asignarVecinos(ArrayList<Celda> listaVecinos, Celda celdaActual)
    {
        //Si la fila de la celda actual es mayor que 0 y el vecino de arriba no está visitado, agregamos el vecino de arriba a la lista (siempre y cuando no esté en la misma).
        if(celdaActual.getPosY2() > 0 && !this.celdas[celdaActual.getPosY2()-1][celdaActual.getPosX2()].isVisitada())
        {
            if(!listaVecinos.contains(this.celdas[celdaActual.getPosY2()-1][celdaActual.getPosX2()]))
            {
                listaVecinos.add(this.celdas[celdaActual.getPosY2()-1][celdaActual.getPosX2()]);
            }
        }
        //Si la fila de la celda actual es menor que el total de filas y el vecino de abajo no está visitado, agregamos el vecino de abajo a la lista (siempre y cuando no esté en la misma).
        if(celdaActual.getPosY2() < this.filasLaberinto-1 && !this.celdas[celdaActual.getPosY2()+1][celdaActual.getPosX2()].isVisitada())
        {
            if(!listaVecinos.contains(this.celdas[celdaActual.getPosY2()+1][celdaActual.getPosX2()]))
            {
                listaVecinos.add(this.celdas[celdaActual.getPosY2()+1][celdaActual.getPosX2()]);
            }
        }
        //Si la columna de la celda actual es menor que el total de columnas y el vecino derecho no está visitado, agregamos el vecino derecho a la lista (siempre y cuando no esté en la misma).
        if(celdaActual.getPosX2() < this.columnasLaberinto-1 && !this.celdas[celdaActual.getPosY2()][celdaActual.getPosX2()+1].isVisitada())
        {
            if(!listaVecinos.contains(this.celdas[celdaActual.getPosY2()][celdaActual.getPosX2()+1]))
            {
                listaVecinos.add(this.celdas[celdaActual.getPosY2()][celdaActual.getPosX2()+1]);
            }
        }
        //Si la columna de la celda actual es mayor que 0 y el vecino de izquierdo no está visitado, agregamos el vecino de izquierdo a la lista (siempre y cuando no esté en la misma).
        if(celdaActual.getPosX2() > 0 && !this.celdas[celdaActual.getPosY2()][celdaActual.getPosX2()-1].isVisitada())
        {
            if(!listaVecinos.contains(this.celdas[celdaActual.getPosY2()][celdaActual.getPosX2()-1]))
            {
                listaVecinos.add(this.celdas[celdaActual.getPosY2()][celdaActual.getPosX2()-1]);
            }
        }
    }
    
    /**
     * crearCamino
     * 
     * Este método se encarga de crear el camino entre la celda que fue seleccionada y sus vecinos visitados, para que luego el JPanel 
     * lo dibuje. Puede pasar que la celda seleccionada tenga más de 1 vecino visitado para dibujar sus caminos por lo que se crea un 
     * nuevo método que gestiona ese caso y seleccione al azar un vecino visitado para poder dibujar el camino.
     * 
     * @param celdaActual es la celda previa seleccionada.
     * @param celdaObjetivo es la celda seleccionada a partir de la cual se dibujará el camino.
     */
    public void crearCamino(Celda celdaActual, Celda celdaObjetivo)
    {
        System.out.println("CELDA ACT: ("+celdaActual.getPosY2()+"),("+celdaActual.getPosX2()+")");
        System.out.println("CELDA OBJ: ("+celdaObjetivo.getPosY2()+"),("+celdaObjetivo.getPosX2()+")");
        ArrayList<String> vecinos = this.variosVecinos(celdaObjetivo); //Vemos si la celda seleccionada tiene varios vecinos visitados.
        if(vecinos.size() == 1) //Si tiene 1 solo vecino visitado vemos cuál es y dibujamos el camino.
        {
            if("arriba".equals(vecinos.get(0))) //Si es el vecino de arriba quitamos las paredes entre el vecino de arriba y la celda seleccionada.
            {
                celdaObjetivo.getLados()[0] = false;
                celdaObjetivo.getVecinoArriba().getLados()[2] = false;
            }
            if("abajo".equals(vecinos.get(0))) //Si es el vecino de abajo quitamos las paredes entre el vecino de abajo y la celda seleccionada.
            {
                celdaObjetivo.getLados()[2] = false;
                celdaObjetivo.getVecinoAbajo().getLados()[0] = false;
            }
            if("derecho".equals(vecinos.get(0))) //Si es el vecino derecho quitamos las paredes entre el vecino derecho y la celda seleccionada.
            {
                celdaObjetivo.getLados()[1] = false;
                celdaObjetivo.getVecinoDerecho().getLados()[3] = false;
            }
            if("izquierdo".equals(vecinos.get(0))) //Si es el vecino izquierdo quitamos las paredes entre el vecino izquierdo y la celda seleccionada.
            {
                celdaObjetivo.getLados()[3] = false;
                celdaObjetivo.getVecinoIzquierdo().getLados()[1] = false;
            }
        }
        else if(vecinos.size() > 1) //Si la celda seleccionada tiene más de 1 vecino visitado seleccionamos uno al azar y, dependiendo de cuál sea, dibujamos su camino.
        {
            int cualVecino = this.randomInt(0, vecinos.size()-1);
            if("arriba".equals(vecinos.get(cualVecino)))
            {
                celdaObjetivo.getLados()[0] = false;
                celdaObjetivo.getVecinoArriba().getLados()[2] = false;
            }
            if("abajo".equals(vecinos.get(cualVecino)))
            {
                celdaObjetivo.getLados()[2] = false;
                celdaObjetivo.getVecinoAbajo().getLados()[0] = false;
            }
            if("derecho".equals(vecinos.get(cualVecino)))
            {
                celdaObjetivo.getLados()[1] = false;
                celdaObjetivo.getVecinoDerecho().getLados()[3] = false;
            }
            if("izquierdo".equals(vecinos.get(cualVecino)))
            {
                celdaObjetivo.getLados()[3] = false;
                celdaObjetivo.getVecinoIzquierdo().getLados()[1] = false;
            }
        }
    }
    
    /**
     * variosVecinos
     * 
     * Este método se encarga de ver si la celda seleccionada tiene más de 1 vecino visitado a partir del cual se dibuja su camino
     * 
     * @param celdaObjetivo es la celda seleccionada.
     * @return la lista con los vecinos visitados que tiene la celda seleccionada.
     */
    public ArrayList<String> variosVecinos(Celda celdaObjetivo)
    {
        ArrayList<String> vecinos = new ArrayList<String>(); //Generamos e inicializamos la lista de los vecinos visitados que tiene la celda.
        //Si el vecino de arriba existe y está visitado agrégalo a la lista.
        if(celdaObjetivo.getVecinoArriba() != null && celdaObjetivo.getVecinoArriba().isVisitada())
        {
            vecinos.add("arriba");
        }
        //Si el vecino de abajo existe y está visitado agrégalo a la lista.
        if(celdaObjetivo.getVecinoAbajo() != null && celdaObjetivo.getVecinoAbajo().isVisitada())
        {
            vecinos.add("abajo");
        }
        //Si el vecino derecho existe y está visitado agrégalo a la lista.
        if(celdaObjetivo.getVecinoDerecho() != null && celdaObjetivo.getVecinoDerecho().isVisitada())
        {
            vecinos.add("derecho");
        }
        //Si el vecino izquierdo existe y está visitado agrégalo a la lista.
        if(celdaObjetivo.getVecinoIzquierdo() != null && celdaObjetivo.getVecinoIzquierdo().isVisitada())
        {
            vecinos.add("izquierdo");
        }
        return vecinos; //Retornamos la lista de los vecinos visitados de la celda seleccionada.
    }
    
    //=============================FINAL DEL BLOQUE DE CÓDIGO DEL ALGORITMO DE PRIM=============================
    
    //=============================INICIO DEL BLOQUE DE CÓDIGO DEL ALGORITMO DE ALDOUS-BRODER=============================
    
    /**
     * algoritmoAldousBroder
     * 
     * Este método se encarga de coordinar el algoritmo de Aldous-Broder. El algoritmo consiste en:
     * ANTES DE INICIAR:
     * *- Se selecciona al azar una celda de la matriz de laberintos y se marca como visitada.
     * *- Se revisan sus vecinos y se selecciona uno al azar.
     * INICIO DEL ALGORITMO:
     * 1- Se selecciona un vecino al azar y se marca como visitado.
     * 2- Se dibuja el camino entre este vecino seleccionado y la celda previa.
     * 3- Se revisan los vecinos de la celda seleccionada
     * 4- Repetir hasta que todas las celdas de la matriz estén visitadas.
     */
    public void algoritmoAldousBroder()
    {
        int celdasVisitadas = 1; //Al inicio tenemos 1 celda visitada.
        Celda celdaActual = this.seleccionarCelda(celdasVisitadas, null); //Seleccionamos una celda al azar de la matriz.
        Celda celdaObjetivo; //Generamos la celda objetivo a seleccionar.
        while(celdasVisitadas < (this.filasLaberinto*this.columnasLaberinto)) //Mientras todas las celdas de la matriz no estén visitadas.
        {
            System.out.println("CELDAS VISITADAS: "+celdasVisitadas);
            celdaObjetivo = this.seleccionarCelda(celdasVisitadas, celdaActual); //Seleccionamos cuál será la celda objetivo
            if(this.caminar(celdaActual, celdaObjetivo)) //Dibujamos el camino si la celda que fue seleccionada no está visitada.
            {
                celdaObjetivo.setVisitada(true); //La marcamos como visitada
                celdasVisitadas++; //Una celda visitada más
            }
            celdaActual = celdaObjetivo; //Hacemos el cambio.
        }
    }
    
    /**
     * seleccionarCelda
     * 
     * Este método se encarga de seleccionar la celda a partir de la cual se dibujará el camino.
     * 
     * @param celdasVisitadas es el número de celdas de la matriz que han sido visitadas.
     * @param celdaActual es la celda en donde estamos parados.
     * @return la celda seleccionada.
     */
    public Celda seleccionarCelda(int celdasVisitadas, Celda celdaActual)
    {
        Celda celdaObjetivo; //Generamos la celda que se seleccionará.
        ArrayList<String> vecinos; //Generamos la lista de los vecinos que tiene la celda actual. 
        if(celdasVisitadas == 1 && celdaActual == null) //Si solo hay 1 celda visitada seleccionamos al azar una de la matriz y se retorna.
        {
            celdaObjetivo = this.celdas[this.randomInt(0, this.filasLaberinto-1)][this.randomInt(0, this.columnasLaberinto-1)];
            celdaObjetivo.setVisitada(true);
            return celdaObjetivo;
        }
        else //En cualquier otro caso se revisan los vecinos de la celda actual y se selecciona uno al azar para retornarlo.
        {
            vecinos = this.revisarVecinos(celdaActual); //La lista de vecinos será igual a los vecinos que tenga la celda actual.
            int cualVecino = this.randomInt(0, vecinos.size()-1); //De la lista se selecciona un vecino al azar.
            if("arriba".equals(vecinos.get(cualVecino))) //Si el vecino es de arriba, la celda a seleccionar será ese vecino y se retorna.
            {
                celdaObjetivo = celdaActual.getVecinoArriba();
                return celdaObjetivo;
            }
            if("abajo".equals(vecinos.get(cualVecino))) //Si el vecino es de abajo, la celda a seleccionar será ese vecino y se retorna.
            {
                celdaObjetivo = celdaActual.getVecinoAbajo();
                return celdaObjetivo;
            }
            if("derecho".equals(vecinos.get(cualVecino))) //Si el vecino es derecho, la celda a seleccionar será ese vecino y se retorna.
            {
                celdaObjetivo = celdaActual.getVecinoDerecho();
                return celdaObjetivo;
            }
            if("izquierdo".equals(vecinos.get(cualVecino))) //Si el vecino es izquierdo, la celda a seleccionar será ese vecino y se retorna.
            {
                celdaObjetivo = celdaActual.getVecinoIzquierdo();
                return celdaObjetivo;
            }
        }
        return null;
    }
    
    /**
     * revisarVecinos
     * 
     * Este método se encarga de revisar los vecinos que tiene la celda seleccionada y agregarlos a una lista que se retornará para mayor
     * control.
     * 
     * @param celdaObjetivo es la celda actual del método seleccionarCelda.
     * @return la lista de los vecinos de la celda actual.
     */
    public ArrayList<String> revisarVecinos(Celda celdaObjetivo)
    {
        ArrayList<String> vecinos = new ArrayList<String>();
        if(celdaObjetivo.getVecinoArriba() != null)
        {
            vecinos.add("arriba");
        }
        if(celdaObjetivo.getVecinoAbajo() != null)
        {
            vecinos.add("abajo");
        }
        if(celdaObjetivo.getVecinoDerecho() != null)
        {
            vecinos.add("derecho");
        }
        if(celdaObjetivo.getVecinoIzquierdo() != null)
        {
            vecinos.add("izquierdo");
        }
        return vecinos;
    }
    
    /**
     * caminar
     * 
     * Este método se encarga de revisar si la celda objetivo está o no visitada. Si lo está no se dibuja ningún camino y si no está
     * visitada hay que determinar qué vecino es la celda objetivo para trazar su camino correspondiente.
     * 
     * @param celdaActual es la celda previa que se había seleccionado.
     * @param celdaObjetivo es la nueva celda seleccionada
     * @return verdadero si no está visitada, falso si está visitada.
     */
    public boolean caminar(Celda celdaActual, Celda celdaObjetivo)
    {
        if(!celdaObjetivo.isVisitada())
        {
            this.trazar(celdaActual, celdaObjetivo);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * trazar
     * 
     * Este método se encarga de eliminar las paredes correspondientes (trazar el camino) entre la celda actual y la celda objetivo.
     * 
     * @param celdaActual es la celda previa que se había seleccionado.
     * @param celdaObjetivo es la nueva celda seleccionada.
     */
    public void trazar(Celda celdaActual, Celda celdaObjetivo)
    {
        //Me moví al vecino derecho
        if((celdaObjetivo.getPosX2()-celdaActual.getPosX2()) == 1)
        {
            celdaActual.getLados()[1] = false;
            celdaObjetivo.getLados()[3] = false;
        }
        //Me moví al vecino izquierdo
        if((celdaObjetivo.getPosX2()-celdaActual.getPosX2()) == -1)
        {
            celdaActual.getLados()[3] = false;
            celdaObjetivo.getLados()[1] = false;
        }
        //Me moví al vecino de abajo
        if((celdaObjetivo.getPosY2()-celdaActual.getPosY2()) == 1)
        {
            celdaActual.getLados()[2] = false;
            celdaObjetivo.getLados()[0] = false;
        }
        //Me moví al vecino de arriba
        if((celdaObjetivo.getPosY2()-celdaActual.getPosY2()) == -1)
        {
            celdaActual.getLados()[0] = false;
            celdaObjetivo.getLados()[2] = false;
        }
    }
    
    //=============================FINAL DEL BLOQUE DE CÓDIGO DEL ALGORITMO DE ALDOUS-BRODER=============================
    
    /**
     * randomInt
     * 
     * Este método se encarga de seleccionar un número entero al azar entre un mínimo y un máximo.
     * 
     * @param min es el mínimo número a ser seleccionado.
     * @param max es el máximo número a ser seleccionado.
     * @return el número seleccionado al azar.
     */
    public int randomInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**
     * vaciarPanel
     * 
     * Este método se encarga de colocar todas las paredes de todas las celdas de la matriz de celdas en falso para que no se dibujen o
     * que el laberinto esté "vacío".
     */
    public void vaciarPanel()
    {
        for(int i=0;i<this.filasLaberinto;i++)
        {
            for(int j=0;j<this.columnasLaberinto;j++)
            {
                celdas[i][j].getLados()[0] = false;
                celdas[i][j].getLados()[1] = false;
                celdas[i][j].getLados()[2] = false;
                celdas[i][j].getLados()[3] = false;
            }
        }
    }

    //Getters y setters
    public int getTipoAlgoritmo() {
        return tipoAlgoritmo;
    }

    public void setTipoAlgoritmo(int tipoAlgoritmo) 
    {
        if(tipoAlgoritmo == 0)
        {
            this.tipoAlgoritmo = tipoAlgoritmo;
            this.setAlgoritmo("Algoritmo de división recursiva");
        }
        else if(tipoAlgoritmo == 1)
        {
            this.tipoAlgoritmo = tipoAlgoritmo;
            this.setAlgoritmo("Algoritmo de Prim");
        }
        else if(tipoAlgoritmo == 2)
        {
            this.tipoAlgoritmo = tipoAlgoritmo;
            this.setAlgoritmo("Algoritmo de Aldous-Broder");
        }
       
    }
    
    public int getFilasLaberinto() {
        return filasLaberinto;
    }

    public void setFilasLaberinto(int filasLaberinto) {
        this.filasLaberinto = filasLaberinto;
    }

    public int getColumnasLaberinto() {
        return columnasLaberinto;
    }

    public void setColumnasLaberinto(int columnasLaberinto) {
        this.columnasLaberinto = columnasLaberinto;
    }

    public double getTiempoGeneracion() {
        return tiempoGeneracion;
    }

    public void setTiempoGeneracion(double tiempoGeneracion) {
        this.tiempoGeneracion = tiempoGeneracion;
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public int getAnchoCelda() {
        return anchoCelda;
    }

    public void setAnchoCelda(int anchoCelda) {
        this.anchoCelda = anchoCelda;
    }

    public File getLaberinto2() {
        return laberinto2;
    }

    public void setLaberinto2(File laberinto2) {
        this.laberinto2 = laberinto2;
    }
}
