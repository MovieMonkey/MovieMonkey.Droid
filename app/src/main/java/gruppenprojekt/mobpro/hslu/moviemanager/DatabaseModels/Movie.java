package gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels;

import java.util.List;

public class Movie {
    private int id;
    private int tmdb_Id;
    private int imdb_Id;
    private int year;
    private String originalTitle;
    private List<String> genre;
    private String overview;
    private String thumpPathBigLocal;
    private String thumpPathSmallLocal;
    private String thumpPathRemote;
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
        return tmdb_Id;
    }
    public void setTmdbId(int tmdb_Id) {
        this.tmdb_Id = tmdb_Id;
    }

    public int getImdbId() {
        return imdb_Id;
    }
    public void setImdbId(int imdb_Id) {
        this.imdb_Id = imdb_Id;
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

    public List<String> getGenre() {
        return genre;
    }
    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getThumbPathBigLocal() {
        return thumpPathBigLocal;
    }
    public void setThumbPathBigLocal(String thumpPathBigLocal) {
        this.thumpPathBigLocal = thumpPathBigLocal;
    }

    public String getThumbPathSmallLocal() {
        return thumpPathSmallLocal;
    }
    public void setThumbPathSmallLocal(String thumpPathSmallLocal) {
        this.thumpPathSmallLocal = thumpPathSmallLocal;
    }

    public String getThumbPathRemote() {
        return thumpPathRemote;
    }
    public void setThumbPathRemote(String thumpPathRemote) {
        this.thumpPathRemote = thumpPathRemote;
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
