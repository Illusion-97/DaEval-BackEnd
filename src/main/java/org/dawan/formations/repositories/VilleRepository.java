package org.dawan.formations.repositories;

import org.dawan.formations.models.TitreProfessionnel;
import org.dawan.formations.models.Ville;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
    List<Ville> findAllByNameContaining(String name);

    Ville getBySlug(String slug);

    @Query("FROM Ville v WHERE " +
            "(:name IS NULL OR v.name like %:name%) AND" +
            "(:slug IS NULL OR v.slug like %:slug%)")
    List<Ville> getFiltered(String name, String slug, Pageable pageable);

    @Query("SELECT COUNT(v.id) FROM Ville v WHERE " +
            "(:name IS NULL OR v.name like %:name%) AND" +
            "(:slug IS NULL OR v.slug like %:slug%)")
    Long getCount(String name, String slug);
}
