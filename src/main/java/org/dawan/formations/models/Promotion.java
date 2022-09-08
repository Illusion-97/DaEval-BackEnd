package org.dawan.formations.models;


import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import java.util.List;

@Entity
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @ManyToOne(optional = false)
    private TitreProfessionnel titreProfessionnel;
    @ManyToOne(optional = false)
    private Ville ville;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(mappedBy = "promotions")
    private List<Etudiant> etudiants;

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public TitreProfessionnel getTitreProfessionnel() {
        return titreProfessionnel;
    }

    public void setTitreProfessionnel(TitreProfessionnel titreProfessionnel) {
        this.titreProfessionnel = titreProfessionnel;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}
