package org.dawan.formations.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDto implements Serializable {
    private long id;
    private int version;
    private long utilisateurId;
    private List<Long> promotionsId;
    private String utilisateurNom;
    private String utilisateurPrenom;


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
}

