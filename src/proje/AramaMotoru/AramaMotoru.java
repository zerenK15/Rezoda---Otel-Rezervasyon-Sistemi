package proje.AramaMotoru;

import java.util.ArrayList;
import java.util.List;

public class AramaMotoru {

    public List<Otel> otelAra(List<Otel> tumOteller, String sehir) {
        List<Otel> sonuclar = new ArrayList<>();
        for (Otel otel : tumOteller) {
            if (otel.getLokasyon().getSehir().equalsIgnoreCase(sehir)) {
                sonuclar.add(otel);
            }
        }
        return sonuclar;
    }


    public List<Otel> otelAra(List<Otel> tumOteller, String sehir, int minYildiz) {
        List<Otel> sonuclar = new ArrayList<>();
        for (Otel otel : tumOteller) {
            if (otel.getLokasyon().getSehir().equalsIgnoreCase(sehir) && otel.getYildizSayisi() >= minYildiz) {
                sonuclar.add(otel);
            }
        }
        return sonuclar;
    }
}