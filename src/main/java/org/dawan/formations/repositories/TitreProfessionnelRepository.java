package org.dawan.formations.repositories;

import org.dawan.formations.models.BlocCompetences;
import org.dawan.formations.models.TitreProfessionnel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitreProfessionnelRepository extends JpaRepository<TitreProfessionnel, Long> {
    List<TitreProfessionnel> findAllByTitreContaining(String titre);

    TitreProfessionnel getBySlug(String slug);

    @Query("FROM TitreProfessionnel t WHERE " +
            "(:titre IS NULL OR t.titre like %:titre%) AND" +
            "(:slug IS NULL OR t.slug like %:slug%) AND" +
            "(:objectives IS NULL OR t.objectives like %:objectives%) AND" +
            "(:description IS NULL OR t.description like %:description%)")
    List<TitreProfessionnel> getFiltered(String titre, String slug, String description, String objectives, Pageable pageable);

    @Query("SELECT COUNT(t.id) FROM TitreProfessionnel t WHERE " +
            "(:titre IS NULL OR t.titre like %:titre%) AND" +
            "(:slug IS NULL OR t.slug like %:slug%) AND" +
            "(:objectives IS NULL OR t.objectives like %:objectives%) AND" +
            "(:description IS NULL OR t.description like %:description%)")
    Long getCount(String titre, String slug, String description, String objectives);
}
