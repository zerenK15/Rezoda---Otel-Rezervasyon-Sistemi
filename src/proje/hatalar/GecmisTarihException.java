package proje.hatalar;

// müşteri rezervasyon için bugünden daha eski bir tarih 
// seçmeye çalıştığında fırlatılan özel hata sınıfımız.

public class GecmisTarihException extends Exception {
    public GecmisTarihException(String mesaj) {
        super(mesaj);
    }
}
