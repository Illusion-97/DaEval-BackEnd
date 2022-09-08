package org.dawan.formations.repositories;

import org.dawan.formations.dtos.PromotionDto;
import org.dawan.formations.models.Promotion;
import java.util.Date;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByVilleNameContainingAndTitreProfessionnelTitreContainingAndDateDebutIsAfter(String name, String titre, Date dateDebut, Pageable pageable);

    @Query("FROM Promotion p JOIN p.titreProfessionnel JOIN p.ville WHERE (:titreId IS NULL OR p.titreProfessionnel.id = :titreId)" +
            "AND (:villeId IS NULL OR p.ville.id = :villeId)")
    List<Promotion> findByTitreProfessionnelIdAndVilleId(@Param("titreId")Long id, @Param("villeId") Long id1);


    @Query("SELECT COUNT(p.id) FROM Promotion p JOIN p.titreProfessionnel JOIN p.ville WHERE (:titreId IS NULL OR p.titreProfessionnel.id = :titreId)" +
            "AND (:villeId IS NULL OR p.ville.id = :villeId)")
    Long countByTitreAndVille(@Nullable Long titreId, @Nullable Long villeId);


    @Query("FROM Promotion p JOIN p.titreProfessionnel t " +
            "JOIN p.ville v WHERE " +
            "(:titreProfessionnelId = 0L OR t.id = :titreProfessionnelId) AND" +
            "(:villeId = 0L OR v.id = :villeId) AND"+
            "(:dateDebut IS NULL OR p.dateDebut >= :dateDebut) AND" +
            "(:dateFin IS NULL OR p.dateFin <= :dateFin)")
    List<Promotion> getFiltered(long titreProfessionnelId, long villeId, Date dateDebut, Date dateFin, Pageable of);

    @Query("SELECT COUNT(p.id) FROM Promotion p JOIN p.titreProfessionnel t " +
            "JOIN p.ville v WHERE " +
            "(:titreProfessionnelId = 0L OR t.id = :titreProfessionnelId) AND" +
            "(:villeId = 0L OR v.id = :villeId) AND"+
            "(:dateDebut IS NULL OR p.dateDebut >= :dateDebut) AND" +
            "(:dateFin IS NULL OR p.dateFin <= :dateFin)")
    Long getCount(long titreProfessionnelId, long villeId, Date dateDebut, Date dateFin);
}
