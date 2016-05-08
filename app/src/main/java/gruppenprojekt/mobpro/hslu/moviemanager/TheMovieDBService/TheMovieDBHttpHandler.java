package gruppenprojekt.mobpro.hslu.moviemanager.TheMovieDBService;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.SSLPeerUnverifiedException;

public class TheMovieDBHttpHandler {

    public String getTextFromHttpContent(URL newUrl){
        String resultText;
        InputStream content = getHttpContent(newUrl);
        resultText = getTextFromInputStream(content);

        try {
            content.close();
        } catch (IOException ex){
            Log.e("MovieManager", "Couldn't close HTTPInputStream");
        }

        return resultText;
    }

    private InputStream getHttpContent(URL newURL){
        InputStream content = null;

        try{
            HttpURLConnection httpConnection = (HttpURLConnection) newURL.openConnection();
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.connect();

            if(httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                content = httpConnection.getInputStream();
                Log.i("MovieManager", httpConnection.getResponseMessage());
            }
        } catch (SSLPeerUnverifiedException ex) {
            Log.e("MovieManager", "Not a trusted SSL-Certificate! \"" + this.getClass() + "\"");
        } catch (IOException ex) {
            Log.e("MovieManager", "Fehler in \"" + this.getClass() + "\"");
        } finally {
            return content;
        }
    }

    private String getTextFromInputStream(InputStream newInputStream) {
        StringBuilder text = new StringBuilder();

        if(newInputStream != null){
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(newInputStream));

            try{
                while((line = reader.readLine()) != null){
                    text.append(line);
                    text.append("\n");
                }

                reader.close();
                Log.i("MovieManager", "Text gelesen");
            } catch(IOException ex){
                Log.e("MovieManager", "Fehler beim Extrahieren des Textes!");
            }
        }

        return text.toString();
    }
}