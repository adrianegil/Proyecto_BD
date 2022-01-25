package servicios;

import java.sql.ResultSet;
import java.sql.Statement;

import modelo.Situacion;

public class SituacionServicios {

   private Situacion recuperar(ResultSet result) {
      Situacion situacion = null;
      try {
         situacion = new Situacion();
         situacion.setCodSituacion(result.getInt(1));
         situacion.setNombSituacion(result.getString(2));
      } catch (Exception e) {
         System.out.println(e);
      }
      return situacion;
   }

   public Situacion buscarSituacion(int cod_situacion) {
      Situacion situacion = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM situacion "
                 + "WHERE codsituac=" + cod_situacion + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            situacion = recuperar(result);
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return situacion;
   }
}
