package proje.OdaSiniflari;

public class StandartOda extends Oda {   //'extends' ile StandartOda class'ının Oda class'ının alt sınıfı olduğunu belirttik
	
	public StandartOda(double fiyat, int yatakSayisi) {
		super(fiyat, yatakSayisi);  //"super" ile "Oda" sınıfın constructor'ına değer gönderdik
		                            //Kalıtım yoluyla üst sınıfın (Oda) özelliklerini kullanır
	}
	
	@Override   //Burada üst sınıftaki abstract(soyut) metodu bu sınıfa göre override ettik.(Polimorfizm)
	public void odaOzellikleriniYazdir() {
		System.out.println("---- Standart Oda ----");
		System.out.println("Yatak Sayısı: " + getYatakSayisi());  //get ile yatak sayısına ulaşacağız
		System.out.println("Gecelik Fiyat: " + getFiyat() + " TL");  //get ile fiyat sayısına ulaşacağız
	}

}
