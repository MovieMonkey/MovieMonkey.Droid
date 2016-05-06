package gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses;


import android.content.Context;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.DataAccess;

public class MovieDataAccess extends BasicDataAccess implements DataAccess<Movie> {

    public MovieDataAccess(Context context) {
        super(context);
    }

    @Override
    public void save(Movie itemToSave) {

    }

    @Override
    public void delete(Movie itemToDelete) {

    }

    @Override
    public List<Movie> loadList() {
        return null;
    }
}
