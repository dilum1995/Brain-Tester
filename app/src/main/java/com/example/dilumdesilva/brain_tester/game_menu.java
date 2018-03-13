package com.example.dilumdesilva.brain_tester;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class game_menu extends AppCompatActivity implements View.OnClickListener {

    //declaring the components of the UI screen
    Button btn_newGame, btn_continueGame, btn_aboutGame, btn_exitGame, btn_popUp_Okay;
    private PopupWindow about_popUpWindow;
    TextView x_closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        //initializing menu buttons
        btn_newGame = (Button) findViewById(R.id.btn_newGame);
        btn_continueGame = (Button) findViewById(R.id.btn_continueGame);
        btn_aboutGame = (Button) findViewById(R.id.btn_aboutGame);
        btn_exitGame = (Button) findViewById(R.id.btn_exitGame);

        //initializing onclick events for the menu buttons
        btn_newGame.setOnClickListener(this);
        btn_continueGame.setOnClickListener(this);
        btn_aboutGame.setOnClickListener(this);
        btn_exitGame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        //to perform actions according to the captured click event
        switch (v.getId()){
            case R.id.btn_newGame :{
                showDifficultyPage();
                break;
            }
            case R.id.btn_continueGame :{
                break;
            }
            case R.id.btn_aboutGame :{
                showAboutWindowPopUp(v);
                break;
            }
            case R.id.btn_exitGame :{
                showExitGamePage();
            }
        }
    }

    //method which direct the player to the steps of starting the game
    private void showDifficultyPage() {
        List<String> listDifficultyLevel = new ArrayList<String>();
        listDifficultyLevel.add("Novice");
        listDifficultyLevel.add("Easy");
        listDifficultyLevel.add("Medium");
        listDifficultyLevel.add("Guru");

        //Create sequence of items
        final CharSequence[] Difficulty = listDifficultyLevel.toArray(new String[listDifficultyLevel.size()]);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this,R.style.whiteDialogBoxTheme);
        dialogBuilder.setTitle("Please select the game listDifficultyLevel");

        dialogBuilder.setItems(Difficulty, new DialogInterface.OnClickListener() {

            //when an item is clicked, listDifficultyLevel is passed via the intent
            public void onClick(DialogInterface dialog, int item) {
                //the selected item stored in a string to be passed
                String selectedDifficulty = Difficulty[item].toString();
                Intent intent = new Intent(getBaseContext(), game_screen.class);
                intent.putExtra("DifficultyLevel", selectedDifficulty);

                Toast.makeText(game_menu.this, selectedDifficulty + " Difficulty selected",Toast.LENGTH_SHORT).show();

                //start the Game Screen activity
                startActivity(intent);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }


    //method which handles all the actions related to aboutGame
    private void showAboutWindowPopUp(View v){
        try {
            //get an instance of layoutinflater
            LayoutInflater inflater = (LayoutInflater) game_menu.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //initiate the view
            View layout = inflater.inflate(R.layout.about_popup,
                    (ViewGroup) findViewById(R.id.dilum_customPopUp));

            //initialize a size for the popup
            about_popUpWindow = new PopupWindow(layout, 1000, 1450, true);

            // display the popup in the center
            about_popUpWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            x_closeButton = (TextView) layout.findViewById(R.id.popupClose);
            x_closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    about_popUpWindow.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method which handles all the actions related to exit from the game
    private void showExitGamePage() {
        AlertDialog.Builder exitAlertBuilder = new AlertDialog.Builder(this , R.style.whiteDialogBoxTheme);
        exitAlertBuilder.setMessage("Would you like to save your data before exit?");
        exitAlertBuilder.setCancelable(true);

        //this part will perform actions if user press yes
        exitAlertBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        //this part will perform actions if user press no
        exitAlertBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        //simply assigning the alert builder to a alert dialog varible to display
        AlertDialog exitAlertDialog = exitAlertBuilder.create();
        exitAlertDialog.show();
    }
}
