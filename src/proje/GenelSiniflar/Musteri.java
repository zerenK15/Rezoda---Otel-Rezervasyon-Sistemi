package proje.GenelSiniflar;

public class Musteri extends Kullanici {

	private String adSoyad;

    public Musteri(String kullaniciAdi, String sifre, String adSoyad) {
        super(kullaniciAdi, sifre);
        this.adSoyad = adSoyad;
    }

    public String getAdSoyad() { return adSoyad; }
    
    @Override
    public void rolunuSoyle() {
        System.out.println("Ben sisteme kayıtlı bir müşteriyim.");
    }
}
