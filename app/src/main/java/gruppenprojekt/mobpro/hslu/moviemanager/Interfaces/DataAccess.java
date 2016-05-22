package gruppenprojekt.mobpro.hslu.moviemanager.Interfaces;

import java.util.List;

public interface DataAccess<T> {
    void save(T itemToSave);

    void delete(T itemToDelete);

    List<T> loadList();

    T getById(int id);
}