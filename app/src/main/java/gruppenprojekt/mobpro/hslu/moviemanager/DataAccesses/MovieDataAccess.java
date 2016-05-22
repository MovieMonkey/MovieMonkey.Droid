package gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gruppenprojekt.mobpro.hslu.moviemanager.DatabaseModels.Movie;
import gruppenprojekt.mobpro.hslu.moviemanager.Interfaces.DataAccess;

public class MovieDataAccess extends BasicDataAccess implements DataAccess<Movie> {

    public MovieDataAccess(Context context) {
        super(context);
    }

    @Override
    public void save(Movie itemToSave) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, itemToSave.getOriginalTitle());
        values.put(KEY_GENRE, itemToSave.getGenre());
        values.put(KEY_YEAR, itemToSave.getYear());
        values.put(KEY_OVERVIEW, itemToSave.getOverview());
        values.put(KEY_IMAGE_ID, itemToSave.getImageID());

        if(itemToSave.getId() == 0){
            db.insert(TABLE_MOVIES,
                    null,
                    values);
        } else {
            db.update(TABLE_MOVIES,
                    values,
                    KEY_ID + " = ?",
                    new String[] {String.valueOf(itemToSave.getId())});
        }

        db.close();
    }

    @Override
    public void delete(Movie itemToDelete) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MOVIES,
                KEY_ID + " = ? ",
                new String[] {String.valueOf(itemToDelete.getId())});

        db.close();
    }

    @Override
    public List<Movie> loadList() {
        List<Movie> movies = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, null, null, null, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    movies.add(mapCursorToObject(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch(Exception ex){
            Log.e("MovieManager", ex.getMessage());
        }
        finally {
            cursor.close();
        }
        return movies;
    }

    @Override
    public Movie getById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, null, KEY_ID + " = " + id, null, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    return mapCursorToObject(cursor);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch(Exception ex){
            Log.e("MovieManager", ex.getMessage());
        }
        finally {
            cursor.close();
        }
        return null;
    }

    private Movie mapCursorToObject(Cursor cursor){
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(cursor.getString(0)));
        movie.setOriginalTitle(cursor.getString(1));
        movie.setGenre(cursor.getString(2));
        movie.setYear(cursor.getInt(3));
        movie.setOverview(cursor.getString(4));
        movie.setImageID(cursor.getString(5));

        return movie;
    }
}
