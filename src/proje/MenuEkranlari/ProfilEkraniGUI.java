package proje.MenuEkranlari;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List; // İŞTE HATAYI ÇÖZEN HAYAT KURTARICI SATIR!

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import proje.GenelSiniflar.Musteri;
import proje.GenelSiniflar.Rezervasyon;
import proje.hatalar.GecersizGirisException;
import proje.mantik.SistemHafizasi;

@SuppressWarnings("serial")
public class ProfilEkraniGUI extends JFrame {
    private JTable tablo;
    private DefaultTableModel tabloModel;
    private Musteri musteri;
    private JLabel lblKullanici; 

    public ProfilEkraniGUI(Musteri musteri, List<Rezervasyon> musteriRezervasyonlari) {
        this.musteri = musteri;
        setTitle("Profil Ekranı");
        setBounds(100, 100, 750, 480);
        getContentPane().setLayout(new BorderLayout(10, 10));

        // --- ÜST KISIM (KULLANICI VE KART BİLGİLERİ) ---
        JPanel ustPanel = new JPanel(new BorderLayout(0, 10));
        ustPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        
        JPanel baslikPanel = new JPanel(new BorderLayout());
        JLabel baslik = new JLabel("Kullanıcı Profil ve Rezervasyon Bilgileri", SwingConstants.CENTER);
        baslik.setFont(new Font("Segoe UI", Font.BOLD, 20));
        baslikPanel.add(baslik, BorderLayout.CENTER);

        JButton btnDuzenle = new JButton("✏️ Profili Düzenle");
        btnDuzenle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnDuzenle.setBackground(new Color(241, 196, 15));
        btnDuzenle.setFocusPainted(false);
        btnDuzenle.addActionListener(e -> profiliDuzenlePenceresiAc());
        baslikPanel.add(btnDuzenle, BorderLayout.EAST);
        
        ustPanel.add(baslikPanel, BorderLayout.NORTH);

        lblKullanici = new JLabel("", SwingConstants.CENTER);
        lblKullanici.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ustPanel.add(lblKullanici, BorderLayout.CENTER);

        kullaniciBilgileriniGuncelle();

        getContentPane().add(ustPanel, BorderLayout.NORTH);

        // --- ORTA KISIM (TABLO) ---
        tabloModel = new DefaultTableModel(new Object[]{"Rezervasyon No", "Oda No", "Giriş Tarihi", "Çıkış Tarihi", "Aktif"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablo = new JTable(tabloModel);
        tablo.setFillsViewportHeight(true);
        tablo.setRowHeight(25);
        tablo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().add(new JScrollPane(tablo), BorderLayout.CENTER);

        // --- ALT KISIM (Geri Butonu ve Açıklama) ---
        JPanel altPanel = new JPanel(new BorderLayout());
        altPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        JLabel aciklama = new JLabel("Yukarıda adınıza kayıtlı olan rezervasyonlar listelenmektedir.");
        aciklama.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        altPanel.add(aciklama, BorderLayout.WEST);

        JButton btnGeri = new JButton("← Geri");
        btnGeri.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGeri.setBackground(new Color(149, 165, 166));
        btnGeri.setForeground(Color.WHITE);
        btnGeri.setFocusPainted(false);
        btnGeri.addActionListener(e -> dispose());
        altPanel.add(btnGeri, BorderLayout.EAST);

        getContentPane().add(altPanel, BorderLayout.SOUTH);

        doldurTablo(musteriRezervasyonlari);
        setVisible(true);
    }

    private void kullaniciBilgileriniGuncelle() {
        String adSoyad = (musteri != null && musteri.getAdSoyad() != null && !musteri.getAdSoyad().isEmpty()) ? musteri.getAdSoyad() : "Bilinmiyor";
        String tc = (musteri != null && musteri.getTcKimlik() != null && !musteri.getTcKimlik().isEmpty()) ? musteri.getTcKimlik() : "Bilinmiyor";
        String tel = (musteri != null && musteri.getTelefon() != null && !musteri.getTelefon().isEmpty()) ? musteri.getTelefon() : "Bilinmiyor";

        String kartMetni = "Sistemde kayıtlı bir ödeme yönteminiz bulunmamaktadır.";
        if (SistemHafizasi.kartKayitliMi) {
            String gizliNo = "";
            if(SistemHafizasi.kayitliKartNo.length() >= 4) {
                 gizliNo = "**** **** **** " + SistemHafizasi.kayitliKartNo.substring(SistemHafizasi.kayitliKartNo.length() - 4);
            } else {
                 gizliNo = SistemHafizasi.kayitliKartNo;
            }
            kartMetni = SistemHafizasi.kayitliKartAdi + " - " + gizliNo;
        }

        String kullaniciBilgisi = String.format(
                "<html><div style='text-align: center; font-family: Segoe UI;'>" +
                "<b>Ad Soyad:</b> %s &nbsp;&nbsp;&nbsp; <b>TC:</b> %s &nbsp;&nbsp;&nbsp; <b>Telefon:</b> %s<br><br>" +
                "<b style='color:#2980b9;'>Kayıtlı Kartınız:</b> <span style='color:#2980b9;'>%s</span>" +
                "</div></html>",
                adSoyad, tc, tel, kartMetni);
                
        lblKullanici.setText(kullaniciBilgisi);
    }

    private void profiliDuzenlePenceresiAc() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        JTextField txtAd = new JTextField(musteri.getAdSoyad());
        JTextField txtTc = new JTextField(musteri.getTcKimlik());
        JTextField txtTel = new JTextField(musteri.getTelefon());
        
        JTextField txtKartAdi = new JTextField(SistemHafizasi.kayitliKartAdi);
        JTextField txtKartNo = new JTextField(SistemHafizasi.kayitliKartNo);
        JTextField txtSonKullanma = new JTextField(SistemHafizasi.kayitliSonKullanma);

        panel.add(new JLabel("Ad Soyad:")); panel.add(txtAd);
        panel.add(new JLabel("TC Kimlik (11 Hane):")); panel.add(txtTc);
        panel.add(new JLabel("Telefon:")); panel.add(txtTel);
        panel.add(new JLabel("Kayıtlı Kart Adı:")); panel.add(txtKartAdi);
        panel.add(new JLabel("Kart Numarası:")); panel.add(txtKartNo);
        panel.add(new JLabel("Son Kullanma (AA/YY):")); panel.add(txtSonKullanma);

        int secim = JOptionPane.showConfirmDialog(this, panel, "Profil Bilgilerini Düzenle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (secim == JOptionPane.OK_OPTION) {
            try {
                String ad = txtAd.getText().trim();
                String tc = txtTc.getText().trim();
                String tel = txtTel.getText().trim();
                String kAdi = txtKartAdi.getText().trim();
                String kNo = txtKartNo.getText().trim();
                String sKullanma = txtSonKullanma.getText().trim();

                // 1. ZORUNLU ALAN BOŞLUK KONTROLLERİ
                if (ad.isEmpty() || tc.isEmpty() || tel.isEmpty()) {
                    throw new GecersizGirisException("Ad, TC ve Telefon bilgileri boş bırakılamaz!");
                }

                // 2. KİŞİ BİLGİSİ FORMAT KONTROLLERİ
                if (!ad.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$")) {
                    throw new GecersizGirisException("Ad Soyad alanı sadece harflerden oluşmalıdır!");
                }
                if (!tc.matches("[0-9]{11}")) {
                    throw new GecersizGirisException("TC Kimlik numarası hatalı! Tam olarak 11 haneli rakamlardan oluşmalıdır.");
                }
                if (!tel.matches("[0-9\\s]+")) {
                    throw new GecersizGirisException("Telefon numarası hatalı! Sadece rakam içermelidir.");
                }

                // 3. KART BİLGİLERİ GİRİLMİŞSE ONLARI KONTROL ET
                if (!kNo.isEmpty()) {
                    if (!kAdi.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$")) {
                        throw new GecersizGirisException("Kart sahibinin adı sadece harflerden oluşmalıdır!");
                    }
                    if (kNo.length() < 16 || !kNo.matches("[0-9\\s]+")) {
                        throw new GecersizGirisException("Kart numarası en az 16 haneli rakamlardan oluşmalıdır!");
                    }
                    if (!sKullanma.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
                        throw new GecersizGirisException("Son kullanma tarihi geçerli değil! (Örn: 12/28)");
                    }
                }

                // TÜM KONTROLLER GEÇİLDİYSE KAYDET
                musteri.setAdSoyad(ad);
                musteri.setTcKimlik(tc);
                musteri.setTelefon(tel);

                if (!kNo.isEmpty()) {
                    SistemHafizasi.kayitliKartAdi = kAdi;
                    SistemHafizasi.kayitliKartNo = kNo;
                    SistemHafizasi.kayitliSonKullanma = sKullanma;
                    SistemHafizasi.kartKayitliMi = true;
                } else {
                    SistemHafizasi.kartKayitliMi = false;
                    SistemHafizasi.kayitliKartAdi = "";
                    SistemHafizasi.kayitliKartNo = "";
                    SistemHafizasi.kayitliSonKullanma = "";
                }

                SistemHafizasi.verileriKaydet();
                kullaniciBilgileriniGuncelle();
                JOptionPane.showMessageDialog(this, "Profil bilgileriniz başarıyla güncellendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

            } catch (GecersizGirisException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Geçersiz Bilgi Girişi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void doldurTablo(List<Rezervasyon> list) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy");
        tabloModel.setRowCount(0);
        
        for (Rezervasyon r : list) {
            tabloModel.addRow(new Object[]{
                    r.getRezervasyonId(), 
                    r.getOdaNo(),
                    r.getGirisTarihi() != null ? r.getGirisTarihi().format(formatter) : "",
                    r.getCikisTarihi() != null ? r.getCikisTarihi().format(formatter) : "",
                    r.isAktifMi() ? "Evet" : "Hayır"
            });
        }
    }
}