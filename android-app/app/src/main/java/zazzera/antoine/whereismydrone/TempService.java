package zazzera.antoine.whereismydrone;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TempService extends IntentService {

    private static final String TAG = "Temperature Service";

    public TempService(){
        super ("TempService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        // Diffusion d'un message global reçu par le BroadcastReceiver
        while (true){
            Temperature temp = null;
            try {
                String myurl= "https://api.mlab.com/api/1/databases/temperature_raspberry/collections/data_rasp?apiKey=P1k7QvxL9EdwCVkpRJL6NqB-XX9peOlt";

                URL url = new URL(myurl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);/* milliseconds */
                connection.setConnectTimeout(15000);/* milliseconds */
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                int response = connection.getResponseCode();
                Log.d(TAG, "The response is: " + response);
                InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
                String result = InputStreamOperations.InputStreamToString(inputStream);

                JSONArray array = new JSONArray(result);
                // On récupère le JSON complet
                JSONObject jsonObject = array.getJSONObject(0);
                temp = new Temperature();
                temp.setCelcius(jsonObject.getDouble("celcius"));
                temp.setFahrenheit(jsonObject.getDouble("fahrenheit"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent message = new Intent("com.example.myapp.EVENT_DONE");
            message.putExtra(MainActivity.TEMPERATURE, temp);
            sendBroadcast(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
