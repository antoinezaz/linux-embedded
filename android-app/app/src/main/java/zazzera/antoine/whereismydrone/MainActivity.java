package zazzera.antoine.whereismydrone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final String TEMPERATURE = "Temperature";
    private static final String TAG = "MainActivity";
    private TextView celcius;
    private TextView fahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListenerTemp();
            }
        });
    }

    private void startListenerTemp(){
        BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(final Context context, final Intent intent) {
                // Faire quelquechose au résultat
                Temperature temp = intent.getParcelableExtra(TEMPERATURE);
                if (temp != null){
                    Log.v(TAG, "onPostExecute: " + temp.getId() +  " " + temp.getCelcius() + " "
                            + temp.getFahrenheit());

                    celcius = (TextView) findViewById(R.id.celcius);
                    celcius.setText(temp.getCelcius() + " °C");
                    fahrenheit = (TextView) findViewById(R.id.fahrenheit);
                    fahrenheit.setText(temp.getFahrenheit() + "°F");
                }
            }
        };


        // enregistrement du BroadcastReceiver
        IntentFilter filter = new IntentFilter("com.example.myapp.EVENT_DONE");
        registerReceiver(myBroadcastReceiver, filter);

        // lancement du service
        Intent intent = new Intent(this, TempService.class);
        startService(intent);
    }
}
