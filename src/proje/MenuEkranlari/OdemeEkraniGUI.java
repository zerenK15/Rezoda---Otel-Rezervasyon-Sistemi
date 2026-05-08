package proje.MenuEkranlari;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proje.GenelSiniflar.Otel;
import proje.OdaSiniflari.OdaTipi;
import proje.mantik.SistemHafizasi;
import proje.hatalar.GecersizGirisException;
import proje.hatalar.GecmisTarihException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OdemeEkraniGUI extends JFrame {
    private JFrame odaEkran;
    private Otel otel;
    private OdaTipi odaTipi;
    private LocalDate girisTarihi;
    private LocalDate cikisTarihi;
    private int gunSayisi;
    private double toplamTutar;

    public OdemeEkraniGUI(JFrame odaEkran, Otel otel, OdaTipi odaTipi, LocalDate girisTarihi, LocalDate cikisTarihi) {
        this.odaEkran = odaEkran;
        this.otel = otel;
        this.odaTipi = odaTipi;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        this.gunSayisi = (int) ChronoUnit.DAYS.between(girisTarihi, cikisTarihi);
        this.toplamTutar = odaTipi.getGunlukFiyat() * gunSayisi;

        setTitle("Rezoda - Ödeme");
        setBounds(100, 100, 700, 650);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));
        getContentPane().setLayout(null);

        // --- ÜST PANEL ---
        JPanel ustPanel = new JPanel();
        ustPanel.setBackground(new Color(41, 128, 185));
        ustPanel.setBounds(0, 0, 700, 80);
        ustPanel.setLayout(null);
        getContentPane().add(ustPanel);

        JLabel lblBaslik = new JLabel("ÖDEME");
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblBaslik.setForeground(Color.WHITE);
        lblBaslik.setBounds(30, 20, 400, 50);
        ustPanel.add(lblBaslik);

        // --- ÖZET PANEL ---
        JPanel ozetPanel = new JPanel();
        ozetPanel.setBackground(new Color(236, 240, 241));
        ozetPanel.setBounds(30, 100, 640, 120);
        ozetPanel.setLayout(null);
        ozetPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1));
        getContentPane().add(ozetPanel);

        ozetPanel.add(createLabel("Otel: " + otel.getAd(), 20, 10, 300, 25, true));
        ozetPanel.add(createLabel("Oda: " + odaTipi.getLabel(), 20, 35, 300, 25, true));
        ozetPanel.add(createLabel("Tarih: " + girisTarihi + " / " + cikisTarihi + " (" + gunSayisi + " Gün)", 20, 60, 400, 25, true));
        
        JLabel lblTutar = createLabel("Toplam Tutar: " + String.format("%.2f TL", toplamTutar), 20, 85, 300, 25, true);
        lblTutar.setForeground(new Color(231, 76, 60));
        lblTutar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ozetPanel.add(lblTutar);

        // --- BİLGİ GİRİŞ PANELİ ---
        JPanel bilgiPanel = new JPanel();
        bilgiPanel.setBackground(Color.WHITE);
        bilgiPanel.setBounds(30, 240, 640, 280);
        bilgiPanel.setLayout(null);
        bilgiPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1));
        getContentPane().add(bilgiPanel);

        JTextField txtKartAdi = new JTextField();
        JPasswordField txtKartNo = new JPasswordField();
        JTextField txtSonKullanma = new JTextField();
        JPasswordField txtCVV = new JPasswordField();

        bilgiPanel.add(createLabel("Kart Sahibinin Adı:", 20, 20, 150, 25, true));
        txtKartAdi.setBounds(200, 20, 400, 25); bilgiPanel.add(txtKartAdi);

        bilgiPanel.add(createLabel("Kart Numarası:", 20, 60, 150, 25, true));
        txtKartNo.setBounds(200, 60, 400, 25); bilgiPanel.add(txtKartNo);

        bilgiPanel.add(createLabel("Son Kullanma (AA/YY):", 20, 100, 150, 25, true));
        txtSonKullanma.setBounds(200, 100, 150, 25); bilgiPanel.add(txtSonKullanma);

        bilgiPanel.add(createLabel("CVV:", 20, 140, 150, 25, true));
        txtCVV.setBounds(200, 140, 100, 25); bilgiPanel.add(txtCVV);

        JCheckBox chkKaydet = new JCheckBox("Kartı kaydet");
        chkKaydet.setBounds(20, 180, 200, 25);
        bilgiPanel.add(chkKaydet);

        JCheckBox chkKullan = new JCheckBox("Kayıtlı Kartı Kullan");
        chkKullan.setBounds(20, 215, 200, 25);
        chkKullan.setEnabled(SistemHafizasi.kartKayitliMi);
        bilgiPanel.add(chkKullan);

        chkKullan.addActionListener(e -> {
            if (chkKullan.isSelected() && SistemHafizasi.kartKayitliMi) {
                txtKartAdi.setText(SistemHafizasi.kayitliKartAdi);
                txtKartNo.setText(SistemHafizasi.kayitliKartNo);
                txtSonKullanma.setText(SistemHafizasi.kayitliSonKullanma);
            }
        });

        // --- ÖDEME YAP BUTONU (HATA YAKALAMA BURADA) ---
        JButton btnOde = new JButton("✓ ÖDEME YAP");
        btnOde.setBounds(250, 545, 200, 40);
        btnOde.setBackground(new Color(46, 204, 113));
        btnOde.setForeground(Color.WHITE);
        btnOde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (girisTarihi.isBefore(LocalDate.now())) {
                        throw new GecmisTarihException("Geçmiş bir tarih için rezervasyon yapılamaz!");
                    }

                    String kAdi = txtKartAdi.getText().trim();
                    String kNo = new String(txtKartNo.getPassword());
                    String sKullanma = txtSonKullanma.getText().trim();
                    String cvv = new String(txtCVV.getPassword());

                    if (kAdi.isEmpty() || kNo.isEmpty() || sKullanma.isEmpty() || cvv.isEmpty()) {
                        throw new GecersizGirisException("Lütfen tüm ödeme bilgilerini doldurunuz.");
                    }
                    if (!kAdi.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$")) {
                        throw new GecersizGirisException("İsim sadece harflerden oluşmalıdır.");
                    }
                    if (kNo.length() < 16 || !kNo.matches("[0-9\\s]+")) {
                        throw new GecersizGirisException("Kart numarası en az 16 haneli rakam olmalıdır.");
                    }
                    if (!sKullanma.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
                        throw new GecersizGirisException("Son kullanma AA/YY formatında olmalıdır.");
                    }
                    if (cvv.length() != 3 || !cvv.matches("[0-9]+")) {
                        throw new GecersizGirisException("CVV 3 haneli bir rakam olmalıdır.");
                    }

                    // Onaylanırsa
                    if (chkKaydet.isSelected()) {
                        SistemHafizasi.kayitliKartAdi = kAdi;
                        SistemHafizasi.kayitliKartNo = kNo;
                        SistemHafizasi.kayitliSonKullanma = sKullanma;
                        SistemHafizasi.kartKayitliMi = true;
                    }

                    proje.GenelSiniflar.Rezervasyon yeni = new proje.GenelSiniflar.Rezervasyon(
                        "RZ-"+(System.currentTimeMillis()%10000), 
                        SistemHafizasi.aktifMusteri.getTcKimlik(), "101", girisTarihi, cikisTarihi);
                    
                    SistemHafizasi.globalRezervasyonlar.add(yeni);
                    SistemHafizasi.verileriKaydet();
                    
                    JOptionPane.showMessageDialog(OdemeEkraniGUI.this, "Ödemeniz Onaylandı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    
                    dispose();
                    new AnaMenuGUI().setVisible(true);

                } catch (GecmisTarihException | GecersizGirisException ex) {
                    JOptionPane.showMessageDialog(OdemeEkraniGUI.this, ex.getMessage(), "Bilgi Hatası", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(btnOde);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y, int w, int h, boolean bold) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, w, h);
        l.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, 11));
        return l;
    }

    private void donOdaEkranina() {
        this.dispose();
        odaEkran.setVisible(true);
    }
}