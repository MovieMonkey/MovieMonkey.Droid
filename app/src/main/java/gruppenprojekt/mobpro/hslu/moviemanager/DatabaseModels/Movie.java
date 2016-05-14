package gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels;

import java.util.List;

public class Movie {
    private int id;
    private int tmdbId;
    private int imdbId;
    private int year;
    private String originalTitle;
    private String genre;
    private String overview;
    private String thumbPathBigLocal;
    private String thumbPathSmallLocal;
    private String thumbPathRemote;
    private List<String> actors;
    private double rating;

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

    public int getImdbId() {
        return imdbId;
    }
    public void setImdbId(int imdbId) {
        this.imdbId = imdbId;
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

    public String getThumbPathBigLocal() {
        return thumbPathBigLocal;
    }
    public void setThumbPathBigLocal(String thumpPathBigLocal) {
        this.thumbPathBigLocal = thumpPathBigLocal;
    }

    public String getThumbPathSmallLocal() {
        return thumbPathSmallLocal;
    }
    public void setThumbPathSmallLocal(String thumpPathSmallLocal) {
        this.thumbPathSmallLocal = thumpPathSmallLocal;
    }

    public String getThumbPathRemote() {
        return thumbPathRemote;
    }
    public void setThumbPathRemote(String thumpPathRemote) {
        this.thumbPathRemote = thumpPathRemote;
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
}
