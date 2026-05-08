package proje.GenelSiniflar;

import java.time.LocalDate;

public class Rezervasyon {
    private String rezervasyonId;
    private String musteriTc;
    private String odaNo;
    private LocalDate girisTarihi;
    private LocalDate cikisTarihi;
    private boolean aktifMi;

    public Rezervasyon(String rezervasyonId, String musteriTc, String odaNo, LocalDate girisTarihi, LocalDate cikisTarihi) {
        this.rezervasyonId = rezervasyonId;
        this.musteriTc = musteriTc;
        this.odaNo = odaNo;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        this.aktifMi = true;
    }

    // Getter ve Setter Metotları (Kapsülleme)
    public String getRezervasyonId() { return rezervasyonId; }
    public void setRezervasyonId(String rezervasyonId) { this.rezervasyonId = rezervasyonId; }

    public String getMusteriTc() { return musteriTc; }
    public void setMusteriTc(String musteriTc) { this.musteriTc = musteriTc; }

    public String getOdaNo() { return odaNo; }
    public void setOdaNo(String odaNo) { this.odaNo = odaNo; }

    public LocalDate getGirisTarihi() { return girisTarihi; }
    public void setGirisTarihi(LocalDate girisTarihi) { this.girisTarihi = girisTarihi; }

    public LocalDate getCikisTarihi() { return cikisTarihi; }
    public void setCikisTarihi(LocalDate cikisTarihi) { this.cikisTarihi = cikisTarihi; }

    public boolean isAktifMi() { return aktifMi; }
    public void setAktifMi(boolean aktifMi) { this.aktifMi = aktifMi; }
}