package org.dawan.formations.dtos;

import org.dawan.formations.models.STATUT_UTILISATEUR;

public class SimpleUtilisateurDto {
    long id;
    private String nom;
    private String prenom;
    private STATUT_UTILISATEUR statut;
    private String email;

    public SimpleUtilisateurDto() {
    }

    public SimpleUtilisateurDto(long id, String nom, String prenom, STATUT_UTILISATEUR statut, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.statut = statut;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
