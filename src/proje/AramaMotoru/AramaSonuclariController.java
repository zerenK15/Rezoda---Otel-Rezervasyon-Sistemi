package proje.AramaMotoru;

import java.util.List;
import javafx.scene.control.ListView;

public class AramaSonuclariController {
    private ListView<String> sonucListesiView;

    public AramaSonuclariController(ListView<String> sonucListesiView) {
        this.sonucListesiView = sonucListesiView;
    }

    public void sonuclariGoster(List<Otel> bulunanOteller) {
        sonucListesiView.getItems().clear(); // Önceki aramayı temizle

        if(bulunanOteller.isEmpty()) {
            sonucListesiView.getItems().add("Bu kriterlere uygun otel bulunamadı.");
            return;
        }

        for (Otel otel : bulunanOteller) {
            String gosterilecekMetin = otel.getOtelAdi() + " - " + otel.getYildizSayisi() + " Yıldız (" + otel.getLokasyon().getIlce() + ")";
            sonucListesiView.getItems().add(gosterilecekMetin);
        }
    }
}