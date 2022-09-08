package org.dawan.formations.repositories;

import org.dawan.formations.models.Epreuve;
import org.dawan.formations.models.TYPE_EPREUVE;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
    List<Epreuve> findByBlocCompetences_Id(Long id);

    long countByBlocCompetences_Id(Long id);


    @Query("SELECT COUNT(e.id) FROM Epreuve e JOIN e.blocCompetences b WHERE " +
            "(:titre IS NULL OR e.titre like %:titre%) AND" +
            "(:description IS NULL OR e.description like %:description%) AND" +
            "(:type IS NULL OR e.type = :type) AND" +
            "(:blocCompetencesId = 0L OR b.id = :blocCompetencesId)")
    Long getCount(String titre, String description, long blocCompetencesId, TYPE_EPREUVE type);

    @Query("FROM Epreuve e JOIN e.blocCompetences b WHERE " +
            "(:titre IS NULL OR e.titre like %:titre%) AND" +
            "(:description IS NULL OR e.description like %:description%) AND" +
            "(:type IS NULL OR e.type = :type) AND" +
            "(:blocCompetencesId = 0L OR b.id = :blocCompetencesId)")
    List<Epreuve> getFiltered(String titre, String description, long blocCompetencesId, TYPE_EPREUVE type, Pageable of);
}
