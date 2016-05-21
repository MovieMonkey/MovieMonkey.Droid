package gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories;

import android.app.Application;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.DataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.DataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

public class MovieDataRepository implements DataRepository<Movie> {

    public DataAccess<Movie> movieDataAccess;

    public Application application;

    public MovieDataRepository(MovieDataAccess movieDataAccess){
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public List<Movie> load() {
        return movieDataAccess.loadList();
    }

    @Override
    public void save(Movie itemToSave) {

        if(itemToSave.getOriginalTitle().isEmpty()){
            itemToSave.setOriginalTitle(application.getResources().getString(R.string.text_placeholder));
        }

        movieDataAccess.save(itemToSave);
    }

    @Override
    public void delete(Movie itemToDelete) {
        movieDataAccess.delete(itemToDelete);
    }
}