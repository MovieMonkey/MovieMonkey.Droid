package gruppenprojekt.mobpro.hslu.moviemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories.MovieDataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;

public class DetailsMovieActivity extends AppCompatActivity {

    public static final String SELECTED_MOVIE_ID_TAG = "selected_movie_id";

    private MovieDataRepository dataRepository;
    private Movie selectedMovie;

    private ImageView imageViewThumbnail;
    private TextView textViewTitle;
    private TextView textViewGenre;
    private TextView textViewYear;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataRepository = new MovieDataRepository(new MovieDataAccess(this));
        selectedMovie = dataRepository.getById(getIntent().getIntExtra(SELECTED_MOVIE_ID_TAG, 0));

        setTitle(selectedMovie.getOriginalTitle());

        imageViewThumbnail = (ImageView) findViewById(R.id.search_list_view_row_image);
        textViewTitle = (TextView) findViewById(R.id.search_list_row_title);
        textViewGenre = (TextView) findViewById(R.id.search_list_row_genre);
        textViewYear = (TextView) findViewById(R.id.search_list_row_year);
        textViewDescription = (TextView) findViewById(R.id.text_view_movie_description);
        Button button_mark_favorite = (Button) findViewById(R.id.button_mark_favorite);
        
        imageViewThumbnail.setImageBitmap(selectedMovie.getThumbnail());
        textViewTitle.setText(selectedMovie.getOriginalTitle());
        textViewGenre.setText(selectedMovie.getGenre());
        textViewYear.setText(String.valueOf(selectedMovie.getYear()));
        textViewDescription.setText(selectedMovie.getOverview());

        button_mark_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
