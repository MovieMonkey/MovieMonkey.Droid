package gruppenprojekt.mobpro.hslu.moviemanager.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import gruppenprojekt.mobpro.hslu.moviemanager.Adapters.CustomMovieAdapter;
import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories.MovieDataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.R;
import gruppenprojekt.mobpro.hslu.moviemanager.SearchActivity;

public class MovieListFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MovieListFragment.
     */
    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    private final String POSTER_THUMBNAIL_PATH = "https://image.tmdb.org/t/p/w185";

    private FloatingActionButton floatingActionButton;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        listView = (ListView) view.findViewById(R.id.list_view_movie);
        listView.setAdapter(new CustomMovieAdapter(getActivity(),
                new MovieDataRepository(new MovieDataAccess(getActivity())).getCache(),
                POSTER_THUMBNAIL_PATH));

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        return view;
    }
}
