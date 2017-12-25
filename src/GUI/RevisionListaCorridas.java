package GUI;

/**
* GUI.RevisionListaCorridas
* 
* La clase RevisionListaCorridas del paquete GUI es la encargada de contener una tabla que muestre todas las corridas previas para que
* el usuario pueda verlas y seleccionar alguna para ver el laberinto que se generó en dicha corrida.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

import Codigo.Proyecto; //Importar el código de la clase Proyecto del paquete Codigo.
import java.awt.Color; //Librería para colocarle un color de fondo al actual JFrame.
import java.awt.image.BufferedImage; //Librería para poder capturar la imagen del JPanel.
import java.io.IOException; //Librería para manejar las excepciones relacionadas con la lectura y escritura de archivos.
import java.util.logging.Level; //Librería para manejar la respuesta a excepciones.
import java.util.logging.Logger; //Librería para manejar la respuesta a excepciones.
import javax.imageio.ImageIO; //Librería para manejar la lectura y escritura de imágenes.
import javax.swing.ImageIcon; //Librería que permite agregar una imagen a un JLabel.
import javax.swing.JDialog; //Librería que permite utilizar todos los métodos de un JDialog.
import javax.swing.JLabel; //Librería que permite utilizar todos los métodos de un JLabel.
import javax.swing.JOptionPane; //Librería que permite mostrar mensajes de error al usuario.
import javax.swing.JPanel; //Librería que permite utilizar todos los métodos de un JPanel.
import javax.swing.table.DefaultTableModel; //Librería que permite utilizar el modelo default de un JTable para trabajar sobre él.

public class RevisionListaCorridas extends javax.swing.JDialog 
{
    //Atributos
    Proyecto proyecto; //El proyecto que fue capturado por el archivo de objetos.
    
    /**
     * Constructor con parámetros
     * 
     * @param parent
     * @param modal
     * @param proyecto es el proyecto que fue capturado por el archivo de objetos en el JFrame Menu.
     */
    public RevisionListaCorridas(java.awt.Frame parent, boolean modal, Proyecto proyecto) 
    {
        super(parent, modal); //Herencia del JFrame padre.
        this.proyecto = proyecto; //Inicialización del proyecto capturado por el archivo de objetos.
        initComponents(); //Iniciar todos los componentes del actual JDialog.
        this.setLocationRelativeTo(null); //Coloca el JDialog en el centro de la pantalla del usuario.
        this.getContentPane().setBackground(Color.BLACK); //Colorea el fondo del JDialog de color negro.
        this.llenarTabla(); //Llamada al método que llena la tabla que contiene todas las corridas previas.
    }
    
    /**
     * llenarTabla
     * 
     * Este método es el encargado de llenar la tabla con todas las corridas previas existentes en el proyecto.
     */
    public void llenarTabla()
    {
        DefaultTableModel model = (DefaultTableModel) this.tablaCorridas.getModel(); //Genero un modelo default para la tabla.
        for(int i=0;i<this.proyecto.getListaCorridas().size();i++) //Para cada corrida existente en el proyecto.
        {
            //Extraigo todos los datos de la corrida para que sean visibles en la tabla (número de la corrida, tipo de algoritmo, tamaño del laberinto y el tiempo que tardó su generación).
            String numeroCorrida = Integer.toString(i+1);
            String algoritmoCorrida = this.proyecto.getListaCorridas().get(i).getAlgoritmo();
            String tamanoMatriz = ""+this.proyecto.getListaCorridas().get(i).getFilasLaberinto()+"x"+this.proyecto.getListaCorridas().get(i).getColumnasLaberinto()+"------"+this.proyecto.getListaCorridas().get(i).getAnchoCelda();
            String tiempoGeneracion = Double.toString(this.proyecto.getListaCorridas().get(i).getTiempoGeneracion());
            model.addRow(new Object[]{numeroCorrida, algoritmoCorrida, tamanoMatriz, tiempoGeneracion}); //Añado la fila a la tabla.
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCorridas = new javax.swing.JTable();
        visualizarLaberinto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Revisión de lista de corridas");

        tablaCorridas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° corrida", "Algoritmo utilizado", "Tamaño de matriz", "Tiempo consumido (segundos)"
            }
        ));
        jScrollPane1.setViewportView(tablaCorridas);

        visualizarLaberinto.setBackground(new java.awt.Color(255, 204, 51));
        visualizarLaberinto.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        visualizarLaberinto.setText("Revisar laberinto");
        visualizarLaberinto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visualizarLaberintoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 214, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(221, 221, 221))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(visualizarLaberinto)
                .addGap(318, 318, 318))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(visualizarLaberinto)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * visualizarLaberintoMouseClicked
     * 
     * Este método es el encargado de mostrar al usuario el laberinto que se generó en la corrida que seleccionó de la tabla y al hacer
     * click sobre el botón "Revisar laberinto".
     * 
     * @param evt 
     */
    private void visualizarLaberintoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visualizarLaberintoMouseClicked
        if(this.tablaCorridas.getSelectionModel().isSelectionEmpty()) //Si no se ha seleccionado ninguna corrida en la tabla muestra error.
        {
            JOptionPane.showMessageDialog(null, "Error! Selecciona alguna corrida");
        }
        else //Si se seleccionó alguna corrida de la tabla
        {
            JDialog contenedor = new JDialog(this,true); //Genero un nuevo JDialog que contendrá el laberinto a mostrar.
            contenedor.setLocationRelativeTo(this); //Coloco este nuevo JDialog en el centro de la pantalla.
            BufferedImage myPicture; //Genero la imagen.
            try 
            {
                //Asigno a la imagen la lectura del archivo que tiene la corrida seleccionada por el usuario.
                myPicture = ImageIO.read(this.proyecto.getListaCorridas().get(this.tablaCorridas.getSelectedRow()).getLaberinto2());
                JLabel picLabel = new JLabel(new ImageIcon(myPicture)); //Genero un JLabel con un ícono para la imagen del laberinto.
                contenedor.add(picLabel); //Añado ese JLabel al actual JDialog.
                contenedor.pack(); //Acomodo el tamaño del JLabel y del JDialog.
                contenedor.setVisible(true); //Hago visible el JDialog con el laberinto al usuario.
            } catch (IOException ex) {
                Logger.getLogger(RevisionListaCorridas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_visualizarLaberintoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCorridas;
    private javax.swing.JButton visualizarLaberinto;
    // End of variables declaration//GEN-END:variables
}
