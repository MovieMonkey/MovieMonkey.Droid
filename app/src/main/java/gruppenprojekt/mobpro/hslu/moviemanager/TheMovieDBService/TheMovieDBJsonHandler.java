package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;

public class TheMovieDBJsonHandler {

    private Map<Integer, String> genreIDs = new HashMap<>();

    public TheMovieDBJsonHandler(){}

    public void setGenreIDs(String newJson){
        try{
            JSONObject jsonRootObject = new JSONObject(newJson);
            JSONArray jsonArray = jsonRootObject.optJSONArray("genres");

            if (jsonArray != null) {
                for(int x = 0; x < jsonArray.length(); x++){
                    JSONObject jsonObject = jsonArray.getJSONObject(x);
                    genreIDs.put(jsonObject.optInt("id"),jsonObject.optString("name").toString());
                }
            }

            int f = 0;
        } catch(JSONException ex) {
            Log.e("MovieManager", "JSON parsing failed!");
        }
    }

    public List<Movie> getMovieList(String newJson) {
        List<Movie> movieList = new ArrayList<>();

        try{
            JSONObject jsonRootObject = new JSONObject(newJson);
            JSONArray jsonArray = jsonRootObject.optJSONArray("results");

            if (jsonArray != null) {
                for(int x = 0; x < jsonArray.length(); x++){
                    JSONObject jsonObject = jsonArray.getJSONObject(x);
                    JSONArray jsonArrayGenre = jsonObject.optJSONArray("genre_ids");

                    Movie currMovie = new Movie();
                    currMovie.setThumbPathRemote(jsonObject.optString("poster_path").toString());
                    currMovie.setOriginalTitle(jsonObject.optString("original_title").toString());
                    currMovie.setOverview(jsonObject.optString("overview").toString());
                    currMovie.setYear(getYearFromReleaseDate(jsonObject.optString("release_date").toString()));
                    currMovie.setGenre(generateGenresFromIDs(jsonArrayGenre));
                    currMovie.setTmdbId(jsonObject.optInt("id"));
                    currMovie.setRating(jsonObject.optDouble("vote_average"));

                    movieList.add(currMovie);
                }
            }

            return movieList;

        } catch(JSONException ex) {
            Log.e("MovieManager", "JSON parsing failed!");
        }

        return null;
    }

    private List<String> generateGenresFromIDs(JSONArray newJsonArray){
        List<String> genres = new ArrayList<>();

        if(newJsonArray != null)
        {
            for(int x = 0; x < newJsonArray.length(); x++){
                int id;

                try{
                    id = newJsonArray.getInt(x);
                } catch (JSONException ex){
                    id = 0;
                }

                genres.add(genreIDs.get(id));
            }
        }
        else
            return null;

        return genres;
    }

    private int getYearFromReleaseDate(String releaseDate){
        String[] split = releaseDate.split("-");

        return Integer.parseInt(split[0]);
    }
}