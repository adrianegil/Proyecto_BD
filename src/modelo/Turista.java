package modelo;

public class Turista {

   private int codTuris;
   private long numPaspTuris;
   private String nombTuris;
   private String apellTuris;
   private String sexTuris;
   private int edadTuris;
   private long contactTurist;
   private Pais paisTuris;

   @Override
   public String toString() {
      return nombTuris + " " + apellTuris + " " + "de " + paisTuris;
   }

   public Turista() {
      super();
   }

   public Turista(int codTuris, long numPaspTuris, String nombTuris, String apellTuris, String sexTuris, int edadTuris,
                  long contactTurist, Pais paisTuris) {
      super();
      this.codTuris = codTuris;
      this.numPaspTuris = numPaspTuris;
      this.nombTuris = nombTuris;
      this.apellTuris = apellTuris;
      this.sexTuris = sexTuris;
      this.edadTuris = edadTuris;
      this.contactTurist = contactTurist;
      this.paisTuris = paisTuris;
   }

   public int getEdadTuris() {
      return edadTuris;
   }

   public void setEdadTuris(int edadTuris) {
      this.edadTuris = edadTuris;
   }

   public int getCodTuris() {
      return codTuris;
   }

   public void setCodTuris(int codTuris) {
      this.codTuris = codTuris;
   }

   public long getNumPaspTuris() {
      return numPaspTuris;
   }

   public void setNumPaspTuris(long numPaspTuris) {
      this.numPaspTuris = numPaspTuris;
   }

   public String getNombTuris() {
      return nombTuris;
   }

   public void setNombTuris(String nombTuris) {
      this.nombTuris = nombTuris;
   }

   public String getApellTuris() {
      return apellTuris;
   }

   public void setApellTuris(String apellTuris) {
      this.apellTuris = apellTuris;
   }

   public String getSexTuris() {
      return sexTuris;
   }

   public void setSexTuris(String sexTuris) {
      this.sexTuris = sexTuris;
   }

   public long getContactTurist() {
      return contactTurist;
   }

   public void setContactTurist(long contactTurist) {
      this.contactTurist = contactTurist;
   }

   public Pais getPaisTuris() {
      return paisTuris;
   }

   public void setPaisTuris(Pais paisTuris) {
      this.paisTuris = paisTuris;
   }
}
