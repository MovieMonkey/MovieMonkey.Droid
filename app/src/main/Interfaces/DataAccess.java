package gruppenprojekt.mobpro.hslu.moviemanager.DataAccess;

        import java.util.List;

public interface DataAccess {
    void save(T itemToSave);

    void delete(T itemToDelete);

    List<T> loadList();
}