package gruppenprojekt.mobpro.hslu.moviemanager;

public interface MovieDatabaseService {
    List<Movies> getSuggestions(String keyword);
}