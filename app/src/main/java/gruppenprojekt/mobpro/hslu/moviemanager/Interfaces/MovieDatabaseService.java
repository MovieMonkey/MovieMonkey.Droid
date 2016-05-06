package gruppenprojekt.mobpro.hslu.moviemanager.Interfaces;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;

public interface MovieDatabaseService {
    List<Movie> getSuggestions(String keyword);
}