package org.dawan.formations.dtos;

import org.dawan.formations.models.STATUT_UTILISATEUR;

import java.io.Serializable;

public class UtilisateurDto implements Serializable {
    private long id;
    private int version;
    private String nom;
    private String prenom;
    private STATUT_UTILISATEUR statut;
    private String email;
    private String password;
    private boolean active;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public STATUT_UTILISATEUR getStatut() {
        return statut;
    }

    public void setStatut(STATUT_UTILISATEUR statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
