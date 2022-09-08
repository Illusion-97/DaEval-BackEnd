package org.dawan.formations.dtos;

import java.io.Serializable;

public class SimpleEtudiantDto implements Serializable {
    private long id;
    private SimpleUtilisateurDto utilisateur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SimpleUtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(SimpleUtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }

}

