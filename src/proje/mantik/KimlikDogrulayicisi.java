package proje.mantik;

import proje.modeller.Musteri;
import java.util.List;

public class KimlikDogrulayicisi implements IKimlikDogrulama {
	//Asıl sınıfımızı oluşturduk ve "implements" ile Interface'e bakladık
	//Bu yüzden IKimlikDogrulama arayüzündeki kurallara uygun çalışacak
	
	
	@Override   //Polimorfizm uyguladık. Arayüzden gelen soyut metodu ezip, içine kendi dogrulama algoritmamızı yazdık
	public boolean dogrula(String girilenAd, String girilenSifre, List<Musteri> kayitliMusteriler) {  //Dogrulama islemini yapan metod
	/*
	 BUNLARI SONRA SİLECEĞİM!!!
	 
	 List birden fazla müşteriyi alt alta depolamamızı sağlar. Normal diziden farkı baştan kaç kişi olduğunu belirtmenin zorunlu olmamasıdır
	
	"<Musteri>" sadece musteriler sınıfından üretilmiş nesnelerin konulabileceğini söyler.
	
	"kayitliMusteriler" ise bu List'in adıdır 
	*/
		
		for(int i = 0; i < kayitliMusteriler.size(); i++) { 
			
			Musteri musteri = kayitliMusteriler.get(i);  // Listeden i. sıradaki müşteri nesnesini çekip, alt satırlarda şifre/ad kontrolü yapabilmek için 'musteri' değişkenine atıyoruz.
			
			if(musteri.getKullaniciAdi().equals(girilenAd) && musteri.getSifre().equals(girilenSifre)) {    //Girilen ad ile şifrenin, sistemdeki ad ve şifreyle uyumlu olup olmadığını kontrol eder
				System.out.println("Giriş Başarılı! Hoş geldiniz, " + musteri.getAd());
				return true;
			}
	    }
		
		System.out.println("Hata: Kullanıcı adı veya şifre yanlış, tekrar deneyiniz!");
		return false;
	}
}
