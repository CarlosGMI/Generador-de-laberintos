package GUI;

/**
* GUI.Menu
* 
* La clase Menu del paquete GUI es la encargada de que el usuario pueda seleccionar qué es lo que desea hacer en el proyecto:
* *- Iniciar una corrida.
* *- Revisar una corrida previa.
* *- Salir.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

//Librerías
import java.awt.Color; //Librería para colocarle un color de fondo al actual JFrame.
import Codigo.Proyecto; //Importar el código de la clase Proyecto del paquete Codigo.
import javax.swing.JFrame; //Librería para poder utilizar todos los métodos de JFrame.
import javax.swing.JOptionPane; //Librería para poder mostrar mensajes de error al usuario.

public class Menu extends javax.swing.JFrame 
{
    //Atributos
    Seleccion seleccionAlgoritmo; //La próxima ventana JFrame que se abrirá cuando el usuario desee iniciar una nueva corrida.
    Proyecto proyecto; //El proyecto que se recuperará del archivo de objetos para que el usuario pueda revisar corridas previas.
    
    //Constructor por defecto
    public Menu()
    {
        initComponents(); //Inicia los componentes del JFrame.
        this.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla del usuario.
        this.getContentPane().setBackground(Color.BLACK); //Colorea el fondo del JFrame de color negro.
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Al cerrar el JFrame se termina la ejecución del proyecto.
    }
    
    /**
    * Constructor con parámetros
    * 
    * Este constructor se utiliza cuando el usuario regresa de la ventana de Seleccion para poder revisar una corrida previa, guardando
    * el proyecto creado en el archivo de objetos y luego leyéndolo para pasarlo a la ventana RevisionListaCorridas
    *
    * @param proyecto es el proyecto que se lee del archivo de objetos y pasa a la ventana RevisionListaCorridas.
    */
    public Menu(Proyecto proyecto) 
    {
        initComponents(); //Inicia los componentes del JFrame.
        this.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla del usuario.
        this.getContentPane().setBackground(Color.BLACK); //Colorea el fondo del JFrame de color negro.
        this.proyecto = proyecto; 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonIniciarCorrida = new javax.swing.JButton();
        botonRevisarCorrida = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ColorMaze.png"))); // NOI18N

        botonIniciarCorrida.setBackground(new java.awt.Color(0, 204, 51));
        botonIniciarCorrida.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        botonIniciarCorrida.setText("Iniciar corrida");
        botonIniciarCorrida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIniciarCorridaMouseClicked(evt);
            }
        });

        botonRevisarCorrida.setBackground(new java.awt.Color(255, 204, 51));
        botonRevisarCorrida.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        botonRevisarCorrida.setText("Revisar corrida previa");
        botonRevisarCorrida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRevisarCorridaMouseClicked(evt);
            }
        });

        botonSalir.setBackground(new java.awt.Color(255, 51, 51));
        botonSalir.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        botonSalir.setText("Salir");
        botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRevisarCorrida)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(botonSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(botonIniciarCorrida)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(botonIniciarCorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonRevisarCorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    * botonSalirMouseClicked
    *
    * Este método es el encargado de cerrar el JFrame cuando el usuario hace click sobre el botón "Salir".
    */
    private void botonSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_botonSalirMouseClicked
    /*
    * botonIniciarMouseClicked
    *
    * Este método es el encargado de abrir la ventana Seleccion cuando el usuario hace click sobre el botón "Iniciar corrida".
    */
    private void botonIniciarCorridaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIniciarCorridaMouseClicked
        this.dispose(); //Cierro el JFrame actual.
        seleccionAlgoritmo = new Seleccion(); //Creo una nueva ventana Seleccion.
        this.seleccionAlgoritmo.setVisible(true); //Hago visible la ventana Seleccion.
    }//GEN-LAST:event_botonIniciarCorridaMouseClicked
    /*
    * botonRevisarCorridaMouseClicked
    *
    * Este método es el encargado de abrir la ventana RevisionListaCorridas cuando el usuario hace click sobre el botón "Revisar corrida previa".
    */
    private void botonRevisarCorridaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRevisarCorridaMouseClicked
        if(this.proyecto == null) //Si no hay ningún proyecto previo (estamos ejecutando por 1era vez el proyecto), muestra un mensaje de error.
        {
            JOptionPane.showMessageDialog(null, "Error! No hay proyectos disponibles aún");
        }
        else //Si hay proyectos previos disponibles
        {
            //Genera la nueva ventana RevisionListaCorridas y pásale el proyecto previo que capturamos del archivo de objetos.
            RevisionListaCorridas rev = new RevisionListaCorridas(new javax.swing.JFrame(),true, this.proyecto);
            rev.setVisible(true); //Hago la nueva ventana visible.
        }
    }//GEN-LAST:event_botonRevisarCorridaMouseClicked

    public static void main(String args[]) 
    {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonIniciarCorrida;
    private javax.swing.JButton botonRevisarCorrida;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
