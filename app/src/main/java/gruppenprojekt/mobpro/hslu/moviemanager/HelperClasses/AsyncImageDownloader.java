package gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBHttpHandler;

/**
 * Created by Adrian Kauz on 22.05.2016.
 */
public class AsyncImageDownloader extends AsyncTask<Void, Void, Void>{
    //Field
    File file;
    URL url;


    //Konstruktor
    public AsyncImageDownloader(File newFile, URL newUrl){
        this.file = newFile;
        this.url = newUrl;
    }


    //Methods
    @Override
    protected Void doInBackground(Void... params) {
        TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
        InputStream inputStream = httpHandler.getInputStreamFromHttpContent(this.url);

        try (FileOutputStream fo = new FileOutputStream(file)) {
            byte buffer[] = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(buffer)) != -1) {
                fo.write(buffer, 0, length);
            }

            fo.flush();
        } catch (Exception ex) {

        }

        return null;
    }
}
