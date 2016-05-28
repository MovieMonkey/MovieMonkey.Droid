package gruppenprojekt.mobpro.hslu.moviemanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories.MovieDataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;

public class DetailsMovieActivity extends AppCompatActivity {

    public static final String SELECTED_MOVIE_ID_TAG = "selected_movie_id";

    private MovieDataRepository dataRepository;
    private Movie selectedMovie;

    private ImageView imageViewThumbnail;
    private TextView textViewTitle;
    private TextView textViewGenre;
    private TextView textViewYear;
    private TextView textViewOverview;
    private RatingBar ratingBarRating;
    private TextView textViewRatingInfo;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataRepository = new MovieDataRepository(new MovieDataAccess(this));
        selectedMovie = dataRepository.getById(getIntent().getIntExtra(SELECTED_MOVIE_ID_TAG, 0));

        setTitle("Details");

        imageViewThumbnail = (ImageView) findViewById(R.id.movieThumbnail);
        textViewTitle = (TextView) findViewById(R.id.movieTitle);
        textViewGenre = (TextView) findViewById(R.id.movieGenre);
        textViewYear = (TextView) findViewById(R.id.movieYear);
        textViewOverview = (TextView) findViewById(R.id.movieOverview);
        ratingBarRating = (RatingBar) findViewById(R.id.movieRating);
        textViewRatingInfo = (TextView) findViewById(R.id.movieRatingInfo);

        //set thumbnail
        Bitmap thumbnail = BitmapFactory.decodeFile(getFilesDir() + HelperClass.LOCAL_POSTER_PATH + selectedMovie.getImageID());

        if(thumbnail == null){
            thumbnail = BitmapFactory.decodeResource(getResources(),R.drawable.moviemanager_icon_no_image);
        }

        imageViewThumbnail.setImageBitmap(thumbnail);

        textViewTitle.setText(selectedMovie.getOriginalTitle());
        textViewGenre.setText(selectedMovie.getGenre());
        textViewYear.setText(String.valueOf(selectedMovie.getYear()));
        ratingBarRating.setRating((float) selectedMovie.getRating());
        textViewRatingInfo.setText(String.valueOf(selectedMovie.getRating() + "/10    (" + String.format("%,d",selectedMovie.getRatingCount()).replace(",","'") + ") votes"));
        textViewOverview.setText(selectedMovie.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_details_menu, menu);
        this.menu = menu;

        setIcon();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            case R.id.action_favorite:
                SetFavorite();
                return true;

            case R.id.action_delete:
                dataRepository.delete(selectedMovie);
                finish();
                return true;
        }

        return false;
    }

    private void SetFavorite(){
        if(selectedMovie.getIsFavorite()) {
            selectedMovie.setIsFavorite(false);
        } else{
            selectedMovie.setIsFavorite(true);
        }
        dataRepository.save(selectedMovie);
        setIcon();
    }

    private void setIcon(){
        if(selectedMovie.getIsFavorite()) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_star, getTheme()));
        } else{
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_star_empty, getTheme()));
        }
    }
}
