package GUI;

/**
* GUI.ContenedorLaberinto
* 
* La clase ContenedorLaberinto del paquete GUI es la encargada de contener y mostrar el JPanel en el cual se dibujará el laberinto
* generado por el algoritmo especificado por el usuario en la ventana Seleccion.
* Aquí se creará el JPanel Laberinto, se le pasará el proyecto que se creó previamente y se agregará al JDIalog actual.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

//Librerías
import Codigo.Proyecto; //Importar el código de la clase Proyecto del paquete Codigo.
import java.awt.Color; //Librería para colocarle un color de fondo al actual JDialog.
import org.apache.commons.lang3.RandomStringUtils; //Librería para generar caracteres alfanuméricos random.

public class ContenedorLaberinto extends javax.swing.JDialog
{
    /**
    * Constructor con parámetros
    * 
    * @param ancho es el ancho del laberinto especificado por el usuario en el JFrame Seleccion.
    * @param alto es el alto del laberinto especificado por usuario en el JFrame Seleccion.
    * @param numAlgoritmo es el identificador del algoritmo que seleccionó el usuario dada la JList del JFrame Seleccion.
    * @param proyecto es el proyecto que se creó al abrir un nuevo JFrame Seleccion.
    * @param anchoCelda es el ancho de la celda del laberinto especificado por el usuario en el JFrame Seleccion.
    */
    public ContenedorLaberinto(java.awt.Frame parent, boolean modal, int ancho, int alto, int numAlgoritmo, Proyecto proyecto, int anchoCelda) 
    {
        super(parent, modal); //Heredamos el constructor del padre de este JDialog.
        initComponents(); //Inicializamos todos los componentes del actual JDialog.
        this.setLocationRelativeTo(null); //Coloca el JDialog en el centro de la pantalla del usuario.
        String name = RandomStringUtils.randomAlphanumeric(9); //Generamos un String random de longitud 9 caracteres.
        proyecto.iniciar(numAlgoritmo, alto, ancho, anchoCelda); //Iniciamos la generación del laberinto con las especificaciones del usuario.
        Laberinto panelMaze = new Laberinto(proyecto, anchoCelda, name); //Creamos el JPanel Laberinto con las especificaciones del usuario.
        panelMaze.setBackground(Color.yellow); //Coloreamos el fondo del JPanel de color amarillo.
        //Especificamos el tamaño en píxeles del JPanel Laberinto que creamos (ancho y alto especificados por el tamaño de la celda).
        panelMaze.setSize(ancho*anchoCelda, alto*anchoCelda);
        this.setSize(panelMaze.getWidth()+20, panelMaze.getHeight()+50); //Especificamos el tamaño del JDIalog actual.
        this.add(panelMaze); //Agregamos el JPanel Laberinto creado al JDialog actual.
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
