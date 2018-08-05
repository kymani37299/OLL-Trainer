package com.example.marko.olltrainer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.marko.olltrainer.model.OLLCase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO: Save selected cases on exit
    // TODO: Add select all button
    // TODO: Improve theme

    private static ArrayList<OLLCase> itemsSelected = new ArrayList<>();
    private static ArrayList<OLLCase> ollCases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListView = findViewById(R.id.oll_case_list);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                OLLCase oll_case = (OLLCase) parent.getItemAtPosition(position);
                if(itemsSelected.contains(oll_case)) {
                    itemsSelected.remove(oll_case);
                    view.setBackgroundColor(Color.WHITE);
                } else {
                    itemsSelected.add(oll_case);
                    view.setBackgroundColor(Color.GRAY);
                }
            }
        });

        ollCases = OLLCase.deserialize_cases(this);

        mListView.setAdapter(new OLLCaseAdapter(this,R.layout.oll_cube_view,ollCases));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.train_action) {
            Intent i = new Intent(MainActivity.this, TrainActivity.class);
            startActivity(i);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public static ArrayList<OLLCase> getSelectedCases() {
        if(itemsSelected.isEmpty()) {
            return ollCases;
        }else {
            return itemsSelected;
        }
    }

}
