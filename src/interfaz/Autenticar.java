package interfaz;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import auxiliar.GestorRecursos;
import auxiliar.Utiles;
import auxiliar.*;
import modelo.Rol;
import modelo.Usuario;
import servicios.LocalizacionServicios;

import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.Color;

public class Autenticar extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textNombreUsuario;
   private JPasswordField passwordContraseña;
   private JButton btnAceptar;
   private JButton btnCerrar;

   public Autenticar() {

      GestorRecursos.T3.play();
      setIconImage(Toolkit.getDefaultToolkit().getImage(Autenticar.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
      setResizable(false);
      setTitle("Informaci\u00F3n de Usuario");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 483, 331);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel labelImg = new JLabel("");
      labelImg.setLocation(26, 105);
      labelImg.setSize(425, 81);
      labelImg.setIcon(Utiles.imagenAjustadaDefin(425, 81, "/recursos/imagen.png", 110));

      getContentPane().add(labelImg);

      Panel panel = new Panel();
      panel.setBounds(5, 274, 468, 23);

      contentPane.add(panel);
      panel.setLayout(new GridLayout(0, 2, 4, 0));

      btnAceptar = new JButton("Aceptar");
      btnAceptar.setForeground(Color.BLACK);

      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               Usuario x = LocalizacionServicios.getServicios().usuarioServicios.buscarUsuario(textNombreUsuario.getText());
               if (x != null) {
                  if (x.getPassword().equals(Encriptar.getMd5(new String(passwordContraseña.getPassword())))) {
                     MenuPrincipal frame = new MenuPrincipal(x);
                     frame.setLocationRelativeTo(null);
                     frame.setVisible(true);
                     dispose();
                  } else
                     JOptionPane.showMessageDialog(Autenticar.this, "Contraseña incorrecta");
               } else
                  JOptionPane.showMessageDialog(Autenticar.this, "Usuario Incorrecto");
            } catch (Exception o) {
               JOptionPane.showMessageDialog(Autenticar.this, "Error en la Conexión");
            }
         }
      });
      panel.add(btnAceptar);

      getRootPane().setDefaultButton(btnAceptar);

      btnCerrar = new JButton("Cerrar");
      btnCerrar.addMouseListener(new MouseAdapter() {
      });
      btnCerrar.setForeground(Color.BLACK);
      btnCerrar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Autenticar.this.dispose();
         }
      });
      panel.add(btnCerrar);
      btnCerrar.setMnemonic('c');

      JLabel lblSistemaParaAutomatizacion = new JLabel("Sistema para la Renta de Autos");
      lblSistemaParaAutomatizacion.setFont(new Font("Arial", Font.BOLD, 26));
      lblSistemaParaAutomatizacion.setBounds(40, 21, 397, 81);
      contentPane.add(lblSistemaParaAutomatizacion);

      JLabel lblIntroduzcaLasCredenciales = new JLabel("Introduzca las credenciales de acceso");
      lblIntroduzcaLasCredenciales.setFont(new Font("Arial", Font.PLAIN, 16));
      lblIntroduzcaLasCredenciales.setBounds(114, 197, 276, 23);
      contentPane.add(lblIntroduzcaLasCredenciales);

      JLabel lblNewLabel = new JLabel("Usuario:");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel.setBounds(43, 231, 67, 23);
      contentPane.add(lblNewLabel);

      textNombreUsuario = new JTextField();
      textNombreUsuario.setBounds(114, 233, 107, 20);
      contentPane.add(textNombreUsuario);
      textNombreUsuario.setColumns(10);

      JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
      lblContrasea.setFont(new Font("Arial", Font.BOLD, 13));
      lblContrasea.setBounds(264, 231, 86, 23);
      contentPane.add(lblContrasea);

      passwordContraseña = new JPasswordField();
      passwordContraseña.setBounds(347, 233, 107, 20);
      contentPane.add(passwordContraseña);

      JLabel lblInformtica = new JLabel((String) null);
      lblInformtica.setVerticalAlignment(SwingConstants.BOTTOM);
      lblInformtica.setHorizontalAlignment(SwingConstants.CENTER);
      lblInformtica.setFont(new Font("Arial", Font.BOLD, 18));
      lblInformtica.setBounds(171, 79, 135, 23);
      contentPane.add(lblInformtica);
   }
}
