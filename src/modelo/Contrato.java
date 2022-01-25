package modelo;

import java.util.Date;

public class Contrato {

   private Auto auto;
   private Turista turista;
   private int codContrat;
   private Date fechInicio;
   private Date fechFin;
   private int cantDiasPorr;
   private String formPago;
   private Chofer chofer = null;
   private Date fechEntregAuto = null;
   private Double importeTotal = null;
   private String nombContratista = null;

   public Contrato() {
      super();
   }

   public Auto getAuto() {
      return auto;
   }

   public void setAuto(Auto auto) {
      this.auto = auto;
   }

   public Turista getTurista() {
      return turista;
   }

   public void setTurista(Turista turista) {
      this.turista = turista;
   }

   public int getCodContrat() {
      return codContrat;
   }

   public void setCodContrat(int codContrat) {
      this.codContrat = codContrat;
   }

   public Date getFechInicio() {
      return fechInicio;
   }

   public void setFechInicio(Date fechInicio) {
      this.fechInicio = fechInicio;
   }

   public Date getFechFin() {
      return fechFin;
   }

   public void setFechFin(Date fechFin) {
      this.fechFin = fechFin;
   }

   public int getCantDiasPorr() {
      return cantDiasPorr;
   }

   public void setCantDiasPorr(int cantDiasPorr) {
      this.cantDiasPorr = cantDiasPorr;
   }

   public String getFormPago() {
      return formPago;
   }

   public void setFormPago(String formPago) {
      this.formPago = formPago;
   }

   public Chofer getChofer() {
      return chofer;
   }

   public void setChofer(Chofer chofer) {
      this.chofer = chofer;
   }

   public Date getFechEntregAuto() {
      return fechEntregAuto;
   }

   public void setFechEntregAuto(Date fechEntregAuto) {
      this.fechEntregAuto = fechEntregAuto;
   }

   public Double getImporteTotal() {
      return importeTotal;
   }

   public void setImporteTotal(Double importeTotal) {
      this.importeTotal = importeTotal;
   }

   public String getNombContratista() {
      return nombContratista;
   }

   public void setNombContratista(String nombContratista) {
      this.nombContratista = nombContratista;
   }
}
