package servicios;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Marca;

public class MarcaServicios {

   private Marca recuperar(ResultSet result) {
      Marca marca = null;
      try {
         marca = new Marca();
         marca.setCodMarca(result.getInt(1));
         marca.setNombMarca(result.getString(2));
      } catch (Exception e) {
         System.out.println(e);
      }
      return marca;
   }

   public Marca buscarMarca(int cod_marca) {
      Marca marca = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * "
                 + "FROM marca "
                 + "WHERE codmarca = " + cod_marca + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            marca = new Marca(result.getInt(1), result.getString(2));
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return marca;
   }

   public LinkedList<Marca> listMarcas() {
      LinkedList<Marca> List = new LinkedList<Marca>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * "
                 + "FROM marca;";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Marca marca = recuperar(result);
            List.add(marca);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }
}
