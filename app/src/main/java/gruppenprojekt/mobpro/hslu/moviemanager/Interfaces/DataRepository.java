package gruppenprojekt.mobpro.hslu.moviemanager.Interfaces;

import java.util.List;

/**
 * Provides an interface for a default data repository. These objects can
 * be used to perform common operations again the database.
 * @param <T> Placeholder for the concrete type the repository is for.
 */
public interface DataRepository<T> {

    List<T> load();

    List<T> load(boolean filterFavorites);

    void save(T itemToSave);

    void delete(T itemToDelete);

    T getById(int id);
}
