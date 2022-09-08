package org.dawan.formations.repositories;

import org.dawan.formations.models.Positionnement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Positionnement, Long> {

    @Query("SELECT COUNT(p.id) FROM Positionnement p " +
            "JOIN p.etudiant etu " +
            "JOIN p.promotion promo " +
            "JOIN p.competence comp " +
            "JOIN comp.blocCompetences bloc  WHERE " +
            "(:blocCompetencesId = 0L OR bloc.id = :blocCompetencesId) AND" +
            "(:competenceId = 0L OR comp.id = :competenceId) AND" +
            "(:etudiantId = 0L OR etu.id = :etudiantId) AND" +
            "(:promotionId = 0L OR promo.id = :promotionId)")
    Long getCount(long blocCompetencesId, long competenceId, long etudiantId, long promotionId);

    @Query("FROM Positionnement p " +
            "JOIN p.etudiant etu " +
            "JOIN p.promotion promo " +
            "JOIN p.competence comp " +
            "JOIN comp.blocCompetences bloc  WHERE " +
            "(:blocCompetencesId = 0L OR bloc.id = :blocCompetencesId) AND" +
            "(:competenceId = 0L OR comp.id = :competenceId) AND" +
            "(:etudiantId = 0L OR etu.id = :etudiantId) AND" +
            "(:promotionId = 0L OR promo.id = :promotionId)")
    List<Positionnement> getFiltered(long blocCompetencesId, long competenceId, long etudiantId, long promotionId, Pageable of);
}
