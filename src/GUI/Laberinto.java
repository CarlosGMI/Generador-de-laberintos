package GUI;

/**
* GUI.Laberinto
* 
* La clase Laberinto del paquete GUI es la encargada de dibujar el laberinto que se ha generado gracias al algoritmo que ha sido
* especificado por el usuario.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

//Librerías
import java.awt.Graphics; //Librería que permite poder dibujar sobre un JPanel.
import Codigo.Proyecto; //Importar el código de la clase Proyecto del paquete Codigo.
import java.awt.AWTException; //Librería para manejar las excepciones relacionadas con las instrucciones que involucran componentes AWT.
import java.awt.Color; //Librería para colocarle un color de fondo al actual JPanel.
import java.awt.Dimension; //Librería para especificar dimensiones a componentes AWT.
import java.awt.Rectangle; //Librería para generar rectángulos en componentes AWT.
import java.awt.Robot; //Librería para poder utilizar la clase Robot y hacer una captura de pantalla.
import java.awt.image.BufferedImage; //Librería para poder convertir el JPanel en una imagen.
import java.io.File; //Librería para poder crear archivos en la carpeta del proyecto.
import java.io.IOException; //Librería para manejar las excepciones relacionadas con la lectura y escritura de archivos.
import java.util.logging.Level; //Librería para manejar la respuesta a excepciones.
import java.util.logging.Logger; //Librería para manejar la respuesta a excepciones.
import javax.imageio.ImageIO; //Librería para manejar la lectura y escritura de imágenes.

public class Laberinto extends javax.swing.JPanel
{
    //Atributos
    private Proyecto proyecto; //El proyecto que fue creado en el JFrame Seleccion y será utilizado para obtener los datos necesarios para dibujar el laberinto.
    private int ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal, anchoCelda; //Puntos relacionados con el trazo de las líneas que conforman el laberinto.
    private String name; //El String random que fue creado en el JDialog ContenedorLaberinto y que se usará para colocarle nombre al archivo que se genere para guardar la imagen del laberinto.
    
    //Constructor por defecto
    public Laberinto()
    {
        
    }
    
    /**
     * Constructor con parámetros
     * 
     * @param proyecto es el proyecto que se genera en el JFrame Seleccion y que será utilizado para obtener los datos necesarios para dibujar el laberinto.
     * @param anchoCelda es el ancho de la celda en píxeles especificado por el usuario en el JFrame Seleccion.
     * @param name es el nombre que tendrá el archivo que almacena la imagen del laberinto generado.
     */
    public Laberinto(Proyecto proyecto, int anchoCelda, String name) 
    {
        initComponents(); //Inicia todos los componentes del JPanel.
        //Inicializa todos los atributos.
        this.proyecto = proyecto;
        this.anchoCelda = anchoCelda;
        this.name = name;
    }
    
    /**
    * paintComponent (sobreescrito)
    *
    * Este método es el encargado de dibujar el laberinto dadas las especificaciones del usuarios.
    * Para cada algoritmo:
    *  - Recorre la matriz de celdas que conforma el laberinto.
    *  - Comprueba cuáles son las paredes que están activadas en la celda.
    *  - Dibuja cada pared activada para cada celda.
    */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //Herencia del método padre.
        g.setColor(Color.black); //Coloreamos todo lo que dibujaremos en el JPanel de color negro.
        g.drawRect(0, 0, proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getFilasLaberinto()*this.anchoCelda, proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getColumnasLaberinto()*this.anchoCelda);
        //Para cada algoritmo
        if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getTipoAlgoritmo() == 0)
        {
            //Recorro la matriz de celdas que conforma el laberinto
            for(int i=0;i<proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getFilasLaberinto()*this.anchoCelda;i++)
            {
                for(int j=0;j<proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getColumnasLaberinto()*this.anchoCelda;j++)
                {
                    //¿La línea de arriba está disponible para ser dibujada?
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[0])
                    {
                        //Defino mis puntos iniciales y finales de la línea que se dibujará
                        int ptoXInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        int ptoXFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        //Dibujo la línea
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //¿La línea de la derecha está disponible para ser dibujada?
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[1])
                    {
                        int ptoXInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        int ptoXFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //¿La línea de abajo está disponible para ser dibujada?
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[2])
                    {
                        int ptoXInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        int ptoXFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //¿La línea de la izquierda está disponible para ser dibujada?
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[3])
                    {
                        int ptoXInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        int ptoXFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                }
            }
            //Detengo la ejecución por 100 milisegundos para esperar que el JPanel se muestre completamente para tomar la captura de pantalla
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Manejo de excepciones
            try 
            {
                //Defino un rectángulo con la posición del JPanel en la pantalla, el ancho del JPanel y la altura del JPanel (tamaño del JPanel).
                Rectangle rect = new Rectangle(this.getLocationOnScreen(),new Dimension(this.getWidth(),this.getHeight()));
                BufferedImage imagebuf = new Robot().createScreenCapture(rect); //Genero una imagen a partir de una captura de pantalla del JPanel.
                File path = new File("CorridasLaberintos"); //Defino el directorio donde se guardará la imagen generada del laberinto.
                File maze = new File(path,this.name+".jpeg"); //Genero el archivo .JPEG que se guardará.
                ImageIO.write(imagebuf,"jpeg", maze); //Escribo la imagen en la carpeta CorridasLaberintos.
                //Le asigno a la corrida del proyecto actual la imagen del laberinto que se generó.
                proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).setLaberinto2(maze);
            } 
            catch (AWTException e1) 
            {
                e1.printStackTrace();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getTipoAlgoritmo() == 1)
        {
            System.out.println("HARÉ EL ALGORITMO DE PRIM");
            //System.out.println("BLABLA: "+proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getColumnasLaberinto()/10);
            for(int i=0;i<proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getFilasLaberinto()*this.anchoCelda;i++)
            {
                for(int j=0;j<proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getColumnasLaberinto()*this.anchoCelda;j++)
                {
                    //LINEA DE ARRIBA (TOP)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[0])
                    {
                        int ptoXInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        int ptoXFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //LINEA DE LA DERECHA (RIGHT)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[1])
                    {
                        int ptoXInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        int ptoXFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //LINEA DE ABAJO (BOTTOM)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[2])
                    {
                        int ptoXInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        int ptoXFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //LINEA DE LA IZQUIERDA (LEFT)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[3])
                    {
                        int ptoXInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        int ptoXFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            try 
            {
                Rectangle rect = new Rectangle(this.getLocationOnScreen(),new Dimension(this.getWidth(),this.getHeight()));
                BufferedImage imagebuf = new Robot().createScreenCapture(rect);
                File path = new File("CorridasLaberintos");
                File maze = new File(path,this.name+".jpeg");
                ImageIO.write(imagebuf,"jpeg", maze);
                proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).setLaberinto2(maze);
            } 
            catch (AWTException e1) 
            {
                e1.printStackTrace();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).setLaberinto(this);
        }
        else if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getTipoAlgoritmo() == 2)
        {
            System.out.println("HARÉ EL ALGORITMO DE ALDOUS-BRODER");
            for(int i=0;i<proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getFilasLaberinto()*this.anchoCelda;i++)
            {
                for(int j=0;j<proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getColumnasLaberinto()*this.anchoCelda;j++)
                {
                    //LINEA DE ARRIBA (TOP)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[0])
                    {
                        int ptoXInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        int ptoXFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //LINEA DE LA DERECHA (RIGHT)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[1])
                    {
                        int ptoXInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        int ptoXFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //LINEA DE ABAJO (BOTTOM)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[2])
                    {
                        int ptoXInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX())+this.anchoCelda;
                        int ptoYInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        int ptoXFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYFinal = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                    //LINEA DE LA IZQUIERDA (LEFT)
                    if(proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getLados()[3])
                    {
                        int ptoXInicial = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYInicial = (proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY())+this.anchoCelda;
                        int ptoXFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosX();
                        int ptoYFinal = proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).getCeldas()[i/this.anchoCelda][j/this.anchoCelda].getPosY();
                        g.drawLine(ptoXInicial, ptoYInicial, ptoXFinal, ptoYFinal);
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            try 
            {
                Rectangle rect = new Rectangle(this.getLocationOnScreen(),new Dimension(this.getWidth(),this.getHeight()));
                BufferedImage imagebuf = new Robot().createScreenCapture(rect);
                File path = new File("CorridasLaberintos");
                File maze = new File(path,this.name+".jpeg");
                ImageIO.write(imagebuf,"jpeg", maze);
                proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).setLaberinto2(maze);
            } 
            catch (AWTException e1) 
            {
                e1.printStackTrace();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //proyecto.getListaCorridas().get(proyecto.getListaCorridas().size()-1).setLaberinto(this);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
}
