package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.Adapters.MovieAdapter;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.AsyncDelegate;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.MovieGrabberService;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

public class TheMovieDBService implements MovieGrabberService, AsyncDelegate {
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

            //First clear cache
            //HelperClass.clearThumbnailCache(context);

            //Then start search
            TheMovieDBAsyncLoader loader = new TheMovieDBAsyncLoader(this, keyword);
            loader.execute();
        }
    }

    @Override
    public void asyncComplete(List<Movie> movieList){
        Log.i("MovieManager", "AsyncComplete");

        if(movieList == null) {
            Log.i("MovieManager", "No entry found!");
            Toast.makeText(this.context,"No Entry found. Maybe you searched for a TV-Show instead of a movie!",Toast.LENGTH_SHORT).show();
        } else {
            if(movieList.size() > 500){
                Toast.makeText(this.context,"500+ entries found!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.context,movieList.size() + " entries found!",Toast.LENGTH_SHORT).show();
            }

            Log.i("MovieManager", "Found " + movieList.size() + " entries!");

            ListView listView = (ListView) ((Activity) this.context).findViewById(R.id.search_ListView);
            MovieAdapter adapter = new MovieAdapter((Activity) this.context, movieList, false);
            listView.setAdapter(adapter);
        }
    }
}
