package proje.MenuEkranlari;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import proje.GenelSiniflar.Otel;
import proje.OdaSiniflari.OdaTipi;

@SuppressWarnings("serial")
public class OdaSecimEkraniGUI extends JFrame {
    private JFrame aramaEkran;
    private Otel secilenOtel;
    private OdaTipi secilenOda;
    private JLabel lblToplamFiyat;
    
    private JComboBox<LocalDate> cbGirisTarihi;
    private JComboBox<LocalDate> cbCikisTarihi;
    
    private List<JButton> odaButonlari = new ArrayList<>();

    public OdaSecimEkraniGUI(JFrame aramaEkran, Otel otel) {
        this.aramaEkran = aramaEkran;
        this.secilenOtel = otel;

        setTitle("Rezoda - Oda Seçimi");
        setBounds(100, 100, 900, 750); 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));
        getContentPane().setLayout(null);

        // BAŞLIK PANEL
        JPanel ustPanel = new JPanel();
        ustPanel.setBackground(new Color(41, 128, 185));
        ustPanel.setBounds(0, 0, 900, 80);
        ustPanel.setLayout(null);
        getContentPane().add(ustPanel);

        JLabel lblBaslik = new JLabel("ODA SEÇİMİ - " + otel.getAd());
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBaslik.setForeground(Color.WHITE);
        lblBaslik.setBounds(30, 20, 700, 50);
        ustPanel.add(lblBaslik);

        // X BUTONU
        JButton btnKapat = new JButton("✕");
        btnKapat.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKapat.setBackground(new Color(231, 76, 60));
        btnKapat.setForeground(Color.WHITE);
        btnKapat.setBounds(850, 20, 35, 35);
        btnKapat.setBorderPainted(false);
        btnKapat.setFocusPainted(false);
        btnKapat.addActionListener(e -> donAramaEkranina());
        ustPanel.add(btnKapat);

        // TARİH SEÇİMİ PANEL
        JPanel tarihPanel = new JPanel();
        tarihPanel.setBackground(new Color(236, 240, 241));
        tarihPanel.setBounds(30, 100, 840, 60);
        tarihPanel.setLayout(null);
        tarihPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1));
        getContentPane().add(tarihPanel);

        JLabel lblGiris = new JLabel("Giriş Tarihi:");
        lblGiris.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblGiris.setBounds(20, 15, 80, 30);
        tarihPanel.add(lblGiris);

        cbGirisTarihi = new JComboBox<>();
        cbGirisTarihi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbGirisTarihi.setBounds(100, 15, 130, 30);
        LocalDate bugun = LocalDate.now();
        for (int i = 0; i <= 30; i++) {
            cbGirisTarihi.addItem(bugun.plusDays(i));
        }
        tarihPanel.add(cbGirisTarihi);

        JLabel lblCikis = new JLabel("Çıkış Tarihi:");
        lblCikis.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblCikis.setBounds(270, 15, 80, 30);
        tarihPanel.add(lblCikis);

        cbCikisTarihi = new JComboBox<>();
        cbCikisTarihi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbCikisTarihi.setBounds(350, 15, 130, 30);
        tarihPanel.add(cbCikisTarihi);

        // Giriş tarihi değiştiğinde çıkış tarihlerini dinamik 1 ay menzille güncelle
        cbGirisTarihi.addActionListener(e -> {
            LocalDate secilenGiris = (LocalDate) cbGirisTarihi.getSelectedItem();
            cbCikisTarihi.removeAllItems();
            if (secilenGiris != null) {
                for (int i = 1; i <= 30; i++) {
                    cbCikisTarihi.addItem(secilenGiris.plusDays(i));
                }
            }
            updateToplamFiyat();
        });

        cbCikisTarihi.addActionListener(e -> updateToplamFiyat());
        
        // Başlangıç tetiklemesi
        cbGirisTarihi.setSelectedIndex(0);

        // ODA SEÇENEKLERI PANEL
        JPanel odaPanel = new JPanel();
        odaPanel.setBackground(new Color(245, 247, 250));
        odaPanel.setBounds(30, 180, 840, 400);
        odaPanel.setLayout(new GridLayout(2, 2, 15, 15));
        odaPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        getContentPane().add(odaPanel);

        OdaTipi[] odaTipleri = OdaTipi.values();
        for (OdaTipi odaTipi : odaTipleri) {
            JButton btnOda = createOdaButton(odaTipi);
            odaButonlari.add(btnOda); 
            odaPanel.add(btnOda);
        }

        // TOPLAM FİYAT PANEL 
        JPanel fiyatPanel = new JPanel();
        fiyatPanel.setBackground(new Color(236, 240, 241));
        fiyatPanel.setBounds(30, 590, 840, 60);
        fiyatPanel.setLayout(null);
        fiyatPanel.setBorder(new LineBorder(new Color(189, 195, 199), 2));
        getContentPane().add(fiyatPanel);

        JLabel lblFiyatBaslik = new JLabel("Toplam Fiyat:");
        lblFiyatBaslik.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblFiyatBaslik.setBounds(20, 15, 150, 30);
        fiyatPanel.add(lblFiyatBaslik);

        lblToplamFiyat = new JLabel("0.00 TL");
        lblToplamFiyat.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblToplamFiyat.setForeground(new Color(231, 76, 60));
        lblToplamFiyat.setBounds(200, 10, 200, 40);
        fiyatPanel.add(lblToplamFiyat);

        // DEVAM BUTONU
        JButton btnDevam = new JButton("→ ÖDEME EKRANINA GİT");
        btnDevam.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDevam.setBackground(new Color(46, 204, 113));
        btnDevam.setForeground(Color.WHITE);
        btnDevam.setBounds(650, 660, 220, 40); 
        btnDevam.setBorderPainted(false);
        btnDevam.setFocusPainted(false);
        btnDevam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (secilenOda != null && cbGirisTarihi.getSelectedItem() != null && cbCikisTarihi.getSelectedItem() != null) {
                    LocalDate giris = (LocalDate) cbGirisTarihi.getSelectedItem();
                    LocalDate cikis = (LocalDate) cbCikisTarihi.getSelectedItem();
                    
                    OdemeEkraniGUI odemeEkran = new OdemeEkraniGUI(OdaSecimEkraniGUI.this, secilenOtel, secilenOda, giris, cikis);
                    odemeEkran.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(OdaSecimEkraniGUI.this, "Lütfen bir oda tipi seçiniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        getContentPane().add(btnDevam);

        // GERİ BUTONU
        JButton btnGeri = new JButton("← GERİ");
        btnGeri.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGeri.setBackground(new Color(149, 165, 166));
        btnGeri.setForeground(Color.WHITE);
        btnGeri.setBounds(30, 660, 150, 40);
        btnGeri.setBorderPainted(false);
        btnGeri.setFocusPainted(false);
        btnGeri.addActionListener(e -> donAramaEkranina());
        getContentPane().add(btnGeri);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                donAramaEkranina();
            }
        });
    }

    private JButton createOdaButton(OdaTipi odaTipi) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout(10, 10));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setBorder(new LineBorder(new Color(189, 195, 199), 2)); 

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblAd = new JLabel(odaTipi.getLabel());
        lblAd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAd.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblFiyat = new JLabel(String.format("%.0f TL/Gün", odaTipi.getGunlukFiyat()));
        lblFiyat.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFiyat.setForeground(new Color(231, 76, 60));
        lblFiyat.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblKapasite = new JLabel("Kapasite: " + odaTipi.getYatakKapasitesi() + " kişi");
        lblKapasite.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblKapasite.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblAd);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblFiyat);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblKapasite);

        btn.add(panel, BorderLayout.CENTER);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secilenOda = odaTipi;
                for (JButton b : odaButonlari) {
                    b.setBorder(new LineBorder(new Color(189, 195, 199), 2));
                }
                btn.setBorder(new LineBorder(new Color(52, 152, 219), 4));
                updateToplamFiyat();
            }
        });

        return btn;
    }

    private void updateToplamFiyat() {
        if (secilenOda != null && cbGirisTarihi.getSelectedItem() != null && cbCikisTarihi.getSelectedItem() != null) {
            LocalDate giris = (LocalDate) cbGirisTarihi.getSelectedItem();
            LocalDate cikis = (LocalDate) cbCikisTarihi.getSelectedItem();
            int gunSayisi = (int) ChronoUnit.DAYS.between(giris, cikis);
            
            if (gunSayisi > 0) {
                double toplamFiyat = secilenOda.getGunlukFiyat() * gunSayisi;
                lblToplamFiyat.setText(String.format("%.2f TL (%d Gün)", toplamFiyat, gunSayisi));
            } else {
                lblToplamFiyat.setText("Hatalı Tarih!");
            }
        }
    }

    private void donAramaEkranina() {
        this.setVisible(false);
        this.dispose();
        aramaEkran.setVisible(true);
    }
}