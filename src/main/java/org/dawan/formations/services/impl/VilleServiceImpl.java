package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.VilleDto;
import org.dawan.formations.models.Ville;
import org.dawan.formations.repositories.VilleRepository;
import org.dawan.formations.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VilleServiceImpl extends GenericServiceImpl<Ville, VilleDto, VilleRepository> implements VilleService {

    @Autowired
    public VilleServiceImpl(VilleRepository repo) {
        super(repo, Ville.class, VilleDto.class);
    }

    @Override
    public VilleDto GETBySlug(String slug) {
        return getDto(getRepo().getBySlug(slug));
    }

    public List<VilleDto> POSTFilteredByPage(VilleDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getName(), obj.getSlug(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(VilleDto obj) {
        return getRepo().getCount(obj.getName(), obj.getSlug());
    }
}
