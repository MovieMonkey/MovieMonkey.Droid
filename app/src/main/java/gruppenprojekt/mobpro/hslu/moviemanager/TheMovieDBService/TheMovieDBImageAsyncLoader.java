package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.MovieHolder;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

/**
 * Created by Adrian Kauz on 15.05.2016.
 */
public class TheMovieDBImageAsyncLoader extends AsyncTask<Void, Void, Bitmap>{

    private final boolean SHOW_INFO = false;
    private MovieHolder holder;
    private boolean getLocalContent;
    private ContextWrapper cw;
    private Movie movieItem;

    public TheMovieDBImageAsyncLoader(Movie movieItem, MovieHolder view, ContextWrapper newCW, boolean getLocalContent ){
        this.holder = view;
        this.cw = newCW;
        this.movieItem = movieItem;
        this.getLocalContent = getLocalContent;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        HelperClass.newInfoLine(this,"doInBackground: Begin",SHOW_INFO);
        Bitmap thumbnail;

        if(getLocalContent) {
            thumbnail = BitmapFactory.decodeFile(cw.getFilesDir().toString() + HelperClass.LOCAL_THUMBNAIL_PATH + this.movieItem.getImageID());
        } else {

            //Load thumbnail from the website and save it on the cache
            TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
            InputStream inputStream = httpHandler.getInputStreamFromHttpContent(HelperClass.getURL(HelperClass.REMOTE_POSTER_THUMBNAIL_PATH + this.movieItem.getImageID()));

            thumbnail = BitmapFactory.decodeStream(inputStream);
        }

        HelperClass.newInfoLine(this,"doInBackground: End",SHOW_INFO);

        return thumbnail;
    }

    @Override
    protected void onPostExecute(Bitmap newThumbnail)
    {
        Bitmap currBitmap;

        if(newThumbnail == null){
            currBitmap = BitmapFactory.decodeResource(cw.getResources(),R.drawable.moviemanager_default_picture);
        } else {
            currBitmap = newThumbnail;
        }

        if(new String(this.holder.txtTitle.getText().toString()).equals(this.movieItem.getOriginalTitle())) {
            this.holder.imgCover.setImageBitmap(currBitmap);
        }

        movieItem.setThumbnail(currBitmap);
    }
}
