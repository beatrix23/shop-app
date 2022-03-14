package ro.ubb.catalog.core.service;

import java.util.List;

public interface GenericService<T> {

    List<T> getAllEntities();

    T findOneEntity(Integer id);

    T addEntity(T entity);

    T updateEntity(Integer id, T entity);

    void deleteById(Integer id);


}
