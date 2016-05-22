package gruppenprojekt.mobpro.hslu.moviemanager;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import gruppenprojekt.mobpro.hslu.moviemanager.Fragments.FavoriteListFragment;
import gruppenprojekt.mobpro.hslu.moviemanager.Fragments.MovieListFragment;
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prepare environment
        HelperClass.prepareEnvironment(getApplicationContext());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                    drawerLayout,
                    toolbar,
                    R.string.text_drawer_open,
                    R.string.text_drawer_close);

            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }

        loadFirstFragment();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadFirstFragment(){
        MovieListFragment fragment = MovieListFragment.newInstance();
        String tag = fragment.getTag();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.support.design.R.anim.abc_fade_in, android.support.design.R.anim.abc_fade_out);

        transaction.replace(R.id.content_frame, fragment, tag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(android.support.design.R.anim.abc_fade_in, android.support.design.R.anim.abc_fade_out);
        String tag = "";

        if (id == R.id.nav_all) {
            MovieListFragment fragment = MovieListFragment.newInstance();
            tag = fragment.getTag();

            transaction.replace(R.id.content_frame, fragment, tag);
        } else if(id == R.id.nav_favorites){
            FavoriteListFragment fragment = FavoriteListFragment.newInstance();
            tag = fragment.getTag();

            transaction.replace(R.id.content_frame, fragment, tag);
        }

        transaction.addToBackStack(tag);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}