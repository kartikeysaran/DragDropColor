package kartikey.saran.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import kartikey.saran.myapplication.Adapters.SelectPartAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(v-> {
            startActivity(new Intent(MainActivity.this, SelectActivity.class));
        });
    }
}