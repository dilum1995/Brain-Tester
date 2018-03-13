package com.example.dilumdesilva.brain_tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dilumdesilva on 3/9/18.
 */

public class QuestionsBuilder {

    public String strDifficultyLevel;
    public int difficultyLevel;

    public int maxTerms;

    List<Integer> termsList;
    List<Character> operatorList;

    //strings to store the question and answer
    String questionString,answerString;

    public QuestionsBuilder(String StrDifficultyLevel){
        this.strDifficultyLevel = StrDifficultyLevel;

        //initializing the difficulty level
        switch (strDifficultyLevel){

            case "Novice" : {
                difficultyLevel = 1;
                maxTerms = 2;
            }

            case "Easy" : {
                difficultyLevel = 2;
                maxTerms = 3;
            }

            case "Medium" : {
                difficultyLevel = 3;
                maxTerms = 4;
            }

            case "Guru" : {
                difficultyLevel = 4;
                maxTerms = 6;
            }

        }

        //initializing a list of numbers
        termsList = new ArrayList<>();

        //initializing a list of operators
        operatorList = new ArrayList<>();

        //Generate the numbers depending on the difficulty
        generateNumberList(termsList , maxTerms);

        //Generate list of operators
        generateOperatorList(operatorList , maxTerms);

        //generate the question and answer
        generateQuestion();

    }



    public String[] generateQuestion() {
        switch (strDifficultyLevel){
            case "Novice" : {

                String[] questionNanswer = generateNoviceLevelQ() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            case "Easy" : {
                String[] questionNanswer = generateEasyLevelQ() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            case "Medium" : {
                String[] questionNanswer = generateMediumLevelQ() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            case "Guru" : {
                String[] questionNanswer = generateGuruLevelQ() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            default: break;
        }

        return null;
    }

    public char generateOperator(){

        Random random = new Random();
        int operator = random.nextInt(4);

        switch (operator){
            case 0 : return '+';

            case 1 : return '-';

            case 2 : return  '*';

            case 3 : return '/';
        }
        return '0';
    }

    public int generateRandomNumber(){

        Random random = new Random();

        //99 is the maximum and 1 is the minimum
        int number = random.nextInt(99) + 1;

        return number;

    }

    public void generateNumberList(List termList , int maximumTerms){

        for (int i = 0; i < maximumTerms ; i++){
            termList.add(generateRandomNumber());
        }

    }

    public void generateOperatorList(List operatorList , int maximumTerms){

        for (int i = 0; i < (maximumTerms-1) ; i++){
            operatorList.add(generateOperator());
        }

    }





    private String[] generateGuruLevelQ() {
        Random random = new Random();
        int number = random.nextInt(3);

        switch (number){
            //if zero, generate a question with four integers
            case 0:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)+ " " + operatorList.get(2) + " " + termsList.get(3);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,4));

                return new String[] {questionString,answerString};
            }
            //if one, generate a question with five integers
            case 1:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)
                        + " " + operatorList.get(2) + " " + termsList.get(3)
                        + " " + operatorList.get(3) + " " + termsList.get(4);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,5));

                return new String[] {questionString,answerString};

            }
            //if two, generate a question with six integers
            case 2:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)
                        + " " + operatorList.get(2) + " " + termsList.get(3)
                        + " " + operatorList.get(3) + " " + termsList.get(4)
                        + " " + operatorList.get(4) + " " + termsList.get(5);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,6));

                return new String[] {questionString,answerString};

            }

        }

        return null;
    }

    private String[] generateMediumLevelQ() {
        Random random = new Random();
        int number = random.nextInt(3);

        switch (number){
            //if zero, generate a question with two integers
            case 0:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,2));

                return new String[] {questionString,answerString};
            }
            //if one, generate a question with three integers
            case 1:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,3));

                return new String[] {questionString,answerString};

            }
            //if two, generate a question with four integers
            case 2:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)+ " " + operatorList.get(2) + " " + termsList.get(3);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,4));

                return new String[] {questionString,answerString};

            }
        }

        return null;
    }

    private String[] generateEasyLevelQ() {
        Random random = new Random();
        int number = random.nextInt(2);

        switch (number){
            //if zero, generate a question with two integers
            case 0:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,2));

                return new String[] {questionString,answerString};
            }
            //if one, generate a question with three integers
            case 1:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
                answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,3));

                return new String[] {questionString,answerString};

            }
        }

        return null;
    }

    private String[] generateNoviceLevelQ() {
        questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1);

       /* EvaluateEngine evaluateEngine = new EvaluateEngine();
        Double answer = evaluateEngine.evaluate(questionString);
        answerString = String.valueOf((int) Math.round(answer));*/

        AnswersBuilder answersBuilder = new AnswersBuilder(strDifficultyLevel);
        answerString = String.valueOf(answersBuilder.generateAnswer(termsList,operatorList,2));

        return new String[] {questionString,answerString};
    }
}
