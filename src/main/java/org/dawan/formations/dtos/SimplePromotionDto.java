package org.dawan.formations.dtos;

import java.io.Serializable;
import java.util.Date;

public class SimplePromotionDto implements Serializable {
    private long id;
    private Date dateDebut;
    private Date dateFin;
    private TitreProfessionnelDto titreProfessionnel;
    private VilleDto ville;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public TitreProfessionnelDto getTitreProfessionnel() {
        return titreProfessionnel;
    }

    public void setTitreProfessionnel(TitreProfessionnelDto titreProfessionnel) {
        this.titreProfessionnel = titreProfessionnel;
    }

    public VilleDto getVille() {
        return ville;
    }

    public void setVille(VilleDto ville) {
        this.ville = ville;
    }
}
