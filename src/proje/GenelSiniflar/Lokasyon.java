package proje.GenelSiniflar;

public class Lokasyon {
	private String sehir;
    private String ilce;

    public Lokasyon(String sehir, String ilce) {
        this.sehir = sehir;
        this.ilce = ilce;
    }

    public String getSehir() { return sehir; }
    public void setSehir(String sehir) { this.sehir = sehir; }
    
    public String getIlce() { return ilce; }
    public void setIlce(String ilce) { this.ilce = ilce; }
}
