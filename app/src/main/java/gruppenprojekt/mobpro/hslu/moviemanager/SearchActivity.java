package gruppenprojekt.mobpro.hslu.moviemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBService;

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    EditText searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = (EditText) findViewById(R.id.searchKey);
        searchField.setOnEditorActionListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add a movie");
    }

    public void startSearchMovie(View v) {
        HelperClass.hideOnScreenKeyboard(getApplicationContext(),v);

        TheMovieDBService service = new TheMovieDBService(this);
        service.searchMovie(this.searchField.getText().toString());
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

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            startSearchMovie(v);
            return true;
        }

        return false;
    }
}
