package gruppenprojekt.mobpro.hslu.moviemanager.DataAccess;

public class Movie {
    private int id;
    private String name;

    public Movie(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}