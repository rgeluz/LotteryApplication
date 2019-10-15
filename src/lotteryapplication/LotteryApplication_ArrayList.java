
package lotteryapplication;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner; 

/**
 * File: LotteryApplication_ArrayList.java
 * @author Roman Geluz
 * Date: 07/11/2017
 * Course: CISC 190 Java Programming
 * Assignment: 
 * Write a program that simulates a lottery. The program should have an array of
 * five integers named lottery and should generate a random number in the
 * range of 0 through 9 for each element in the array. The user should enter five
 * digits, which should be stored in an integer array named user. The program is to
 * compare the corresponding elements in the two arrays and keep a count of the
 * digits that match. For example, the following shows the lottery array and the user
 * array with sample numbers stored in each. There are two matching digits
 * (elements 2 and 4).
 * 
 * lottery array:
 * ____________
 * 7 4 9 1 3
 * ____________
 * 
 * user array:
 * ___________
 * 4 2 9 7 3
 * ___________
 * 
 * The program should display the random numbers stored in the lottery array and
 * the number of matching digits. If all of the digits match, display a message
 * proclaiming the user as a grand prize winner.
 * 
 * (This version uses an ArrayList implementation as opposed to an Array implementation)
 * 
 */
public class LotteryApplication_ArrayList {
    
    //Constants
    public static final boolean TEST_MODE = true;
    public static final int TOTAL_NUMBER_OF_LOTTERY_NUMBERS = 5;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("$                                                   $");
        System.out.println("$     WELCOME TO THE LOTTERY APPLICATION            $");
        System.out.println("$     (ArrayList version)                           $");
        System.out.println("$     By: Roman Geluz                               $");
        System.out.println("$     CISC 190 Java Programming                     $");
        System.out.println("$                                                   $");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("");
        
        //Ask user for lottery numbers.
        List<Integer> userLotteryNumbers = requestUserLotteryNumbers();
        
        //Generate a set of winning lottery numbers.
        List<Integer> winningLotteryNumbers = new ArrayList<Integer>();
        winningLotteryNumbers = generateWinningLotteryNumbers(winningLotteryNumbers);
        
