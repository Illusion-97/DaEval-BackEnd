package org.dawan.formations.dtos;

import java.util.Date;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PromotionDto implements Serializable {
    private long id;
    private int version;
    private Date dateDebut;
    private Date dateFin;
    private long titreProfessionnelId;
    private String titreProfessionnelTitre;
    private long villeId;
    private String villeName;
    private List<Long> etudiantsId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getTitreProfessionnelId() {
        return titreProfessionnelId;
    }

    public void setTitreProfessionnelId(long titreProfessionnelId) {
        this.titreProfessionnelId = titreProfessionnelId;
    }

    public long getVilleId() {
        return villeId;
    }

    public void setVilleId(long villeId) {
        this.villeId = villeId;
    }

    public List<Long> getEtudiantsId() {
        return etudiantsId;
    }

    public void setEtudiantsId(List<Long> etudiantsId) {
        this.etudiantsId = etudiantsId;
    }

    public String getVilleName() {
        return villeName;
    }

    public void setVilleName(String villeName) {
        this.villeName = villeName;
    }

    public String getTitreProfessionnelTitre() {
        return titreProfessionnelTitre;
    }

    public void setTitreProfessionnelTitre(String titreProfessionnelTitre) {
        this.titreProfessionnelTitre = titreProfessionnelTitre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionDto that = (PromotionDto) o;
        return dateDebut.equals(that.dateDebut) && dateFin.equals(that.dateFin) && titreProfessionnelId == that.titreProfessionnelId && villeId == that.villeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateDebut, dateFin, titreProfessionnelId, villeId);
    }
}
