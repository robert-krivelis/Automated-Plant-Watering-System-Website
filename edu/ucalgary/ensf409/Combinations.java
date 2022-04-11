package edu.ucalgary.ensf409;

// generates all combinations of an integer of length i
import java.util.ArrayList;

public class Combinations {

    public ArrayList<Integer[]> allCombinations = new ArrayList<Integer[]>();

    public Combinations() {
    }

    // recursive get all combinations function
    void recursiveCreateCombinations(Integer[] arr, int len, int startPosition, Integer[] result) {
        if (len == 0) {
            this.allCombinations.add(result.clone());
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            recursiveCreateCombinations(arr, len - 1, i + 1, result);
        }
    }

    public ArrayList<Integer[]> getAllCombinations(){
        return this.allCombinations;
    }
}
