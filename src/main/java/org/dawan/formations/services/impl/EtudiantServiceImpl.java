package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.EtudiantDto;
import org.dawan.formations.dtos.EtudiantFilterDto;
import org.dawan.formations.dtos.InscriptionDto;
import org.dawan.formations.dtos.SimpleUtilisateurDto;
import org.dawan.formations.models.Etudiant;
import org.dawan.formations.models.Promotion;
import org.dawan.formations.models.STATUT_UTILISATEUR;
import org.dawan.formations.models.Utilisateur;
import org.dawan.formations.repositories.EtudiantRepository;
import org.dawan.formations.repositories.PromotionRepository;
import org.dawan.formations.repositories.UtilisateurRepository;
import org.dawan.formations.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class EtudiantServiceImpl extends GenericServiceImpl<Etudiant, EtudiantDto, EtudiantRepository> implements EtudiantService {

    private final PromotionRepository promotionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public EtudiantServiceImpl(EtudiantRepository repo, PromotionRepository promotionRepository, UtilisateurRepository utilisateurRepository) {
        super(repo, Etudiant.class, EtudiantDto.class);
        this.promotionRepository = promotionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<EtudiantDto> GETsearchByNameOrEmail(String search, int page, int max) {
        return getListDto(getRepo().findByUtilisateur_NomContainingIgnoreCaseOrUtilisateur_PrenomContainingIgnoreCaseOrUtilisateur_EmailContainingIgnoreCase(search,search,search, PageRequest.of(page,max)));
    }

    @Override
    public Etudiant inscription(long id, Long promotion) {
            Etudiant base = getRepo().findByUtilisateur_Id(id);
            if (base == null) {
                Optional<Utilisateur> user = utilisateurRepository.findById(id);
                if (user.isPresent()){
                    base = new Etudiant();
                    base.setUtilisateur(user.get());
                    base.setPromotions(new ArrayList<>());
                }
            }
            if (base != null && base.getPromotions().stream().map(Promotion::getId).noneMatch(Predicate.isEqual(promotion))){
                Optional<Promotion> promo = promotionRepository.findById(promotion);
                if(promo.isPresent()){
                    base.getPromotions().add(promo.get());
                    base.getUtilisateur().setStatut(STATUT_UTILISATEUR.ETUDIANT);
                }
            }
        return base;
    }

    public long POSTinscription(InscriptionDto obj){
        getRepo().saveAllAndFlush(Arrays.stream(obj.getUtilisateursId())
                .mapToObj(id -> inscription(id, obj.getPromotionId())).filter(Objects::nonNull)
                .collect(Collectors.toList()));
        promotionRepository.findById(obj.getPromotionId()).ifPresent( promo -> {
            getRepo().saveAllAndFlush( promo.getEtudiants().stream()
                    .filter(etudiant -> Arrays.stream(obj.getUtilisateursId())
                            .noneMatch(id -> etudiant.getUtilisateur().getId() == id))
                    .peek(exclus -> exclus.getPromotions().remove(promo)).collect(Collectors.toList()));
        });
        return obj.getPromotionId();
    }

    @Override
    public List<EtudiantDto> GETfindByPromotionId(long promoId) {
        return getListDto(getRepo().findByPromotionsId(promoId));
    }

    @Override
    public List<EtudiantDto> GETfindByVilleId(long villeId) {
        return getListDto(getRepo().findByPromotionsVilleId(villeId));
    }

    public List<EtudiantDto> POSTFilteredByPage(EtudiantFilterDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getUtilisateurNom(), obj.getUtilisateurPrenom(),
                obj.getPromotionId(), obj.getVilleId(), PageRequest.of(page,max)));
    }

    public List<SimpleUtilisateurDto> POSTFilteredSimpleUser(EtudiantFilterDto obj) {
        return getRepo().getFiltered(obj.getUtilisateurNom(), obj.getUtilisateurPrenom(),
                obj.getPromotionId(), obj.getVilleId(), Pageable.unpaged()).stream()
                .map(etudiant -> {
                    Utilisateur utilisateur = etudiant.getUtilisateur();
                    return new SimpleUtilisateurDto(
                            utilisateur.getId(),
                            utilisateur.getNom(),
                            utilisateur.getPrenom(),
                            utilisateur.getStatut(),
                            utilisateur.getEmail());
                }).collect(Collectors.toList());
    }

    public Long POSTFilteredCount(EtudiantFilterDto obj) {
        return getRepo().getCount(obj.getUtilisateurNom(), obj.getUtilisateurPrenom(), obj.getPromotionId(), obj.getVilleId());
    }
}
