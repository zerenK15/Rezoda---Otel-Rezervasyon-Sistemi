package proje.mantik;  //kontrol işlemleri yaptığık için bunu mantık paketine koyduk

import proje.GenelSiniflar.Musteri;   //Müşteri sınıfı farklı pakette olduğu için importla bu arayüz içine dahil ettik
import java.util.List;   //Java'nın standart list yapısını bu dosyaya çağırdık

public interface IKimlikDogrulama {
	boolean dogrula(String girilenAd, String girilenSifre, List<Musteri> kayitliMusteriler);  //Soyut metot
	//Bununla bu arayüzü uygulayacak her sınıfın mecburen bir "doğrula" metodu barındırmasını zorunlu kıldık
	//Ya başarılı ya başarısız olacağı için boolean kullandık
}
