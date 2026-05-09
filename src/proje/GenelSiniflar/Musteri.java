package proje.GenelSiniflar;

public class Musteri extends Kullanici {
    private String adSoyad;
    private String kartAdi;
    private String kartNo;
    private String sonKullanma;

    public Musteri(String kullaniciAdi, String sifre, String adSoyad) {
        super(kullaniciAdi, sifre);
        this.adSoyad = adSoyad;
        this.kartAdi = "";
        this.kartNo = "";
        this.sonKullanma = "";
    }

    public String getAdSoyad() { return adSoyad; }
    public void setAdSoyad(String adSoyad) { this.adSoyad = adSoyad; }

    public String getKartAdi() { return kartAdi; }
    public void setKartAdi(String kartAdi) { this.kartAdi = kartAdi; }

    public String getKartNo() { return kartNo; }
    public void setKartNo(String kartNo) { this.kartNo = kartNo; }

    public String getSonKullanma() { return sonKullanma; }
    public void setSonKullanma(String sonKullanma) { this.sonKullanma = sonKullanma; }

    @Override
    public void rolunuSoyle() {
        System.out.println("Ben sisteme kayıtlı bir müşteriyim. Adım: " + adSoyad);
    }
}