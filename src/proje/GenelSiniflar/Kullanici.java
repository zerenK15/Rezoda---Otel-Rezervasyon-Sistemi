package proje.GenelSiniflar;

public abstract class Kullanici {

    private String kullaniciAdi;
    private String sifre;

    public Kullanici(String kullaniciAdi, String sifre) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }

    public String getKullaniciAdi() { return kullaniciAdi; }
    public String getSifre() { return sifre; }

    public abstract void rolunuSoyle();
}
