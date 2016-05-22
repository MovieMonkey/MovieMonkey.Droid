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
import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;

public class TheMovieDBJsonHandler {
    private final boolean SHOW_INFO = false;
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


    public int getTotalPages(String newJson){
        return getRootInfo(newJson, "total_pages");
    }


    public int getTotalResults(String newJson){
        return getRootInfo(newJson, "total_results");
    }


    private int getRootInfo(String newJson, String newKey){
        int value = 0;

        try{
            JSONObject jsonRootObject = new JSONObject(newJson);
            value = jsonRootObject.optInt(newKey,0);
        } catch(JSONException ex) {
            Log.e("MovieManager", "JSON parsing failed!");
        }

        return value;
    }


    public List<Movie> getMovieList(String newJson) {
        HelperClass.newInfoLine(this,"getMovieList: Begin",SHOW_INFO);

        List<Movie> movieList = new ArrayList<>();

        try{
            JSONObject jsonRootObject = new JSONObject(newJson);
            JSONArray jsonArray = jsonRootObject.optJSONArray("results");

            if (jsonArray != null) {
                for(int x = 0; x < jsonArray.length(); x++){
                    JSONObject jsonObject = jsonArray.getJSONObject(x);

                    //reject entry if year is not defined
                    if(jsonObject.optString("release_date").toString().length() > 0){
                        JSONArray jsonArrayGenre = jsonObject.optJSONArray("genre_ids");

                        Movie currMovie = new Movie();

                        currMovie.setImageID(jsonObject.optString("poster_path","").toString());
                        currMovie.setOriginalTitle(jsonObject.optString("original_title","No title found").toString());
                        currMovie.setOverview(jsonObject.optString("overview","No Overview found").toString());
                        currMovie.setYear(getYearFromReleaseDate(jsonObject.optString("release_date").toString()));
                        currMovie.setGenre(getGenreListAsString(jsonArrayGenre));
                        currMovie.setTmdbId(jsonObject.optInt("id",0));
                        currMovie.setRating(jsonObject.optDouble("vote_average",0.0));

                        movieList.add(currMovie);
                    }
                }
            }
        } catch(JSONException ex) {
            movieList.clear();
            Log.e("MovieManager", "JSON parsing failed!");
        }

        HelperClass.newInfoLine(this,"getMovieList: End",SHOW_INFO);
        return movieList;
    }

    private String getGenreListAsString(JSONArray newJsonArray) {
        List<String> genreList = getGenreList(newJsonArray);

        StringBuilder builder = new StringBuilder();

        for(int x = 0; x < genreList.size(); x++){
            if(x == 0){
                builder.append(genreList.get(x));
            }
            else{
                builder.append(", ");
                builder.append(genreList.get(x));
            }
        }

        return builder.toString();
    }

    private List<String> getGenreList(JSONArray newJsonArray){
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
        int year = 0;

        if(releaseDate.length() != 0){
            String[] split = releaseDate.split("-");
            year = Integer.parseInt(split[0]);
        }

        return year;
    }
}