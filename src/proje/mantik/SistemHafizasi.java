package proje.mantik;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import proje.GenelSiniflar.Musteri;
import proje.GenelSiniflar.Rezervasyon;

public class SistemHafizasi {
    public static Musteri aktifMusteri;
    public static List<Musteri> kullanicilar = new ArrayList<>();
    public static List<Rezervasyon> globalRezervasyonlar = new ArrayList<>();

    public static void init() {
        verileriYukle();
    }

    // 3. MADDE: Gerçek Giriş Yapma Kontrolü
    public static boolean girisYap(String kullaniciAdi, String sifre) {
        for (Musteri m : kullanicilar) {
            if (m.getKullaniciAdi().equals(kullaniciAdi) && m.getSifre().equals(sifre)) {
                aktifMusteri = m; // Giriş yapıldığında o kişiyi "aktif" yap
                return true;
            }
        }
        return false;
    }

    public static boolean kullaniciAdiAlinmisMi(String kullaniciAdi) {
        for (Musteri m : kullanicilar) {
            if (m.getKullaniciAdi().equals(kullaniciAdi)) return true;
        }
        return false;
    }

    public static void verileriKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("kullanicilar_veri.txt"))) {
            for (Musteri m : kullanicilar) {
                writer.write(m.getKullaniciAdi() + ";" + m.getSifre() + ";" + m.getAdSoyad() + ";" +
                             m.getKartAdi() + ";" + m.getKartNo() + ";" + m.getSonKullanma() + "\n");
            }
        } catch (Exception e) {}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rezervasyonlar_veri.txt"))) {
            for (Rezervasyon r : globalRezervasyonlar) {
                writer.write(r.getRezervasyonId() + ";" + r.getKullaniciAdi() + ";" + r.getOdaNo() + ";" +
                             r.getGirisTarihi() + ";" + r.getCikisTarihi() + ";" + r.isAktifMi() + "\n");
            }
        } catch (Exception e) {}
    }

    public static void verileriYukle() {
        kullanicilar.clear();
        globalRezervasyonlar.clear();

        File kDosya = new File("kullanicilar_veri.txt");
        if (kDosya.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(kDosya))) {
                String satir;
                while ((satir = br.readLine()) != null) {
                    String[] v = satir.split(";", -1);
                    if (v.length >= 3) {
                        Musteri m = new Musteri(v[0], v[1], v[2]);
                        if (v.length >= 6) {
                            m.setKartAdi(v[3]);
                            m.setKartNo(v[4]);
                            m.setSonKullanma(v[5]);
                        }
                        kullanicilar.add(m);
                    }
                }
            } catch (Exception e) {}
        } else {
            // Hiç dosya yoksa test edebilmen için varsayılan bir müşteri ekleyelim
            Musteri testMusteri = new Musteri("berkay", "123", "Mehmet Berkay Yalçın");
            kullanicilar.add(testMusteri);
        }

        File rDosya = new File("rezervasyonlar_veri.txt");
        if (rDosya.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(rDosya))) {
                String satir;
                while ((satir = br.readLine()) != null) {
                    String[] v = satir.split(";");
                    if (v.length == 6) {
                        Rezervasyon r = new Rezervasyon(v[0], v[1], v[2], LocalDate.parse(v[3]), LocalDate.parse(v[4]));
                        r.setAktifMi(Boolean.parseBoolean(v[5]));
                        globalRezervasyonlar.add(r);
                    }
                }
            } catch (Exception e) {}
        }
    }
    
    // Aktif kullanıcının kendi rezervasyonlarını filtreleyip çeker
    public static List<Rezervasyon> getAktifMusteriRezervasyonlari() {
        List<Rezervasyon> liste = new ArrayList<>();
        if (aktifMusteri == null) return liste;
        for(Rezervasyon r : globalRezervasyonlar) {
            if(r.getKullaniciAdi().equals(aktifMusteri.getKullaniciAdi())) {
                liste.add(r);
            }
        }
        return liste;
    }
}