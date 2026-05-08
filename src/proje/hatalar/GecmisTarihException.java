package proje.hatalar;

// Müşteri rezervasyon için bugünden daha eski bir tarih 
// seçmeye çalıştığında fırlatılan özel hata sınıfımız.
@SuppressWarnings("serial")
public class GecmisTarihException extends Exception {
    public GecmisTarihException(String mesaj) {
        super(mesaj);
    }
}