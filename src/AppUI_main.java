import java.util.Scanner;

class BadInputException extends Exception {
    String s;

    BadInputException(String s){
        this.s = s;
    }
    
}
public class AppUI_main {

    public static Scanner loginscan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        int menuChoice = 0;

        Application application = new Application(); 

        displayMenu();
        System.out.print("Your Choice: ");
        menuChoice = loginscan.nextInt();

        while (true){

            try {         

                switch(menuChoice){
                    case 1:
                        application.enterSymptoms();
                        System.out.println("Added Symtpoms to the Database");
                        System.out.println("Going back to Main Menu");
                        System.out.println();
                        displayMenu();
                        menuChoice = loginscan.nextInt();
                        break;
        
                    case 2: 
        
                        application.viewSymptomSummary();
                        System.out.println();
                        displayMenu();
                        menuChoice = loginscan.nextInt();
        
                        break;
                    case 3:
                        application.viewRiskIndicator();
                        System.out.println();
                        System.out.println();
                        System.out.println("Going back to Main Menu");
                        displayMenu();
                        menuChoice = loginscan.nextInt();
        
                        break;
        
                    case 4:
                        System.out.println();
                        System.out.println("Exiting....");
                        System.exit(0);
                        break;
                        
                    default:
                    System.out.println("Enter Number from 1-4");
                    menuChoice = loginscan.nextInt();


            }
    
            
        }
        catch (Exception e){
            System.out.println("Bad Input. Try again");
            
            continue;
        }
 

        }
    
    }


    public static void displayMenu(){


        System.out.println("Welcome to Sport Concussion Assessment System");
        System.out.println("Please select one of the following options");
        System.out.println("1. Enter Symptoms");
        System.out.println("2. Display Symptoms Summary");
        System.out.println("3. Am I at Risk?");
        System.out.println("4. Exit");
    

    }


}

