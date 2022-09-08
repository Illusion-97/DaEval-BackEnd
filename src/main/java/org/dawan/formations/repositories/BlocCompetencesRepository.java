package org.dawan.formations.repositories;

import org.dawan.formations.models.BlocCompetences;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlocCompetencesRepository extends JpaRepository<BlocCompetences, Long> {
    List<BlocCompetences> findByTitreProfessionnel_Id(Long id);

    long countByTitreProfessionnelId(Long id);

    @Query("FROM BlocCompetences b JOIN b.titreProfessionnel t WHERE " +
            "(:titre IS NULL OR b.titre like %:titre%) AND " +
            "(:description IS NULL OR b.description like %:description%) AND " +
            "(:titreProfessionnelId = 0L OR t.id = :titreProfessionnelId)")
    List<BlocCompetences> getFiltered(String titre, String description, long titreProfessionnelId, Pageable pageable);



    @Query("SELECT COUNT(b.id) FROM BlocCompetences b JOIN b.titreProfessionnel t WHERE " +
            "(:titre IS NULL OR b.titre like %:titre%) AND" +
            "(:description IS NULL OR b.description like %:description%) AND" +
            "(:titreProfessionnelId = 0L OR t.id = :titreProfessionnelId)")
    Long getCount(String titre, String description, long titreProfessionnelId);
}
