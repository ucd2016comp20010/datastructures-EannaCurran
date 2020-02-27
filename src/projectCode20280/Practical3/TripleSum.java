package projectCode20280.Practical3;

public class TripleSum {

    public static int tripleSum(int[] number){
        int numOfTriples = 0;

        for(int i = 0; i < number.length - 2; i++){
            if(number[i] + number[i+1] + number[i+2] == 0){
                numOfTriples++;
            }
        }
        return numOfTriples;
    }
}
