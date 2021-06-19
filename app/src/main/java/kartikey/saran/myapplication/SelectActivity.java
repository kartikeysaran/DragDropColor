package kartikey.saran.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import kartikey.saran.myapplication.Adapters.SelectPartAdapter;
import kartikey.saran.myapplication.Model.Obj;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ArrayList<Obj> objs = new ArrayList<>();
        objs.add(new Obj(R.drawable.head, "Head"));
        objs.add(new Obj(R.drawable.center, "Center"));
        objs.add(new Obj(R.drawable.leftleg, "Left Leg"));
        objs.add(new Obj(R.drawable.rightleg, "Right Leg"));
        objs.add(new Obj(R.drawable.lefthand, "Left Hand"));
        objs.add(new Obj(R.drawable.righthand, "Right Hand"));

        SelectPartAdapter selectPartAdapter = new SelectPartAdapter(objs, getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(selectPartAdapter);

        findViewById(R.id.btn).setOnClickListener(v->{
            if(Application.objs!= null && Application.objs.size() >= 3 ) {
                startActivity(new Intent(SelectActivity.this, DragActivity.class));
            } else {
                Toast.makeText(this, "Atleast Select 3 Components", Toast.LENGTH_SHORT).show();
            }
        });
    }
}