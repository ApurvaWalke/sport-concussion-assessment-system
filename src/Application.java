import java.util.ArrayList;
import java.util.Scanner;


public class Application {

    String name;
    static String[] symptomsList;
    public static Scanner scan = new Scanner(System.in);
    public String current_rating = "";

    ArrayList<String> recordDatabase = new ArrayList<>();
 
    public static  int gameid = 1;
    ArrayList<Integer> symptomCountAccrossGames = new ArrayList<>();

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RESET = "\u001B[0m";
    void viewRiskIndicator() {



        if (current_rating == "No Difference"){
            System.out.println(TEXT_GREEN + "No Significant Difference in Rating. Low Risk Factor" + TEXT_RESET);
        }
        else if (current_rating == "Unsure") {
            System.out.println(TEXT_YELLOW +  "Unsure about the Rating. Medium Risk Factor" + TEXT_RESET);
        }
        else if (current_rating == "Very Different"){
            System.out.println(TEXT_RED + "Very different Rating. High Risk Factor" + TEXT_RESET);

        }
        else{
            System.out.println(current_rating);
        }
            

    
    }
    
    
    void viewSymptomSummary() {
        
        getData();
    }
    
    
    void enterSymptoms() {

        ArrayList<Integer> gameData = fillSymptomAssessmentForm();

        System.out.println("Calculating Symptom Summary...");

        symptomSummary(gameData);


        System.out.println("Saving Symptoms Data to the Database ...");

        saveData(gameData);



    } 

    private void symptomSummary(ArrayList<Integer> gameData) {

        int totalSymptoms = numbeOfSymptoms(gameData);

        // System.out.println("Total Number of Symptoms: " + Integer.toString(totalSymptoms));

        int symtomSeverityScore = symptomSeverityScore(gameData);

        //System.out.println("Symptom Severity Score:" + Integer.toString(symtomSeverityScore));

        current_rating = overallRating(totalSymptoms, symtomSeverityScore, recordDatabase);

        //System.out.println(current_rating);

        String rating_summary0 = "GameID:" + Integer.toString(gameid);

        gameid++; 

       String rating_summary1 = "Total Number of Symptoms: " + Integer.toString(totalSymptoms);

       String rating_summary2 = "Symptom Severity Score:" + Integer.toString(symtomSeverityScore);

       String rating_summary3 = current_rating;

       String ratingForTheGame = rating_summary0 + "\n" + rating_summary1 + "\n" + rating_summary2 + "\n" + rating_summary3;

       // String[] current_record = new String[] {rating_summary0, rating_summary1, rating_summary2, rating_summary3}; 

       //System.out.println("on line 74");
        //System.out.println(ratingForTheGame);
       

       //System.out.println(ratingForTheGame);

       recordDatabase.add(ratingForTheGame);
       symptomCountAccrossGames.add(totalSymptoms);

    

    }


  


    private String overallRating(int totalSymptoms, int symtomSeverityScore, ArrayList<String> recordDatabase2) {

        String output = ""; 

        if (recordDatabase2.size() == 0){

            output = "No Overall Rating as no previous game";
        }
        else {

            //System.out.println("on line 99");

            var previous_record = recordDatabase.get(gameid-2);
            int previousSymptomCount = symptomCountAccrossGames.get(gameid-2);

            int totalSymptomDifference = totalSymptoms - previousSymptomCount;

            if (totalSymptomDifference <3 && symtomSeverityScore < 10){
                output = "No Difference";
            }
            else if (totalSymptomDifference <3 && symtomSeverityScore >= 10){
                output = "Unsure";
            }
            else if (totalSymptomDifference >=3 || symtomSeverityScore >= 15){
                output = "Very Different";
            }

            else{
                output = "out of context";
            }

        }
    
        return output;
    }




    private int symptomSeverityScore(ArrayList<Integer> gameData) {

        int severityScore = 0;

        for (int i=0; i<gameData.size(); i++){

            severityScore = severityScore + gameData.get(i);

        }

        return severityScore;
    }


    private int numbeOfSymptoms(ArrayList<Integer> gameData) {

        int numSymtoms = 0;

        for (int i=0; i<gameData.size(); i++){

            if (gameData.get(i) > 0){
                numSymtoms++;
            }

            }

            return numSymtoms;
    }


    private void saveData(ArrayList<Integer> gameData) {

  

    }

    void  getData(){

        System.out.println("Printing Latest Record" );
        for (int i = recordDatabase.size()-1; i >=0; i--) {
            
            System.out.println(recordDatabase.get(i));
            System.out.println();

        }

    
    }


    public static ArrayList<Integer> fillSymptomAssessmentForm(){
        ArrayList<Integer> symptomScoreList = new ArrayList<Integer>();

        /*
        Test list

        symptomsList = new String[] {"Headache", "Pressure in Head", "Neck Pain"};

        */


        
        symptomsList = new String[] {"Headache", "Pressure in Head", "Neck Pain", "Nausea or Vomiting", "Dizziness",
                                    "Blurred vision", "Balance problems", "Sensitivity to light", "Sensitivity to noise",
                                    "Feeling slowed down", "Feeling like 'in a fog'", "Don't feel right",
                                    "Difficulty concentrating", "Difficulty remembering", "Fatigue or low energy",
                                    "Confusion", "Drowsiness", "Trouble falling asleep", "More emotional",
                                    "Irritability", "Sadness", "Nervous or Anxious"};

        
    
        System.out.println();
        System.out.println("\t Enter your pain score from 0 - 6: none(0), mild(1-2), moderate(3-4), severe(5-6)");
    
        for (String s:symptomsList){
          System.out.println();
          System.out.print("\t " + s + ": ");
          int userScoreEntry = scan.nextInt();
          if (userScoreEntry >= 0 && userScoreEntry <= 6){
            symptomScoreList.add(userScoreEntry);
          }
          else{
            System.out.println();
            System.out.println("\t Invalid Input. Enter pain score between 0 - 6");
            System.out.print("\t " + s + ": ");
            userScoreEntry = scan.nextInt();
            symptomScoreList.add(userScoreEntry);
          }
        }
        return symptomScoreList;
    }

}

    