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

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.MovieHolder;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

/**
 * Created by Adrian Kauz on 15.05.2016.
 */
public class TheMovieDBImageAsyncLoader extends AsyncTask<Void, Void, Bitmap>{

    private URL imageUrl;
    private int itemPosition;
    private MovieHolder holder;
    private String imageFileName;
    private final boolean SHOW_INFO = false;
    private ContextWrapper cw;

    public TheMovieDBImageAsyncLoader(int Position, MovieHolder view, URL newImageUrl, String newImageFileName, ContextWrapper newCW ){
        this.itemPosition = Position;
        this.imageUrl = newImageUrl;
        this.holder = view;
        this.imageFileName = newImageFileName;
        this.cw = newCW;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        HelperClass.newInfoLine(this,"doInBackground: Begin",SHOW_INFO);
        Bitmap thumbnail;

        String directoryPath = this.cw.getFilesDir().toString();
        File file = new File(directoryPath + HelperClass.PATH_THUMBNAIL_CACHE + this.imageFileName);

        HelperClass.newInfoLine(this,"doInBackground: File: " + file.getPath(),SHOW_INFO);
        //Check first if file exists on cache
        if(!file.exists()) {
            //If not, get it from the website and save it on the cache
            TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
            InputStream inputStream = httpHandler.getInputStreamFromHttpContent(this.imageUrl);

            //and save it to the cache
            try (FileOutputStream fo = new FileOutputStream(file)) {
                byte buffer[] = new byte[1024];
                int length = 0;

                while ((length = inputStream.read(buffer)) != -1) {
                    fo.write(buffer, 0, length);
                }

                fo.flush();
            } catch (Exception ex) {

            }
        }

        //get thumbnail from cache
        thumbnail = BitmapFactory.decodeFile(directoryPath + HelperClass.PATH_THUMBNAIL_CACHE + this.imageFileName);

        HelperClass.newInfoLine(this,"doInBackground: End",SHOW_INFO);
        return thumbnail;
    }

    @Override
    protected void onPostExecute(Bitmap newThumbnail)
    {
        if(isCancelled() || newThumbnail == null){
            this.holder.imgCover.setImageResource(R.mipmap.moviemanager_default_picture);
        } else {
            this.holder.imgCover.setImageBitmap(newThumbnail);
        }
    }
}
