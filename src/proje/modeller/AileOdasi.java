package proje.modeller;

public class AileOdasi extends Oda {
		private boolean cocukYatagiVarMi;
		
		public AileOdasi(double fiyat, int yatakSayisi, boolean cocukYatagiVarMi) {
			super(fiyat, yatakSayisi);  //Kalıtım yoluyla üst sınıfın özelliklerini kullandık
			this.cocukYatagiVarMi = cocukYatagiVarMi;
		}
		
		@Override   //Üst sınıftaki (Oda) abstract metodu bu sınıfa göre eziyoruz (Polimorfizm)
		public void odaOzellikleriniYazdir() {
			System.out.println("---- Aile Odası ----");
			System.out.println("Gecelik Ücret: " + getFiyat() + " TL");
			System.out.println("Yatak Sayisi: " + getYatakSayisi());
			System.out.println("Çocuk Yatağı: " + (cocukYatagiVarMi ? "Mevcut" : "Yok"));  //Burada demek istediğimiz cocukYatagiVarMi doğru ise "Mevcut" yaz, Eğer uanlışsa "Yok" yaz
		}
	

}
