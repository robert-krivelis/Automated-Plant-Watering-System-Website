package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodSelectionAlgorithm {
    // private Hamper hamper = new Hamper();
    private ArrayList<Integer> hamperClientsByType; // we only need one
    private ArrayList<Food> allFood;

    // private HashMap<String, Integer> maxCaloriesInEachCategory;
    private ArrayList<Integer> maxCaloriesInEachCategory = new ArrayList<Integer>();
    private ArrayList<Integer> calorieTargetsForEachCategory;
    private int excessCaloriesMax; // calories in all food - targets
    private int maxCalories; // calories in all food
    private int excessCaloriesMin; // how few calories over excess - will change due to algo
    private ArrayList<ArrayList<Integer>> bestHamper; // best hamper found (fewest excess cals) - will change due to
                                                      // algo

    private ArrayList<ArrayList<Integer>> allFoodSimpleArray;

    FoodSelectionAlgorithm(ArrayList<Integer> hamperClientsByType, ArrayList<Food> allFood,
            ArrayList<ClientTypes> clientRequirementsByType) {
        this.hamperClientsByType = hamperClientsByType;
        this.allFood = allFood;
        this.maxCaloriesInEachCategory = calculateMaxCaloriesInEachCategory();
        this.calorieTargetsForEachCategory = calculateCalorieTargetsForEachCategory(clientRequirementsByType);
        this.maxCalories = sumArrayList(
                this.maxCaloriesInEachCategory);
        this.excessCaloriesMax = this.maxCalories
                - sumArrayList(this.calorieTargetsForEachCategory);
        // this.allFoodSimpleArray = foodArrayToCaloriesArray(this.allFood);

    }

    void simulatedAnnealingAlgorithm() {
        // heuristic search algorithm for finding most efficient hamper
        // algorithm search parameters
        // internal struggle: does this algorithm use just simple arraylist ints or
        // actual hampers
        double TEMP = 10000;
        double COOL = 0.999;
        double MIN_TEMP = 0.01;

        Hamper currentSolution = new Hamper();
        for (Food food : this.allFood) {
            currentSolution.addFood(food);
        }
        Hamper newSolution = new Hamper(currentSolution.getHamperFood());
        // keep track of best solution so far
        // this.bestHamper = (ArrayList<Integer>) currentSolution.clone();
        this.bestHamper = currentSolution;
        for (ArrayList<Integer> foodSimpleArray : currentSolution) // adjust available food according to what was added
                                                                   // to the hamper
            this.allFoodSimpleArray.remove(foodSimpleArray);

        while (TEMP > MIN_TEMP) {

        }
    }

    private ArrayList<Integer> calculateMaxCaloriesInEachCategory() {
        ArrayList<Integer> cals = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
        for (Food food : this.allFood) {
            cals.set(0, cals.get(0) + food.getWholeGrains());
            cals.set(1, cals.get(1) + food.getFruitVeggies());
            cals.set(2, cals.get(2) + food.getProtein());
            cals.set(3, cals.get(3) + food.getOther());
        }
        return cals;
    }

    private Integer sumArrayList(ArrayList<Integer> arr) {
        Integer sum = 0;
        for (Integer val : arr)
            sum += val;
        return sum;
    }

    private ArrayList<ArrayList<Integer>> foodArrayToCaloriesArray(ArrayList<Food> foodArray) {
        // this needs to be like a double list right?
        ArrayList<ArrayList<Integer>> caloriesFullArray = new ArrayList<ArrayList<Integer>>();
        for (Food food : foodArray) {
            ArrayList<Integer> caloriesCategoriesArray = new ArrayList<Integer>();
            caloriesCategoriesArray.add(food.getWholeGrains());
            caloriesCategoriesArray.add(food.getFruitVeggies());
            caloriesCategoriesArray.add(food.getProtein());
            caloriesCategoriesArray.add(food.getOther());
            caloriesFullArray.add(caloriesCategoriesArray);
        }
        return caloriesFullArray;

    }

    private ArrayList<Integer> calculateCalorieTargetsForEachCategory(ArrayList<ClientTypes> clientRequirementsByType) {

        int numOfDaysInWeek = 7;
        // for each category (0=grains,1=veg,2=prot,3=other), sum the calorie
        // requirements for the entire family (this.hamperClientsByType) and store it in
        // CalorieTargetsForEachCategory
        // for (int requirement: this.hamperClientsByType)
        int numAdultMales = this.hamperClientsByType.get(0);
        int numAdultFemales = this.hamperClientsByType.get(1);
        int numChildOverEight = this.hamperClientsByType.get(2);
        int numChildUnderEight = this.hamperClientsByType.get(3);

        int targetWholeGrains = 0;
        int targetFruitVeggies = 0;
        int targetProtein = 0;
        int targetOther = 0;
        // needed whole grains
        targetWholeGrains += clientRequirementsByType.get(0).getWholeGrains() * numAdultMales
                + clientRequirementsByType.get(1).getWholeGrains() * numAdultFemales
                + clientRequirementsByType.get(2).getWholeGrains() * numChildOverEight
                + clientRequirementsByType.get(3).getWholeGrains() * numChildUnderEight;
        // needed fruit veggies
        targetFruitVeggies += clientRequirementsByType.get(0).getFruitVeggies() * numAdultMales
                + clientRequirementsByType.get(1).getFruitVeggies() * numAdultFemales
                + clientRequirementsByType.get(2).getFruitVeggies() * numChildOverEight
                + clientRequirementsByType.get(3).getFruitVeggies() * numChildUnderEight;
        // needed protein
        targetProtein += clientRequirementsByType.get(0).getProtein() * numAdultMales
                + clientRequirementsByType.get(1).getProtein() * numAdultFemales
                + clientRequirementsByType.get(2).getProtein() * numChildOverEight
                + clientRequirementsByType.get(3).getProtein() * numChildUnderEight;
        // needed other
        targetOther += clientRequirementsByType.get(0).getOther() * numAdultMales
                + clientRequirementsByType.get(1).getOther() * numAdultFemales
                + clientRequirementsByType.get(2).getOther() * numChildOverEight
                + clientRequirementsByType.get(3).getOther() * numChildUnderEight;

        targetWholeGrains *= numOfDaysInWeek;
        targetFruitVeggies *= numOfDaysInWeek;
        targetProtein *= numOfDaysInWeek;
        targetOther *= numOfDaysInWeek;
        List<Integer> targets = Arrays.asList(targetWholeGrains, targetFruitVeggies, targetProtein, targetOther);
        ArrayList<Integer> calorieTargets = new ArrayList<Integer>(targets); // will be size 4
        return calorieTargets;
    }

}