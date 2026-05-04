package proje.modeller;

public class SuitOda extends Oda {
	private boolean jakuziVarMi;
	
	public SuitOda(double fiyat, int yatakSayisi, boolean jakuziVarMi) {
		super(fiyat, yatakSayisi);   //Kalıtım ile üst sınıfın(Oda) özelliklerini kullandık
		this.jakuziVarMi = jakuziVarMi;
	}
	
	@Override
	public void odaOzellikleriniYazdir() {
		System.out.println("---- Suit Oda ----");
		System.out.println("Yatak Sayısı: " + getYatakSayisi());
		System.out.println("Gecelik Ücret: " + getFiyat() + " TL");
		System.out.println("Jakuzi Durumu: " + (jakuziVarMi ? "Var" : "Yok"));  //Burada jakuziVarMi doğru ise "Var", yanlış ise "Yok" yazdır diyor.
		System.out.println("Özellik: Lüks Konaklama");
	}

}