package interfaz;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.Font;

import com.toedter.calendar.JDateChooser;

import modelo.Auto;
import modelo.Chofer;
import modelo.Contrato;
import modelo.Situacion;
import modelo.Turista;
import modelo.Usuario;
import servicios.LocalizacionServicios;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
//import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class InterfazContrato extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JLabel lblNewLabel_FechInicio;
   private JDateChooser dateChooser;
   private JComboBox comboBox_Auto;
   private JComboBox comboBox_Cliente;
   private JComboBox comboBox_Chofer;
   private JCheckBox chckbxChofer;
   private JLabel lblNewLabel_Contratista;
   private Usuario usuario;
   private Date FechInicio;
   private JComboBox comboBox_FormPago;

   public InterfazContrato(Usuario usuario) {

      GregorianCalendar fecha = new GregorianCalendar();
      FechInicio = new Date(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DATE));
      this.usuario = usuario;

      setTitle("Nuevo Contrato");
      setResizable(false);
      setBounds(100, 100, 588, 356);
      getContentPane().setLayout(null);
      contentPanel.setBounds(526, 0, 1, 450);
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel);
      contentPanel.setLayout(null);

      JPanel panel = new JPanel();

      panel.setBounds(10, 22, 563, 257);
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Introduzca los datos del Contrato", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      getContentPane().add(panel);
      panel.setLayout(null);

      JLabel lblAuto = new JLabel("Auto:");
      lblAuto.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblAuto.setBounds(10, 54, 83, 23);
      panel.add(lblAuto);

      JLabel lblNewLabel = new JLabel("Cliente:");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNewLabel.setBounds(10, 20, 83, 23);
      panel.add(lblNewLabel);

      JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio:");
      lblFechaDeInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblFechaDeInicio.setBounds(10, 88, 108, 23);
      panel.add(lblFechaDeInicio);

      JLabel lblFechaDeFin = new JLabel("Fecha de Fin:");
      lblFechaDeFin.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblFechaDeFin.setBounds(10, 122, 115, 23);
      panel.add(lblFechaDeFin);

      JLabel lblFormaDePago = new JLabel("Forma de Pago:");
      lblFormaDePago.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblFormaDePago.setBounds(10, 156, 115, 23);
      panel.add(lblFormaDePago);

      chckbxChofer = new JCheckBox("Chofer");
      chckbxChofer.setSelected(true);
      chckbxChofer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (chckbxChofer.isSelected()) {
               JOptionPane.showMessageDialog(InterfazContrato.this, "Seleccione un chofer de la lista");
               comboBox_Chofer.setVisible(true);
            } else {
               comboBox_Chofer.setVisible(false);
            }
         }
      });
      chckbxChofer.setFont(new Font("Tahoma", Font.BOLD, 14));
      chckbxChofer.setBounds(287, 168, 97, 23);
      panel.add(chckbxChofer);

      JLabel lblContratista = new JLabel("Contratista:");
      lblContratista.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblContratista.setBounds(10, 195, 97, 23);
      panel.add(lblContratista);

      lblNewLabel_FechInicio = new JLabel("New label");
      lblNewLabel_FechInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNewLabel_FechInicio.setBounds(125, 88, 108, 23);
      panel.add(lblNewLabel_FechInicio);

      dateChooser = new JDateChooser();
      dateChooser.setBounds(125, 122, 108, 20);
      panel.add(dateChooser);

      comboBox_Cliente = new JComboBox();
      comboBox_Cliente.setBounds(103, 23, 294, 20);
      panel.add(comboBox_Cliente);

      comboBox_Chofer = new JComboBox();
      comboBox_Chofer.setBounds(287, 198, 263, 20);
      panel.add(comboBox_Chofer);

      comboBox_Auto = new JComboBox();
      comboBox_Auto.setBounds(103, 54, 294, 20);
      panel.add(comboBox_Auto);

      comboBox_FormPago = new JComboBox();
      comboBox_FormPago.setModel(new DefaultComboBoxModel(new String[]{"Efectivo", "Cheque", "Tarjeta de Cr\u00E9dito"}));
      comboBox_FormPago.setBounds(125, 156, 150, 23);
      panel.add(comboBox_FormPago);

      lblNewLabel_Contratista = new JLabel("New label");
      lblNewLabel_Contratista.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNewLabel_Contratista.setBounds(125, 195, 150, 23);
      panel.add(lblNewLabel_Contratista);

      JPanel panel_1 = new JPanel();
      panel_1.setBounds(0, 304, 582, 23);
      getContentPane().add(panel_1);
      panel_1.setLayout(new GridLayout(0, 2, 0, 0));

      JButton btnAceptar = new JButton("Aceptar");
      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            GregorianCalendar fecha = new GregorianCalendar();
            Date FechInicio = new Date(fecha.get(Calendar.YEAR) - 1900, fecha.get(Calendar.MONTH), fecha.get(Calendar.DATE));

            Contrato cont = new Contrato();
            cont.setAuto((Auto) comboBox_Auto.getSelectedItem());
            cont.setTurista((Turista) comboBox_Cliente.getSelectedItem());
            cont.setFechInicio(FechInicio);
            cont.setFechFin(dateChooser.getDate());
            cont.setFormPago((String) comboBox_FormPago.getSelectedItem());
            cont.setNombContratista(InterfazContrato.this.usuario.getNombyApell());
            cont.setImporteTotal((double) 400);

            Auto auto = (Auto) comboBox_Auto.getSelectedItem();
				/*
				auto.setSituacion(new Situacion(2, "Alquilado"));
				LocalizacionServicios.getServicios().autoServicio.modificarAuto(auto);
				
				*/
            if (chckbxChofer.isSelected()) {
               Chofer chofer = (Chofer) comboBox_Chofer.getSelectedItem();
					/*	
					chofer.setDisponible(false);
					LocalizacionServicios.getServicios().choferServicios.modificarChofer(chofer);
					*/
               cont.setChofer((Chofer) comboBox_Chofer.getSelectedItem());
               try {
                  LocalizacionServicios.getServicios().contratoServicios.insertarContrato1(cont);
                  JOptionPane.showMessageDialog(InterfazContrato.this, "Se ha creado un nuevo Contrato");
               } catch (SQLException e2) {
                  getToolkit().beep();
                  JOptionPane.showMessageDialog(InterfazContrato.this, "La fecha de inicio no puede ser mayor que la fecha final");
               }
               InterfazContrato.this.dispose();

            } else {
               cont.setChofer(null);
               try {
                  LocalizacionServicios.getServicios().contratoServicios.insertarContrato2(cont);
                  JOptionPane.showMessageDialog(InterfazContrato.this, "Se ha creado un nuevo Contrato");
               } catch (SQLException e3) {
                  getToolkit().beep();
                  JOptionPane.showMessageDialog(InterfazContrato.this, e3.getMessage());
               }
               InterfazContrato.this.dispose();
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

      LinkedList<Turista> listTuris = LocalizacionServicios.getServicios().turistaServicios.listTurista();
      LinkedList<Auto> listAutos = LocalizacionServicios.getServicios().autoServicio.listAutos();
      LinkedList<Chofer> listChof = LocalizacionServicios.getServicios().choferServicios.listChoferes();

      for (Chofer chofer : listChof) {
         if (chofer.isDisponible())
            comboBox_Chofer.addItem(chofer);
      }
      for (Auto auto : listAutos) {
         if (auto.getSituacion().getCodSituacion() == 3) {
            comboBox_Auto.addItem(auto);
         }
      }
      for (Turista turis : listTuris) {
         comboBox_Cliente.addItem(turis);
      }
      lblNewLabel_Contratista.setText(usuario.getNombyApell());
      lblNewLabel_FechInicio.setText(FechInicio.getDate() + "/" + (FechInicio.getMonth() + 1) + "/" + FechInicio.getYear());
   }
}
