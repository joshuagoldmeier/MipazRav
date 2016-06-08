package com.example.steve.mipazrav;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.net.URLConnection;

public class DummyListActivity extends Activity {
    private String[] items = {"Why The Rebbe Was The Greatest", "Is Shaving Permitted On Yom Hatzmaut? (no)",
            "Shaboygen Is The Best Class", "Shavuos: The day of Judgment", "Why Family Purity?", "The Rambam", "Torah Belongs only " +
            "to Orthodox Males", "Yeshiva University The End?"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_list);


        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DummyListActivity.this, MediaPlayback.class);
                startActivity(intent);



            }
        });
    }
}