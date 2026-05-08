package proje.GenelSiniflar;

import java.time.LocalDate;

public class Musteri extends Kullanici {

    // Temel bilgiler
    private String adSoyad;
    private String tcKimlik;
    private String telefon;
    
    // Rezervasyon bilgileri
    private int odaNumarasi;
    private LocalDate girisTarihi;
    private LocalDate cikisTarihi;

    // Boş Constructor
    public Musteri() {
        super("", "");
        this.adSoyad = "";
        this.tcKimlik = "";
        this.telefon = "";
        this.odaNumarasi = 0;
    }

    // Basit Constructor (orijinal)
    public Musteri(String kullaniciAdi, String sifre, String adSoyad) {
        super(kullaniciAdi, sifre);
        this.adSoyad = adSoyad;
        this.tcKimlik = "";
        this.telefon = "";
        this.odaNumarasi = 0;
    }

    // Tam Constructor (detaylı bilgiler)
    public Musteri(String kullaniciAdi, String sifre, String adSoyad, String tcKimlik, 
                   String telefon, int odaNumarasi, LocalDate girisTarihi, LocalDate cikisTarihi) {
        super(kullaniciAdi, sifre);
        this.adSoyad = adSoyad;
        this.tcKimlik = tcKimlik;
        this.telefon = telefon;
        this.odaNumarasi = odaNumarasi;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
    }

    // ========== GETTER VE SETTER ==========
    public String getAdSoyad() { return adSoyad; }
    public void setAdSoyad(String adSoyad) { this.adSoyad = adSoyad; }

    public String getTcKimlik() { return tcKimlik; }
    public void setTcKimlik(String tcKimlik) { this.tcKimlik = tcKimlik; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public int getOdaNumarasi() { return odaNumarasi; }
    public void setOdaNumarasi(int odaNumarasi) { this.odaNumarasi = odaNumarasi; }

    public LocalDate getGirisTarihi() { return girisTarihi; }
    public void setGirisTarihi(LocalDate girisTarihi) { this.girisTarihi = girisTarihi; }

    public LocalDate getCikisTarihi() { return cikisTarihi; }
    public void setCikisTarihi(LocalDate cikisTarihi) { this.cikisTarihi = cikisTarihi; }

    // ========== YARDIMCI METOTLAR ==========
    
    /**
     * Müşterinin otel de kalacağı gün sayısını hesaplar
     */
    public int getKalinacakGunSayisi() {
        if (girisTarihi == null || cikisTarihi == null) {
            return 0;
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(girisTarihi, cikisTarihi);
    }

    @Override
    public void rolunuSoyle() {
        System.out.println("Ben sisteme kayıtlı bir müşteriyim. Adım: " + adSoyad);
    }

    @Override
    public String toString() {
        return "Müşteri Bilgileri:\n" +
               "Kullanıcı Adı    : " + getKullaniciAdi() + "\n" +
               "Ad Soyad         : " + adSoyad + "\n" +
               "TC Kimlik        : " + tcKimlik + "\n" +
               "Telefon          : " + telefon + "\n" +
               "Oda No           : " + odaNumarasi + "\n" +
               "Giriş Tarihi     : " + girisTarihi + "\n" +
               "Çıkış Tarihi     : " + cikisTarihi + "\n" +
               "Kalınacak Gün    : " + getKalinacakGunSayisi() + " Gün\n" +
               "------------------------------";
    }
}
