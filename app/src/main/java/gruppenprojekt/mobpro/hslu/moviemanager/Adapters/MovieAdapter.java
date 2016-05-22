package gruppenprojekt.mobpro.hslu.moviemanager.Adapters;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.MovieHolder;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.R;
import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBImageAsyncLoader;

/**
 * Created by Adrian Kauz on 14.05.2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie>{
    private final boolean SHOW_INFO = false;
    private final boolean isLocal;
    private final Activity context;
    private final List<Movie> items;

    public MovieAdapter(Activity context, List<Movie> movies, boolean isLocal){
        super(context, R.layout.search_list_view_item, R.id.search_list_row_title);
        this.isLocal = isLocal;
        this.context = context;
        this.items = movies;

        Collections.sort(this.items);
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Movie getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return items.indexOf(getItem(position));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        HelperClass.newInfoLine(this,"getView: Begin",SHOW_INFO);
        HelperClass.newInfoLine(this,"getView: Current position: " + position + ": " + items.get(position).getImageID(),SHOW_INFO);

        View view = convertView;
        Movie movieItem = items.get(position);
        MovieHolder holder;

        //Adapter fills automatically up the visible Listview-Area with Listview-Items.
        //Count of visible items is always the same. When the user is scrolling, the adapter automatically
        //recycle the items and simulate a "scrolling"
        if(view == null){
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.search_list_view_item,parent, false);

            //set up MovieHolder
            holder = new MovieHolder();
            holder.position = position;
            holder.shortPathToThumbnail = movieItem.getImageID();
            holder.txtTitle = (TextView) view.findViewById(R.id.search_list_row_title);
            holder.txtGenre = (TextView) view.findViewById(R.id.search_list_row_genre);
            holder.txtYear = (TextView) view.findViewById(R.id.search_list_row_year);
            holder.imgCover = (ImageView) view.findViewById(R.id.search_list_view_row_image);

            //store holder with view
            view.setTag(holder);
        } else {
            holder = (MovieHolder) view.getTag();
        }

        if(movieItem != null){
            holder.position = position;
            holder.shortPathToThumbnail = movieItem.getImageID();
            holder.txtTitle.setText(movieItem.getOriginalTitle());
            holder.txtGenre.setText(movieItem.getGenre());
            holder.txtYear.setText(String.valueOf(movieItem.getYear()));
            holder.imgCover.setImageBitmap(null);

            if(movieItem.getThumbnail() == null){
                new TheMovieDBImageAsyncLoader(
                        movieItem,
                        holder,
                        new ContextWrapper(this.context),
                        this.isLocal
                ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
            } else {
                holder.imgCover.setImageBitmap(movieItem.getThumbnail());
            }
        }

        HelperClass.newInfoLine(this,"getView: End",SHOW_INFO);
        return view;
    }
}
