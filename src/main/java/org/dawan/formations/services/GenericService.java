package org.dawan.formations.services;

import java.util.List;

public interface GenericService<E,T,R> {
    List<T> GETAll();

    List<T> GETAllByPage(int page, int max);

    /*List<T> POSTFilteredByPage(T obj, int page, int max);
    Long POSTFilteredCount(T obj);*/

    T GETById(Long id);

    T POSTSave(T obj);
    T PUTSave(T obj);


    void DELETEById(Long id);

    String getDtoName();

    String getDtoStructure();

    Long GETCount();

    R getRepo();

    List<T> getListDto(List<E> list);

    T getDto(E entity);
}
