package org.dawan.formations.dtos;

import java.io.Serializable;
import java.util.List;

public class EtudiantFilterDto implements Serializable {
    private long id;
    private int version;
    private long utilisateurId;
    private List<Long> promotionsId;
    private String utilisateurNom;
    private String utilisateurPrenom;
    private long promotionId;
    private long villeId;

    public EtudiantFilterDto(long id) {
        this.utilisateurId = id;
    }

    public EtudiantFilterDto() {
    }


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

    public long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public List<Long> getPromotionsId() {
        return promotionsId;
    }

    public void setPromotionsId(List<Long> promotionsId) {
        this.promotionsId = promotionsId;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public void setUtilisateurPrenom(String utilisateurPrenom) {
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public long getVilleId() {
        return villeId;
    }

    public void setVilleId(long villeId) {
        this.villeId = villeId;
    }
}

