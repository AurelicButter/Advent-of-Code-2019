import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

//Range of the two numbers.
//Must have a pair of adjacent digits.
//Digits must not decrease. Only stay the same or increase

public class part2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split("-");
        scanner.close();

        int counter = 0;
        int start = Integer.parseInt(numbers[0]);
        int end = Integer.parseInt(numbers[1]);
        int range = end - start;
        
        for (int x = 0; x < range; x++) {
            int currNumber = start + x;
            String[] arrCurrNum = Integer.toString(currNumber).split("");

            //If number has instant 0 or 1, fail check. 1st number greater than 1.
            boolean zeroCheck = Arrays.stream(arrCurrNum).anyMatch("0"::equals);
            boolean oneCheck = Arrays.stream(arrCurrNum).anyMatch("1"::equals);

            if (oneCheck == false && zeroCheck == false) {
                boolean adjCheck = false; //True if a pair is detected
                boolean decreaseCheck = true; // False if number order decreases
                boolean trioCheck = false; //True if no pair and a trio is detected
                int highestNum = 2;
                int[] pairInts = new int[3]; //Digit values of detected pair.
                int pairCount = 0;

                for (int w = 2; w < 10; w++) {
                    String currPair = Integer.toString(w * 10 + w);
                    boolean check = Integer.toString(currNumber).contains(currPair);
                
                    if (check == true) {
                        pairInts[pairCount] = w;
                        pairCount += 1;
                    }
                }

                switch (pairCount) {
                    case 1:
                        String currTrio = Integer.toString(pairInts[0] * 100 + pairInts[0] * 10 + pairInts[0]); 
                        trioCheck = Integer.toString(currNumber).contains(currTrio);
                        break;
                    case 2:
                        //Check both pairs.
                        String currTrio1 = Integer.toString(pairInts[0] * 100 + pairInts[0] * 10 + pairInts[0]); 
                        String currTrio2 = Integer.toString(pairInts[1] * 100 + pairInts[1] * 10 + pairInts[1]); 
                        
                        boolean trio1Check = Integer.toString(currNumber).contains(currTrio1);
                        boolean trio2Check = Integer.toString(currNumber).contains(currTrio2);

                        if (trio1Check && trio2Check) {
                            trioCheck = true;
                        }                        
                        break;                        
                    case 3:
                        break;
                }

                for (int y = 0; y < 5; y++) {
                    int startNum = Integer.parseInt(arrCurrNum[y]);
                    int midNum = Integer.parseInt(arrCurrNum[y + 1]);

                    if (startNum >= highestNum) {
                        highestNum = startNum;
                    } else {
                        decreaseCheck = false;
                    }

                    if (midNum >= highestNum) {
                        highestNum = midNum;
                    } else {
                        decreaseCheck = false;
                    }
    
                    if (startNum == midNum) {
                        adjCheck = true;
                    }
                }
    
                if (adjCheck && decreaseCheck && trioCheck == false) {
                    counter += 1;
                }
            }
        }

        System.out.println("Total possibles: " + Integer.toString(counter));
    }
}