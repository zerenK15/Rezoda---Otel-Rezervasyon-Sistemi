package proje.hatalar;

//Kullanıcı sayı girmesi gereken yere harf girdiğinde veya 
//hataları yaptığında fırlatılan özel hata sınıfımız.

public class GecersizGirisException extends Exception {
    public GecersizGirisException(String mesaj) {
        super(mesaj);
    }
}