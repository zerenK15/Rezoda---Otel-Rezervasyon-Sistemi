package proje.OdaSiniflari;

	public class KralDairesi extends Oda{
		private boolean ozelHizmetciVarMi;
		
		public KralDairesi(double fiyat, int yatakSayisi, boolean ozelHizmetciVarMi) {
			super(fiyat, yatakSayisi);   //Kalıtımla üst sınıfın (Oda) özelliğini kullandık
			this.ozelHizmetciVarMi = ozelHizmetciVarMi;
		}
		
		@Override
		public void odaOzellikleriniYazdir() {
			System.out.println("---- Kral Dairesi ----");
			System.out.println("Yatak Sayısı: " + getYatakSayisi());
			System.out.println("Gecelik Ücret: " + getFiyat() + " TL");
			System.out.println("Özel Hizmetçi Var Mı: " + (ozelHizmetciVarMi ? "Var" : "Yok"));   //Burada ozelHizmetciVarMi doğru ise "Var" yazdır, yanlış ise "Yok" yazdır diyor
		}

	}


