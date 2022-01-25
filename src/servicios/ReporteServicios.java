package servicios;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;

public class ReporteServicios {

   public static ReporteServicios reportes = new ReporteServicios();

   private Connection myConexion = null;

   public ReporteServicios() {
      super();
      this.myConexion = LocalizacionServicios.getConnection();
   }

   public void CargarReporteListadoAutos() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ListadoAutos.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteListadoChoferes() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ListadoChoferes.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteListadoTurista() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ListadoTurista.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteListadoSituacionAutos() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ListadoSituacionAutos.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteListadoTuristasIncumplidores() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ListadoIncumplidores.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteListadoContratos() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ListadoContratos.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteContratosXMarcasModelos() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ContratosXMarcasModelos.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }

   public void CargarReporteContratosXPaises() {
      try {
         JasperPrint print = JasperFillManager.fillReport("src/reportes/ContratosXPaises.jasper", null, myConexion);
         JasperViewer view = new JasperViewer(print, false);
         view.setVisible(true);
      } catch (JRException e2) {
         e2.printStackTrace();
      }
   }
}
