package org.dawan.formations.repositories;

import org.dawan.formations.models.Epreuve;
import org.dawan.formations.models.Evaluation;
import org.dawan.formations.models.TYPE_EPREUVE;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("FROM Evaluation e " +
            "JOIN e.etudiant etu " +
            "JOIN e.promotion promo " +
            "JOIN e.epreuve epr " +
            "JOIN e.epreuve.blocCompetences bloc " +
            "WHERE (:etudiantId IS NULL OR etu.id= :etudiantId) " +
            "AND (:promotionId IS NULL OR promo.id= :promotionId) " +
            "AND (:blocId IS NULL OR bloc.id = :blocId)")
    List<Evaluation> findByEpreuve_BlocCompetences_IdAndEtudiant_IdAndPromotion_Id(@Param("etudiantId") Long etudiantId,
                                                                                   @Param("promotionId") Long promotionId,
                                                                                   @Param("blocId") Long blocId);

    @Query("SELECT COUNT(e.id) FROM Evaluation e " +
            "JOIN e.etudiant etu " +
            "JOIN e.promotion promo " +
            "JOIN e.epreuve epr " +
            "JOIN e.epreuve.blocCompetences bloc " +
            "WHERE (:etudiantId IS NULL OR etu.id= :etudiantId) " +
            "AND (:promotionId IS NULL OR promo.id= :promotionId) " +
            "AND (:blocId IS NULL OR bloc.id = :blocId)")
    List<Evaluation> countByEpreuve_BlocCompetences_IdAndEtudiant_IdAndPromotion_Id(@Param("etudiantId") Long etudiantId,
                                                                                   @Param("promotionId") Long promotionId,
                                                                                   @Param("blocId") Long blocId);

    @Query("SELECT AVG(e.note) FROM Evaluation e " +
            "JOIN e.etudiant etu " +
            "JOIN e.promotion promo " +
            "JOIN e.epreuve epr " +
            "JOIN e.epreuve.blocCompetences bloc " +
            "WHERE (:etudiantId IS NULL OR etu.id= :etudiantId) " +
            "AND (:promotionId IS NULL OR promo.id= :promotionId) " +
            "AND (:blocId IS NULL OR bloc.id = :blocId)")
    double getAvgByEtudiantIdAndPromotionIdAndBlocCompetenceId(@Param("etudiantId") Long etudiantId,
                                                               @Param("promotionId") Long promotionId,
                                                               @Param("blocId") Long blocId);

    @Query("SELECT COUNT(e.id) FROM Evaluation e " +
            "JOIN e.etudiant etu " +
            "JOIN e.promotion promo " +
            "JOIN e.epreuve epr " +
            "JOIN epr.blocCompetences bloc WHERE " +
            "(:epreuveId = 0L OR epr.id = :epreuveId) AND" +
            "(:blocCompetencesId = 0L OR bloc.id = :blocCompetencesId) AND" +
            "(:etudiantId = 0L OR etu.id = :etudiantId) AND" +
            "(:promotionId = 0L OR promo.id = :promotionId)")
    Long getCount(long epreuveId, long etudiantId, long promotionId, long blocCompetencesId);

    @Query("FROM Evaluation e " +
            "JOIN e.etudiant etu " +
            "JOIN e.promotion promo " +
            "JOIN e.epreuve epr " +
            "JOIN epr.blocCompetences bloc WHERE " +
            "(:epreuveId = 0L OR epr.id = :epreuveId) AND" +
            "(:blocCompetencesId = 0L OR bloc.id = :blocCompetencesId) AND" +
            "(:etudiantId = 0L OR etu.id = :etudiantId) AND" +
            "(:promotionId = 0L OR promo.id = :promotionId)")
    List<Evaluation> getFiltered(long epreuveId, long etudiantId, long promotionId, long blocCompetencesId, Pageable of);
}
