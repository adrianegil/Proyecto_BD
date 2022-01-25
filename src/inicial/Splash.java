package inicial;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;

import auxiliar.GestorRecursos;
import auxiliar.HiloBarra;
import interfaz.Autenticar;

import javax.swing.event.ChangeEvent;
import java.awt.Font;

public class Splash extends JWindow {
   public static JProgressBar MyprogressBar;

   public Splash() {

      getContentPane().setBackground(Color.WHITE);
      ImageIcon img = new ImageIcon(Splash.class.getResource("/recursos/imagen.png"));
      JPanel panel = (JPanel) getContentPane();
      getContentPane().setLayout(null);
      JLabel label = new JLabel(img);
      label.setFont(new Font("Tahoma", Font.ITALIC, 13));
      label.setLocation(0, 0);
      label.setSize(324, 137);
      panel.add(label);

      MyprogressBar = new JProgressBar();
      MyprogressBar.setStringPainted(true);
      MyprogressBar.setVisible(false);
      MyprogressBar.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            if (MyprogressBar.getValue() == 100) {
               Autenticar frame = new Autenticar();
               Splash.this.dispose();
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);
            }
         }
      });
      MyprogressBar.setBounds(0, 156, 324, 19);
      getContentPane().add(MyprogressBar);

      JLabel lblCargando = new JLabel("Cargando..");
      lblCargando.setFont(new Font("Tahoma", Font.BOLD, 12));
      lblCargando.setBounds(10, 136, 74, 14);
      getContentPane().add(lblCargando);

      JLabel lblNewLabel = new JLabel("GilSoft");
      lblNewLabel.setForeground(Color.RED);
      lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
      lblNewLabel.setBounds(222, 125, 46, 14);
      getContentPane().add(lblNewLabel);

      JLabel lblNewLabel_1 = new JLabel("Todos los derechos reservados");
      lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
      lblNewLabel_1.setBounds(175, 137, 149, 14);
      getContentPane().add(lblNewLabel_1);
      setSize(324, 175);

      HiloBarra hilo = new HiloBarra();
      hilo.start();
      GestorRecursos.T2.play();
   }
}
