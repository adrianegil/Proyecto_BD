package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import auxiliar.Encriptar;
import modelo.Rol;
import modelo.Usuario;
import servicios.LocalizacionServicios;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdministrarUsuario extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTextField textFieldNomUsuario;
   private JTable table;
   private LinkedList<Usuario> listUsuarios;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();
   private JPasswordField passwordField_Password;
   private JPasswordField passwordField_RepeatPassword;
   JButton btnModificar;
   int posicion;
   private JTextField textField_NombreyApell;

   public AdministrarUsuario() {

      setTitle("Administrar Usuarios");
      setResizable(false);
      setBounds(100, 100, 364, 472);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);

      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Usuario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      panel_1.setBounds(10, 237, 339, 165);
      contentPanel.add(panel_1);
      panel_1.setLayout(null);

      JLabel lblNewLabel = new JLabel("Usuario");
      lblNewLabel.setBounds(10, 53, 86, 17);
      panel_1.add(lblNewLabel);
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

      textFieldNomUsuario = new JTextField();
      textFieldNomUsuario.setBounds(10, 76, 142, 20);
      panel_1.add(textFieldNomUsuario);
      textFieldNomUsuario.setColumns(10);

      JLabel lblContrasea = new JLabel("Contrase\u00F1a");
      lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblContrasea.setBounds(10, 107, 112, 14);
      panel_1.add(lblContrasea);

      JLabel lblRol = new JLabel("Rol");
      lblRol.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblRol.setBounds(176, 54, 46, 14);
      panel_1.add(lblRol);

      JComboBox comboBox_Rol = new JComboBox();
      comboBox_Rol.setFont(new Font("Tahoma", Font.PLAIN, 12));
      comboBox_Rol.setModel(new DefaultComboBoxModel(new String[]{"Administrador", "Trabajador", "Ejecutivo"}));
      comboBox_Rol.setBounds(174, 75, 112, 20);
      panel_1.add(comboBox_Rol);

      JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a");
      lblConfirmarContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblConfirmarContrasea.setBounds(176, 109, 162, 14);
      panel_1.add(lblConfirmarContrasea);

      passwordField_Password = new JPasswordField();
      passwordField_Password.setBounds(10, 132, 142, 20);
      panel_1.add(passwordField_Password);

      passwordField_RepeatPassword = new JPasswordField();
      passwordField_RepeatPassword.setBounds(176, 132, 142, 20);
      panel_1.add(passwordField_RepeatPassword);

      JLabel lblNombreYApellidos = new JLabel("Nombre y Apellidos:");
      lblNombreYApellidos.setBounds(10, 22, 142, 20);
      panel_1.add(lblNombreYApellidos);
      lblNombreYApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));

      textField_NombreyApell = new JTextField();
      textField_NombreyApell.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
               getToolkit().beep();
            }
         }
      });
      textField_NombreyApell.setBounds(176, 23, 142, 20);
      panel_1.add(textField_NombreyApell);
      textField_NombreyApell.setColumns(10);

      JButton btnEliminar = new JButton("Eliminar");
      btnEliminar.setEnabled(false);
      btnEliminar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String nombUsuario = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
            int resp = JOptionPane.showConfirmDialog(AdministrarUsuario.this, "Realmente desea eliminar el usuario", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
               try {
                  LocalizacionServicios.getServicios().usuarioServicios.eliminarUsuario(nombUsuario);
               } catch (ClassNotFoundException | SQLException e1) {
                  e1.printStackTrace();
               }
            }
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            actualizarTabla();
         }
      });
      btnEliminar.setBounds(261, 187, 89, 23);
      contentPanel.add(btnEliminar);

      btnModificar = new JButton("Modificar");
      btnModificar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            String nombUsuario = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
            Usuario user = LocalizacionServicios.getServicios().usuarioServicios.buscarUsuario(nombUsuario);

            if (textFieldNomUsuario.getText().isEmpty() || passwordField_Password.getText().isEmpty()
                    || passwordField_RepeatPassword.getText().isEmpty() || textField_NombreyApell.getText().isEmpty())
               JOptionPane.showMessageDialog(null, "Debe llenar todos los datos");
            else if (Encriptar.getMd5(new String(passwordField_Password.getPassword())).equals(Encriptar.getMd5(new String(passwordField_RepeatPassword.getPassword())))) {
               String nombRol = comboBox_Rol.getSelectedItem().toString();
               int codRol;
               if (nombRol.equalsIgnoreCase("Administrador"))
                  codRol = 1;
               else if (nombRol.equalsIgnoreCase("Ejecutivo"))
                  codRol = 3;
               else
                  codRol = 2;

               Rol rol = new Rol(codRol, nombRol);
               user.setNombUsuario(textFieldNomUsuario.getText());
               user.setNombyApell(textField_NombreyApell.getText());
               user.setPassword(passwordField_Password.getText());
               user.setRol(rol);

               int resp = JOptionPane.showConfirmDialog(AdministrarUsuario.this, "Realmente desea modificar el usuario", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);

               if (resp == JOptionPane.YES_OPTION) {
                  LocalizacionServicios.getServicios().usuarioServicios.actualizarUsuario(user);
                  JOptionPane.showMessageDialog(null, " Se ha modificado el usuario correctamente");
                  textField_NombreyApell.setText("");
                  textFieldNomUsuario.setText("");
                  passwordField_Password.setText("");
                  passwordField_RepeatPassword.setText("");
                  actualizarTabla();
               }
            }
         }
      });
      btnModificar.setEnabled(false);
      btnModificar.setBounds(160, 187, 89, 23);
      contentPanel.add(btnModificar);

      JButton btnCancelar = new JButton("Cancelar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });
      btnCancelar.setBounds(261, 413, 89, 23);
      contentPanel.add(btnCancelar);

      JButton btnInsertar = new JButton("Insertar");
      btnInsertar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (textFieldNomUsuario.getText().isEmpty() || passwordField_Password.getText().isEmpty()
                    || passwordField_RepeatPassword.getText().isEmpty() || textField_NombreyApell.getText().isEmpty())
               JOptionPane.showMessageDialog(null, "Debe llenar todos los datos");
            else if (Encriptar.getMd5(new String(passwordField_Password.getPassword())).equals(Encriptar.getMd5(new String(passwordField_RepeatPassword.getPassword())))) {
               boolean seguir = true;
               listUsuarios = LocalizacionServicios.getServicios().usuarioServicios.listUsuarios();
               for (Usuario usuario : listUsuarios) {
                  if (usuario.getNombUsuario().equals(textFieldNomUsuario.getText()) && usuario.getNombyApell().equals(textField_NombreyApell.getText())) {
                     seguir = false;
                  }
               }
               if (seguir) {
                  String nombRol = comboBox_Rol.getSelectedItem().toString();
                  int codRol;

                  if (nombRol.equalsIgnoreCase("Administrador"))
                     codRol = 1;
                  else if (nombRol.equalsIgnoreCase("Ejecutivo"))
                     codRol = 3;
                  else
                     codRol = 2;
                  Rol rol = new Rol(codRol, nombRol);

                  Usuario user = new Usuario();
                  user.setNombUsuario(textFieldNomUsuario.getText());
                  user.setNombyApell(textField_NombreyApell.getText());
                  user.setPassword(passwordField_Password.getText());
                  user.setRol(rol);

                  LocalizacionServicios.getServicios().usuarioServicios.insertarUsuario(user);
                  JOptionPane.showMessageDialog(null, " Se inserto el usuario correctamente");

                  textFieldNomUsuario.setText("");
                  passwordField_Password.setText("");
                  passwordField_RepeatPassword.setText("");
                  actualizarTabla();
               } else {
                  JOptionPane.showMessageDialog(AdministrarUsuario.this, "Ya existe este usuario", "Error al insertar", JOptionPane.ERROR_MESSAGE);
               }
            } else
               JOptionPane.showMessageDialog(AdministrarUsuario.this, "Password Erroneo");
         }
      });
      btnInsertar.setBounds(160, 413, 89, 23);
      contentPanel.add(btnInsertar);

      JPanel panel = new JPanel();
      panel.setBounds(10, 11, 339, 165);
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Usuarios", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      contentPanel.add(panel);
      panel.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 21, 319, 133);
      panel.add(scrollPane);

      table = new JTable();
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            posicion = table.getSelectedRow();
            if (posicion != -1) {
               btnModificar.setEnabled(true);
               btnEliminar.setEnabled(true);
               String nombUsuario = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
               Usuario user = LocalizacionServicios.getServicios().usuarioServicios.buscarUsuario(nombUsuario);
               textField_NombreyApell.setText(user.getNombyApell());
               textFieldNomUsuario.setText(user.getNombUsuario());
            }
         }
      });
      scrollPane.setViewportView(table);

      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new LineBorder(new Color(0)));
      panel_2.setBounds(10, 221, 339, 1);
      contentPanel.add(panel_2);

      actualizarTabla();
   }

   public void actualizarTabla() {

      Object[] aux = new Object[]{"Usuario", "Nombre y Apellidos", "Rol"};
      defaultTableModel = new DefaultTableModel(aux, 0);

      try {
         listUsuarios = LocalizacionServicios.getServicios().usuarioServicios.listUsuarios();
      } catch (Exception e1) {
         JOptionPane.showMessageDialog(this, "Error en la Base de Datos");
         dispose();
      }
      for (Usuario usuario : listUsuarios) {
         defaultTableModel.addRow(new Object[]{usuario.getNombUsuario(), usuario.getNombyApell(), usuario.getRol().getNombRol()});
      }
      table.setModel(defaultTableModel);
   }
}
