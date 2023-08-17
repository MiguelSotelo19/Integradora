package mx.edu.utez.photoparty.models.Dao;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
public interface DaoRepository<T> {
    List<T> findAll(Long id);
    List<T> findAll();
    T findOne(Long id, String id2);
    boolean save(T object);
    boolean update(T object, String aux);
    boolean delete(Long id, String id2);
}
