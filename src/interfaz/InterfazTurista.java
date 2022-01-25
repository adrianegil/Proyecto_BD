package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.Pais;
import modelo.Turista;
import servicios.LocalizacionServicios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazTurista extends JDialog {

   private static final long serialVersionUID = 1L;
   private final JPanel contentPanel = new JPanel();
   private JTextField textFieldNumPasp;
   private JTextField textField_NombTuris;
   private JTextField textField_ApellTuris;
   private JTextField textField_NumContac;
   private JComboBox comboBox_Paises;
   private JComboBox comboBox_Sex;
   private JSpinner spinner_Edad;
   private Turista turista;
   private int opcion;
   private JButton btnAceptar;

   public InterfazTurista(Turista tur, int opc) {

      this.turista = tur;
      this.opcion = opc;

      setResizable(false);
      setTitle("Turista");
      setModal(true);
      setBounds(100, 100, 368, 375);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);

      JPanel panel = new JPanel();
      panel.setBounds(10, 11, 342, 285);
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Turista", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      contentPanel.add(panel);
      panel.setLayout(null);

      JLabel lblNumDePasaporte = new JLabel("Num. de pasaporte:");
      lblNumDePasaporte.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNumDePasaporte.setBounds(10, 24, 141, 28);
      panel.add(lblNumDePasaporte);

      JLabel lblNombre = new JLabel("Nombre:");
      lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNombre.setBounds(10, 65, 141, 25);
      panel.add(lblNombre);

      JLabel lblNewLabel = new JLabel("Apellidos:");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNewLabel.setBounds(10, 111, 83, 25);
      panel.add(lblNewLabel);

      JLabel lblSexo = new JLabel("Sexo:");
      lblSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblSexo.setBounds(10, 196, 83, 28);
      panel.add(lblSexo);

      JLabel lblNumDeContacto = new JLabel("Num. de contacto:");
      lblNumDeContacto.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNumDeContacto.setBounds(10, 157, 141, 28);
      panel.add(lblNumDeContacto);

      JLabel lblPais = new JLabel("Pais:");
      lblPais.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblPais.setBounds(10, 233, 42, 28);
      panel.add(lblPais);

      textFieldNumPasp = new JTextField();
      textFieldNumPasp.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            int tam = textFieldNumPasp.getText().length();
            if (!Character.isDigit(c) || tam >= 11) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textFieldNumPasp.setBounds(161, 28, 164, 25);
      panel.add(textFieldNumPasp);
      textFieldNumPasp.setColumns(10);

      textField_NombTuris = new JTextField();
      textField_NombTuris.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();

            if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textField_NombTuris.setColumns(10);
      textField_NombTuris.setBounds(161, 67, 164, 25);
      panel.add(textField_NombTuris);

      textField_ApellTuris = new JTextField();
      textField_ApellTuris.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();

            if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textField_ApellTuris.setColumns(10);
      textField_ApellTuris.setBounds(161, 111, 164, 25);
      panel.add(textField_ApellTuris);

      textField_NumContac = new JTextField();
      textField_NumContac.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();

            if (!Character.isDigit(c)) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textField_NumContac.setColumns(10);
      textField_NumContac.setBounds(161, 161, 164, 25);
      panel.add(textField_NumContac);

      comboBox_Sex = new JComboBox();
      comboBox_Sex.setModel(new DefaultComboBoxModel(new String[]{"Masculino\t", "Femenino"}));
      comboBox_Sex.setBounds(65, 202, 117, 20);
      panel.add(comboBox_Sex);

      comboBox_Paises = new JComboBox();
      comboBox_Paises.setBounds(65, 235, 117, 20);
      panel.add(comboBox_Paises);

      JLabel lblEdad = new JLabel("Edad:");
      lblEdad.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblEdad.setBounds(217, 233, 46, 28);
      panel.add(lblEdad);

      spinner_Edad = new JSpinner();
      spinner_Edad.setModel(new SpinnerNumberModel(0, 0, 110, 1));
      spinner_Edad.setBounds(265, 239, 42, 20);
      panel.add(spinner_Edad);

      JPanel panel_1 = new JPanel();
      panel_1.setBounds(0, 321, 362, 26);
      contentPanel.add(panel_1);
      panel_1.setLayout(new GridLayout(0, 2, 0, 0));

      btnAceptar = new JButton("Aceptar");
      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (textFieldNumPasp.getText().isEmpty() || textField_NombTuris.getText().isEmpty()
                    || textField_ApellTuris.getText().isEmpty() || textField_NumContac.getText().isEmpty() || Integer.parseInt(spinner_Edad.getValue().toString()) == 0) {
               JOptionPane.showMessageDialog(null, "Debe llenar todos los datos");
            } else {
               Turista tur = new Turista();
               tur.setNumPaspTuris(Long.parseLong(textFieldNumPasp.getText()));
               tur.setNombTuris(textField_NombTuris.getText());
               tur.setApellTuris(textField_ApellTuris.getText());
               tur.setSexTuris(comboBox_Sex.getSelectedItem().toString());
               tur.setContactTurist(Long.parseLong(textField_NumContac.getText()));
               tur.setEdadTuris(Integer.parseInt(spinner_Edad.getValue().toString()));
               tur.setPaisTuris((Pais) comboBox_Paises.getSelectedItem());

               if (opcion == 1) {
                  LocalizacionServicios.getServicios().turistaServicios.insertarTurista(tur);
                  JOptionPane.showMessageDialog(InterfazTurista.this, "Se ha insertado correctamente el turista");
                  textFieldNumPasp.setText("");
                  textField_NombTuris.setText("");
                  textField_ApellTuris.setText("");
                  textField_NumContac.setText("");
               } else {

                  turista.setNumPaspTuris(Long.parseLong(textFieldNumPasp.getText()));
                  turista.setNombTuris(textField_NombTuris.getText());
                  turista.setApellTuris(textField_ApellTuris.getText());
                  turista.setSexTuris(comboBox_Sex.getSelectedItem().toString());
                  turista.setContactTurist(Long.parseLong(textField_NumContac.getText()));
                  turista.setEdadTuris(Integer.parseInt(spinner_Edad.getValue().toString()));
                  turista.setPaisTuris((Pais) comboBox_Paises.getSelectedItem());

                  LocalizacionServicios.getServicios().turistaServicios.modificarTurista(turista);
                  JOptionPane.showMessageDialog(InterfazTurista.this, "Se ha modificado correctamente el turista");
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

      actualizarPaises();
      actualizar();
   }


   public void actualizarPaises() {

      LinkedList<Pais> list = LocalizacionServicios.getServicios().paisServicios.listPaises();
      for (Pais pais : list) {
         comboBox_Paises.addItem(pais);
      }
   }

   public void actualizar() {

      if (opcion == 1) {
         btnAceptar.setText("Insertar");
      } else {
         btnAceptar.setText("Modificar");
         textField_NombTuris.setText(turista.getNombTuris());
         textField_ApellTuris.setText(turista.getApellTuris());
         textFieldNumPasp.setText(String.valueOf(turista.getNumPaspTuris()));
         textField_NumContac.setText(String.valueOf(turista.getContactTurist()));
         comboBox_Sex.setSelectedItem(turista.getSexTuris());
         comboBox_Paises.setSelectedItem(turista.getPaisTuris());
         spinner_Edad.setValue(turista.getEdadTuris());
      }
   }
}
