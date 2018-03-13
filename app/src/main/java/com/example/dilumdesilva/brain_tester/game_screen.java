package com.example.dilumdesilva.brain_tester;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//implemented onclicklistner interface to handle the onclick events of the buttons
//implemented oncheckedchangedlistner to handle the state of the switch
public class game_screen extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    //declaring gui components
    TextView tv_lblHintState, tv_timeCounter, tv_lblHintsResult, tv_lblQuestionCounter, tv_lblQuestion, tv_lblAnswer;

    //declaring all keypad buttons
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnDel, btnHash, btnMinus;

    //declaring switch for the hint on/off function
    Switch hintSwitch;

    //a string variable to hold the difficulty level and answer
    String difficultyLevel, answerString;

    //static variable to maintain the question counter
    static int questionCount = 0;

    //to hold the game tries (initially this has been assigned to 1)
    int numOfTries = 1;

    //variable which holds the remaining seconds
    long secondsLeft;

    //boolean variables to hold states
    boolean isAnswerSubmitted = false;
    boolean isHintStateClicked = false;
    boolean isCounterRunning = false;
    boolean isContinueBtnClicked = false;

    //constant varible which holds max question count
    public static final int maxQuestionCount = 10;
    //constant which hold timer start time
    public static final int counter = 10000;

    //array which holds all the question and it's answer
    String [] questionAndAnswer_array;

    //array list store the game scores
    ArrayList<String> scoreArray;

    //Save data
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //initializing the arraylist which holds the game scores
        scoreArray = new ArrayList<>();

        Intent intent = getIntent();

        //initializing keypad related special functioning buttons
        btnDel = (Button) findViewById(R.id.btnDel);
        btnHash = (Button) findViewById(R.id.btnHash);
        btnMinus = (Button) findViewById(R.id.btnMinus);

        //initializing numberpad related buttons
        btnZero = (Button) findViewById(R.id.btnZero);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);

        //setting onclick listeners to the keypad buttons
        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnHash.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

        //initializing the switch related stuff
        hintSwitch = (Switch) findViewById(R.id.switchHints);
        hintSwitch.setOnCheckedChangeListener(this);
        tv_lblHintState = (TextView) findViewById(R.id.labelHintsOnOff);

        //initializing the items related to timer
        tv_timeCounter = (TextView) findViewById(R.id.labelTimeCounter_TV);

        //initializing the items related to question and q counter
        tv_lblQuestionCounter = (TextView) findViewById(R.id.questionCounter_TV);
        tv_lblQuestion = (TextView) findViewById(R.id.question_TV);

        //initializing the answer label and hints label
        tv_lblAnswer = (TextView) findViewById(R.id.answer_TV);
        tv_lblHintsResult = (TextView) findViewById(R.id.hintResultHolder_TV);


        isContinueBtnClicked = intent.getBooleanExtra("isContinueBtnClicked", false);

        if (isContinueBtnClicked){

            isHintStateClicked = intent.getBooleanExtra("HintsState",true);
            hintSwitch.setChecked(isHintStateClicked);

            difficultyLevel = intent.getStringExtra("DifficultyLevel");
            Toast.makeText(getBaseContext(), "Game Resumed", Toast.LENGTH_SHORT).show();

            tv_lblQuestion.setText(intent.getStringExtra("Question"));
            tv_lblAnswer.setText(intent.getStringExtra("Answer"));

            tv_lblHintsResult.setText(intent.getStringExtra("Result"));
            questionCount = intent.getIntExtra("QuestionNumber",0);

            if(isHintStateClicked){
                tv_lblHintState.setText("Hints are ON");

            }else {
                tv_lblHintState.setText("Hints are OFF");
            }

            Double answer = Double.valueOf(tv_lblQuestion.getText().toString());
            answerString = String.valueOf((int) Math.round(answer));

        }else{

            //since user hasn't pressed the continue button resetting all the states to default
            isHintStateClicked = false;
            isCounterRunning = false;
            isAnswerSubmitted = false;
            numOfTries = 1;
            questionCount = 0;

            difficultyLevel = intent.getStringExtra("DifficultyLevel");
            tv_timeCounter.setText("");
            tv_lblQuestion.setText("?");
            tv_lblAnswer.setText("ANSWER");


            makeQuestions();
        }

        //start the countdown timer
        countDownTimer.start();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sharedPreferences.edit();

    }

    private void makeQuestions() {
        countDownTimer.start();

        tv_lblHintsResult.setText("");
        tv_lblAnswer.setText("?");
        numOfTries = 1;

        //Generate a question
        QuestionsBuilder questionsBuilder = new QuestionsBuilder(difficultyLevel);

        questionAndAnswer_array = questionsBuilder.generateQuestion();

        tv_lblQuestion.setText(questionAndAnswer_array[0]);

        questionCount++;
        //Toast.makeText(getBaseContext(), "Question Number " + questionCount, Toast.LENGTH_SHORT).show();
        isAnswerSubmitted = false;
    }

    //method which handle the onclick events of the buttons
    @Override
    public void onClick(View v) {

        if(tv_lblAnswer.getText().toString().equals("?")){
            tv_lblAnswer.setText(null);
        }

        switch (v.getId()){
            case R.id.btnDel:{
                //once user press d key this will clear the last entered number

                //temporally storing the current entered answer
                String tempAnswer = tv_lblAnswer.getText().toString();

                //validating the current entered answer
                if (tempAnswer.length() >1 ) {

                    //starting index is 0 while required ending index has to be tempAnswer.length() - 1
                    tempAnswer = tempAnswer.substring(0, tempAnswer.length() - 1);
                    tv_lblAnswer.setText(tempAnswer);
                }
                else if (tempAnswer.length() <=1 ) {
                    tv_lblAnswer.setText("");
                }
                break;
            }
            case R.id.btnHash:{
                if(tv_lblAnswer.getText().toString().equals("")){
                    generateAlertBox("Please input a valid answer","OKAY");
                } else{

                    //call the validate method
                    validater();
                }
                break;
            }
            case R.id.btnMinus:{
                tv_lblAnswer.append("-");
                break;
            }
            case R.id.btnZero:{
                tv_lblAnswer.append("0");
                break;
            }
            case R.id.btnOne:{
                tv_lblAnswer.append("1");
                break;
            }
            case R.id.btnTwo:{
                tv_lblAnswer.append("2");
                break;
            }
            case R.id.btnThree:{
                tv_lblAnswer.append("3");
                break;
            }
            case R.id.btnFour:{
                tv_lblAnswer.append("4");
                break;
            }
            case R.id.btnFive:{
                tv_lblAnswer.append("5");
                break;
            }
            case R.id.btnSix:{
                tv_lblAnswer.append("6");
                break;
            }
            case R.id.btnSeven:{
                tv_lblAnswer.append("7");
                break;
            }
            case R.id.btnEight:{
                tv_lblAnswer.append("8");
                break;
            }
            case R.id.btnNine:{
                tv_lblAnswer.append("9");
                break;
            }
        }

    }

    private void validater() {
        if(questionCount < maxQuestionCount ) {

            if (isAnswerSubmitted == false) {

                //call the check answer method
                answerChecker();

            } else {

                //move on to the next question
                makeQuestions();

            }
        }else if(questionCount == maxQuestionCount ){

            //check the answer
            answerChecker();

        }else {

            /*when the maximum questions have been answered
            finish the current activity and move to the score */
            Intent intent = new Intent(getBaseContext(),
                    score_screen.class);
            intent.putStringArrayListExtra("scorelist", scoreArray);
            finish();
            startActivity(intent);

        }
    }

    private void answerChecker() {

        //to hold the correct answer temporally
        String tempCorrectAnswer;


        if(isContinueBtnClicked){
            tempCorrectAnswer = answerString;
        }else{
            tempCorrectAnswer = questionAndAnswer_array[1];
        }
        //check for the answer
        String submittedAnswer = tv_lblAnswer.getText().toString();

        if (submittedAnswer.equals(tempCorrectAnswer)) {
            tv_lblHintsResult.setText("CORRECT");
            tv_lblHintsResult.setTextColor(Color.GREEN);

            //save the score
            scoreArray.add("Question " + questionCount + "\t - \t" + String.valueOf(calculateScore(secondsLeft)));

        } else {
            //checking if the hints option is on
            if(isHintStateClicked == false ) {

                tv_lblHintsResult.setText("WRONG");
                tv_lblHintsResult.setTextColor(Color.RED);

                //save the score
                scoreArray.add("Question " + questionCount + "\t - \t" + "0");

            }else {

                if(numOfTries < 5) {

                    if (Integer.parseInt(submittedAnswer) > Integer.parseInt(tempCorrectAnswer)) {

                        tv_lblHintsResult.setText("LESS");
                        tv_lblHintsResult.setTextColor(Color.YELLOW);

                    } else if (Integer.parseInt(submittedAnswer) < Integer.parseInt(tempCorrectAnswer)) {

                        tv_lblHintsResult.setText("GREATER");
                        tv_lblHintsResult.setTextColor(Color.YELLOW);

                    }
                    Toast.makeText(getBaseContext() , "Attempts " + numOfTries , Toast.LENGTH_SHORT).show();
                    if(numOfTries==4) {
                        isAnswerSubmitted=true;
                        return;
                    }
                    numOfTries++;
                    return;
                }
            }
        }

        //button is pressed once
        isAnswerSubmitted = true;
    }

    private int calculateScore(long secondsRemaining) {
        int score = (int) (100 / (10 - secondsRemaining));
        return score;
    }

    //this method will generate alerts depending on the invoked situation
    private void generateAlertBox(String Msg, String Btn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(Msg);

        builder.setPositiveButton(
                Btn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //method which handles the state changes of the hints switch
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            tv_lblHintState.setText("Hints ON");
            isHintStateClicked = true;
            Toast.makeText(getBaseContext(),"Hints ON",Toast.LENGTH_SHORT).show();
        }else {
            tv_lblHintState.setText("Hints OFF");
            isHintStateClicked = false;
            Toast.makeText(getBaseContext(),"Hints OFF",Toast.LENGTH_SHORT).show();
        }
    }

    CountDownTimer countDownTimer = new CountDownTimer(counter, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsLeft = millisUntilFinished / 1000;
            tv_timeCounter.setText(millisUntilFinished/1000+"");
            String qCount = Integer.toString(questionCount);
            tv_lblQuestionCounter.setText("Q "+qCount);
        }

        @Override
        public void onFinish() {
            //save the score
            scoreArray.add("Question " + questionCount + "\t - \t" + "0");

            //check if the maximum number of questions have been reached
            if(questionCount == maxQuestionCount){

                //finish the current activity and move to the score
                Intent intent = new Intent(getBaseContext(),
                        score_screen.class);
                intent.putStringArrayListExtra("scorelist", scoreArray);
                countDownTimer.cancel();
                finish();
                startActivity(intent);

            }else {

                //move on to the next question
                makeQuestions();
                isCounterRunning = false;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();

        //save the current game by calling savegamedata method
        saveGameData();

        Intent intent = new Intent(this, game_menu.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish(); // finish the current activity
        startActivity(intent);

    }

    public void saveGameData(){

        //Data to be passed
        editor.putString("Difficulty" , difficultyLevel);
        editor.putBoolean("Hints" , isHintStateClicked);
        editor.putLong("TimeLeft" , secondsLeft);
        editor.putString("Question" , tv_lblQuestion.getText().toString());
        editor.putString("Answer" , tv_lblAnswer.getText().toString());
        editor.putString("Result" , tv_lblHintsResult.getText().toString());
        editor.putInt("QuestionNumber" , questionCount);
        //editor.putStringSet("Scores" , (Set<String>) scores);
        editor.commit();
    }


}
