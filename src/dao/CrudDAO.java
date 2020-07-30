package dao;

import entity.SupperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO <T extends SupperEntity,ID extends Serializable> extends SuperDAO {
    public List<T> findAll();
    public T find(ID pk);
    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(ID pk);
}
