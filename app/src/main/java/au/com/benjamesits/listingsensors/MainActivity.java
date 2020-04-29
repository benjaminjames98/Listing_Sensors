package au.com.benjamesits.listingsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get list of sensors
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        if (sensors.isEmpty()) {
            String message = "No sensors detected";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // get list of sensor names
        List<String> sensorNames = new ArrayList<>();
        for (Sensor s : sensors)
            sensorNames.add(s.getName());

        // display sensor names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sensorNames);
        ListView listView = findViewById(R.id.sensorListView);
        listView.setAdapter(adapter);
    }
}
