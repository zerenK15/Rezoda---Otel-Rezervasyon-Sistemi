package proje.MenuEkranlari;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import proje.mantik.SistemHafizasi;

@SuppressWarnings("serial")
public class AnaMenuGUI extends JFrame {

    public AnaMenuGUI() {
        // Sistemi ve dummy müşteriyi başlatıyoruz
        SistemHafizasi.init();

        setTitle("Rezoda - Otel Rezervasyon Sistemi");
        setBounds(100, 100, 700, 550);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));
        getContentPane().setLayout(null);

        // BAŞLIK PANEL
        JPanel ustPanel = new JPanel();
        ustPanel.setBackground(new Color(41, 128, 185));
        ustPanel.setBounds(0, 0, 700, 80);
        ustPanel.setLayout(null);
        getContentPane().add(ustPanel);

        JLabel lblLogo = new JLabel("REZODA");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBounds(30, 20, 250, 50);
        ustPanel.add(lblLogo);

        // PROFİL BUTONU (Yeni Yeri ve Tasarımı)
        JButton btnProfil = new JButton("👤 PROFİL");
        btnProfil.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnProfil.setBackground(new Color(52, 73, 94)); // Daha koyu, premium bir lacivert
        btnProfil.setForeground(Color.WHITE);
        btnProfil.setBounds(490, 20, 140, 35);
        btnProfil.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1)); 
        btnProfil.setFocusPainted(false);
        btnProfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProfilEkraniGUI profilEkran = new ProfilEkraniGUI(SistemHafizasi.aktifMusteri, SistemHafizasi.globalRezervasyonlar);
                profilEkran.setVisible(true);
            }
        });
        ustPanel.add(btnProfil);

        // X BUTONU
        JButton btnKapat = new JButton("✕");
        btnKapat.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKapat.setBackground(new Color(231, 76, 60));
        btnKapat.setForeground(Color.WHITE);
        btnKapat.setBounds(650, 20, 35, 35);
        btnKapat.setBorderPainted(false);
        btnKapat.setFocusPainted(false);
        btnKapat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ustPanel.add(btnKapat);

        // MENU PANEL
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(245, 247, 250));
        menuPanel.setBounds(50, 120, 600, 350);
        menuPanel.setLayout(null);
        getContentPane().add(menuPanel);

        // BUTONLARIN YENİ DİZİLİMİ (3 Buton için ortalandı)
        // BUTON 1: OTEL ARA
        JButton btnAra = new JButton("🔍 OTEL ARA");
        btnAra.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAra.setBackground(new Color(52, 152, 219));
        btnAra.setForeground(Color.WHITE);
        btnAra.setBounds(10, 80, 280, 80);
        btnAra.setBorderPainted(false);
        btnAra.setFocusPainted(false);
        btnAra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AramaEkraniGUI aramaEkran = new AramaEkraniGUI(AnaMenuGUI.this);
                aramaEkran.setVisible(true);
                setVisible(false);
            }
        });
        menuPanel.add(btnAra);

        // BUTON 2: HAKKIMIZDA
        JButton btnHakkimizda = new JButton("ℹ️ HAKKIMIZDA");
        btnHakkimizda.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHakkimizda.setBackground(new Color(230, 126, 34));
        btnHakkimizda.setForeground(Color.WHITE);
        btnHakkimizda.setBounds(310, 80, 280, 80);
        btnHakkimizda.setBorderPainted(false);
        btnHakkimizda.setFocusPainted(false);
        btnHakkimizda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String hakkimizdaMetni = "<html><body style='width: 320px; font-family: Segoe UI;'>" +
                        "<h2 style='color: #2980b9; margin-bottom: 5px;'>REZODA Rezervasyon Sistemi</h2>" +
                        "<p style='margin-top: 0;'><b>Sürüm:</b> 1.0</p>" +
                        "<p>Bu uygulama <b>Object Oriented Programming</b> dersi kapsamında geliştirilmiştir.</p>" +
                        "<h3 style='color: #2c3e50; border-bottom: 1px solid #ccc; padding-bottom: 3px;'>Grup 25 Üyeleri:</h3>" +
                        "<ul style='margin-top: 5px;'>" +
                        "<li>Mehmet Berkay Yalçın (22118080045)</li>" +
                        "<li>Ayşenur Yerli (24118080768)</li>" +
                        "<li>Zeren Kızılsu (25118080067)</li>" +
                        "<li>Muhammed Sait Ataoğlu (25118080776)</li>" +
                        "</ul>" +
                        "</body></html>";
                
                JOptionPane.showMessageDialog(AnaMenuGUI.this, hakkimizdaMetni, "Hakkımızda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuPanel.add(btnHakkimizda);

        // BUTON 3: ÇIKIŞ (Alta Ortalı)
        JButton btnCikis = new JButton("🚪 ÇIKIŞ");
        btnCikis.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCikis.setBackground(new Color(231, 76, 60));
        btnCikis.setForeground(Color.WHITE);
        btnCikis.setBounds(160, 190, 280, 80);
        btnCikis.setBorderPainted(false);
        btnCikis.setFocusPainted(false);
        btnCikis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuPanel.add(btnCikis);

        // ALT PANEL
        JPanel altPanel = new JPanel();
        altPanel.setBackground(new Color(52, 73, 94));
        altPanel.setBounds(0, 510, 700, 40);
        altPanel.setLayout(null);
        getContentPane().add(altPanel);

        JLabel lblDurum = new JLabel("Hoş geldiniz! Lütfen bir seçim yapın.");
        lblDurum.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDurum.setForeground(Color.WHITE);
        lblDurum.setBounds(20, 10, 300, 20);
        altPanel.add(lblDurum);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        AnaMenuGUI frame = new AnaMenuGUI();
        frame.setVisible(true);
    }
}