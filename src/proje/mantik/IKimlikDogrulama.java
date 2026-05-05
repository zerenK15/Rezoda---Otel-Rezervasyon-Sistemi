package proje.mantik;  //kontrol işlemleri yaptığık için bunu mantık paketine koyduk

import proje.modeller.Musteri;   //Müşteri sınıfı farklı pakette olduğu için importla bu arayüz içine dahil ettik
import java.util.List;   //Java'nın standart list yapısını bu dosyaya çağırdık

public interface IKimlikDogrulama {
	
	/*SONRA SİLİNECEK BU YORUM SATIRI!!!
	  Sistemi geleceği dönük ve esnek tasarlamak için interface kullandık.
	  Soyutlama kuralını uygulayarak kimlik doğrulama işleminin sadece kurallarını burada belirledik 
	*/
	
	boolean dogrula(String girilenAd, String girilenSifre, List<Musteri> kayitliMusteriler);  //Soyut metot
	//Bununla bu arayüzü uygulayacak her sınıfın mecburen bir "doğrula" metodu barındırmasını zorunlu kıldık
	//Ya başarılı ya başarısız olacağı için boolean kullandık
}
