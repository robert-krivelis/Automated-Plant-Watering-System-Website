package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodBankManager {
    private ArrayList<ArrayList<Integer>> allClientTypesFromUser;
    private ArrayList<Food> allFood;
    private ArrayList<ClientTypes> clientRequirementsByType;

    public FoodBankManager(ArrayList<ArrayList<Integer>> allClientTypesFromUser) {
        // on intialization get food from DB and set users 
        this.allClientTypesFromUser = allClientTypesFromUser;
        FoodInventoryDatabaseAccess foodDB = new FoodInventoryDatabaseAccess();
        this.allFood = foodDB.getAllFood();
        this.clientRequirementsByType = foodDB.getClientRequirementsByType();
    }
    public void useAlgorithm(){
        ArrayList<Integer> firstClient = allClientTypesFromUser.get(0);
        FoodSelectionAlgorithm foodAlgo = new FoodSelectionAlgorithm(
                firstClient, this.allFood, this.clientRequirementsByType);
    }
    public void generateOrder() {
        for (ArrayList<Integer> hamperClients : this.allClientTypesFromUser) {
            try {
                // hamperCreator only does one at a time. FoodBankManager creates multiple hampers.
                HamperCreator createdHamper = new HamperCreator(
                    hamperClients.get(0),
                    hamperClients.get(1),
                    hamperClients.get(2),
                    hamperClients.get(3)); // Order: AdultM AdultF ChildO8 ChildU8
                // somewhere there's going to be a call
                // to food selection algorithm. but not today :) 
                System.out.println(createdHamper.getTotCalories());
                System.out.println(createdHamper.getMinExcessCalories());
            } catch (InsufficientCaloriesForHamperException e) {
                generateErrorForm();
            }

        }
        // // creates hamper creator object and inputs client configuration for the hamper
        // HamperCreator createdHamper = new HamperCreator(0, 0, 0, 1); // AM AF CO8 CU8
        // // calculates total calories required for hamper

        // // adds food from inventory to hamper
        // System.out.println(createdHamper.getTotCalories());
        // // System.out.println(Arrays.toString(createdHamper.bruteForceMostEfficientHamper()));
        // System.out.println(createdHamper.getMinExcessCalories());
        // // createdHamper.bruteForceMostEfficientHamper();

        // // createdHamper.addFoodItemsToHamper();

        // // System.out.println(createdHamper.formattedTotalHamperRequirements());

        // // System.out.println(createdHamper.getHamper().formattedCaloriesInHamper());

        // // System.out.println(createdHamper.getHamper().formattedFoodInHamper());

        // // System.out.println(createdHamper.formattedInventoryList());

    }

    private void generateErrorForm() {
    }
}
