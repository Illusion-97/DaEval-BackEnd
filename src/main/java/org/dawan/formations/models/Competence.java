package org.dawan.formations.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Competence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;
    private String titre;
    private String description;
    @ManyToOne(optional = false)
    private BlocCompetences blocCompetences;

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlocCompetences getBlocCompetences() {
        return blocCompetences;
    }

    public void setBlocCompetences(BlocCompetences blocCompetences) {
        this.blocCompetences = blocCompetences;
    }
}
