package gruppenprojekt.mobpro.hslu.moviemanager.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.AddMovieDialog;
import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

/**
 * Created by Adrian Kauz on 14.05.2016.
 */
public class SearchActivityBottomFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_search_bottom,container,false);


        final ListView listView = (ListView) view.findViewById(R.id.search_ListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie)listView.getItemAtPosition(i);

                new AddMovieDialog(getActivity(), movie).show();
            }
        });

        return view;
    }

}
