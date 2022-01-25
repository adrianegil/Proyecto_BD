package servicios;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Pais;

public class PaisServicios {

   public LinkedList<Pais> listPaises() {
      LinkedList<Pais> PaisList = new LinkedList<Pais>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM pais ORDER BY nombpais ;";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Pais pais = new Pais(result.getInt(1), result.getString(2));
            PaisList.add(pais);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return PaisList;
   }

   public Pais buscarPais(String nombpais) {
      Pais pais = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT *  FROM  pais  WHERE nombpais = '" + nombpais + "';";

         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            pais = new Pais(result.getInt(1), result.getString(2));
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return pais;
   }

   public Pais buscarPais(int codpais) {
      Pais pais = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT *  FROM pais  WHERE codpais = " + codpais + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            pais = new Pais(result.getInt(1), result.getString(2));
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return pais;
   }
}
