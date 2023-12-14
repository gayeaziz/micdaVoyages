package repository;

public interface CrudRepository<T, ID> {
    T save(T entity);

    T getById(ID id);

    void update(T entity);

    void delete(ID id);


}