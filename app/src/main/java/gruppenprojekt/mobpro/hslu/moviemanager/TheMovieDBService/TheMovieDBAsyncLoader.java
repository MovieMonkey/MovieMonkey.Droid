package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.AsyncDelegate;

public class TheMovieDBAsyncLoader extends AsyncTask<Void, Void, List<Movie>>
{
    AsyncDelegate delegate;
    URL genreUrl;
    URL searchUrl;
    List<Movie> movies;

    public TheMovieDBAsyncLoader(AsyncDelegate newDelegate, URL newGenreUrl, URL newSearchUrl){
        this.genreUrl = newGenreUrl;
        this.searchUrl = newSearchUrl;
        this.delegate = newDelegate;
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
        String jsonResult = httpHandler.getTextFromHttpContent(this.searchUrl);

        Log.i("MovieManager","JSON-Result: " + jsonResult);

        if(jsonResult != null)
        {
            //First load genres
            String jsonGenre = httpHandler.getTextFromHttpContent(this.genreUrl);

            TheMovieDBJsonHandler jsonHandler =  new TheMovieDBJsonHandler();
            jsonHandler.setGenreIDs(jsonGenre);
            this.movies = jsonHandler.getMovieList(jsonResult);
        }

        return this.movies;
    }

    @Override
    protected void onPostExecute(List<Movie> newMovieList)
    {
        //Fill up SearchList here
        Log.i("MovieManager","(onPostExecute) start");
        delegate.asyncComplete(newMovieList);
    }
}