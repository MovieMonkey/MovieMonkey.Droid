package gruppenprojekt.mobpro.hslu.moviemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBService;

public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
    }

    public void startSearchMovie(View v) {
        EditText editText = (EditText) findViewById(R.id.searchKey);
        TheMovieDBService service = new TheMovieDBService(this);
        service.searchMovie(editText.getText().toString());
    }
}
