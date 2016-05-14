package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.content.Context;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.Adapters.MovieAdapter;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.AsyncDelegate;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.MovieGrabberService;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

public class TheMovieDBService implements MovieGrabberService, AsyncDelegate {

    private final String API_KEY = "c1c4bc25d948773ed2019e99a4a82e6d";
    private final String POSTER_THUMBNAIL_PATH = "https://image.tmdb.org/t/p/w185";
    private final String POSTER_ORIGINAL_PATH = "https://image.tmdb.org/t/p/original";
    private Context context;

    public TheMovieDBService(Context newContext){
        this.context = newContext;
    }

    @Override
    public void getSuggestions(String keyword){
    }

    @Override
    public void searchMovie(String keyword) {
        if(keyword.length() > 0)
        {
            keyword = keyword.replace(" ","+");
            keyword = keyword.replace("&","%26");

            URL genreUrl = setURL("https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY);
            URL searchUrl = setURL("https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language_id=en&query=" + keyword);
            TheMovieDBAsyncLoader loader = new TheMovieDBAsyncLoader(this, genreUrl,searchUrl);
            loader.execute();
        }
    }

    @Override
    public void asyncComplete(List<Movie> movieList){
      Log.i("MovieManager", "AsyncComplete");

        //MovieAdapter adapter = new MovieAdapter(this.context, R.layout.fragment_list_view,movieList);
        //setListAdapter(adapter);
    }

    private URL setURL(String newString){
        try{
            return new URL(newString);
        } catch(MalformedURLException ex){
            return null;
        }
    }
}
