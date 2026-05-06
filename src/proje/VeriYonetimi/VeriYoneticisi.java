package proje.VeriYonetimi;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VeriYoneticisi implements IVeriYonetimi {
    private static final String DOSYA_YOLU = "musteriler.txt";

    @Override
    public boolean veriKaydet(String musteriVerisi) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU, true))) {
            writer.write(musteriVerisi);
            writer.newLine();
            System.out.println("Müşteri başarıyla kaydedildi.");
            return true;
        } catch (IOException e) {
            System.err.println("Kritik Hata: Dosyaya yazılamadı! " + e.getMessage());
            return false;
        }
    }

    public void musteriKaydet(Musteri musteri) {
        String formatliVeri = musteri.getKullaniciAdi() + "," + musteri.getSifre() + "," + musteri.getAdSoyad();
        veriKaydet(formatliVeri);
    }
}