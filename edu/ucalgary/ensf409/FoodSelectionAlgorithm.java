package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FoodSelectionAlgorithm {
    private Hamper hamper = new Hamper();
    private ArrayList<Integer> hamperClientsByType; // we only need one
    private ArrayList<Food> allFood;
    // private HashMap<String, Integer> maxCaloriesInEachCategory;
    private ArrayList<Integer> maxCaloriesInEachCategory;
    private ArrayList<Integer> calorieTargetsForEachCategory;

    FoodSelectionAlgorithm(ArrayList<Integer> hamperClientsByType, ArrayList<Food> allFood,
            ArrayList<ClientTypes> clientRequirementsByType) {
        this.hamperClientsByType = hamperClientsByType;
        this.allFood = allFood;
        this.maxCaloriesInEachCategory = calculateMaxCaloriesInEachCategory();
        this.calorieTargetsForEachCategory = calculateCalorieTargetsForEachCategory(clientRequirementsByType);

    }

    private ArrayList<Integer> calculateMaxCaloriesInEachCategory() {
        ArrayList<Integer> cals = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
        for (Food food : this.allFood) {
            this.maxCaloriesInEachCategory.set(0, this.maxCaloriesInEachCategory.get(0) + food.getWholeGrains());
            this.maxCaloriesInEachCategory.set(1, this.maxCaloriesInEachCategory.get(1) + food.getFruitVeggies());
            this.maxCaloriesInEachCategory.set(2, this.maxCaloriesInEachCategory.get(2) + food.getProtein());
            this.maxCaloriesInEachCategory.set(3, this.maxCaloriesInEachCategory.get(3) + food.getOther());
        }
        return cals;
    }

    private ArrayList<Integer> calculateCalorieTargetsForEachCategory(ArrayList<ClientTypes> clientRequirementsByType) {
        ArrayList<Integer> calorieTargets = new ArrayList<Integer>(); // will be size 4
        int numOfDaysInWeek = 7;
        // for each category (0=grains,1=veg,2=prot,3=other), sum the calorie
        // requirements for the entire family (this.hamperClientsByType) and store it in
        // CalorieTargetsForEachCategory
        // for (int requirement: this.hamperClientsByType)
        int numAdultMales = this.hamperClientsByType.get(0);
        int numAdultFemales = this.hamperClientsByType.get(1);
        int numChildOverEight = this.hamperClientsByType.get(2);
        int numChildUnderEight = this.hamperClientsByType.get(3);

        int requiredForAdultMale = clientRequirementsByType.get(0).getWholeGrains();
        int requiredForAdultFemale = clientRequirementsByType.get(1).getWholeGrains();
        int requiredForChildOverEight = clientRequirementsByType.get(2).getWholeGrains();
        int requiredForUnderEight = clientRequirementsByType.get(3).getWholeGrains();

        for (int clientType = 0; clientType < 4; clientType++) {
            int grainRequirement = clientRequirementsByType.get(clientType).getWholeGrains();
            int fruitVeggiesRequirement = clientRequirementsByType.get(clientType).getFruitVeggies();
            int proteinRequirement = clientRequirementsByType.get(clientType).getProtein();
            int otherRequirement = clientRequirementsByType.get(clientType).getOther();
                    
            int numOfClientsOfType = this.hamperClientsByType.get(clientType);
            calorieTargets.set(0, grainRequirement);


            int singleClientRequirement = clientRequirementsByType.get(clientType);
            int numberOfClientsOfType = this.hamperClientsByType.get(clientType);
            int totalCalsRequiredForType = singleClientRequirement.getWholeGrains()
                    * this.hamperClientsByType.get(clientType);
            calorieTargets.set(clientType, totalCalsForType);

        }
        // needed whole grains
        totWholeGrains += clientRequirementsByType.get(0).getWholeGrains() * adultMales
                + clientRequirementsByType.get(1).getWholeGrains() * adultFemales
                + clientRequirementsByType.get(2).getWholeGrains() * childOverEight
                + clientRequirementsByType.get(3).getWholeGrains() * childUnderEight;
        // needed fruit veggies
        totFruitVeggies += clientRequirementsByType.get(0).getFruitVeggies() * adultMales
                + clientRequirementsByType.get(1).getFruitVeggies() * adultFemales
                + clientRequirementsByType.get(2).getFruitVeggies() * childOverEight
                + clientRequirementsByType.get(3).getFruitVeggies() * childUnderEight;
        // needed protein
        totProtein += clientRequirementsByType.get(0).getProtein() * adultMales
                + clientRequirementsByType.get(1).getProtein() * adultFemales
                + clientRequirementsByType.get(2).getProtein() * childOverEight
                + clientRequirementsByType.get(3).getProtein() * childUnderEight;
        // needed other
        totOther += clientRequirementsByType.get(0).getOther() * adultMales
                + clientRequirementsByType.get(1).getOther() * adultFemales
                + clientRequirementsByType.get(2).getOther() * childOverEight
                + clientRequirementsByType.get(3).getOther() * childUnderEight;
        // needed total calories
        totCalories += clientRequirementsByType.get(0).getCalories() * adultMales
                + clientRequirementsByType.get(1).getCalories() * adultFemales
                + clientRequirementsByType.get(2).getCalories() * childOverEight
                + clientRequirementsByType.get(3).getCalories() * childUnderEight;

        totWholeGrains = totWholeGrains * numOfDaysInWeek;
        totFruitVeggies = totFruitVeggies * numOfDaysInWeek;
        totProtein = totProtein * numOfDaysInWeek;
        totOther = totOther * numOfDaysInWeek;
        totCalories = totCalories * numOfDaysInWeek;
    }

}