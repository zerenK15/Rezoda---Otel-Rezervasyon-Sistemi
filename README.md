import java.util.Scanner;

public class OtelFiyatHesaplayici {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Varsayılan gecelik oda fiyatı (Bunu isterseniz kullanıcıdan da alabilirsiniz)
        double gecelikFiyat = 1500.0;

        System.out.println("--- Otel Fiyat Hesaplama Sistemine Hoş Geldiniz ---");
        System.out.println("Güncel Gecelik Oda Fiyatı: " + gecelikFiyat + " TL\n");

        // Kullanıcıdan kalınacak gün sayısını alma
        System.out.print("Lütfen kalacağınız gün sayısını giriniz: ");
        int gunSayisi = scanner.nextInt();

        // Kullanıcıdan indirim oranını alma
        System.out.print("Herhangi bir indirim oranınız var mı? (Yüzde olarak, indirim yoksa 0 giriniz): ");
        double indirimOrani = scanner.nextDouble();

        // Hesaplamalar
        double indirimsizToplam = gunSayisi * gecelikFiyat;
        double indirimTutari = (indirimsizToplam * indirimOrani) / 100;
        double netOdenecekTutar = indirimsizToplam - indirimTutari;

        // Fatura Detayını Ekrana Yazdırma
        System.out.println("\n==================================");
        System.out.println("          FATURA ÖZETİ            ");
        System.out.println("==================================");
        System.out.println("Gecelik Fiyat       : " + gecelikFiyat + " TL");
        System.out.println("Kalınacak Gün Sayısı: " + gunSayisi + " Gün");
        System.out.println("----------------------------------");
        System.out.println("Ara Toplam          : " + indirimsizToplam + " TL");

        if (indirimOrani > 0) {
            System.out.println("Uygulanan İndirim   : %" + indirimOrani + " (-" + indirimTutari + " TL)");
        }

        System.out.println("----------------------------------");
        System.out.println("NET ÖDENECEK TUTAR  : " + netOdenecekTutar + " TL");
        System.out.println("==================================");

        scanner.close();
    }
}
