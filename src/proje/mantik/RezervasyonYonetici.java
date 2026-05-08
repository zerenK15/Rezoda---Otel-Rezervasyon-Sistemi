package proje.mantik;

import java.time.LocalDate;
import java.util.List;

import proje.GenelSiniflar.Rezervasyon;

public class RezervasyonYonetici {
    
    /**
     * Aynı odaya birden fazla rezervasyonu engelleyen Çakışma Önleyici algoritma.
     */
    public boolean odaMusaitMi(String odaNo, LocalDate giris, LocalDate cikis, List<Rezervasyon> tumRezervasyonlar) {
        for (Rezervasyon r : tumRezervasyonlar) {
            if (r.getOdaNo().equals(odaNo) && r.isAktifMi()) {
                // Tarih aralıklarının kesişip kesişmediğini kontrol eder
                if (giris.isBefore(r.getCikisTarihi()) && cikis.isAfter(r.getGirisTarihi())) {
                    return false; // Çakışma var, oda müsait değil
                }
            }
        }
        return true; // Çakışma yok, oda müsait
    }
}