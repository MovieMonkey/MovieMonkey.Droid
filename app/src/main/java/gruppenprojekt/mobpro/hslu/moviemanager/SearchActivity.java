package gruppenprojekt.mobpro.hslu.moviemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;
import gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService.TheMovieDBService;

public class SearchActivity extends AppCompatActivity {

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
        HelperClass.hideOnScreenKeyboard(getApplicationContext(),v);

        EditText editText = (EditText) findViewById(R.id.searchKey);
        TheMovieDBService service = new TheMovieDBService(this);
        service.searchMovie(editText.getText().toString());
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
