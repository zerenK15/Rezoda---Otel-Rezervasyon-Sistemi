package proje.MenuEkranlari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proje.mantik.SistemHafizasi;

@SuppressWarnings("serial")
public class GirisEkraniGUI extends JFrame {

    // Ekranda kullanılacak bileşenleri (text kutularını) burada tanımlıyoruz ki her yerden erişebilelim
    private JTextField txtKullaniciAdi;
    private JPasswordField txtSifre;

    public GirisEkraniGUI() {
        // Pencerenin üstünde yazacak başlık
        setTitle("Rezoda Otel Rezervasyon Sistemi - Giriş");
        
        // Pencerenin ekrandaki yeri ve büyüklüğü (x, y, genişlik, yükseklik)
        setBounds(100, 100, 450, 350);
        
        // Çarpıya basınca program arka planda çalışmaya devam etmesin, tamamen kapansın
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hazır layout(düzen) kullanmıyoruz, her şeyi elimizle piksel piksel yerleştireceğiz (null layout)
        getContentPane().setLayout(null); 

        // Arka plan rengini hafif gri/beyaz bir tona ayarladık
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel lblBaslik = new JLabel("REZODA'YA HOŞ GELDİNİZ", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBaslik.setBounds(10, 20, 410, 30);  // Ekranda duracağı yer
        getContentPane().add(lblBaslik); // Yazıyı ekrana ekledik
        
        // Kullanıcı adı etiketi
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblKullaniciAdi.setBounds(60, 80, 100, 25);
        getContentPane().add(lblKullaniciAdi);

        // Kullanıcının adını yazacağı kutucuk
        txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(180, 80, 180, 25);
        getContentPane().add(txtKullaniciAdi);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSifre.setBounds(60, 130, 100, 25);
        getContentPane().add(lblSifre);

        // Şifre kutusu (yazarken ekranda yıldız/nokta çıkması için JTextField yerine JPasswordField kullandık)
        txtSifre = new JPasswordField();
        txtSifre.setBounds(180, 130, 180, 25);
        getContentPane().add(txtSifre);

        // Giriş yap butonu
        JButton btnGirisYap = new JButton("Giriş Yap");
        btnGirisYap.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGirisYap.setBackground(new Color(41, 128, 185));
        btnGirisYap.setForeground(Color.WHITE);
        btnGirisYap.setBounds(180, 180, 180, 35);
        getContentPane().add(btnGirisYap);

        // Hesabı olmayanlar için kayıt butonu
        JButton btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnKayitOl.setBounds(180, 230, 180, 30);
        getContentPane().add(btnKayitOl);

        // Giriş yap butonuna tıklanınca ne olacağını buraya yazdık
        btnGirisYap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Kutulara girilen yazıları çekip string değişkenlere atadık
                String kullanici = txtKullaniciAdi.getText();
                String sifre = new String(txtSifre.getPassword());

                // Kullanıcı hiçbir şey yazmadan butona basarsa ekrana uyarı veriyoruz
                if(kullanici.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Sisteme Kayıtlı Olan Kullanıcı Kontrol Edilir
                // Girilen bilgiler doğru mu diye bizim SistemHafizasi'na soruyoruz
                if (SistemHafizasi.girisYap(kullanici, sifre)) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı! Ana Menüye yönlendiriliyorsunuz.");
                    
                    // Giriş başarılıysa ana menü penceresinden yeni bir nesne oluştur ve ekranda göster
                    AnaMenuGUI anaMenu = new AnaMenuGUI(); 
                    anaMenu.setVisible(true);
                    
                    dispose();    //Yeni ekrana yönlendirme yaptığım için mevcut giriş ekranını kapattık, böylece programımız arka planda boşuna ram tüketmeyecek.
                } else {

                    // Şifre veya isim yanlışsa hata mesajı fırlatacak
                    JOptionPane.showMessageDialog(null, "Hatalı Kullanıcı Adı veya Şifre! Kaydınız yoksa Kayıt Ol butonunu kullanın.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Kayıt ol butonuna tıklanınca çalışacak kodlar
        btnKayitOl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Kayıt ekranı penceresini açıyoruz (burada dispose() yapmadık çünkü giriş ekranı arkada kalsın istiyoruz)
                KayitEkraniGUI kayitEkrani = new KayitEkraniGUI();
                kayitEkrani.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        
        // Uygulama başlarken hafızadaki kullanıcı ve rezervasyonları yükler(Arayüz çökmesin diye Swing standart kodudur)
        SistemHafizasi.init(); 

        // Pencereyi ekranda gösterme işlemleri
        EventQueue.invokeLater(() -> {
            try {
                GirisEkraniGUI frame = new GirisEkraniGUI();
                frame.setVisible(true);   // Pencereyi görünür yapması için
            } catch (Exception e) {
                e.printStackTrace();    // Hata çıkarsa konsolda görelim diye
            }
        });
    }
}
