package proje.hatalar;

//Burası müşteri rezervasyon için dünün veya geçen haftanın tarihini seçerse fırlatılacak hata sınıfıdır

public class GecmisTarihException extends Exception{
	
	public GecmisTarihException(String mesaj) {
		super(mesaj);  //Dışarıdan gelen hata yazısını (mesaj) yakalayıp super() ile de miras aldığımız sınıfa kaydedilir
	}

}

