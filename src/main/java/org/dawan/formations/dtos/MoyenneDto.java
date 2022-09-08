package org.dawan.formations.dtos;

public class MoyenneDto {
    private Double moyenne;
    private Double moyennePromo;
    private long blocId;

    public MoyenneDto(Double moyenne, Double moyennePromo, long blocId) {
        this.moyenne = moyenne;
        this.blocId = blocId;
        this.moyennePromo = moyennePromo;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Double getMoyennePromo() {
        return moyennePromo;
    }

    public void setMoyennePromo(Double moyennePromo) {
        this.moyennePromo = moyennePromo;
    }

    public long getBlocId() {
        return blocId;
    }

    public void setBlocId(long blocId) {
        this.blocId = blocId;
    }
}
