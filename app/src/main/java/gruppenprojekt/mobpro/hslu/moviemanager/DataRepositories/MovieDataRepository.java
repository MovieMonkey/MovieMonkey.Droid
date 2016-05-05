package applysolutions.com.moneyfoxdroid.DataRepositories;

import android.app.Activity;
import android.app.Application;

import java.util.List;

import javax.inject.Inject;

import applysolutions.com.moneyfoxdroid.DatabaseModels.Account;
import applysolutions.com.moneyfoxdroid.Interfaces.DataAccess;
import applysolutions.com.moneyfoxdroid.Interfaces.DataRepository;
import applysolutions.com.moneyfoxdroid.MyApp;
import applysolutions.com.moneyfoxdroid.R;

public class MovieDataRepository implements DataRepository<Movie> {

    public DataAccess<Movie> movieDataAccess;

    public Application application;

    public MovieDataRepository(MovieDataAccess movieDataAccess){
        accountDataAccess = movieDataAccess;

        loadCache();
    }

    private List<Movie> movieCache;

    @Override
    public List<Movie> getCache() {
        return movieCache;
    }

    /**
     * Searches through all cached accounts for the one with the same ID.
     * If none is found it will create a new one and return this.
     * @param id id to search for in the cache.
     * @return The Account with the same id or a new created.
     */
    @Override
    public Movie getFromCache(int id) {
        for(Movie movie : movieCache) {
            if(movie.getId() == id) {
                return movie;
            }
        }

        return new Movie();
    }

    @Override
    public void loadCache() {
        movieCache = movieDataAccess.loadList();
    }

    @Override
    public void save(Movie itemToSave) {

        if(itemToSave.getName().isEmpty()){
            itemToSave.setName(application.getResources().getString(R.string.text_placeholder));
        }

        movieDataAccess.save(itemToSave);
        movieCache.add(itemToSave);
    }

    @Override
    public void delete(Movie itemToDelete) {
        movieDataAccess.delete(itemToDelete);
        movieCache.remove(itemToDelete);
    }
}