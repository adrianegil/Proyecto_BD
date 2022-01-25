package auxiliar;

import inicial.Splash;

public class HiloBarra extends Thread {

   public void run() {
      Splash.MyprogressBar.setVisible(true);
      for (int i = 0; i <= 100; i++) {
         Splash.MyprogressBar.setValue(i);
         try {
            Thread.sleep(30);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}
