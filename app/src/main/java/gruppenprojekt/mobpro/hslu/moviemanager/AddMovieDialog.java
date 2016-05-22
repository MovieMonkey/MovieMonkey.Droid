package gruppenprojekt.mobpro.hslu.moviemanager;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories.MovieDataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;

/**
 * Created by ninop on 20/05/2016.
 */
public class AddMovieDialog extends Dialog implements View.OnClickListener {

    private Activity currentActivity;
    private Movie selectedMovie;

    private ImageView imageViewThumbnail;
    private TextView textViewTitle;
    private TextView textViewGenre;
    private TextView textViewYear;
    private TextView textViewDescription;

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

                HelperClass.savePoster(getContext(),selectedMovie.getImageID());
                HelperClass.saveThumbnail(getContext(),selectedMovie.getImageID(),selectedMovie.getThumbnail());

                Toast.makeText(currentActivity, "Erfolgreich gespeichert",
                        Toast.LENGTH_LONG).show();

                currentActivity.finish();

                dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        imageViewThumbnail = (ImageView) findViewById(R.id.search_list_view_row_image);
        textViewTitle = (TextView) findViewById(R.id.search_list_row_title);
        textViewGenre = (TextView) findViewById(R.id.search_list_row_genre);
        textViewYear = (TextView) findViewById(R.id.search_list_row_year);
        textViewDescription = (TextView) findViewById(R.id.text_view_movie_description);

        imageViewThumbnail.setImageBitmap(selectedMovie.getThumbnail());
        textViewTitle.setText(selectedMovie.getOriginalTitle());
        textViewGenre.setText(selectedMovie.getGenre());
        textViewYear.setText(String.valueOf(selectedMovie.getYear()));
        textViewDescription.setText(selectedMovie.getOverview());
    }

    @Override
    public void onClick(View view) {

    }
}
