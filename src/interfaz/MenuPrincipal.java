package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import auxiliar.GestorRecursos;
import auxiliar.Utiles;
import inicial.Splash;
import modelo.Usuario;
import servicios.ReporteServicios;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MenuPrincipal extends JFrame {

   private JPanel contentPane;
   private JMenu mnGestionDeUsuario;
   private JMenu mnReportes;
   private JMenuItem mntmAdministrarUsuarios;
   private JMenu mnListados;
   private Usuario usuario;
   private JMenuItem mntmContratos;
   private JLabel label_NombreyApell;
   private JLabel lblNewLabel_Rol;
   private JLabel label_fechHoy;
   private JLabel labelDia;


   public MenuPrincipal(Usuario usuario) {
      setTitle("Men\u00FA Principal");
      setResizable(false);

      this.usuario = usuario;

      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Utiles.confirmarCerrar(MenuPrincipal.this);
         }
      });
      setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/recursos/ICON.png")));
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      setBounds(100, 100, 700, 575);

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      JMenu mnArchivo = new JMenu("Archivo");
      mnArchivo.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseMoved(MouseEvent e) {
            mnArchivo.setForeground(Color.red);
            mnArchivo.setBorder(new LineBorder(Color.red));
         }
      });
      mnArchivo.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseExited(MouseEvent e) {
            mnArchivo.setForeground(Color.black);
            mnArchivo.setBorder(null);

         }
      });
      mnArchivo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/recursos/ic_menu_home.png")));

      menuBar.add(mnArchivo);

      JMenuItem mntmSalir = new JMenuItem("Salir");
      mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
      mntmSalir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Utiles.confirmarCerrar(MenuPrincipal.this);
         }
      });

      mntmAdministrarUsuarios = new JMenuItem("Administrar Usuarios");
      mnArchivo.add(mntmAdministrarUsuarios);
      mntmAdministrarUsuarios.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AdministrarUsuario dialog = new AdministrarUsuario();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
         }
      });

      mnGestionDeUsuario = new JMenu("Gesti\u00F3n de Usuario");
      mnArchivo.add(mnGestionDeUsuario);

      JMenuItem mntmCambiarDeUsuario = new JMenuItem("Cambiar de Usuario");
      mntmCambiarDeUsuario.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Autenticar dialog = new Autenticar();
            MenuPrincipal.this.dispose();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mnGestionDeUsuario.add(mntmCambiarDeUsuario);

      JMenuItem mntmCambiarContrasea = new JMenuItem("Cambiar Contrase\u00F1a");
      mntmCambiarContrasea.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            CambiarContraseña dialog = new CambiarContraseña(MenuPrincipal.this.usuario);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
         }
      });
      mnGestionDeUsuario.add(mntmCambiarContrasea);
      mnArchivo.add(mntmSalir);

      JMenu mnOpciones = new JMenu("Opciones");
      mnOpciones.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseMoved(MouseEvent e) {
            mnOpciones.setForeground(Color.red);
            mnOpciones.setBorder(new LineBorder(Color.red));

         }
      });
      mnOpciones.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseExited(MouseEvent e) {
            mnOpciones.setForeground(Color.black);
            mnOpciones.setBorder(null);

         }
      });
      mnOpciones.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/recursos/ic_menu_manage.png")));
      menuBar.add(mnOpciones);

      JMenuItem mntmAyuda = new JMenuItem("Ayuda");
      mnOpciones.add(mntmAyuda);
      mntmAyuda.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Ayuda dialog = new Ayuda();
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });

      mnListados = new JMenu("Listados");
      mnOpciones.add(mnListados);

      JMenuItem mntmAutos = new JMenuItem("Autos");
      mntmAutos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoAuto dialog = new ListadoAuto();
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mnListados.add(mntmAutos);

      JMenuItem mntmChoferes = new JMenuItem("Choferes");
      mntmChoferes.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoChofer dialog = new ListadoChofer();
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mnListados.add(mntmChoferes);

      JMenuItem mntmTuristas = new JMenuItem("Turistas");
      mntmTuristas.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            ListadoTurista dialog = new ListadoTurista();
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mnListados.add(mntmTuristas);

      mntmContratos = new JMenuItem("Gestionar Contratos");
      mnOpciones.add(mntmContratos);
      mntmContratos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoContrato dialog = new ListadoContrato(MenuPrincipal.this.usuario);
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });

      mnReportes = new JMenu("Reportes");
      mnReportes.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseMoved(MouseEvent e) {

            mnReportes.setForeground(Color.red);
            mnReportes.setBorder(new LineBorder(Color.red));
         }
      });
      mnReportes.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseExited(MouseEvent e) {
            mnReportes.setForeground(Color.black);
            mnReportes.setBorder(null);
         }
      });
      mnReportes.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/recursos/ic_menu_agenda.png")));

      menuBar.add(mnReportes);

      JMenuItem mntmListar = new JMenuItem("Listado de los turistas");
      mntmListar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteListadoTurista();
         }
      });
      mnReportes.add(mntmListar);

      JMenuItem mntmListadoDeLos = new JMenuItem("Listado de los autos");
      mntmListadoDeLos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteListadoAutos();
         }
      });
      mnReportes.add(mntmListadoDeLos);

      JMenuItem mntmListadoDeLos_1 = new JMenuItem("Listado de los contratos");
      mntmListadoDeLos_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteListadoContratos();
         }
      });
      mnReportes.add(mntmListadoDeLos_1);

      JMenuItem mntmListadoDeLos_2 = new JMenuItem("Listado de los choferes");
      mntmListadoDeLos_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteListadoChoferes();
         }
      });
      mnReportes.add(mntmListadoDeLos_2);

      JMenuItem mntmListadoDeLa = new JMenuItem("Listado de la situaci\u00F3n de los autos");
      mntmListadoDeLa.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteListadoSituacionAutos();
         }
      });
      mnReportes.add(mntmListadoDeLa);

      JMenuItem mntmListadosDeTuristas = new JMenuItem("Listados de turistas incumplidores de contrato");
      mntmListadosDeTuristas.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteListadoTuristasIncumplidores();
         }
      });
      mnReportes.add(mntmListadosDeTuristas);

      JMenuItem mntmResumenDeContratos = new JMenuItem("Resumen de contratos por marcas y modelos");
      mntmResumenDeContratos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteContratosXMarcasModelos();
         }
      });
      mnReportes.add(mntmResumenDeContratos);

      JMenuItem mntmResumenDeContratos_1 = new JMenuItem("Resumen de contratos por paises ");
      mntmResumenDeContratos_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ReporteServicios.reportes.CargarReporteContratosXPaises();
         }
      });
      mnReportes.add(mntmResumenDeContratos_1);

      JMenu mnAvercaDe = new JMenu("Acerca de");
      mnAvercaDe.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseMoved(MouseEvent e) {
            mnAvercaDe.setForeground(Color.red);
            mnAvercaDe.setBorder(new LineBorder(Color.red));
         }
      });
      mnAvercaDe.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseExited(MouseEvent e) {
            mnAvercaDe.setForeground(Color.black);
            mnAvercaDe.setBorder(null);
         }
      });

      JMenu mnConexin = new JMenu("Conexi\u00F3n");
      mnConexin.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseMoved(MouseEvent e) {
            mnConexin.setForeground(Color.red);
            mnConexin.setBorder(new LineBorder(Color.red));
         }
      });
      mnConexin.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseExited(MouseEvent e) {
            mnConexin.setForeground(Color.black);
            mnConexin.setBorder(null);

         }
      });
      mnConexin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/recursos/ic_menu_refresh.png")));
      menuBar.add(mnConexin);

      JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
      mntmDesconectar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      mnConexin.add(mntmDesconectar);
      mnAvercaDe.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/recursos/ic_menu_notifications.png")));

      menuBar.add(mnAvercaDe);

      JMenuItem mntmDesarrolladores = new JMenuItem("Desarrollador");
      mntmDesarrolladores.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Est. Adrian E Gil", "GilSoft", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      mnAvercaDe.add(mntmDesarrolladores);

      JMenuItem mntmVersinDelSoftware = new JMenuItem("Versi\u00F3n del Software");
      mntmVersinDelSoftware.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Versión 1.0", "GilSoft", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      mnAvercaDe.add(mntmVersinDelSoftware);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);

      ImageIcon img = new ImageIcon(Splash.class.getResource("/recursos/imagen.png"));
      contentPane.setLayout(null);
      JLabel lblNewLabelImg = new JLabel();
      lblNewLabelImg.setBounds(0, 363, 694, 112);

      lblNewLabelImg.setIcon(Utiles.imagenAjustadaDefin(694, 112, "/recursos/imagen.png", 110));

      lblNewLabelImg.setBackground(Color.WHITE);
      contentPane.add(lblNewLabelImg);

      JLabel lblNewLabel_Img2 = new JLabel("");
      lblNewLabel_Img2.setBounds(0, 28, 694, 339);
      lblNewLabel_Img2.setIcon(Utiles.imagenAjustadaDefin(694, 339, "/recursos/Carro.jpg", 110));
      //lblNewLabel_Img2.setBorder(new LineBorder(Color.black));

      contentPane.add(lblNewLabel_Img2);

      JLabel lblBienbenido = new JLabel("Bienvenido");
      lblBienbenido.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblBienbenido.setBounds(261, 0, 90, 28);
      contentPane.add(lblBienbenido);

      label_NombreyApell = new JLabel("");
      label_NombreyApell.setFont(new Font("Tahoma", Font.BOLD, 15));
      label_NombreyApell.setBounds(487, 0, 207, 29);
      contentPane.add(label_NombreyApell);

      lblNewLabel_Rol = new JLabel("");
      lblNewLabel_Rol.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblNewLabel_Rol.setBounds(361, 0, 116, 28);
      contentPane.add(lblNewLabel_Rol);

      label_fechHoy = new JLabel("");
      label_fechHoy.setFont(new Font("Tahoma", Font.BOLD, 15));
      label_fechHoy.setBounds(135, 0, 116, 28);
      contentPane.add(label_fechHoy);

      labelDia = new JLabel("21 / 4 / 2019");
      labelDia.setFont(new Font("Tahoma", Font.BOLD, 15));
      labelDia.setBounds(35, 0, 90, 28);
      contentPane.add(labelDia);

      GestionarPermisos();
   }

   public void GestionarPermisos() {
      GregorianCalendar fecha = new GregorianCalendar();
      int dia = fecha.get(Calendar.DAY_OF_WEEK);
      String nombDia = "";
      if (dia == 1)
         nombDia = "Domingo";
      if (dia == 2)
         nombDia = "Lunes";
      if (dia == 3)
         nombDia = "Martes";
      if (dia == 4)
         nombDia = "Miércoles";
      if (dia == 5)
         nombDia = "Jueves";
      if (dia == 6)
         nombDia = "Viernes";
      if (dia == 7)
         nombDia = "Sábado";

      //label_fechHoy.setText(nombDia + ": "+fecha.get(Calendar.DATE)+" / " + (fecha.get(Calendar.MONTH)+1)+" / "+fecha.get(Calendar.YEAR));
      label_fechHoy.setText(Calendar.getInstance().getTime().getDate() + " / " + (Calendar.getInstance().getTime().getMonth() + 1) + " / " + (Calendar.getInstance().getTime().getYear() + 1900));
      labelDia.setText(nombDia);
      if (usuario.getRol().getCodRol() == 2) {
         mntmAdministrarUsuarios.setVisible(false);
         mnListados.setVisible(false);
         mnReportes.setVisible(false);

         lblNewLabel_Rol.setText("Trabajador:");
      } else if (usuario.getRol().getCodRol() == 1) {

         mnListados.setVisible(false);
         mnReportes.setVisible(false);
         mntmContratos.setVisible(false);
         lblNewLabel_Rol.setText("Administrador:");

      } else if (usuario.getRol().getCodRol() == 3) {
         mntmAdministrarUsuarios.setVisible(false);
         lblNewLabel_Rol.setText("Ejecutivo:");
      }
      label_NombreyApell.setText(usuario.getNombyApell());
   }
}
