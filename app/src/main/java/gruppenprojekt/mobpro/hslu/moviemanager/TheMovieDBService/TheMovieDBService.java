package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.MovieGrabberService;

public class TheMovieDBService implements MovieGrabberService {

    private final String API_KEY = "c1c4bc25d948773ed2019e99a4a82e6d";
    private final String POSTER_THUMBNAIL_PATH = "https://image.tmdb.org/t/p/w185";
    private final String POSTER_ORIGINAL_PATH = "https://image.tmdb.org/t/p/original";

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
            TheMovieDBAsyncLoader loader = new TheMovieDBAsyncLoader(genreUrl,searchUrl);
            loader.execute();
        }
    }

    private URL setURL(String newString){
        try{
            return new URL(newString);
        } catch(MalformedURLException ex){
            return null;
        }
    }
}
