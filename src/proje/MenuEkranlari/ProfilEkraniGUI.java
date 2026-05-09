package proje.MenuEkranlari;

import javax.swing.*;
import java.awt.*;
import proje.GenelSiniflar.Musteri;
import proje.mantik.SistemHafizasi;
import proje.hatalar.GecersizGirisException;

@SuppressWarnings("serial")
public class ProfilEkraniGUI extends JFrame {
    private Musteri musteri;
    private JLabel lblKullanici; 

    public ProfilEkraniGUI(Musteri musteri) {
        this.musteri = musteri;
        setTitle("Rezoda - Profilim");
        setBounds(200, 200, 450, 350); // Ekrani ufaltıp daha şık hale getirdik
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // --- ÜST PANEL ---
        JPanel ustPanel = new JPanel(new BorderLayout(0, 10));
        ustPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        ustPanel.setBackground(Color.WHITE);
        
        JLabel baslik = new JLabel("👤 KULLANICI PROFİLİ", SwingConstants.CENTER);
        baslik.setFont(new Font("Segoe UI", Font.BOLD, 22));
        baslik.setForeground(new Color(44, 62, 80));
        ustPanel.add(baslik, BorderLayout.NORTH);

        // --- BİLGİ KISMI ---
        lblKullanici = new JLabel("", SwingConstants.CENTER);
        kullaniciBilgileriniGuncelle();
        ustPanel.add(lblKullanici, BorderLayout.CENTER);
        
        getContentPane().add(ustPanel, BorderLayout.CENTER);

        // --- ALT PANEL (Butonlar) ---
        JPanel altPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        altPanel.setBackground(Color.WHITE);

        JButton btnGeri = new JButton("← Geri");
        btnGeri.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGeri.setBackground(new Color(149, 165, 166));
        btnGeri.setForeground(Color.WHITE);
        btnGeri.setFocusPainted(false);
        btnGeri.setPreferredSize(new Dimension(130, 40));
        btnGeri.addActionListener(e -> dispose());

        JButton btnDuzenle = new JButton("✏️ Düzenle");
        btnDuzenle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDuzenle.setBackground(new Color(241, 196, 15));
        btnDuzenle.setForeground(new Color(44, 62, 80));
        btnDuzenle.setFocusPainted(false);
        btnDuzenle.setPreferredSize(new Dimension(130, 40));
        btnDuzenle.addActionListener(e -> profiliDuzenlePenceresiAc());

        altPanel.add(btnGeri);
        altPanel.add(btnDuzenle);
        
        getContentPane().add(altPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void profiliDuzenlePenceresiAc() {
        JPanel p = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField tAd = new JTextField(musteri.getAdSoyad());
        JTextField tKAdi = new JTextField(musteri.getKartAdi());
        JTextField tKNo = new JTextField(musteri.getKartNo());
        JTextField tSKul = new JTextField(musteri.getSonKullanma());

        p.add(new JLabel("Ad Soyad:")); p.add(tAd);
        p.add(new JLabel("Kart Üzerindeki İsim:")); p.add(tKAdi);
        p.add(new JLabel("Kart No:")); p.add(tKNo);
        p.add(new JLabel("Son Kullanma (AA/YY):")); p.add(tSKul);

        int result = JOptionPane.showConfirmDialog(this, p, "Profili Düzenle", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String ad = tAd.getText().trim();
                String kNo = tKNo.getText().trim();
                
                if (ad.isEmpty() || !ad.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$")) {
                    throw new GecersizGirisException("İsim sadece harflerden oluşmalıdır!");
                }
                if (!kNo.isEmpty() && (!kNo.matches("[0-9\\s]+") || kNo.length() < 16)) {
                    throw new GecersizGirisException("Kart Numarası geçerli değil!");
                }

                musteri.setAdSoyad(ad);
                musteri.setKartAdi(tKAdi.getText().trim());
                musteri.setKartNo(kNo);
                musteri.setSonKullanma(tSKul.getText().trim());
                
                SistemHafizasi.verileriKaydet();
                kullaniciBilgileriniGuncelle();
                JOptionPane.showMessageDialog(this, "Güncellendi!");

            } catch (GecersizGirisException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void kullaniciBilgileriniGuncelle() {
        String kartMetni = "Sistemde kayıtlı bir ödeme yönteminiz bulunmamaktadır.";
        if (musteri.getKartNo() != null && !musteri.getKartNo().isEmpty()) {
            String kNo = musteri.getKartNo();
            String gizliNo = kNo.length() >= 4 ? "**** **** **** " + kNo.substring(kNo.length() - 4) : kNo;
            kartMetni = musteri.getKartAdi() + " - " + gizliNo;
        }

        lblKullanici.setText("<html><div style='text-align: center; font-family: Segoe UI;'>" +
        "<h3 style='color:#2c3e50; margin-bottom:5px;'>" + musteri.getAdSoyad() + "</h3>" + 
        "<p style='margin-top:0; color:#7f8c8d;'>Kullanıcı Adı: @" + musteri.getKullaniciAdi() + "</p><hr>" +
        "<p style='margin-top:10px;'><b style='color:#2980b9;'>Kayıtlı Kart:</b><br>" + kartMetni + "</p>" +
        "</div></html>");
    }
}