package gll1zu.futoverseny;

import jakarta.persistence.*;
@Entity
@Table(name = "futok")
public class FutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long futoId;
    private String futoNev;
    private int futoKor;
    private String futoNeme;

    public FutoEntity() {
    }

    public long getFutoId() {
        return futoId;
    }

    public String getFutoNev() {
        return futoNev;
    }

    public int getFutoKor() {
        return futoKor;
    }

    public String getFutoNeme() {
        return futoNeme;
    }

    public void setFutoId(long futoId) {
        this.futoId = futoId;
    }

    public void setFutoNev(String futoNev) {
        this.futoNev = futoNev;
    }

    public void setFutoKor(int futoKor) {
        this.futoKor = futoKor;
    }

    public void setFutoNeme(String futoNeme) {
        this.futoNeme = futoNeme;
    }

}
