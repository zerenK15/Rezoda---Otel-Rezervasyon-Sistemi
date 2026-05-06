package proje.GenelSiniflar;

public class Otel {
	private String otelAdi;
    private int yildizSayisi;
    private Lokasyon lokasyon;

    public Otel(String otelAdi, int yildizSayisi, Lokasyon lokasyon) {
        this.otelAdi = otelAdi;
        this.yildizSayisi = yildizSayisi;
        this.lokasyon = lokasyon;
    }

    public String getOtelAdi() { return otelAdi; }
    public int getYildizSayisi() { return yildizSayisi; }
    public Lokasyon getLokasyon() { return lokasyon; }
}
