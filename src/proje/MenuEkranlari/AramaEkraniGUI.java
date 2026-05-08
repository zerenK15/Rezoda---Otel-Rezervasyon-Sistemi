package proje.MenuEkranlari;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import proje.GenelSiniflar.Lokasyon;
import proje.GenelSiniflar.Otel;
import proje.mantik.AramaMotoru;

@SuppressWarnings("serial")
public class AramaEkraniGUI extends JFrame {
    private JFrame anaMenu;
    private JList<Otel> otelListesi;
    private DefaultListModel<Otel> modelListesi;

    public AramaEkraniGUI(JFrame anaMenu) {
        this.anaMenu = anaMenu;
        setTitle("Rezoda - Otel Arama");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));
        getContentPane().setLayout(null);

        // BAŞLIK PANEL
        JPanel ustPanel = new JPanel();
        ustPanel.setBackground(new Color(41, 128, 185));
        ustPanel.setBounds(0, 0, 800, 80);
        ustPanel.setLayout(null);
        getContentPane().add(ustPanel);

        JLabel lblBaslik = new JLabel("OTEL ARAMA");
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblBaslik.setForeground(Color.WHITE);
        lblBaslik.setBounds(30, 20, 400, 50);
        ustPanel.add(lblBaslik);

        // X BUTONU
        JButton btnKapat = new JButton("✕");
        btnKapat.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKapat.setBackground(new Color(231, 76, 60));
        btnKapat.setForeground(Color.WHITE);
        btnKapat.setBounds(750, 20, 35, 35);
        btnKapat.setBorderPainted(false);
        btnKapat.setFocusPainted(false);
        btnKapat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                donAnaMenuye();
            }
        });
        ustPanel.add(btnKapat);

        // ARA PANEL
        JPanel araPanel = new JPanel();
        araPanel.setBackground(new Color(236, 240, 241));
        araPanel.setBounds(30, 100, 740, 80);
        araPanel.setLayout(null);
        araPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1));
        getContentPane().add(araPanel);

        JLabel lblSehir = new JLabel("Şehir:");
        lblSehir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSehir.setBounds(20, 15, 60, 25);
        araPanel.add(lblSehir);

        JTextField txtSehir = new JTextField();
        txtSehir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtSehir.setBounds(90, 15, 200, 25);
        araPanel.add(txtSehir);

        JButton btnAra = new JButton("🔍 ARA");
        btnAra.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnAra.setBackground(new Color(52, 152, 219));
        btnAra.setForeground(Color.WHITE);
        btnAra.setBounds(310, 15, 100, 25);
        btnAra.setBorderPainted(false);
        btnAra.setFocusPainted(false);
        araPanel.add(btnAra);

        // OTEL LİSTESİ
        modelListesi = new DefaultListModel<>();
        otelListesi = new JList<>(modelListesi);
        otelListesi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        otelListesi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        otelListesi.setCellRenderer(new OtelListRenderer());

        JScrollPane scrollPane = new JScrollPane(otelListesi);
        scrollPane.setBounds(30, 200, 740, 250);
        getContentPane().add(scrollPane);

        // SEÇ BUTONU (Düzeltilen Kısım)
        JButton btnSec = new JButton("✓ ODALARI SEÇ");
        btnSec.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSec.setBackground(new Color(46, 204, 113));
        btnSec.setForeground(Color.WHITE);
        btnSec.setBounds(300, 470, 200, 40);
        btnSec.setBorderPainted(false);
        btnSec.setFocusPainted(false);
        btnSec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (otelListesi.getSelectedValue() != null) {
                    Otel secilenOtel = otelListesi.getSelectedValue();
                    // Buradaki yorum satırlarını kaldırdım, geçiş artık aktif:
                    OdaSecimEkraniGUI odaEkran = new OdaSecimEkraniGUI(AramaEkraniGUI.this, secilenOtel);
                    odaEkran.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(AramaEkraniGUI.this, "Lütfen bir otel seçiniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        getContentPane().add(btnSec);

        // GERİ BUTONU
        JButton btnGeri = new JButton("← GERİ");
        btnGeri.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGeri.setBackground(new Color(149, 165, 166));
        btnGeri.setForeground(Color.WHITE);
        btnGeri.setBounds(100, 470, 150, 40);
        btnGeri.setBorderPainted(false);
        btnGeri.setFocusPainted(false);
        btnGeri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                donAnaMenuye();
            }
        });
        getContentPane().add(btnGeri);

        // ALT PANEL
        JPanel altPanel = new JPanel();
        altPanel.setBackground(new Color(52, 73, 94));
        altPanel.setBounds(0, 560, 800, 40);
        altPanel.setLayout(null);
        getContentPane().add(altPanel);

        JLabel lblDurum = new JLabel("Otel seçmek için listeye tıklayın.");
        lblDurum.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDurum.setForeground(Color.WHITE);
        lblDurum.setBounds(20, 10, 400, 20);
        altPanel.add(lblDurum);

        btnAra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sehir = txtSehir.getText().trim();
                if (sehir.isEmpty()) {
                    JOptionPane.showMessageDialog(AramaEkraniGUI.this, "Lütfen bir şehir adı giriniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                } else {
                    araOteller(sehir);
                }
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                donAnaMenuye();
            }
        });
    }

    private List<Otel> otelleriDosyadanOku() {
        List<Otel> oteller = new ArrayList<>();
        String dosyaYolu = "oteller.csv"; 
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dosyaYolu), StandardCharsets.UTF_8))) {
            String satir;
            boolean ilkSatir = true;
            
            while ((satir = br.readLine()) != null) {
                if (ilkSatir) {
                    ilkSatir = false;
                    continue;
                }
                
                String[] veriler = satir.split(",");
                if (veriler.length == 6) {
                    int id = Integer.parseInt(veriler[0]);
                    String ad = veriler[1];
                    String sehir = veriler[2];
                    String ilce = veriler[3];
                    double puan = Double.parseDouble(veriler[4]);
                    int odaSayisi = Integer.parseInt(veriler[5]);
                    
                    oteller.add(new Otel(id, ad, new Lokasyon(sehir, ilce), puan, odaSayisi));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Oteller dosyadan okunamadı!\nHata: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
        return oteller;
    }

    private void araOteller(String sehir) {
        modelListesi.clear();
        List<Otel> tumOteller = otelleriDosyadanOku();
        AramaMotoru motor = new AramaMotoru();
        List<Otel> bulunanOteller = motor.otelAra(tumOteller, sehir);
        
        if (bulunanOteller.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bu şehirde otel bulunamadı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Otel otel : bulunanOteller) {
            modelListesi.addElement(otel);
        }
    }

    private void donAnaMenuye() {
        this.setVisible(false);
        this.dispose();
        anaMenu.setVisible(true);
    }

    private static class OtelListRenderer extends JLabel implements ListCellRenderer<Otel> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Otel> list, Otel value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                setText(String.format("<html><b>%s</b> - %s, %s ⭐%.1f (Kapasite: %d)</html>",
                        value.getAd(), value.getLokasyon().getSehir(), value.getLokasyon().getIlce(), value.getPuan(), value.getOdaSayisi()));
                setFont(new Font("Segoe UI", Font.PLAIN, 13));
                setOpaque(true);

                if (isSelected) {
                    setBackground(new Color(52, 152, 219));
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            }
            return this;
        }
    }
}