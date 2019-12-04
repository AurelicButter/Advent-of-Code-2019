import java.util.Scanner;
import java.util.Arrays;

//Range of the two numbers.
//Two adjacent digits
//Digits must not decrease. Only stay the same or increase

public class part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split("-");
        scanner.close();

        int counter = 0;
        int start = Integer.parseInt(numbers[0]);
        int range = Integer.parseInt(numbers[1]) - start; //End - start
        
        for (int x = 0; x < range; x++) {
            int currNumber = start + x;
            String[] arrCurrNum = Integer.toString(currNumber).split("");

            //If number has instant 0 or 1, fail check. 1st number greater than 1.
            boolean zeroCheck = Arrays.stream(arrCurrNum).anyMatch("0"::equals);
            boolean oneCheck = Arrays.stream(arrCurrNum).anyMatch("1"::equals);

            if (oneCheck == false && zeroCheck == false) {
                boolean adjCheck = false; //True if a pair is detected
                boolean decreaseCheck = true; // False if number order decreases
                int highestNum = 2;

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
    
                if (adjCheck && decreaseCheck) {
                    counter += 1;
                }
            }
        }

        System.out.println("Total possibles: " + Integer.toString(counter));
    }
}