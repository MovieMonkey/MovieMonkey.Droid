package gruppenprojekt.mobpro.hslu.moviemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBService;

public class SearchActivity extends Activity {

    public List<Movie> currMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
    }

    public void startSearchMovie(View v) {
        EditText editText = (EditText) findViewById(R.id.searchKey);
        TheMovieDBService service = new TheMovieDBService(this);
        service.searchMovie(editText.getText().toString());
        int x = 0;
    }
}
