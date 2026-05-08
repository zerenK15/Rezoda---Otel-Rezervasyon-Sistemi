package proje.MenuEkranlari;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate; // TARIH ICIN EKLENDI

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import proje.GenelSiniflar.Otel;
import proje.OdaSiniflari.OdaTipi;
import proje.mantik.OtelFiyatHesaplayici;

@SuppressWarnings("serial")
public class OtelSecimEkraniGUI extends JFrame {
    private final JFrame searchFrame;
    private final JFrame returnFrame;
    private final Otel otel;
    private JComboBox<OdaTipi> comboOdaTipi;
    private JSpinner spinnerGunSayisi;
    private JLabel lblToplam;
    private double gecelikFiyat;

    public OtelSecimEkraniGUI(JFrame searchFrame, JFrame returnFrame, Otel otel) {
        this.searchFrame = searchFrame;
        this.returnFrame = returnFrame;
        this.otel = otel;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Rezoda - Otel Detayları");
        setBounds(100, 100, 640, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (searchFrame != null) {
                    searchFrame.setVisible(true);
                } else if (returnFrame != null) {
                    returnFrame.setVisible(true);
                }
            }
        });
        getContentPane().setBackground(new Color(245, 247, 250));
        getContentPane().setLayout(null);

        JLabel lblBaslik = new JLabel("OTEL SEÇİMİ");
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblBaslik.setForeground(new Color(44, 62, 80));
        lblBaslik.setHorizontalAlignment(SwingConstants.CENTER);
        lblBaslik.setBounds(0, 20, 640, 40);
        getContentPane().add(lblBaslik);

        JPanel otelPanel = new JPanel();
        otelPanel.setBackground(Color.WHITE);
        otelPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            "Otel Bilgileri", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), new Color(52, 73, 94)
        ));
        otelPanel.setBounds(30, 80, 575, 120);
        otelPanel.setLayout(null);
        getContentPane().add(otelPanel);

        JLabel lblOtelAdi = new JLabel("🏨 " + otel.getAd());
        lblOtelAdi.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblOtelAdi.setBounds(20, 20, 520, 30);
        otelPanel.add(lblOtelAdi);

        JLabel lblOtelLokasyon = new JLabel(otel.getLokasyon().getSehir() + " / " + otel.getLokasyon().getIlce() + "  |  " + otel.getPuan() + " Yıldız");
        lblOtelLokasyon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblOtelLokasyon.setForeground(new Color(127, 140, 141));
        lblOtelLokasyon.setBounds(20, 55, 520, 25);
        otelPanel.add(lblOtelLokasyon);

        JLabel lblOzellikler = new JLabel("<html><b>Otel Özellikleri:</b> " + getOtelOzellikleri(otel) + "</html>");
        lblOzellikler.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblOzellikler.setBounds(20, 80, 520, 30);
        otelPanel.add(lblOzellikler);

        JPanel secimPanel = new JPanel();
        secimPanel.setBackground(Color.WHITE);
        secimPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            "Oda Tipi ve Fiyat", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), new Color(52, 73, 94)
        ));
        secimPanel.setBounds(30, 215, 575, 170);
        secimPanel.setLayout(null);
        getContentPane().add(secimPanel);

        JLabel lblOdaTipi = new JLabel("Oda Tipi:");
        lblOdaTipi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblOdaTipi.setBounds(20, 30, 90, 25);
        secimPanel.add(lblOdaTipi);

        comboOdaTipi = new JComboBox<>(OdaTipi.values());
        comboOdaTipi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboOdaTipi.setBackground(Color.WHITE);
        comboOdaTipi.setBounds(110, 30, 250, 30);
        secimPanel.add(comboOdaTipi);

        JLabel lblGunSayisi = new JLabel("Gün Sayısı:");
        lblGunSayisi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblGunSayisi.setBounds(20, 75, 90, 25);
        secimPanel.add(lblGunSayisi);

        spinnerGunSayisi = new JSpinner(new SpinnerNumberModel(1, 1, 14, 1));
        spinnerGunSayisi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        spinnerGunSayisi.setBounds(110, 75, 80, 30);
        secimPanel.add(spinnerGunSayisi);

        JLabel lblToplamLabel = new JLabel("Tahmini Toplam:");
        lblToplamLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblToplamLabel.setBounds(20, 120, 110, 25);
        secimPanel.add(lblToplamLabel);

        lblToplam = new JLabel();
        lblToplam.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblToplam.setForeground(new Color(231, 76, 60));
        lblToplam.setBounds(140, 118, 280, 30);
        secimPanel.add(lblToplam);

        JButton btnHesapla = new JButton("Fiyat Hesapla");
        btnHesapla.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnHesapla.setBackground(new Color(52, 152, 219));
        btnHesapla.setForeground(Color.WHITE);
        btnHesapla.setFocusPainted(false);
        btnHesapla.setBounds(390, 35, 160, 30);
        secimPanel.add(btnHesapla);

        JButton btnOde = new JButton("Rezervasyonu Tamamla");
        btnOde.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnOde.setBackground(new Color(39, 174, 96));
        btnOde.setForeground(Color.WHITE);
        btnOde.setFocusPainted(false);
        btnOde.setBounds(95, 400, 180, 45);
        getContentPane().add(btnOde);

        JButton btnGeri = new JButton("Geri Dön");
        btnGeri.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGeri.setBackground(new Color(231, 76, 60));
        btnGeri.setForeground(Color.WHITE);
        btnGeri.setFocusPainted(false);
        btnGeri.setBounds(365, 400, 180, 45);
        getContentPane().add(btnGeri);

        ActionListener guncelleListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guncelleFiyat();
            }
        };

        comboOdaTipi.addActionListener(guncelleListener);
        spinnerGunSayisi.addChangeListener(e -> guncelleFiyat());
        btnHesapla.addActionListener(guncelleListener);

        btnOde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OdaTipi odaTipi = getSeciliOdaTipi();
                int gunSayisi = (Integer) spinnerGunSayisi.getValue();
                
                // YENİ EKLENEN KISIM: Gün sayısını bugünün tarihine ekleyip LocalDate formatına çeviriyoruz
                LocalDate giris = LocalDate.now();
                LocalDate cikis = giris.plusDays(gunSayisi);

                // OdemeEkraniGUI artık (JFrame, Otel, OdaTipi, LocalDate, LocalDate) bekliyor
                OdemeEkraniGUI odeme = new OdemeEkraniGUI(returnFrame != null ? returnFrame : searchFrame,
                        otel, odaTipi, giris, cikis);
                        
                odeme.setLocationRelativeTo(OtelSecimEkraniGUI.this);
                OtelSecimEkraniGUI.this.dispose();
            }
        });

        btnGeri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchFrame != null) {
                    searchFrame.setVisible(true);
                } else if (returnFrame != null) {
                    returnFrame.setVisible(true);
                }
                OtelSecimEkraniGUI.this.dispose();
            }
        });

        guncelleFiyat();
        setVisible(true);
    }

    private OdaTipi getSeciliOdaTipi() {
        OdaTipi secim = (OdaTipi) comboOdaTipi.getSelectedItem();
        return secim != null ? secim : OdaTipi.STANDART;
    }

    private double getSeciliFiyat() {
        return getSeciliOdaTipi().getGunlukFiyat();
    }

    private String getOtelOzellikleri(Otel otel) {
        StringBuilder ozellikler = new StringBuilder("Ücretsiz Wi-Fi, Kahvaltı, 24/7 Resepsiyon");
        if (otel.getPuan() >= 4) {
            ozellikler.append(", Havuz, Spor Salonu");
        }
        if (otel.getPuan() == 5) {
            ozellikler.append(", Spa, VIP Hizmet");
        }
        return ozellikler.toString();
    }

    private void guncelleFiyat() {
        this.gecelikFiyat = getSeciliFiyat();
        int gunSayisi = (Integer) spinnerGunSayisi.getValue();
        OtelFiyatHesaplayici hesaplayici = new OtelFiyatHesaplayici(gecelikFiyat, gunSayisi, 0);
        lblToplam.setText(String.format("Toplam: %.2f TL (%d gün)", hesaplayici.netOdenecekTutarHesapla(), gunSayisi));
    }
}