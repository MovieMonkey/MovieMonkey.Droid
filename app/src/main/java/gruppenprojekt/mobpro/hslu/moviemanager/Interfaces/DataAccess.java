package gruppenprojekt.mobpro.hslu.moviemanager.Interfaces;

import java.util.List;

public interface DataAccess<T> {
    void save(T itemToSave);

    void delete(T itemToDelete);

    List<T> loadList();

    List<T> loadList(boolean filterFavorites);

    T getById(int id);
}