package proje.MenuEkranlari;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import proje.GenelSiniflar.Rezervasyon;

@SuppressWarnings("serial")
public class RezervasyonBilgisiGUI extends JFrame {
    private JTable tablo;
    private DefaultTableModel tabloModel;

    public RezervasyonBilgisiGUI(List<Rezervasyon> musteriRezervasyonlari) {
        setTitle("Rezoda - Rezervasyon Geçmişim");
        setBounds(150, 150, 750, 400); // Daha geniş ve yatay, tabloya uygun
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 247, 250));

        // --- ÜST PANEL ---
        JPanel ustPanel = new JPanel(new BorderLayout());
        ustPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        ustPanel.setBackground(new Color(41, 128, 185));
        
        JLabel baslik = new JLabel("REZERVASYON GEÇMİŞİM", SwingConstants.CENTER);
        baslik.setFont(new Font("Segoe UI", Font.BOLD, 22));
        baslik.setForeground(Color.WHITE);
        ustPanel.add(baslik, BorderLayout.CENTER);
        getContentPane().add(ustPanel, BorderLayout.NORTH);

        // --- ORTA KISIM (TABLO) ---
        tabloModel = new DefaultTableModel(new Object[]{"Rezervasyon No", "Oda No", "Giriş Tarihi", "Çıkış Tarihi", "Aktif"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablo = new JTable(tabloModel);
        tablo.setFillsViewportHeight(true);
        tablo.setRowHeight(25);
        tablo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablo.getTableHeader().setBackground(new Color(236, 240, 241));
        tablo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel ortaPanel = new JPanel(new BorderLayout());
        ortaPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        ortaPanel.setBackground(new Color(245, 247, 250));
        ortaPanel.add(new JScrollPane(tablo), BorderLayout.CENTER);
        getContentPane().add(ortaPanel, BorderLayout.CENTER);

        // --- ALT KISIM (Geri Butonu) ---
        JPanel altPanel = new JPanel(new BorderLayout());
        altPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        altPanel.setBackground(new Color(245, 247, 250));
        
        JLabel aciklama = new JLabel("Yukarıda adınıza kayıtlı olan geçmiş ve güncel rezervasyonlar listelenmektedir.");
        aciklama.setFont(new Font("Segoe UI", Font.ITALIC, 12));
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

    private void doldurTablo(List<Rezervasyon> list) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        tabloModel.setRowCount(0);
        for(Rezervasyon r : list) {
            tabloModel.addRow(new Object[]{
                r.getRezervasyonId(), 
                r.getOdaNo(), 
                r.getGirisTarihi().format(formatter), 
                r.getCikisTarihi().format(formatter), 
                r.isAktifMi() ? "Evet" : "Hayır"
            });
        }
    }
}