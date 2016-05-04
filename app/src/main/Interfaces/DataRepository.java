package gruppenprojekt.mobpro.hslu.moviemanager;

import java.util.List;

/**
 * Provides an interface for a default data repository. These objects can
 * be used to perform common operations again the database.
 * @param <T> Placeholder for the concrete type the repository is for.
 */
public interface DataRepository<T> {

    /**
     * Gives access to the cache with all the loaded objects.
     * This collection mirrors the content of the database.
     * @return Returns all cached objects. This is identical to the values in the DB.
     */
    List<T> getCache();

    /**
     * Searches for an concrete id in the cache and will return this object if found.
     * Otherwise a new object is created and return
     * @param id id to search for in the cache.
     * @return the item found or a new created.
     */
    T getFromCache(int id);

    void loadCache();

    void save(T itemToSave);

    void delete(T itemToDelete);
}
