package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

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

    //private final WeakReference<ImageView> imageViewReference;

    public TheMovieDBImageAsyncLoader(int Position, MovieHolder view, URL newImageUrl){
        this.itemPosition = Position;
        this.imageUrl = newImageUrl;
        this.holder = view;
        //this.imageViewReference = new WeakReference<>(view);
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        TheMovieDBHttpHandler httpHandler = new TheMovieDBHttpHandler();
        return httpHandler.getBitmapFromHttpContent(this.imageUrl);
    }

    @Override
    protected void onPostExecute(Bitmap newBitmap)
    {
        if(isCancelled()){
            newBitmap = null;
        } else {
            //if(this.holder.position == itemPosition){
                this.holder.imgCover.setImageBitmap(newBitmap);
            //}

            /*
            if(imageViewReference != null){
                ImageView imageView = imageViewReference.get();
                if(imageView != null){
                    imageView.setImageBitmap(newBitmap);
                }
            }*/
        }

        //Fill up SearchList here
        Log.i("MovieManager","(onPostExecute) start");
    }
}
