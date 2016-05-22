package gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class Movie implements Comparable<Movie> {
    private int id;
    private int tmdbId;
    private int imdbId;
    private String imageID;
    private int year;
    private String originalTitle;
    private String genre;
    private String overview;
    private List<String> actors;
    private double rating;
    private Bitmap thumbnail;

    public Movie(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTmdbId() {
        return tmdbId;
    }
    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getImageID() {
        return this.imageID;
    }
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getActors() {
        return actors;
    }
    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public Bitmap getThumbnail(){
        return this.thumbnail;
    }
    public void setThumbnail(Bitmap newThumbnail){
        this.thumbnail = newThumbnail;
    }

    public int compareTo(Movie movieItem){
        //Absteigend sortieren
        //return Integer.compare(this.year, movieItem.getYear());

        //Aufsteigend sortieren
        return Integer.compare(movieItem.getYear(), this.year);
    }
}
