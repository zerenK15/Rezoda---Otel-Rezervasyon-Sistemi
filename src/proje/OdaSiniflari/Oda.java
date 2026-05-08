package proje.OdaSiniflari;

public abstract class Oda {   //Müşteri sadece oda diyip rezervasyon yapamaz, odanın tipini belirtmelidir. O yüzden abstract class yaptık
	
	private double fiyat; 
	private int yatakSayisi;  //private ile kapsülleme yaptık. Böylece dışarıdan doğrudan erişilemez
	
	public Oda(double fiyat, int yatakSayisi) {  //Constructor oluşturduk (Nesne üretilirken ilk değerleri atamak için)
		this.fiyat = fiyat;
		this.yatakSayisi = yatakSayisi;
	}
	
	public Oda(int yatakSayisi) {    //Fiyatı henüz belli olmayan odalar için overloading yaptık
		this.yatakSayisi = yatakSayisi;
		this.fiyat = 0.0;  //Varsayılan değer olarak belirledik
	}
	
	
	//Getter ve Setter metotları (Kapsülleme için)
	public double getFiyat() {   //Fiyatı görebilmek için
		return fiyat;
	}
	
	public void setFiyat(double fiyat) {   //Fiyatı değiştirebilmek için
		if (fiyat > 0) {
			this.fiyat = fiyat;
		}
		
		else {
			System.out.println("Hata! Fiyat sıfırdan büyük olmalı.");   //Bu if else bloğunda fiyat sıfırdan büyük olmalı şartı koyduk
		}
	}
	
	public int getYatakSayisi() {  //Yatak sayısını görebilmek için
		return yatakSayisi;
	}
	
	public void setYatakSayisi(int yatakSayisi) {   //Yatak sayısını değiştirebilmek için set metodu yazdık
		this.yatakSayisi = yatakSayisi;
	}
	
	public abstract void odaOzellikleriniYazdir();  //Alt sınıfların içlerini doldurmak zorunda olduğu soyut bir metot yazdık
	
	

}
