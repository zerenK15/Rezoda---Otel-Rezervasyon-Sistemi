package proje.mantik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import proje.GenelSiniflar.Musteri;
import proje.GenelSiniflar.Rezervasyon;

public class SistemHafizasi {
    
    public static Musteri aktifMusteri;
    public static List<Rezervasyon> globalRezervasyonlar = new ArrayList<>();

    public static String kayitliKartAdi = "";
    public static String kayitliKartNo = "";
    public static String kayitliSonKullanma = "";
    public static boolean kartKayitliMi = false;

    public static void init() {
        if (aktifMusteri == null) {
            aktifMusteri = new Musteri("mberkay", "12345", "Mehmet Berkay Yalçın", "12345678901", "0555 444 33 22", 0, null, null);
        }
        verileriYukle();
    }

    // UYGULAMA KAPANIRKEN VEYA GÜNCELLEME YAPILDIĞINDA ÇAĞRILACAK
    public static void verileriKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profil_veri.txt"))) {
            writer.write(aktifMusteri.getAdSoyad() + "\n");
            writer.write(aktifMusteri.getTcKimlik() + "\n");
            writer.write(aktifMusteri.getTelefon() + "\n");
            writer.write(kartKayitliMi + "\n");
            writer.write(kayitliKartAdi + "\n");
            writer.write(kayitliKartNo + "\n");
            writer.write(kayitliSonKullanma + "\n");
        } catch (Exception e) {
            System.out.println("Profil kaydedilemedi: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rezervasyonlar_veri.txt"))) {
            for (Rezervasyon r : globalRezervasyonlar) {
                writer.write(r.getRezervasyonId() + ";" + r.getMusteriTc() + ";" + 
                             r.getOdaNo() + ";" + r.getGirisTarihi() + ";" + 
                             r.getCikisTarihi() + ";" + r.isAktifMi() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Rezervasyonlar kaydedilemedi: " + e.getMessage());
        }
    }

    // UYGULAMA İLK AÇILDIĞINDA ÇAĞRILACAK (init içinde)
    public static void verileriYukle() {
        File profilDosya = new File("profil_veri.txt");
        if (profilDosya.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(profilDosya))) {
                aktifMusteri.setAdSoyad(reader.readLine());
                aktifMusteri.setTcKimlik(reader.readLine());
                aktifMusteri.setTelefon(reader.readLine());
                kartKayitliMi = Boolean.parseBoolean(reader.readLine());
                kayitliKartAdi = reader.readLine();
                kayitliKartNo = reader.readLine();
                kayitliSonKullanma = reader.readLine();
            } catch (Exception e) {
                System.out.println("Profil yüklenemedi: " + e.getMessage());
            }
        }

        File rezDosya = new File("rezervasyonlar_veri.txt");
        if (rezDosya.exists()) {
            globalRezervasyonlar.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(rezDosya))) {
                String satir;
                while ((satir = reader.readLine()) != null) {
                    String[] veri = satir.split(";");
                    if (veri.length == 6) {
                        Rezervasyon r = new Rezervasyon(veri[0], veri[1], veri[2], 
                                LocalDate.parse(veri[3]), LocalDate.parse(veri[4]));
                        r.setAktifMi(Boolean.parseBoolean(veri[5]));
                        globalRezervasyonlar.add(r);
                    }
                }
            } catch (Exception e) {
                System.out.println("Rezervasyonlar yüklenemedi: " + e.getMessage());
            }
        }
    }
}