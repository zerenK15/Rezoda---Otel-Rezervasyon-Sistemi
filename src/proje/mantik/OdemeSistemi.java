package proje.mantik;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Otel reservation sisteminde ödeme işlemlerini yönetir.
 * Kart doğrulama, ödeme işleme gibi işlevleri içerir.
 */
public class OdemeSistemi {
    
    private String kartSahibi;
    private String kartNumarasi;
    private String sonKullanmaTarihi;
    private String cvvKodu;
    private double odenecekTutar;
    private boolean odemeBasarili;
    private LocalDateTime odemeTarihi;

    // Constructor
    public OdemeSistemi() {
        this.odemeBasarili = false;
        this.odenecekTutar = 0;
    }

    // ========== GETTER VE SETTER ==========
    public String getKartSahibi() { return kartSahibi; }
    public void setKartSahibi(String kartSahibi) { this.kartSahibi = kartSahibi; }

    public String getKartNumarasi() { return kartNumarasi; }
    public void setKartNumarasi(String kartNumarasi) { this.kartNumarasi = kartNumarasi; }

    public String getSonKullanmaTarihi() { return sonKullanmaTarihi; }
    public void setSonKullanmaTarihi(String sonKullanmaTarihi) { this.sonKullanmaTarihi = sonKullanmaTarihi; }

    public String getCvvKodu() { return cvvKodu; }
    public void setCvvKodu(String cvvKodu) { this.cvvKodu = cvvKodu; }

    public double getOdenecekTutar() { return odenecekTutar; }
    public void setOdenecekTutar(double odenecekTutar) { this.odenecekTutar = odenecekTutar; }

    public boolean isOdemeBasarili() { return odemeBasarili; }
    public LocalDateTime getOdemeTarihi() { return odemeTarihi; }

    // ========== KART DOĞRULAMA ==========

    /**
     * Kart numarasının geçerli olup olmadığını kontrol et (16 haneli)
     */
    public boolean kartNumarasiGecerliMi() {
        return kartNumarasi != null && kartNumarasi.replaceAll(" ", "").length() == 16 && 
               kartNumarasi.replaceAll(" ", "").matches("\\d+");
    }

    /**
     * CVV kodunun geçerli olup olmadığını kontrol et (3 haneli)
     */
    public boolean cvvGecerliMi() {
        return cvvKodu != null && cvvKodu.length() == 3 && cvvKodu.matches("\\d+");
    }

    /**
     * Son kullanma tarihinin geçerli olup olmadığını kontrol et (AA/YY format)
     */
    public boolean tarihGecerliMi() {
        if (sonKullanmaTarihi == null || !sonKullanmaTarihi.matches("\\d{2}/\\d{2}")) {
            return false;
        }
        
        String[] tarihParcalari = sonKullanmaTarihi.split("/");
        int ay = Integer.parseInt(tarihParcalari[0]);
        int yil = Integer.parseInt(tarihParcalari[1]);
        
        if (ay < 1 || ay > 12) {
            return false;
        }
        
        return true;
    }

    /**
     * Kart sahibi adının geçerli olup olmadığını kontrol et
     */
    public boolean kartSahibiGecerliMi() {
        return kartSahibi != null && !kartSahibi.trim().isEmpty() && 
               kartSahibi.length() >= 3;
    }

    /**
     * Tüm ödeme bilgilerinin geçerli olup olmadığını kontrol et
     */
    public boolean tumBilgilerGecerliMi() {
        return kartSahibiGecerliMi() && kartNumarasiGecerliMi() && 
               tarihGecerliMi() && cvvGecerliMi();
    }

    // ========== ÖDEME İŞLEMLERİ ==========

    /**
     * Ödeme işlemini gerçekleştir
     * @return true if payment successful, false otherwise
     */
    public boolean odemeIsleminiGerceklestir() {
        // Tüm bilgiler doğru mu kontrol et
        if (!tumBilgilerGecerliMi()) {
            return false;
        }

        // Gerçek sistemde burada banka API'si çağrılacak
        // Şimdilik simülasyon yapıyoruz
        try {
            System.out.println("Ödeme işlemi bankanız ile iletişim kurularak işleniyor...");
            Thread.sleep(2000); // 2 saniye bekle (simülasyon)
            
            odemeBasarili = true;
            odemeTarihi = LocalDateTime.now();
            
            System.out.println("Ödeme başarıyla gerçekleştirilmiştir!");
            return true;
            
        } catch (InterruptedException e) {
            System.err.println("Ödeme işlemi sırasında hata oluştu: " + e.getMessage());
            odemeBasarili = false;
            return false;
        }
    }

    // ========== RAPORLAMA ==========

    /**
     * Ödeme onay mesajını döndür
     */
    public String odemeOnayMesajiGetir() {
        if (!odemeBasarili) {
            return "Ödeme başarısız!";
        }

        StringBuilder mesaj = new StringBuilder();
        mesaj.append("=======================================\n");
        mesaj.append("        ÖDEME ONAY BELGESİ             \n");
        mesaj.append("=======================================\n");
        mesaj.append("Sayın " + kartSahibi + ",\n");
        mesaj.append(String.format("Ödeme Tutarı    : %.2f TL\n", odenecekTutar));
        mesaj.append("Kart Son 4 Hane : " + kartNumarasi.substring(kartNumarasi.length() - 4) + "\n");
        mesaj.append("Ödeme Tarihi     : " + odemeTarihi.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n");
        mesaj.append("=======================================\n");
        mesaj.append("Ödemeniz başarıyla alınmıştır.\n");
        mesaj.append("Bizi tercih ettiğiniz için teşekkür ederiz!\n");
        mesaj.append("İyi tatiller dileriz.\n");
        mesaj.append("=======================================");

        return mesaj.toString();
    }

    /**
     * Hata mesajını döndür
     */
    public String hataMesajiGetir() {
        StringBuilder mesaj = new StringBuilder();
        mesaj.append("Ödeme işlemi sırasında hatalar tespit edildi:\n\n");

        if (!kartSahibiGecerliMi()) {
            mesaj.append("❌ Kart sahibi adı geçersiz\n");
        }
        if (!kartNumarasiGecerliMi()) {
            mesaj.append("❌ Kart numarası geçersiz (16 haneli olmalı)\n");
        }
        if (!tarihGecerliMi()) {
            mesaj.append("❌ Son kullanma tarihi geçersiz (AA/YY formatı)\n");
        }
        if (!cvvGecerliMi()) {
            mesaj.append("❌ CVV kodu geçersiz (3 haneli olmalı)\n");
        }

        return mesaj.toString();
    }

    /**
     * Test için main metodu
     */
    public static void main(String[] args) {
        OdemeSistemi odeme = new OdemeSistemi();
        odeme.setKartSahibi("Ahmet Yılmaz");
        odeme.setKartNumarasi("1234 5678 9012 3456");
        odeme.setSonKullanmaTarihi("12/25");
        odeme.setCvvKodu("123");
        odeme.setOdenecekTutar(4050.00);

        if (odeme.odemeIsleminiGerceklestir()) {
            System.out.println(odeme.odemeOnayMesajiGetir());
        } else {
            System.out.println(odeme.hataMesajiGetir());
        }
    }
}
