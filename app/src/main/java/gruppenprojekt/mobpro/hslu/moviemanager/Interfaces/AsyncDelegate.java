package gruppenprojekt.mobpro.hslu.moviemanager.Interfaces;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;

/**
 * Created by Adrian Kauz on 14.05.2016.
 */
public interface AsyncDelegate {
    void asyncComplete(List<Movie> movieList);
}
