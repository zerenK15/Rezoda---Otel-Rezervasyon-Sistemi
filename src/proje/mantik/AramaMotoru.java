package proje.mantik;

import java.util.ArrayList;
import java.util.List;

import proje.GenelSiniflar.Otel;

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
            if (otel.getLokasyon().getSehir().equalsIgnoreCase(sehir) && otel.getPuan() >= minYildiz) {
                sonuclar.add(otel);
            }
        }
        return sonuclar;
    }
}