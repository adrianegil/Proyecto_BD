package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.Auto;
import modelo.Marca;
import modelo.Modelo;
import modelo.Situacion;
import modelo.Tarifa;
import servicios.LocalizacionServicios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazAuto extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTextField textField_NumPlaca;
   private JComboBox comboBox_Modelo;
   private JComboBox comboBox_Marca;
   private DefaultComboBoxModel defaultComboBoxModel_1 = new DefaultComboBoxModel();
   private DefaultComboBoxModel defaultComboBoxModel_2 = new DefaultComboBoxModel();
   private JComboBox comboBox_Color;
   private JSpinner spinner_ingresoEspecial;
   private JSpinner spinner_ingreso;
   private JSpinner spinner_Km;
   private Auto auto;
   private int opcion;
   private JButton btnAcept;
   private JLabel lblSituacin;
   private JComboBox comboBoxSitua;


   public InterfazAuto(Auto atu, int opc) {

      this.auto = atu;
      this.opcion = opc;

      setResizable(false);
      setTitle("Auto");
      setBounds(100, 100, 353, 382);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      {
         JPanel panel = new JPanel();
         panel.setBounds(10, 11, 326, 302);
         panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Auto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

         contentPanel.add(panel);
         panel.setLayout(null);

         JLabel lblNewLabel = new JLabel("Num. de placa:");
         lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblNewLabel.setBounds(10, 11, 106, 29);
         panel.add(lblNewLabel);

         JLabel lblKilometraje = new JLabel("Kilometraje:");
         lblKilometraje.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblKilometraje.setBounds(10, 52, 95, 29);
         panel.add(lblKilometraje);

         JLabel lblMarca = new JLabel("Marca:");
         lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblMarca.setBounds(10, 92, 106, 29);
         panel.add(lblMarca);

         JLabel lblModelo = new JLabel("Modelo:");
         lblModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblModelo.setBounds(10, 129, 61, 29);
         panel.add(lblModelo);

         JLabel lblColor = new JLabel("Color:");
         lblColor.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblColor.setBounds(10, 170, 56, 29);
         panel.add(lblColor);

         JLabel lblIngreso = new JLabel("Ingreso:");
         lblIngreso.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblIngreso.setBounds(10, 208, 71, 29);
         panel.add(lblIngreso);

         JLabel lblIngresoEspecial = new JLabel("Ingreso especial:");
         lblIngresoEspecial.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblIngresoEspecial.setBounds(10, 245, 124, 29);
         panel.add(lblIngresoEspecial);

         textField_NumPlaca = new JTextField();
         textField_NumPlaca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
               char c = e.getKeyChar();
               int tam = textField_NumPlaca.getText().length();
               if (!Character.isDigit(c) && !Character.isLetter(c) || tam >= 7) {
                  e.consume();
                  getToolkit().beep();
               }
            }
         });
         textField_NumPlaca.setBounds(135, 17, 124, 20);
         panel.add(textField_NumPlaca);
         textField_NumPlaca.setColumns(10);

         spinner_Km = new JSpinner();
         spinner_Km.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
         spinner_Km.setBounds(135, 58, 61, 20);
         panel.add(spinner_Km);

         comboBox_Marca = new JComboBox();
         comboBox_Marca.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
               actualizarModelo();
            }
         });
         comboBox_Marca.setBounds(88, 98, 171, 20);
         panel.add(comboBox_Marca);

         comboBox_Modelo = new JComboBox();
         comboBox_Modelo.setBounds(88, 135, 171, 20);
         panel.add(comboBox_Modelo);

         comboBox_Color = new JComboBox();
         comboBox_Color.setModel(new DefaultComboBoxModel(new String[]{"Amarillo", "Negro", "Blanco", "Rojo", "Azul", "Verde", "Morado", "Gris", "Naranja"}));
         comboBox_Color.setBounds(88, 176, 171, 20);
         panel.add(comboBox_Color);

         spinner_ingreso = new JSpinner();
         spinner_ingreso.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
         spinner_ingreso.setBounds(135, 214, 61, 20);
         panel.add(spinner_ingreso);

         spinner_ingresoEspecial = new JSpinner();
         spinner_ingresoEspecial.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
         spinner_ingresoEspecial.setBounds(135, 251, 61, 20);
         panel.add(spinner_ingresoEspecial);

         lblSituacin = new JLabel("Situaci\u00F3n");
         lblSituacin.setFont(new Font("Tahoma", Font.BOLD, 14));
         lblSituacin.setBounds(220, 226, 76, 22);
         panel.add(lblSituacin);

         comboBoxSitua = new JComboBox();
         comboBoxSitua.setModel(new DefaultComboBoxModel(new String[]{"Disponible", "Taller"}));
         comboBoxSitua.setBounds(220, 251, 96, 20);
         panel.add(comboBoxSitua);
      }

      JPanel panel = new JPanel();
      panel.setBounds(0, 324, 347, 29);
      contentPanel.add(panel);
      panel.setLayout(new GridLayout(0, 2, 0, 0));

      btnAcept = new JButton("Aceptar");
      btnAcept.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (textField_NumPlaca.getText().isEmpty() || comboBox_Marca.getSelectedIndex() == 0 ||
                    (double) spinner_ingreso.getValue() == 0 || (double) spinner_ingresoEspecial.getValue() == 0) {
               JOptionPane.showMessageDialog(InterfazAuto.this, "Debe llenar todos los campos");
            } else {
               Tarifa tarifa = new Tarifa();

               tarifa.setIngreso((double) spinner_ingreso.getValue());
               tarifa.setIngresoEspecial((double) spinner_ingresoEspecial.getValue());
               LocalizacionServicios.getServicios().tarifaServicios.insertarTarifa(tarifa);
               Tarifa tarif = LocalizacionServicios.getServicios().tarifaServicios.buscarTarifa(tarifa.getIngreso(), tarifa.getIngresoEspecial());
               Situacion situacion = new Situacion(3, "Disponible");

               Auto aut = new Auto();
               aut.setNumPlaca(textField_NumPlaca.getText());
               aut.setCantkm((int) spinner_Km.getValue());
               aut.setColor(comboBox_Color.getSelectedItem().toString());
               aut.setModelo((Modelo) comboBox_Modelo.getSelectedItem());
               aut.setMarca((Marca) comboBox_Marca.getSelectedItem());
               aut.setSituacion(situacion);
               aut.setTarifa(tarif);

               if (opcion == 1) {
                  LocalizacionServicios.getServicios().autoServicio.insertarAuto(aut);
                  JOptionPane.showMessageDialog(InterfazAuto.this, "Se ha insertado el auto correctamente");
                  dispose();
               } else {
                  if (comboBoxSitua.getSelectedItem().equals("Taller"))
                     situacion.setCodSituacion(1);

                  auto.setNumPlaca(textField_NumPlaca.getText());
                  auto.setCantkm((int) spinner_Km.getValue());
                  auto.setColor(comboBox_Color.getSelectedItem().toString());
                  auto.setModelo((Modelo) comboBox_Modelo.getSelectedItem());
                  auto.setMarca((Marca) comboBox_Marca.getSelectedItem());
                  auto.setSituacion(situacion);
                  auto.setTarifa(tarif);

                  LocalizacionServicios.getServicios().autoServicio.modificarAuto(auto);
                  JOptionPane.showMessageDialog(InterfazAuto.this, "Se ha modificado el auto correctamente");
                  dispose();
               }
            }
         }
      });
      panel.add(btnAcept);

      JButton btnCancelar = new JButton("Cancelar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });
      panel.add(btnCancelar);
      actualizarMarcas();
      actualizar();
   }


   public void actualizarModelo() {

      defaultComboBoxModel_2 = new DefaultComboBoxModel();

      if (comboBox_Marca.getSelectedIndex() == 0) {
         JOptionPane.showMessageDialog(InterfazAuto.this, "Seleccione una Marca");

      } else {
         LinkedList<Modelo> modelos = LocalizacionServicios.getServicios().modeloServicios.listModelos((Marca) comboBox_Marca.getSelectedItem());

         for (Modelo modelo : modelos) {
            defaultComboBoxModel_2.addElement(modelo);
         }
         if (modelos.isEmpty()) {
            defaultComboBoxModel_2.addElement("No hay modelos");

         } else
            defaultComboBoxModel_2.setSelectedItem(modelos.get(0));
      }
      comboBox_Modelo.setModel(defaultComboBoxModel_2);
   }

   public void actualizarMarcas() {

      defaultComboBoxModel_1 = new DefaultComboBoxModel();
      defaultComboBoxModel_1.addElement("Seleccione una Marca");

      LinkedList<Marca> list = LocalizacionServicios.getServicios().marcaServicios.listMarcas();
      for (Marca marca : list) {
         defaultComboBoxModel_1.addElement(marca);
      }
      comboBox_Marca.setModel(defaultComboBoxModel_1);
   }

   public void actualizar() {

      if (opcion == 1) {
         btnAcept.setText("Insertar");
         lblSituacin.setVisible(false);
         comboBoxSitua.setVisible(false);
      } else {
         btnAcept.setText("Modificar");
         lblSituacin.setVisible(true);
         comboBoxSitua.setVisible(true);
         textField_NumPlaca.setText(auto.getNumPlaca());
         spinner_Km.setValue(auto.getCantkm());
         comboBox_Marca.setSelectedItem(auto.getMarca());
         comboBox_Modelo.setSelectedItem(auto.getModelo());
         comboBox_Color.setSelectedItem(auto.getColor());
         spinner_ingreso.setValue(auto.getTarifa().getIngreso());
         spinner_ingresoEspecial.setValue(auto.getTarifa().getIngresoEspecial());
         comboBoxSitua.setSelectedItem(auto.getSituacion().getNombSituacion());
      }
   }
}
