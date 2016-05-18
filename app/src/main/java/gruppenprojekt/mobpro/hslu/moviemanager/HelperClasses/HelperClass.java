package gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Adrian Kauz on 18.05.2016.
 */
public final class HelperClass {
    public static final String APP_NAME = "MovieManager";
    public static final String API_KEY = "c1c4bc25d948773ed2019e99a4a82e6d";
    public static final String POSTER_THUMBNAIL_PATH = "https://image.tmdb.org/t/p/w185";
    public static final String POSTER_ORIGINAL_PATH = "https://image.tmdb.org/t/p/original";
    public static final String GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY;
    public static final String SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language_id=en&query=";
    public static final String PATH_THUMBNAIL_CACHE = "/cache/thumbnails";


    public static void newInfoLine(Object newClass, String newInfo, boolean showInfo){
        if(showInfo){
            Log.i(APP_NAME + " --> \"" + newClass.getClass().getSimpleName() + "\"", newInfo);
        }
    }


    public static URL setURL(String newString){
        try{
            return new URL(newString);
        } catch(MalformedURLException ex){
            Log.e("MovieManager","MalformedURLException: " + newString);
            return null;
        }
    }


    public static void hideOnScreenKeyboard(Context newContext, View newView){
        InputMethodManager imm = (InputMethodManager) newContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(newView.getWindowToken(),0);
    }


    public static void prepareEnvironment(Context newContext){
        //Prepare thumbnail folder
        File thumbCachePath = new File(newContext.getFilesDir().toString() + PATH_THUMBNAIL_CACHE);

        if(!thumbCachePath.exists()){
            thumbCachePath.mkdirs();
        }
    }


    public static void clearThumbnailCache(Context newContext){
        File thumbCachePath = new File(newContext.getFilesDir().toString() + PATH_THUMBNAIL_CACHE);

        for(File file: thumbCachePath.listFiles()){
            file.delete();
        }
    }
}
