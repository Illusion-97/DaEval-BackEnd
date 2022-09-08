package org.dawan.formations.services.impl;

import org.dawan.formations.services.GenericService;
import org.dawan.formations.tools.DtoTools;
import org.dawan.formations.tools.ReflecTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericServiceImpl<E, T, R> implements GenericService<E, T, R> {
    protected final JpaRepository<E, Long> repo;
    private final Class<E> entityType;
    private final Class<T> dtoType;

    @Autowired
    protected GenericServiceImpl(JpaRepository<E, Long> repo, Class<E> entityType, Class<T> dtoType) {
        this.repo = repo;
        this.dtoType = dtoType;
        this.entityType = entityType;
    }

    @Override
    public List<T> GETAll() {
        return repo.findAll().stream()
                .map(r -> DtoTools.convert(r, dtoType))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> GETAllByPage(int page, int max) {
        return repo.findAll(PageRequest.of(page, max)).stream()
                .map(r -> DtoTools.convert(r, dtoType))
                .collect(Collectors.toList());
    }

    @Override
    public T GETById(Long id) {
        try {
            return DtoTools.convert(repo.findById(id).orElse(entityType.newInstance()), dtoType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T POSTSave(T obj) {
        return DtoTools.convert(repo.saveAndFlush(DtoTools.convert(obj, entityType)), dtoType);
    }

    @Override
    public T PUTSave(T obj) {
        return POSTSave(obj);
    }

    @Override
    public void DELETEById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Long GETCount() {
        return repo.count();
    }

    @Override
    public String getDtoName() {
        return dtoType.getSimpleName();
    }

    @Override
    public String getDtoStructure() {
        return ReflecTool.getJsonStructure(dtoType);
    }

    @Override
    public R getRepo() {
        return (R)repo;
    }

    @Override
    public List<T> getListDto(List<E> list) {
        return list.stream().map(e -> DtoTools.convert(e,dtoType)).collect(Collectors.toList());
    }

    @Override
    public T getDto(E entity) {
        if (entity == null) return null;
        return DtoTools.convert(entity,dtoType);
    }
}
