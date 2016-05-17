package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.URL;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.MovieHolder;

/**
 * Created by Adrian Kauz on 15.05.2016.
 */
public class TheMovieDBImageAsyncLoader extends AsyncTask<Void, Void, Bitmap>{

    private URL imageUrl;
    private int itemPosition;
    private MovieHolder holder;
    private String imageFileName;
    private final String PATH_CACHE = "/data/cache";
    private ContextWrapper cw;

    //private final WeakReference<ImageView> imageViewReference;

    public TheMovieDBImageAsyncLoader(int Position, MovieHolder view, URL newImageUrl, String newImageFileName, ContextWrapper newCW ){
        this.itemPosition = Position;
        this.imageUrl = newImageUrl;
        this.holder = view;
        this.imageFileName = newImageFileName;
        this.cw = newCW;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap thumbnail;

        String directoryPath = this.cw.getFilesDir().toString();
        File file = new File(directoryPath + this.imageFileName);

        //Check first if file exists on cache
        if(!file.exists()) {
            //If not, get it from the website and save it on the cache
            TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
            InputStream inputStream = httpHandler.getInputStreamFromHttpContent(this.imageUrl);

            //and save it to the cache
            //File newFile = new File(directoryPath + this.imageFileName);

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
        thumbnail = BitmapFactory.decodeFile(directoryPath + this.imageFileName);

        return thumbnail;
    }

    @Override
    protected void onPostExecute(Bitmap newBitmap)
    {
        if(isCancelled()){
            this.holder.imgCover.setImageBitmap(null);
        } else {
                this.holder.imgCover.setImageBitmap(newBitmap);
        }
    }
}
