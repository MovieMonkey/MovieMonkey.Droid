package gruppenprojekt.mobpro.hslu.moviemanager.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.R;

/**
 * Created by Adrian Kauz on 14.05.2016.
 */
public class SearchActivityBottomFragment extends Fragment{
    Button button;
    List<Movie> movieList;

    /*
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] valueList = {"Was","auch","immer"};
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_search_bottom,container,false);

        /*
        listView = (ListView) view.findViewById(R.id.search_ListView);

        adapter = new ArrayAdapter<>(getActivity(),R.layout.search_list_view_item,R.id.search_list_view_row_item,valueList);
        listView.setAdapter(adapter);
*/
        return view;
    }
}
