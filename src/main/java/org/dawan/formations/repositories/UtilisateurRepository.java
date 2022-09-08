package org.dawan.formations.repositories;

import org.dawan.formations.models.STATUT_UTILISATEUR;
import org.dawan.formations.models.Utilisateur;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);

    List<Utilisateur> findAllByNomContainingOrPrenomContainingOrEmailContaining(String nom, String prenom, String email);

    @Query("FROM Utilisateur u WHERE " +
            "(:nom IS NULL OR u.nom like %:nom%) AND" +
            "(:prenom IS NULL OR u.prenom like %:prenom%) AND" +
            "(:email IS NULL OR u.email like %:email%) AND" +
            "(:statut IS NULL OR u.statut = :statut)")
    List<Utilisateur> getFiltered(String nom, String prenom, String email, STATUT_UTILISATEUR statut, Pageable of);

    @Query("SELECT COUNT(u.id) FROM Utilisateur u WHERE " +
            "(:nom IS NULL OR u.nom like %:nom%) AND" +
            "(:prenom IS NULL OR u.prenom like %:prenom%) AND" +
            "(:email IS NULL OR u.email like %:email%) AND" +
            "(:statut IS NULL OR u.statut = :statut)")
    Long getCount(String nom, String prenom, String email, STATUT_UTILISATEUR statut);
}
