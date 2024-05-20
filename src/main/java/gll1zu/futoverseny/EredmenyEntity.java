package gll1zu.futoverseny;

import jakarta.persistence.*;

@Entity
@Table(name = "eredmeny")
public class EredmenyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eredmenyId;

    @ManyToOne
    @JoinColumn(name = "verseny_id", nullable = false)
    private VersenyEntity verseny;

    @ManyToOne
    @JoinColumn(name = "resztvevo_id", nullable = false)
    private FutoEntity resztvevo;

    @Column(name = "Verseny", nullable = false)
    private String versenyNev;

    @Column(name = "Idoeredmeny_perc", nullable = false)
    private int eredmenyIdo;

    public EredmenyEntity() {
    }

    public VersenyEntity getVerseny() {
        return verseny;
    }

    public String getVersenyNev() {
        return versenyNev;
    }

    public FutoEntity getResztvevo() {
        return resztvevo;
    }

    public int getEredmenyIdo() {
        return eredmenyIdo;
    }
    public long getEredmenyId() {
        return eredmenyId;
    }
    public void setVersenyNev(String versenyNev) {
        this.versenyNev = versenyNev;
    }

    public void setResztvevo(FutoEntity resztvevo) {
        this.resztvevo = resztvevo;
    }

    public void setEredmenyIdo(int eredmenyIdo) {
        this.eredmenyIdo = eredmenyIdo;
    }

    public void setVerseny(VersenyEntity verseny) {
        this.verseny = verseny;
    }

    public String getResztvevoNev() {
        return resztvevo.getFutoNev();
    }
}
