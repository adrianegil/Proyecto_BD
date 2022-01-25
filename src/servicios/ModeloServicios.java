package servicios;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Marca;
import modelo.Modelo;

public class ModeloServicios {

   private Modelo recuperar(ResultSet result) {
      Modelo modelo = null;
      try {
         modelo = new Modelo();
         modelo.setCodModelo(result.getInt(1));
         modelo.setNombModelo(result.getString(2));
         modelo.setMarca(LocalizacionServicios.getServicios().marcaServicios.buscarMarca(result.getInt(3)));
      } catch (Exception e) {
         System.out.println(e);
      }
      return modelo;
   }

   public Modelo buscarModelo(int cod_modelo) {
      Modelo modelo = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM modelo WHERE modelo.codmodelo = " + cod_modelo + "; ";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            modelo = recuperar(result);
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return modelo;
   }

   public LinkedList<Modelo> listModelos(Marca mar) {
      LinkedList<Modelo> List = new LinkedList<Modelo>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * "
                 + " FROM modelo  "
                 + " WHERE modelo.codmarca =" + mar.getCodMarca() + "; ";

         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Modelo modelo = recuperar(result);
            List.add(modelo);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }

   public LinkedList<Modelo> listModelos() {
      LinkedList<Modelo> List = new LinkedList<Modelo>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM modelo ;";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Modelo modelo = recuperar(result);
            List.add(modelo);
         }
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }
}
