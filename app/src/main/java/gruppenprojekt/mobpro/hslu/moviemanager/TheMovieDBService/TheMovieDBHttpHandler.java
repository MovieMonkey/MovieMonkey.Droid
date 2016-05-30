package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.SSLPeerUnverifiedException;

import gruppenprojekt.mobpro.hslu.moviemanager.HelperClasses.HelperClass;

public class TheMovieDBHttpHandler {
    private final boolean SHOW_INFO = false;

    public String getTextFromHttpContent(URL newUrl){
        String resultText = "";
        InputStream content = getHttpContent(newUrl);

        if(content != null) {
            resultText = getTextFromInputStream(content);

            try {
                content.close();
            } catch (IOException ex){
                Log.e("MovieManager", "Couldn't close HTTPInputStream");
            }
        }

        return resultText;
    }

    public Bitmap getBitmapFromHttpContent(URL newUrl){
        Bitmap resultBitmap;
        InputStream content = getHttpContent(newUrl);
        resultBitmap = getBitmapFromInputStream(content);

        if(content != null)
        {
            try {
                content.close();
            } catch (IOException ex){
                Log.e("MovieManager", "Couldn't close HTTPInputStream");
            }
        }

        return resultBitmap;
    }


    public InputStream getInputStreamFromHttpContent(URL newUrl){
        return getHttpContent(newUrl);
    }


    private InputStream getHttpContent(URL newURL){
        HelperClass.newInfoLine(this,"getHttpContent: Begin",SHOW_INFO);

        InputStream content = null;

        if(newURL == null){
            HelperClass.newInfoLine(this,"getHttpContent: URL is null",SHOW_INFO);
        } else {
            HelperClass.newInfoLine(this,"getHttpContent: " + newURL.toString(),SHOW_INFO);
        }

        try{
            HttpURLConnection httpConnection = (HttpURLConnection) newURL.openConnection();
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.connect();

            HelperClass.newInfoLine(this,"getHttpContent: ResponseCode: " + httpConnection.getResponseCode(),SHOW_INFO);

            if(httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                content = httpConnection.getInputStream();
            }
        } catch (SSLPeerUnverifiedException ex) {
            Log.e("MovieManager", "Not a trusted SSL-Certificate! \"" + this.getClass() + "\"");
        } catch (IOException ex) {
            Log.e("MovieManager", "Fehler in \"" + this.getClass() + "\"");
        } catch (Exception ex){
            Log.e("MovieManager", "Fehler: " + ex.getMessage());
        } finally {
            HelperClass.newInfoLine(this,"getHttpContent: End",SHOW_INFO);
            return content;
        }
    }

    private Bitmap getBitmapFromInputStream(InputStream newInputStream) {
        return BitmapFactory.decodeStream(newInputStream);
    }

    private String getTextFromInputStream(InputStream newInputStream) {
        HelperClass.newInfoLine(this,"getTextFromInputStream: Begin",SHOW_INFO);

        StringBuilder text = new StringBuilder();

        if(newInputStream != null){
            String line;

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(newInputStream))){
                while((line = reader.readLine()) != null){
                    text.append(line);
                    text.append("\n");
                }

                HelperClass.newInfoLine(this,"getTextFromInputStream: Text gelesen",SHOW_INFO);
            } catch(IOException ex){
                Log.e("MovieManager", "Fehler beim Extrahieren des Textes!");
            }
        }

        HelperClass.newInfoLine(this,"getTextFromInputStream: End",SHOW_INFO);
        return text.toString();
    }
}