package org.dawan.formations.repositories;

import org.dawan.formations.models.Competence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findByBlocCompetences_Id(Long id);

    long countByBlocCompetences_Id(Long id);

    @Query("FROM Competence c JOIN c.blocCompetences b WHERE " +
            "(:titre IS NULL OR c.titre like %:titre%) AND" +
            "(:description IS NULL OR c.description like %:description%) AND" +
            "(:blocCompetencesId = 0L OR b.id = :blocCompetencesId)")
    List<Competence> getFiltered(String titre, String description, long blocCompetencesId, Pageable pageable);

    @Query("SELECT COUNT(c.id) FROM Competence c JOIN c.blocCompetences b WHERE " +
            "(:titre IS NULL OR c.titre like %:titre%) AND" +
            "(:description IS NULL OR c.description like %:description%) AND" +
            "(:blocCompetencesId = 0L OR b.id = :blocCompetencesId)")
    Long getCount(String titre, String description, long blocCompetencesId);

    List<Competence> findByBlocCompetences_TitreProfessionnel_Id(Long id);


}
