import java.io.Serializable;

public class Bok implements Serializable {

    private String forfatter;
    private int antallSider;
    private String tittel;

    public Bok(String forfatter, int antallSider, String tittel) {

        this.setAntallSider(antallSider);
        this.setForfatter(forfatter);
        this.setTittel(tittel);

    }

    public String getForfatter() { return forfatter; }
    public int getAntallSider() { return antallSider; }
    public String getTittel() { return tittel; }

    public void setForfatter(String forfatter) { this.forfatter = forfatter; }
    public void setAntallSider(int antallSider) { this.antallSider = antallSider; }
    public void setTittel(String tittel) { this.tittel = tittel; }

}
