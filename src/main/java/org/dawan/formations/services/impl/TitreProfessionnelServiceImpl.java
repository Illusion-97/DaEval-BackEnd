package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.TitreProfessionnelDto;
import org.dawan.formations.models.TitreProfessionnel;
import org.dawan.formations.repositories.TitreProfessionnelRepository;
import org.dawan.formations.services.TitreProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TitreProfessionnelServiceImpl extends GenericServiceImpl<TitreProfessionnel, TitreProfessionnelDto, TitreProfessionnelRepository> implements TitreProfessionnelService {

    @Autowired
    public TitreProfessionnelServiceImpl(TitreProfessionnelRepository repo) {
        super(repo, TitreProfessionnel.class, TitreProfessionnelDto.class);
    }

    @Override
    public TitreProfessionnelDto getByTitre(String titre) {
        return getDto(getRepo().getBySlug(titre));
    }

    public List<TitreProfessionnelDto> POSTFilteredByPage(TitreProfessionnelDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getTitre(), obj.getSlug(), obj.getDescription(), obj.getObjectives(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(TitreProfessionnelDto obj) {
        return getRepo().getCount(obj.getTitre(), obj.getSlug(), obj.getDescription(), obj.getObjectives());
    }
}
