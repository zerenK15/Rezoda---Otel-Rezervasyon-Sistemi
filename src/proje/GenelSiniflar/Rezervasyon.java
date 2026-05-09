package proje.GenelSiniflar;
import java.time.LocalDate;

public class Rezervasyon {
    private String rezervasyonId;
    private String kullaniciAdi; // musteriTc yerine kullaniciAdi geldi
    private String odaNo;
    private LocalDate girisTarihi;
    private LocalDate cikisTarihi;
    private boolean aktifMi;

    public Rezervasyon(String rezervasyonId, String kullaniciAdi, String odaNo, LocalDate girisTarihi, LocalDate cikisTarihi) {
        this.rezervasyonId = rezervasyonId;
        this.kullaniciAdi = kullaniciAdi;
        this.odaNo = odaNo;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        this.aktifMi = true;
    }

    public String getRezervasyonId() { return rezervasyonId; }
    public String getKullaniciAdi() { return kullaniciAdi; }
    public String getOdaNo() { return odaNo; }
    public LocalDate getGirisTarihi() { return girisTarihi; }
    public LocalDate getCikisTarihi() { return cikisTarihi; }
    public boolean isAktifMi() { return aktifMi; }
    public void setAktifMi(boolean aktifMi) { this.aktifMi = aktifMi; }
}