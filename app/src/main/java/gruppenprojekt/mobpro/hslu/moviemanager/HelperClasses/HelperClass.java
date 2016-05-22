package gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBHttpHandler;

/**
 * Created by Adrian Kauz on 18.05.2016.
 */
public final class HelperClass {
    public static final String APP_NAME = "MovieManager";
    public static final String API_KEY = "c1c4bc25d948773ed2019e99a4a82e6d";
    public static final String LOCAL_POSTER_PATH = "/images/posters";
    public static final String LOCAL_THUMBNAIL_PATH = "/images/thumbnails";
    public static final String REMOTE_POSTER_THUMBNAIL_PATH = "https://image.tmdb.org/t/p/w185";
    public static final String REMOTE_POSTER_PATH = "https://image.tmdb.org/t/p/w396";
    public static final String REMOTE_POSTER_ORIGINAL_PATH = "https://image.tmdb.org/t/p/original";
    public static final String GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY;
    public static final String SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language_id=en&query=";


    public static void newInfoLine(Object newClass, String newInfo, boolean showInfo){
        if(showInfo){
            Log.i(APP_NAME + " --> \"" + newClass.getClass().getSimpleName() + "\"", newInfo);
        }
    }


    public static URL getURL(String newString){
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
        File thumbnailPath = new File(newContext.getFilesDir().toString() + LOCAL_THUMBNAIL_PATH);

        if(!thumbnailPath.exists()){
            thumbnailPath.mkdirs();
        }

        //Prepare poster folder
        File posterPath = new File(newContext.getFilesDir().toString() + LOCAL_POSTER_PATH);

        if(!posterPath.exists()){
            posterPath.mkdirs();
        }
    }


    public static void savePoster(Context context, String imageID){
        String directoryPath =  context.getFilesDir().toString();
        File file = new File(directoryPath + HelperClass.LOCAL_POSTER_PATH + imageID);

        new AsyncImageDownloader(file,HelperClass.getURL(HelperClass.REMOTE_POSTER_PATH + imageID)).execute();
    }


    public static void saveThumbnail(Context context, String imageID, Bitmap newBitmap) {
        String directoryPath =  context.getFilesDir().toString();
        File file = new File(directoryPath + HelperClass.LOCAL_THUMBNAIL_PATH + imageID);

        try (FileOutputStream fo = new FileOutputStream(file)) {
            newBitmap.compress(Bitmap.CompressFormat.JPEG,80,fo);
        } catch (Exception ex) {

        }
    }
}
