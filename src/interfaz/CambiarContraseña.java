package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import auxiliar.Encriptar;
import modelo.Usuario;
import servicios.LocalizacionServicios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiarContraseña extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JPasswordField passwordFieldContraseñaActual;
   private JPasswordField passwordField_NuevaContr;
   private JPasswordField passwordField_ConfirmContr;
   private Usuario usuario;
   private JLabel lblNewLabel_Usuario;
   private JLabel lblNewLabel_NombyApell;
   private JButton btnAceptar;


   public CambiarContraseña(Usuario usuario) {

      this.usuario = usuario;

      setTitle("Cambiar Contrase\u00F1a");
      setResizable(false);
      setBounds(100, 100, 382, 308);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);

      JPanel panel = new JPanel();
      panel.setBounds(10, 22, 359, 206);
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Usuario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      contentPanel.add(panel);
      panel.setLayout(null);

      JLabel lblUsuario = new JLabel("Usuario:");
      lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblUsuario.setBounds(10, 21, 155, 23);
      panel.add(lblUsuario);

      JLabel lblNombreYApellidos = new JLabel("Nombre y Apellidos:");
      lblNombreYApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNombreYApellidos.setBounds(10, 55, 155, 23);
      panel.add(lblNombreYApellidos);

      JLabel lblContrasea = new JLabel("Contrase\u00F1a actual:");
      lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblContrasea.setBounds(10, 104, 155, 23);
      panel.add(lblContrasea);

      JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a:");
      lblNuevaContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNuevaContrasea.setBounds(10, 138, 155, 23);
      panel.add(lblNuevaContrasea);

      JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a:");
      lblConfirmarContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblConfirmarContrasea.setBounds(10, 172, 168, 23);
      panel.add(lblConfirmarContrasea);

      lblNewLabel_NombyApell = new JLabel("New label");
      lblNewLabel_NombyApell.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNewLabel_NombyApell.setBounds(182, 56, 167, 20);
      panel.add(lblNewLabel_NombyApell);

      lblNewLabel_Usuario = new JLabel("New label");
      lblNewLabel_Usuario.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblNewLabel_Usuario.setBounds(182, 21, 167, 20);
      panel.add(lblNewLabel_Usuario);

      passwordFieldContraseñaActual = new JPasswordField();
      passwordFieldContraseñaActual.setBounds(175, 107, 168, 20);
      panel.add(passwordFieldContraseñaActual);

      passwordField_NuevaContr = new JPasswordField();
      passwordField_NuevaContr.setBounds(175, 141, 168, 20);
      panel.add(passwordField_NuevaContr);

      passwordField_ConfirmContr = new JPasswordField();
      passwordField_ConfirmContr.setBounds(175, 172, 168, 20);
      panel.add(passwordField_ConfirmContr);

      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new LineBorder(new Color(0)));
      panel_1.setBounds(10, 90, 339, 1);
      panel.add(panel_1);

      JButton btnCancelar = new JButton("Cancelar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });
      btnCancelar.setBounds(280, 245, 89, 23);
      contentPanel.add(btnCancelar);

      btnAceptar = new JButton("Aceptar");
      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (passwordFieldContraseñaActual.getText().isEmpty() || passwordField_NuevaContr.getText().isEmpty() || passwordField_ConfirmContr.getText().isEmpty())
               JOptionPane.showMessageDialog(CambiarContraseña.this, "Todos los datos deben estar rellenados");

            else if (CambiarContraseña.this.usuario.getPassword().equals(Encriptar.getMd5(new String(passwordFieldContraseñaActual.getPassword())))) {
               if (Encriptar.getMd5(new String(passwordField_NuevaContr.getPassword())).equals(Encriptar.getMd5(new String(passwordField_ConfirmContr.getPassword())))) {
                  int resp = JOptionPane.showConfirmDialog(CambiarContraseña.this, "Realmente desea cambiar su contraseña ", "Confirmar ", JOptionPane.YES_NO_CANCEL_OPTION);
                  if (resp == JOptionPane.YES_OPTION) {
                     CambiarContraseña.this.usuario.setPassword(passwordField_NuevaContr.getText());
                     LocalizacionServicios.getServicios().usuarioServicios.actualizarUsuario(CambiarContraseña.this.usuario);
                     JOptionPane.showMessageDialog(CambiarContraseña.this, "Se ha cambiado correctamente la contraseña", "Confirmar", JOptionPane.INFORMATION_MESSAGE);
                     dispose();
                  }
               } else
                  JOptionPane.showMessageDialog(CambiarContraseña.this, "No ha confirmado correctamente la contraseña");
            } else
               JOptionPane.showMessageDialog(CambiarContraseña.this, "La contraseña actual es incorrecta");
         }
      });
      btnAceptar.setBounds(181, 245, 89, 23);
      contentPanel.add(btnAceptar);

      actualizar();

   }

   public void actualizar() {

      lblNewLabel_Usuario.setText(usuario.getNombUsuario());
      lblNewLabel_NombyApell.setText(usuario.getNombyApell());
   }
}
