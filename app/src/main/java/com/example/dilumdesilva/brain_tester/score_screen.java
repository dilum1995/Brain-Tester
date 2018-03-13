package com.example.dilumdesilva.brain_tester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class score_screen extends AppCompatActivity {

    private ArrayAdapter<String> ScoreAdapter;  //Array adapter to store the scores
    ListView scoreListView;

    Button backButton;

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        backButton = (Button) findViewById(R.id.btnBack_scoreScreen);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , game_menu.class);
                finish();
                startActivity(intent);

            }
        });

        //initialize the score list view
        Intent i = getIntent();
        arrayList = i.getStringArrayListExtra("scorelist");

        ArrayList<String> listArr = new ArrayList<>();

        for(int j=0 ; j<10 ; j++){

            listArr.add(arrayList.get(j));

        }

        ScoreAdapter = new ArrayAdapter<String>(this,
                R.layout.score_list_items , listArr);

        scoreListView = (ListView) findViewById(R.id.list_scoreScreen) ;
        scoreListView.setAdapter(ScoreAdapter);
    }
}
