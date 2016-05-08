package gruppenprojekt.mobpro.hslu.moviemanager.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

public class MovieAdapter extends ArrayAdapter<Movie> {
    Context context;
    int layoutResourceId;
    List<Movie> data = null;

    public MovieAdapter(Context context, int layoutResourceId, List<Movie> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MovieHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MovieHolder();

            row.setTag(holder);
        }
        else
        {
            holder = (MovieHolder)row.getTag();
        }

        Movie movie = data.get(position);

        return row;
    }
    
    static class MovieHolder
    {
        TextView txtName;
        TextView txtYear;
        ImageView imgCover;
    }
}
