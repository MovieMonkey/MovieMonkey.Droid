package gruppenprojekt.mobpro.hslu.moviemanager;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

        setTitle(selectedMovie.getOriginalTitle());

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
