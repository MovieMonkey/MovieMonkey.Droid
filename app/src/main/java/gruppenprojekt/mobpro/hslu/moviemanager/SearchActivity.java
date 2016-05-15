package gruppenprojekt.mobpro.hslu.moviemanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Fragments.MovieListFragment;
import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBService;

public class SearchActivity extends AppCompatActivity {

    public List<Movie> currMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Film hinzufügen");
    }

    public void startSearchMovie(View v) {
        EditText editText = (EditText) findViewById(R.id.searchKey);
        TheMovieDBService service = new TheMovieDBService(this);
        service.searchMovie(editText.getText().toString());
        int x = 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return false;
    }
}
