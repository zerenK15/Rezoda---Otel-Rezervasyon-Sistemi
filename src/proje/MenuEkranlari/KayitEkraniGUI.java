package proje.MenuEkranlari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proje.GenelSiniflar.Musteri;
import proje.mantik.SistemHafizasi;

@SuppressWarnings("serial")
public class KayitEkraniGUI extends JFrame {

    private JTextField txtAdSoyad;
    private JTextField txtKullaniciAdi;
    private JPasswordField txtSifre;
    private JTextField txtKartAdi;
    private JTextField txtKartNo;
    private JTextField txtSonKullanma;

    public KayitEkraniGUI() {
        setTitle("Rezoda Otel Rezervasyon Sistemi - Kayıt Ol");
        setBounds(150, 150, 450, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(236, 240, 241));

        JLabel lblZorunlu = new JLabel("Kullanıcı Bilgileri (Zorunlu)");
        lblZorunlu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblZorunlu.setForeground(new Color(41, 128, 185));
        lblZorunlu.setBounds(40, 10, 300, 25);
        getContentPane().add(lblZorunlu);

        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
        lblAdSoyad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblAdSoyad.setBounds(40, 40, 120, 25);
        getContentPane().add(lblAdSoyad);

        txtAdSoyad = new JTextField();
        txtAdSoyad.setBounds(180, 40, 180, 25);
        getContentPane().add(txtAdSoyad);

        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblKullaniciAdi.setBounds(40, 80, 120, 25);
        getContentPane().add(lblKullaniciAdi);

        txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(180, 80, 180, 25);
        getContentPane().add(txtKullaniciAdi);

        JLabel lblSifre = new JLabel("Şifre Belirleyin:");
        lblSifre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSifre.setBounds(40, 120, 120, 25);
        getContentPane().add(lblSifre);

        txtSifre = new JPasswordField();
        txtSifre.setBounds(180, 120, 180, 25);
        getContentPane().add(txtSifre);

        // 5. MADDE: İsteğe Bağlı Kredi Kartı Bilgileri
        JLabel lblKart = new JLabel("Kredi Kartı Bilgileri (İsteğe Bağlı)");
        lblKart.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblKart.setForeground(new Color(41, 128, 185));
        lblKart.setBounds(40, 170, 300, 25);
        getContentPane().add(lblKart);

        JLabel lblKartAdi = new JLabel("Kart Üzerindeki İsim:");
        lblKartAdi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblKartAdi.setBounds(40, 200, 130, 25);
        getContentPane().add(lblKartAdi);

        txtKartAdi = new JTextField();
        txtKartAdi.setBounds(180, 200, 180, 25);
        getContentPane().add(txtKartAdi);

        JLabel lblKartNo = new JLabel("Kart Numarası:");
        lblKartNo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblKartNo.setBounds(40, 240, 130, 25);
        getContentPane().add(lblKartNo);

        txtKartNo = new JTextField();
        txtKartNo.setBounds(180, 240, 180, 25);
        getContentPane().add(txtKartNo);

        JLabel lblSonKullanma = new JLabel("Son Kullanma (AA/YY):");
        lblSonKullanma.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSonKullanma.setBounds(40, 280, 130, 25);
        getContentPane().add(lblSonKullanma);

        txtSonKullanma = new JTextField();
        txtSonKullanma.setBounds(180, 280, 180, 25);
        getContentPane().add(txtSonKullanma);

        JButton btnKaydiTamamla = new JButton("Kaydı Tamamla");
        btnKaydiTamamla.setBackground(new Color(39, 174, 96));
        btnKaydiTamamla.setForeground(Color.WHITE);
        btnKaydiTamamla.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnKaydiTamamla.setBounds(180, 350, 180, 35);
        getContentPane().add(btnKaydiTamamla);

        btnKaydiTamamla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adSoyad = txtAdSoyad.getText().trim();
                String kullaniciAdi = txtKullaniciAdi.getText().trim();
                String sifre = new String(txtSifre.getPassword());

                if(adSoyad.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ad Soyad, Kullanıcı Adı ve Şifre alanları boş bırakılamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (SistemHafizasi.kullaniciAdiAlinmisMi(kullaniciAdi)) {
                    JOptionPane.showMessageDialog(null, "Bu Kullanıcı Adı zaten alınmış! Lütfen başka bir ad deneyin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Kullanıcı oluşturuluyor ve kart bilgileri (varsa) ekleniyor
                Musteri yeniMusteri = new Musteri(kullaniciAdi, sifre, adSoyad);
                yeniMusteri.setKartAdi(txtKartAdi.getText().trim());
                yeniMusteri.setKartNo(txtKartNo.getText().trim());
                yeniMusteri.setSonKullanma(txtSonKullanma.getText().trim());

                SistemHafizasi.kullanicilar.add(yeniMusteri);
                SistemHafizasi.verileriKaydet();

                JOptionPane.showMessageDialog(null, "Kayıt Başarıyla Oluşturuldu! Giriş yapabilirsiniz.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                dispose(); 
            }
        });
    }
}