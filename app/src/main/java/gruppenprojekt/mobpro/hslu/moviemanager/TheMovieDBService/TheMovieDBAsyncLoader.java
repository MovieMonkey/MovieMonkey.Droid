package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.os.AsyncTask;

import java.net.URL;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.AsyncDelegate;

public class TheMovieDBAsyncLoader extends AsyncTask<Void, Void, List<Movie>>
{
    private AsyncDelegate delegate;
    private String keyword;

    private final boolean SHOW_INFO = false;

    public TheMovieDBAsyncLoader(AsyncDelegate newDelegate, String newKeyword){
        this.delegate = newDelegate;
        this.keyword = newKeyword;
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        HelperClass.newInfoLine(this,"doInBackground: Begin",SHOW_INFO);

        List<Movie> movies = null;

        TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
        URL searchUrl = HelperClass.getURL(HelperClass.SEARCH_URL + this.keyword + "&page=1");

        //Load first page
        String jsonResult = httpHandler.getTextFromHttpContent(searchUrl);

        if(jsonResult != null)
        {
            HelperClass.newInfoLine(this,"doInBackground: jsonResult != null",SHOW_INFO);
            TheMovieDBJsonHandler jsonHandler =  new TheMovieDBJsonHandler();
            //Get number of results
            int totalResults = jsonHandler.getTotalResults(jsonResult);
            HelperClass.newInfoLine(this,"doInBackground: Total Results: " + totalResults,SHOW_INFO);

            if(totalResults > 0){
                //First prepare genres
                searchUrl = HelperClass.getURL(HelperClass.GENRE_URL);
                String jsonGenre = httpHandler.getTextFromHttpContent(searchUrl);
                jsonHandler.setGenreIDs(jsonGenre);

                //Get number of pages
                int totalPages = jsonHandler.getTotalPages(jsonResult);
                HelperClass.newInfoLine(this,"doInBackground: Total Pages: " + totalPages,SHOW_INFO);

                //Load movies from the first page
                movies = jsonHandler.getMovieList(jsonResult);

                //Load movies from the other pages
                for(int x = 2; x <= totalPages; x++){
                    searchUrl = HelperClass.getURL(HelperClass.SEARCH_URL + this.keyword + "&page=" + x);
                    jsonResult = httpHandler.getTextFromHttpContent(searchUrl);

                    if(jsonResult != null){
                        movies.addAll(jsonHandler.getMovieList(jsonResult));
                    }

                    //Quick and Dirty save
                    if(movies.size() > 500){
                        break;
                    }
                }
            }
        }

        HelperClass.newInfoLine(this,"doInBackground: End",SHOW_INFO);
        return movies;
    }

    @Override
    protected void onPostExecute(List<Movie> newMovieList)
    {
        HelperClass.newInfoLine(this,"onPostExecute: Begin",SHOW_INFO);

        //Fill up SearchList here
        delegate.asyncComplete(newMovieList);

        HelperClass.newInfoLine(this,"onPostExecute: End",SHOW_INFO);
    }
}