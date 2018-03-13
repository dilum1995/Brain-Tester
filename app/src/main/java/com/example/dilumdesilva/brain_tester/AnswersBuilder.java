package com.example.dilumdesilva.brain_tester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilumdesilva on 3/9/18.
 */

public class AnswersBuilder {

    private String difficulty;
    private int[] answer;
    ArrayList<Integer> arrayList;

    public AnswersBuilder(String difficulty){

        this.difficulty = difficulty;

    }

    public int generateAnswer(List<Integer> termsList , List<Character> operatorList , int numTerms){

        arrayList = new ArrayList<>();
        int finalAnswer = 0;

        switch (difficulty) {
            case "Novice": {
                answer = new int[numTerms - 1];
                answer[0] = Operation(termsList.get(0) , operatorList.get(0) , termsList.get(1) );
                finalAnswer = answer[0];
                break;
            }
            case "Easy": {

                if (numTerms == 2) {

                    answer = new int[numTerms - 1];
                    answer[0]  = Operation(termsList.get(0) , operatorList.get(0), termsList.get(1) );

                    finalAnswer = answer[0];

                }else if(numTerms == 3){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));

                    finalAnswer = answer [1];

                }

                break;
            }
            case "Medium": {

                if (numTerms == 2) {

                    answer = new int[numTerms - 1];
                    answer[0]  = Operation(termsList.get(0) , operatorList.get(0), termsList.get(1) );

                    finalAnswer = answer[0];

                }else if(numTerms == 3){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));

                    finalAnswer = answer [1];

                }else if (numTerms == 4){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));

                    finalAnswer = answer [2];

                }

                break;
            }
            case "Guru" :{

                if (numTerms == 4){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));

                    finalAnswer = answer [2];

                } else if(numTerms == 5){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));
                    answer[3] = Operation(answer[2] , operatorList.get(3) , termsList.get(4));

                    finalAnswer = answer [3];

                } else if(numTerms == 6){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));
                    answer[3] = Operation(answer[2] , operatorList.get(3) , termsList.get(4));
                    answer[4] = Operation(answer[3] , operatorList.get(4) , termsList.get(5));

                    finalAnswer = answer [4];

                }

                break;
            }
        }
        return finalAnswer;
    }


    public int Operation (int num1 ,char op, int num2){

        int cal = 0;

        switch (op){
            case '+' : {
                cal = num1 + num2;
                arrayList.add(cal);
                break;
            }
            case '-' : {
                cal = num1 - num2;
                arrayList.add(cal);
                break;
            }
            case '*' : {
                cal = num1 * num2;
                arrayList.add(cal);
                break;
            }
            case '/' : {
                cal = num1 / num2;
                arrayList.add(cal);
                break;
            }
        }

        return cal;
    }
}
