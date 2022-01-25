package interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.Font;

public class Ayuda extends JDialog {

   private final JPanel contentPanel = new JPanel();

   public Ayuda() {

      setResizable(false);
      setBackground(Color.WHITE);
      setBounds(100, 100, 450, 300);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBackground(Color.WHITE);
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);

      JTextPane txtpnAyudaParaLa = new JTextPane();
      txtpnAyudaParaLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txtpnAyudaParaLa.setText("Ayuda para la buena manipulaci\u00F3n del Software\r\n\r\n1. En dependencia del rol que usted posea en la empresa har\u00E1 uso de las distintas funcionalidades del Software\r\n\r\n2. En la pesta\u00F1a Cambiar Contrase\u00F1a podr\u00E1 cambiar su contrase\u00F1a de autentificaci\u00F3n.\r\n\r\n3. Pulsando Escape saldr\u00E1 de la aplicaci\u00F3n.\r\n\r\n4. Ante cualquier duda o sugerencia contactar con los desarrolladores del Softwares.");
      txtpnAyudaParaLa.setEditable(false);
      GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
      gl_contentPanel.setHorizontalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addComponent(txtpnAyudaParaLa, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
      );
      gl_contentPanel.setVerticalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addComponent(txtpnAyudaParaLa, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
      );
      contentPanel.setLayout(gl_contentPanel);
   }
}
