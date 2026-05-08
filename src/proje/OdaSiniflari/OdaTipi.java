package proje.OdaSiniflari;

public enum OdaTipi {
    STANDART("Standart Oda", 1800.0, 2),
    AILE_ODASI("Aile Odası", 2500.0, 4),
    SUIT("Suit Oda", 4200.0, 2),
    KRAL_DAIRESI("Kral Dairesi", 6500.0, 4);

    private final String label;
    private final double gunlukFiyat;
    private final int yatakKapasitesi;

    OdaTipi(String label, double gunlukFiyat, int yatakKapasitesi) {
        this.label = label;
        this.gunlukFiyat = gunlukFiyat;
        this.yatakKapasitesi = yatakKapasitesi;
    }

    public String getLabel() {
        return label;
    }

    public double getGunlukFiyat() {
        return gunlukFiyat;
    }

    public int getYatakKapasitesi() {
        return yatakKapasitesi;
    }

    @Override
    public String toString() {
        return String.format("%s - %.0f TL/Gün", label, gunlukFiyat);
    }
}
