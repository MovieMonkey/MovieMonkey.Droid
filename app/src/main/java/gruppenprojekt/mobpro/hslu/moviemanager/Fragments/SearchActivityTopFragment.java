package gruppenprojekt.mobpro.hslu.moviemanager.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gruppenprojekt.mobpro.hslu.moviemanager.R;

/**
 * Created by Adrian Kauz on 14.05.2016.
 */
public class SearchActivityTopFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_search_top,container,false);
    }
}
