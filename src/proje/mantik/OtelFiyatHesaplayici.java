package proje.mantik;

/**
 * Otel reservation sisteminde fiyat hesaplama işlemlerini yönetir.
 * Gecelik fiyata göre toplam tutarı hesaplar, indirimler uygular.
 */
public class OtelFiyatHesaplayici {
    
    private double gecelikFiyat; // Varsayılan gecelik oda fiyatı
    private int gunSayisi;       // Kalınacak gün sayısı
    private double indirimOrani; // İndirim yüzdesi (0-100)

    // Default Constructor
    public OtelFiyatHesaplayici() {
        this.gecelikFiyat = 1500.0;  // Varsayılan 1500 TL
        this.gunSayisi = 1;
        this.indirimOrani = 0;
    }

    // Constructor with parameters
    public OtelFiyatHesaplayici(double gecelikFiyat, int gunSayisi, double indirimOrani) {
        setGecelikFiyat(gecelikFiyat);
        setGunSayisi(gunSayisi);
        setIndirimOrani(indirimOrani);
    }

    // ========== GETTER VE SETTER ==========
    public double getGecelikFiyat() {
        return gecelikFiyat;
    }

    public void setGecelikFiyat(double gecelikFiyat) {
        if (gecelikFiyat > 0) {
            this.gecelikFiyat = gecelikFiyat;
        } else {
            System.err.println("Hata: Fiyat sıfırdan büyük olmalı!");
            this.gecelikFiyat = 1500.0;
        }
    }

    public int getGunSayisi() {
        return gunSayisi;
    }

    public void setGunSayisi(int gunSayisi) {
        if (gunSayisi > 0) {
            this.gunSayisi = gunSayisi;
        } else {
            System.err.println("Hata: Gün sayısı sıfırdan büyük olmalı!");
            this.gunSayisi = 1;
        }
    }

    public double getIndirimOrani() {
        return indirimOrani;
    }

    public void setIndirimOrani(double indirimOrani) {
        if (indirimOrani >= 0 && indirimOrani <= 100) {
            this.indirimOrani = indirimOrani;
        } else {
            System.err.println("Hata: İndirim oranı 0-100 arasında olmalı!");
            this.indirimOrani = 0;
        }
    }

    // ========== FİYAT HESAPLAMA METOTLARI ==========

    /**
     * İndirimsiz toplam tutarı hesapla
     */
    public double indirimsizToplamHesapla() {
        return gunSayisi * gecelikFiyat;
    }

    /**
     * İndirim tutarını hesapla
     */
    public double indirimTutariHesapla() {
        double indirimsizToplam = indirimsizToplamHesapla();
        return (indirimsizToplam * indirimOrani) / 100.0;
    }

    /**
     * Net ödenecek tutarı hesapla (indirim uygulanmış)
     */
    public double netOdenecekTutarHesapla() {
        return indirimsizToplamHesapla() - indirimTutariHesapla();
    }

    /**
     * Fatura bilgilerini String olarak döndür
     */
    public String faturaBilgisiGetir() {
        double indirimsizToplam = indirimsizToplamHesapla();
        double indirimTutari = indirimTutariHesapla();
        double netTutar = netOdenecekTutarHesapla();

        StringBuilder fatura = new StringBuilder();
        fatura.append("==================================\n");
        fatura.append("         FATURA ÖZETİ            \n");
        fatura.append("==================================\n");
        fatura.append(String.format("Gecelik Fiyat       : %.2f TL\n", gecelikFiyat));
        fatura.append(String.format("Kalınacak Gün Sayısı: %d Gün\n", gunSayisi));
        fatura.append("----------------------------------\n");
        fatura.append(String.format("Ara Toplam          : %.2f TL\n", indirimsizToplam));

        if (indirimOrani > 0) {
            fatura.append(String.format("Uygulanan İndirim   : %%%,.0f (-%.2f TL)\n", indirimOrani, indirimTutari));
        }

        fatura.append("----------------------------------\n");
        fatura.append(String.format("NET ÖDENECEK TUTAR  : %.2f TL\n", netTutar));
        fatura.append("==================================");

        return fatura.toString();
    }

    /**
     * Test için main metodu
     */
    public static void main(String[] args) {
        OtelFiyatHesaplayici hesaplayici = new OtelFiyatHesaplayici(1500, 3, 10);
        System.out.println(hesaplayici.faturaBilgisiGetir());
    }
}
