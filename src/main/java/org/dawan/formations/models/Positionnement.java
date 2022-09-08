package org.dawan.formations.models;

import javax.persistence.*;

@Entity
public class Positionnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    @ManyToOne(optional = false)
    private Competence competence;
    @ManyToOne(optional = false)
    private Etudiant etudiant;
    @ManyToOne(optional = false)
    private Promotion promotion;
    private Niveau avant;
    private Niveau apres;

    public Positionnement() {
    }

    public Positionnement(Competence competence, Etudiant etudiant, Promotion promotion, Niveau avant, Niveau apres) {
        this.id = 0L;
        this.version = 0;
        this.competence = competence;
        this.etudiant = etudiant;
        this.promotion = promotion;
        this.avant = avant;
        this.apres = apres;
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

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Niveau getAvant() {
        return avant;
    }

    public void setAvant(Niveau avant) {
        this.avant = avant;
    }

    public Niveau getApres() {
        return apres;
    }

    public void setApres(Niveau apres) {
        this.apres = apres;
    }
}
