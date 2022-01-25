package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import modelo.Tarifa;


public class TarifaServicios {

   public Tarifa buscarTarifa(int cod_tarifa) {
      Tarifa tarifa = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * From tarifa WHERE codtarifa = " + cod_tarifa + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next()) {
            tarifa = new Tarifa();
            tarifa.setCodTarifa(result.getInt(1));
            tarifa.setIngreso(result.getDouble(2));
            tarifa.setIngresoEspecial(result.getDouble(3));
         }
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return tarifa;
   }

   public Tarifa buscarTarifa(double ingreso, double ingreso_especial) {
      Tarifa tarifa = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM tarifa WHERE ingreso = " + ingreso + " AND ingresoespecial = " + ingreso_especial + " ;";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next()) {
            tarifa = new Tarifa(result.getInt(1), result.getDouble(2), result.getDouble(3));
				/*tarifa.setCodTarifa(result.getInt(1));
				tarifa.setIngreso(result.getDouble(2));
				tarifa.setIngresoEspecial(result.getDouble(3));	
				*/
         }
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return tarifa;
   }

   public void insertarTarifa(Tarifa tarifa) {
      try {
         String sqlSentenc = "SELECT tarifa_insertar(?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection().prepareStatement(sqlSentenc);
         preparCons.setDouble(1, tarifa.getIngreso());
         preparCons.setDouble(2, tarifa.getIngresoEspecial());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}
