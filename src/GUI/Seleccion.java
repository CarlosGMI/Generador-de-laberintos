package GUI;

/**
* GUI.Seleccion
* 
* La clase Seleccion del paquete GUI es la encargada de que el usuario pueda seleccionar diferentes aspectos para poder iniciar una corrida:
* *- Tipo de algoritmo de generación del laberinto (Algoritmo de división recursiva, algoritmo de Prim o algoritmo de Aldous-Broder)
* *- Tamaño en filas del laberinto.
* *- Tamaño en columnas del laberinto.
* *- El ancho y alto (tamaño en píxeles) de cada celda del laberinto.
* Al mismo tiempo, el usuario puede regresarse a la ventana Menu haciendo click sobre el botón "Regresar" o, una vez que ingrese todos los
* campos especificados arriba, hacer click sobre el botón "Iniciar corrida" para generar el laberinto.
* 
* @author Carlos Maldonado.
* @version 1.00, 24/12/2017
* 
*/

//Librerías
import java.awt.Color; //Librería para colocarle un color de fondo al actual JFrame.
import Codigo.Proyecto; //Importar el código de la clase Proyecto del paquete Codigo.
import javax.swing.JOptionPane; //Librería para poder mostrar mensajes de error al usuario.

public class Seleccion extends javax.swing.JFrame
{
    //Atributos
    Proyecto proyecto; //El proyecto que se crea debido a que el usuario quiere iniciar una nueva corrida.
    
    //Constructor por defecto
    public Seleccion() 
    {
        initComponents(); //Inicia los componentes del JFrame.
        this.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla del usuario.
        this.getContentPane().setBackground(Color.BLACK); //Colorea el fondo del JFrame de color negro.
        proyecto = new Proyecto(); //Creo el proyecto que se genera al iniciar una nueva corrida.
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboBoxOpciones = new javax.swing.JComboBox();
        botonIniciar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoAncho = new javax.swing.JTextField();
        campoAltura = new javax.swing.JTextField();
        campoAnchoCelda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione el algoritmo de generación de laberintos de su preferencia:");

        comboBoxOpciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Algoritmo de división recursiva", "Algoritmo de Prim", "Algoritmo de Aldous-Broder" }));

        botonIniciar.setBackground(new java.awt.Color(51, 204, 0));
        botonIniciar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        botonIniciar.setText("Iniciar corrida");
        botonIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIniciarMouseClicked(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tamaño del laberinto:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Altura (filas)");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ancho (columnas)");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ancho de celda (píxeles)");

        botonRegresar.setBackground(new java.awt.Color(255, 204, 51));
        botonRegresar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        botonRegresar.setText("Regresar");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(206, 206, 206)
                                        .addComponent(jLabel5)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoAltura)
                                    .addComponent(campoAncho)
                                    .addComponent(campoAnchoCelda))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonIniciar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(comboBoxOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoAncho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoAnchoCelda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonIniciar)
                    .addComponent(botonRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    * botonIniciarMouseClicked
    *
    * Este método es el encargado de abrir el JDialog que contendrá al laberinto generado a partir de los campos especificados por el
    * usuario al hacer click en "Iniciar corrida".
    */
    private void botonIniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIniciarMouseClicked
        //Si los campos de texto están vacíos muestra un mensaje de error.
        if("".equals(this.campoAltura.getText()) || "".equals(this.campoAncho.getText()) || "".equals(this.campoAnchoCelda.getText()))
        {
            JOptionPane.showMessageDialog(null, "Error! Por favor, rellena todos los campos de texto");
        }
        else //Si todos los campos están llenos 
        {
            //Manejo de excepción en caso de que el usuario ingrese letras en vez de números.
            try
            {
                //Si los números ingresados por el usuario en los campos de texto son correctos crea el JDialog del laberinto y hazlo visible.
                if((Integer.parseInt(this.campoAltura.getText()) >= 10) && (Integer.parseInt(this.campoAncho.getText()) >= 10) && (Integer.parseInt(this.campoAnchoCelda.getText()) >= 10 && Integer.parseInt(this.campoAnchoCelda.getText())%10 == 0))
                {
                    ContenedorLaberinto maze = new ContenedorLaberinto(new javax.swing.JFrame(), true,Integer.parseInt(this.campoAncho.getText()),Integer.parseInt(this.campoAltura.getText()),this.comboBoxOpciones.getSelectedIndex(),this.proyecto,Integer.parseInt(this.campoAnchoCelda.getText()));
                    maze.setVisible(true);
                }
                //Si los números ingresados por el usuario en los campos de texto no son correctos muestra un mensaje de error.
                else
                {
                    JOptionPane.showMessageDialog(null, "Error! Por favor, todos los campos deben ser mayores o iguales que 10 y el campo del ancho de la celda debe ser múltiplo de 10");
                }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Error! Por favor, usa solo números enteros para llenar los campos de texto");
            }
        }
    }//GEN-LAST:event_botonIniciarMouseClicked
    /**
    * botonRegresarActionPerformed
    *
    * Este método es el encargado de abrir el JFrame Menu cuando el usuario no quiera iniciar más corridas y solo desee ir a la ventana
    * principal. Además, se guardará el proyecto creado con todas las corridas iniciadas en un archivo de objeto que posteriormente 
    * se leerá para poder mostrar las corridas previas.
    */
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        Menu menu = new Menu(this.proyecto); //Creo el JFrame Menu y le paso el proyecto que se creó al abrir el actual JFrame.
        menu.setVisible(true); //Hago visible el menú.
        this.dispose(); //Cierro el actual JFrame.
    }//GEN-LAST:event_botonRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Seleccion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonIniciar;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JTextField campoAltura;
    private javax.swing.JTextField campoAncho;
    private javax.swing.JTextField campoAnchoCelda;
    private javax.swing.JComboBox comboBoxOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
