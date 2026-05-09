package proje.MenuEkranlari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proje.mantik.SistemHafizasi;

@SuppressWarnings("serial")
public class GirisEkraniGUI extends JFrame {
    
    private JTextField txtKullaniciAdi;
    private JPasswordField txtSifre;

    public GirisEkraniGUI() {
        setTitle("Rezoda Otel Rezervasyon Sistemi - Giriş");
        setBounds(100, 100, 450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null); 
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel lblBaslik = new JLabel("REZODA'YA HOŞ GELDİNİZ", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBaslik.setBounds(10, 20, 410, 30);
        getContentPane().add(lblBaslik);

        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblKullaniciAdi.setBounds(60, 80, 100, 25);
        getContentPane().add(lblKullaniciAdi);

        txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(180, 80, 180, 25);
        getContentPane().add(txtKullaniciAdi);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSifre.setBounds(60, 130, 100, 25);
        getContentPane().add(lblSifre);

        txtSifre = new JPasswordField();
        txtSifre.setBounds(180, 130, 180, 25);
        getContentPane().add(txtSifre);

        JButton btnGirisYap = new JButton("Giriş Yap");
        btnGirisYap.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGirisYap.setBackground(new Color(41, 128, 185));
        btnGirisYap.setForeground(Color.WHITE);
        btnGirisYap.setBounds(180, 180, 180, 35);
        getContentPane().add(btnGirisYap);

        // 2. MADDE: Butonun Adı Sadece "Kayıt Ol" Oldu
        JButton btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnKayitOl.setBounds(180, 230, 180, 30);
        getContentPane().add(btnKayitOl);

        btnGirisYap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kullanici = txtKullaniciAdi.getText();
                String sifre = new String(txtSifre.getPassword());

                if(kullanici.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // 3. MADDE: Sisteme Kayıtlı Olan Kullanıcı Kontrol Edilir
                if (SistemHafizasi.girisYap(kullanici, sifre)) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı! Ana Menüye yönlendiriliyorsunuz.");
                    AnaMenuGUI anaMenu = new AnaMenuGUI(); 
                    anaMenu.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Hatalı Kullanıcı Adı veya Şifre! Kaydınız yoksa Kayıt Ol butonunu kullanın.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnKayitOl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KayitEkraniGUI kayitEkrani = new KayitEkraniGUI();
                kayitEkrani.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SistemHafizasi.init(); // Uygulama başlarken hafızadaki kullanıcı ve rezervasyonları yükler
        EventQueue.invokeLater(() -> {
            try {
                GirisEkraniGUI frame = new GirisEkraniGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}