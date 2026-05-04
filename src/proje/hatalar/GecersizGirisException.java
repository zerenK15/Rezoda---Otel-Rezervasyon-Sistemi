package proje.hatalar;

//Burası kullanıcı sayı girmesi gereken yere (telefon no, yatak sayısı vb.) harf girerse fırlatılacak özel hata sınıfımızdır

public class GecersizGirisException extends Exception {    //Exception sınıfı Java'da hazır olarak bulunur. Biz onu miras alarak (inheritance) kendi projemize özgü bir hata türü tanımlıyoruz
	
	public GecersizGirisException(String mesaj) {
		super(mesaj);
		/*   BU SATIRI SONRADAN SİLMELİYİM!!!
		 
		 Exception sınıfında şöyle bir satır vardır:
		  public class Exception {
		      private String message;
		  
		      public Exception(String s) {
		          this.message = s; 
		      }
		  }
		   yani biz super(mesaj) yazarken s yerine mesaj'ı koyduk
		   
		  */
	}

}
