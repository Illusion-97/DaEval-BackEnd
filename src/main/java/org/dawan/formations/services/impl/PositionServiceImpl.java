package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.EnumDto;
import org.dawan.formations.dtos.PositionDto;
import org.dawan.formations.models.Niveau;
import org.dawan.formations.models.Positionnement;
import org.dawan.formations.repositories.CompetenceRepository;
import org.dawan.formations.repositories.EtudiantRepository;
import org.dawan.formations.repositories.PositionRepository;
import org.dawan.formations.repositories.PromotionRepository;
import org.dawan.formations.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionServiceImpl extends GenericServiceImpl<Positionnement, PositionDto, PositionRepository> implements PositionService {

    private final PromotionRepository promotionRepository;
    private final CompetenceRepository competenceRepository;
    private final EtudiantRepository etudiantRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository repo, PromotionRepository promotionRepository, CompetenceRepository competenceRepository, EtudiantRepository etudiantRepository) {
        super(repo, Positionnement.class, PositionDto.class);
        this.promotionRepository = promotionRepository;
        this.competenceRepository = competenceRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public List<PositionDto> POSTFilteredByPage(PositionDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getBlocCompetencesId(), obj.getCompetenceId(), obj.getEtudiantId(), obj.getPromotionId(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(PositionDto obj) {
        if(obj.getEtudiantId() != 0 && obj.getPromotionId() != 0 &&
                getRepo().getCount(0, 0, obj.getEtudiantId(), obj.getPromotionId()) == 0 ){
                generate(obj.getEtudiantId(), obj.getPromotionId());
            }
        return getRepo().getCount(obj.getBlocCompetencesId(), obj.getCompetenceId(), obj.getEtudiantId(), obj.getPromotionId());
    }

    private void generate(long etudiantId, long promotionId) {
        promotionRepository.findById(promotionId).ifPresent(promotion1 ->
                etudiantRepository.findById(etudiantId).ifPresent(value -> getRepo().saveAllAndFlush(
                competenceRepository.findByBlocCompetences_TitreProfessionnel_Id(promotion1.getTitreProfessionnel().getId()).stream()
                        .map(competence -> new Positionnement(competence, value, promotion1, Niveau.ABSENT, Niveau.ABSENT)
                        ).collect(Collectors.toList()))));
    }

    public List<EnumDto> GETNiveaux() {
        return Arrays.stream(Niveau.values()).map(niveau -> new EnumDto(niveau, niveau.name())).collect(Collectors.toList());
    }

    public List<PositionDto> getForGrille(long uId, long pId){
        return getListDto(getRepo().getFiltered(0, 0, uId, pId, Pageable.unpaged()));
    }
}
