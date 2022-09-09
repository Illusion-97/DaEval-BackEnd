package org.dawan.formations.repositories;

import org.dawan.formations.models.Etudiant;
import org.dawan.formations.models.STATUT_UTILISATEUR;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByUtilisateur_NomContainingIgnoreCaseOrUtilisateur_PrenomContainingIgnoreCaseOrUtilisateur_EmailContainingIgnoreCase(String nom, String prenom, String email, Pageable pageable);
    List<Etudiant> findByPromotionsId(long id);
    List<Etudiant> findByPromotionsVilleId(long id);
    Etudiant findByUtilisateur_Id(Long id);



    @Query("SELECT COUNT(E.id) FROM Etudiant E JOIN E.promotions P JOIN P.ville V " +
            "WHERE (:id IS NULL OR P.id = :id) AND (:id1 IS NULL OR V.id = :id1)")
    long countByPromotionsAndVille(@Nullable Long id, @Nullable Long id1);

    @Query("UPDATE Utilisateur u SET u.statut = :statut WHERE u.id = :id AND u.statut <> :statut")
    void setStatut(Long id, STATUT_UTILISATEUR statut);


    @Query("SELECT DISTINCT e FROM Etudiant e " +
            "JOIN e.utilisateur u " +
            "JOIN e.promotions p " +
            "JOIN p.ville v WHERE " +
            "(:nom IS NULL OR u.nom like %:nom%) AND" +
            "(:prenom IS NULL OR u.prenom like %:prenom%) AND" +
            "(:promotionId = 0L OR p.id = :promotionId) AND" +
            "(:villeId = 0L OR v.id = :villeId)")
    List<Etudiant> getFiltered(String nom, String prenom, long promotionId, long villeId, Pageable of);

    @Query("SELECT COUNT(e.id) FROM Etudiant e " +
            "JOIN e.utilisateur u " +
            "JOIN e.promotions p " +
            "JOIN p.ville v WHERE " +
            "(:nom IS NULL OR u.nom like %:nom%) AND" +
            "(:prenom IS NULL OR u.prenom like %:prenom%) AND" +
            "(:promotionId = 0L OR p.id = :promotionId) AND" +
            "(:villeId = 0L OR v.id = :villeId)")
    Long getCount(String nom, String prenom, long promotionId, long villeId);
}
