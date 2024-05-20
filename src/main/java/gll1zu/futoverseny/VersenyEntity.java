package gll1zu.futoverseny;

import jakarta.persistence.*;

@Entity
@Table(name = "verseny")
public class VersenyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long versenyId;

    @Column(name = "Verseny_neve", nullable = false)
    private String versenyNev;

    @Column(name = "tavolsag_km", nullable = false)
    private int versenyTav;


    public long getVersenyId() {
        return versenyId;
    }

    public String getVersenyNev() {
        return versenyNev;
    }

    public int getVersenyTav() {
        return versenyTav;
    }



    public void setVersenyId(long versenyId) {
        this.versenyId = versenyId;
    }

    public void setVersenyNev(String versenyNev) {
        this.versenyNev = versenyNev;
    }

    public void setVersenyTav(int versenyTav) {
        this.versenyTav = versenyTav;
    }


}
