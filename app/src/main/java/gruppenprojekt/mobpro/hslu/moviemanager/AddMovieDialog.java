package gruppenprojekt.mobpro.hslu.moviemanager;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories.MovieDataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;

/**
 * Created by ninop on 20/05/2016.
 */
public class AddMovieDialog extends Dialog implements View.OnClickListener {

    private Activity currentActivity;
    private Movie selectedMovie;

    public AddMovieDialog(Activity activity, Movie movie) {
        super(activity);
        this.currentActivity = activity;
        this.selectedMovie = movie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.dialog_add_movie);

        Button positiveButton = (Button) findViewById(R.id.button_add_movie);
        Button negativeButton = (Button) findViewById(R.id.button_cancel);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MovieDataRepository(new MovieDataAccess(currentActivity))
                        .save(selectedMovie);
                dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
