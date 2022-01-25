package inicial;

import javax.swing.UIManager;

public class ClaseInicial {

   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
      } catch (Throwable e) {
         e.printStackTrace();
      }
      Splash sp = new Splash();
      sp.setLocationRelativeTo(null);
      sp.setVisible(true);
   }
}
