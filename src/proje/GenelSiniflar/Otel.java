package proje.GenelSiniflar;

public class Otel {
    private int id;
    private String ad;
    private Lokasyon lokasyon;
    private double puan;
    private int odaSayisi;

    public Otel(int id, String ad, Lokasyon lokasyon, double puan, int odaSayisi) {
        this.id = id;
        this.ad = ad;
        this.lokasyon = lokasyon;
        this.puan = puan;
        this.odaSayisi = odaSayisi;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    public double getPuan() {
        return puan;
    }

    public void setPuan(double puan) {
        this.puan = puan;
    }

    public int getOdaSayisi() {
        return odaSayisi;
    }

    public void setOdaSayisi(int odaSayisi) {
        this.odaSayisi = odaSayisi;
    }

    @Override
    public String toString() {
        return ad + " - " + (lokasyon != null ? lokasyon.getSehir() : "");
    }

}
