package gruppenprojekt.mobpro.hslu.moviemanager.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.Adapters.MovieAdapter;
import gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses.MovieDataAccess;
import gruppenprojekt.mobpro.hslu.moviemanager.DataRepositories.MovieDataRepository;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.DetailsMovieActivity;
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

    private FloatingActionButton floatingActionButton;
    private ListView listView;

    private MovieDataRepository dataRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        getActivity().setTitle("Movies");

        dataRepository = new MovieDataRepository(new MovieDataAccess(getActivity()));

        listView = (ListView) view.findViewById(R.id.list_view_movie);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie)listView.getItemAtPosition(i);

                Intent intent = new Intent(getActivity(), DetailsMovieActivity.class);
                intent.putExtra(DetailsMovieActivity.SELECTED_MOVIE_ID_TAG, movie.getId());
                startActivity(intent);
            }
        });

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        List<Movie> movieList = dataRepository.load();

        listView.setAdapter(new MovieAdapter(getActivity(),
                movieList, true));

        (getActivity().findViewById(R.id.text_view_list_placeholder))
                .setVisibility(movieList.size() > 0
                        ? View.INVISIBLE
                        : View.VISIBLE);
    }
}