        //Process results
        showResults(userLotteryNumbers, winningLotteryNumbers);
    }
    
    /**
     * Request from user their lottery numbers. 
     * Performs input validation. Numbers must be between 0 and 9.
     * @param numberOfElements
     * @return 
     */
    private static List<Integer> requestUserLotteryNumbers(){
        System.out.println("Please enter your " + TOTAL_NUMBER_OF_LOTTERY_NUMBERS +
                          " lottery numbers:");   
        List<Integer> userLotteryNumber = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        int count =0;
        while(count<TOTAL_NUMBER_OF_LOTTERY_NUMBERS){       
            int number = -1;          
            if(count==0){
                System.out.print("1) Enter a number between 0 and 9: ");   
            }else{
                System.out.print((count+1) + ") Enter another number between 0 and 9: ");
            }
            number = sc.nextInt();
            while (number < 0 || number > 9){
                System.out.println("invalid number, number must be between 0 and 9: "); 
                if(count==0){
                    System.out.print("1) Enter a number between 0 and 9: ");   
                }else{
                    System.out.print((count+1) + ") Enter another number between 0 and 9: ");
                }
                number = sc.nextInt();
            }
            userLotteryNumber.add(number);
            count++;
        }   
        return userLotteryNumber;
    }
    
    /**
     * Generate a set of winning lottery numbers.
     * calls upon the generateRandomNumber() method.
     * also produces fixed set of numbers for testing.
     * @param lotteryNumbers
     * @param numberOfElements
     * @return 
     */
    private static List<Integer> generateWinningLotteryNumbers(List<Integer> lotteryNumbers){     
        if(TEST_MODE){ //for testing purposes
            
            //Test set#1
            /*lotteryNumbers.add(1);
            lotteryNumbers.add(2);
            lotteryNumbers.add(3);
            lotteryNumbers.add(4);
            lotteryNumbers.add(5);*/
            
            //Test set#2
            lotteryNumbers.add(7);
            lotteryNumbers.add(4);
            lotteryNumbers.add(9);
            lotteryNumbers.add(1);
            lotteryNumbers.add(3);
        } else {
            for(int i=0; i<TOTAL_NUMBER_OF_LOTTERY_NUMBERS; i++){
                lotteryNumbers.add(generateRandomNumber(0,9));
            }
        }
        return lotteryNumbers;
    }
    
    /**
     * Generates a number between min and max.
     * @param min
     * @param max
     * @return 
     */
    private static int generateRandomNumber(int min, int max){
        return (min + (int)(Math.random() * ((max - min) + 1)));
    }
    
    /**
     * Show the results of matching the user's numbers with the winning lottery numbers
     * @param userLotteryNumbers
     * @param winningLotteryNumbers 
     */
    private static void showResults(List<Integer> userLotteryNumbers, List<Integer> winningLotteryNumbers){
        System.out.println("");
        System.out.println("_____________________________________________________");
        System.out.println("____________________R_E_S_U_L_T_S____________________");
        System.out.println("");

        //check to see if the user lottery numbers have any matching numbers.
        checkForMatchingNumbers(userLotteryNumbers, winningLotteryNumbers);

        System.out.println("");
        System.out.println("");
        System.out.println("_____________________________________________________");
        System.out.println("");
        System.out.println("");
    }
    
    /**
     * compare the user's lottery numbers with the winning lottery numbers.
     * Display the number of matches if any.
     * If there are matches, indicate the index of the matching number.
     * If the user's lottery numbers match all winning lottery numbers display dollar ASCii art
     * @param userLotteryNumbers
     * @param winningLotteryNumbers
     * @return 
     */
    private static void checkForMatchingNumbers(List<Integer> userLotteryNumbers, List<Integer> winningLotteryNumbers){
        List<Boolean> matchingElementList = new ArrayList<Boolean>();
        int matchCount = 0;
        //intialize matchingElement's elements to all false. 
        for(int i=0; i<userLotteryNumbers.size(); i++){
            matchingElementList.add(false);
        }
        //for each match, set true to the matching number's index.
        for(int i=0; i<userLotteryNumbers.size(); i++){
            if(userLotteryNumbers.get(i)==winningLotteryNumbers.get(i)){
                matchingElementList.set(i, true); 
                matchCount++;
            }
        }
        
        //display match count
        System.out.println("");
        switch(matchCount){
            case 0: 
                System.out.println("There are no matching numbers."); 
                break;
            case 1: 
                System.out.println("You are a winner!");
                System.out.println("There is 1 matching number.");
                break;
            case 5:
                System.out.println("Congratulations you are the grand prize winner!!!!");
                System.out.println("You have matched all five winning numbers!");
                break;
            default: 
                System.out.println("Your are a winner!");
                System.out.println("There are " + matchCount + " matching numbers.");
                break;
        }
        System.out.println("");
        
        //Print which indexes contain the matching numbers
        if(matchCount>0){
            String output = "";
            switch(matchCount){
                case 1: 
                    for(int i=0; i<matchingElementList.size(); i++){
                        if(matchingElementList.get(i))
                            output+=i;
                    }
                    System.out.println("The matching number is at index: " + output);
                    break;
                default:
                    int numOfMatchesFound = 0;
                    for(int i=0; i<matchingElementList.size(); i++){
                        if(matchingElementList.get(i)){
                            numOfMatchesFound++;
                            if(numOfMatchesFound==matchCount)
                                output+=i;
                            else
                                output+= i+", ";  
                        }
                    }
                    System.out.println("The matching numbers are at indexes: " + output);
                    break;
            } 
            System.out.println("");
            printLotteryNumbers(userLotteryNumbers, "Your Lottery Numbers");
            printMatchingArrows(matchingElementList);
            printLotteryNumbers(winningLotteryNumbers, "Winning Lottery Numbers");
        } else {
            printLotteryNumbers(userLotteryNumbers, "Your Lottery Numbers");
            System.out.println("");
            printLotteryNumbers(winningLotteryNumbers, "Winning Lottery Numbers");
        } 
        //If grand prize winner display Dollar Sign ASCii art
        if(matchCount==5)
            printMoneySign();
    }
    
    /**
     * Display set of lottery numbers.
     * @param lotteryNumbers
     * @param name 
     */
    private static void printLotteryNumbers(List<Integer> lotteryNumbers, String name){ 
        System.out.println(name+":");
        System.out.println("----------------------------------------------");
        String setOfNumbers = "";
        for(Integer lotteryNumber : lotteryNumbers){
            setOfNumbers+="     "+lotteryNumber;
        }
        System.out.println(setOfNumbers);
        System.out.println("----------------------------------------------"); 
    }
    
    /**
     * Show matching arrows for matching numbers.
     * @param lotteryNumbers
     * @param name
     * @param matchingElements 
     */
    private static void printMatchingArrows(List<Boolean> matchingElements){
        String caretsRow1 = "";
        String caretsRow2 = "";
        String caretsRow3 = "";
        String caretsRow4 = "";
        String caretsRow5 = "";
        int index = 0;
        for(Boolean matchingElement : matchingElements){
            if(matchingElement) {
                caretsRow1+="     "+"^";
                caretsRow2+="     "+"|";
                caretsRow3+="   "+"("+index+")";
                caretsRow4+="     "+"|";
                caretsRow5+="     "+"v";
            } else {
                caretsRow1+="     "+" ";
                caretsRow2+="     "+" ";
                caretsRow3+="     "+" ";
                caretsRow4+="     "+" ";
                caretsRow5+="     "+" ";
            }
            index++;
        }
        System.out.println(caretsRow1);
        System.out.println(caretsRow2);
        System.out.println(caretsRow3);
        System.out.println(caretsRow4);
        System.out.println(caretsRow5);
    }
    
    /**
     * Art is from http://www.chris.com/ascii/index.php?art=objects/money
     */
    private static void printMoneySign(){
        System.out.println("");
        System.out.println("");
        System.out.println("  G R A N D  P R I Z E  W I N N E R ! ! ! !         ");
        String moneySign =  "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$,,,$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$\"OOO\"$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$!OOO!$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$,\"OOO\",$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$,\"OOOOOOO\",$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$!\"OOOOOOOOOOO\"!$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$,\"OOOOOOOOOOOOOOO\",$$$$$$$$$"+"\n"+
                            "$$$$$$$$,1OOOOOOOOOOOOOOOOOOO7,$$$$$$$"+"\n"+
                            "$$$$$$,!OOOOOOOOOOOOOOOOOOOOOO\"$$$$$$$"+"\n"+
                            "$$$$$$!OOOOOOOOO/\"\"\"\"'\\OOOOOOOO1$$$$$$"+"\n"+
                            "$$$$$$\"OOOOOOOOOO\"$$$$!OOOOOOOO\"$$$$$$"+"\n"+
                            "$$$$$$\"OOOOOOOOOOO!$$$\",OOOOOOO!$$$$$$"+"\n"+
                            "$$$$$$$\",OOOOOOOOOO\",$$$\"\"\"\"\"\"\"$$$$$$$"+"\n"+
                            "$$$$$$$$\"!OOOOOOOOOOO\",$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$\",OOOOOOOOOOO\",$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$\"OOOOOOOOOOOO\",$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$\"!OOOOOOOOOOOO\"+$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$\"==OOOOOOOOOOO,$$$$$$$$"+"\n"+
                            "$$$$$$$$,,,,,,$$$$\",OOOOOOOOOO\",$$$$$$"+"\n"+
                            "$$$$$$,\"OOOOOO',$$$$\",OOOOOOOOO!$$$$$$"+"\n"+
                            "$$$$$$1OOOOOOOO.\"~~~\"OOOOOOOOOO!$$$$$$"+"\n"+
                            "$$$$$$!OOOOOOOOOOOOOOOOOOOOOOO1$$$$$$$"+"\n"+
                            "$$$$$$\",OOOOOOOOOOOOOOOOOOOOO,\"$$$$$$$"+"\n"+
                            "$$$$$$$\",OOOOOOOOOOOOOOOOOOO!$$$$$$$$$"+"\n"+
                            "$$$$$$$$$1OOOOOOOOOOOOOOOO,1$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$\"~~,OOOOOOOOOO,\"$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$\"\"\"1OOO1\"'$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$!OOO!$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$,OOO,$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$\"\"\"$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+"\n"+
                            "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+"\n"+
                            "        Dollar, By calendron          ";
        System.out.println(moneySign);
    }
 
}
