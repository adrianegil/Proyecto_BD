package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.Chofer;
import servicios.LocalizacionServicios;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazChofer extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTextField textField_ID;
   private JTextField textField_Nomb;
   private JTextField textField_Apell;
   private Chofer chofer;
   private int opcion;
   private JComboBox comboBox_Cat;
   private JButton btnAceptar;
   private JTextArea textArea_Dir;


   public InterfazChofer(Chofer choferPar, int opc) {

      this.chofer = choferPar;
      this.opcion = opc;


      setModal(true);
      setResizable(false);
      setTitle("Chofer");
      setBounds(100, 100, 312, 384);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);

      JPanel panel = new JPanel();
      panel.setBounds(10, 21, 286, 298);
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Chofer", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      contentPanel.add(panel);
      panel.setLayout(null);

      JLabel lblId = new JLabel("ID:");
      lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblId.setBounds(10, 21, 80, 22);
      panel.add(lblId);

      textField_ID = new JTextField();
      textField_ID.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            int tam = textField_ID.getText().length();
            if (!Character.isDigit(c) || tam >= 11) {
               e.consume();
               getToolkit().beep();
            }

         }
      });
      textField_ID.setBounds(100, 22, 159, 22);
      panel.add(textField_ID);
      textField_ID.setColumns(10);

      JLabel lblNombre = new JLabel("Nombre:");
      lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNombre.setBounds(10, 67, 82, 22);
      panel.add(lblNombre);

      JLabel lblApellidos = new JLabel("Apellidos:");
      lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblApellidos.setBounds(10, 114, 82, 22);
      panel.add(lblApellidos);

      JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
      lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblDireccin.setBounds(10, 163, 82, 22);
      panel.add(lblDireccin);

      JLabel lblCategora = new JLabel("Categor\u00EDa:");
      lblCategora.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblCategora.setBounds(10, 241, 82, 22);
      panel.add(lblCategora);

      textField_Nomb = new JTextField();
      textField_Nomb.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textField_Nomb.setBounds(100, 69, 159, 22);
      panel.add(textField_Nomb);
      textField_Nomb.setColumns(10);

      textField_Apell = new JTextField();
      textField_Apell.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textField_Apell.setBounds(100, 116, 159, 22);
      panel.add(textField_Apell);
      textField_Apell.setColumns(10);

      comboBox_Cat = new JComboBox();
      comboBox_Cat.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E"}));
      comboBox_Cat.setBounds(100, 243, 39, 22);


      panel.add(comboBox_Cat);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(100, 163, 159, 57);
      panel.add(scrollPane);

      textArea_Dir = new JTextArea();
      scrollPane.setViewportView(textArea_Dir);

      JPanel panel_1 = new JPanel();
      panel_1.setBounds(0, 330, 306, 25);
      contentPanel.add(panel_1);
      panel_1.setLayout(new GridLayout(0, 2, 0, 0));

      btnAceptar = new JButton("Aceptar");
      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Chofer chof = new Chofer();
            if (textField_ID.getText().isEmpty() || textField_Nomb.getText().isEmpty()
                    || textField_Apell.getText().isEmpty() || textArea_Dir.getText().isEmpty())
               JOptionPane.showMessageDialog(null, "Debe llenar todos los datos");
            else {
               chof.setIdChof(textField_ID.getText());
               chof.setNombChof(textField_Nomb.getText());
               chof.setApellChof(textField_Apell.getText());
               chof.setDireccChof(textArea_Dir.getText());
               chof.setCatChof(comboBox_Cat.getSelectedItem().toString());

               if (opcion == 1) {
                  LocalizacionServicios.getServicios().choferServicios.insertarChofer(chof);
                  JOptionPane.showMessageDialog(null, " Se insertó el chofer correctamente");
                  textField_ID.setText("");
                  textField_Nomb.setText("");
                  textField_Apell.setText("");
                  textArea_Dir.setText("");
               } else {
                  chofer.setIdChof(textField_ID.getText());
                  chofer.setNombChof(textField_Nomb.getText());
                  chofer.setApellChof(textField_Apell.getText());
                  chofer.setDireccChof(textArea_Dir.getText());
                  chofer.setCatChof(comboBox_Cat.getSelectedItem().toString());

                  LocalizacionServicios.getServicios().choferServicios.modificarChofer(chofer);
                  JOptionPane.showMessageDialog(null, " Se modificó el chofer correctamente");
                  dispose();
               }
            }
         }
      });
      panel_1.add(btnAceptar);

      JButton btnCancelar = new JButton("Cancelar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });
      panel_1.add(btnCancelar);

      actualizar();
   }


   public void actualizar() {

      if (opcion == 1) {
         btnAceptar.setText("Insertar");

      } else {
         textField_Nomb.setText(chofer.getNombChof());
         textField_Apell.setText(chofer.getApellChof());
         textField_ID.setText(chofer.getIdChof());
         comboBox_Cat.setSelectedItem(chofer.getCatChof());
         textArea_Dir.setText(chofer.getDireccChof());
         btnAceptar.setText("Modificar");
      }
   }
}
